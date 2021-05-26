package LeetcodePrograms;

import java.util.ArrayList;

public class HeapInsertion {
	private void shiftUp(ArrayList<Integer> items) {
		int k = items.size() - 1;
		while (k > 0) {
			int p = (k - 1) / 2;
			int item = items.get(k);
			int parent = items.get(p);

			// swap
			if (item > parent) {
				items.set(k, parent);
				items.set(p, item);

				// move up 1 level
				k = p;
			} else {
				break;
			}
		}
	}

	private void ShiftDown(ArrayList<Integer> items) {
		int k = 0;
		int l = 2 * k + 1;
		while (l < items.size()) {
			int max = l;
			int r = l + 1;
			if (r < items.size()) // there is a right child
			{
				if (items.get(k) > items.get(l)) {
					max++;
				}
			}
			if (items.get(k) < items.get(l)) { // switch
				int temp = items.get(k);
				items.set(k, items.get(max));
				items.set(max, temp);
				k = max;
				l = 2 * k + 1;
			} else {
				break;
			}
		}

	}

	public static void main(String[] args) {

	}

}
