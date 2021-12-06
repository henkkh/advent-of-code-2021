fun main() {

    fun breed(testInput: List<Int>, nDays: Int): Int {
        val state = testInput.toMutableList()
        tailrec fun laternFishTimer(index: Int, n: Int) {
            if (n == 0) return
            state[index] -= 1
            if (state[index] < 0) {
                state[index] = 6
                state.add(8)
                laternFishTimer(state.size - 1, n - 1)
            }
            laternFishTimer(index, n - 1)
        }

        testInput.indices.forEach {
            laternFishTimer(it, nDays)
        }
        return state.size
    }


    fun part1(testInput: List<Int>): Int {
        return breed(testInput, 80)
    }


    fun part2(testInput: List<Int>, nDays: Int): Long {
        var dayCounts = (0..8).map { 0L }.toMutableList()
        testInput.forEach { dayCounts[it] += 1L }
        (1..nDays).forEach { n ->
            run {
                val vvv = (0..8).map { 0L }.toMutableList()
                dayCounts.forEachIndexed { index, count ->
                    if (index == 0) {
                        vvv[6] += count
                        vvv[8] += count
                    } else {
                        vvv[index - 1] += count
                    }
                }
                dayCounts = vvv
            }
        }
        return dayCounts.sum()
    }


    val testInput = "3,4,3,1,2".split(',').map{it.toInt()}
    check(part1(testInput) == 5934)
    check(part2(testInput,256) == 26984457539L)


    val input = readInput("day6")[0].split(',').map{it.toInt()}
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input,256)}")

}
