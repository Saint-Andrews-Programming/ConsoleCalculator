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
		System.out.println("Type exit to exit the calculator");

		Boolean run = true;
		while (run) {
			try {
				String input = in.next();

				if (input.equalsIgnoreCase("exit"))
					System.exit(0);

				List<IEquationPart> equationParts = Parser.parse(input);

				for (IEquationPart part : equationParts) {
					System.out.print(part.toString() + " ");
				}

				System.out.print(" = ");

				Equation equation = new Equation(equationParts);
				for (IEquationPart value : equation.value()) {
					System.out.print(value.toString() + " ");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("An unknown error has accured!");
			}
		}
	}
}
