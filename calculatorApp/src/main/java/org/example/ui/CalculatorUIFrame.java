package org.example.ui;

import org.example.services.CalculatorService;

import javax.swing.*;

public class CalculatorUIFrame extends JFrame {

    public CalculatorUIFrame(){

        setTitle("Calculadora");
        setSize(500,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new CalculatorUIPanel(new CalculatorService()));

        setVisible(true);
    }
}