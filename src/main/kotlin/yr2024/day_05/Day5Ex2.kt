package yr2024.day_05

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 5 - Exercise 2")
    val (rules, updates) = InputExtractor.extractDataForDay5()
    val correctedUpdates = updates.filter { !isUpdateValid(it,rules) }
        .map{fixInvalidUpdate(it, rules)}
    val count = countMiddlePages(correctedUpdates)
    println(count)
}

fun fixInvalidUpdate(update: List<Int>, ruleMap:Map<Int,List<Int>>): List<Int>{
    var newUpdate = update.toMutableList()
    while(!isUpdateValid(newUpdate, ruleMap)){
        var i = 1
        while(i<newUpdate.size){
            val page = newUpdate[i]
            if(ruleMap.keys.contains(page)){
                val subsequentPages = ruleMap[page]
                newUpdate.forEachIndexed{j, page2 ->
                    if (i > j && subsequentPages!=null && subsequentPages.contains(page2)) {
                        newUpdate[i] = page2
                        newUpdate[j] = page
                        i=j
                    }
                }
            }
            i++
        }
    }
    return newUpdate
}
