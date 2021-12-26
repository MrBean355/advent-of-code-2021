package com.github.mrbean355.aoc.day22

import com.github.mrbean355.aoc.base.PuzzleTest

class Day22Test : PuzzleTest(Day22::class) {

    override val part1TestCases = mapOf(
        "day22/example1.txt" to 39,
        "day22/example2.txt" to 590784,
        "day22/example3.txt" to 474140,
        "day22/puzzle.txt" to 524792,
    )

    override val part2TestCases: Map<String, Long>
        get() = emptyMap()

}