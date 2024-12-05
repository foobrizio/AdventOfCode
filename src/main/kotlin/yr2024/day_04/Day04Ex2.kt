package yr2024.day_04

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 4 - Exercise 2")
    val arrayOfChars = InputExtractor.extractInputForDay4()
    val count = countXMasOccurrences(arrayOfChars)
    //108 è troppo bassa
    //3623 è troppo alta
    println(count)
}

fun countXMasOccurrences(matrix: List<CharArray>):Int {
    var count = 0
    for(row in 1..<matrix.size-1){
        for(col in 1..<matrix[row].size-1){
            if(matrix[row][col]=='A'){
                if( (matrix[row-1][col-1] == 'M' || matrix[row+1][col+1] =='M') &&
                    (matrix[row-1][col-1] == 'S' || matrix[row+1][col+1] =='S') &&
                    (matrix[row+1][col-1] == 'M' || matrix[row-1][col+1] =='M') &&
                    (matrix[row+1][col-1] == 'S' || matrix[row-1][col+1] =='S'))
                    count++
            }
        }
    }
    return count
}