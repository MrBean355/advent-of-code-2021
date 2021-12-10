package com.github.mrbean355.aoc.day6

import com.github.mrbean355.aoc.base.Puzzle

class Day6(input: List<String>) : Puzzle {

    private val initialFish = input.first().split(',').map(String::toInt)

    override fun part1(): Number {
        return simulateFishLife(generations = 80)
    }

    override fun part2(): Number {
        return simulateFishLife(generations = 256)
    }

    private fun simulateFishLife(generations: Int): Long {
        val timers = (0..8)
        val population = timers.associateWith { 0L }.toMutableMap()

        initialFish.forEach { population.increment(it) }

        repeat(generations) {
            timers.forEach { timer ->
                population.shiftLeft(timer)
            }
            population.increment(6, population.getValue(-1))
            population.increment(8, population.getValue(-1))
        }

        return timers.sumOf(population::getValue)
    }

    /** Increment the value of the [key] by [amount]. */
    private fun MutableMap<Int, Long>.increment(key: Int, amount: Long = 1) {
        put(key, getValue(key) + amount)
    }

    /** Move the value of the [key] to the value of `key - 1`. */
    private fun MutableMap<Int, Long>.shiftLeft(key: Int) {
        put(key - 1, getValue(key))
        put(key, 0)
    }
}