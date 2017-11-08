package lambdas;

import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class FunctionalInterfacesTest {
    private Logger logger = Logger.getLogger(FunctionalInterfacesTest.class.getName());

    @Test
    public void implementConsumerUsingAnonInnerClass() {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("Hello, World!");
    }

    @SuppressWarnings("Convert2MethodRef")
    @Test
    public void implementConsumerUsingLambda() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingMethodReference() {
        Consumer<String> printer = System.out::println;
        printer.accept("Hello, World!");
    }

    @Test
    public void implementSupplierUsingAnonInnerClass() {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Hello";
            }
        };

        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingLambda() {
        logger.fine("this is a message");
        logger.fine(() -> "this is a message");

        Supplier<String> supplier = () -> "Hello";
        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingMethodReference() {
        // Create a Supplier<Double> that calls Math.random()
        Supplier<Double> supplier = Math::random;
        assertTrue(supplier.get() >= 0.0);
        assertTrue(supplier.get() < 1.0);

        // Create a DoubleSupplier that does the same
        DoubleSupplier doubleSupplier = Math::random;
        assertTrue(doubleSupplier.getAsDouble() >= 0.0);
        assertTrue(doubleSupplier.getAsDouble() < 1.0);
    }

    @Test
    public void constructorReference() {
        List<String> stringList = Arrays.asList("a b c b c d".split(" "));
        assertEquals(6, stringList.size());

        // Add the strings to a Set
        Set<String> strings = stringList.stream()
                .collect(Collectors.toSet());
        assertEquals(4, strings.size());
        assertEquals(HashSet.class, strings.getClass());

        // Add the strings to a TreeSet
        SortedSet<String> sortedStrings = stringList.stream()
                .collect(Collectors.toCollection(TreeSet::new));
        assertEquals(4, sortedStrings.size());
        assertEquals(TreeSet.class, sortedStrings.getClass());
        assertEquals("a", sortedStrings.first());
    }

    @Test
    public void filterWithPredicate() {
//        IntStream.of(3, 1, 4, 1, 5, 9)
//                .filter(n -> true)  // accept even nums only
//                .forEach(n -> assertTrue(n % 2 == 0));
    }
}
