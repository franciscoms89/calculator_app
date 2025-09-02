package org.example.listenersTest;

import org.example.listeners.CalculatorActionListener;
import org.example.services.CalculatorService;
import org.example.services.ICalculatorService;
import org.example.ui.CalculatorUIPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorFullTest {

    private ICalculatorService service;
    private CalculatorUIPanel uiPanel;
    private JLabel display;
    private JTextField historyDisplay;
    private CalculatorActionListener listener;

    @BeforeEach
    void setUp() {
        service = new CalculatorService();
        uiPanel = new CalculatorUIPanel(service);

        // Obtenemos referencias al display y historial
        JPanel displayPanel = (JPanel) uiPanel.getComponent(0);
        display = (JLabel) ((JPanel) displayPanel.getComponent(2)).getComponent(0);
        historyDisplay = (JTextField) displayPanel.getComponent(0);

        listener = new CalculatorActionListener(display, historyDisplay, service);
    }

    @Test
    void testServiceOperations() {
        assertEquals(5, service.add(2, 3));
        assertEquals(-1, service.subtract(2, 3));
        assertEquals(6, service.multiply(2, 3));
        assertEquals(2, service.divide(6, 3));
        assertThrows(ArithmeticException.class, () -> service.divide(1, 0));

        assertEquals(4, service.squareRoot(16));
        assertEquals(0, service.squareRoot(0));
        assertEquals(16, service.squared(4));
        assertEquals(8, service.cubed(2));
        assertEquals(27, Math.pow(3, 3)); // cubo directamente
    }

    @Test
    void testSimpleAddition() {
        listener.actionPerformed(new ActionEvent(this, 0, "1"));
        listener.actionPerformed(new ActionEvent(this, 0, "+"));
        listener.actionPerformed(new ActionEvent(this, 0, "2"));
        listener.actionPerformed(new ActionEvent(this, 0, "="));

        assertEquals("3", display.getText().replace(",", ""));
        assertEquals("1+2=3", historyDisplay.getText().replace(",", ""));
    }

    @Test
    void testMultipleOperations() {
        listener.actionPerformed(new ActionEvent(this, 0, "3"));
        listener.actionPerformed(new ActionEvent(this, 0, "+"));
        listener.actionPerformed(new ActionEvent(this, 0, "4"));
        listener.actionPerformed(new ActionEvent(this, 0, "-"));
        listener.actionPerformed(new ActionEvent(this, 0, "2"));
        listener.actionPerformed(new ActionEvent(this, 0, "*"));
        listener.actionPerformed(new ActionEvent(this, 0, "5"));
        listener.actionPerformed(new ActionEvent(this, 0, "="));

        assertEquals("25", display.getText().replace(",", ""));
        assertTrue(historyDisplay.getText().contains("="));
    }

    @Test
    void testSpecialFunctions() {
        // Raíz cuadrada
        listener.actionPerformed(new ActionEvent(this, 0, "9"));
        listener.actionPerformed(new ActionEvent(this, 0, "√"));
        assertEquals("3", display.getText().replace(",", ""));
        assertTrue(historyDisplay.getText().contains("√(9)=3"));

        // Cuadrado
        listener.actionPerformed(new ActionEvent(this, 0, "4"));
        listener.actionPerformed(new ActionEvent(this, 0, "x²"));
        assertEquals("16", display.getText().replace(",", ""));
        assertTrue(historyDisplay.getText().contains("4²=16"));

        // Cubo
        listener.actionPerformed(new ActionEvent(this, 0, "2"));
        listener.actionPerformed(new ActionEvent(this, 0, "x³"));
        assertEquals("8", display.getText().replace(",", ""));
        assertTrue(historyDisplay.getText().contains("2³=8"));
    }

    @Test
    void testChangeSign() {
        listener.actionPerformed(new ActionEvent(this, 0, "5"));
        listener.actionPerformed(new ActionEvent(this, 0, "+/-"));
        assertEquals("-5", display.getText().replace(",", ""));
        listener.actionPerformed(new ActionEvent(this, 0, "+/-"));
        assertEquals("5", display.getText().replace(",", ""));
    }

    @Test
    void testDecimalInput() {
        listener.actionPerformed(new ActionEvent(this, 0, "1"));
        listener.actionPerformed(new ActionEvent(this, 0, ","));
        listener.actionPerformed(new ActionEvent(this, 0, "5"));
        assertEquals("1,5", display.getText());
    }

    @Test
    void testACAndCE() {
        listener.actionPerformed(new ActionEvent(this, 0, "1"));
        listener.actionPerformed(new ActionEvent(this, 0, "+"));
        listener.actionPerformed(new ActionEvent(this, 0, "2"));
        listener.actionPerformed(new ActionEvent(this, 0, "CE"));
        assertEquals("0", display.getText());
        listener.actionPerformed(new ActionEvent(this, 0, "AC"));
        assertEquals("0", display.getText());
        assertEquals("", historyDisplay.getText());
    }

    @Test
    void testBackspace() {
        listener.actionPerformed(new ActionEvent(this, 0, "1"));
        listener.actionPerformed(new ActionEvent(this, 0, "2"));
        listener.actionPerformed(new ActionEvent(this, 0, "BACK"));
        assertEquals("1", display.getText());
        listener.actionPerformed(new ActionEvent(this, 0, "BACK"));
        assertEquals("0", display.getText());
    }

    @Test
    void testDivisionByZeroShowsError() {
        listener.actionPerformed(new ActionEvent(this, 0, "5"));
        listener.actionPerformed(new ActionEvent(this, 0, "/"));
        listener.actionPerformed(new ActionEvent(this, 0, "0"));
        listener.actionPerformed(new ActionEvent(this, 0, "="));
        assertEquals("Error", display.getText());
    }
}