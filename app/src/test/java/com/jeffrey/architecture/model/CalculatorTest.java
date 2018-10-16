package com.jeffrey.architecture.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void setNumbers_whenGivenTwoValidIntegers_shouldPass() {
        calculator.setNumbers(6, 8);
        assertEquals(calculator.getNumber1(), 6);
        assertEquals(calculator.getNumber2(), 8);
    }

    @Test
    public void setNumbers_whenGivenTwoValidStringsOfNumber_shouldPass() {
        calculator.setNumbers("6", "8");
        assertEquals(calculator.getNumber1(), 6);
        assertEquals(calculator.getNumber2(), 8);
    }

    @Test
    public void setNumbers_whenGivenOneOfInvalidStringOfNumber_shouldNotPass() {
        try {
            calculator.setNumbers("", "8");
        } catch (NumberFormatException nfe) {
            assertNotNull(nfe);
        }
    }

    @Test
    public void calculatePlus_whenGivenTwoNumbers_shouldReturnCorrectResult() {
        calculator.setNumbers("6", "8");
        int result = calculator.calculatePlus();
        assertEquals(result, 14);
    }

    @Test
    public void calculateMinus_whenGivenTwoNumbers_shouldReturnCorrectResult() {
        calculator.setNumbers("6", "8");
        int result = calculator.calculateMinus();
        assertEquals(result, -2);
    }
}
