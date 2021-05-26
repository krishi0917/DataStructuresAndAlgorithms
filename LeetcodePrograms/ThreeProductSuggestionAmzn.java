package LeetcodePrograms;
import java.util.*;

// Search AutoComplete System.
// Amzn Question
// ImplementTrie Data striucte
public class ThreeProductSuggestionAmzn {

        public class Node {
            Map<Character, Node> children = new HashMap<>();
            Map<Integer, String> wordTreeMap = new HashMap<>();
            public List<String> wordForThatSearch = new ArrayList<>();
        }

        Node root = new Node();

        public void addWord(String word) {
            word = word.toLowerCase();
            Node p = root;
            for (char c : word.toCharArray()) {
                if(!p.children.containsKey(c)) {
                    p.children.put(c, new Node());
                }
                p = p.children.get(c);
                p.wordForThatSearch.add(word);
            }
        }

        public List<String> getAllWordsForQuery(String query) {
            //   System.out.println(query);
            query=query.toLowerCase();
            Node p = root;

            for(int i=0; i<query.length(); i++) {

                char ch = query.charAt(i);
                if(!p.children.containsKey(ch)) {
                    return null;
                }
                p = p.children.get(ch);
                if(i == query.length() - 1) {
                    return p.wordForThatSearch;
                }
            }
            return null;
        }


        List<List<String> > threeProductSuggestions(int numProducts,
                List<String> repository,
                String customerQuery)
        {
            List<List<String>> result = new ArrayList();

            for(int i=0; i< repository.size(); i++) {
                addWord(repository.get(i));
            }

            for(int i=1; i < customerQuery.length(); i++) {
                List<String> suggestions = getAllWordsForQuery(customerQuery.substring(0, i+1));
                if(suggestions == null)
                    continue;
                //   System.out.println(suggestions.size());
                Collections.sort(suggestions);
                if(suggestions.size() < 3) {
                    result.add(suggestions);
                } else {
                    List<String> subList = new ArrayList<String>();
                    for(int j =0; j<=2; j++) {
                        subList.add(suggestions.get(j));
                    }
                    result.add(subList);

                }

            }

            return result;
        }
    }
