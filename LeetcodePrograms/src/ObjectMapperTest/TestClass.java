package LeetcodePrograms.src.ObjectMapperTest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public class TestClass {
    static class Person {
        public String name;
        public int age;

        // Default constructor is needed for Jackson
        public Person() {}

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    static class NewPerson{
        public String name;
        public int age;
        public String city;
        public List<String> hobbies;

    }
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Serialize Java object to JSON
        Person person = new Person("Alice", 30);
        String json = objectMapper.writeValueAsString(person);
        String jsonString = "{\"name\":\"John\", \"age\":30, \"city\":\"New York\", \"hobbies\":[\"reading\", \"traveling\"]}";
        System.out.println("Serialized JSON: " + json);

        // Deserialize JSON back to Java object
        String inputJson = "{\"name\":\"Bob\",\"age\":25}";
        Person deserialized = objectMapper.readValue(inputJson, Person.class);
        NewPerson deserializedPerson = objectMapper.readValue(jsonString, NewPerson.class);
        // System.out.println("Deserialized person: " + deserialized.name + ", " + deserialized.age);
        System.out.println("Deserialized person: " + deserializedPerson.name + ", " + deserializedPerson.age + " , " + deserializedPerson.city + " , "
        + deserializedPerson.hobbies);
    }
}
