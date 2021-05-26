package LeetcodePrograms;

public class FibonacciSeries {
    public static int fibonacciSeriesRecursive(int n) {
        if (n == 1 || n == 0) {
            return n;
        }
        return fibonacciSeriesRecursive(n - 1) + fibonacciSeriesRecursive(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(fibonacciSeriesRecursive(5));
    }
}