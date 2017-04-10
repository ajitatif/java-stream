package org.turkisi.training.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class Iteration {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        System.out.println("iterable");
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println(next);
        }

        System.out.println("foreach clause");
        for (Integer i : list) {
            System.out.println(i);
        }

        System.out.println("stream forEach");
        list.forEach(i -> System.out.println(i));

        System.out.println("parallel stream forEach");
        list.parallelStream().forEach(i -> System.out.println(i));

        System.out.println("parallel stream forEachOrdered");
        list.parallelStream().forEachOrdered(System.out::println);
    }
}
