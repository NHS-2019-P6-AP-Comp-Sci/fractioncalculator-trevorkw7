/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static int nOperated = 0;
	public static int dOperated = 0;
	public static int wOperated = 0;

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Welcome to Frac Calc");
		System.out.println("Please enter a value followed by an arithmatic operator and another value:");
		System.out.print(".\n.\n.\n");
		String userInput = scanner.nextLine();
		while (!userInput.equalsIgnoreCase("quit")) {
			System.out.println("The answer is " + produceAnswer(userInput));
			System.out.println("Please enter a value followed by an arithmatic operator and another value:");
			System.out.print(".\n.\n.\n");
			userInput = scanner.nextLine();
		}

	}

	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {

		double finalAnswer;
		// Substring Input
		String firstOperandS = input.substring(0, input.indexOf(' '));
		input = input.substring(input.indexOf(' ') + 1);
		String operator = input.substring(0, input.indexOf(' '));
		input = input.substring(input.indexOf(' ') + 1);
		String secondOperandS = input;

		// Separate into whole, num, dem
		String firstW = findWhole(firstOperandS);
		String firstN = findNum(firstOperandS);
		String firstD = findDem(firstOperandS);

		String secondW = findWhole(secondOperandS);
		String secondN = findNum(secondOperandS);
		String secondD = findDem(secondOperandS);

		// Parse all string values into integers
		int w1 = Integer.parseInt(firstW);
		int n1 = Integer.parseInt(firstN);
		int d1 = Integer.parseInt(firstD);

		int w2 = Integer.parseInt(secondW);
		int n2 = Integer.parseInt(secondN);
		int d2 = Integer.parseInt(secondD);

		// convert mixed numbers into improper fractions
		if (w1 != 0 && n1 != 0 && d1 != 0) {
			n1 = mixedToImproper(w1, n1, d1);
			w1 = 0;
		}
		if (w2 != 0 && n2 != 0 && d2 != 0) {
			n2 = mixedToImproper(w2, n2, d2);
			w2 = 0;
		}

		// operate

		if (operator.equals("+")) {
			// make common denominator when adding
			if (d1 != d2) {
				int d1Temp = d1;
				int d2Temp = d2;

				n1 *= d2Temp;
				d1 *= d2Temp;
				n2 *= d1Temp;
				d2 *= d1Temp;
			}
			nOperated = add(w1, n1, d1, w2, n2, d2);
			dOperated = d1;
		}
		if (operator.equals("-")) {
			// make common denominator when subtracting
			if (d1 != d2) {
				int d1Temp = d1;
				int d2Temp = d2;

				n1 *= d2Temp;
				d1 *= d2Temp;
				n2 *= d1Temp;
				d2 *= d1Temp;
			}

			// make second operand negative
			if (w2 == 0) {
				n2 = -1 * n2;
			}

			if (w2 != 0) {
				w2 = -1 * w2;
			}
			nOperated = add(w1, n1, d1, w2, n2, d2);
			dOperated = d1;
		}

		if (operator.equals("*")) {
			// mixed * mixed and frac * frac
			if (w1 == 0 && w2 == 0) {
				nOperated = n1 * n2;
				dOperated = d1 * d2;
			}
			// whole * frac or mixed
			if (w1 != 0 && w2 == 0) {
				nOperated = w1 * n2;
				dOperated = d2;
			}
			// frac or mixed * whole
			if (w1 == 0 && w2 != 0) {
				nOperated = w2 * n1;
				dOperated = d1;
			}
			// whole + whole
			if (n1 == 0 && n2 == 0) {
				nOperated = w1 * w2;
				dOperated = 1;
			}
		}

		if (operator.equals("/")) {
			// reciprocal second operand
			if (w2 == 0) {
				int nTemp = n2;
				int dTemp = d2;
				n2 = dTemp;
				d2 = nTemp;
			} else if (w2 != 0) {
				n2 = 1;
				d2 = w2;
				w2 = 0;
			}
			// multiplication
			if (w1 == 0 && w2 == 0) {
				nOperated = n1 * n2;
				dOperated = d1 * d2;
			}
			// whole * frac or mixed
			if (w1 != 0 && w2 == 0) {
				nOperated = w1 * n2;
				dOperated = d2;
			}
			// frac or mixed * whole
			if (w1 == 0 && w2 != 0) {
				nOperated = w2 * n1;
				dOperated = d1;
			}
			// whole + whole
			if (n1 == 0 && n2 == 0) {
				nOperated = w1 * w2;
			}
		}
		if(firstD.equals("0") || firstD.equals("0")) {
			return "ERROR: Cannot divide by zero.";
		}

		return simplify(nOperated, dOperated);
	}

	// TODO: Fill in the space below with any helper methods that you think you will
	// need
	public static String findWhole(String operand) {
		// 3_1/2 19/4 965
		if (operand.contains("_")) {
			return operand.substring(0, operand.indexOf('_'));
		}

		else if (operand.contains("/")) {
			return "0";
		} else {
			return operand;
		}
	}

	public static String findNum(String operand) {
		if (operand.contains("_")) {
			return operand.substring(operand.indexOf('_') + 1, operand.indexOf('/'));
		} else if (operand.contains("/")) {
			return operand.substring(0, operand.indexOf('/'));
		} else {
			return "0";
		}
	}

	public static String findDem(String operand) {
		if (operand.contains("/")) {
			return operand.substring(operand.indexOf('/') + 1);
		} else {
			return "1";
		}
	}

	public static int mixedToImproper(int whole, int num, int dem) {
		int finalNum = 0;
		// negative exception
		if (whole < 0) {
			finalNum = whole * dem - num;
//    		System.out.print("negative");
		} else {
			finalNum = whole * dem + num;
		}
		return finalNum;
	}

	// operation methods
	public static int add(int w1, int n1, int d1, int w2, int n2, int d2) {
		int nOperated = 0;
		// mixed + mixed and frac + frac
		if (w1 == 0 && w2 == 0) {
			nOperated = n1 + n2;
		}
		// whole + frac or mixed
		if (n1 == 0 && n2 != 0) {
			nOperated = mixedToImproper(w1, n2, d2);
		}
		// frac or mixed + whole
		if (n1 != 0 && n2 == 0) {
			nOperated = mixedToImproper(w2, n1, d1);
		}
		// whole + whole
		if (n1 == 0 && n2 == 0) {
			nOperated = w1 + w2;
		}
		return nOperated;
	}

	public static String simplify(int num, int dem) {
		String finalValue = "";
		// simplify 1 and 0 denominators
		if (dem == 1) {
			finalValue = Integer.toString(num);
		} 
		else if (dem == 0) {
			finalValue = "0";
		}

		// extract whole number
		int whole = (num / dem);
		num = num - whole * dem;

		// reduce fraction
		if (num > dem) {
			for (int i = num; i > 0; i--) {
				int remainder = num % i;
				int denominator = dem % i;
				if ((remainder == 0) && (denominator == 0)) {
					num = num / i;
					dem = dem / i;
				}
			}
		} else {
			for (int i = dem; i > 0; i--) {
				int remainder = num % i;
				int denominator = dem % i;
				if ((remainder == 0) && (denominator == 0)) {
					num = num / i;
					dem = dem / i;
				}
			}
		}

		if (num % 3 == 0 && dem % 3 == 0) {
			num = num / 3;
			dem = dem / 3;
		}

		// simplify whole numbers
		if (num == 0 || dem == 0) {
			finalValue = Integer.toString(whole);
		}

		// absolute value counterpart
		int absDenom = Math.abs(dem);
		int absNum = Math.abs(num);

		// string construction
		if (whole == 0) {
			if (num < 0 && dem > 0) {
				finalValue = Integer.toString(num) + "/" + Integer.toString(absDenom);
			} 
			else if (num > 0 && dem < 0) {
				finalValue = Integer.toString(0 - num) + "/" + Integer.toString(absDenom);
			} 
			else if (num > 0 && dem > 0) {
				finalValue = Integer.toString(num) + "/" + Integer.toString(dem);
			} 
			else if (num < 0 && dem < 0) {
				finalValue = Integer.toString(absNum) + "/" + Integer.toString(absDenom);
			}
		}

		if (whole != 0 && num != 0 && dem != 0) {
			if (whole < 0) {
				finalValue = Integer.toString(whole) + "_" + Integer.toString(absNum) + "/"
						+ Integer.toString(absDenom);
			} 
			else if (whole > 0 && ((num < 0 && dem > 0) || (num > 0 && dem < 0))) {
				finalValue = Integer.toString(0 - whole) + "_" + Integer.toString(absNum) + "/"
						+ Integer.toString(absDenom);
			} 
			else if (whole > 0 && num < 0 && dem < 0) {
				finalValue = Integer.toString(whole) + "_" + Integer.toString(absNum) + "/"
						+ Integer.toString(absDenom);
			} 
			else {
				finalValue = Integer.toString(whole) + "_" + Integer.toString(absNum) + "/"
						+ Integer.toString(absDenom);
			}

		}

		return finalValue;
	}

}
