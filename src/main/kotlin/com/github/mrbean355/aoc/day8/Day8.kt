package com.github.mrbean355.aoc.day8

import com.github.mrbean355.aoc.base.Puzzle

class Day8(private val input: List<String>) : Puzzle {

    private val uniqueLengths = listOf(2, 3, 4, 7)

    override fun part1(): Number {
        return input.sumOf { line ->
            val (_, output) = line.split(" | ")
            output.split(' ').count {
                it.length in uniqueLengths
            }
        }
    }

    override fun part2(): Number {
        return 0
    }
}