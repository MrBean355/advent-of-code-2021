package com.github.mrbean355.aoc

import java.io.File

fun main(args: Array<String>) {
    day1()
}

fun readInput(file: String): List<String> {
    return File(Thread.currentThread().contextClassLoader.getResource(file).file)
        .readLines()
}