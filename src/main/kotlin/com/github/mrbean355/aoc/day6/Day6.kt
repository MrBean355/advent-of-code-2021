package com.github.mrbean355.aoc.day6

import com.github.mrbean355.aoc.base.Puzzle

class Day6(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        var currentGeneration = input.first().split(',').map(String::toInt)
        var population = currentGeneration.size.toLong()

        repeat(80) {
            val nextGeneration = mutableListOf<Int>()
            currentGeneration.forEach { daysLeft ->
                if (daysLeft == 0) {
                    nextGeneration += 6
                    nextGeneration += 8
                    ++population
                } else {
                    nextGeneration += daysLeft - 1
                }
            }
            currentGeneration = nextGeneration
        }

        return population
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }
}