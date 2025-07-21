package LeetcodePrograms.InterviewQuestions;
import java.util.*;
/*from abc import ABC


class BankingSystem(ABC):
    """
    BankingSystem interface.
    """

    def create_account(self, timestamp: int, account_id: str) -> bool:
        """
        Should create a new account with the given identifier if it
        doesn't already exist.
        Returns True if the account was successfully created or
        False if an account with account_id already exists.
        """
        # default implementation
        return False

    def deposit(self, timestamp: int, account_id: str, amount: int) -> int | None:
        """
        Should deposit the given amount of money to the specified
        account account_id.
        Returns the balance of the account after the operation has
        been processed.
        If the specified account doesn't exist, should return
        None.
        """
        # default implementation
        return None

    def transfer(self, timestamp: int, source_account_id: str, target_account_id: str, amount: int) -> int | None:
        """
        Should transfer the given amount of money from account
        source_account_id to account target_account_id.
        Returns the balance of source_account_id if the transfer
        was successful or None otherwise.
          * Returns None if source_account_id or
          target_account_id doesn't exist.
          * Returns None if source_account_id and
          target_account_id are the same.
          * Returns None if account source_account_id has
          insufficient funds to perform the transfer.
        """
        # default implementation
        return None

    def top_spenders(self, timestamp: int, n: int) -> list[str]:
        """
        Should return the identifiers of the top n accounts with
        the highest outgoing transactions - the total amount of
        money either transferred out of or paid/withdrawn (the
        *pay* operation will be introduced in level 3) - sorted in
        descending order, or in case of a tie, sorted alphabetically
        by account_id in ascending order.
        The result should be a list of strings in the following
        format: `["<account_id_1>(<total_outgoing_1>)", "<account_id
        _2>(<total_outgoing_2>)", ..., "<account_id_n>(<total_outgoi
        ng_n>)"]`.
          * If less than n accounts exist in the system, then return
          all their identifiers (in the described format).
          * Cashback (an operation that will be introduced in level 3)
          should not be reflected in the calculations for total
          outgoing transactions.
        """
        # default implementation
        return []

    def pay(self, timestamp: int, account_id: str, amount: int) -> str | None:
        """
        Should withdraw the given amount of money from the specified
        account.
        All withdraw transactions provide a 2% cashback - 2% of the
        withdrawn amount (rounded down to the nearest integer) will
        be refunded to the account 24 hours after the withdrawal.
        If the withdrawal is successful (i.e., the account holds
        sufficient funds to withdraw the given amount), returns a
        string with a unique identifier for the payment transaction
        in this format:
        "payment[ordinal number of withdraws from all accounts]" -
        e.g., "payment1", "payment2", etc.
        Additional conditions:
          * Returns None if account_id doesn't exist.
          * Returns None if account_id has insufficient funds to
          perform the payment.
          * *top_spenders* should now also account for the total
          amount of money withdrawn from accounts.
          * The waiting period for cashback is 24 hours, equal to
          24 * 60 * 60 * 1000 = 86400000 milliseconds (the unit for
          timestamps).
          So, cashback will be processed at timestamp
          timestamp + 86400000.
          * When it's time to process cashback for a withdrawal, the
          amount must be refunded to the account before any other
          transactions are performed at the relevant timestamp.
        """
        # default implementation
        return None

    def get_payment_status(self, timestamp: int, account_id: str, payment: str) -> str | None:
        """
        Should return the status of the payment transaction for the
        given payment.
        Specifically:
          * Returns None if account_id doesn't exist.
          * Returns None if the given payment doesn't exist for
          the specified account.
          * Returns None if the payment transaction was for an
          account with a different identifier from account_id.
          * Returns a string representing the payment status:
          "IN_PROGRESS" or "CASHBACK_RECEIVED".
        """
        # default implementation
        return None

 */
public class CoinbaseAssesment2 {
    public static void main(String[] args) throws InterruptedException {
        BankingSystemImpl bank = new BankingSystemImpl();

        long now = System.currentTimeMillis();

        // Create accounts
        System.out.println("Create A1: " + bank.createAccount(now, "A1"));  // true
        System.out.println("Create A2: " + bank.createAccount(now, "A2"));  // true
        System.out.println("Create A1 again: " + bank.createAccount(now, "A1"));  // false

        // Deposit
        System.out.println("Deposit 1000 into A1: " + bank.deposit(now, "A1", 1000));  // 1000

        // Transfer
        System.out.println("Transfer 300 from A1 to A2: " + bank.transfer(now, "A1", "A2", 300));  // 700

        // Pay (with cashback)
        String paymentId = bank.pay(now, "A1", 200);
        System.out.println("Payment from A1: " + paymentId);  // payment1
        System.out.println("Payment status: " + bank.getPaymentStatus(now, "A1", paymentId));  // IN_PROGRESS

        // Try cashback before 1 day
        System.out.println("Deposit after short time (no cashback yet): " + bank.deposit(now + 1000, "A1", 0));

        // Wait 1 day (simulate)
        long tomorrow = now + 86400000L + 1;
        System.out.println("Deposit after 1 day (cashback should be applied): " + bank.deposit(tomorrow, "A1", 0));

        // Check status again
        System.out.println("Payment status after 1 day: " + bank.getPaymentStatus(tomorrow, "A1", paymentId));  // CASHBACK_RECEIVED

        // Top spenders
        System.out.println("Top spender(s): " + bank.topSpenders(tomorrow, 2));  // [A1(500), A2(0)]
    }
}



class Account {
    String accountId;
    int balance;
    int totalOutgoing;
    List<Cashback> cashbackPending;
    Map<String, String> paymentStatus;

    public Account(String accountId) {
        this.accountId = accountId;
        this.balance = 0;
        this.totalOutgoing = 0;
        this.cashbackPending = new ArrayList<Cashback>();
        this.paymentStatus = new HashMap<String, String>();
    }
}

class Cashback {
    long timestamp;
    int amount;
    String paymentId;

    public Cashback(long timestamp, int amount, String paymentId) {
        this.timestamp = timestamp;
        this.amount = amount;
        this.paymentId = paymentId;
    }
}

class BankingSystemImpl {
    Map<String, Account> bankAccounts;
    int paymentCounter;

    public BankingSystemImpl() {
        this.bankAccounts = new HashMap<String, Account>();
        this.paymentCounter = 0;
    }

    public boolean createAccount(long timestamp, String accountId) {
        if (bankAccounts.containsKey(accountId)) {
            return false;
        }
        bankAccounts.put(accountId, new Account(accountId));
        return true;
    }

    private void processCashback(long timestamp, String accountId) {
        if (!bankAccounts.containsKey(accountId)) return;

        Account account = bankAccounts.get(accountId);
        Iterator<Cashback> it = account.cashbackPending.iterator();
        while (it.hasNext()) {
            Cashback cb = it.next();
            if (timestamp >= cb.timestamp) {
                account.balance += cb.amount;
                account.paymentStatus.put(cb.paymentId, "CASHBACK_RECEIVED");
                it.remove();
            }
        }
    }

    public Integer deposit(long timestamp, String accountId, int amount) {
        if (!bankAccounts.containsKey(accountId)) return null;
        processCashback(timestamp, accountId);
        Account account = bankAccounts.get(accountId);
        account.balance += amount;
        return account.balance;
    }

    public Integer transfer(long timestamp, String srcId, String tgtId, int amount) {
        if (!bankAccounts.containsKey(srcId) || !bankAccounts.containsKey(tgtId) || srcId.equals(tgtId)) {
            return null;
        }

        Account source = bankAccounts.get(srcId);
        if (source.balance < amount) return null;

        processCashback(timestamp, srcId);
        processCashback(timestamp, tgtId);

        Account target = bankAccounts.get(tgtId);
        source.balance -= amount;
        source.totalOutgoing += amount;
        target.balance += amount;

        return source.balance;
    }

    public List<String> topSpenders(long timestamp, int n) {
        PriorityQueue<Account> pq = new PriorityQueue<Account>(new Comparator<Account>() {
            public int compare(Account a1, Account a2) {
                return a2.totalOutgoing - a1.totalOutgoing;
            }
        });

        for (Account acc : bankAccounts.values()) {
            pq.add(acc);
        }

        List<String> result = new ArrayList<String>();
        for (int i = 0; i < n && !pq.isEmpty(); i++) {
            Account acc = pq.poll();
            result.add(acc.accountId + "(" + acc.totalOutgoing + ")");
        }
        return result;
    }

    public String pay(long timestamp, String accountId, int amount) {
        if (!bankAccounts.containsKey(accountId)) return null;
        processCashback(timestamp, accountId);

        Account account = bankAccounts.get(accountId);
        if (account.balance < amount) return null;

        account.balance -= amount;
        account.totalOutgoing += amount;

        long cashbackTs = timestamp + 86400000L;
        int cashbackAmount = (int)(0.02 * amount);
        paymentCounter++;

        String paymentId = "payment" + paymentCounter;
        account.cashbackPending.add(new Cashback(cashbackTs, cashbackAmount, paymentId));
        account.paymentStatus.put(paymentId, "IN_PROGRESS");

        return paymentId;
    }

    public String getPaymentStatus(long timestamp, String accountId, String paymentId) {
        if (!bankAccounts.containsKey(accountId)) return null;
        Account account = bankAccounts.get(accountId);
        if (!account.paymentStatus.containsKey(paymentId)) return null;
        return account.paymentStatus.get(paymentId);
    }
}

