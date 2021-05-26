package LeetcodePrograms;

/**
 * @author Rishi Khurana
 */
public class test {
    //interesting way of solving
    // Cracking the coding interview question
    // Given a string, write a function to check if it is a permutation of a palindrome.
    public static void main(String []args){

        System.out.println(isPermutation("Ttact coa"));
    }

    // here T and t will go to the same location
    static boolean isPermutation(String phrase){
        int countOdd = 0;
        int []table = new int[Character.getNumericValue('z') - Character.getNumericValue('a')];
        for(char c : phrase.toCharArray()){
            int x = getCharNumber(c);
            if(x != -1){
                table[x]++;
                if(table[x]%2 == 1){
                    countOdd++;
                }else{
                    countOdd--;
                }
            }
        }
        return countOdd<=1;
    }

    private static int getCharNumber(Character c) {
        int val = Character.getNumericValue(c);
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        if(a <= val && val <=z){
            return val - a;
        }
        return -1;
    }
}
