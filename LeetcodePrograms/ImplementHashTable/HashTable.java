package LeetcodePrograms.ImplementHashTable;

public class HashTable {
	private final static int TABLE_SIZE = 5;
	HashEntry[] table;

	HashTable() {
		table = new HashEntry[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			table[i] = null;
	}

	public int get(int key) {
		int hash = key % TABLE_SIZE;
		while (table[hash] != null && table[hash].getKey() != key)
			hash = (hash + 1) % TABLE_SIZE;
		if (table[hash] == null)
			return -1;

		else
			return table[hash].getValue();
	}

	public void put(int key, int value) {
		int hash = key % TABLE_SIZE;
		while (table[hash] != null && table[hash].getKey() != key)
			hash = (hash + 1) % TABLE_SIZE;
		table[hash] = new HashEntry(key, value);
	}

	public static void main(String[] args) {
		HashTable map1 = new HashTable();
		HashTable map2 = new HashTable();
		map1.put(3, 4);
		map1.put(5, 6);
		map2.put(3, 4);
		map2.put(5, 6);
	}
}
