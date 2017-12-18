package day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnotHash {
	private static final int LIST_SIZE = 256;

	private static int rotateList(List<Integer> list, List<Integer> lengths, int round, int currentIndex) {
		for (int i = 0; i < lengths.size(); i++) {
			int lastIndex = currentIndex + lengths.get(i);
			List<Integer> sublist;
			if (lastIndex < LIST_SIZE) {
				sublist = list.subList(currentIndex, lastIndex);
				Collections.reverse(sublist);
			} else {
				lastIndex %= LIST_SIZE;
				sublist = new ArrayList<Integer>();
				sublist.addAll(list.subList(currentIndex, LIST_SIZE));
				sublist.addAll(list.subList(0, lastIndex));
				Collections.reverse(sublist);
				int sublistIndex = 0;
				for (int j = currentIndex; j < LIST_SIZE; j++) {
					list.remove(j);
					list.add(j, sublist.get(sublistIndex++));
				}
				for (int j = 0; j < lastIndex; j++) {
					list.remove(j);
					list.add(j, sublist.get(sublistIndex++));
				}
			}
			currentIndex += lengths.get(i) + (round * lengths.size()) + i;
			currentIndex %= LIST_SIZE;
		}
		return currentIndex;
	}

	private static List<Integer> computeDenseHash(List<Integer> sparseHash) {
		List<Integer> denseHash = new ArrayList<>();

		int totalIndex = 0;
		while (totalIndex < sparseHash.size()) {
			int newVal = sparseHash.get(totalIndex);
			for (int i = 1; i < 16; i++) {
				newVal ^= sparseHash.get(i + totalIndex);
			}
			totalIndex += 16;
			denseHash.add(newVal);
		}
		return denseHash;
	}

	public static void main(String[] args) throws IOException {
		List<Integer> list = new ArrayList<>(LIST_SIZE);

		for (int i = 0; i < LIST_SIZE; i++)
			list.add(i);

		List<Integer> lengths = new ArrayList<>();
		BufferedReader bufReader = new BufferedReader(new FileReader("./input/10"));
		for (Character c : bufReader.readLine().toCharArray())
			lengths.add((int) c);
		lengths.add(17);
		lengths.add(31);
		lengths.add(73);
		lengths.add(47);
		lengths.add(23);

		System.out.println(lengths);

		int nextIndex = 0;
		for (int i = 0; i < 64; i++) {
			nextIndex = rotateList(list, lengths, i, nextIndex);
		}

		List<Integer> denseHash = computeDenseHash(list);
		System.out.println("denseHash: " + denseHash);

		StringBuilder hexCode = new StringBuilder();
		for (int number : denseHash) {
			String hexString = Integer.toHexString(number);
			if (hexString.length() == 1)
				hexCode.append("0");
			hexCode.append(hexString);
		}
		System.out.println("hexCode: " + hexCode);
	}
}
