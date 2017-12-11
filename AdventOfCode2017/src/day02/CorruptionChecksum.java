package day02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorruptionChecksum {

	private static int computeDifferenceEvenlyDivisible(String input) {

		List<String> inputList = Arrays.asList(input.trim().split("	"));
		List<Integer> numbers = new ArrayList<>();

		for (String str : inputList) {
			numbers.add(Integer.valueOf(str));
		}

		for (int i = 0; i < numbers.size() - 1; i++) {
			for (int j = i + 1; j < numbers.size(); j++) {
				if (numbers.get(i) > numbers.get(j) && numbers.get(i) % numbers.get(j) == 0)
					return numbers.get(i) / numbers.get(j);
				if (numbers.get(i) < numbers.get(j) && numbers.get(j) % numbers.get(i) == 0)
					return numbers.get(j) / numbers.get(i);
				if (numbers.get(i) == numbers.get(j))
					return 1;
			}
		}
		return 0;
	}

	private static int computeDifferenceMaxMin(String input) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		List<String> inputList = Arrays.asList(input.trim().split("	"));
		for (String str : inputList) {
			int currentNumber = Integer.valueOf(str);
			if (currentNumber > max)
				max = currentNumber;
			if (currentNumber < min)
				min = currentNumber;
		}
		return max - min;
	}

	public static void main(String[] args) {
		int result = 0;

		BufferedReader bufReader;
		try {
			bufReader = new BufferedReader(new FileReader("./input/02"));
			String line = bufReader.readLine();

			while (line != null) {
				result += computeDifferenceEvenlyDivisible(line);
				line = bufReader.readLine();
			}
			System.out.println("result: " + result);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
