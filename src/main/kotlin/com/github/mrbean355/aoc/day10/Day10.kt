package com.github.mrbean355.aoc.day10

import com.github.mrbean355.aoc.base.Puzzle
import java.util.Stack

class Day10(private val input: List<String>) : Puzzle {

    override fun part1(): Number {
        return input.sumOf { line ->
            when (val ch = line.findMismatchedCharacter()) {
                ')' -> 3
                ']' -> 57
                '}' -> 1197
                '>' -> 25137
                null -> 0.toInt()
                else -> error("Invalid character: $ch")
            }
        }
    }

    override fun part2(): Number {
        val stack = Stack<Char>()
        return input.filter { it.isIncomplete() }.map { line ->
            line.forEach { ch ->
                when (ch) {
                    '(', '[', '{', '<' -> stack.push(ch)
                    ')' -> stack.popExpected('(')
                    ']' -> stack.popExpected('[')
                    '}' -> stack.popExpected('{')
                    '>' -> stack.popExpected('<')
                    else -> error("Invalid character: $ch")
                }
            }
            var score = 0L
            while (stack.isNotEmpty()) {
                val ch = stack.pop()
                score = score * 5 + when (ch) {
                    '(' -> 1
                    '[' -> 2
                    '{' -> 3
                    '<' -> 4
                    else -> error("Invalid character: $ch")
                }
            }
            score
        }.sorted().let { scores ->
            scores[scores.size / 2]
        }
    }

    private fun String.findMismatchedCharacter(): Char? {
        val stack = Stack<Char>()
        forEach { ch ->
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

    private fun String.isIncomplete(): Boolean {
        return this.findMismatchedCharacter() == null
    }

    private fun Stack<Char>.popExpected(char: Char) {
        pop().also {
            check(it == char) { "Expected to pop $char but got $it" }
        }
    }
}