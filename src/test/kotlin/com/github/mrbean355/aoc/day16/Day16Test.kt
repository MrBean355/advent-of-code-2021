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

    override val part2TestCases: Map<String, Any>
        get() = emptyMap()

}