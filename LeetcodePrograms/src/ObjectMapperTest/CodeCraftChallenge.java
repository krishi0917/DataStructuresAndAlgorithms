package LeetcodePrograms.src.ObjectMapperTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.*;
import org.junit.*;
public class CodeCraftChallenge {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final int MAX_RETRIES = 3;
    private static final ExecutorService executor = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        try {
            CombinedResponse response = fetchAllServices();

            System.out.println("Final Combined Response:");
            System.out.println("User: " + response.user);
            System.out.println("Order: " + response.order);
            System.out.println("Payment: " + response.payment);


            Assert.assertNotNull("User should not be null", response.user);
            Assert.assertNotNull("User name should not be null", response.user.getName());
            Assert.assertFalse("User name should not be empty", response.user.getName().isEmpty());
            Assert.assertEquals("User ID should be 1", 1, response.user.getId());

            Assert.assertNotNull("Order should not be null", response.order);
            Assert.assertNotNull("Order items should not be null", response.order.getItems());
            Assert.assertFalse("Order items should not be empty", response.order.getItems().isEmpty());

            Assert.assertNotNull("Payment should not be null", response.payment);
            Assert.assertEquals("Payment status should be success", "success", response.payment.getStatus());


            System.out.println("✅ All assertions passed. System is valid.");

        } catch (AssertionError ae) {
            System.err.println("❌ Assertion failed: " + ae.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Failed to process services: " + e.getMessage());
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    public static CombinedResponse fetchAllServices() throws Exception {
        Future<User> userFuture = executor.submit(new Callable<User>() {
            public User call() throws Exception {
                return getUser();
            }
        });

        Future<Order> orderFuture = executor.submit(new Callable<Order>() {
            public Order call() throws Exception {
                return getOrder();
            }
        });

        Future<Payment> paymentFuture = executor.submit(new Callable<Payment>() {
            public Payment call() throws Exception {
                return getPaymentWithRetry();
            }
        });

        User user = userFuture.get();
        Order order = orderFuture.get();
        Payment payment = paymentFuture.get();

        return new CombinedResponse(user, order, payment);
    }

    public static User getUser() throws IOException {
        String response = userService();
        User user = objectMapper.readValue(response, User.class);
        if (user.getName() == null || user.getName().isEmpty()) {
            throw new AssertionError("User must have a name");
        }
        return user;
    }

    public static Order getOrder() throws IOException {
        String response = orderService();
        Order order = objectMapper.readValue(response, Order.class);
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new AssertionError("Order must have items");
        }
        return order;
    }

    public static Payment getPaymentWithRetry() throws Exception {
        int attempts = 0;
        while (true) {
            try {
                String response = paymentService();
                Payment payment = objectMapper.readValue(response, Payment.class);
                if (!"success".equals(payment.getStatus())) {
                    throw new AssertionError("Payment must be successful");
                }
                return payment;
            } catch (Exception e) {
                attempts++;
                if (attempts >= MAX_RETRIES) {
                    throw new Exception("Payment failed after retries", e);
                }
                long backoff = (long) Math.pow(2, attempts) * 100L;
                System.err.println("Retrying payment in " + backoff + "ms: " + e.getMessage());
                Thread.sleep(backoff);
            }
        }
    }

    // Simulated Services
    public static String userService() {
        return "{ \"id\": 1, \"name\": \"Alice\" }";
    }

    public static String orderService() {
        return "{ \"orderId\": 101, \"items\": [\"Book\", \"Pen\"] }";
    }

    public static String paymentService() {
        if (Math.random() < 0.7) {
            throw new RuntimeException("Payment service failed");
        }
        return "{ \"paymentId\": \"pay_999\", \"status\": \"success\" }";
    }

    // === POJO Classes ===

    public static class User {
        private int id;
        private String name;

        public User() {} // required for Jackson

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "'}";
        }
    }

    public static class Order {
        private int orderId;
        private List<String> items;

        public Order() {}

        public int getOrderId() { return orderId; }
        public void setOrderId(int orderId) { this.orderId = orderId; }

        public List<String> getItems() { return items; }
        public void setItems(List<String> items) { this.items = items; }

        @Override
        public String toString() {
            return "Order{orderId=" + orderId + ", items=" + items + "}";
        }
    }

    public static class Payment {
        private String paymentId;
        private String status;

        public Payment() {}

        public String getPaymentId() { return paymentId; }
        public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        @Override
        public String toString() {
            return "Payment{paymentId='" + paymentId + "', status='" + status + "'}";
        }
    }

    public static class CombinedResponse {
        public User user;
        public Order order;
        public Payment payment;

        public CombinedResponse(User user, Order order, Payment payment) {
            this.user = user;
            this.order = order;
            this.payment = payment;
        }
    }
}
