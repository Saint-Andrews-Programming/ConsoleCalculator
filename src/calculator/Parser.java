package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import calculator.equation.EquationValue;
import calculator.equation.IEquationPart;
import calculator.equation.Operators;

public class Parser {

	private static final String Digits = "(\\p{Digit}+)";
	private static final String HexDigits = "(\\p{XDigit}+)";
	// an exponent is 'e' or 'E' followed by an optionally
	// signed decimal integer.
	private static final String Exp = "[eE][+-]?" + Digits;

	// Optional leading "whitespace"
	private static final String fpRegex = ("[\\x00-\\x20]*" +

	"[+-]?(" + // Optional sign character
			"NaN|" + // "NaN" string
			"Infinity|" + // "Infinity" string

			// A decimal floating-point string representing a finite positive
			// number without a leading sign has at most five basic pieces:
			// Digits . Digits ExponentPart FloatTypeSuffix
			//
			// Since this method allows integer-only strings as input
			// in addition to strings of floating-point literals, the
			// two sub-patterns below are simplifications of the grammar
			// productions from the Java Language Specification, 2nd
			// edition, section 3.10.2.

			// Digits ._opt Digits_opt ExponentPart_opt FloatTypeSuffix_opt
			// Digits ExponentPart_opt FloatTypeSuffix_opt
			"(((" + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|"
			+ "(\\.(" + Digits + ")(" + Exp + ")?)|" +

			// Hexadecimal strings
			"((" +
			// 0[xX] HexDigits ._opt BinaryExponent FloatTypeSuffix_opt
			"(0[xX]" + HexDigits + "(\\.)?)|" +

			// 0[xX] HexDigits_opt . HexDigits BinaryExponent
			// FloatTypeSuffix_opt
			"(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ")" +

			// Optional trailing "whitespace"
			")[pP][+-]?" + Digits + "))" + "[fFdD]?))" + "[\\x00-\\x20]*");

	public static List<IEquationPart> parse(String input) {
		input = applyFixes(input);

		List<IEquationPart> equationParts = new ArrayList<IEquationPart>();

		String number = "";

		for (int i = 0; i < input.length(); i++) {

			if (operatorCheck(input, i)) {
				if (getOperatorType(input.charAt(i) + "") == Operators.SUBTRACT) {
					number = "-";
					i++;
				}

				if (getOperatorType(input.charAt(i) + "") == Operators.ADD) {
					number = "+";
					i++;
				}
			}

			// Check to see if input is a number
			if (Pattern.matches(fpRegex, number + input.charAt(i)))
				number = number + input.charAt(i);

			else {

				if (!number.equalsIgnoreCase(""))
					equationParts.add(new EquationValue(toDouble(number)));

				number = "";
				if (isOperator(input.charAt(i) + "")) {
					equationParts.add(getOperatorType(input.charAt(i) + ""));
					continue;
				}
			}

			if (i == input.length() - 1)
				equationParts.add(new EquationValue(toDouble(number)));
		}

		return equationParts;
	}

	// False == Operator, True == Negative/Positive
	private static boolean operatorCheck(String input, int i) {

		if (isOperator(input.charAt(i) + "") && i != input.length() - 1) {
			if (i != 0) {
				if (isOperator(input.charAt(i) + "")
						&& isOperator(input.charAt(i + 1) + "")) {
					return false;
				}

				if (isOperator(input.charAt(i) + "")
						&& isOperator(input.charAt(i - 1) + "")) {
					return true;
				}
			}

			if (i == 0) {
				return true;
			}
		}

		return false;
	}

	private static Double toDouble(String strDouble) {
		try {
			return Double.valueOf(strDouble);
		} catch (NumberFormatException e) {
			System.out.println("User input error!");
			Main.in.nextLine();
			System.exit(0);
			return 0.0;
		}
	}

	private static String applyFixes(String input) {
		input = input.replaceAll("\\s+", "");
		input = input.replaceAll("--", "+");
		input = input.replaceAll("[+][+]", "+");
		input = input.replaceAll("[.][.]", ".");

		return input;
	}

	private static Operators getOperatorType(String operatorString) {
		if (operatorString.equalsIgnoreCase("+"))
			return Operators.ADD;

		if (operatorString.equalsIgnoreCase("-"))
			return Operators.SUBTRACT;

		if (operatorString.equalsIgnoreCase("*"))
			return Operators.MULTIPLY;

		if (operatorString.equalsIgnoreCase("/"))
			return Operators.DIVIDE;

		if (operatorString.equalsIgnoreCase("("))
			return Operators.PARENTHESES_OPEN;

		if (operatorString.equalsIgnoreCase(")"))
			return Operators.PARENTHESES_CLOSE;

		return null;
	}

	private static boolean isOperator(String operatorString) {
		if (operatorString.equalsIgnoreCase("+"))
			return true;

		if (operatorString.equalsIgnoreCase("-"))
			return true;

		if (operatorString.equalsIgnoreCase("*"))
			return true;

		if (operatorString.equalsIgnoreCase("/"))
			return true;

		if (operatorString.equalsIgnoreCase("("))
			return true;

		if (operatorString.equalsIgnoreCase(")"))
			return true;

		return false;
	}
}
