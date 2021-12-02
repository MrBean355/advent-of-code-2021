package com.github.mrbean355.aoc.day1

import com.github.mrbean355.aoc.base.PuzzleTest

class Day1Test : PuzzleTest(Day1::class) {

    override val part1TestCases = mapOf(
        "day1/example.txt" to 7,
        "day1/puzzle.txt" to 1532,
    )

    override val part2TestCases = mapOf(
        "day1/example.txt" to 5,
        "day1/puzzle.txt" to 1571,
    )
}