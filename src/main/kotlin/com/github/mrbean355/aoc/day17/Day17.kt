package com.github.mrbean355.aoc.day17

import com.github.mrbean355.aoc.base.Puzzle

// TODO: how can we determine this threshold?
private const val MAGIC = 200

class Day17(input: List<String>) : Puzzle {

    private val targetX: IntRange
    private val targetY: IntRange

    init {
        val line = input.first().substringAfter("target area: ")
        val (minX, maxX) = line.substringAfter("x=").substringBefore(',').split("..")
        val (minY, maxY) = line.substringAfter("y=").split("..")

        targetX = minX.toInt()..maxX.toInt()
        targetY = minY.toInt()..maxY.toInt()
    }

    override fun part1(): Any {
        var maxY = Int.MIN_VALUE

        for (xVelocity in 1..MAGIC) {
            for (yVelocity in 1..MAGIC) {
                findMaxY(xVelocity, yVelocity)?.let {
                    if (it > maxY) {
                        maxY = it
                    } else {
                        return maxY
                    }
                }
            }
        }

        error("Couldn't find max Y")
    }

    override fun part2(): Any {
        val velocities = mutableSetOf<Pair<Int, Int>>()

        for (xVelocity in -MAGIC..MAGIC) {
            for (yVelocity in -MAGIC..MAGIC) {
                findMaxY(xVelocity, yVelocity)?.let {
                    velocities += xVelocity to yVelocity
                }
            }
        }

        return velocities.size
    }

    private fun findMaxY(xVel: Int, yVel: Int): Int? {
        var x = 0
        var y = 0
        var maxY = 0
        var xVelocity = xVel
        var yVelocity = yVel

        while (x !in targetX || y !in targetY) {
            x += xVelocity
            y += yVelocity

            // Give up if we've missed the target.
            if (x > targetX.last) {
                break
            }
            if (y < targetY.first) {
                break
            }

            maxY = maxOf(maxY, y)
            xVelocity = (xVelocity - 1).coerceAtLeast(0)
            yVelocity -= 1
        }

        return if (x in targetX && y in targetY) {
            maxY
        } else {
            null
        }
    }
}