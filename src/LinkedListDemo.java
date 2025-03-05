import java.util.LinkedList;

public class LinkedListDemo {

    public static void main(String[] args) {
        
        LinkedList<String> ll = new LinkedList<>();

        ll.add("Amber");
        ll.add("Amber");
        ll.add("Amber");
        System.out.print("\033[H\033[2J");
        System.out.println(ll);
    }
}