package LeetcodePrograms;

public class TowersOfHanoi {
	public static void tower(int n, char sourceRod, char destinationRod,char auxiliaryRod) {
		if (n == 0) {
			return;
		}
		int x = n - 1;
//		System.out.println("tower(" + x + ", " + sourceRod + ", "+ auxiliaryRod + ", " + destinationRod + ")");
		tower(n - 1, sourceRod, auxiliaryRod, destinationRod);
		System.out.println("Move the disk " + n + " from " + sourceRod + " to "+ destinationRod);
//		System.out.println("tower(" + x + ", " + auxiliaryRod + ", "+ destinationRod + ", " + sourceRod + ")");
		tower(n - 1, auxiliaryRod, destinationRod, sourceRod);
	}

	public static void main(String[] args) {
		System.out.println("tower(3, 'S', 'D', 'A')");
		tower(3, 'S', 'D', 'A');
	}

}
