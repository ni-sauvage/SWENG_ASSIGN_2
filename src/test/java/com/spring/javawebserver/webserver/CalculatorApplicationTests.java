package com.spring.javawebserver.webserver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests {

	@Test
	public void testParser() {
		String parseString = "(((1-5)^3)+(10-(3+(-6/3))))";
		String[] parsedString = { "(", "(", "(", "1", "-", "5", ")", "^", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "-6", "/", "3", ")", ")", ")", ")" };
		assertEquals(true, Arrays.equals(Parser.parse(parseString), parsedString), "testing parser with non-empty infixLiteral String");
	}

	@Test
    public void testEvaluateInOrder(){
		String[] infixLiterals = { "(", "(", "(", "1", "-", "5", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6", "/", "3", ")", ")", ")", ")" };
        assertEquals(-7, Arith.evaluateInfixOrder(infixLiterals), "testing evaluateInfixOrder with non-empty infixLiteral String");
        infixLiterals = new String[]{ "(", "(", "(", "5", "*", "(", "3", "/", "4", ")", ")", "-", "5", ")", "+", "(", "7", "/", "(", "2", "+", "3", ")", ")", "-", "7", ")"};
        assertEquals(-6.85, Arith.evaluateInfixOrder(infixLiterals), "testing evaluateInfixOrder with non-empty infixLiteral String");
        /*
		infixLiterals = new String[]{"1", "5", "-", "3", "*", "6", "3", "/", "10", "-", "+"};
		assertEquals(new IllegalArgumentException(), Arith.evaluateInfixOrder(infixLiterals), "testing evaluateInfixOrder with invalid infixLiteral String");
        infixLiterals = null;
        assertEquals(new IllegalArgumentException(), Arith.evaluateInfixOrder(infixLiterals), "testing evaluateInfixOrder with empty infixLiteral String");
		*/
        infixLiterals = new String[]{ "(", "(", "(", "1", "-", "5", ")", "*", "3", ")", "+", "(", "-10", "-", "(", "3", "+", "(", "6", "/", "3", ")", ")", ")", ")" };
        assertEquals(-27, Arith.evaluateInfixOrder(infixLiterals), "testing evaluateInfixOrder with non-empty infixLiteral String with negative value");
		infixLiterals = new String[]{ "1", "/", "4" };
        assertEquals(0.25, Arith.evaluateInfixOrder(infixLiterals), "testing evaluateInfixOrder with non-empty infixLiteral String with non-integer result");
		infixLiterals = new String[]{ "3", "^", "4", "^", "2" };
        assertEquals(43046721, Arith.evaluateInfixOrder(infixLiterals), "testing evaluateInfixOrder with non-empty infixLiteral String with power operators");
    }

    @Test
    public void testValidateInfix(){
        String[] infixLiterals = { "(", "(", "(", "1", "-", "5", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6", "/", "3", ")", ")", ")", ")" };
        assertEquals( true, Arith.validateInfixOrder(infixLiterals), "testing validateInfixOrder with non-empty valid infixLiteral String");
        infixLiterals = new String[]{"(", "(", ")", "1", "-", "5", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6", "/", "3", ")", ")", ")", ")"};
        assertEquals(false, Arith.validateInfixOrder(infixLiterals), "testing validateInfixOrder with non-empty invalid infixLiteral String");
        infixLiterals = new String[]{"(", "(", "(", "1", "-", "5", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6", "/", "3", ")", ")", ")"};
        assertEquals(false, Arith.validateInfixOrder(infixLiterals), "testing validateInfixOrder with non-empty invalid infixLiteral String");
        infixLiterals = new String[]{"(", "(", "(", "1", "-", "5", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6", "2", "3", ")", ")", ")", ")"};
        assertEquals(false, Arith.validateInfixOrder(infixLiterals), "testing validateInfixOrder with non-empty invalid infixLiteral String");
        infixLiterals = new String[]{"(", "(", "(", "1", "-", "5", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "+", "2", "3", ")", ")", ")", ")"};
        assertEquals(false, Arith.validateInfixOrder(infixLiterals), "testing validateInfixOrder with non-empty invalid infixLiteral String");
        infixLiterals = new String[]{"(", "(", "(", "1", "-", "5", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6", "/", "+", ")", ")", ")", ")"};
        assertEquals(false, Arith.validateInfixOrder(infixLiterals), "testing validateInfixOrder with non-empty invalid infixLiteral String");
        infixLiterals = null;
        assertEquals(false, Arith.validateInfixOrder(infixLiterals), "testing validateInfixOrder with non-empty invalid infixLiteral String");
        infixLiterals = new String[]{ "(", "(", "(", "5", "*", "(", "3", "/", "4", ")", ")", "-", "5", ")", "+", "(", "7", "/", "(", "2", "+", "3", ")", ")", "-", "7", ")"};
        assertEquals(true, Arith.validateInfixOrder(infixLiterals), "testing validateInfixOrder with non-empty valid infixLiteral String");
    }
    @Test
    public void testInfixToPostfix(){
        String[] infixLiterals = { "(", "(", "(", "1", "-", "5", ")", "*", "3", ")", "+", "(", "10", "-", "(", "3", "+", "(", "6", "/", "3", ")", ")", ")", ")" };
        String[] postfixLiterals = {"1", "5", "-", "3", "*", "10", "3", "6", "3", "/", "+", "-", "+"};
        assertEquals(true, Arrays.equals(Arith.convertInfixToPostfix(infixLiterals), postfixLiterals), "Testing infixToPostfix with non-empty infixLiterals String");
		infixLiterals = new String[]{"1", "^", "2", "^", "3"};
		postfixLiterals = new String[]{"1", "2", "3", "^", "^"};
        assertEquals(true, Arrays.equals(Arith.convertInfixToPostfix(infixLiterals), postfixLiterals), "Testing infixToPostfix with non-empty infixLiterals String");
    }
	@Test
	public void testEvaluatePostfixOrder(){
		String[] expr = {"1", "2", "+"};
		assertEquals(3, Arith.evaluatePostfixOrder(expr), "Testing evaluatePostfix with String Array 1, 2, +");
		expr = new String[] {"1", "2", "+", "4", "*"};
		assertEquals(12, Arith.evaluatePostfixOrder(expr), "Testing evaluatePostfix with String Array 1, 2, +, 4, *");
		expr = new String[] {"1", "2", "+", "4", "*", "3", "/"};
		assertEquals(4, Arith.evaluatePostfixOrder(expr), "Testing evaluatePostfix with String Array 1, 2, +, 4, *, 3, /");
		expr = new String[] {"1", "2", "+", "4", "*", "3", "/", "2", "^"};
		assertEquals(16, Arith.evaluatePostfixOrder(expr), "Testing evaluatePostfix with String Array 1, 2, +, 4, *, 3, /, 2, ^");
		expr = new String[] {"1", "2", "+", "4", "*", "3", "/", "2", "^", "1", "-"};
		assertEquals(15, Arith.evaluatePostfixOrder(expr), "Testing evaluatePostfix with String Array 1, 2, +, 4, *, 3, /, 2, ^, 1, -");
		expr = new String[] {"4", "2", "3", "+", "*"};
		assertEquals(20, Arith.evaluatePostfixOrder(expr), "Testing evaluatePostfix with String Array 4, 2, 3, +, *");
	}
	@Test
	public void testIsOperand(){
		String operand = "1";
		assertEquals(true, Arith.isOperand(operand), "Testing isOperand with valid single-digit operand with no minus");
		operand = "-1";
		assertEquals(true, Arith.isOperand(operand), "Testing isOperand with valid single-digit operand and minus");
		operand = "100";
		assertEquals(true, Arith.isOperand(operand), "Testing isOperand with valid multi-digit operand with no minus");
		operand = "-100";
		assertEquals(true, Arith.isOperand(operand), "Testing isOperand with valid multi-digit operand and minus");
		operand = "-";
		assertEquals(false, Arith.isOperand(operand), "Testing isOperand with invalid string");
		operand = "1-0";
		assertEquals(false, Arith.isOperand(operand), "Testing isOperand with invalid string");
		operand = "10-";
		assertEquals(false, Arith.isOperand(operand), "Testing isOperand with invalid string");
	}
	@Test 
	public void testIsOperator(){
		String operator = "+";
		assertEquals(true, Arith.isOperator(operator), "Testing isOperator with valid operator");
		operator = "-";
		assertEquals(true, Arith.isOperator(operator), "Testing isOperator with valid operator");
		operator = "*";
		assertEquals(true, Arith.isOperator(operator), "Testing isOperator with valid operator");
		operator = "/";
		assertEquals(true, Arith.isOperator(operator), "Testing isOperator with valid operator");
		operator = "^";
		assertEquals(true, Arith.isOperator(operator), "Testing isOperator with valid operator");
		operator = "-1";
		assertEquals(false, Arith.isOperator(operator), "Testing isOperator with single-digit operand and minus");
		operator = "100";
		assertEquals(false, Arith.isOperator(operator), "Testing isOperator with multi-digit operand with no minus");
		operator = "-100";
		assertEquals(false, Arith.isOperator(operator), "Testing isOperator with multi-digit operand and minus");
	}
	@Test
	public void testGetPrecedence(){
		String operator = "+";
		assertEquals(1, Arith.getPrecedence(operator), "Testing getPrecedence with addition");
		operator = "-";
		assertEquals(1, Arith.getPrecedence(operator), "Testing getPrecedence with subtraction");
		operator = "*";
		assertEquals(2, Arith.getPrecedence(operator), "Testing getPrecedence with multiplication");
		operator = "/";
		assertEquals(2, Arith.getPrecedence(operator), "Testing getPrecedence with division");
		operator = "^";
		assertEquals(3, Arith.getPrecedence(operator), "Testing getPrecedence with power");
	}
	@Test 
	public void testIsRightAssociative(){
		String operator = "+";
		assertEquals(false, Arith.isRightAssociative(operator), "Testing isRightAssociative with addition");
		operator = "-";
		assertEquals(false, Arith.isRightAssociative(operator), "Testing isRightAssociative with subtraction");
		operator = "*";
		assertEquals(false, Arith.isRightAssociative(operator), "Testing isRightAssociative with multiplication");
		operator = "/";
		assertEquals(false, Arith.isRightAssociative(operator), "Testing isRightAssociative with division");
		operator = "^";
		assertEquals(true, Arith.isRightAssociative(operator), "Testing isRightAssociative with power");
	}
}
