package LeetcodePrograms;

import java.util.ArrayList;
import java.util.List;

public class ListIterator {
	ArrayList<ArrayList<Integer>> list;

	private int currentIndex = 0;
	private int currentList = 0;

	public ListIterator(ArrayList<ArrayList<Integer>> list) {
		this.list = list;
	}

	public boolean hasNext() {
		if(currentList < list.size())
			return true;
		else
			return false;
	}

	public int next() {
		if (hasNext()) {
			List<Integer> thisList = list.get(currentList);
			if (currentIndex == thisList.size() - 1) {
				int temp = currentIndex;
				currentIndex = 0;
				currentList++;
				return thisList.get(temp);
			} else if (currentIndex < thisList.size()) {
				return thisList.get(currentIndex++);
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> listOfLists = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> firstList = new ArrayList<Integer>();
		ArrayList<Integer> secondList = new ArrayList<Integer>();
		ArrayList<Integer> thirdList = new ArrayList<Integer>();
		firstList.add(2);
		firstList.add(3);
		firstList.add(4);
		secondList.add(5);
		secondList.add(6);
		secondList.add(7);
		secondList.add(4);
		secondList.add(17);
		secondList.add(27);
		secondList.add(1);
		secondList.add(27);
		thirdList.add(6);
		thirdList.add(9);
		thirdList.add(8);
		listOfLists.add(firstList);
		listOfLists.add(secondList);
		listOfLists.add(thirdList);

		ListIterator itr = new ListIterator(listOfLists);
		while (itr.hasNext()) {
			int x = (int) itr.next();
			System.out.println(x);
		}

	}

}


