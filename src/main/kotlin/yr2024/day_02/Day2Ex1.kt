package yr2024.day_02

import util.InputExtractor
import kotlin.math.abs

fun main(args: Array<String>) {
    println("Day 2 - Exercise 1")
    var reports = InputExtractor.extractInputForDay2()
    val result = calculateSafeReports(reports)
    println(result)
}

fun calculateSafeReports(reports:List<List<Int>>): Int{
    var safeCount = 0;
    reports.forEach{
        if(isSafe(it))
            safeCount++;
    }
    return safeCount
}

fun isSafe(report:List<Int>): Boolean{
    if(report[0]==56 && report[1]==54){
        println()
    }
    var prev = report[0]
    if(report[0]==report[1])
        return false
    var increasing = report[0] < report[1]
    for (i in 1..<report.size){
        if(increasing){
            var tooHigh = abs(report[i]-prev) >3
            var lowerOrEqual = report[i]<=prev
            if(tooHigh or lowerOrEqual)
                return false
        }
        else{
            var tooLow = abs(prev-report[i]) >3
            var higherOrEqual = report[i]>=prev
            if(tooLow or higherOrEqual)
                return false
        }
        prev = report[i]
    }
    return true
}