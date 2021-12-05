data class BingoCard(val cardValues: List<List<Int>>) {
    private val rowAgg = cardValues.map { it.sum() }.toMutableList()
    private val colAgg = rowAgg.indices.map { index -> colSum(index) }.toMutableList()
    private var lastFoundNum: Int? = null

    fun bingo(): Boolean {
        return rowAgg.any { it == 0 } || colAgg.any { it == 0 }
    }

    fun score(): Int {
        return lastFoundNum!! * rowAgg.sum()
    }

    fun findNumber(num: Int): Pair<Int, Int>? {
        for (i in rowAgg.indices) {
            for (j in rowAgg.indices) {
                if (cardValues[i][j] == num) {
                    this.rowAgg[i] = this.rowAgg[i] - num
                    this.colAgg[j] = this.colAgg[j] - num
                    lastFoundNum = num
                    return Pair(i, j)
                }
            }
        }
        return null
    }

    private fun colSum(col: Int) = cardValues.sumOf { row -> row[col] }
}


fun main() {

    fun parseInput(testInput: List<String>): List<BingoCard> {
        val list = mutableListOf<BingoCard>()
        val len = (testInput.size - 1) / 6

        for (i in (0 until len)) {
            list.add(
                BingoCard(
                    testInput.subList(i * 6 + 2, i * 6 + 7)
                        .map { it.trim().split("""\s+""".toRegex()).map { it1 -> it1.toInt() } })
            )
        }
        return list
    }

    fun part1(testInput: List<String>): Int? {
        val drawNumbers = testInput[0].split(',').map { it.toInt() }
        val bcList = parseInput(testInput)

        drawNumbers.forEach { i ->
            bcList.forEach { bc -> bc.findNumber(i) }
            bcList.firstOrNull { it.bingo() }?.also {
                return it.score()
            }
        }
        return null
    }


    fun part2(testInput: List<String>): Int? {
        var lowestScore: Int? = null
        val drawNumbers = testInput[0].split(',').map { it.toInt() }
        val bcList = parseInput(testInput).toMutableList()

        drawNumbers.forEach { i ->
            bcList.forEach { bc -> bc.findNumber(i) }
            val sl = bcList.filter { it.bingo() }
            sl.forEach {
                lowestScore = it.score()
                bcList.remove(it)
            }
            println(lowestScore)
//            println(bcList.filter { it.bingo() })
            if (bcList.isEmpty()) return lowestScore
        }
        return lowestScore
    }

    val testInput = readInput("day4example")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("day4")
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input)}")

}
