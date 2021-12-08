package com.github.mrbean355.aoc.day8

import com.github.mrbean355.aoc.base.PuzzleTest

class Day8Test : PuzzleTest(Day8::class) {

    override val part1TestCases = mapOf(
        "day8/example.txt" to 26,
        "day8/puzzle.txt" to 255,
    )

    override val part2TestCases: Map<String, Any>
        get() = emptyMap()

}