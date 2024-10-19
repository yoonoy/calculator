package com.example.calculator;

public class FinalCalculator {
    // Fields to store operands, operator, result, and error flag
    private double operand1;
    private double operand2;
    private char operator;
    private double result;
    private boolean isError;

    // Sets the first operand
    public void setOperand1(double operand) {
        this.operand1 = operand;
    }

    // Sets the second operand
    public void setOperand2(double operand) {
        this.operand2 = operand;
    }

    // Sets the operator for the calculation
    public void setOperator(char operator) {
        this.operator = operator;
    }

    // Performs the calculation based on the operator and operands
    public void calculate() {
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                if (operand2 == 0) {
                    isError = true;
                } else {
                    result = operand1 / operand2;
                }
                break;
            default:
                isError = true;
        }
    }

    // Returns the result of the calculation
    public double getResult() {
        return result;
    }

    // Checks if an error occurred during the calculation
    public boolean isError() {
        return isError;
    }

    // Resets the calculator to its default state
    public void reset() {
        operand1 = 0;
        operand2 = 0;
        operator = '\0';
        result = 0;
        isError = false;
    }
}
