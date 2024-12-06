package yr2024.day_06

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 6 - Exercise 1")
    val guardMap = InputExtractor.extractInputForDay6()
    val visitedPositions = countDistinctPositions(guardMap)
}


fun countDistinctPositions(guardMap: List<CharArray>): Int{
    var positionSet = mutableSetOf<Pair<Int,Int>>()
    var startingSituation = findStartingSituation(guardMap)
    var position = Pair(startingSituation.first, startingSituation.second)
    var state = startingSituation.third
    positionSet.add(position)
    while(true){
        var result: Triple<Int,Int,Char> = Triple(-1,-1,'?')
        if(state == 'W')
            // we're out of the board
            break;
        else if(state == '^'){
            // We want to go up.
            result = stepUpOrTurn(guardMap, position)

        }
        else if(state == '>'){
            // We want to go right
            result = stepRightOrTurn(guardMap, position)
        }
        else if(state == 'v'){
            // We want to go down
            result = stepDownOrTurn(guardMap, position)
        }
        else if(state == '<'){
            // We want to go left
            result = stepLeftOrTurn(guardMap, position)
        }
        else if(state == '?'){
            // Some unexpected error
            break;
        }
        position = Pair(result.first, result.second)
        state = result.third
    }
    return positionSet.size
}

fun findStartingSituation(guardMap: List<CharArray>): Triple<Int,Int, Char>{
    guardMap.forEachIndexed { row, it  ->
        it.forEachIndexed{col, it2 ->
            if(it2=='v' || it2=='^' || it2=='>' || it2=='<')
                return Triple(row,col, it2)
        }
    }
    return Triple(-1,-1,'-')
}

fun stepUpOrTurn(guardMap: List<CharArray>, startingPosition: Pair<Int,Int>): Triple<Int,Int,Char>{
    val row = startingPosition.first
    val col = startingPosition.second
    guardMap[row][col]='X'
    if(guardMap[row-1][col]=='#'){
        //Obstacle. Let's turn
        if(col+1 == guardMap.size){
            // We're exiting the map
            return Triple(row, col+1, 'W')
        }
        guardMap[row][col+1]='>'
        return Triple(row, col+1, '>')
    }
    else{
        if(row-1 < 0){
            //We're exiting the map
            return Triple(row-1, col, 'W')
        }
        guardMap[row-1][col]='^'
        return Triple(row-1, col,'^')
    }
}

fun stepDownOrTurn(guardMap: List<CharArray>, startingPosition: Pair<Int,Int>): Triple<Int,Int,Char>{
    val row = startingPosition.first
    val col = startingPosition.second
    guardMap[row][col]='X'
    if(guardMap[row+1][col]=='#'){
        //Obstacle. Let's turn
        if(col-1 < 0){
            // We're exiting the map
            return Triple(row, col-1, 'W')
        }
        guardMap[row][col-1]='<'
        return Triple(row, col-1, '<')
    }
    else{
        if(row+1 == guardMap.size){
            //We're exiting the map
            return Triple(row+1, col, 'W')
        }
        guardMap[row-1][col]='v'
        return Triple(row+1, col,'v')
    }
}

fun stepRightOrTurn(guardMap: List<CharArray>, startingPosition: Pair<Int,Int>): Triple<Int,Int,Char>{
    val row = startingPosition.first
    val col = startingPosition.second
    guardMap[row][col]='X'
    if(guardMap[row][col+1]=='#'){
        //Obstacle. Let's turn
        if(row+1 == guardMap.size){
            // We're exiting the map
            return Triple(row+1, col, 'W')
        }
        guardMap[row+1][col]='v'
        return Triple(row+1,col, 'v')
    }
    else{
        if(col+1 == guardMap[row].size){
            //We're exiting the map
            return Triple(row, col+1, 'W')
        }
        guardMap[row][col+1]='>'
        return Triple(row, col+1,'>')
    }
}

fun stepLeftOrTurn(guardMap: List<CharArray>, startingPosition: Pair<Int,Int>): Triple<Int,Int,Char>{
    val row = startingPosition.first
    val col = startingPosition.second
    guardMap[row][col]='X'
    if(guardMap[row][col-1]=='#'){
        //Obstacle. Let's turn
        if(row-1 < 0){
            //We're exiting the map
            return Triple(row-1, col, 'W')
        }
        guardMap[row-1][col]='^'
        return Triple(row-1, col,'^')
    }
    else{
        if(col-1 < 0){
            //We're exiting the map
            return Triple(row, col-1, 'W')
        }
        guardMap[row][col-1]='<'
        return Triple(row, col-1,'<')
    }
}