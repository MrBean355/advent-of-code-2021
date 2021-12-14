package com.github.mrbean355.aoc.day14

import com.github.mrbean355.aoc.base.Puzzle

private typealias CharPair = Pair<Char, Char>

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
            .flatMap { (pair, insertion) -> listOf(pair.first, pair.second, insertion) }
            .distinct()

        val pairCounts = uniqueLetters
            .flatMap { letter -> uniqueLetters.map { other -> letter to other } }
            .associateWith { pair -> template.count(pair).toLong() }
            .toMutableMap()

        repeat(iterations) {
            pairCounts.filterValues { it > 0 }.forEach { (pair, count) ->
                val insertion = rules.getValue(pair)
                pairCounts.add(pair, -count)
                pairCounts.add(pair.first to insertion, count)
                pairCounts.add(insertion to pair.second, count)
            }
        }

        val letterCounts = uniqueLetters.associateWith { letter ->
            pairCounts.mapValues { (pair, count) ->
                (pair.count(letter) * count)
            }.values.sum().inc() / 2
        }.values

        return letterCounts.maxOf { it } - letterCounts.minOf { it }
    }

    /** Add the [amount] to the value of the [key]. */
    private fun MutableMap<CharPair, Long>.add(key: CharPair, amount: Long) {
        put(key, getValue(key) + amount)
    }

    /** @return how many times the [letter] appears in this pair. */
    private fun CharPair.count(letter: Char): Int {
        return (if (first == letter) 1 else 0) + (if (second == letter) 1 else 0)
    }

    /** @return how many times the [pair] appears in this string. */
    private fun String.count(pair: CharPair): Int {
        val str = "${pair.first}${pair.second}"
        return windowed(2).count { it == str }
    }

    private fun loadPolymerTemplate(): String {
        return input.first()
    }

    private fun loadRules(): Map<CharPair, Char> {
        return input.drop(2).associate { line ->
            val (pair, insert) = line.split(" -> ")
            (pair[0] to pair[1]) to insert.single()
        }
    }
}