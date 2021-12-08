package com.github.mrbean355.aoc.day7

import com.github.mrbean355.aoc.base.Puzzle
import kotlin.math.abs

class Day7(input: List<String>) : Puzzle {

    private val positions = input.first().split(',').map(String::toInt)

    override fun part1(): Number {
        var bestFuelCost = Int.MAX_VALUE

        positions.forEach { position ->
            val cost = positions.sumOf { abs(it - position) }
            if (cost < bestFuelCost) {
                bestFuelCost = cost
            }
        }

        return bestFuelCost
    }

    override fun part2(): Number {
        return 0
    }
}