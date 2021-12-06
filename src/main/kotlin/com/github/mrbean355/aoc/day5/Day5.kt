package com.github.mrbean355.aoc.day5

import com.github.mrbean355.aoc.base.Puzzle

class Day5(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        val lines = parse(input).filter { (a, b) ->
            a.x == b.x || a.y == b.y
        }

        val points = mutableSetOf<Point>()
        val visited = mutableSetOf<Point>()

        lines.forEach { (head, tail) ->
            if (head.x == tail.x) {
                // Vertical line
                val y0 = if (head.y <= tail.y) head.y else tail.y
                val y1 = if (y0 == head.y) tail.y else head.y

                for (y in y0..y1) {
                    val pt = Point(head.x, y)
                    if (!points.add(pt)) {
                        visited += pt
                    }
                }
            } else {
                // Horizontal line
                val x0 = if (head.x <= tail.x) head.x else tail.x
                val x1 = if (x0 == head.x) tail.x else head.x

                for (x in x0..x1) {
                    val pt = Point(x, head.y)
                    if (!points.add(pt)) {
                        visited += pt
                    }
                }
            }
        }

        return visited.size
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun parse(input: List<String>): List<Line> {
        return input.map { line ->
            val (head, tail) = line.split(" -> ")
            Line(Point(head), Point(tail))
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