package com.github.mrbean355.aoc.day12

import com.github.mrbean355.aoc.base.PuzzleTest

class Day12Test : PuzzleTest(Day12::class) {

    override val part1TestCases = mapOf(
        "day12/example1.txt" to 10,
        "day12/example2.txt" to 19,
        "day12/example3.txt" to 226,
        "day12/puzzle.txt" to 4691,
    )

    override val part2TestCases = mapOf(
        "day12/example1.txt" to 36,
        "day12/example2.txt" to 103,
        "day12/example3.txt" to 3509,
        "day12/puzzle.txt" to 140718,
    )
}