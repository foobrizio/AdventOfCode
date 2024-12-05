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
    for(row in matrix.indices){
        for(col in matrix[row].indices){
            if(matrix[row][col]=='M'){
                // START THE COUNT
                if(row>=3 && col>=3 && isXFoundAt(row,col,matrix,"north-east"))
                    count++;
                if((matrix.size-1-col)>=3 && row>=3 && isXFoundAt(row,col,matrix,"north-west"))
                    count++;
                if((matrix.size-1-col)>=3 && (matrix[row].size-1-row)>=3 && isXFoundAt(row,col,matrix,"south-west"))
                    count++;
                if(col>=3 && (matrix[row].size-1-row)>=3 && isXFoundAt(row,col,matrix,"south-east"))
                    count++;
            }
        }
    }
    return count
}

fun isXFoundAt(row: Int, col: Int, matrix: List<CharArray>, direction: String): Boolean{
    when(direction){
        "north-east" -> {
            if(matrix[row-1][col-1] != 'A')
                return false
            if(matrix[row-2][col-2] != 'S')
                return false
            return (matrix[row-2][col] == 'M' || matrix[row][col-2] == 'M') && (matrix[row-2][col] == 'S' || matrix[row][col-2] == 'S')
        }
        "north-west" -> {
            if(matrix[row-1][col+1] != 'A')
                return false
            if(matrix[row-2][col+2] != 'S')
                return false
            return (matrix[row-2][col] == 'M' || matrix[row][col+2] == 'M') && (matrix[row-2][col] == 'S' || matrix[row][col+2] == 'S')
        }
        "south-west" -> {
            if(matrix[row+1][col+1] != 'A')
                return false
            if(matrix[row+2][col+2] != 'S')
                return false
            return (matrix[row+2][col] == 'M' || matrix[row][col+2] == 'M') && (matrix[row+2][col] == 'S' || matrix[row][col+2] == 'S')
        }
        "south-east" -> {
            if(matrix[row+1][col-1] != 'A')
                return false
            if(matrix[row+2][col-2] != 'S')
                return false
            return (matrix[row+2][col] == 'M' || matrix[row][col-2] == 'M') && (matrix[row+2][col] == 'S' || matrix[row][col-2] == 'S')
        }
    }
    return false
}