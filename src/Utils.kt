import java.io.File
import kotlin.math.pow

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

infix fun Int.pow(exponent: Int): Int = toDouble().pow(exponent).toInt()

/*
 * Stack stuff
 */
fun <T> MutableList<T>.push(item: T) = this.add(this.count(), item)
fun <T> MutableList<T>.pop(): T? = if(this.count() > 0) this.removeAt(this.count() - 1) else null
fun <T> MutableList<T>.peek(): T? = if(this.count() > 0) this[this.count() - 1] else null
