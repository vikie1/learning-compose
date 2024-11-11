package com.github.vikie1.tipcalculator

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTest {
    @Test
    fun calculateTip_20PercentNoRoundUp(){
        val tipAmount = 10.00
        val tipPercent = 20.00
        val expectedAmount = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(tipAmount, tipPercent, false)
        assertEquals(expectedAmount, actualTip)
    }
}