package LeetcodePrograms.InterviewQuestions.Coinbase;
import java.util.*;
interface Filter {
    boolean matches(Transaction txn); // Returns true if transaction passes the filter
}
class Transaction { // Represents a transaction with fields we may filter or paginate on
    int time, id, userId, currency, amount;
    // Constructor to initialize transaction fields
    public Transaction(int time, int id, int userId, int currency, int amount) {
        this.time = time; this.id = id; this.userId = userId; this.currency = currency; this.amount = amount; }

    public int get(String field) { // Generic getter using field name string, supports dynamic filtering/sorting
        if ("time".equals(field)) return time; if ("id".equals(field)) return id; if ("userId".equals(field)) return userId; if ("currency".equals(field)) return currency; if ("amount".equals(field)) return amount;
        throw new IllegalArgumentException("Invalid field: " + field); }

    @Override // For readable output of a transaction
    public String toString() {  return "Transaction{id=" + id + ", time=" + time + ", userId=" + userId + ", currency=" + currency + ", amount=" + amount + "}";}
}

// Implements basic comparison filter like =, >, <, etc.
class ComparisonFilter implements Filter {
    String field;
    String op;
    int value;

    // Constructor to set field, operator and target value
    public ComparisonFilter(String field, String op, int value) {
        this.field = field;
        this.op = op;
        this.value = value;
    }

    public boolean matches(Transaction txn) {
        int actual = txn.get(field); // Get the actual field value from the transaction
        // Apply the comparison operator
        if ("=".equals(op)) return actual == value;
        if (">".equals(op)) return actual > value;
        if ("<".equals(op)) return actual < value;
        if (">=".equals(op)) return actual >= value;
        if ("<=".equals(op)) return actual <= value;
        if ("!=".equals(op)) return actual != value;
        throw new IllegalArgumentException("Invalid operator: " + op);
    }
}

// Combines multiple filters with logical AND
class AndFilter implements Filter {
    List<Filter> filters; // List of filters to combine

    public AndFilter(List<Filter> filters) {
        this.filters = filters;
    }

    public boolean matches(Transaction txn) {
        // All filters must pass
        for (Filter f : filters) {
            if (!f.matches(txn)) return false;
        }
        return true;
    }
}

// Combines multiple filters with logical OR
class OrFilter implements Filter {
    List<Filter> filters;

    public OrFilter(List<Filter> filters) {
        this.filters = filters;
    }

    public boolean matches(Transaction txn) {
        // At least one filter must pass
        for (Filter f : filters) {
            if (f.matches(txn)) return true;
        }
        return false;
    }
}
class TransactionSearchEngine { // Engine to support filtering and cursor-based paginationhah
    // Applies a filter and returns matching transactions
    public List<Transaction> filter(List<Transaction> txns, Filter filter) {
        List<Transaction> result = new ArrayList<Transaction>();
        for (Transaction txn : txns) {
            if (filter.matches(txn)) {
                result.add(txn);
            }
        }
        return result;
    }

    // Applies filter + cursor + limit to return a paginated result
    public List<Transaction> paginate(List<Transaction> txns, Filter filter, final String sortField, int cursor, int limit) {
        List<Transaction> result = new ArrayList<Transaction>();// Step 1: Apply filter and cursor
        for (Transaction txn : txns) {
            // Only include if it passes the filter and is after the cursor
            if (filter.matches(txn) && txn.get(sortField) > cursor) {
                result.add(txn);
            }
        }

        // Step 2: Sort the filtered results by the given sortField
        Collections.sort(result, new Comparator<Transaction>() {
            public int compare(Transaction a, Transaction b) {
                return Integer.compare(a.get(sortField), b.get(sortField));
            }
        });

        // Step 3: Return only the first N results as per the limit
        List<Transaction> page = new ArrayList<Transaction>();
        for (int i = 0; i < Math.min(limit, result.size()); i++) {
            page.add(result.get(i));
        }

        return page;
    }
}

public class PaginationAndFiltering {
    public static void main(String[] args) {
        // Sample dataset of transactions
        List<Transaction> txns = Arrays.asList(
                new Transaction(1, 11, 1, 1, 10),
                new Transaction(2, 12, 1, 3, 11),
                new Transaction(3, 13, 2, 1, -10),
                new Transaction(11, 22, 1, 2, -3),
                new Transaction(11, 23, 1, 1, 5),
                new Transaction(12, 24, 1, 2, 6),
                new Transaction(13, 25, 1, 1, 10)
        );

        // Define composite filter: (userId == 1 OR userId == 2) AND amount > 0
        Filter filter = new AndFilter(Arrays.asList(
                new OrFilter(Arrays.asList(
                        new ComparisonFilter("userId", "=", 1),
                        new ComparisonFilter("userId", "=", 2)
                )),
                new ComparisonFilter("amount", ">", 0)
        ));

        TransactionSearchEngine engine = new TransactionSearchEngine();
        List<Transaction> transactions = engine.filter(txns, filter);

        // First page: start from cursor = 0, limit = 2
        int cursor = 0;
        int limit = 2;
        List<Transaction> page1 = engine.paginate(txns, filter, "time", cursor, limit);
        System.out.println("Page 1:");
        for (Transaction t : page1) {
            System.out.println(t);
        }

        // Prepare for next page by taking last time from previous page
        int nextCursor = page1.get(page1.size() - 1).get("time");

        // Second page: start from new cursor
        List<Transaction> page2 = engine.paginate(txns, filter, "time", nextCursor, limit);
        System.out.println("\nPage 2:");
        for (Transaction t : page2) {
            System.out.println(t);
        }
    }
}
