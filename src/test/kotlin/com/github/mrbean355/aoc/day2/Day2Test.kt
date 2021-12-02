package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.PuzzleTest

class Day2Test : PuzzleTest(Day2::class) {

    override val part1TestCases = mapOf(
        "day2/example.txt" to 150,
        "day2/puzzle.txt" to 1690020,
    )

    override val part2TestCases = mapOf(
        "day2/example.txt" to 900,
        "day2/puzzle.txt" to 1408487760,
    )
}