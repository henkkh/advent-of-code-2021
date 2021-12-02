data class SubmarinePos(val horPos: Int = 0, val aim: Int = 0, val depth: Int = 0) {
    operator fun plus(increment: SubmarinePos): SubmarinePos {
        return SubmarinePos(
            this.horPos + increment.horPos,
            this.aim + increment.aim,
            this.depth + this.aim * increment.horPos
        )
    }
}

fun main() {

    fun List<String>.toSubMarinePos(): SubmarinePos {
        val value = this[1].toInt()
        return when (this[0]) {
            "forward" -> SubmarinePos(horPos = value)
            "up" -> SubmarinePos(aim = -value)
            "down" -> SubmarinePos(aim = value)
            else -> SubmarinePos()
        }
    }

    fun part1(lines: List<SubmarinePos>): Int {
        return lines
            .reduce { acc, submarinePos -> acc + submarinePos }
            .let { submarinePos -> submarinePos.horPos * submarinePos.aim }
    }

    fun part2(lines: List<SubmarinePos>): Int {
        return lines
            .fold(SubmarinePos()) { acc, submarinePos -> acc + submarinePos }
            .let { submarinePos -> submarinePos.horPos * submarinePos.depth }
    }


    val testInput = readInput("day2example")
        .map { it.split(' ').toSubMarinePos() }
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("day2")
        .map { it.split(' ').toSubMarinePos() }
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input)}")

}
