package com.spring.javawebserver.webserver;

import java.util.ArrayDeque;
import java.util.Deque;

public class Arith {
    public static boolean validateInfixOrder(String[] infixLiterals) {
        return false;
    }

    public static double evaluateInfixOrder(String[] infixLiterals) {
        return -1;
    }

    public static String[] convertInfixToPostfix(String[] infixLiterals) {
        return infixLiterals;
    }

    public static double evaluatePostfixOrder(String[] postfixLiterals) {
        Deque<Double> operandStack = new ArrayDeque<>();
        for (String postfixLiteral : postfixLiterals) {
            if (isOperator(postfixLiteral)) {
                double secondOperand = operandStack.pop();
                double firstOperand = operandStack.pop();
                double result;
                if (postfixLiteral.equals("+")) {
                    result = firstOperand + secondOperand;
                    operandStack.push(result);
                } else if (postfixLiteral.equals("-")) {
                    result = firstOperand - secondOperand;
                    operandStack.push(result);
                }
                if (postfixLiteral.equals("*")) {
                    result = firstOperand * secondOperand;
                    operandStack.push(result);
                }
                if (postfixLiteral.equals("/")) {
                    result = firstOperand / secondOperand;
                    operandStack.push(result);
                }
                if (postfixLiteral.equals("^")) {
                    result = Math.pow(firstOperand, secondOperand);
                    operandStack.push(result);
                }
            } else if (isOperand(postfixLiteral)) {
                operandStack.push(Double.valueOf(postfixLiteral));
            }
        }
        return operandStack.pop();
    }

    public static boolean isOperand(String possibleOperand) {
        if(possibleOperand.charAt(0) == '-'){
            possibleOperand = possibleOperand.substring(1, possibleOperand.length());
        }
        for (int i = 0; i < possibleOperand.length(); i++){
            if (possibleOperand.charAt(i) < '0' || possibleOperand.charAt(i) > '9'){
                return false;
            }
        }
        return possibleOperand.length() >= 1;
    }

    public static boolean isOperator(String possibleOperator) {
        String operators = "+-/*^";
        return (operators.contains(possibleOperator) && possibleOperator.length() == 1);
    }

    public static boolean isRightAssociative(String operator) {
        if(!isOperator(operator))
            throw new IllegalArgumentException();
        if (operator == "^"){
            return true;
        }
        return false;
    }

    public static int getPrecedence(String operator) {
        switch (operator){
            case "^":
                return 3;
            case "*":
            case "/":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                throw new IllegalArgumentException();
        }
    }
}
