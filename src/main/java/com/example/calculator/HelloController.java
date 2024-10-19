package com.example.calculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController implements Initializable {

    private Label label;
    @FXML
    private TextField txtDisplay;
    private int decimalClick = 0;
    private String generalOperationObject;
    private double firstDouble;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if necessary
    }

    @FXML
    private void handleGeneralAction(ActionEvent event) {
        generalOperationObject = ((Button) event.getSource()).getText();

        switch (generalOperationObject) {
            case "AC":

                txtDisplay.setText("");
                decimalClick = 0;
                firstDouble = 0;
                break;
            case "+/-":
                double plusMinus = Double.parseDouble(txtDisplay.getText());
                plusMinus = plusMinus * (-1);
                txtDisplay.setText(String.valueOf(plusMinus));
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                // Store the first number and set the operation
                firstDouble = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText(""); // Clear for the second input
                decimalClick = 0;
                break;
            default:
                break;
        }
    }

    @FXML
    private void handlerDigitAction(ActionEvent event) {
        String digitObject = ((Button) event.getSource()).getText();
        String oldText = txtDisplay.getText();
        String newText = oldText + digitObject;
        txtDisplay.setText(newText);
    }

    @FXML
    private void handlerDecimalAction(ActionEvent event) {
        if (decimalClick == 0) {
            String decimalObject = ((Button) event.getSource()).getText();
            String oldText = txtDisplay.getText();
            String newText = oldText + decimalObject;
            txtDisplay.setText(newText);
            decimalClick = 1;
        }
    }

    @FXML
    private void handlerEqualAction(ActionEvent event) {
        // Ensure that we have a second number
        if (txtDisplay.getText().isEmpty()) {
            txtDisplay.setText("Error");
            return;
        }

        double secondDouble;
        double result = 0;

        // Get the second number
        String secondText = txtDisplay.getText();
        secondDouble = Double.parseDouble(secondText);


        switch (generalOperationObject) {
            case "+":
                result = firstDouble + secondDouble;
                break;
            case "-":
                result = firstDouble - secondDouble;
                break;
            case "*":
                result = firstDouble * secondDouble;
                break;
            case "/":
                if (secondDouble == 0) {
                    // Handle division by zero
                    txtDisplay.setText("Error");
                    return;
                }
                result = firstDouble / secondDouble;
                break;
            case "%":
                result = firstDouble % secondDouble;
                break;
            default:
                txtDisplay.setText("Error");
                return;
        }

        // Display the result
        String format = String.format("%.1f", result);
        txtDisplay.setText(format);
    }
}