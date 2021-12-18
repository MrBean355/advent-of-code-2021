package com.github.mrbean355.aoc.day16

import com.github.mrbean355.aoc.base.Puzzle
import java.math.BigInteger

private const val TYPE_LITERAL = "100"

class Day16(input: List<String>) : Puzzle {

    private val binaryInput = input.first().toBinary()

    override fun part1(): Any {
        var pointer = 0
        var sum = 0

        while (pointer < binaryInput.length - 7) {
            sum += binaryInput.substring(pointer, pointer + 3).toDecimal()
            pointer += 3
            val type = binaryInput.substring(pointer, pointer + 3)
            pointer += 3

            if (type == TYPE_LITERAL) {
                var literal = ""
                while (true) {
                    val part = binaryInput.substring(pointer, pointer + 5)
                    pointer += 5
                    literal += part.drop(1)
                    if (part.startsWith('0')) {
                        break
                    }
                }
            } else {
                val lengthType = binaryInput[pointer]
                ++pointer
                pointer += if (lengthType == '0') 15 else 11
            }
        }

        return sum
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }

    private fun String.toBinary(): String {
        return map { it.toBinary() }.joinToString(separator = "")
    }

    private fun Char.toBinary(): String {
        return BigInteger(this.toString(), 16).toString(2).padStart(4, '0')
    }

    private fun String.toDecimal(): Int {
        return toInt(2)
    }
}