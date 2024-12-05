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

        fun extractRawInputForDay4(): String {
            return openFile("2024/input_day4.txt")
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


    }
}
