package com.github.mrbean355.aoc.day2

import com.github.mrbean355.aoc.base.Puzzle

class Day2(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        var x = 0
        var y = 0

        input.forEach { line ->
            val (instruction, argument) = line.split(' ')
            val magnitude = argument.toInt()
            when (instruction) {
                "forward" -> x += magnitude
                "up" -> y -= magnitude
                "down" -> y += magnitude
            }
        }

        return x * y
    }

    override fun part2(): Number {
        var x = 0
        var y = 0
        var aim = 0

        input.forEach { line ->
            val (instruction, magnitude) = line.split(' ')
            val amount = magnitude.toInt()
            when (instruction) {
                "forward" -> {
                    x += amount
                    y += amount * aim
                }
                "up" -> aim -= amount
                "down" -> aim += amount
            }
        }

        return x * y
    }
}