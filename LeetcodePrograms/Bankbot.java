package LeetcodePrograms;
You've been asked to program a bot for a popular bank that will automate the management of incoming requests. There are three types of requests the bank can receive:

        transfer i j sum: request to transfer sum amount of money from the ith account to the jth one (i and j are 1-based);
        deposit i sum: request to deposit sum amount of money in the ith account (1-based);
        withdraw i sum: request to withdraw sum amount of money from the ith account (1-based).
        Your bot should also be able to process invalid requests. There are two types of invalid requests:

        invalid account number in the requests;
        withdrawal / transfer of a larger amount of money than is currently available.
        For the given list of balances and requests, return the state of balances after all requests have been processed, or an array of a single element [-<request_id>] (please note the minus sign), where <request_id> is the 1-based index of the first invalid request.

        Example

        For balances = [10, 100, 20, 50, 30] and
        requests = ["withdraw 2 10", "transfer 5 1 20", "deposit 5 20", "transfer 3 4 15"],
        the output should be solution(balances, requests) = [30, 90, 5, 65, 30].

        Here are the states of balances after each request:

        "withdraw 2 10": [10, 90, 20, 50, 30];
        "transfer 5 1 20": [30, 90, 20, 50, 10];
        "deposit 5 20": [30, 90, 20, 50, 30];
        "transfer 3 4 15": [30, 90, 5, 65, 30], which is the answer.
        For balances = [20, 1000, 500, 40, 90] and
        requests = ["deposit 3 400", "transfer 1 2 30", "withdraw 4 50"],
        the output should be solution(balances, requests) = [-2].

        After the first request, balances becomes equal to [20, 1000, 900, 40, 90], but the second one turns it into [-10, 1030, 900, 40, 90], which is invalid. Thus, the second request is invalid, and the answer is [-2]. Note that the last request is also invalid, but it shouldn't be included in the answer.

        Input/Output

        [execution time limit] 3 seconds (java)

        [memory limit] 1 GB

        [input] array.integer balances

        Array of integers, where balances[i] is the amount of money in the (i + 1)th account.

        Guaranteed constraints:
        0 < balances.length ≤ 100,
        0 ≤ balances[i] ≤ 105.

        [input] array.string requests

        Array of requests in the order they should be processed. Each request is guaranteed to be in the format described above.
        It is guaranteed that for each i and j in a request 0 < i, j ≤ 1000.

        Guaranteed constraints:
        0 < requests.length ≤ 100,
        for each i, j and sum in each element of request:

        0 < i ≤ 1000,
        0 < j ≤ 1000,
        0 ≤ sum ≤ 105.
        [output] array.integer

        The balances after executing all of the requests or array with a single integer - the index of the first invalid request preceded by -.

        [Java] Syntax Tips

// Prints help message to the console
// Returns a string
//
// Globals declared here will cause a compilation error,
// declare variables inside the function instead!
        String helloWorld(String name) {
        System.out.println("This prints to the console when you Run Tests");
        return "Hello, " + name;
        }

        Java
        v17.0.3
        12345678910111213141516171819202122232425262728293031323334353637
        int[] solution(int[] remAmount, String[] transactionToPerform) {
        for (int a = 0; a < transactionToPerform.length; a++) {
        String[] segments = transactionToPerform[a].split(" ");
        String command = segments[0];
        int innitialAcc = Integer.parseInt(segments[1]) - 1;
        int finalAcc = command.equals("transfer") ? Integer.parseInt(segments[2]) - 1 : -1;

        int money = Integer.parseInt(segments[segments.length - 1]);


        TESTS
        CUSTOM TESTS

public class Bankbot {
    int[] solution(int[] remAmount, String[] transactionToPerform) {
        for (int a = 0; a < transactionToPerform.length; a++) {
            String[] segments = transactionToPerform[a].split(" ");
            String command = segments[0];
            int innitialAcc = Integer.parseInt(segments[1]) - 1;
            int finalAcc = command.equals("transfer") ? Integer.parseInt(segments[2]) - 1 : -1;

            int money = Integer.parseInt(segments[segments.length - 1]);

            if (!checkValidityAccount(innitialAcc, remAmount.length) || (finalAcc != -1 && !checkValidityAccount(finalAcc, remAmount.length))) {
                return new int[]{-a - 1};
            }

            if (command.equals("withdraw")) {
                if (remAmount[innitialAcc] < money) {
                    return new int[]{-a - 1};
                }
                remAmount[innitialAcc] -= money;
            } else if (command.equals("deposit")) {
                remAmount[innitialAcc] += money;
            } else if (command.equals("transfer")) {
                if (remAmount[innitialAcc] < money) {
                    return new int[]{-a - 1};
                }
                remAmount[innitialAcc] -= money;
                remAmount[finalAcc] += money;
            }
        }
        return remAmount;
    }


    public boolean checkValidityAccount(int balanceMoney, int sumAcc) {
        return balanceMoney >= 0 && balanceMoney < sumAcc;
    }






}
