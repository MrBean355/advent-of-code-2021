package com.github.mrbean355.aoc.day9

import com.github.mrbean355.aoc.base.PuzzleTest

class Day9Test : PuzzleTest(Day9::class) {

    override val part1TestCases = mapOf(
        "day9/example.txt" to 15,
        "day9/puzzle.txt" to 502,
    )

    override val part2TestCases = mapOf(
        "day9/example.txt" to 1134,
        "day9/puzzle.txt" to 1330560,
    )
}