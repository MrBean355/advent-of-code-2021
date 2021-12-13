package com.github.mrbean355.aoc.day8

import com.github.mrbean355.aoc.base.Puzzle

class Day8(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val uniqueLengths = listOf(2, 3, 4, 7)

        return input.sumOf { line ->
            val (_, output) = line.split(" | ")
            output.split(' ').count {
                it.length in uniqueLengths
            }
        }
    }

    override fun part2(): Any {
        return input.sumOf { line ->
            val (signalPattern, output) = line.split(" | ")
            val signals = signalPattern.split(' ')
            val outputDigits = output.split(' ')
            val mapping = loadMapping(signals)

            outputDigits.joinToString("") { digit ->
                val decoded = buildString {
                    digit.forEach { ch ->
                        mapping.forEach { (k, v) ->
                            if (v == ch) {
                                append(k)
                            }
                        }
                    }
                }
                decodeDigit(decoded).toString()
            }.toInt()
        }
    }

    private fun loadMapping(signals: List<String>): Map<Char, Char?> {
        val mapping = mutableMapOf<Char, Char?>()

        val one = signals.single { it.length == 2 }
        val seven = signals.single { it.length == 3 }
        val four = signals.single { it.length == 4 }
        val eight = signals.single { it.length == 7 }

        mapping['a'] = (seven - one).single()

        signals.filter { it.length == 5 }.forEach { word ->
            val segments = word - four - mapping['a']
            if (segments.size == 1) {
                mapping['g'] = segments.first()
            }
        }

        mapping['e'] = (eight - four - mapping['a'] - mapping['g']).single()

        signals.filter { it.length == 6 }.forEach { word ->
            val segments = word - one - mapping['a'] - mapping['e'] - mapping['g']
            if (segments.size == 1) {
                mapping['b'] = segments.first()
            }
        }

        mapping['d'] = (eight - seven - mapping['b'] - mapping['e'] - mapping['g']).single()

        signals.filter { it.length == 6 }.forEach { word ->
            val segments = word.toSet() - mapping['a'] - mapping['b'] - mapping['d'] - mapping['e'] - mapping['g']
            if (segments.size == 1) {
                mapping['f'] = segments.first()
            }
        }

        mapping['c'] = (one.toSet() - mapping['f']).single()

        mapping.forEach { (t, u) ->
            check(u != null) { "No mapping found for '$t'" }
        }

        return mapping
    }

    private operator fun String.minus(other: String): Set<Char> {
        return toSet() - other.toSet()
    }

    private val segments = mutableListOf(
        /* 0 */ "abcefg",
        /* 1 */ "cf",
        /* 2 */ "acdeg",
        /* 3 */ "acdfg",
        /* 4 */ "bcdf",
        /* 5 */ "abdfg",
        /* 6 */ "abdefg",
        /* 7 */ "acf",
        /* 8 */ "abcdefg",
        /* 9 */ "abcdfg"
    )

    private fun decodeDigit(digit: String): Char {
        return segments.indexOfFirst { segment ->
            segment.length == digit.length && digit.all { it in segment }
        }.let {
            if (it == -1) {
                error("Couldn't decode $digit")
            }
            it.digitToChar()
        }
    }
}