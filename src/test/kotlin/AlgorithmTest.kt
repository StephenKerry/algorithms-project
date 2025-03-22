import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import utils.findLargestNumber


class FindLargestNumberTest {

    @Test
    fun `should return null for an empty list`() {
        val inputList = emptyList<Int>()
        val result = findLargestNumber(inputList)
        assertNull(result, "Expected null for an empty list")
    }

    @Test
    fun `should return the only element in a single-item list`() {
        val inputList = listOf(42)
        val result = findLargestNumber(inputList)
        assertEquals(42, result, "Expected the only element for a single-item list")
    }

    @Test
    fun `should return the correct largest number for a list of positive integers`() {
        val inputList = listOf(1, 3, 7, 2, 5)
        val result = findLargestNumber(inputList)
        assertEquals(7, result, "Expected the largest number from the list")
    }

    @Test
    fun `should return the correct largest number for a list with negative integers`() {
        val inputList = listOf(-10, -20, -3, -50)
        val result = findLargestNumber(inputList)
        assertEquals(-3, result, "Expected the largest number from the negative integers list")
    }

    @Test
    fun `should return the correct largest number for a list with duplicate largest numbers`() {
        val inputList = listOf(4, 4, 4, 4)
        val result = findLargestNumber(inputList)
        assertEquals(4, result, "Expected the correct largest number even with duplicates")
    }

    @Test
    fun `should return the correct largest number when the list has mixed positive and negative numbers`() {
        val inputList = listOf(-10, 0, 5, -3, 20, -1)
        val result = findLargestNumber(inputList)
        assertEquals(20, result, "Expected the correct largest number from a mixed list")
    }

    @Test
    fun `should return the correct largest number for a list with all zeros`() {
        val inputList = listOf(0, 0, 0, 0)
        val result = findLargestNumber(inputList)
        assertEquals(0, result, "Expected 0 for a list with all zeros")
    }

    @Test
    fun `should return the correct largest number for a very large list`() {
        val inputList = (1..1_000_000).toList()
        val result = findLargestNumber(inputList)
        assertEquals(1_000_000, result, "Expected the largest number from a very large list")
    }

    @Test
    fun `should handle a list with Int_MIN_VALUE and Int_MAX_VALUE correctly`() {
        val inputList = listOf(Int.MIN_VALUE, Int.MAX_VALUE)
        val result = findLargestNumber(inputList)
        assertEquals(Int.MAX_VALUE, result, "Expected Int.MAX_VALUE as the largest number")
    }

}

