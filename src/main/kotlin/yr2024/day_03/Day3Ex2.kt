package yr2024.day_03

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 3 - Exercise 2")
    var text = InputExtractor.extractRawInputForDay3()
    var strings = extractEnabledMultiplications(text)
    val result = sumAllMultiplications(strings)
    println(result)
}

fun extractEnabledMultiplications(text: String): List<String> {
    var result = mutableListOf<String>()
    val do_chunks = text.split("do()")
    do_chunks.forEachIndexed { index, s ->
        var without_donts = s.split("don't()")[0]
        val regex = Regex("mul\\(\\d*,\\d*\\)")
        val matches = regex.findAll(without_donts)
        matches.forEach {
            it.groups.first()?.value?.let { it1 -> result.add(it1) }
        }
    }
    return result
}