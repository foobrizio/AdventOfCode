package yr2024.day_06

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 6 - Exercise 1")
    val guardMap = InputExtractor.extractInputForDay6()
    val visitedPositions = getDistinctPositions(guardMap)
    println(visitedPositions.size)
}


fun getDistinctPositions(guardMap: List<CharArray>): MutableSet<Pair<Int,Int>>{
    val positionSet = mutableSetOf<Pair<Int,Int>>()
    val startingSituation = findStartingSituation(guardMap)
    var position = Pair(startingSituation.first, startingSituation.second)
    var state = startingSituation.third
    positionSet.add(position)
    while(true){
        var result: Triple<Int,Int,Char> = Triple(-1,-1,'?')
        when(state){
            'W' -> break;                                           // we're out of the board
            '?' -> break;                                           // Some unexpected error
            '^' -> result = stepUpOrTurn(guardMap, position)        // We want to go up.
            '>' -> result = stepRightOrTurn(guardMap, position)     // We want to go right
            'v' -> result = stepDownOrTurn(guardMap, position)      // We want to go down
            '<' -> result = stepLeftOrTurn(guardMap, position)      // We want to go left
        }
        position = Pair(result.first, result.second)
        state = result.third
        if(state != 'W')
            positionSet.add(position)
    }
    return positionSet
}

fun findStartingSituation(guardMap: List<CharArray>): Triple<Int,Int, Char>{
    guardMap.forEachIndexed { row, it  ->
        it.forEachIndexed{col, it2 ->
            if(it2=='v' || it2=='^' || it2=='>' || it2=='<')
                return Triple(row,col, it2)
        }
    }
    return Triple(-1,-1,'?')
}

fun stepUpOrTurn(guardMap: List<CharArray>, startingPosition: Pair<Int,Int>): Triple<Int,Int,Char>{
    val row = startingPosition.first
    val col = startingPosition.second
    guardMap[row][col]='X'
    if(row-1< 0){
        //We're exiting the map
        return Triple(row-1, col, 'W')
    }
    else if(guardMap[row-1][col]=='#'){
        //Obstacle. Let's turn, without making steps
        guardMap[row][col]='>'
        return Triple(row, col, '>')
    }
    else{
        // Keep going up
        guardMap[row-1][col]='^'
        return Triple(row-1, col,'^')
    }
}

fun stepDownOrTurn(guardMap: List<CharArray>, startingPosition: Pair<Int,Int>): Triple<Int,Int,Char>{
    val row = startingPosition.first
    val col = startingPosition.second
    guardMap[row][col]='X'
    if(row+1 == guardMap.size){
        //We're exiting the map
        return Triple(row+1, col, 'W')
    }
    else if(guardMap[row+1][col]=='#'){
        //Obstacle. Let's turn, without making steps
        guardMap[row][col]='<'
        return Triple(row, col, '<')
    }
    else{
        // Keep going down
        guardMap[row+1][col]='v'
        return Triple(row+1, col,'v')
    }
}

fun stepRightOrTurn(guardMap: List<CharArray>, startingPosition: Pair<Int,Int>): Triple<Int,Int,Char>{
    val row = startingPosition.first
    val col = startingPosition.second
    guardMap[row][col]='X'
    if(col+1 == guardMap[row].size){
        //We're exiting the map
        return Triple(row, col+1, 'W')
    }
    else if(guardMap[row][col+1]=='#'){
        //Obstacle. Let's turn, without making steps
        guardMap[row][col]='v'
        return Triple(row,col, 'v')
    }
    else{
        // Keep going right
        guardMap[row][col+1]='>'
        return Triple(row, col+1,'>')
    }
}

fun stepLeftOrTurn(guardMap: List<CharArray>, startingPosition: Pair<Int,Int>): Triple<Int,Int,Char>{
    val row = startingPosition.first
    val col = startingPosition.second
    guardMap[row][col]='X'
    if(col-1 < 0){
        //We're exiting the map
        return Triple(row, col-1, 'W')
    }
    else if(guardMap[row][col-1]=='#'){
        //Obstacle. Let's turn, without making steps
        guardMap[row][col]='^'
        return Triple(row, col,'^')
    }
    else{
        // Keep going left
        guardMap[row][col-1]='<'
        return Triple(row, col-1,'<')
    }
}