import java.util.Stack;

public class ReverseDLL {
    public static <T> void reverse(DoublyLinkedList<T> dll) {
        Stack<T> stack = new Stack<>();
        for (T item : dll) {
            stack.push(item);
        }
        dll.clear();
        while (!stack.isEmpty()) {
            dll.add(stack.pop());
        }
    }
    public static void main(String[] args) {
        DoublyLinkedList<String> main = new DoublyLinkedList<>();
        DoublyLinkedList<String> test = new DoublyLinkedList<>();

        for (String arg : args) {
            main.add(arg);
            test.add(arg);
        }

        reverse(test);
        reverse(test);

        boolean equal = true;
        if (main.size() != test.size()) {
            equal = false;
        } else {
            for (int i = 0; i < main.size(); i++) {
                if (!main.get(i).equals(test.get(i))) {
                    equal = false;
                    break;
                }
            }
        }

        if (equal) {
            System.out.println("Reverse worked with the list: " + main);
        } else {
            System.out.println("Reverse did not work for the list.");
            System.out.println("Original: " + main);
            System.out.println("Test:" + test);
        }
    }
}