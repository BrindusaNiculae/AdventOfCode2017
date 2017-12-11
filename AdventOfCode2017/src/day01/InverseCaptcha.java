package day01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InverseCaptcha {

	private static int resultNextDigit(String input) {
		int result = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == input.charAt((i + 1) % input.length()))
				result += input.charAt(i) - '0';
		}

		return result;
	}

	private static int resultHalfwayAroundDigit(String input) {
		int result = 0;
		int length = input.length();
		int step = length / 2;

		for (int i = 0; i < length; i++) {
			if (input.charAt(i) == input.charAt((i + step) % length))
				result += input.charAt(i) - '0';
		}

		return result;
	}

	public static void main(String[] args) {
		BufferedReader bufReader;
		try {
			bufReader = new BufferedReader(new FileReader("./input/01"));
			String input = bufReader.readLine();
			System.out.println("resultNextDigit: " + resultNextDigit(input));
			System.out.println("resultHalfwayAroundDigit: " + resultHalfwayAroundDigit(input));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
