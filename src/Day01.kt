fun main() {
    fun part1(lines: List<Int>): Int {
        return lines.subList(0, lines.size - 1)
            .mapIndexed { index, i -> if (lines[index + 1] > i) 1 else 0 }
            .sum()
    }

    fun List<Int>.part2(): List<Int> {
        return this.subList(0, this.size - 2)
            .mapIndexed { index, i -> listOf(i, this[index + 1], this[index + 2]).sum() }
    }

    val testInput = readInput("day1example").map { it.toInt() }
    check(part1(testInput) == 7)
    check(part1(testInput.part2()) == 5)

    val input = readInput("day1").map { it.toInt() }
    println(part1(input))
    println(part1(input.part2()))
}
