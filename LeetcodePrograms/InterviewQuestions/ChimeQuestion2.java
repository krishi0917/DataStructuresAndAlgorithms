package LeetcodePrograms.InterviewQuestions;
import java.util.*;
/*
Imagine that you're writing software for a credit card provider. Your task is to implement a program that will add new credit card accounts, process charges and credits against them, and display summary information.

You are given a list of commands:

Add <card_holder> <card_number> $<limit>: Add command will create a new credit card for the given card_holder, card_number, and limit. It is guaranteed that the given card_holder didn't have a credit card before this operation.
New cards start with a $0 balance.
Cards numbers should be validated using basic validation.
(Bonus) Card numbers should be validated using the Luhn 10 algorithm.
Charge <card_holder> $<amount>: Charge command will increase the balance of the card associated with the provided name by the amount specified.
Charges that would raise the balance over the limit are ignored as if they were declined.
Charges against invalid cards are ignored.
Credit <card_holder> $<amount>: Credit command will decrease the balance of the card associated with the provided name by the amount specified.
Credits that would drop the balance below $0 will create a negative balance.
Credits against invalid cards are ignored.
Credit Card validation
In order to ensure the credit card number is valid, we want to run some very basic validation.
You need to ensure the string is only composed of digits [0-9] and is between 12 and 16 characters long (although most cards are 15 to 16, let's keep it simple).

(Bonus) How the Luhn algorithm works:

Starting with the rightmost digit, which is the check digit, and moving left, double the value of every second digit. If the result of this doubling operation is greater than 9 (e.g., 8 * 2 = 16), then add the digits of the product (e.g., 16: 1 + 6 = 7, 18: 1 + 8 = 9).
Take the sum of all the digits.
If the total modulo 10 is equal to 0 (if the total ends in zero) then the number is valid according to the Luhn algorithm, otherwise it is not valid.
The last Unit Test will be testing for the Luhn algorithm.


Luhn(number) = 7 + 9 + 9 + 4 + 7 + 6 + 9 + 7 + 7 = 65 = 5 (mod 10) != 0

Your Challenge

Return the card holder names with the balance of the card associated with the provided name. The names in output should be displayed in lexicographical order.
Display "error" instead of the balance if the credit card number does not pass validation.

Example

For

operations = [["Add", "Tom", "4111111111111111", "$1000"],
              ["Add", "Lisa", "5454545454545454", "$3000"],
              ["Add", "Quincy", "12345678901234", "$2000"],
              ["Charge", "Tom", "$500"],
              ["Charge", "Tom", "$800"],
              ["Charge", "Lisa", "$7"],
              ["Credit", "Lisa", "$100"],
              ["Credit", "Quincy", "$200"]]
the output should be

creditCardProvider(operations) = [["Lisa", "$-93"],
                                  ["Quincy", "error"],
                                  ["Tom", "$500"]]
Input/Output

[execution time limit] 3 seconds (java)

[input] array.array.string operations

An array of operations. It is guaranteed that card limits and amounts of each operation are in the range [1, 3000]. It is also guaranteed that each card holder name will contain no more than 10 symbols and each card number will contain from 12 to 16 digits.

Guaranteed constraints:
1 ≤ operations.length ≤ 10,
3 ≤ operations[i].length ≤ 4.

[output] array.array.string

Array of card holders and their card balances.
 */
public class ChimeQuestion2 {
    String[][] solution(String[][] operations) {
        Map<String, Integer> creditBalanceMap = new TreeMap<>(); // R
        Map<String, String> cardNumberMap = new HashMap<>(); // card number
        Map<String, Integer> cardLimitMap = new HashMap<>();
        for (String[] operation : operations) {
            String name = operation[1];
            if (operation.length == 4) {
                String cardNumber = operation[2];
                String cardLimit = operation[3].substring(1);
                boolean isCardValid = false;
                if (cardNumber.length() >= 12 && cardNumber.length() <= 16) {
                    isCardValid = true;
                }
                char[] cardNumberArr = cardNumber.toCharArray();
                for (int i = 0; i < cardNumberArr.length; i++) {
                    if (!Character.isDigit(cardNumberArr[i])) {
                        isCardValid = false;
                        break;
                    }
                }
                cardNumberMap.put(name, cardNumber + "_" + isCardValid);
                creditBalanceMap.put(name, 0); //0
                cardLimitMap.put(name, Integer.parseInt(cardLimit)); //10000
            } else if (operation.length == 3) {

                String typeTransaction = operation[0];
                int amount = Integer.parseInt(operation[2].substring(1));
                String isCardvalid = cardNumberMap.get(name).split("_")[1];
                if (isCardvalid.equals("false")) {
                    creditBalanceMap.put(name, Integer.MIN_VALUE);
                    continue;
                }
                if (typeTransaction.equals("Credit")) {
                    creditBalanceMap.put(name, creditBalanceMap.get(name) - amount);
                } else { //charge

                    if (cardLimitMap.get(name) - creditBalanceMap.get(name) > amount) {
                        creditBalanceMap.put(name, creditBalanceMap.get(name) + amount);
                    }
                }
            }
        }

        String[][] result = new String[creditBalanceMap.size()][2];
        int i = 0;
        for (Map.Entry<String, Integer> entry : creditBalanceMap.entrySet()) {
            result[i][0] = entry.getKey();
            if (entry.getValue() == Integer.MIN_VALUE) {
                result[i][1] = "error";
            } else {
                result[i][1] = "$" + (entry.getValue());
            }
            i++;
        }
        return result;
    }
}
