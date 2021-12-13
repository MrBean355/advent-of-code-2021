package com.github.mrbean355.aoc.day13

import com.github.mrbean355.aoc.base.Puzzle

class Day13(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        return loadFoldInstructions()
            .first()
            .foldPaper(loadPoints())
            .size
    }

    override fun part2(): Any {
        return loadFoldInstructions()
            .fold(loadPoints()) { points, instruction -> instruction.foldPaper(points) }
            .toPrettyString()
    }

    private fun Set<Point>.toPrettyString(): String {
        val maxX = maxOf(Point::x)
        val maxY = maxOf(Point::y)

        return buildString {
            for (y in 0..maxY) {
                for (x in 0..maxX) {
                    append(if (contains(Point(x, y))) '#' else ' ')
                }
                appendLine()
            }
        }.dropLast(1)
    }

    private fun loadPoints(): Set<Point> {
        return input.takeWhile { ',' in it }.map {
            val (x, y) = it.split(',')
            Point(x.toInt(), y.toInt())
        }.toSet()
    }

    private fun loadFoldInstructions(): List<FoldInstruction> {
        return input.takeLastWhile { it.startsWith("fold") }.map { line ->
            val (axis, position) = line.substringAfter("along ").split('=')
            FoldInstruction(axis.single().lowercaseChar(), position.toInt())
        }
    }

    private data class Point(
        val x: Int,
        val y: Int
    )

    private class FoldInstruction(
        private val direction: Char,
        private val position: Int,
    ) {

        fun foldPaper(points: Set<Point>): Set<Point> {
            return when (direction) {
                'x' -> foldVertical(points)
                'y' -> foldHorizontal(points)
                else -> error("Invalid fold: $direction = $position")
            }
        }

        private fun foldHorizontal(points: Set<Point>): Set<Point> {
            return points.map { pt ->
                if (pt.y > position) {
                    Point(pt.x, 2 * position - pt.y)
                } else {
                    pt
                }
            }.toSet()
        }

        private fun foldVertical(points: Set<Point>): Set<Point> {
            return points.map { pt ->
                if (pt.x > position) {
                    Point(2 * position - pt.x, pt.y)
                } else {
                    pt
                }
            }.toSet()
        }
    }
}