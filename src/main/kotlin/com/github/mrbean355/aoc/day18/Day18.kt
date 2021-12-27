package com.github.mrbean355.aoc.day18

import com.github.mrbean355.aoc.base.Puzzle

class Day18(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return input.map(::parse).reduce { acc, snailFish ->
            acc + snailFish
        }.magnitude
    }

    override fun part2(): Any {
        var max = Int.MIN_VALUE

        for (i in input.indices) {
            for (j in input.indices) {
                if (j != i) {
                    val sum = parse(input[j]) + parse(input[i])
                    max = maxOf(max, sum.magnitude)
                }
            }
        }

        return max
    }

    private fun parse(
        input: String,
        parent: Snailfish.Pair? = null
    ): Snailfish {
        return if (input.startsWith('[')) {
            var level = 1
            var i = 1
            while (true) {
                when (input[i]) {
                    '[' -> ++level
                    ']' -> --level
                    ',' -> {
                        if (level == 1) {
                            break
                        }
                    }
                }
                ++i
            }

            Snailfish.Pair(parent).also {
                it.lhs = parse(input.substring(1, i), it)
                it.rhs = parse(input.substring(i + 1, input.length - 1), it)
            }
        } else {
            Snailfish.Literal(input.toInt(), parent)
        }
    }
}