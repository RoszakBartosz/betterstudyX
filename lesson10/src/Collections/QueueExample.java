package Collections;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {


    Queue<String> queue = new LinkedList<>();

        queue.offer("Adam");
        queue.offer("Bartek");
        queue.offer("Damian");

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue);

        Deque<String> deque = new LinkedList<>();
        deque.addFirst("Jan");
        deque.addLast("Krzysztof");
        deque.addFirst("Jaro");
        System.out.println(deque);




    }
}
