package day07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursiveCircus {

	private static void parseLine(List<String> all, List<String> children, String line) {
		String[] partial = line.split(" -> ");
		String program = partial[0].split(" ")[0];
		String weight = partial[0].split(" ")[1];
		weight = weight.substring(1, weight.length() - 1);
		all.add(program);
		if (partial.length == 2) {
			children.addAll(Arrays.asList(partial[1].split(", ")));
		}

	}

	private static String findBottomProgram(List<String> all, List<String> children) {

		for (String program : all)
			if (!children.contains(program))
				return program;
		return "";
	}

	public static void main(String[] args) {
		BufferedReader bufReader;
		List<String> allPrograms = new ArrayList<>();
		List<String> childrenPrograms = new ArrayList<>();
		try {
			bufReader = new BufferedReader(new FileReader("./input/07"));
			String line = bufReader.readLine();

			while (line != null) {
				parseLine(allPrograms, childrenPrograms, line);
				line = bufReader.readLine();
			}
			System.out.println("parent 0: " + findBottomProgram(allPrograms, childrenPrograms));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
