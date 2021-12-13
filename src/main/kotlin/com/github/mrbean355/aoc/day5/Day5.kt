package com.github.mrbean355.aoc.day5

import com.github.mrbean355.aoc.base.Puzzle

class Day5(input: List<String>) : Puzzle {

    private val lines = input.map { line ->
        val (head, tail) = line.split(" -> ")
        Line(Point(head), Point(tail))
    }

    override fun part1(): Any {
        return lines.filter { (a, b) -> a.x == b.x || a.y == b.y }
            .countOverlappingLines()
    }

    override fun part2(): Any {
        return lines.countOverlappingLines()
    }

    private fun List<Line>.countOverlappingLines(): Int {
        val visited = mutableMapOf<Point, Int>()

        forEach { line ->
            line.allPoints().forEach {
                val count = visited.getOrPut(it) { 0 }
                visited[it] = count + 1
            }
        }

        return visited.count { it.value >= 2 }
    }

    private fun Line.allPoints(): List<Point> {
        val higherX = if (head.x > tail.x) head else tail
        val other = if (higherX === head) tail else head

        return if (higherX.x == other.x) {
            val (minY, maxY) = listOf(higherX.y, other.y).sorted()
            (minY..maxY).map { Point(higherX.x, it) }
        } else {
            val gradient = when {
                higherX.y > other.y -> 1
                higherX.y < other.y -> -1
                else -> 0
            }
            val intercept = head.y - gradient * head.x

            (other.x..higherX.x).map { x ->
                Point(x, gradient * x + intercept)
            }
        }
    }

    private data class Line(
        val head: Point,
        val tail: Point
    )

    private data class Point(
        val x: Int,
        val y: Int
    ) {
        companion object {

            /** Construct an instance from a string like "1,2". */
            operator fun invoke(s: String): Point {
                val (x, y) = s.split(',')
                return Point(x.toInt(), y.toInt())
            }
        }
    }
}