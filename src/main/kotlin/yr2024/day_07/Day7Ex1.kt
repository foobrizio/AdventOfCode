package yr2024.day_07

import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 7 - Exercise 1")
    val operationList = InputExtractor.extractInputForDay7()
    val sumOfValidOperations = calculateValidOperations(operationList)
    println(sumOfValidOperations)
    //19434596952 is too low
    //609962899570 is too low
}

fun calculateValidOperations(opList: List<Pair<Long, List<Long>>>): Long {
    var cont = 0L
    for (i in opList.indices) {
        if(isValidOperation(opList[i]))
            cont+=opList[i].first
    }
    return cont
}

fun isValidOperation(op: Pair<Long, List<Long>>): Boolean {
    val result = op.first
    // Since there can be only two available operators, + and *, we'll represent all possible configurations with a
    // binary number, where 0 is a + and 1 is a *. For example, if we have a sequence of numbers like 1 14 75 3, a
    // hypothetical configuration 1*14+73+3 will be represented with the binary number 101
    var binaryCode = createInitialBinaryCode(op.second)
    do{
        var calculation = 0L
        binaryCode.forEachIndexed { index, c ->
            if(index == 0){
                if(c == '0')
                    calculation = op.second[index] + op.second[index+1]
                if(c == '1')
                    calculation = op.second[index] + op.second[index+1]
            }
            else{
                if(c == '0')
                    calculation += op.second[index+1]
                if(c == '1')
                    calculation *= op.second[index+1]
            }
        }
        if(result == calculation) return true
        binaryCode = incrementBinaryCode(binaryCode)
    }while (!isAllZero(binaryCode))
    return false
}

fun createInitialBinaryCode(sequence: List<Long>): String {
    val length = sequence.size-1
    var str = ""
    for (i in 0 until length) {
        str+="0"
    }
    return str
}

fun incrementBinaryCode(binaryCode: String): String {
    var newBinaryCode = binaryCode.toString()
    for(i in newBinaryCode.length-1 downTo 0){
        if(newBinaryCode[i] == '1')
            newBinaryCode = newBinaryCode.substring(0,i)+'0'+newBinaryCode.substring(i+1)
        else{
            newBinaryCode = newBinaryCode.substring(0,i)+'1'+newBinaryCode.substring(i+1)
            break;
        }
    }
    return newBinaryCode
}

fun isAllZero(binaryCode: String): Boolean = !binaryCode.contains('1')
