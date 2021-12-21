package com.github.mrbean355.aoc.base

class Grid<T>(
    rows: Int,
    columns: Int,
    initialiser: (Int, Int) -> T
) {
    private val table: MutableList<MutableList<T>> = MutableList(rows) { y ->
        MutableList(columns) { x ->
            initialiser(x, y)
        }
    }

    val width: Int
        get() = table.first().size

    val height: Int
        get() = table.size

    fun get(x: Int, y: Int): T = table[y][x]

    fun set(x: Int, y: Int, value: T) {
        table[y][x] = value
    }

    fun addRow(index: Int, initialiser: (Int) -> T) {
        val columns = table.first().size
        table.add(index, MutableList(columns, initialiser))
    }

    fun addColumn(index: Int, initialiser: (Int) -> T) {
        table.forEachIndexed { rowIndex, row ->
            row.add(index, initialiser(rowIndex))
        }
    }

    fun getNeighbours(x: Int, y: Int): List<T> {
        val result = mutableListOf<T>()
        for (y2 in y - 1..y + 1) {
            if (y2 !in 0 until height) {
                continue
            }
            for (x2 in x - 1..x + 1) {
                if (x2 in 0 until width) {
                    result += get(x2, y2)
                }
            }
        }
        return result
    }

    fun count(predicate: (T) -> Boolean): Int {
        return table.sumOf { row ->
            row.count(predicate)
        }
    }

    fun copy(): Grid<T> {
        return Grid(height, width, ::get)
    }

    override fun toString(): String {
        return table.joinToString(separator = "\n") {
            it.joinToString(separator = "")
        }
    }
}