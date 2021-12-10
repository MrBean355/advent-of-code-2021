package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.Puzzle
import java.util.Stack

class Day10(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return input.sumOf { line ->
            when (findMismatchedCharacter(line)) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                else -> 0.toInt()
            }
        }
    }

    override fun part2(): Number {
        TODO("Not yet implemented")
    }

    private fun findMismatchedCharacter(line: String): Char? {
        val stack = Stack<Char>()
        line.forEach { ch ->
            when (ch) {
                '(', '[', '{', '<' -> stack.push(ch)
                ')' -> stack.pop().also {
                    if (it != '(') return ch
                }
                ']' -> stack.pop().also {
                    if (it != '[') return ch
                }
                '}' -> stack.pop().also {
                    if (it != '{') return ch
                }
                '>' -> stack.pop().also {
                    if (it != '<') return ch
                }
                else -> error("Invalid character: $ch")
            }
        }
        return null
    }
}