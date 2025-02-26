package Collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPerformanceTest {
    public static void main(String[] args) {

        long arrayListTime = performMultipleTests(new ArrayList<>(), 1000, 1000);
        long linkedListTime = performMultipleTests(new LinkedList<>(), 1000, 1000);
        System.out.println(arrayListTime);
        System.out.println(linkedListTime);

    }


    private static long performMultipleTests(List<Integer> aNumbers, long numberOfElements, long numberOfTests) {
        long totalOfTime = 0;
        for (int i = 0; i < numberOfTests; i++) {
            long singleTestTime = singleTest(aNumbers, numberOfElements);
            totalOfTime += singleTestTime;
        }
        return totalOfTime/numberOfTests;
    }

    private static long singleTest(List<Integer> aNumbers, long numberOfElements) {


        long start = System.nanoTime();
        for (int i = 0; i < numberOfElements; i++) {
            aNumbers.add(i);
        }
        long stop = System.nanoTime();

        return stop - start;
    }
}

