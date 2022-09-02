package com.example.calculator

import org.junit.Test

import org.junit.Assert.*
import encryption.WeirdCryptor

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun rightShiftingWorks() {
        val inputNumbers: IntArray = intArrayOf(127, 0, 1, 2, 3, 5, 100, 125)
        val shifts: IntArray = intArrayOf(1, 2, 3, 4, 5)
        val expectedOutcome: IntArray = intArrayOf(191, 223, 239, 247, 251, 0, 0, 0, 0, 0, 128, 64, 32, 16, 8, 1, 128, 64, 32, 16, 129, 192, 96, 48, 24, 130, 65, 160, 80, 40, 50, 25, 140, 70, 35, 190, 95, 175, 215, 235)

        val sampleObject = WeirdCryptor("Sample", "Sample")
        var expectedOutcomeIndex: Int = 0

        // this part tests if the circular shift is valid
        inputNumbers.forEach { input_numbers ->
            shifts.forEach { shifts ->
                assertEquals(sampleObject.shiftRight8(input_numbers, shifts).code, expectedOutcome[expectedOutcomeIndex])
                expectedOutcomeIndex++
            }
        }
    }

    @Test
    fun cancelOutShifting() {
        val shift: Int = 2
        val upTo: Int = 32

        // This part tests if the right shift and left shift
        // cancels out their result
        val sampleObject = WeirdCryptor("Sample", "Sample")
        for (integer in 1 .. upTo) {
            val rightShifted = sampleObject.shiftRight8(integer, shift).code
            val normal = sampleObject.shiftLeft8(rightShifted, shift).code
            assertEquals(integer, normal)
        }
    }
}