import java.util.Stack;

public class ReverseDLL {
    public static <T> void reverse(DoublyLinkedList<T> dll) {
        Stack<T> stack = new Stack<>();                         // creates an empty stack
        for (T item : dll) {                                    // loops through the elements in the stack
            stack.push(item);                                   // adds the elements item in the stack
        }
        dll.clear();                                            // clears the stack
        while (!stack.isEmpty()) {                              // loops until stak empty
            dll.add(stack.pop());                               // pop/removes element from stack and adds them in reverse order
        }
    }
    public static void main(String[] args) {

        // creates two lists, one main and one to test
        DoublyLinkedList<String> main = new DoublyLinkedList<>();
        DoublyLinkedList<String> test = new DoublyLinkedList<>();

        for (String arg : args) {
            main.add(arg);                                      // add element to main list for every argument
            test.add(arg);                                      // add element to test list for every argument
        }

        reverse(test);                                          // reverse the list twice
        reverse(test);

        // checks if the two lists are the same ofter reversing test
        boolean equal = true;
        if (main.size() != test.size()) {                       // checks if the amount of elements are the same
            equal = false;
        } else {
            for (int i = 0; i < main.size(); i++) {             // if so, check each element
                if (!main.get(i).equals(test.get(i))) {
                    equal = false;
                    break;
                }
            }
        }

        // output the result
        if (equal) {
            System.out.println("Reverse worked with the list: " + main);
        } else {
            System.out.println("Reverse did not work for the list.");
            System.out.println("Original: " + main);
            System.out.println("Test:" + test);
        }
    }
}