//package LeetcodePrograms;
//import java.util.*;
//public class TrieAutocomplete1 {
//
//    class  Node {
//        public Map<Character, Node> children = new HashMap<>();
//        public Map<Integer,String> wordTreeMap = new TreeMap<>();
//        List<String> wordforthatSearch = new ArrayList<>();  // i added it later on, not needed though
//    }
//
//    Node root = new Node();
//
//    public  void addWord(String word) {
//        Node p = root;
//        for (char c : word.toCharArray()) {
//            if (!p.children.containsKey(c)) {
//                p.children.put(c, new Node());
//            }
//            p = p.children.get(c);
//            //                p.wordTreeMap.put(count, word);
//            p.wordforthatSearch.add(word);
////            String  x = callnewMethod(word);
//        }
//    }
//
//    public List<String> callnewMethod(String customerQuery){
//        Node p = root;
//        for (char c : customerQuery.toCharArray()) {
//            if (!p.children.containsKey(c)) {
//                return Collections.emptyList();
//            }
//            p = p.children.get(c);
//        }
//
////        List<String> words = new ArrayList<String>();
////        List<String> justWordswithoutCount = new ArrayList<>();
////        words.addAll(p.wordTreeMap.values());
////        justWordswithoutCount.addAll(p.wordforthatSearch);
////        //Collections.sort(words, Collections.reverseOrder()); //this doesnt work
////        Collections.reverse(words);
////        System.out.println("all the words without order is "+ justWordswithoutCount);
////        return words;
//    }
//
//    List<String > threeProductSuggestions(int numProducts,
//            List<String> repository,
//            String customerQuery)
//    {
//
//
//        for(int i = 0 ; i < repository.size();i++){
//            addWord(repository.get(i));
//        }
//
//
//
//
//    }
//
//
//    public static void main(String []args){
//        TrieAutocomplete1 trie = new  TrieAutocomplete1();
//        int numProducts = 5;
//        List<String> list = new ArrayList<>();
//        list.add("mobile");
//        list.add("mouse");
//        list.add("moneypot");
//        list.add("monitor");
//String        customerQuery = "mo";
//        System.out.println(trie.threeProductSuggestions(5, list, customerQuery));
//    }
//
//}
