package day06;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MemoryReallocation {
	private static String stringRepresentation(List<Integer> configuration) {
		StringBuilder result = new StringBuilder();

		for (Integer item : configuration) {
			result.append(String.valueOf(item));
		}
		return result.toString();
	}

	private static int countStepsTillRepeat(List<Integer> banks, boolean checkIndex) {
		List<String> configurations = new ArrayList<>();
		int steps = 0;

		while (true) {
			configurations.add(stringRepresentation(banks));
			int max = Integer.MIN_VALUE;
			int index = 0;
			for (int i = 0; i < banks.size(); i++) {
				if (banks.get(i) > max) {
					max = banks.get(i);
					index = i;
				}
			}

			banks.set(index, 0);
			for (int i = 1; i <= max; i++) {
				int localIndex = (index + i) % banks.size();
				int value = banks.get(localIndex);
				banks.set(localIndex, value + 1);
			}

			steps++;
			if (configurations.contains(stringRepresentation(banks))) {
				if (checkIndex) {
					return configurations.size() - configurations.indexOf(stringRepresentation(banks));
				}
				break;
			}
		}

		return steps;
	}

	public static void main(String[] args) {
		BufferedReader bufReader;
		List<Integer> banks = new ArrayList<>();
		try {
			bufReader = new BufferedReader(new FileReader("./input/06"));
			String line = bufReader.readLine();

			for (String bank : line.split("	")) {
				banks.add(Integer.valueOf(bank));
				line = bufReader.readLine();
			}
			// System.out.println("steps: " + countStepsTillRepeat(banks, false));
			System.out.println("steps: " + countStepsTillRepeat(banks, true));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
