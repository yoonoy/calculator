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

    // The text field where the numbers and results are displayed
    @FXML
    private TextField txtDisplay;

    // Keeps track of whether the decimal button has been clicked
    private int decimalClick = 0;

    // Stores the selected operation (+, -, *, /)
    private String generalOperationObject;

    // Stores the first number entered by the user
    private double firstDouble;

    // Called when the controller is initialized. You can add any necessary setup logic here
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialization logic if necessary
    }

    // Handles general button actions such as arithmetic operations (+, -, *, /) and other special functions (AC, +/-)
    @FXML
    private void handleGeneralAction(ActionEvent event) {
        // Get the text of the clicked button
        generalOperationObject = ((Button) event.getSource()).getText();

        switch (generalOperationObject) {
            case "AC":
                // Clear the display and reset variables
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
                // Store the first number and clear the display for the second number input
                firstDouble = Double.parseDouble(txtDisplay.getText());
                txtDisplay.setText(""); // Clear the display for the next input
                decimalClick = 0; // Reset decimal click for the next input
                break;

            default:
                break;
        }
    }

    // Handles digit button clicks (0-9) to display numbers in the text field
    @FXML
    private void handlerDigitAction(ActionEvent event) {
        // Get the digit from the button that was clicked
        String digitObject = ((Button) event.getSource()).getText();

        // Append the clicked digit to the current display text
        String oldText = txtDisplay.getText();
        String newText = oldText + digitObject;
        txtDisplay.setText(newText);
    }

    // Handles decimal point button click
    @FXML
    private void handlerDecimalAction(ActionEvent event) {
        // Only allow one decimal point to be added
        if (decimalClick == 0) {
            String decimalObject = ((Button) event.getSource()).getText();
            String oldText = txtDisplay.getText();
            String newText = oldText + decimalObject;
            txtDisplay.setText(newText);
            decimalClick = 1; // Ensure no more decimal points can be added until the next operation
        }
    }

    // Handles the equals button click and performs the selected arithmetic operation
    @FXML
    private void handlerEqualAction(ActionEvent event) {
        // Check if a second number has been entered
        if (txtDisplay.getText().isEmpty()) {
            txtDisplay.setText("Error");
            return;
        }

        // Variables for the second number and result
        double secondDouble;
        double result = 0;

        // Get the second number from the display
        String secondText = txtDisplay.getText();
        secondDouble = Double.parseDouble(secondText);

        // Perform the arithmetic operation based on the stored operation
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
                // Handle division by zero
                if (secondDouble == 0) {
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
        // Format and display the result
        String format = String.format("%.1f", result);
        txtDisplay.setText(format);
    }
}
