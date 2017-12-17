package day08;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntBiFunction;

public class Registers {
	private static Map<String, ToIntBiFunction<Integer, Integer>> commands = new HashMap<>();
	static {
		commands.put("inc", (x, y) -> x + y);
		commands.put("dec", (x, y) -> x - y);
	}

	private static void executeOperation(Map<String, Integer> registers, String register, String operation,
			int modifier) {
		int value = registers.get(register);

		registers.put(register, commands.get(operation).applyAsInt(value, modifier));
	}

	private static boolean conditionsTrue(Map<String, Integer> registers, String conditionReg, String conditionOp,
			int conditionLimit) {

		if (conditionOp.equals("==") && registers.get(conditionReg) == conditionLimit)
			return true;
		if (conditionOp.equals("<=") && registers.get(conditionReg) <= conditionLimit)
			return true;
		if (conditionOp.equals(">=") && registers.get(conditionReg) >= conditionLimit)
			return true;
		if (conditionOp.equals("!=") && registers.get(conditionReg) != conditionLimit)
			return true;
		if (conditionOp.equals("<") && registers.get(conditionReg) < conditionLimit)
			return true;
		if (conditionOp.equals(">") && registers.get(conditionReg) > conditionLimit)
			return true;
		return false;
		// Does not compute the correct answer.
		// switch (conditionOp) {
		// case "==":
		// if (registers.get(conditionReg) == conditionLimit)
		// return true;
		// case "<=":
		// if (registers.get(conditionReg) <= conditionLimit)
		// return true;
		// case ">=":
		// if (registers.get(conditionReg) >= conditionLimit)
		// return true;
		// case "!=":
		// if (registers.get(conditionReg) != conditionLimit)
		// return true;
		// case "<":
		// if (registers.get(conditionReg) < conditionLimit)
		// return true;
		// case ">":
		// if (registers.get(conditionReg) > conditionLimit)
		// return true;
		// default:
		// return false;
		// }
	}

	private static int findMaxRegister(Map<String, Integer> registers) {
		int max = Integer.MIN_VALUE;
		for (String regName : registers.keySet()) {
			if (registers.get(regName) > max) {
				max = registers.get(regName);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		BufferedReader bufReader;
		Map<String, Integer> registers = new HashMap<>();

		int max = Integer.MIN_VALUE;
		try {
			bufReader = new BufferedReader(new FileReader("./input/08"));
			String line = bufReader.readLine();

			while (line != null) {
				String[] parts = line.split(" ");
				registers.putIfAbsent(parts[0], 0);
				registers.putIfAbsent(parts[4], 0);
				if (conditionsTrue(registers, parts[4], parts[5], Integer.parseInt(parts[6])))
					executeOperation(registers, parts[0], parts[1], Integer.parseInt(parts[2]));
				if (findMaxRegister(registers) > max)
					max = findMaxRegister(registers);
				line = bufReader.readLine();
			}
			System.out.println("registers: " + registers);
			System.out.println("max register: " + findMaxRegister(registers));
			System.out.println("all time max register: " + max);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
