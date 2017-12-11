package day01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class InverseCaptcha {

	private static int computeResult(String input, int step) {
		int result = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == input.charAt((i + step) % input.length()))
				result += input.charAt(i) - '0';
		}

		return result;
	}

	public static void main(String[] args) {
		BufferedReader bufReader;
		try {
			bufReader = new BufferedReader(new FileReader("./input/01"));
			String input = bufReader.readLine();
			System.out.println("resultNextDigit: " + computeResult(input, 1));
			System.out.println("resultHalfwayAroundDigit: " + computeResult(input, input.length() / 2));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
