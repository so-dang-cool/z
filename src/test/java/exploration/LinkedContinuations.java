package exploration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static so.dang.cool.z.combination.TestFunctions.concat;
import static so.dang.cool.z.combination.TestFunctions.trim;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

public class LinkedContinuations {
    @Test
    void test1() {
        Function<String, Function<String, String>> comp =
            X.of(concat).resolve();

        assertEquals("hello", comp.apply("hell").apply("o"));
    }

    @Test
    void test2() {
        // Function<String, Function<String, Function<String, String>>> comp =
        //     X.of(concat).and(concat).resolve();

        Function<Function<String, Function<String, String>>, Function<String, Function<String, Function<String, String>>>> bridge =
            fn -> a -> b -> fn.apply(concat.apply(a, b));

        Function<String, Function<String, String>> next =
            a -> c -> concat.apply(a, c);

        Function<String, Function<String, Function<String, String>>> comp =
            bridge.apply(next);

        assertEquals("hello", comp.apply("he").apply("ll").apply("o"));
    }

    @Test
    void test3() {
        Function<
            /*Bridge*/ Function</*C*/String, /*NEXT*/String>,
            /*Result*/ Function</*A*/String, Function</*B*/String, /*NEXT*/String>>> bridgedfn =
                fn -> a -> b -> fn.apply(concat.apply(a,b));
        
        assertEquals("hello", bridgedfn.apply(/*next*/trim).apply(" hel").apply("lo "));

        Function<
            /*Bridge*/ Function</*C*/String, /*NEXT*/Function<String, String>>,
            /*Result*/ Function</*A*/String, Function</*B*/String, /*NEXT*/Function<String, String>>>> bridgedfn2 =
                fn -> a -> b -> fn.apply(concat.apply(a,b));

        Function<String, Function<String, String>> curriedfn = a -> b -> concat.apply(a, b);

        assertEquals("hello", bridgedfn2.apply(/*next*/curriedfn).apply("h").apply("e").apply("llo"));
    }
}

abstract class X {
    static class Start<A, B, C, Next> extends X {
        BiFunction<A, B, C> initial;

        Start(BiFunction<A, B, C> initial) {
            this.initial = initial;
        }

        <D, E, Nexter>

        Continue<
            Function<
            Function<C, Next>,
            Function<A, Function<B, Next>>>,
            C, D, E,
            Nexter>

        and(BiFunction<C, D, E> next)
        {
            Function<
                Function<C, Next>,
                Function<A, Function<B, Next>>> bridge =
                    fn -> a -> b-> fn.apply(initial.apply(a, b));

                    return null;
            // return new Continue<Function<
            //     Function<C, Next>,
            //     Function<A, Function<B, Next>>>, C, D, E, Nexter>(bridge, next);
        }

        Function<A, Function<B, C>> resolve() {
            return Z.split(initial);
        }
    }

    static class Continue<Prev, A, B, C, Next> extends X {
        Function<Function<A, Next>, Prev> prevBridge;

        BiFunction<A, B, C> initial;
        
        Continue(Function<Function<A, Next>, Prev> prevBridge, BiFunction<A, B, C> initial) {
            this.prevBridge = prevBridge;
            this.initial = initial;
        }


    }

    static <A, B, C, Next> Start<A, B, C, Next> of(BiFunction<A, B, C> initial) {
        return new Start<>(initial);
    }
}
