package yr2024.day_01

import util.InputExtractor
import kotlin.math.abs

fun main(args: Array<String>) {
    println("Day 1 - Exercise 1")
    val input = InputExtractor.extractInputForDay1()

    // IDEA 1 : metodi funzionali
    val result = input.first
        .zip(input.second){
                a,b -> abs(a - b)
        }.reduce{
                a,b -> a+b
        }
    // IDEA 2: metodo classico con for
    //val result = calculateDistance(input.first, input.second)
    println(result)
}

fun calculateDistance(list1: List<Int>, list2: List<Int>): Int{
    var sum: Int = 0
    for(pos in list1.indices){
        sum += abs(list1[pos] - list2[pos])
    }
    return sum
}