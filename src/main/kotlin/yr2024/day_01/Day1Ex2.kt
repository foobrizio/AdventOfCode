import util.InputExtractor

fun main(args: Array<String>) {
    println("Day 1 - Exercise 2")
    val input = InputExtractor.extractInputForDay1()
    val result = calculateSimilarityScore(input.first, input.second)
    println(result)
}

fun calculateSimilarityScore(list1: List<Int>, list2: List<Int>): Int{
    var list3 = createSimilarityList(list1.toMutableList(), list2.toMutableList())
    println("Similarity list calculated")
    return list3
        .filter { a -> a > 0 }
        .reduce{ a, b -> a+b }
}

fun createSimilarityList(list1: MutableList<Int>, list2: MutableList<Int>): MutableList<Int>{
    var list3 = mutableListOf<Int>()
    list1.forEach{
        var value = 0;
        while(true){
            if(list2.size==0)
                break;
            else if (list2[0] < it) {
                try {
                    list2.removeFirst()
                } catch (e: Exception) {
                    break;
                }
            } else if (list2[0] == it) {
                value += it
                try {
                    list2.removeFirst()
                } catch (e: Exception) {
                    break;
                }
            }
            else break;
        }
        list3.add(value)
    }
    return list3
}