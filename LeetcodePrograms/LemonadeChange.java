package LeetcodePrograms;

public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int fives = 1, tens = 0;
        if (bills.length < 1)
            return true;
        if (bills[0] != 5)
            return false;
        for (int index = 1; index < bills.length; index++) {
            if (bills[index] == 5)
                fives++;
            else if (bills[index] == 10) {
                if (fives < 1)
                    return false;
                fives--;
                tens++;
            } else {
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if (fives > 2)
                    fives -= 3;
                else return false;
            }
        }
        return true;
    }

    public static void main(String []args){
        LemonadeChange lchange = new LemonadeChange();
        int [] bills = {5,5,5,10,20};
        boolean x = lchange.lemonadeChange(bills);
    }
}
