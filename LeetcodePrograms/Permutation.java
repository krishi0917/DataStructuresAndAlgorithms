package LeetcodePrograms;//to avoid duplicates, use Set.
//if you want to arrange in ascending order, use TreeSet else Hashset is fine too
import java.util.TreeSet;

public class Permutation {
	public static TreeSet<String> getAllPermutations(String str) {
		TreeSet<String> permutations = new TreeSet<>();
		if (str == null || str.length() == 0) {
			permutations.add("");
			return permutations;
		}
		char first = str.charAt(0);
		String remainingString = str.substring(1);
		TreeSet<String> words = getAllPermutations(remainingString);
		System.out.println("permutation is " + words);
		for (String word : words) {
			for (int i = 0; i <= word.length(); i++) {
				String prefix = word.substring(0, i);
				String suffix = word.substring(i);
				permutations.add(prefix + first + suffix);
			}
		}
		return permutations;
	}

	public static void main(String[] args) {
		String str = "abc";
		TreeSet<String> permutations = getAllPermutations(str);
		System.out.println(permutations.toString());
	}
}