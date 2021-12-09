fun main() {

    fun List<String>.parseFile(): List<CharArray> {
        //Surround all boundaries with a additional 9
        val seq = (0..this[0].length + 1).map { '9' }.toCharArray()
        val list = emptyList<CharArray>().toMutableList()
        list.add(seq)
        list.addAll(this.map { ('9' + it + '9').toCharArray() })
        list.add(seq)
        return list
    }

    fun bottoms(m: List<CharArray>): List<Pair<Int, Int>> {
        val foundRows = emptyList<Pair<Int, Int>>().toMutableList()
        val maxI = m.size - 2
        val maxJ = m[0].size - 2

        //First compare on rowlevel only
        for (i in (1..maxI)) {
            for (j in (1..maxJ)) {
                if (m[i][j] < m[i][j + 1] && m[i][j - 1] > m[i][j]) foundRows.add(Pair(i, j))
            }
        }

        return foundRows.filter { (i, j) ->
            (m[i][j] < m[i + 1][j] && m[i - 1][j] > m[i][j])
        }
    }

    fun part1(m: List<CharArray>): Int {
        return bottoms(m).map { m[it.first][it.second].digitToInt() + 1 }.sum()
    }


    fun part2(m: List<CharArray>): Int {
        val basinSizes = emptyList<Int>().toMutableList()
        bottoms(m).forEach {
            val basinList = emptyList<Pair<Int, Int>>().toMutableList()
            fun basin(p: Pair<Int, Int>) {
                val (i, j) = p
                if (m[i][j] == '9') return
                if (p !in basinList) basinList.add(p)
                ((i - 1)..(i + 1)).filter { it != i && Pair(it, j) !in basinList }.forEach { basin(Pair(it, j)) }
                ((j - 1)..(j + 1)).filter { it != j && Pair(i, it) !in basinList }.forEach { basin(Pair(i, it)) }
            }
            basin(it)
            basinSizes.add(basinList.size)
        }
        return basinSizes.sortedDescending().take(3).fold(1) { acc, i -> acc * i }
    }

    val testInput = readInput("day9example").parseFile()
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("day9").parseFile()
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input)}")
}

