package yr2024.day_04

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 4 - Exercise 1")
    val arrayOfChars = InputExtractor.extractInputForDay4()
    val count = countWordOccurrences(arrayOfChars)
    println(count)
}

fun countWordOccurrences(matrix: List<CharArray>): Int{
    var result = 0
    for (row in matrix.indices){
        for (col in matrix[row].indices){
            if(matrix[row][col]=='X'){
                // START THE COUNT
                if(col>=3 && isFoundAt(row,col,matrix,"east")) // We can search from right to left
                    result++;
                if(row>=3 && col>=3 && isFoundAt(row,col,matrix,"north-east"))
                    result++;
                if(row>=3 && isFoundAt(row,col, matrix,"north")) // We can search from down to up
                    result++;
                if((matrix.size-1-col)>=3 && row>=3 && isFoundAt(row,col,matrix,"north-west"))
                    result++;
                if((matrix.size-1-col)>=3 && isFoundAt(row,col,matrix,"west"))
                    result++;
                if((matrix.size-1-col)>=3 && (matrix[row].size-1-row)>=3 && isFoundAt(row,col,matrix,"south-west"))
                    result++;
                if((matrix[row].size-1-row)>=3 && isFoundAt(row,col,matrix,"south"))
                    result++;
                if(col>=3 && (matrix[row].size-1-row)>=3 && isFoundAt(row,col,matrix,"south-east"))
                    result++;
            }
        }
    }
    return result
}

fun isFoundAt(row: Int, col: Int, matrix: List<CharArray>, direction:String): Boolean{
    when(direction){
        "east" -> {
            if(matrix[row][col-1] != 'M')
                return false
            if(matrix[row][col-2] != 'A')
                return false
            if(matrix[row][col-3] != 'S')
                return false
            return true
        }
        "north-east" -> {
            if(matrix[row-1][col-1] != 'M')
                return false
            if(matrix[row-2][col-2] != 'A')
                return false
            if(matrix[row-3][col-3] != 'S')
                return false
            return true
        }
        "north" -> {
            if(matrix[row-1][col] != 'M')
                return false
            if(matrix[row-2][col] != 'A')
                return false
            if(matrix[row-3][col] != 'S')
                return false
            return true
        }
        "north-west" -> {
            if(matrix[row-1][col+1] != 'M')
                return false
            if(matrix[row-2][col+2] != 'A')
                return false
            if(matrix[row-3][col+3] != 'S')
                return false
            return true
        }
        "west" -> {
            if(matrix[row][col+1] != 'M')
                return false
            if(matrix[row][col+2] != 'A')
                return false
            if(matrix[row][col+3] != 'S')
                return false
            return true
        }
        "south-west" -> {
            if(matrix[row+1][col+1] != 'M')
                return false
            if(matrix[row+2][col+2] != 'A')
                return false
            if(matrix[row+3][col+3] != 'S')
                return false
            return true
        }
        "south" -> {
            if(matrix[row+1][col] != 'M')
                return false
            if(matrix[row+2][col] != 'A')
                return false
            if(matrix[row+3][col] != 'S')
                return false
            return true
        }
        "south-east" -> {
            if(matrix[row+1][col-1] != 'M')
                return false
            if(matrix[row+2][col-2] != 'A')
                return false
            if(matrix[row+3][col-3] != 'S')
                return false
            return true
        }
    }
    return false
}