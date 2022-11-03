package com.spring.javawebserver.webserver;

import java.util.ArrayList;

public class Parser {
    /**
     * 
     * @param expr - String inputted into webapp
     * @return Array of Strings, parsed into separate components
     */
    
    public static String[] parse (String expr){
        if(expr == null) return null;                                                               // If there is no input, return null
        expr = expr.replaceAll("\\s+","");                                       // Remove all whitespace from comment
        ArrayList<String> parseList = new ArrayList<String>();                                      // Create arraylist to store results of parsing
        ArrayList<Character> operandList = new ArrayList<Character>();                              // Create arraylist to store multi-digit operands
        for(int i = 0; i < expr.length(); i++){
            if(expr.charAt(i) == '(' || expr.charAt(i) == ')' || 
                (Arith.isOperator(Character.toString(expr.charAt(i))) && expr.charAt(i) != '-')){
                if(operandList.size() != 0){
                    parseList.add(charListToString(operandList));
                    operandList = new ArrayList<Character>();
                }
                parseList.add(Character.toString(expr.charAt(i)));
            }
            else if(Arith.isOperand(Character.toString(expr.charAt(i))))
                operandList.add(expr.charAt(i));
            else if(expr.charAt(i) == '-'){
                if(i == 0 || !Arith.isOperand(Character.toString(expr.charAt(i-1)))){
                    operandList.add(expr.charAt(i));
                } else {
                    if(operandList.size() != 0){
                        parseList.add(charListToString(operandList));
                        operandList = new ArrayList<Character>();
                    }
                    parseList.add(Character.toString(expr.charAt(i)));

                }
            } 
            else {
                operandList.add(expr.charAt(i));
            }
        }
        if (!operandList.isEmpty())
            parseList.add(charListToString(operandList));
        return stringListToStringArray(parseList);
    }


    public static String charListToString(ArrayList<Character> list){
        char[] combineString = new char[list.size()];
        for(int i = 0; i < combineString.length; i++){
            combineString[i] = list.get(i);
        }
        return new String(combineString);
    }

    public static String[] stringListToStringArray(ArrayList<String> list){
        String[] stringArray = new String[list.size()];
        for(int i = 0; i < stringArray.length; i++){
            stringArray[i] = list.get(i);
        }
        return stringArray;
    }
}
