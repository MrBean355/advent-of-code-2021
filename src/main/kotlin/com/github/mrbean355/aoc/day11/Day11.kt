package com.github.mrbean355.aoc.day11

import com.github.mrbean355.aoc.base.Puzzle

private const val WIDTH = 10

class Day11(input: List<String>) : Puzzle {

    private val octopi = input.flatMap { line ->
        line.map { it.digitToInt() }
    }.toMutableList()

    override fun part1(): Number {
        var totalFlashes = 0

        repeat(100) {
            octopi.replaceAll { it + 1 }

            val flashedOctopi = mutableSetOf<Int>()

            while (true) {
                val flashingOctopi = octopi.withIndex()
                    .filter { it.index !in flashedOctopi && it.value > 9 }
                    .map { it.index }

                if (flashingOctopi.isEmpty()) {
                    break
                }

                flashingOctopi.forEach { index ->
                    flashedOctopi += index
                    getNeighbours(index).forEach {
                        octopi[it] = octopi[it] + 1
                    }
                }
                totalFlashes += flashingOctopi.size
            }

            flashedOctopi.forEach {
                octopi[it] = 0
            }
        }

        return totalFlashes
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun getNeighbours(index: Int): List<Int> {
        val row = index / WIDTH
        return listOfNotNull(
            validRowIndex(index - WIDTH - 1, row - 1),
            validRowIndex(index - WIDTH, row - 1),
            validRowIndex(index - WIDTH + 1, row - 1),
            validRowIndex(index - 1, row),
            validRowIndex(index + 1, row),
            validRowIndex(index + WIDTH - 1, row + 1),
            validRowIndex(index + WIDTH, row + 1),
            validRowIndex(index + WIDTH + 1, row + 1),
        )
    }

    private fun validRowIndex(index: Int, expectedRow: Int): Int? {
        return index.takeIf {
            it >= 0 && it < octopi.size && it / WIDTH == expectedRow
        }
    }

    private fun print() {
        octopi.chunked(WIDTH).forEach { row ->
            println(row.joinToString(separator = ""))
        }
    }
}