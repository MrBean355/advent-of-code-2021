package com.github.mrbean355.aoc.day14

import com.github.mrbean355.aoc.base.Puzzle

class Day14(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return polymerize(iterations = 10)
    }

    override fun part2(): Number {
        return polymerize(iterations = 40)
    }

    private fun polymerize(iterations: Int): Long {
        val template = loadPolymerTemplate()
        val rules = loadRules()

        val uniqueLetters = rules
            .flatMap { (pair, insertion) -> listOf(pair[0], pair[1], insertion) }
            .distinct()

        val pairCounts = uniqueLetters
            .flatMap { letter -> uniqueLetters.map { other -> "$letter$other" } }
            .associateWith { pair -> template.split(pair).size.toLong() - 1 }
            .toMutableMap()

        repeat(iterations) {
            pairCounts.filterValues { it > 0 }.forEach { (pair, count) ->
                val insertion = rules.getValue(pair)
                val firstNewPair = pair[0].toString() + insertion
                val secondNewPair = insertion.toString() + pair[1]

                pairCounts.add(pair, -count)
                pairCounts.add(firstNewPair, count)
                pairCounts.add(secondNewPair, count)
            }
        }

        val letterCounts = uniqueLetters.associateWith { letter ->
            pairCounts.mapValues { (pair, count) ->
                (pair.count { it == letter } * count)
            }.values.sum().inc() / 2
        }.values

        return letterCounts.maxOf { it } - letterCounts.minOf { it }
    }

    private fun MutableMap<String, Long>.add(key: String, amount: Long) {
        put(key, getValue(key) + amount)
    }

    private fun loadPolymerTemplate(): String {
        return input.first()
    }

    private fun loadRules(): Map<String, Char> {
        return input.drop(2).associate { line ->
            val (pair, insert) = line.split(" -> ")
            pair to insert.single()
        }
    }
}