package com.spring.javawebserver.webserver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CalculatorApplicationTests {

	@Test
    public void testEvaluateInOrder(){
    
    }
    @Test
    public void testValidateInfix(){
        
    }
    @Test
    public void testInfixToPostfix(){
        
    }
	@Test
	public void testEvaluatePostfixOrder(){

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
