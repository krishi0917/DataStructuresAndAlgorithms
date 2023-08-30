package LeetcodePrograms.InterviewQuestions;
import java.util.*;

// At Affirm, we have many forms of data ranging from raw external data (e.g. credit reports) to primitive values that are consumed by our ML models as features. They can also be an intermediate representation to derive other data or used in business rule evaluations (e.g. legal and compliance checks).

//     user ID   merchant ID
// \         /
//  \       /
//   \     /
//    \   /
//     \ /
// how much the user spent at the
// merchant within the last 7 days

// class Resolver(object):
//     input_signal_names = ['user_id', 'merchant_id']
//     output_signal_name = 'user_loan_amount_at_some_merchant_7_days'

//     @staticmethod
//     def run(input_signal_values):
//
    class UnresolvableSignal extends Exception {
        public static final long serialVersionUID = 1;

        public UnresolvableSignal(String signal) {
            super(signal);
        }
    }

    interface Resolver {
        String[] getInputSignalNames(); // ["A"]

        String getOutputSignalName(); // "B"

        int resolve(Map<String, Integer> inputSignalValues) throws UnresolvableSignal; // {"A": 10}
    }

    class Engine {
        Map<String, Resolver> resolverMap;

        //  Map<String, Resolver> resolverMap "C" => ResolverC
        public Engine() {
            resolverMap = new HashMap<>();
            // throw new UnsupportedOperationException("Not yet implemented.");
        }

        // ResolverD -- support arbitrary N resolvers
        public void register(Resolver resolver) {
            resolverMap.put(resolver.getOutputSignalName(), resolver);
            // throw new UnsupportedOperationException("Not yet implemented.");
        }

        // {"A": 10, "C": 30}
        // resolve("C", {"A": 10})
        // ResolverD input signals ['A', 'C'] -> output signal 'D'
        public int resolve(String signalToResolve, Map<String, Integer> inputSignalValues) throws UnresolvableSignal {
            Resolver r = resolverMap.get(signalToResolve); // # ResolverC
            //   for (String s: r.getInputSignalNames()) { ... // if we have the inputSignal, return that -- otherwise call resolve for the input }
            // "C" => ResolverC -> ["B"] -> engine.resolve("B", inputSignalValues)
            // ^ -- inputSignalValues.put(s, ^)
            // return r.resolve(inputSignalValues) // {"A": 10, "B": 15}

            // throw new UnsupportedOperationException("Not yet implemented.");
            return 0;
        }
    }

    class ResolverB implements Resolver {
        public String[] getInputSignalNames() {
            return new String[]{ "A" };
        }

        public String getOutputSignalName() {
            return "B";
        }

        public int resolve(Map<String, Integer> inputSignalValues) throws UnresolvableSignal {
            Integer a = inputSignalValues.get("A");
            if (a == null) {
                throw new UnresolvableSignal("A");
            }
            return a + 5;
        }
    }

    class ResolverC implements Resolver {
        public String[] getInputSignalNames() {
            return new String[]{ "B" };
        }

        public String getOutputSignalName() {
            return "C";
        }

        public int resolve(Map<String, Integer> inputSignalValues) throws UnresolvableSignal {
            Integer b = inputSignalValues.get("B");
            if (b == null) {
                throw new UnresolvableSignal("B");
            }
            return b * 2;
        }
    }

public class affirmOnsiteQuestion {

        public static void main(String[] args) throws UnresolvableSignal {
            Engine engine = new Engine();
            engine.register(new ResolverB());
            engine.register(new ResolverC());

            System.out.println(engine.resolve("C", Map.of("A", 10))); // should be 30
            System.out.println(engine.resolve("C", Map.of("B", 15))); // should be 30
        }
    }






