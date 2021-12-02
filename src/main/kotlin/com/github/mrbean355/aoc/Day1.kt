package com.github.mrbean355.aoc

fun day1() {
    val exampleInput = listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
    println(calculatePart1(exampleInput))
    println(calculatePart2(exampleInput))

    val input = readInput("day1.txt").map(String::toInt)
    println(calculatePart1(input))
    println(calculatePart2(input))
}

private fun calculatePart1(input: List<Int>): Int {
    var count = 0
    var prev = input.first()
    input.drop(1).forEach { current ->
        if (current > prev) {
            ++count
        }
        prev = current
    }
    return count
}

private fun calculatePart2(input: List<Int>): Int {
    val sums = input.windowed(3).map { it.sum() }
    return calculatePart1(sums)
}