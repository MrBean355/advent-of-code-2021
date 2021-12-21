package com.github.mrbean355.aoc.day21

import com.github.mrbean355.aoc.base.Puzzle

class Day21(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val dice = Dice()
        val p1 = Player(input[0].substringAfter(": ").toInt())
        val p2 = Player(input[1].substringAfter(": ").toInt())

        while (true) {
            p1.move(dice)
            if (p1.score >= 1000) {
                return p2.score * dice.totalRolls
            }

            p2.move(dice)
            if (p2.score >= 1000) {
                return p1.score * dice.totalRolls
            }
        }
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }

    private class Player(
        private var position: Int
    ) {
        var score = 0
            private set

        fun move(dice: Dice) {
            val forward = dice.roll() + dice.roll() + dice.roll()
            position += forward
            while (position > 10) {
                position -= 10
            }
            score += position
        }
    }

    private class Dice {
        private var next = 1
        private var rolls = 0

        val totalRolls: Int get() = rolls

        fun roll(): Int {
            val result = next++
            if (next > 100) {
                next = 1
            }
            ++rolls
            return result
        }
    }
}