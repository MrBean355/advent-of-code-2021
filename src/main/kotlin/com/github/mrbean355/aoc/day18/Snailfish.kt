package com.github.mrbean355.aoc.day18

sealed class Snailfish(
    var parent: Pair?
) {

    abstract val magnitude: Int

    class Literal(
        var value: Int,
        parent: Pair?
    ) : Snailfish(parent) {

        override val magnitude get() = value

        override fun toString() = value.toString()

        companion object {
            val empty = Literal(0, null)
        }
    }

    class Pair constructor(
        parent: Pair?,
        var lhs: Snailfish = Literal.empty,
        var rhs: Snailfish = Literal.empty
    ) : Snailfish(parent) {

        override val magnitude get() = 3 * lhs.magnitude + 2 * rhs.magnitude

        override fun toString() = "[$lhs,$rhs]"

    }
}

operator fun Snailfish.plus(other: Snailfish): Snailfish.Pair {
    return Snailfish.Pair(null, this, other).also {
        this.parent = it
        other.parent = it
        it.reduce()
    }
}

private fun Snailfish.reduce() {
    if (explode() || split()) {
        reduce()
    }
}

private fun Snailfish.explode(): Boolean {
    findNestedPair()?.let { nested ->
        nested.findLeftLiteral()?.let { left ->
            left.value += nested.lhs.magnitude
        }
        nested.findRightLiteral()?.let { right ->
            right.value += nested.rhs.magnitude
        }
        val parent = nested.parent!!
        val replacement = Snailfish.Literal(0, parent)
        when {
            parent.lhs === nested -> parent.lhs = replacement
            parent.rhs === nested -> parent.rhs = replacement
        }
        return true
    }
    return false
}

private fun Snailfish.split(): Boolean {
    return when (this) {
        is Snailfish.Literal -> {
            if (value >= 10) {
                val leftValue = value / 2
                val rightValue = value - leftValue
                val replacement = Snailfish.Pair(parent).also {
                    it.lhs = Snailfish.Literal(leftValue, it)
                    it.rhs = Snailfish.Literal(rightValue, it)
                }
                val parent = parent!!
                when {
                    parent.lhs === this -> parent.lhs = replacement
                    parent.rhs === this -> parent.rhs = replacement
                }
                true
            } else {
                false
            }
        }
        is Snailfish.Pair -> lhs.split() || rhs.split()
    }
}

/** @return a pair that is nested 4 pairs deep. */
private fun Snailfish.findNestedPair(
    level: Int = 1,
): Snailfish.Pair? {

    if (this !is Snailfish.Pair) {
        return null
    }

    if (level == 4) {
        if (lhs is Snailfish.Pair) {
            return lhs as Snailfish.Pair
        }
        if (rhs is Snailfish.Pair) {
            return rhs as Snailfish.Pair
        }
        return null
    }

    return lhs.findNestedPair(level + 1)
        ?: rhs.findNestedPair(level + 1)
}

/** @return the closest left-most literal. */
private fun Snailfish.Pair.findLeftLiteral(): Snailfish.Literal? {
    var pair = parent
    var prev = this

    while (pair != null) {
        if (pair.lhs !== prev) {
            var lhs = pair.lhs
            while (lhs !is Snailfish.Literal) {
                lhs = (lhs as Snailfish.Pair).rhs
            }
            return lhs
        }
        prev = pair
        pair = pair.parent
    }

    return null
}

/** @return the closest right-most literal. */
private fun Snailfish.Pair.findRightLiteral(): Snailfish.Literal? {
    var pair = parent
    var prev = this

    while (pair != null) {
        if (pair.rhs !== prev) {
            var rhs = pair.rhs
            while (rhs !is Snailfish.Literal) {
                rhs = (rhs as Snailfish.Pair).lhs
            }
            return rhs
        }
        prev = pair
        pair = pair.parent
    }

    return null
}
