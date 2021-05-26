package LeetcodePrograms.CrackingTheCodingInterview;

import java.util.LinkedList;


/**
 * @author Rishi Khurana
 * Chapter 3 Question 6 Animal Shelter
 */
public class AnimalShelter {
    abstract class Animal {

        private int order;
        protected String name;
        public Animal(String n){
            name = n;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(final int order) {
            this.order = order;
        }

        public boolean isOlderThan(Animal a){
            return this.order < a.getOrder();
        }
    }
    class AnimalQueue{
        LinkedList<Dog> dogs = new LinkedList<Dog>();
        LinkedList<Cat> cats = new LinkedList<Cat>();
        private int order = 0; //act as timestamp

        public void enqueue(Animal a){
            a.setOrder(order);
            order++;
            if(a instanceof Dog)
                dogs.addLast((Dog)a);
            else if(a instanceof Cat)
                cats.addLast((Cat) a);
        }

        public Animal dequeueAny(){
            // look at the top of the queues and pop the animal with the oldest time stamp
            if(dogs.size() == 0)
                return dequeueCats();
            if(cats.size() == 0)
                return dequeueDogs();
            Dog dog = dogs.peek();
            Cat cat = cats.peek();
            if(dog.isOlderThan(cat))
                return dequeueDogs();
            else
                return dequeueCats();


        }

        public Dog dequeueDogs(){
            return dogs.poll();

        }

        public Cat dequeueCats(){
            return cats.poll();
        }

    }
    public class Dog extends Animal{
        public Dog(String n){
            super(n);
        }
    }
    public class Cat extends Animal{
        public Cat(String n){
            super(n);
        }
    }

}
