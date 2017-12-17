package day07;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecursiveCircus {

	private static class Program {
		String name;
		int weight;
		List<Program> children;
		Program parent;

		public Program(String name) {
			this.name = name;
			children = new ArrayList<>();
			parent = null;
		}

		@Override
		public String toString() {
			if (parent != null) {
				return name + "[" + weight + "]" + "(" + parent.name + ")->" + children + "\n";
			} else {
				return name + "[" + weight + "]" + "(---)->" + children + "\n";
			}
		}
	}

	private static void constructTreeFromLine(Map<String, Program> all, String line) {
		String[] partial = line.split(" -> ");
		String baseProgram = partial[0].split(" ")[0];
		String stringValueWeight = partial[0].split(" ")[1];
		int intValueWeight = Integer.valueOf(stringValueWeight.substring(1, stringValueWeight.length() - 1));

		if (all.containsKey(baseProgram)) {
			Program programToUpdate = all.get(baseProgram);
			programToUpdate.weight = intValueWeight;
		} else {
			Program newProgram = new Program(baseProgram);
			newProgram.weight = intValueWeight;
			all.put(baseProgram, newProgram);
		}
		if (partial.length == 2) {
			String[] childPrgmNames = partial[1].split(", ");
			for (String childPrgmName : childPrgmNames) {
				if (all.containsKey(childPrgmName)) {
					Program programToUpdate = all.get(childPrgmName);
					programToUpdate.parent = all.get(baseProgram);
				} else {
					Program childPrgm = new Program(childPrgmName);
					all.put(childPrgmName, childPrgm);
					childPrgm.parent = all.get(baseProgram);
				}
				all.get(baseProgram).children.add(all.get(childPrgmName));
			}
		}
	}

	private static Program findBottomProgram(Map<String, Program> all) {

		for (String prgmName : all.keySet())
			if (all.get(prgmName).parent == null)
				return all.get(prgmName);
		return null;
	}

	private static int weightSoFar(Program node) {
		if (node.children.isEmpty()) {
			return node.weight;
		}
		int totalWeight = node.weight;
		for (Program childProgram : node.children) {
			totalWeight += weightSoFar(childProgram);
		}
		return totalWeight;
	}

	/*
	 * Returns 0, max, or -min depending on the values.
	 *
	 * The min is returned as negative to know if the diff needs to be subtracted or
	 * added.
	 *
	 * If all the values are similar -> returns 0.
	 *
	 * If the different value is max, the correct value is computed by subtracting
	 * the diff to the corresponding node weight.
	 *
	 * If the different value is min, the correct value is computed by adding the
	 * diff to the corresponding node weight.
	 *
	 * diff == Math.abs(different value - balanced value);
	 */
	private static int differentValue(List<Integer> values) {
		if (values == null || values.size() == 0)
			return 0;
		int max = values.get(0);
		int min = values.get(0);
		int differentValue = values.get(0);

		for (int i = 1; i < values.size(); i++) {
			if (values.get(i) > max)
				max = values.get(i);
			if (values.get(i) < min)
				min = values.get(i);
			differentValue ^= values.get(i);
		}
		if (max == min)
			return 0;
		if (max == differentValue)
			return max;
		return 0 - min;
	}

	private static List<Integer> weightTillChild(Program node) {
		if (node.children.isEmpty())
			return null;
		List<Integer> childWeights = new ArrayList<>();
		for (Program childProgram : node.children) {
			childWeights.add(weightSoFar(childProgram));
		}
		return childWeights;
	}

	private static int computeCorrectWeight(Program node) {
		int result = 0;
		List<Integer> childTotalWeights = weightTillChild(node);
		int diff = differentValue(childTotalWeights);
		if (diff != 0) {
			for (int i = 0; i < node.children.size(); i++) {
				Program childProgram = node.children.get(i);
				if (differentValue(weightTillChild(childProgram)) != 0) {
					return computeCorrectWeight(childProgram);
				}
				if (Math.abs(diff) == childTotalWeights.get(i)) {
					if (diff > 0) {
						result = childProgram.weight - Math.abs(
								childTotalWeights.get(i) - childTotalWeights.get((i + 1) % childTotalWeights.size()));
					} else {
						result = childProgram.weight + Math.abs(
								childTotalWeights.get(i) - childTotalWeights.get((i + 1) % childTotalWeights.size()));
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		BufferedReader bufReader;
		Map<String, Program> allPrograms = new HashMap<>();
		try {
			bufReader = new BufferedReader(new FileReader("./input/07"));
			String line = bufReader.readLine();

			while (line != null) {
				constructTreeFromLine(allPrograms, line);
				line = bufReader.readLine();
			}
			Program root = findBottomProgram(allPrograms);
			System.out.println("programs tree: " + root);
			System.out.println("correct weight: " + computeCorrectWeight(root));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
