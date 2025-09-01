package org.example.ui;

import org.example.listeners.CalculatorActionListener;
import org.example.listeners.CalculatorKeyListener;
import org.example.services.ICalculatorService;

import javax.swing.*;
import java.awt.*;

public class CalculatorUIPanel extends JPanel {

    private final JLabel display;
    private final JTextField historyDisplay;
    private final ICalculatorService calculatorService;

    public CalculatorUIPanel(ICalculatorService calculatorService) {
        this.calculatorService = calculatorService;

        setLayout(new BorderLayout(5,5));

        // Panel contenedor de los displays (historial y principal)
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));

        // "Pantalla" con el historial
        historyDisplay = new JTextField();
        historyDisplay.setEditable(false);
        historyDisplay.setFocusable(false);
        historyDisplay.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
        historyDisplay.setBackground(Color.WHITE);
        historyDisplay.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
        historyDisplay.setHorizontalAlignment(JTextField.RIGHT);
        historyDisplay.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // altura fija
        displayPanel.add(historyDisplay);

        // Espacio entre displays
        displayPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Display principal
        display = new JLabel("0", SwingConstants.RIGHT);
        display.setOpaque(true);
        display.setBackground(Color.LIGHT_GRAY);
        display.setFont(new Font("Arial Unicode MS", Font.BOLD, 28));
        display.setPreferredSize(new Dimension(0, 60));
        display.setHorizontalAlignment(SwingConstants.RIGHT);

        // Panel intermedio para que el display principal ocupe el ancho por completo
        JPanel displayWrapper = new JPanel(new BorderLayout());
        displayWrapper.add(display, BorderLayout.CENTER);
        displayWrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        displayPanel.add(displayWrapper);

        add(displayPanel, BorderLayout.NORTH);

        // Botones de la calculadora
        JPanel buttonsPanel = new JPanel(new GridLayout(6, 4, 5, 5));
        String[] buttons = {
                "", "AC", "CE", "BACK",
                "√", "x³", "x²", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "+/-", "0", ",", "="};

        CalculatorActionListener listener = new CalculatorActionListener(display, historyDisplay, calculatorService);

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial Unicode MS", Font.BOLD, 18));
            // Desactiva botones vacíos
            if (!text.isEmpty()) button.addActionListener(listener);
            else button.setEnabled(false);
            buttonsPanel.add(button);
        }

        add(buttonsPanel, BorderLayout.CENTER);

        CalculatorKeyListener.setupKeyBindings(this, listener);
    }
}