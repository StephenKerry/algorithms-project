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