fun main() {

    fun allIn(a: CharArray, b: CharArray): Boolean = (a.count { b.contains(it) }) == a.size
    

    fun segmentParser(numbers: List<CharArray>): Map<String, Int> {
        val segmentMap = emptyMap<String, Int>().toMutableMap()

        val ca1 = numbers.first { it.size == 2 }
        val ca4 = numbers.first { it.size == 4 }
        val ca235 = numbers.filter { it.size == 5 }
        val ca069 = numbers.filter { it.size == 6 }

        segmentMap += Pair(String(ca1), 1)
        segmentMap += Pair(String(numbers.first { it.size == 3 }), 7)
        segmentMap += Pair(String(ca4), 4)
        segmentMap += Pair(String(numbers.first { it.size == 7 }), 8)

        val ca3 = ca235.first { allIn(ca1, it) }
        val ca9 = ca069.first { allIn(ca4, it) }
        val ca5 = ca235.filterNot { it.contentEquals(ca3) }.first { allIn(it, ca9) }
        val ca0 = ca069.filterNot { it.contentEquals(ca9) }.first { allIn(ca1, it) }

        segmentMap += Pair(String(ca3), 3)
        segmentMap += Pair(String(ca5), 5)
        segmentMap += Pair(String(ca9), 9)

        segmentMap += Pair(String(ca235.filterNot { it.contentEquals(ca3) || it.contentEquals(ca5) }.first()), 2)
        segmentMap += Pair(String(ca069.filterNot { it.contentEquals(ca9) }.first { allIn(ca1, it) }), 0)
        segmentMap += Pair(String(ca069.filterNot { it.contentEquals(ca9) || it.contentEquals(ca0) }.first()), 6)

        return segmentMap
    }

    fun String.alphabetized() = String(toCharArray().apply { sort() })

    fun List<String>.parseFile(): List<Pair<List<CharArray>, List<String>>> =
        this.map { it.split('|') }
            .map {
                Pair(
                    it[0].trim().split(' ').map { it.toCharArray().apply { sort() } },
                    it[1].trim().split(' ').map { it.alphabetized() }
                )
            }

    fun part1(testInput: List<Pair<List<CharArray>, List<String>>>): Int {
        val len1478 = listOf(2, 3, 4, 7)
        return testInput.map { it.second.count { it.length in len1478 } }.sum()
    }

    fun part2(testInput: List<Pair<List<CharArray>, List<String>>>): Int =
        testInput.map { line ->
                    val mappedN = segmentParser(line.first)
                    line.second.fold(0) { acc, i -> acc * 10 + mappedN[i]!! }.toInt()
            }.sum()


    val testInput = readInput("day8example").parseFile()
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("day8").parseFile()
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input)}")
}

