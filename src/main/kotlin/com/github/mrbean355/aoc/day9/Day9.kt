package com.github.mrbean355.aoc.day9

import com.github.mrbean355.aoc.base.Puzzle

class Day9(input: List<String>) : Puzzle {

    private val width = input.first().length
    private val heights = input.joinToString("").map(Char::digitToInt)

    override fun part1(): Number {
        return heights.withIndex().sumOf { (index, height) ->
            val minAdjacent = adjacentHeights(index).minOf { it }
            if (height < minAdjacent) height + 1 else 0
        }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun adjacentHeights(index: Int) = buildList {
        if (index % width != 0) {
            // Left
            add(heights[index - 1])
        }
        if ((index + 1) % width != 0) {
            // Right
            add(heights[index + 1])
        }
        if (index >= width) {
            // Above
            add(heights[index - width])
        }
        if (index < heights.size - width) {
            // Below
            add(heights[index + width])
        }
    }
}