package yr2024.day_05

import util.InputExtractor
import yr2024.day_04.countXMasOccurrences

fun main(args: Array<String>) {
    println("Day 5 - Exercise 1")
    val (rules, updates) = InputExtractor.extractDataForDay5()
    val validUpdates = updates.filter { isUpdateValid(it,rules) }
    val result = countMiddlePages(validUpdates)
    println(result)
}

fun isUpdateValid(update: List<Int>, ruleMap: Map<Int, List<Int>>): Boolean{
    update.forEachIndexed{i, page ->
        if(ruleMap.keys.contains(page)){
            val subsequentPages = ruleMap[page]
            update.forEachIndexed{j, page2 ->
                if (i > j && subsequentPages!=null && subsequentPages.contains(page2))
                    return false
            }
        }
    }
    return true
}

//3636 è troppo basso come valore
fun countMiddlePages(updates: List<List<Int>>): Int {
    var count = 0
    updates.forEach {
        count += it[(it.size-1)/2]
    }
    return count
}