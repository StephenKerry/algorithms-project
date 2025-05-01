package utils

fun findLargestNumber(inputList: List<Int>): Int? {
    if (inputList.isEmpty()) return null
    var largest = inputList[0]
    for (number in inputList) {
        if (number > largest) {
            largest = number
        }
    }
    return largest
}
fun countVowelsAndConsonants(input: String): Pair<Int, Int> {
    val vowels = setOf('a', 'e', 'i', 'o', 'u')
    var vowelCount = 0
    var consonantCount = 0

    for (char in input.lowercase()) {
        if (char in 'a'..'z') {
            if (char in vowels) vowelCount++
            else consonantCount++
        }
    }

    return Pair(vowelCount, consonantCount)
}

fun mostFrequentElement(inputList: List<String>): String? {
    if (inputList.isEmpty()) return null

    val frequencyMap = mutableMapOf<String, Int>()

    // Count frequency of each string
    for (item in inputList) {
        if (frequencyMap.containsKey(item)) {
            val currentCount = frequencyMap[item] ?: 0
            frequencyMap[item] = currentCount + 1
        } else {
            frequencyMap[item] = 1
        }
    }

    var mostFrequent: String? = null
    var highestCount = 0

    // Find the first element with the highest frequency
    for (item in inputList) {
        val count = frequencyMap[item] ?: 0
        if (count > highestCount) {
            highestCount = count
            mostFrequent = item
        }
    }

    return mostFrequent
}


fun countOccurrences(haystack: String, needle: String): Int {
    if (needle.isEmpty() || haystack.length < needle.length) return 0

    val lowerHaystack = haystack.lowercase()
    val lowerNeedle = needle.lowercase()

    var count = 0
    var index = 0

    while (index <= lowerHaystack.length - lowerNeedle.length) {
        val segment = lowerHaystack.substring(index, index + lowerNeedle.length)
        if (segment == lowerNeedle) {
            count++
            index += lowerNeedle.length // move past this occurrence (non-overlapping)
        } else {
            index++
        }
    }

    return count
}
fun isPalindrome(input: String): Boolean {
    val cleaned = input.lowercase().filter { it.isLetterOrDigit() }
    return cleaned == cleaned.reversed()
}

