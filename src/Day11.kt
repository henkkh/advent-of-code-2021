typealias DumboStack = MutableList<Pair<Int,Int>>

fun main() {

    fun List<String>.parseFile(): List<CharArray> {
        //Surround all boundaries with a additional x
        val seq = (0..this[0].length + 1).map { 'x' }.toCharArray()
        val list = emptyList<CharArray>().toMutableList()
        list.add(seq)
        list.addAll(this.map { ('x' + it + 'x').toCharArray() })
        list.add(seq)
        return list
    }
    fun flashes(dumbos: List<CharArray>): Int {
        val flashing: DumboStack = mutableListOf()

        val maxI = dumbos.size - 2
        val maxJ = dumbos[0].size - 2

        //add 1
        for (i in (1..maxI)) for (j in (1..maxJ)) {
            dumbos[i][j]++
            if ( dumbos[i][j] > '9') flashing.push(Pair(i,j))
        }
        var flashCount = 0
        while (flashing.isNotEmpty()) {
            flashCount++
            val (p,q) = flashing.pop()!!
            for (i in (p-1..p+1)) for (j in (q-1..q+1)) {
                if (dumbos[i][j] <= '9' && dumbos[i][j] != 'x') {
                    dumbos[i][j]++
                    if (dumbos[i][j] > '9') flashing.push(Pair(i,j))
                }
            }
        }
        // set to 0 if >= 9
        for (i in (1..maxI)) for (j in (1..maxJ)) if (dumbos[i][j] >'9' ) dumbos[i][j] = '0'
        return flashCount
    }

    fun part1(dumbos: List<CharArray>): Int {
        return (1..100).sumOf{ flashes(dumbos)}
    }

    fun part2(dumbos: List<CharArray>): Int {
        var iteration = 0
        while ( true) {
            iteration++
            if ( flashes(dumbos) == 100) {
                return iteration
            }
        }
    }

    val testInput = readInput("day11example").parseFile().apply { println(this) }
    check(part1(testInput) == 1656)
    check(part2(testInput) == 95)

    val input = readInput("day11").parseFile()
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input)+ 100} ")
}

