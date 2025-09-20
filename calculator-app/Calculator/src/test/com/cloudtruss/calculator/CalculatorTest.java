package com.cloudtruss.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private Calculator calc;

    // Things that must be setup before running tests.
    // This will be called right before each test is run, which helps to reset things to original state
    @BeforeEach
    public void setup() {
        calc = new Calculator();
    }

    @Test
    public void canAddZeroPlusZero() {
        int sum = calc.add(0, 0);
        assertEquals(0, sum, "Was expecting zero");
    }

    @Test
    public void canAddOnePlusOne() {
        int sum = calc.add(1, 1);
        assertEquals(2, sum, "Was expecting two");
    }

    @Test()
    @Disabled
    // @Ignore For JUnit < 5
    public void canAddMaxIntPlusOne() {
        int sum = (int) calc.add(Integer.MAX_VALUE, 1F);
        // MAX_INT_VALUE = 2147483647
        assertEquals(Integer.MAX_VALUE + 1F, sum);
    }

    @Test
    public void annuityCalculationExampleOne() {
        String answer = calc.calcAnnuity("22000", 7, "0.06", 1);
        assertEquals("$184,664.43", answer);
    }

    @Test
    public void annuityCalculationExampleTwo() {
        String answer = calc.calcAnnuity("1200", 10, "0.08", 4);
        assertEquals("$72,482.38", answer);
    }
}
