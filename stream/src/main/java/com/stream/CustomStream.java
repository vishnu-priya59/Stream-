package com.stream;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/*
(Interface) Function<T, R>
T ~ input type, R~ type of result output

 unary(takes one argument) function T to R

 Consumer - unary T to void

 Predicate - unary T to boolean
  boolean test()

 Supplier - nullary(takes no arguments)function to R
     T get();

 BinaryOperator is specialized from BiFunction


        Function<T>


Stream is used to perform operations like filtering, mapping, reducing, and sorting.
There are two types of operations:

-> Intermediate operations(Are not executed until a terminal operation is called.)
-> Terminal operations(returns the final result.They don;t return a stream)

 */


public class CustomStream<T>{
    private final List<T> elements;

    CustomStream(List<T> elements) {
        this.elements = elements;
    }

    public static <T> CustomStream<T> of(Collection<T> collection) {
        return new CustomStream<>(new ArrayList<>(collection));
    }

    public CustomStream<T> filter(Predicate<? super T> predicate) {
        List<T> filtered = elements.stream()
                .filter(predicate)
                .collect(Collectors.toList());
        return new CustomStream<>(filtered);
    }

    public <R> CustomStream<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mapped = elements.stream()
                .map(mapper)
                .collect(Collectors.toList());
        return new CustomStream<>(mapped);
    }

    public List<T> toList() {
        return new ArrayList<>(elements);
    }

    public <K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper,
                                  Function<? super T, ? extends V> valueMapper) {
        return elements.stream().collect(Collectors.toMap(keyMapper, valueMapper));
    }

    public CustomStream<T> sorted(Comparator<? super T> comparator) {
        List<T> sortedList = elements.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
        return new CustomStream<>(sortedList);
    }

    public CustomStream<T> distinct() {
        List<T> distinctList = elements.stream()
                .distinct()
                .collect(Collectors.toList());
        return new CustomStream<>(distinctList);
    }

    public CustomStream<T> limit(long maxSize) {
        List<T> limitedList = elements.stream()
                .limit(maxSize)
                .collect(Collectors.toList());
        return new CustomStream<>(limitedList);
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Vishnu", "Priya", "Isha", "Vanshika", "Veda");
        CustomStream<String> customStream = CustomStream.of(names);

        // Filter names that start with 'V'
        CustomStream<String> filtered = customStream.filter(name -> name.startsWith("V"));
        System.out.println(filtered);

        // Map to uppercase
        CustomStream<String> mapped = customStream.map(String::toUpperCase);
        System.out.println(mapped);

        // Collect to List
        List<String> list = customStream.toList();
        System.out.println(list);

        // Collect to Map
        Map<String, Integer> nameLengthMap = customStream.toMap(name -> name, String::length);
        System.out.println(nameLengthMap);

        // Sorted names
        CustomStream<String> sorted = customStream.sorted(String::compareTo);
        System.out.println(sorted);

        // Distinct names (if duplicates existed)
        CustomStream<String> distinct = customStream.distinct();
        System.out.println(distinct);

        // Limit to first 3 names
        CustomStream<String> limited = customStream.limit(3);
        System.out.println(limited);
    }
}