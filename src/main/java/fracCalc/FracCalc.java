/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*; 


public class FracCalc {
	public static Scanner scanner = new Scanner(System.in); 

    public static void main(String[] args)
    {
    	
    	boolean running  = true;
    	System.out.println("Welcome to Frac Calc");
    	while (running) {
    		System.out.println("Please enter a value followed by an arithmatic operator and another value:");
    		String userInput = scanner.nextLine(); 
    		System.out.print(".\n.\n.\n" );
    		System.out.println("The answer is " + produceAnswer(userInput));
    		
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
    public static String produceAnswer(String input) {
    	
    	String firstOperand = input.substring(0, input.indexOf(' '));
    	input = input.substring(input.indexOf(' ') + 1);
    	String operator = input.substring(0, input.indexOf(' '));
    	input = input.substring(input.indexOf(' ') + 1);
    	String secondOperand = input;  
    	return input;
    }
    // TODO: Fill in the space below with any helper methods that you think you will need

}
