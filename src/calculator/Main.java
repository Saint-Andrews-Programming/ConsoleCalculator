package calculator;

import java.util.List;
import java.util.Scanner;

import calculator.equation.IEquationPart;

public class Main {
	
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("=========================================");
		System.out.println("Welcome to Tommsy64's console calculator!");
		System.out.println("=========================================");

		List<IEquationPart> equationParts = Parser.parse(in.next());

		for (IEquationPart part : equationParts) {
			System.out.println(part.toString());
		}
	}
}
