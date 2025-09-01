package org.example.listeners;

import org.example.services.ICalculatorService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CalculatorActionListener implements ActionListener {

    private final JLabel display;
    private final JTextField historyDisplay;
    private final ICalculatorService calculatorService;

    private String operator = "";
    private Double num1 = null, num2 = null, result = null;
    private boolean newOperation = true;

    // Historial de lo que pulsa el usuario
    private final StringBuilder historyText = new StringBuilder();
    private String lastNumber = "";

    public CalculatorActionListener(JLabel display, JTextField historyDisplay, ICalculatorService calculatorService) {
        this.display = display;
        this.historyDisplay = historyDisplay;
        this.calculatorService = calculatorService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        try {
            switch (input) {

                case "AC":
                    display.setText("0");
                    historyText.setLength(0);
                    historyDisplay.setText("");
                    operator = "";
                    num1 = num2 = result = null;
                    lastNumber = "";
                    newOperation = true;
                    break;

                case "CE":
                    display.setText("0");
                    lastNumber = "";
                    newOperation = true;
                    break;

                case "BACK":
                    if (!newOperation && display.getText().length() > 1) {
                        display.setText(display.getText().substring(0, display.getText().length() - 1));
                        lastNumber = display.getText();
                    } else {
                        display.setText("0");
                        lastNumber = "0";
                    }
                    break;

                case "+/-":
                    double value = Double.parseDouble(display.getText().replace(",", "."));
                    value = -value;
                    display.setText(formatResult(value));
                    lastNumber = formatResult(value);
                    break;

                case "+": case "-": case "*": case "/":
                    // Guardamos el número que acabamos de escribir
                    if (!lastNumber.isEmpty()) {
                        historyText.append(lastNumber).append(input);
                        historyDisplay.setText(historyText.toString());
                    }

                    if (num1 == null) {
                        num1 = Double.parseDouble(display.getText().replace(",", "."));
                    } else if (!newOperation && !operator.isEmpty()) {
                        num2 = Double.parseDouble(display.getText().replace(",", "."));
                        result = calculate(num1, num2, operator);
                        display.setText(formatResult(result));
                        num1 = result;
                    }

                    operator = input;
                    newOperation = true;
                    lastNumber = "";
                    break;

                case "√":
                    num1 = Double.parseDouble(display.getText().replace(",", "."));
                    if (num1 < 0) {
                        display.setText("Error");
                        historyText.setLength(0);
                        historyText.append("√(").append(formatResult(num1)).append(")");
                        historyDisplay.setText(historyText.toString());
                        num1 = num2 = result = null;
                        operator = "";
                        newOperation = true;
                        lastNumber = "";
                    } else {
                        result = calculatorService.squareRoot(num1);
                        display.setText(formatResult(result));
                        historyText.setLength(0);
                        historyText.append("√(").append(formatResult(num1)).append(")=").append(formatResult(result));
                        historyDisplay.setText(historyText.toString());
                        newOperation = true;
                        lastNumber = "";
                    }
                    break;

                case "x²":
                    num1 = Double.parseDouble(display.getText().replace(",", "."));
                    result = calculatorService.squared(num1);
                    display.setText(formatResult(result));
                    historyText.setLength(0);
                    historyText.append(formatResult(num1)).append("²=").append(formatResult(result));
                    historyDisplay.setText(historyText.toString());
                    newOperation = true;
                    lastNumber = "";
                    break;

                case "x³":
                    num1 = Double.parseDouble(display.getText().replace(",", "."));
                    result = calculatorService.cubed(num1);
                    display.setText(formatResult(result));
                    historyText.setLength(0);
                    historyText.append(formatResult(num1)).append("³=").append(formatResult(result));
                    historyDisplay.setText(historyText.toString());
                    newOperation = true;
                    lastNumber = "";
                    break;

                case "=":
                    if (operator.isEmpty() || num1 == null) break;

                    num2 = Double.parseDouble(display.getText().replace(",", "."));
                    result = calculate(num1, num2, operator);
                    display.setText(formatResult(result));

                    // Añadimos el último número y el resultado al historial
                    historyText.append(lastNumber).append("=").append(formatResult(result));
                    historyDisplay.setText(historyText.toString());

                    // Reiniciamos para la siguiente operación
                    num1 = result;
                    operator = "";
                    newOperation = true;
                    historyText.setLength(0);
                    lastNumber = "";
                    break;

                case ",":
                    if (newOperation) {
                        display.setText("0,");
                        newOperation = false;
                    } else if (!display.getText().contains(",")) {
                        display.setText(display.getText() + ",");
                    }
                    lastNumber = display.getText();
                    break;

                default: // Números
                    if (newOperation || display.getText().equals("0")) {
                        display.setText(input);
                        newOperation = false;
                    } else {
                        display.setText(display.getText() + input);
                    }
                    lastNumber = display.getText();
                    break;
            }

        } catch (ArithmeticException | NumberFormatException ex) {
            display.setText("Error");
            historyText.setLength(0);
            historyDisplay.setText("");
            num1 = num2 = result = null;
            operator = "";
            lastNumber = "";
            newOperation = true;
        }
    }

    private double calculate(double num1, double num2, String operator) {
        return switch (operator) {
            case "+" -> calculatorService.add(num1, num2);
            case "-" -> calculatorService.subtract(num1, num2);
            case "*" -> calculatorService.multiply(num1, num2);
            case "/" -> calculatorService.divide(num1, num2);
            default -> throw new IllegalArgumentException("Operador no válido: " + operator);
        };
    }

    private String formatResult(double value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("#,##0.##", symbols);
        return df.format(value);
    }
}