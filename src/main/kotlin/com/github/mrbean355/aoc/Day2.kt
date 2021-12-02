package com.github.mrbean355.aoc

fun day2() {
    val exampleInput = listOf("forward 5", "down 5", "forward 8", "up 3", "down 8", "forward 2")
    println(calculatePart1(exampleInput))
    println(calculatePart2(exampleInput))

    val input = readInput("day2.txt")
    println(calculatePart1(input))
    println(calculatePart2(input))
}

private fun calculatePart1(input: List<String>): Int {
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

private fun calculatePart2(input: List<String>): Int {
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