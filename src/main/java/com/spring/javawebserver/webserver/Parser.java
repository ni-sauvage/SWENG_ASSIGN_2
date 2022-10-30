package com.spring.javawebserver.webserver;

import java.util.ArrayList;

public class Parser {
    
    public static String[] parse (String expr){
        ArrayList<String> parseList = new ArrayList<String>();
        ArrayList<Character> operandList = new ArrayList<Character>();
        expr = expr.replaceAll("\\s+","");
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
                if(!Arith.isOperand(Character.toString(expr.charAt(i-1)))){
                    operandList.add(expr.charAt(i));
                } else {
                    if(operandList.size() != 0){
                        parseList.add(charListToString(operandList));
                        operandList = new ArrayList<Character>();
                    }
                    parseList.add(Character.toString(expr.charAt(i)));

                }
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
