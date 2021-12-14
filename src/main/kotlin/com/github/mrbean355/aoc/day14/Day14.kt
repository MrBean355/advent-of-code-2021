package com.github.mrbean355.aoc.day14

import com.github.mrbean355.aoc.base.Puzzle

class Day14(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        val polymer = loadPolymer().toMutableList()
        val rules = loadRules()

        repeat(10) {
            var i = 0
            while (i < polymer.size - 1) {
                val pair = polymer[i] + polymer[i + 1].toString()
                polymer.add(i + 1, rules.getValue(pair))
                i += 2
            }
        }

        val counts = polymer.distinct().map { ch ->
            polymer.count { it == ch }
        }

        return counts.maxOf { it } - counts.minOf { it }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun loadPolymer(): String {
        return input.first()
    }

    private fun loadRules(): Map<String, Char> {
        return input.drop(2).associate { line ->
            val (pair, insert) = line.split(" -> ")
            pair to insert.single()
        }
    }
}