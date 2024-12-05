package yr2024.day_03

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 3 - Exercise 1")
    var strings = InputExtractor.extractInputForDay3()
    val result = sumAllMultiplications(strings)
    println(result)
}

fun sumAllMultiplications(strings: List<String>): Int{
    var result = 0
    strings.forEach{
        val regex = Regex("\\d+")
        val matches = regex.findAll(it)
        if(matches.count() == 2){
            val iter = matches.iterator()
            val num1 = iter.next().groupValues.first().toInt()
            val num2 = iter.next().groupValues.first().toInt()
            result += num1*num2
        }
    }
    return result
}