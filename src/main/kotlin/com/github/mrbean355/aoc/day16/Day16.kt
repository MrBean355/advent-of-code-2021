package com.github.mrbean355.aoc.day16

import com.github.mrbean355.aoc.base.Puzzle
import java.math.BigInteger

class Day16(input: List<String>) : Puzzle {

    private val binaryInput = input.first().toBinary()

    override fun part1(): Any {
        return readPackets(binaryInput, maxPackets = 1).single()
            .sumAllVersions()
    }

    override fun part2(): Any {
        return readPackets(binaryInput, maxPackets = 1).single()
            .evaluate()
    }

    private fun readPackets(
        binaryInput: String,
        fromIndex: Int = 0,
        maxPackets: Int = Int.MAX_VALUE,
    ): List<Packet> {
        var index = fromIndex
        val packets = mutableListOf<Packet>()

        while (true) {
            if (index >= binaryInput.length || binaryInput.substring(index).all { it == '0' }) {
                break
            }
            if (packets.size >= maxPackets) {
                break
            }

            val packetStart = index
            val version = binaryInput.substring(index, index + 3).toIntDecimal()
            index += 3
            val type = binaryInput.substring(index, index + 3).toIntDecimal()
            index += 3

            packets += if (type == 4) {
                val value = buildString {
                    while (true) {
                        val chunk = binaryInput.substring(index, index + 5)
                        index += 5
                        append(chunk.drop(1))
                        if (chunk.startsWith('0')) {
                            break
                        }
                    }
                }.toLongDecimal()
                Packet.Literal(value, version, index - packetStart)
            } else {
                val lengthType = binaryInput[index++]
                val args = if (lengthType == '0') {
                    val subPacketLength = binaryInput.substring(index, index + 15).toIntDecimal()
                    index += 15
                    val subPackets = binaryInput.substring(index, index + subPacketLength)
                    index += subPacketLength
                    readPackets(subPackets)
                } else {
                    val subPacketCount = binaryInput.substring(index, index + 11).toIntDecimal()
                    index += 11
                    readPackets(binaryInput, fromIndex = index, subPacketCount).also {
                        index += it.sumOf(Packet::size)
                    }
                }
                Packet.Operator(type, args, version, index - packetStart)
            }
        }

        return packets
    }

    private fun Packet.sumAllVersions(): Int {
        return when (this) {
            is Packet.Literal -> version
            is Packet.Operator -> version + args.sumOf {
                it.sumAllVersions()
            }
        }
    }

    private fun String.toBinary(): String {
        return map { it.toBinary() }.joinToString(separator = "")
    }

    private fun Char.toBinary(): String {
        return BigInteger(this.toString(), 16).toString(2).padStart(4, '0')
    }

    private fun String.toIntDecimal(): Int = toInt(2)

    private fun String.toLongDecimal(): Long = toLong(2)

    private sealed interface Packet {
        val version: Int
        val size: Int

        fun evaluate(): Long

        class Literal(
            val value: Long,
            override val version: Int,
            override val size: Int
        ) : Packet {

            override fun evaluate() = value

        }

        class Operator(
            val op: Int,
            val args: List<Packet>,
            override val version: Int,
            override val size: Int
        ) : Packet {

            override fun evaluate(): Long {
                return when (op) {
                    0 -> args.sumOf(Packet::evaluate)
                    1 -> args.fold(1) { acc, packet ->
                        acc * packet.evaluate()
                    }
                    2 -> args.minOf { it.evaluate() }
                    3 -> args.maxOf { it.evaluate() }
                    5 -> if (args[0].evaluate() > args[1].evaluate()) 1 else 0
                    6 -> if (args[0].evaluate() < args[1].evaluate()) 1 else 0
                    7 -> if (args[0].evaluate() == args[1].evaluate()) 1 else 0
                    else -> error("Unexpected operator: $op")
                }
            }
        }
    }
}