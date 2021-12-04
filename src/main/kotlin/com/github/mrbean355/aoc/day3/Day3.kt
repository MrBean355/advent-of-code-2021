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
        TODO("Not yet implemented")
    }
}