package LeetcodePrograms.InterviewQuestions;

/**
 * @author Rishi Khurana
 */
/*

Write a utility to flat a Given data structure of Input Map<Key,Object> to Output Map<String,String>.

The Key in the input Map is a String,
The Value in the Input Map can be a String or anothee Map<String,Object>

Example

Given the following map:

{
    "customer": {                    // i.e Map<"customer",custObject>
      "id": "123",                   // i.e Map<"Id","123">
      "firstname": "Yem",
      "lastname": "Huynh",
      "address": {
        "address1": "199 Fremont",
        "address2": "4th Floor",
        "city": "San Francisco",
        "state": "CA",
        "zip": "94105",
        "digitalAddress": {
          "email": "abc@def.com
        }
      }
    },
    "order": {                        // i.e Map<"order",orderObject>
      "id": "456",
      "productId": "789",
      "totalCost": "154.76"
    }
}


Flatten it into a map where the keys in the map represent the concatenation of the field names that represent the full path to the field value:
{
  "customer.id" : "123"
  "customer.firstname" : "Yem"
  ..
  ..
  "customer.address.address1" : "199 Fremont"
  "customer.address.address2" : "4th Floor"
  "customer.address.digitalAddress.email" : "abc@def.com"
  ..
  ..
  "order.id" : "456"
}
*/


import java.util.*;
import LeetcodePrograms.NestedInteger;


class ebayquestion {
    static Map<String,Object> mapInput;
    static StringBuilder sb ;


    static {
        mapInput = new HashMap<String,Object>();
        sb = new StringBuilder();

        Map<String,Object> mapCustomer = new HashMap<>();
        mapCustomer.put("id", "123");
        mapCustomer.put("firstname", "Nick");
        mapCustomer.put("lastname", "Smith");
        mapCustomer.put("firstname", "Yem");

        Map<String,Object> mapAddress = new HashMap<>();
        mapAddress.put("address1", "199 Fremont St");
        mapAddress.put("address2", "4th Floor");
        mapAddress.put("city", "San Francisco");
        mapAddress.put("state", "CA");
        mapAddress.put("zip", "94105");

        Map<String, Object> digitalAddress = new HashMap<>();
        digitalAddress.put("email","abc@def.com");
        mapAddress.put("digitalAddress",digitalAddress);
        mapCustomer.put("address", mapAddress);
        mapInput.put("customer", mapCustomer);

        Map<String,Object> mapOrder = new HashMap<>();
        mapOrder.put("id", "456");
        mapOrder.put("sku", "789");
        mapOrder.put("total", "150.99");
        mapInput.put("order", mapOrder);
    }

    public void printMap(){
    //    System.out.println(mapInput);
        Map<String,Object> mapInput1 ;
        mapInput1 = mapInput;
        for(String key : mapInput1.keySet()){
           Object value = mapInput1.get(key);
           sb=new StringBuilder();
           //List<String> list = new ArrayList<>();
           if(value instanceof String){
              // sb.append(key  );
               System.out.println(sb.toString() + "."+key +" : " + value);
           }else{
              // list.add(key);
               sb.append(key + ".");
              // mapInput1 = (Map<String, Object>) mapInput1.get(key);
               printMapUtil((Map<String, Object>)mapInput1.get(key),sb);
           }
        }
    }

    public void printMapUtil(Map<String, Object> mapInput , StringBuilder sb){
        for(String key : mapInput.keySet()){
            Object value = mapInput.get(key);

            if(value instanceof String){
               // sb.append(key  );
                System.out.println(sb.toString() + key +" : " + value);

            }else{
                sb.append(key + ".");
              //  mapInput = (Map<String, Object>) mapInput.get(key);
                printMapUtil((Map<String, Object>) mapInput.get(key),sb);
            }
        }
    }



    public static void main(String args[] ) {
        ebayquestion s = new ebayquestion();
        s.printMap4();
    }

    public void printMap4(){
        for(String key : mapInput.keySet()) {
            getStringRepresentation(key, key, mapInput);
        }
    }

    private void getStringRepresentation(String keyPrefix, String key, Map<String, Object> temp) {
        if(temp.get(key) instanceof String ){
            System.out.println(keyPrefix + ": "+temp.get(key));
        } else {
            Map<String,Object> innerMap = (Map<String,Object>)temp.get(key);
            for(String ikey : innerMap.keySet()) {
                getStringRepresentation(keyPrefix + "." + ikey, ikey, (Map<String, Object>) temp.get(key));
            }
        }
    }

}
