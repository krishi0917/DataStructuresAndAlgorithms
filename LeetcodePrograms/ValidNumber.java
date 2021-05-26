package LeetcodePrograms;
//#LinkedIn WeWorkQuestion
/**
 * Created by rkhurana on 2/26/19.
 * facebook
 * 65. Valid Number

 * A valid number can be split up into these components (in order):
 *
 * A decimal number or an integer.
 * (Optional) An 'e' or 'E', followed by an integer.
 * A decimal number can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * One of the following formats:
 * At least one digit, followed by a dot '.'.
 * At least one digit, followed by a dot '.', followed by at least one digit.
 * A dot '.', followed by at least one digit.
 * An integer can be split up into these components (in order):
 *
 * (Optional) A sign character (either '+' or '-').
 * At least one digit.
 * For example, all the following are valid numbers: ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3",
 * "3e+7", "+6e-1", "53.5e93", "-123.456e789"], while the following are not valid numbers: ["abc", "1a", "1e", "e3",
 * "99e2.5", "--6", "-+3", "95a54e53"].
 *
 * Given a string s, return true if s is a valid number.
 */
public class ValidNumber {

    public boolean validateNumber2(String s) {
        boolean n = false;
        boolean e = false;
        boolean afe = true;
        boolean p = false;
        s=s.trim();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                n = true;
                afe = true;
            }
            else if(s.charAt(i)=='.' && p==false && e == false){
                p=true;
            }
            else if (s.charAt(i) == 'e' && e == false && n==true){
                e=true;
                afe = false;
            }
            else if(((s.charAt(i) == '+') || s.charAt(i) =='-') && (i==0 || s.charAt(i-1)=='e'))
                ;
            else
                return false;
        }
        return n && afe;
    }

    public boolean validateNumber(String s) {
        /**
         * isNumber(s)==true if and only if s=s1 or s1+'e'+s2, where s1, s2
         * are valid strings of a number without the char 'e', and s2 is an
         * integer.
         *
         * 'e' : valid_count=0~1; [boolean hasE]
         *
         * Valid chars in a string of a number without 'e':
         *
         * ' ' : valid_count=0~n; must appear at two ends
         *
         * '+/-' : valid_count=0~1; must be the first non-space valid char;
         * [boolean hasFirst]
         *
         * '.' : valid_count=0~1; cannot appear after 'e'; [boolean hasDot]
         *
         * '0~9' : valid_count=1~n; [boolean hasDigit]
         */

        s = s.trim();
        int n = s.length();
        if (n == 0)
            return false;

        boolean hasE, hasFirst, hasDot, hasDigit;
        hasE = hasFirst = hasDot = hasDigit = false;

        char c;
        for (int i = 0; i < n; i++) {
            c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                hasFirst = hasDigit = true;
                continue;
            }

            switch (c) {
			/*
			 * case ' ': continue;
			 */ // extend to accept any space everywhere
                case 'e':
                    // already has 'e' or no digit before 'e'
                    if (hasE || !hasDigit)
                        return false;
                    hasE = true;

                    // reset for the exponential number
                    hasFirst = hasDigit = false;
                    hasDot = true; // the exponent must be an integer, hence
                    // regard as if a dot exists already. Set
                    // hasDot = false extending to accept any
                    // (decimal) number as an exponent.
                    continue;
                case '+':
                case '-':
                    if (hasFirst)
                        return false;
                    hasFirst = true;
                    continue;
                case '.':
                    if (hasDot)
                        return false;
                    hasFirst = hasDot = true;
                    continue;
                default:
                    return false;
            }
        }

        return hasDigit;
    }

    public static void main(String []args){
        ValidNumber vn = new ValidNumber();
//        System.out.println("possible result \"0\" => true. Answer coming as " + vn.validateNumber("2+5"));
//        System.out.println("possible result \" 0.1 \" => true. Answer coming as " + vn.validateNumber(" 0.1"));
//        System.out.println("possible result \"abc\" => false. Answer coming as " + vn.validateNumber("abc"));
//        System.out.println("possible result \"1 a\" => false. Answer coming as " + vn.validateNumber("1 a"));
//        System.out.println("possible result \"2e10\" => true. Answer coming as " + vn.validateNumber("2e10"));
        System.out.println("possible result   \" -90e3   \" => true. Answer coming as " + vn.validateNumber(" -90e3   "));
//        System.out.println("possible result \" 1e\" => false . Answer coming as " + vn.validateNumber(" 1e"));
//        System.out.println("possible result \"e3\" => false . Answer coming as " + vn.validateNumber("e3"));
//        System.out.println("possible result  \" 6e-1\" => true. Answer coming as " + vn.validateNumber(" 6e-1"));
//        System.out.println("possible result \" 99e2.5 \" " + vn.validateNumber(" 99e2.5 "));
//        System.out.println("possible result   \"53.5e93\" => true" + vn.validateNumber("53.5e93"));
//        System.out.println("possible result \" --6 \" => false" + vn.validateNumber(" --6 "));
//        System.out.println("possible result \"-+3\" => false " + vn.validateNumber("-+3"));
//        System.out.println("possible result \"95a54e53\" => false" + vn.validateNumber("95a54e53"));
//        System.out.println("possible result \"+1e+1\" => true. Answer coming as " + vn.validateNumber("+1e+1"));
//        System.out.println("possible result \"1e1+1\" => false. Answer coming as " + vn.validateNumber(" 1e1+1"));
    }
}

