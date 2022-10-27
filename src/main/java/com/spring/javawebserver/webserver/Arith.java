package com.spring.javawebserver.webserver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class Arith {
    
    public static double evaluateInfixOrder(String[] infixLiterals) {
        if (validateInfixOrder(infixLiterals))
            return evaluatePostfixOrder(convertInfixToPostfix(infixLiterals));
        throw new IllegalArgumentException();
    }
    
    public static boolean validateInfixOrder(String[] infixLiterals) {
        if(infixLiterals == null || infixLiterals.length == 0) return false;
        int lbCount = infixLiterals[0].equals("(") ? 1 : 0;
        int rbCount = infixLiterals[infixLiterals.length - 1].equals(")") ? 1 : 0;
        for (int i = 1; i < infixLiterals.length - 1; i++) {
            if (isOperand(infixLiterals[i])) {
                if (isOperand(infixLiterals[i - 1]) || isOperand(infixLiterals[i + 1])) {
                    return false;
                }
            }
            if (isOperator(infixLiterals[i])) {
                if (isOperator(infixLiterals[i - 1]) || isOperator(infixLiterals[i + 1])) {
                    return false;
                }
            }
            if (infixLiterals[i].equals("(")) {
                lbCount++;
                if (isOperator(infixLiterals[i + 1])) {
                    return false;
                }
                if (infixLiterals[i - 1].equals(")") || infixLiterals[i + 1].equals(")")) {
                    return false;
                }
            }
            if (infixLiterals[i].equals(")")) {
                rbCount++;
            }
        }
        return lbCount == rbCount; //if there are equal left and right brackets, return true
      }

    // Shunting yard algorithm
    public static String[] convertInfixToPostfix(String[] infixLiterals) {
        Deque<String> operatorStack = new ArrayDeque<>();
        LinkedList<String> postfixList = new LinkedList<>();
        for (String infixLiteral : infixLiterals) {
            if (isOperand(infixLiteral))
                postfixList.add(infixLiteral); 
            else if (infixLiteral == "(")
                operatorStack.push(infixLiteral);                                                                                                           
            else if (isOperator(infixLiteral)){
                while ((operatorStack.peek() != "(" && operatorStack.peek() != null) &&
                    (Arith.getPrecedence(operatorStack.peek()) > Arith.getPrecedence(infixLiteral) ||
                    (!Arith.isRightAssociative(infixLiteral) && Arith.getPrecedence(operatorStack.peek()) == Arith.getPrecedence(infixLiteral))
                )){
                    postfixList.add(operatorStack.pop());
                }
                operatorStack.push(infixLiteral);
            }
            else if (infixLiteral == ")") {
                while (operatorStack.peek() != "(") {
                    postfixList.add(operatorStack.pop());
                }
                operatorStack.pop();
            }
        }   
        while (operatorStack.peek() != null){
            postfixList.add(operatorStack.pop());
        }
        //Crude conversion ahead, rework if possible.
        String[] postfixLiterals = new String[postfixList.size()];
        for (int i = 0; i < postfixLiterals.length; i++){
            postfixLiterals[i] = postfixList.get(i);
        }
        return postfixLiterals;
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
