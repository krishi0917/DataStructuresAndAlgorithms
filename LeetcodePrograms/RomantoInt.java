package LeetcodePrograms;

import java.util.*;

public class RomantoInt {
	 public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        char[] chars = s.toCharArray();
        int result = 0;
        int i = 0, j = 1;
        for(; j < chars.length; i++, j++) {
            if (map.get(chars[i]) >= map.get(chars[j])) {
                result += map.get(chars[i]);
            } else {
                result -= map.get(chars[i]);
            }
        }
        result += map.get(chars[i]);
        return result;
        
    }
    public static int romantoInt2(String str) {
        int sum = 0;
//	The complexity of Java's implementation of indexOf is O(m*n) where n and m are the length of the search string and pattern respectively.
        if (str.indexOf("IV") != -1) {
            sum -= 2;
        }
        if (str.indexOf("IX") != -1) {
            sum -= 2;
        }
        if (str.indexOf("XL") != -1) {
            sum -= 20;
        }
        if (str.indexOf("XC") != -1) {
            sum -= 20;
        }
        if (str.indexOf("CD") != -1) {
            sum -= 200;
        }
        if (str.indexOf("CM") != -1) {
            sum -= 200;
        }
        int count = 0;
        char c[] = str.toCharArray();
        for (; count <= str.length() - 1; count++) {
            if (c[count] == 'M')
                sum += 1000;
            if (c[count] == 'D')
                sum += 500;
            if (c[count] == 'C')
                sum += 100;
            if (c[count] == 'L')
                sum += 50;
            if (c[count] == 'X')
                sum += 10;
            if (c[count] == 'V')
                sum += 5;
            if (c[count] == 'I')
                sum += 1;
        }
        return sum;
    }

    // Q12 Integer to Roman
    public static String intToRoman(int num) {
        int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X","IX", "V", "IV", "I" };
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }
	public static void main(String[] args) {

        //System.out.println(romanToInt("IVIV"));
        System.out.println(romanToInt("VI"));
	}

}
