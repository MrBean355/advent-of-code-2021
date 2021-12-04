package com.github.mrbean355.aoc.day3

import com.github.mrbean355.aoc.base.Puzzle

class Day3(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        val length = input.first().length
        val mostCommon = mutableListOf<Int>()

        for (i in 0 until length) {
            var one = 0
            input.forEach { line ->
                if (line[i] == '1') ++one
            }
            val zero = input.size - one
            mostCommon += (if (zero > one) 0 else 1)
        }

        val gamma = mostCommon.joinToString("")
        val epsilon = gamma.map { if (it == '0') '1' else '0' }.joinToString("")

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    override fun part2(): Number {
        val oxygenGenerator = input.reduce(true)
        val co2Scrubber = input.reduce(false)

        return oxygenGenerator.toInt(2) * co2Scrubber.toInt(2)
    }

    private fun List<String>.reduce(flip: Boolean): String {
        val bitCount = first().length
        val results = toMutableList()

        for (i in 0 until bitCount) {
            if (results.size <= 1) {
                break
            }
            val mostCommon = results.mostCommonBit(i) ?: '1'
            val toKeep = if (flip) {
                mostCommon
            } else {
                if (mostCommon == '1') '0' else '1'
            }
            results.removeAll { it[i] != toKeep }
        }

        return results.single()
    }

    private fun List<String>.mostCommonBit(position: Int): Char? {
        val ones = count { it[position] == '1' }
        val zeroes = size - ones
        return when {
            zeroes > ones -> '0'
            ones > zeroes -> '1'
            else -> null
        }
    }
}