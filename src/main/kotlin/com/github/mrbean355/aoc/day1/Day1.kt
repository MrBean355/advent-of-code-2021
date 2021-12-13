package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.Puzzle

class Day1(input: List<String>) : Puzzle {

    private val values = input.map(String::toInt)

    override fun part1(): Any {
        return countIncreasingValues(values)
    }

    override fun part2(): Any {
        val sums = values.windowed(3).map { it.sum() }
        return countIncreasingValues(sums)
    }

    private fun countIncreasingValues(input: List<Int>): Int {
        var count = 0
        var prev = input.first()
        input.drop(1).forEach { current ->
            if (current > prev) {
                ++count
            }
            prev = current
        }
        return count
    }
}