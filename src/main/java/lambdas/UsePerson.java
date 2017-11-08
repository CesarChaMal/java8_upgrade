package lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class UsePerson {
    @SuppressWarnings({"UnusedAssignment", "Convert2MethodRef"})
    public static void main(String[] args) {
        List<String> names = Arrays.asList("John", "Paul", "George", "Ringo");

        // Java 7 (and below)
        List<Person> persons = new ArrayList<>();
        for (String name : names) {
            persons.add(new Person(name));
        }
        System.out.println(persons);

        // Java 8
        List<Person> people = names.stream()    // Stream<String>
                .map(name -> new Person(name))  // Stream<Person>
                .collect(Collectors.toList());  // Converts Stream<Person> to List<Person>
        System.out.println(people);
        System.out.println(people.getClass().getName());

        people = names.stream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        people = names.parallelStream()
                .map(Person::new)
                .collect(Collectors.toList());
        System.out.println(people);

        Person[] peopleArray = names.stream()
                .map(Person::new)
                .toArray(Person[]::new);

        Set<Person> peopleSet = names.stream()    // Stream<String>
                //.map(name -> new Person(name))  // Stream<Person>
                .map(Person::new)  // Stream<Person>
                .collect(Collectors.toSet());  // Converts Stream<Person> to List<Person>
        System.out.println(peopleSet);
        System.out.println(peopleSet.getClass().getName());

        Comparator<Person> byName = (o1,o2)->o1.getName().compareTo(o2.getName());
        Set<Person> sortedSet = names.stream()
                .map(Person::new)
                .collect(Collectors.toCollection(   // use lambda to supply Comparator
					()-> new TreeSet<>(byName))
				);
        System.out.println(sortedSet);
        System.out.println(sortedSet.getClass().getName());
        System.out.println("First: " + sortedSet.first());
        System.out.println("Last : " + sortedSet.last());

        System.out.println(Arrays.asList(peopleArray));
        System.out.println(Arrays.toString(peopleArray));
    }
}
