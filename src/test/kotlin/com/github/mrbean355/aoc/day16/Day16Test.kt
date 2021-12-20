package com.github.mrbean355.aoc.day16

import com.github.mrbean355.aoc.base.PuzzleTest

class Day16Test : PuzzleTest(Day16::class) {

    override val part1TestCases = mapOf(
        "day16/example1.txt" to 16,
        "day16/example2.txt" to 12,
        "day16/example3.txt" to 23,
        "day16/example4.txt" to 31,
        "day16/puzzle.txt" to 965,
    )

    override val part2TestCases = mapOf(
        "day16/example5.txt" to 3L,
        "day16/example6.txt" to 54L,
        "day16/example7.txt" to 7L,
        "day16/example8.txt" to 9L,
        "day16/example9.txt" to 1L,
        "day16/example10.txt" to 0L,
        "day16/example11.txt" to 0L,
        "day16/example12.txt" to 1L,
        "day16/puzzle.txt" to 116672213160L,
    )
}