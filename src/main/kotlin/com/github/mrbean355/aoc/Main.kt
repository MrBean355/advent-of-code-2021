package com.github.mrbean355.aoc

import java.io.File

fun main() {
    println("Day 1")
    day1()

    println("Day 2")
    day2()
}

fun readInput(file: String): List<String> {
    return File(Thread.currentThread().contextClassLoader.getResource(file).file)
        .readLines()
}