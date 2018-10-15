package com.jeffrey.architecture.model;

public class Calculator {
    private int number1;
    private int number2;

    public void setNumbers(int num1, int num2) {
        this.number1 = num1;
        this.number2 = num2;
    }

    public void setNumbers(String str1, String str2) throws NumberFormatException {
        this.number1 = Integer.parseInt(str1);
        this.number2 = Integer.parseInt(str2);
    }

    public int calculatePlus() {
        return number1 + number2;
    }

    public int calculateMinus() {
        return number1 - number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}
