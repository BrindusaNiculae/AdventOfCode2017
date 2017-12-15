package day05;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Maze {

	private static int countStepsToExit(List<Integer> maze, boolean alwaysIncrease) {
		int steps = 0;
		int totalIndex = 0;

		while (totalIndex < maze.size()) {
			int step = maze.get(totalIndex);
			if (!alwaysIncrease && step >= 3) {
				maze.set(totalIndex, step - 1);
			} else {
				maze.set(totalIndex, step + 1);
			}
			totalIndex += step;
			steps++;
		}
		return steps;
	}

	public static void main(String[] args) {
		BufferedReader bufReader;
		List<Integer> maze = new ArrayList<>();
		try {
			bufReader = new BufferedReader(new FileReader("./input/05"));
			String line = bufReader.readLine();

			while (line != null) {
				maze.add(Integer.valueOf(line));
				line = bufReader.readLine();
			}
			// System.out.println("stepsTakenA: " + countStepsToExit(maze, true));
			System.out.println("stepsTakenB: " + countStepsToExit(maze, false));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
