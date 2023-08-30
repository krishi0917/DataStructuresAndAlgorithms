package LeetcodePrograms;

import java.util.*;

/*

Add up the amount of subdomain visits.

You'll be provided a fixed table of pairs of strings.

input:

{ "100", "google.com"},
{ "50", "yahoo.com"},
{ "5", "mobile.yahoo.com"},
{ "5", "foo.org"},
{ "5", "bar.org"},

console output (order doesn't matter):

100 google.com correct
55 yahoo.com.  --> null
5 mobile.yahoo.com --> correct
5 foo.org correct
5 bar.org correct
10 org --> null
155 com --> null
*/
public class TrieSalesforceQuestion {


    class TrieNode {
        HashMap<String, TrieNode> children;
        int count;
        String name;
        public TrieNode() {
            children = new HashMap<>();
            count = 0;
            name = null;
        }
    }
    TrieNode root = new TrieNode();
    Map<String,Integer> domainCountMap = new HashMap<>();
    public void findDomainCounts(String [][]input){

        for(String[] inputStr : input){
            int currentInputCount = Integer.parseInt(inputStr[0]);
            String currentInputDomain = inputStr[1];
            String[]currentInputDomainSplit = currentInputDomain.split("\\.");
            TrieNode node = root;
            for(int i = currentInputDomainSplit.length - 1 ; i >= 0 ; i-- ){


                if(node.children.containsKey(currentInputDomainSplit[i])){

                }else{
                    
                    //TrieNode children = node.children;
                    //node.children.put()
                }
            }
        }
    }



}
