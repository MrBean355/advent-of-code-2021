package com.github.mrbean355.aoc.day25

import com.github.mrbean355.aoc.base.Grid
import com.github.mrbean355.aoc.base.Puzzle

private const val EMPTY = '.'
private const val EAST = '>'
private const val SOUTH = 'v'

class Day25(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        var grid = parse()
        var iterations = 0

        while (true) {
            var moves = 0
            var temp = grid.copy()

            for (y in 0 until grid.height) {
                for (x in 0 until grid.width) {
                    val ch = grid.get(x, y)
                    if (ch == EAST) {
                        val next = if (x < grid.width - 1) x + 1 else 0
                        val adjacent = grid.get(next, y)
                        if (adjacent == EMPTY) {
                            temp.set(x, y, EMPTY)
                            temp.set(next, y, EAST)
                            ++moves
                        }
                    }
                }
            }

            grid = temp
            temp = grid.copy()

            for (y in 0 until grid.height) {
                for (x in 0 until grid.width) {
                    val ch = grid.get(x, y)
                    if (ch == SOUTH) {
                        val next = if (y < grid.height - 1) y + 1 else 0
                        val adjacent = grid.get(x, next)
                        if (adjacent == EMPTY) {
                            temp.set(x, y, EMPTY)
                            temp.set(x, next, SOUTH)
                            ++moves
                        }
                    }
                }
            }

            grid = temp
            ++iterations

            if (moves == 0) {
                break
            }
        }

        return iterations
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }

    private fun parse(): Grid<Char> {
        return Grid(
            rows = input.size,
            columns = input.first().length, initialiser = { x, y -> input[y][x] }
        )
    }
}