package com.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollections {
    public static void main(String[] args) {

        //create a stream
        Stream<String> stream = Stream.of("a", "b", "c");
        stream.forEach(System.out::println);


        //create a stream from sources
        Collection<String> collection = Arrays.asList("Vishnu", "Priya");
        Stream<String> stream2 = collection.stream();
        stream2.forEach(System.out::println);

        List<String> lst = Arrays.asList("Aadarsh", "Misra");
        Stream<String> stream3 = collection.stream();
        stream3.forEach(System.out::println);


        Set<String> set = new HashSet<>(lst);
        Stream<String> stream4 = set.stream();
        stream4.forEach(System.out::println);


        Stream<String> stringStream = Stream.generate(() -> "+");
        List<String> list = stringStream
                .limit(5)
                .collect(Collectors.toList());
        System.out.println(list);


        int arr[] = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(arr).filter(n -> n % 2 == 0).sum();

        List<String> list1 = Arrays.asList("Vishnu", " Priya", "Isha", "vanshika");
        String[] array = {"Vishnu", " Priya", "Isha", "vanshika"};
        Stream<String> stream5 = list1.stream();
        Stream<String> mystream = Arrays.stream(array);
        Stream<Integer> s = Stream.of(1, 2, 3, 5);

        //we can use distinct(), sorted().......
        List<Integer> list2 = Arrays.asList(1, 34, 576, 23, 3, 6, 8, 3, 5, 8, 89);
        List<Integer> Filteredlist = list2.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n / 2)
                .distinct()
                .sorted((a, b) -> (b - a))
                .limit(2)
                .skip(1)
                .collect(Collectors.toList());
        System.out.println(Filteredlist);

        List<Integer> Maplist = list2.stream().map(n -> n / 2).collect(Collectors.toList());
        System.out.println(Maplist);

        List<Integer> collect = Stream.iterate(0, x -> x + 1)
                .limit(100)
                .skip(1)
                .filter(n -> n % 2 == 0)
                .map(x -> x / 10)
                .peek(x -> System.out.println(x))
                .collect(Collectors.toList());

        //System.out.println(collect);

        Long integer = Stream.iterate(0, x -> x + 1)
                .limit(100)
                // .peek(x -> System.out.println(x))
                .map(x -> x / 20)
                .distinct()
                .count();
        System.out.println(integer);
    }
}
