package day09;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StreamProcessing {

	private static int skipGarbage(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '>') {
				return i;
			} else if (input.charAt(i) == '!') {
				i++;
			}
		}
		return input.length();
	}

	private static int computeScore(String input) {
		int grade = 0;
		int score = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '{') {
				grade++;
				score += grade;
			} else if (input.charAt(i) == '}') {
				grade--;
			} else if (input.charAt(i) == '<') {
				i += skipGarbage(input.substring(i));
			}
		}
		return score;
	}

	private static int computeGarbageLength(String input) {
		int totalGarbageLength = 0;

		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '<') {
				int garbageLength = 0;
				int j;
				for (j = i + 1; j < input.length(); j++) {
					if (input.charAt(j) == '>')
						break;
					if (input.charAt(j) == '!')
						j += 1;
					else {
						// System.out.println("c: " + input.charAt(j));
						garbageLength++;
					}
				}
				totalGarbageLength += garbageLength;
				i = j;
			}
		}
		return totalGarbageLength;
	}

	public static void main(String[] args) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("./input/09"));
			// System.out.println("Score: " + computeScore(bufferedReader.readLine()));

			System.out.println("Garbage length: " + computeGarbageLength(bufferedReader.readLine()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
