package com.github.mrbean355.aoc.day20

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.Puzzle

class Day20(input: List<String>) : Puzzle {

    private val enhancement = input.first()
    private val inputImage = input.drop(2).map(String::toList)

    override fun part1(): Any {
        val iterations = 2
        var grid = Grid(
            rows = inputImage.size,
            columns = inputImage.first().size,
            initialiser = { x, y -> inputImage[y][x] }
        )

        repeat(iterations + 1) {
            grid.addRow(0) { '.' }
            grid.addRow(grid.height) { '.' }
            grid.addColumn(0) { '.' }
            grid.addColumn(grid.width) { '.' }
        }

        repeat(iterations) {
            val mutated = grid.copy()

            for (y in 0 until grid.height) {
                for (x in 0 until grid.width) {
                    val neighbours = grid.getNeighbours(x, y).toDecimal()
                    val pixel = enhancement[neighbours]
                    mutated.set(x, y, pixel)
                }
            }

            grid = mutated
        }

        return grid.count { it == '#' }
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }

    private fun List<Char>.toDecimal(): Int {
        val binary = joinToString(separator = "") {
            if (it == '.') "0" else "1"
        }
        return binary.toInt(radix = 2)
    }
}