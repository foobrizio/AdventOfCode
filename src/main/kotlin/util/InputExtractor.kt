package util

import java.io.BufferedReader


class InputExtractor{
    companion object{
        private fun openFile(file: String): String {
            val inputStream = this::class.java.classLoader.getResourceAsStream(file)
                ?: throw IllegalArgumentException("File $file not found in resources!")

            return inputStream.bufferedReader().use(BufferedReader::readText)
        }

        // region RAW
        fun extractRawInputForDay3(): String {
            return openFile("2024/input_day3.txt")
        }

        private fun extractRawInputForDay4(): String {
            return openFile("2024/input_day4.txt")
        }

        private fun extractRawInputForDay6(): String {
            return openFile("2024/input_day6.txt")
        }
        // endregion

        fun extractInputForDay1(): Pair<List<Int>, List<Int>> {
            var list1 = mutableListOf<Int>()
            var list2 = mutableListOf<Int>()
            openFile("2024/input_day1.txt").split("\n")
                .forEach {
                    var rowContent = it.trim().split("\\s+".toRegex())
                    if(rowContent.size == 2) {
                        list1.add(rowContent[0].toInt())
                        list2.add(rowContent[1].toInt())
                    }
                }
            return Pair(list1.sorted(), list2.sorted())
        }

        fun extractInputForDay2(): List<List<Int>> {
            var result = mutableListOf<List<Int>>()
            openFile("2024/input_day2.txt").split("\n")
                .forEach{
                    val rowContent = it.trim().split(" ")
                    if(rowContent.size >2){
                        result.add(rowContent
                            .map { a -> a.toInt() })
                    }

                }
            return result
        }

        fun extractInputForDay3(): List<String> {
            var result = mutableListOf<String>()
            var rawText = extractRawInputForDay3()
            val regex = Regex("mul\\(\\d*,\\d*\\)")
            val matches = regex.findAll(rawText)
            matches.forEach {
                it.groups.first()?.value?.let { it1 -> result.add(it1) }
            }
            return result
        }

        fun extractInputForDay4(): List<CharArray>{
            val rawText = extractRawInputForDay4()

            val rows = rawText.split("\n")
            val result = mutableListOf<CharArray>()
            rows
                .filter {it.length>1}
                .forEachIndexed { index, s ->
                    result.add(index, s.trim().toCharArray())
                }
            return result
        }

        // region DAY5
        fun extractDataForDay5(): Pair<Map<Int,List<Int>>, List<List<Int>>>{
            val rawText = openFile("2024/input_day5.txt")
            val ruleMap = extractRules(rawText)
            val updateList = extractUpdates(rawText)
            return Pair(ruleMap, updateList)
        }

        private fun extractRules(rawText: String): Map<Int,MutableList<Int>>{
            val map = mutableMapOf<Int, MutableList<Int>>()
            val ruleRegex = Regex("^\\d*\\|\\d*\$", RegexOption.MULTILINE)
            val ruleMatches = ruleRegex.findAll(rawText)
            ruleMatches.forEach {
                var rule = it.groups.first()?.value
                val splitted = rule!!.split("|")
                val key = splitted[0].toInt()
                val newValue = splitted[1].toInt()
                var list:MutableList<Int> = mutableListOf()
                if(map[key] != null)
                    list = map[key]!!
                list.add(newValue)
                map[key] = list
            }
            return map
        }

        private fun extractUpdates(rawText: String): List<List<Int>>{
            val updateRegex = Regex("^(\\d*,)*\\d*\$", RegexOption.MULTILINE)
            val updateMatches = updateRegex.findAll(rawText)
            val updateList = mutableListOf<List<Int>>()
            updateMatches.forEach {
                val updateRow = it.groups.first()?.value
                if(updateRow!!.isNotEmpty()){
                    val list = updateRow.split(",")
                        .map { str -> str.toInt() }
                    updateList.add(list)
                }

            }
            return updateList
        }

        //endregion

        fun extractInputForDay6(): List<CharArray>{
            val rawText = extractRawInputForDay6()
            val rows = rawText.split("\n")
            val result = mutableListOf<CharArray>()
            rows
                .filter {it.length>1}
                .forEachIndexed { index, s ->
                    result.add(index, s.trim().toCharArray())
                }
            return result
        }

        fun extractInputForDay7(): List<Pair<Long, List<Long>>>{
            val rawText = openFile("2024/input_day7.txt")
            val rows = rawText.split("\n")
            val result = mutableListOf<Pair<Long,List<Long>>>()
            rows
                .filter {it.length>1}
                .forEach{
                    val splitted = it.split(":")
                    val keyResult = splitted[0].trim().toLong()
                    val numbers:List<Long> = splitted[1].trim()
                        .split(" ")
                        .map{it.toLong()}
                    result.add(Pair(keyResult, numbers))
                }
            return result
        }
    }
}
