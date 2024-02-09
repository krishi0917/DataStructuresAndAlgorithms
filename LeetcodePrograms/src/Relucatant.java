package LeetcodePrograms.src;

public class Relucatant {

    private Relucatant internalInstance = new Relucatant();

    public Relucatant() throws Exception {
        throw new Exception("I’m not coming out");
    }

    public static void main(String[] args) {
        try {
            Relucatant b = new Relucatant();
            System.out.println("Surprise!");
        } catch (Exception ex) {
            System.out.println("I told you so");
        }
    }
}