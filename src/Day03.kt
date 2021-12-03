fun main() {

    fun part1(lines: List<List<Int>>): Int {
        val factor = lines[0].size
        val avg = lines.size / 2
        val gamma =
            lines
                // add all bits values
                .reduce { acc, intlist -> intlist.zip(acc, Int::plus) }
                // compare if higher than average convert to character
                .let { il -> il.map { i -> ((i / avg) + '0'.code).toChar() } }
                .joinToString("")
                .toInt(2)
        // bitwise flipping
        val epsilon = (2.pow(factor) -1) - gamma
        println("gamma : ${gamma}, epsilon : $epsilon")
        return epsilon*gamma
    }

    fun oxygenOrC02Rating(lines: List<List<Int>>, useMinority: Boolean): Int {
        tailrec fun filterMoreThanAvg(pos:Int,lines: List<List<Int>>): List<List<Int>>{
            if ( lines.size == 1) return lines
            val majorityBit = if (lines.map{ it[pos]}.average() >= 0.5)  1 else 0
            return filterMoreThanAvg(
                pos +1,
                lines.filter { il -> useMinority.xor( il[pos]  == majorityBit)})
        }
        return filterMoreThanAvg(0,lines)[0].joinToString("").toInt(2)
    }

    fun oxygenRating(lines: List<List<Int>>): Int = oxygenOrC02Rating(lines,false)
    fun co2Rating(lines: List<List<Int>>): Int = oxygenOrC02Rating(lines,true)


    fun part2(lines: List<List<Int>>): Int {
        return oxygenRating(lines)*co2Rating(lines)
    }


    val testInput = readInput("day3example").map { it.toCharArray().map { Character.getNumericValue(it) } }
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("day3").map { it.toCharArray().map { Character.getNumericValue(it) } }
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input)}")

}
