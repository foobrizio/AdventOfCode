package yr2024.day_08

import util.InputExtractor
import kotlin.math.abs

fun main(args: Array<String>) {
    println("Day 8 - Exercise 1")
    val nodeMap = InputExtractor.extractInputForDay8()
    val antiNodeCount = countAntiNodes(nodeMap, true)
    println(antiNodeCount)
}

fun countAntiNodes(nodeMap: List<CharArray>, once :Boolean): Int {
    var visitedCharacters = mutableSetOf<Char>()
    var totalAntiNodeSet = mutableSetOf<Pair<Int,Int>>()
    for(row in nodeMap.indices){
        for(col in nodeMap[row].indices){
            val char = nodeMap[row][col]
            if(char!='.' && !visitedCharacters.contains(char)){
                // Contiamo gli antinodi del carattere solo se incontrato per la prima volta
                var antiNodesOfChar = setOf<Pair<Int,Int>>()
                if(once)
                    antiNodesOfChar = countFirstAntiNodesOfChar(char,nodeMap)
                else
                    antiNodesOfChar = countAllAntiNodesInRowOfChar(char,nodeMap)
                visitedCharacters.add(char)
                totalAntiNodeSet.addAll(antiNodesOfChar)
            }
        }
    }
    return totalAntiNodeSet.size
}

fun countFirstAntiNodesOfChar(char:Char, nodeMap: List<CharArray>): Set<Pair<Int,Int>>{
    // STEP1: Find all positions where a specific char occurs
    val charPositions = findAllPositionsOfChar(char, nodeMap)
    // STEP2: For each position pair, calculate valid antinodes
    val antiNodes = calculateValidAntiNodes(charPositions, nodeMap)
    return antiNodes
}

fun findAllPositionsOfChar(char: Char, nodeMap: List<CharArray>): List<Pair<Int,Int>>{
    // Complexity: O(n*m)
    var listOfPositions = mutableListOf<Pair<Int,Int>>()
    for(row in nodeMap.indices){
        for(col in nodeMap[row].indices){
            if(nodeMap[row][col]==char){
                listOfPositions.add(Pair(row, col))
            }
        }
    }
    return listOfPositions
}

fun calculateValidAntiNodes(charPosition:List<Pair<Int,Int>>, nodeMap: List<CharArray>): Set<Pair<Int,Int>> {
    var validAntinodes = mutableSetOf<Pair<Int, Int>>()
    for (position1 in charPosition.indices)
        for (position2 in charPosition.indices) {
            if (position1 != position2 && !validAntinodes.contains(Pair(position1, position2))) {
                // We have a pair. Let's try to create an antinode on both sides
                val r1 = charPosition[position1].first
                val c1 = charPosition[position1].second
                val r2 = charPosition[position2].first
                val c2 = charPosition[position2].second
                val rGap = abs(r1 - r2)
                val cGap = abs(c1 - c2)
                var row1NewAntinode = r1
                var col1NewAntinode = c1
                var row2NewAntinode = r2
                var col2NewAntinode = c2
                if(r1<=r2 && c1<=c2){
                    row1NewAntinode=r1-rGap
                    row2NewAntinode=r2+rGap
                    col1NewAntinode=c1-cGap
                    col2NewAntinode=c2+cGap
                }
                else if(r1>=r2 && c1<=c2){
                    row1NewAntinode= r1+rGap
                    row2NewAntinode= r2-rGap
                    col1NewAntinode= c1-cGap
                    col2NewAntinode= c2+cGap
                }
                else if(r1<=r2 && c1>=c2){
                    row1NewAntinode=r1-rGap
                    row2NewAntinode=r2+rGap
                    col1NewAntinode= c1+cGap
                    col2NewAntinode= c2-cGap
                }
                else{
                    row1NewAntinode=r1+rGap
                    row2NewAntinode=r2-rGap
                    col1NewAntinode= c1+cGap
                    col2NewAntinode= c2-cGap
                }
                if (row1NewAntinode >= 0 && row1NewAntinode < nodeMap.size && col1NewAntinode >= 0 && col1NewAntinode < nodeMap[0].size)
                    validAntinodes.add(Pair(row1NewAntinode, col1NewAntinode))
                if (row2NewAntinode >= 0 && row2NewAntinode < nodeMap.size && col2NewAntinode >= 0 && col2NewAntinode < nodeMap[0].size)
                    validAntinodes.add(Pair(row2NewAntinode, col2NewAntinode))
            }
        }
    return validAntinodes
}