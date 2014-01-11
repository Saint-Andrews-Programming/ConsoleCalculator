package calculator;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("=========================================");
		System.out.println("Welcome to Tommsy64's console calculator!");
		System.out.println("=========================================");

		Parser.parse(in.next());
	}
}
