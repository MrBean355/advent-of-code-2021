package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.PuzzleTest

class Day10Test : PuzzleTest(Day10::class) {

    override val part1TestCases = mapOf(
        "day10/example.txt" to 26397,
        "day10/puzzle.txt" to 215229,
    )

    override val part2TestCases = mapOf(
        "day10/example.txt" to 288957L,
        "day10/puzzle.txt" to 1105996483L,
    )
}