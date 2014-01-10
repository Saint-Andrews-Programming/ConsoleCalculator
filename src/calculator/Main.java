package calculator;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		while (true) {
			System.out.print("Please enter password: ");

			Scanner in = new Scanner(System.in);
			String input = in.next();

			if (input.equalsIgnoreCase("password")) {
				System.out.println("Password correct!");
				break;
			} else {
				System.out.println("Password incorrect!");
			}
		}
	}
}