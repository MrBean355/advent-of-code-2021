package com.github.mrbean355.aoc.day4

import com.github.mrbean355.aoc.base.Puzzle

class Day4(private val input: List<String>) : Puzzle {

    private val moves: List<Int>
    private val boards: List<BingoBoard>

    init {
        moves = input.first()
            .split(',')
            .map(String::toInt)

        boards = parse()
    }

    override fun part1(): Number {
        moves.forEach { move ->
            boards.forEach { board ->
                board.mark(move)
                if (board.hasWon()) {
                    return board.sumOfUnmarked() * move
                }
            }
        }
        error("No winner found")
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun parse(): List<BingoBoard> {
        val boards = mutableListOf<BingoBoard>()
        var currentBoard = BingoBoard()

        input.drop(2).forEach { line ->
            if (line.isBlank()) {
                boards += currentBoard
                currentBoard = BingoBoard()
            } else {
                val numbers = line.split(' ')
                    .filter { it.isNotBlank() }
                    .map(String::toInt)

                currentBoard.addRow(numbers)
            }
        }

        boards += currentBoard

        return boards
    }
}

class BingoBoard {
    private val numbers: MutableList<MutableList<Int>> = mutableListOf()

    fun addRow(row: List<Int>) {
        numbers += row.toMutableList()
    }

    fun mark(number: Int) {
        numbers.forEach { row ->
            row.forEachIndexed { index, i ->
                if (i == number) {
                    row[index] = -1
                }
            }
        }
    }

    fun hasWon(): Boolean {
        val hasCompletedRow = numbers.any { row ->
            row.all { it == -1 }
        }
        if (hasCompletedRow) {
            return true
        }

        for (x in 0 until 5) {
            val hasCompletedColumn = numbers.all { row ->
                row[x] == -1
            }
            if (hasCompletedColumn) {
                return true
            }
        }

        return false
    }

    fun sumOfUnmarked(): Int {
        return numbers.sumOf { row ->
            row.sumOf {
                if (it != -1) it else 0
            }
        }
    }

    override fun toString(): String {
        return numbers.joinToString("\n") {
            it.joinToString(" ")
        }
    }
}