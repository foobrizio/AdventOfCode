package yr2024.day_08

import util.InputExtractor
import kotlin.math.abs

fun main(args: Array<String>) {
    println("Day 8 - Exercise 2")
    val nodeMap = InputExtractor.extractInputForDay8()
    val antiNodeCount = countAntiNodes(nodeMap, false)
    println(antiNodeCount)
    //1180 is too low
}

fun countAllAntiNodesInRowOfChar(char: Char,  nodeMap: List<CharArray>): Set<Pair<Int,Int>>{
    // STEP1: Find all positions where a specific char occurs
    val charPositions = findAllPositionsOfChar(char, nodeMap)
    // STEP2: For each position pair, calculate valid antinodes
    val antiNodes = calculateValidAntiNodesInRow(charPositions, nodeMap)
    return antiNodes
}

fun calculateValidAntiNodesInRow(charPosition:List<Pair<Int,Int>>, nodeMap: List<CharArray>): Set<Pair<Int,Int>> {
    var validAntinodes = mutableSetOf<Pair<Int, Int>>()
    for (position1 in charPosition.indices)
        for (position2 in charPosition.indices) {
            if (position1 != position2 && !validAntinodes.contains(Pair(position1, position2))) {
                // We have a pair. Let's try to create an antinode on both sides
                // TODO: Capire da qui come viaggiare su entrambi i lati
                val r1 = charPosition[position1].first
                val c1 = charPosition[position1].second
                val r2 = charPosition[position2].first
                val c2 = charPosition[position2].second
                val rGap = abs(r1 - r2)
                val cGap = abs(c1 - c2)
                var newR1 = r1
                var newC1 = c1
                var newR2 = r2
                var newC2 = c2
                while(true){
                    if(newR1 < 0 || newC1 < 0 || newR1 >= nodeMap.size || newC1 >= nodeMap[0].size)
                        break;
                    if(r1<=r2 && c1<=c2){
                        newR1 -= rGap
                        newC1 -= cGap
                    }
                    else if(r1>=r2 && c1<=c2){
                        newR1 += rGap
                        newC1 -= cGap
                    }
                    else if(r1<=r2 && c1>=c2){
                        newR1 -= rGap
                        newC1 += cGap
                    }
                    else{
                        newR1 += rGap
                        newC1 += cGap
                    }
                    if (newR1 >= 0 && newR1 < nodeMap.size && newC1 >= 0 && newC1 < nodeMap[0].size)
                        validAntinodes.add(Pair(newR1, newC1))
                }
                while(true){
                    if(newR2 < 0 || newC2 < 0 || newR2 >= nodeMap.size || newC2 >= nodeMap[0].size)
                        break;
                    if(r1<=r2 && c1<=c2){
                        newR2 += rGap
                        newC2 += cGap
                    }
                    else if(r1>=r2 && c1<=c2){
                        newR2 -= rGap
                        newC2 += cGap
                    }
                    else if(r1<=r2 && c1>=c2){
                        newR2 += rGap
                        newC2 -= cGap
                    }
                    else{
                        newR2 -= rGap
                        newC2 -= cGap
                    }
                    if (newR2 >= 0 && newR2 < nodeMap.size && newC2 >= 0 && newC2 < nodeMap[0].size)
                        validAntinodes.add(Pair(newR2, newC2))
                }
            }
        }
    return validAntinodes
}