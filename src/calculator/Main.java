package calculator;

import java.util.List;
import java.util.Scanner;

import calculator.equation.Equation;
import calculator.equation.IEquationPart;

public class Main {

	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("=========================================");
		System.out.println("Welcome to Tommsy64's console calculator!");
		System.out.println("=========================================");
		System.out.println();

		try {
			List<IEquationPart> equationParts = Parser.parse(in.next());

			for (IEquationPart part : equationParts) {
				System.out.println(part.toString());
			}

			System.out.println();

			Equation equation = new Equation(equationParts);

			System.out.println(equation.value());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("An unknown error has accured!");
		}
	}
}
