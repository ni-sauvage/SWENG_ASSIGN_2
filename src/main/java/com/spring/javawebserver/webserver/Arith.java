package com.spring.javawebserver.webserver;

import java.util.ArrayDeque;
import java.util.ArrayList;
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
        if(infixLiterals == null || infixLiterals.length == 0) return false;                                            // If nothing has been provided to validate, return false                                                  
        String lastLiteral = infixLiterals[infixLiterals.length - 1];                                                   
        String firstLiteral = infixLiterals[0];
        boolean hasOperand = isOperand(firstLiteral) || isOperand(lastLiteral);                                         // An expression with no operands must return false, thus we will create a boolean hasOperand
        int lbCount = firstLiteral.equals("(") ? 1 : 0;                                                       // If the first literal is "(", then start our left bracket count at 1, otherwise 0
        int rbCount = lastLiteral.equals(")") ? 1 : 0;                                                        // If the last literal is ")", then start our right bracket count at 1, otherwise 0
        if((firstLiteral.length() > 4 && !isOperand((firstLiteral))) ||                                                 // If the first literal isn't an operand and has length > 4, then return false 
            (lastLiteral.length() > 4 && !isOperand(lastLiteral)))                                                      // If the last literal isn't an operand and has length > 4, then return false 
            return false;
        if(lastLiteral.equals("(") || firstLiteral.equals(")")) return false;                        // If the first literal is ")" or the last literal is "(" return false
        if(infixLiterals.length == 1){                                                                                  // If there is only one literal, then return true if it is an operand, otherwise false
            if(isOperand(firstLiteral))
                return true;
            return false;
        }
        if(isOperator(lastLiteral)                                                                                      // If the last literal is an operator e.g. "2", "+" return false
            || isFunction(lastLiteral)                                                                                  // If the last literal is a function e.g. 2 + exp return false
            || (isOperator(firstLiteral) && !firstLiteral.equals("-")))                                       // If the first literal is an operator and not "-", then return false. This is to account for situations like "-", "(", "3", "+", "2", ")"
            return false;
        for (int i = 1; i < infixLiterals.length - 1; i++) {                                                            // We have checked for edge cases in the last and first elements. We will now iterate through the rest.
            if(infixLiterals[i].length() > 4 && !isOperand(infixLiterals[i])) return false;                             // If there is some literal that has a length greater than 4 (largest non-operand string possible, e.g. -exp), then return false
            if (isOperand(infixLiterals[i])) {                                                                          // If the literal is a an operand
                if (isOperand(infixLiterals[i - 1]) || isOperand(infixLiterals[i + 1])) {                               // If there is an operand literal to the left or right return false
                    return false;                                                                                      
                }
                hasOperand = true;                                                                                      // Mark these literals as having at least 1 operand
            }
            else if (isOperator(infixLiterals[i])) {                                                                    // If the literal is an operator
                if (isOperator(infixLiterals[i - 1]) || isOperator(infixLiterals[i + 1])                                // If there is an operator to the left or right of it
                        || infixLiterals[i - 1].equals("(")                                                    // Or there is a closing bracket to the left e.g. "(", "+"
                        || infixLiterals[i + 1].equals(")")) {                                                 // Or an opening bracket to the right e.g. "+", ")"  return false
                        return false;                                                                                   
                    }
            }
            else if (infixLiterals[i].equals("(")) {                                                           // If the literal is a left bracket
                lbCount++;                                                                                              // Increment left bracket count
                if (isOperator(infixLiterals[i + 1])) {                                                                 // If there is an operator to the right of the left bracket e.g. "(" return false
                    return false;                                                                                       
                }
                if (infixLiterals[i - 1].equals(")") || infixLiterals[i + 1].equals(")")) {          // If there is a right bracket to the left e.g. ")", "(" or a right bracket to the right e.g. "(", ")" return false
                    return false;
                }
            }
            else if (infixLiterals[i].equals(")")) {                                                           // If the literal is a right bracket
                rbCount++;                                                                                              // Increment right bracket counter
                if (isOperand(infixLiterals[i + 1])) {                                                                  // If there is an operand to the right of the bracket e.g. ")", "3" return false
                    return false;
                }
                if (infixLiterals[i - 1].equals("(") || infixLiterals[i + 1].equals("(")) {          // If there is a left bracket to the left e.g. "(", ")" or a left bracket to the right e.g. ")", "(" return false
                    return false;
                }
            }
            else if(isFunction(infixLiterals[i])){                                                                      // If the literal is a function
                if(isOperand(infixLiterals[i-1]) || infixLiterals[i-1].equals(")"))                           // If there is an operand to the left of the function e.g. "3", "exp" or a right bracket to the left e.g. ")", "exp", return false
                    return false;
                if(!infixLiterals[i + 1].equals("("))                                                         // If there is not a left bracket to the right of the function e.g. "exp", "2" return false
                    return false;                                                                               
            } else {                                                                                                    // If the literal was none of the above, return false
                return false;
            }
        }
        return lbCount == rbCount && hasOperand;                                                                        //if there are equal left and right brackets, and at least one operand has been matched, return true
    }

    // Shunting yard algorithm https://en.wikipedia.org/wiki/Shunting_yard_algorithm
    public static String[] convertInfixToPostfix(String[] infixLiterals) {
        Deque<String> operatorStack = new ArrayDeque<>();
        ArrayList<String> postfixList = new ArrayList<>();
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
