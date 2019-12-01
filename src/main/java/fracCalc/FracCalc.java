/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*; 


public class FracCalc {
	public static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args)
    {
    	
    	System.out.println("Welcome to Frac Calc");
    	System.out.println("Please enter a value followed by an arithmatic operator and another value:");
		System.out.print(".\n.\n.\n" );
		String userInput = scanner.nextLine(); 
    	while (!userInput.equalsIgnoreCase("quit")) {
    		System.out.println("The answer is " + produceAnswer(userInput));
        	System.out.println("Please enter a value followed by an arithmatic operator and another value:");
        	System.out.print(".\n.\n.\n" );
        	userInput = scanner.nextLine(); 
    	}
    	

    }

    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static double produceAnswer(String input) {
    	
    	double finalAnswer;
    	//Substring Input
    	String firstOperandS = input.substring(0, input.indexOf(' '));
    	input = input.substring(input.indexOf(' ') + 1);
    	String operator = input.substring(0, input.indexOf(' '));
    	input = input.substring(input.indexOf(' ') + 1);
    	String secondOperandS = input; 
    	
    	//Separate into whole, num, dem
    	String firstW = findWhole(firstOperandS);
    	String firstN = findNum(firstOperandS);
    	String firstD = findDem(firstOperandS);
    	
    	String secondW = findWhole(secondOperandS);
    	String secondN = findNum(secondOperandS);
    	String secondD = findDem(secondOperandS);
    	
    	//Operate on simplified operands
    	double firstOperand = simplify(firstW, firstN, firstD);
    	double secondOperand = simplify(secondW, secondN, secondD);
    	if(operator.equals("+")) {
    		finalAnswer = add(firstOperand, secondOperand);
    	}
    	else if(operator.equals(" -")) {
    		finalAnswer = subtract(firstOperand, secondOperand);
    	}
    	else if(operator.equals("*")) {
    		finalAnswer = multiply(firstOperand, secondOperand);
    	}
    	else {
    		finalAnswer = divide(firstOperand, secondOperand);
    	}
    	return finalAnswer;
    }
    
    // TODO: Fill in the space below with any helper methods that you think you will need
    public static String findWhole(String operand) {
    	//3_1/2 19/4 965
    	if(operand.contains("_")) {
    		return operand.substring(0, operand.indexOf('_'));
    	}
    	
    	else if(operand.contains("/")) {
    		return "0";
    	}
    	else{
    		return operand;
    	}	
    }
    
    public static String findNum(String operand) {
    	if(operand.contains("_")) {
    		return operand.substring(operand.indexOf('_') + 1, operand.indexOf('/'));
    	}
    	else if(operand.contains("/")) {
    		return operand.substring(0, operand.indexOf('/'));
    	}
    	else {
    		return "0";
    	}
    }
    
    public static String findDem(String operand) {
    	if(operand.contains("/")) {
    		return operand.substring(operand.indexOf('/') + 1);
    	}
    	else {
    		return "1";
    	}
    }
    
    //
    public static double simplify(String whole, String num, String dem) {
    	double wholeD = Double.valueOf(whole); 
    	double numD = Double.valueOf(num); 
    	double demD = Double.valueOf(dem);
    	return wholeD + numD / demD;
    }
    
    //operation methods
    public static double add(double num1, double num2) {
    	return num1 + num2;
    }
    public static double subtract(double num1, double num2) {
    	return num1 - num2;
    }
    public static double multiply(double num1, double num2) {
    	return num1 * num2;
    }
    public static double divide(double num1, double num2) {
    	return num1 / num2;
    }
}
