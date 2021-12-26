package com.github.mrbean355.aoc.day22

import com.github.mrbean355.aoc.base.Puzzle

class Day22(private val input: List<String>) : Puzzle {

    override fun part1(): Any {
        val enabled = mutableSetOf<Cube>()
        input.forEach { line ->
            val space = line.indexOf(' ')
            val turnOn = line.substring(0, space) == "on"
            val (x, y, z) = line.substring(space + 1)
                .split(',')
                .map { it.substringAfter('=') }
            loadCubes(
                x.deserialiseRange(),
                y.deserialiseRange(),
                z.deserialiseRange(),
            ).forEach { cube ->
                if (turnOn) {
                    enabled += cube
                } else {
                    enabled -= cube
                }
            }
        }
        return enabled.size
    }

    override fun part2(): Any {
        TODO("No yet implemented")
    }

    private fun String.deserialiseRange(): IntRange {
        val separator = indexOf("..")
        return substring(0, separator).toInt().coerceAtLeast(-50)
            .rangeTo(substring(separator + 2).toInt().coerceAtMost(50))
    }

    private fun loadCubes(xRange: IntRange, yRange: IntRange, zRange: IntRange): List<Cube> {
        return xRange.flatMap { x ->
            yRange.flatMap { y ->
                zRange.map { z ->
                    Cube(x, y, z)
                }
            }
        }
    }

    private data class Cube(
        val x: Int,
        val y: Int,
        val z: Int,
    )
}