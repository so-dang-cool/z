package exploration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import javax.management.ConstructorParameters;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

public class ContinuationTypeTests {
    public static BiFunction<String, String, String> concat = String::concat;
    public static Function<String, String> toUpper = String::toUpperCase;

    @Test
    void test1() {
        var concatter = Q.with(concat).resolve();
        assertEquals("ab", concatter.apply("a").apply("b"));
    }

    @Test
    void explore1() {
        Function<String, Function<String, String>> concatter =
            a -> b -> concat.apply(a, b);

        assertEquals("ab", concatter.apply("a").apply("b"));
    }

    @Test
    void explore2() {
        Function<
            Function<String, String>,
            Function<String, Function<String, String>>> concatter = null;
        Function<String, Function<String, String>> concatter2 =
            a -> b -> concat.apply(a, b);

        assertEquals("ab", concatter2.apply("a").apply("b"));
    }

    @Test
    void explore3() {
        Function<String, Function<String, String>> con_1 = a -> b -> concat.apply(a, b);
        assertEquals("ab", con_1.apply("a").apply("b"));

        Function<String, Function<String, Supplier<String>>> con_2 = a -> b -> () -> concat.apply(a, b);
        assertEquals("ab", con_2.apply("a").apply("b").get());

        Function<String, Supplier<Function<String, String>>> con_3 = a -> () -> b -> concat.apply(a, b);
        assertEquals("ab", con_3.apply("a").get().apply("b"));

        Function<
            String,
            Function<
                Function<String, String>,
                Function<String, String>
            >
        > con_4 =
            a -> fn -> b -> fn.apply(concat.apply(a, b));
        assertEquals("AB", con_4.apply("a").apply(toUpper).apply("b"));

        Function<
            Function<String, String>,
            Function<
                String,
                Function<String, String>
            >
        > con_5 =
            fn -> a -> b -> fn.apply(concat.apply(a, b));
        assertEquals("AB", con_5.apply(toUpper).apply("a").apply("b"));

        Function<
            Function<String, Integer>,
            Function<
                String,
                Function<String, Integer>
            >
        > con_5a =
            fn -> a -> b -> fn.apply(concat.apply(a, b));
        assertEquals(2, con_5a.apply(String::length).apply("a").apply("b"));

        Function<
            Function<String, Function<String, String>>,
            Function<
                String,
                Function<String, Function<String, String>>
            >
        > con_5b =
            fn -> a -> b -> fn.apply(concat.apply(a, b));
        assertEquals("ABC", con_5b.apply(Z.split(concat)).apply("a").apply("b").apply("c"));

        Function<
            String,
            Function<
                String,
                Function<
                    Function<String, String>,
                    String
                >
            >
        > con_6 =
            a -> b -> fn -> fn.apply(concat.apply(a, b));
        assertEquals("AB", con_6.apply("a").apply("b").apply(toUpper));
    }

    @Test
    void test2() {
        // var concatter = Q.with(concat).chain(concat).resolve();
        // assertEquals("abc", concatter.apply("a").apply("b").apply("c"));
    }
}

class Q<A, B, C> {
    Function<A, Function<B, C>> initial;

    Q(Function<A, Function<B, C>> initial) {
        this.initial = initial;
    }

    static <A, B, C> Q<A, B, C> with(BiFunction<A, B, C> initial) {
        return new Q<>(a -> b -> initial.apply(a, b));
    }

    Function<A, Function<B, C>> resolve() {
        return initial;
    }
}

abstract class Continue {

    class Start<A, B, NEXT> {
        Function<A, B> fn;

        Start(Function<A, B> fn) { this.fn = fn; }
        
        Function<A, NEXT> resolve() {
            return a -> fn.apply(a);
        }
    }

    class Middle<A, B, NEXT> {
        Function<A, B> fn;
        
        Middle(Function<A, B> fn) { this.fn = fn; }
        
        Function<A, B> resolve() {
            return a -> fn.apply(a);
        }
    }

    class End<A, B> {
        Function<A, B> fn;
        
        End(Function<A, B> fn) { this.fn = fn; }
        
        Function<A, B> resolve() {
            return a -> fn.apply(a);
        }
    }
}
