package com.github.mrbean355.aoc.day9

import com.github.mrbean355.aoc.base.Puzzle

class Day9(input: List<String>) : Puzzle {

    private val width = input.first().length
    private val heights = input.joinToString("").map(Char::digitToInt)

    override fun part1(): Any {
        return heights.withIndex().sumOf { (index, height) ->
            val minAdjacent = findAdjacentHeights(index).minOf { it }
            if (height < minAdjacent) height + 1 else 0
        }
    }

    override fun part2(): Any {
        val basins = findBasinIndices()
        val results = basins.associateWith { mutableSetOf<Int>() }

        for (index in heights.indices) {
            if (heights[index] == 9) {
                continue
            }
            val basin = findBasinIndex(index, basins)
            results.getValue(basin) += index
        }

        return results.values.map { it.size }
            .sorted()
            .takeLast(3)
            .reduce { a, b -> a * b }
    }

    private fun findBasinIndices(): List<Int> {
        return heights.withIndex().filter { (index, height) ->
            height < findAdjacentHeights(index).minOf { it }
        }.map { it.index }
    }

    private fun findBasinIndex(index: Int, basins: List<Int>): Int {
        if (index in basins) {
            return index
        }
        val currentHeight = heights[index]
        val downhillIndex = findAdjacentIndices(index)
            .first { heights[it] < currentHeight }
        return findBasinIndex(downhillIndex, basins)
    }

    private fun findAdjacentIndices(index: Int) = buildList {
        if (index % width != 0) {
            // Left
            add(index - 1)
        }
        if ((index + 1) % width != 0) {
            // Right
            add(index + 1)
        }
        if (index >= width) {
            // Above
            add(index - width)
        }
        if (index < heights.size - width) {
            // Below
            add(index + width)
        }
    }

    private fun findAdjacentHeights(index: Int): List<Int> {
        return findAdjacentIndices(index).map { heights[it] }
    }
}