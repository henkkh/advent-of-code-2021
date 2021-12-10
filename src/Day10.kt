typealias CharStack = MutableList<Char>

fun main() {

//    For part2
//    ): 1 point.
//    ]: 2 points.
//    }: 3 points.
//    >: 4 points.
    val openChars = listOf('(', '[', '{', '<')
    val closeChars = listOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)


    fun List<String>.parseFile(): List<CharArray> = this.map { it.toCharArray() }

    fun processNotCorruptedNextChar(stack: CharStack, ch: Char): Boolean {
        if (openChars.indexOf(ch) > -1) {
            stack.push(ch)
            return true
        }
        val ci = closeChars.indexOfFirst { it.first == ch }
        if (ci == -1 || stack.isEmpty()) return true
        if (ci == openChars.indexOf(stack.peek())) {
            stack.pop()
            return true
        }
        return false
    }

    fun part1(chunks: List<CharArray>): Int {
        var points = 0
        for (chunk in chunks) {
            val myStack: CharStack = mutableListOf()
            chunk.firstOrNull { !processNotCorruptedNextChar(myStack, it) }?.apply {
                points += closeChars.first { it.first == this }.second
            }
        }
        return points
    }

    // Int causes overflow: switched to Long
    fun part2(chunks: List<CharArray>): Long {
        val list: MutableList<Long> = mutableListOf()
        for (chunk in chunks) {
            val myStack: CharStack = mutableListOf()
            if (chunk.firstOrNull { !processNotCorruptedNextChar(myStack, it) } == null) {
                list.add(
                    (1..myStack.size)
                        .map { openChars.indexOf(myStack.pop()) + 1 }
                        .fold(0L) { acc, i -> acc * 5 + i }
                )
            }
        }
        return list.sorted()[list.size / 2]
    }


    val testInput = readInput("day10example").parseFile()
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("day10").parseFile()
    println("answer puzzle 1 : ${part1(input)}")
    println("answer puzzle 2 : ${part2(input)}")
}

