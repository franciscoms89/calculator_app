package org.example.servicesTest;

import org.example.services.CalculatorService;
import org.example.services.ICalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceTest {

    private ICalculatorService calculator;

    @BeforeEach
    void setUp() {
        calculator = new CalculatorService();
    }

    @Test
    void testAdd() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(-5, calculator.add(1, -6));
    }

    @Test
    void testSubtract() {
        assertEquals(10, calculator.subtract(11, 1));
        assertEquals(-10, calculator.subtract(-15, -5));
    }

    @Test
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
        assertEquals(-6, calculator.multiply(2, -3));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
        assertEquals(-2, calculator.divide(6, -3));
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }

    @Test
    void testSquareRoot() {
        assertEquals(4, calculator.squareRoot(16));
        assertEquals(0, calculator.squareRoot(0));
        assertTrue(Double.isNaN(calculator.squareRoot(-9)), "La raíz cuadrada de un número negativo debe ser NaN");
    }

    @Test
    void testSquared() {
        assertEquals(9, calculator.squared(3));
        assertEquals(0, calculator.squared(0));
    }

    @Test
    void testCubed() {
        assertEquals(27, calculator.cubed(3));
        assertEquals(-27, calculator.cubed(-3));
    }
}