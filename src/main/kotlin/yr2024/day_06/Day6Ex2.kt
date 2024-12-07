package yr2024.day_06

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 6 - Exercise 1")
    val guardMap = InputExtractor.extractInputForDay6()
    val firstSituation = findStartingSituation(guardMap)
    val visitedPositions = getDistinctPositions(deepCopy(guardMap))
    visitedPositions.remove(Pair(firstSituation.first, firstSituation.second))
    val possibleObstacles = calculateNumberOfPossibleObstacles(guardMap, visitedPositions)
    println(possibleObstacles)
}

fun calculateNumberOfPossibleObstacles(guardMap: List<CharArray>, visitedPositions: Set<Pair<Int,Int>>): Int {
    var cont = 0
    for (position in visitedPositions){
        // For each position we insert a new obstacle and then we check if a loop is created.
        val alteredMap = insertObstacle(guardMap, position)
        if(hasLoop(alteredMap))
            cont++
    }
    return cont
}

fun insertObstacle(guardMap: List<CharArray>, position: Pair<Int,Int>) : List<CharArray>{
    val newGuard = deepCopy(guardMap)
    newGuard[position.first][position.second] = '#'
    return newGuard
}

fun hasLoop(guardMap: List<CharArray>): Boolean{
    val testMap = deepCopy(guardMap)
    val startingSituation = findStartingSituation(testMap)
    val turningHistory = mutableSetOf<Triple<Int,Int,Char>>()
    var position = Pair(startingSituation.first, startingSituation.second)
    var state = startingSituation.third
    while(true){
        var result: Triple<Int,Int,Char> = Triple(-1,-1,'?')
        if(state == 'W')
        // we're out of the board
            return false;
        when(state){
            '^' -> result = stepUpOrTurn(testMap, position)    // We want to go up.
            '>' -> result = stepRightOrTurn(testMap, position) // We want to go right
            'v' -> result = stepDownOrTurn(testMap, position)  // We want to go down
            '<' -> result = stepLeftOrTurn(testMap, position)  // We want to go left
            '?' -> return false // Some unexpected error
        }
        if(result.third != state) {
            if(turningHistory.contains(result))
                return true
            turningHistory.add(result)
        }
        position = Pair(result.first, result.second)
        state = result.third
    }
}

fun deepCopy(matrix: List<CharArray>): List<CharArray> {
    return matrix.map { it.copyOf() }
}