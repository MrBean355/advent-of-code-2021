package com.github.mrbean355.aoc.day15

import com.github.mrbean355.aoc.base.Puzzle

@JvmInline
value class Vertex(val index: Int)

class Day15(input: List<String>) : Puzzle {
    private val width = input.first().length
    private val riskLevels = input.flatMap { line ->
        line.toList().map { it.digitToInt() }
    }

    override fun part1(): Any {
        val vertices = riskLevels.indices.map { Vertex(it) }
        val source = vertices.first()
        val target = vertices.last()

        val dist = vertices.associateWith { Int.MAX_VALUE }.toMutableMap()
        val prev: MutableMap<Vertex, Vertex?> = vertices.associateWith { null }.toMutableMap()
        val toProcess = vertices.toMutableSet()

        dist[source] = 0

        while (toProcess.isNotEmpty()) {
            val current = dist.filterKeys { it in toProcess }
                .minByOrNull { it.value }!!
                .key

            toProcess.remove(current)

            if (current == target) {
                return calculateRiskLevel(prev, source, target)
            }

            findNeighbours(current).filter { it in toProcess }.forEach { neighbour ->
                val neighbourDist = dist.getValue(current) + riskLevels[neighbour.index]
                if (neighbourDist < dist.getValue(neighbour)) {
                    dist[neighbour] = neighbourDist
                    prev[neighbour] = current
                }
            }
        }

        error("Couldn't find path to target")
    }

    override fun part2(): Any {
        TODO("Not yet implemented")
    }

    /** @return valid vertices that are vertical and horizontal neighbours of [who]. */
    private fun findNeighbours(who: Vertex): List<Vertex> {
        return buildList {
            // above
            if (who.index - width >= 0) {
                add(Vertex(who.index - width))
            }
            // left
            if (who.index % width != 0) {
                add(Vertex(who.index - 1))
            }
            // right
            if ((who.index + 1) % width != 0) {
                add(Vertex(who.index + 1))
            }
            // below
            if (who.index + width < riskLevels.size) {
                add(Vertex(who.index + width))
            }
        }
    }

    private fun calculateRiskLevel(prev: Map<Vertex, Vertex?>, source: Vertex, target: Vertex): Int {
        val path = mutableListOf<Vertex>()
        var current: Vertex? = target

        while (current != null) {
            path.add(0, current)
            current = prev[current]
        }

        return path.sumOf {
            riskLevels[it.index]
        } - riskLevels[source.index]
    }
}