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

    override fun part1() = countPathsToEnd(START_CAVE, canRevisit = false)

    override fun part2() = countPathsToEnd(START_CAVE, canRevisit = true)

    private fun countPathsToEnd(
        cave: String,
        canRevisit: Boolean,
        visited: Set<String> = emptySet(),
        hasRevisited: Boolean = false
    ): Int {
        if (cave == END_CAVE) {
            return 1
        }
        val allowedNext = caveMap.getValue(cave).filter { next ->
            next != START_CAVE && (next.isBigCave || next !in visited || (canRevisit && !hasRevisited))
        }
        return if (allowedNext.isNotEmpty()) {
            val nowVisited = visited + cave
            allowedNext.sumOf {
                val nextRevisited = hasRevisited || (!it.isBigCave && it in visited)
                countPathsToEnd(it, canRevisit, nowVisited, nextRevisited)
            }
        } else {
            0
        }
    }

    private val String.isBigCave: Boolean
        get() = all { it.isUpperCase() }
}