package com.github.mrbean355.aoc.day13

import com.github.mrbean355.aoc.base.Puzzle

class Day13(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        val points = input.takeWhile { ',' in it }.map {
            val (x, y) = it.split(',')
            Point(x.toInt(), y.toInt())
        }.toSet()

        val (axis, position) = input.first { it.startsWith("fold") }
            .substringAfter("along ")
            .split('=')

        return when (axis) {
            "x" -> points.foldX(position.toInt()).size
            "y" -> points.foldY(position.toInt()).size
            else -> error("Unexpected axis: $axis")
        }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun Set<Point>.foldX(position: Int): Set<Point> {
        val maxX = maxOf(Point::x)
        val left = filter { it.x < position }.toSet()
        val right = this - left
        val mapped = right.map {
            Point(maxX - it.x, it.y)
        }
        return (left + mapped).toSet()
    }

    private fun Set<Point>.foldY(position: Int): Set<Point> {
        val maxY = maxOf(Point::y)
        val above = filter { it.y < position }.toSet()
        val below = this - above
        val mapped = below.map {
            Point(it.x, maxY - it.y)
        }
        return (above + mapped).toSet()
    }

    private data class Point(
        val x: Int,
        val y: Int
    )
}