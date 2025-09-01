package org.example.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CalculatorKeyListener {

    private static void bind(JPanel panel, String keyStroke, String command, CalculatorActionListener listener) {
        InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = panel.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(keyStroke), command);
        actionMap.put(command, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command));
            }
        });
    }

    public static void setupKeyBindings(JPanel panel, CalculatorActionListener listener) {

        // Números NUMPAD
        for (int i = 0; i <= 9; i++) {
            bind(panel, "NUMPAD" + i, String.valueOf(i), listener);
        }

        // Números (fila superior del teclado)
        for (int i = 0; i <= 9; i++) {
            bind(panel, String.valueOf(i), String.valueOf(i), listener);
        }

        // Operadores NUMPAD
        bind(panel, "ADD", "+", listener);
        bind(panel, "SUBTRACT", "-", listener);
        bind(panel, "MULTIPLY", "*", listener);
        bind(panel, "DIVIDE", "/", listener);

        // = o ENTER
        bind(panel, "ENTER", "=", listener);

        // Punto decimal
        bind(panel, "DECIMAL", ",", listener);
        bind(panel,".", ",", listener);

        // Retroceso, Escape y Suprimir
        bind(panel, "BACK_SPACE", "BACK", listener);
        bind(panel, "DELETE", "CE", listener);
        bind(panel, "ESCAPE", "AC", listener);

        // Funciones especiales
        bind(panel, "R", "√", listener);
        bind(panel, "r", "√", listener);
        bind(panel, "S", "x²", listener);
        bind(panel, "s", "x²", listener);
        bind(panel, "C", "x³", listener);
        bind(panel, "c", "x³", listener);

        // Signo +/- (tecla "M" (más/menos))
        bind(panel, "M", "+/-", listener);
        bind(panel, "m", "+/-", listener);
    }
}