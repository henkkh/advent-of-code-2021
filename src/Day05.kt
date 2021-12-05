fun main() {

    class LijnStuk(val pointA: Pair<Int, Int>, val pointB: Pair<Int, Int>) {
        val rangeX = if ( pointA.first < pointB.first) (pointA.first..pointB.first) else (pointB.first..pointA.first)
        val rangeY = if ( pointA.second < pointB.second) (pointA.second..pointB.second) else (pointB.second..pointA.second)

        fun isVertical(): Boolean = pointA.second == pointB.second
        fun isHorizontal(): Boolean = pointA.first == pointB.first

        fun onLijnStuk(p: Pair<Int, Int>): Boolean {
            if (p.first !in rangeX || p.second !in rangeY
            ) return false

            if (isVertical() || isHorizontal()) return true
            val rc =  (pointB.second - pointA.second).toDouble()/ (pointB.first - pointA.first)
            val b = pointA.second - rc*pointA.first
            return (p.first*rc + b) == p.second.toDouble()
        }

    }

    fun parseInput(testInput: List<String>): List<LijnStuk> {
        return testInput
            .map {
                it.split(" -> ").map { it1 ->
                    Pair(
                        it1.substringBefore(',').toInt(),
                        it1.substringAfter(',').toInt()
                    )
                }
            }
            .map { LijnStuk(it[0], it[1]) }
    }

    fun Array<Array<Int>>.drukAf( ) {
        this.forEach { ints ->
            run {
                ints.forEach { print(if (it ==0) '.' else it) }
                println()
            }
        }
    }

    fun part1(testInput: List<String>, maxInt: Int): Int {
        val lsList = parseInput(testInput).filter { it.isHorizontal() || it.isVertical() }
        val m = Array(maxInt) { Array(maxInt) { 0 } }
        lsList.map {
            for (i in (it.rangeX)) {
                for (j in (it.rangeY)) {
                    if (it.onLijnStuk(Pair(i, j))) {
                        m[j][i] += 1
                    }
                }
            }
        }
//        m.drukAf()
        return m.sumOf { l -> l.count { it >= 2 } }
    }


    fun part2(testInput: List<String>, maxInt :Int): Int {
        val lsList = parseInput(testInput)
        val m = Array(maxInt) { Array(maxInt) { 0 } }
        lsList.map {
            for (i in (it.rangeX)) {
                for (j in (it.rangeY)) {
                    if (it.onLijnStuk(Pair(i, j))) {
                        m[j][i] += 1
                    }
                }
            }
        }
//        m.drukAf()
        return m.sumOf { l -> l.count { it >= 2 } }
    }

    val testInput = readInput("day5example")
    check(part1(testInput, 10) == 5)
    check(part2(testInput,10) == 12)

    val input = readInput("day5")
    println("answer puzzle 1 : ${part1(input, 1000)}")
    println("answer puzzle 2 : ${part2(input,1000)}")

}
