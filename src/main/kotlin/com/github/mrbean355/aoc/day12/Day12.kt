package com.github.mrbean355.aoc.day12

import com.github.mrbean355.aoc.base.Puzzle

private const val START_CAVE = "start"
private const val END_CAVE = "end"

class Day12(input: List<String>) : Puzzle {

    private val caveMap: Map<String, Set<String>>

    init {
        val caveMap = mutableMapOf<String, MutableSet<String>>()
        input.forEach { line ->
            val (source, target) = line.split('-')
            caveMap.getOrPut(source) { mutableSetOf() }.add(target)
            caveMap.getOrPut(target) { mutableSetOf() }.add(source)
        }
        this.caveMap = caveMap
    }

    override fun part1() = countPathsToEnd(START_CAVE)

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun countPathsToEnd(cave: String, visited: Set<String> = emptySet()): Int {
        if (cave == END_CAVE) {
            return 1
        }
        val allowedNext = caveMap.getValue(cave).filter { next ->
            next.isBigCave || next !in visited
        }
        return if (allowedNext.isNotEmpty()) {
            val nowVisited = visited + cave
            allowedNext.sumOf {
                countPathsToEnd(it, nowVisited)
            }
        } else {
            0
        }
    }

    private val String.isBigCave: Boolean
        get() = all { it.isUpperCase() }
}