fun main() {


    fun minFuelDetection(testInput: List<Int>, f: (Int)-> Int): Int {
        val occurences = testInput.groupingBy { it }.eachCount()
        val mediaan = testInput[testInput.size/2]
        val q1 = testInput[testInput.size/4]
        val q3 = testInput[(3*testInput.size)/4]
        // Where is the widest spread?
        val range  = if ( (mediaan - q1) > q3 - mediaan ) (mediaan downTo q1) else (mediaan..q3)
        var minFuel = Int.MAX_VALUE
        for( i in range) {
            var fuel = 0
            occurences.forEach {
                fuel += f( it.key - i)*it.value
            }
            if ( fuel < minFuel) {
                minFuel = fuel
            } else {
                return minFuel
            }
        }
    }

    fun part1(testInput: List<Int>): Int = minFuelDetection(testInput) { Math.abs(it) }
    fun part2(testInput: List<Int>): Int = minFuelDetection(testInput) { (1..Math.abs(it)).sum() }


    val testInput = "16,1,2,0,4,2,7,1,2,14".split(',').map{it.toInt()}.sorted()
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("day7")[0].split(',').map{it.toInt()}.sorted()
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input)}")

}
