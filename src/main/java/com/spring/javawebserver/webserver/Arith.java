package com.spring.javawebserver.webserver;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Set;

public class Arith {
    
    public static double evaluateInfixOrder(String[] infixLiterals) {
        if (validateInfixOrder(infixLiterals))
            return evaluatePostfixOrder(convertInfixToPostfix(infixLiterals));
        throw new IllegalArgumentException();
    }
    
    public static boolean validateInfixOrder(String[] infixLiterals) {
        if(infixLiterals == null || infixLiterals.length == 0) return false;
        boolean hasOperand = isOperand(infixLiterals[0]) || isOperand(infixLiterals[infixLiterals.length - 1]);
        int lbCount = infixLiterals[0].equals("(") ? 1 : 0;
        int rbCount = infixLiterals[infixLiterals.length - 1].equals(")") ? 1 : 0;
        if((infixLiterals[0].length() > 4 && !isOperand((infixLiterals[0]))) || (infixLiterals[infixLiterals.length - 1].length() > 4 && !isOperand(infixLiterals[infixLiterals.length - 1]))) return false;
        if(infixLiterals[infixLiterals.length - 1].equals("(") || infixLiterals[0].equals(")")) return false;
        if(infixLiterals.length == 1){
            if(isOperand(infixLiterals[0]))
                return true;
            return false;
        }
        if(isOperator(infixLiterals[infixLiterals.length-1]) 
            || isFunction(infixLiterals[infixLiterals.length-1])
            || (isOperator(infixLiterals[0])  && !infixLiterals[0].equals("-")))
            return false;
        for (int i = 1; i < infixLiterals.length - 1; i++) {
            if(infixLiterals[i].length() > 4 && !isOperand(infixLiterals[i])) return false;
            if (isOperand(infixLiterals[i])) {
                if (isOperand(infixLiterals[i - 1]) || isOperand(infixLiterals[i + 1])) {
                    return false;
                }
                hasOperand = true;
            }
            else if (isOperator(infixLiterals[i])) {
                if (isOperator(infixLiterals[i - 1]) || isOperator(infixLiterals[i + 1])
                        || infixLiterals[i - 1].equals("(")
                        || infixLiterals[i + 1].equals(")")) {
                        return false;
                    }
            }
            else if (infixLiterals[i].equals("(")) {
                lbCount++;
                if (isOperator(infixLiterals[i + 1])) {
                    return false;
                }
                if (infixLiterals[i - 1].equals(")") || infixLiterals[i + 1].equals(")")) {
                    return false;
                }
            }
            else if (infixLiterals[i].equals(")")) {
                rbCount++;
                if (isOperand(infixLiterals[i + 1])) {
                    return false;
                }
                if (infixLiterals[i - 1].equals("(") || infixLiterals[i + 1].equals("(")) {
                    return false;
                }
            }
            else if(isFunction(infixLiterals[i])){
                if(isOperand(infixLiterals[i-1]) || infixLiterals[i-1].equals(")"))
                    return false;
                if(!infixLiterals[i+1].equals("("))
                    return false;
            } else {
                return false;
            }
        }
        return lbCount == rbCount && hasOperand; //if there are equal left and right brackets, and at least one operand has been matched, return true
    }

    // Shunting yard algorithm
    public static String[] convertInfixToPostfix(String[] infixLiterals) {
        Deque<String> operatorStack = new ArrayDeque<>();
        LinkedList<String> postfixList = new LinkedList<>();
        for (String infixLiteral : infixLiterals) {
            if (isOperand(infixLiteral))
                postfixList.add(infixLiteral); 
            else if (infixLiteral.equals("("))
                operatorStack.push(infixLiteral);                                                                                                           
            else if (isOperator(infixLiteral)){
                while ((isOperator(operatorStack.peek())) &&
                    ((Arith.getPrecedence(operatorStack.peek()) > Arith.getPrecedence(infixLiteral)) ||
                    (!Arith.isRightAssociative(infixLiteral) && Arith.getPrecedence(operatorStack.peek()) == Arith.getPrecedence(infixLiteral))
                )){
                    postfixList.add(operatorStack.pop());
                }
                operatorStack.push(infixLiteral);
            }
            else if(isFunction(infixLiteral))
                operatorStack.push(infixLiteral);
            else if (infixLiteral.equals(")")) {
                while (!operatorStack.peek().equals("(")) {
                    postfixList.add(operatorStack.pop());
                }
                operatorStack.pop();
                if(isFunction(operatorStack.peek()))
                    postfixList.add(operatorStack.pop());
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
            if (isOperator(postfixLiteral) || isFunction(postfixLiteral)) {
                double secondOperand = operandStack.pop();
                double firstOperand = 0;
                if(!isFunction(postfixLiteral) && !operandStack.isEmpty()){
                    firstOperand = operandStack.pop();
                }
                double result;
                switch (postfixLiteral) {
                    case "+":
                        result = firstOperand + secondOperand;
                        operandStack.push(result);
                        break;
                    case "-":
                        result = firstOperand - secondOperand;
                        operandStack.push(result);
                        break;
                    case "*":
                        result = firstOperand * secondOperand;
                        operandStack.push(result);
                        break;
                    case "/":
                        result = firstOperand / secondOperand;
                        operandStack.push(result);
                        break;
                    case "^":
                        result = Math.pow(firstOperand, secondOperand);
                        operandStack.push(result);
                        break;
                    case "log":
                        operandStack.push(Math.log(secondOperand));
                        break;
                    case "exp":
                        operandStack.push(Math.exp(secondOperand));
                        break;
                    case "-log":
                        operandStack.push(-Math.log(secondOperand));
                        break;
                    case "-exp":
                        operandStack.push(-Math.exp(secondOperand));
                        break;
                }
            } else if (isOperand(postfixLiteral)) {
                operandStack.push(Double.valueOf(postfixLiteral));
            }
        }
        return operandStack.pop();
    }

    public static boolean isOperand(String possibleOperand) {
        if(possibleOperand == null) return false;
        if(possibleOperand.charAt(0) == '-'){
            possibleOperand = possibleOperand.substring(1, possibleOperand.length());
        }
        for (int i = 0; i < possibleOperand.length(); i++){
            boolean decimalPoint = false;
            if (possibleOperand.charAt(i) < '0' || possibleOperand.charAt(i) > '9'){
                if(possibleOperand.charAt(i) == '.'){
                    if (decimalPoint == true || possibleOperand.endsWith("."))
                        return false;
                    decimalPoint = true;
                }
                else{
                    return false;
                }
            }
        }
        return possibleOperand.length() >= 1;
    }

    public static boolean isOperator(String possibleOperator) {
        if(possibleOperator == null) return false;
        String operators = "+-/*^";
        return (operators.contains(possibleOperator) && possibleOperator.length() == 1);
    }

    public static boolean isFunction(String possibleFunction){
        if(possibleFunction == null) return false;
        Set<String> functions = Set.of("log", "exp", "-log", "-exp");
        return functions.contains(possibleFunction);
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
