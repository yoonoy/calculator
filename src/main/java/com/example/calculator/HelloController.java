package com.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField txtDisplay;

    private FinalCalculator calculator = new FinalCalculator();
    private boolean isSecondOperand = false;

    @FXML
    private void handlerDigitAction(ActionEvent event) {
        String digit = ((Button) event.getSource()).getText();
        if (isSecondOperand) {
            txtDisplay.setText("");
            isSecondOperand = false;
        }
        txtDisplay.appendText(digit);
    }

    @FXML
    private void handleGeneralAction(ActionEvent event) {
        if (!txtDisplay.getText().isEmpty()) {
            calculator.setOperand1(Double.parseDouble(txtDisplay.getText()));
            calculator.setOperator(((Button) event.getSource()).getText().charAt(0));
            isSecondOperand = true; // Expecting a second operand next

        }
    }

    @FXML
    private void handlerEqualAction(ActionEvent event) {
        if (!txtDisplay.getText().isEmpty()) {
            calculator.setOperand2(Double.parseDouble(txtDisplay.getText()));
            calculator.calculate();
            if (calculator.isError()) {
                txtDisplay.setText("Error: Division by Zero");
                calculator.reset();
            } else {
                txtDisplay.setText(String.valueOf(calculator.getResult()));
            }
        }
    }

    @FXML
    private void handlerAcAction(ActionEvent event) {
        calculator.reset();
        txtDisplay.setText("");
        isSecondOperand = false;
    }
}