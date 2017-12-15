package day04;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class HighEntropyPassphrases {

	private static boolean isValidPassphrase(String input, boolean checkAnagram) {
		HashMap<String, String> individualWords = new HashMap<>();
		String[] words = input.split(" ");

		for (String word : words) {
			if (individualWords.containsKey(word))
				return false;

			if (checkAnagram) {
				char[] value = word.toCharArray();
				Arrays.sort(value);
				StringBuilder sortedWord = new StringBuilder();
				for (char c : value)
					sortedWord.append(c);
				if (individualWords.containsValue(sortedWord.toString()))
					return false;
				individualWords.put(word, sortedWord.toString());
			} else {
				individualWords.put(word, word);
			}
		}
		return true;
	}

	public static void main(String[] args) {
		int resultA = 0;
		int resultB = 0;

		BufferedReader bufReader;
		try {
			bufReader = new BufferedReader(new FileReader("./input/04"));
			String line = bufReader.readLine();

			while (line != null) {
				resultA += isValidPassphrase(line, false) ? 1 : 0;
				resultB += isValidPassphrase(line, true) ? 1 : 0;
				line = bufReader.readLine();
			}
			System.out.println("resultA: " + resultA);
			System.out.println("resultB: " + resultB);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
