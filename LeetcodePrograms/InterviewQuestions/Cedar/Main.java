package LeetcodePrograms.InterviewQuestions.Cedar;
import java.time.LocalDateTime;
import java.util.*;
enum Channel { EMAIL, TEXT, PHONE }

class Bill { int patientId; LocalDateTime billGeneratedAt;
    Bill(int patientId, LocalDateTime billGeneratedAt) { this.patientId = patientId; this.billGeneratedAt = billGeneratedAt; } }

class Communication {  int patientId; Channel channel; LocalDateTime communicationSent;
    Communication(int patientId, Channel channel, LocalDateTime communicationSent) { this.patientId = patientId; this.channel = channel; this.communicationSent = communicationSent; } }

class Payment { int patientId; double amount; LocalDateTime paymentMadeAt;
    Payment(int patientId, double amount, LocalDateTime paymentMadeAt) { this.patientId = patientId; this.amount = amount; this.paymentMadeAt = paymentMadeAt; } }

class CommunicationAnalyzer { private final List<Bill> bills; private final List<Communication> communications; private final List<Payment> payments;
    public CommunicationAnalyzer(List<Bill> bills, List<Communication> communications, List<Payment> payments) { this.bills = bills; this.communications = communications; this.payments = payments; }

    public String getBestChannel() {
        if (bills.isEmpty() || communications.isEmpty() || payments.isEmpty()) {
            return null;
        }
        // Step 2: Create maps for fast lookup by patientId
        Map<Integer, Bill> billsByPatient = new HashMap<>();
        for (Bill bill : bills) {
            billsByPatient.put(bill.patientId, bill);
        }
        Map<Integer, Communication> commByPatient = new HashMap<>();
        for (Communication comm : communications) {
            commByPatient.put(comm.patientId, comm);
        }
/*Map<Integer, List<Communication>> commByPatient = new HashMap<>();
for (Communication comm : communications) {
    int patientId = comm.patientId;
    if (!commByPatient.containsKey(patientId)) {
        commByPatient.put(patientId, new ArrayList<Communication>());
    }
    commByPatient.get(patientId).add(comm);
}    */

        // Payments mapped by patient for quick lookup
        Map<Integer, Payment> paymentsByPatient = new HashMap<>();
        for (Payment payment : payments) {
            paymentsByPatient.put(payment.patientId, payment);
        }

        // Step 3: Count how many patients were contacted via each channel
        Map<Channel, Integer> totalByChannel = new HashMap<>();
        Map<Channel, Integer> paidByChannel = new HashMap<>();

        // Step 4: Iterate over patients who have both a bill and a communication
        for (int patientId : billsByPatient.keySet()) {
            if (commByPatient.containsKey(patientId)) {
                Channel channel = commByPatient.get(patientId).channel;
                // Count that this channel was used
                totalByChannel.put(channel, totalByChannel.getOrDefault(channel, 0) + 1);

                // If this patient also paid, increase paid count for the channel
                if (paymentsByPatient.containsKey(patientId)) {
                    paidByChannel.put(channel, paidByChannel.getOrDefault(channel, 0) + 1);
                }
            }
        }
/*
for (int patientId : billsByPatient.keySet()) {            // ✅ (Existing)
            if (!commByPatient.containsKey(patientId)) continue;   // ✅ (Existing)

            List<Communication> comms = (List<Communication>) commByPatient.get(patientId); // 🆕 (New)

            Set<Channel> uniqueChannels = new HashSet<>();         // 🆕 (New)

            for (Communication comm : comms) {                     // 🆕 (New)
                uniqueChannels.add(comm.channel);                  // 🆕 (New)
            }

            for (Channel channel : uniqueChannels) {               // 🆕 (New)
                totalByChannel.put(channel, totalByChannel.getOrDefault(channel, 0) + 1); // ✅ (Existing, moved inside loop)

                if (paymentsByPatient.containsKey(patientId)) {    // ✅ (Existing, moved inside loop)
                    paidByChannel.put(channel, paidByChannel.getOrDefault(channel, 0) + 1); // ✅ (Existing)
                }
            }
        }
*/
        /*
        PriorityQueue<Map.Entry<Channel, Double>> heap = new PriorityQueue<>(
                (a, b) -> Double.compare(b.getValue(), a.getValue())
        );
        */
        Channel bestChannel = null;
        double maxConversionRate = -1.0; // Start with a very low value
        // Step 5: Calculate conversion rate per channel and find the best one
        for (Channel channel : Channel.values()) {
            int total = totalByChannel.getOrDefault(channel, 0); // Total patients contacted via this channel
            int paid = paidByChannel.getOrDefault(channel, 0); // How many of them paid
            double conversionRate = total == 0 ? 0.0 : (double) paid / total;
            // heap.offer(new AbstractMap.SimpleEntry<>(channel, conversionRate));
            // heap.offer(new ChannelStat(channel, conversionRate));
            // Keep track of the channel with the highest conversion rate
            if (conversionRate > maxConversionRate) {
                maxConversionRate = conversionRate;
                bestChannel = channel;
            }
        }

        // return heap.isEmpty() ? null : heap.peek().getKey().name();
        // Step 6: Return the best channel (or null if none found)
        return (bestChannel == null) ? null : bestChannel.name();

    }
}
// for heap solution
class ChannelStat {
    Channel channel;
    double conversionRate;

    public ChannelStat(Channel channel, double conversionRate) {
        this.channel = channel;
        this.conversionRate = conversionRate;
    }
}
public class Main {
    public static void main(String[] args) {
        List<Bill> bills = Arrays.asList(
                new Bill(1, LocalDateTime.now()),
                new Bill(2, LocalDateTime.now()),
                new Bill(3, LocalDateTime.now())
        );

        List<Communication> comms = Arrays.asList(
                new Communication(1, Channel.PHONE, LocalDateTime.now()),
                new Communication(2, Channel.EMAIL, LocalDateTime.now()),
                new Communication(3, Channel.PHONE, LocalDateTime.now())
        );

        List<Payment> payments = Arrays.asList(
                new Payment(1, 100.0, LocalDateTime.now()),
                new Payment(3, 50.0, LocalDateTime.now())
        );

        CommunicationAnalyzer analyzer = new CommunicationAnalyzer(bills, comms, payments);
        String bestChannel = analyzer.getBestChannel();
        System.out.println("Best communication channel: " + bestChannel);
    }
}
