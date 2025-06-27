package LeetcodePrograms.src.ObjectMapperTest;
import org.json.JSONObject;
import org.json.JSONArray;
public class TestClass2 {
    public static void main(String[] args) {
        String jsonString = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\", \"hobbies\":[\"reading\", \"traveling\"]}";

        JSONObject jsonObject = new JSONObject(jsonString);

        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String city = jsonObject.getString("city");
        JSONArray hobbies = jsonObject.getJSONArray("hobbies");

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
        System.out.println("Hobbies: " + hobbies);
    }
}