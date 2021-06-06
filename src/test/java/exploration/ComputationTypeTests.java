package exploration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

public class ComputationTypeTests {
    @Test
    void test1() {
        Comp<Function<String, String>> comp =
            Comp.of((String s) -> s.toLowerCase())
                .and((String s) -> s.trim());

        assertEquals("hello", comp.resolve().apply("HELLO"));
    }

    @Test
    void test2() {
        BiFunction<String, String, String> concat = String::concat;

        Comp<Function<String, Function<String, String>>> comp =
            Comp.of(concat);

        assertEquals("no way", comp.resolve().apply("no ").apply("way"));
    }

    @Test
    void test3() {
        BiFunction<String, String, String> concat = String::concat;

        // Comp<Function<String, Function<String, String>>> comp =
        //     Comp.of(concat)
        //         .and(concat);

        // assertEquals("no way", comp.resolve().apply("no ").apply("way ").apply("jose"));
    }
}

abstract class Comp<Fn> {

    abstract Fn resolve();

    static <A, B> OfFunction<A, B> of(Function<A, B> thing) {
        return new OfFunction<>(thing);
    }

    static <A, B, C> OfCurriedFunction<A, B, C> of(BiFunction<A, B, C> thing) {
        return new OfCurriedFunction<>(thing);
    }

    static class OfFunction<A, B> extends Comp<Function<A, B>> {
        final Function<A, B> initial;

        OfFunction(Function<A, B> initial) {
            this.initial = initial;
        }

        @Override
        Function<A, B> resolve() {
            return initial;
        }

        <C> Comp<Function<A, C>> and(Function<B, C> next) {
            return new OfFunction<>(Z.fuse(initial, next));
        }
    }

    // static class OfBiFunction<A, B, C> extends Comp<Function<A, Function<B, C>>> {
    //     final BiFunction<A, B, C> initial;

    //     OfBiFunction(BiFunction<A, B, C> initial) {
    //         this.initial = initial;
    //     }

    //     @Override
    //     Function<A, Function<B, C>> resolve() {
    //         return a -> b -> initial.apply(a, b);
    //     }
    // }

    static class OfCurriedFunction<A, B, C> extends Comp<Function<A, Function<B, C>>> {
        final Function<A, Function<B, C>> initial;

        OfCurriedFunction(Function<A, Function<B, C>> initial) {
            this.initial = initial;
        }

        OfCurriedFunction(BiFunction<A, B, C> initial) {
            this.initial = a -> b -> initial.apply(a, b);
        }

        @Override
        Function<A, Function<B, C>> resolve() {
            return initial;
        }

        /*
            (a -> b -> f(a, b))

            a -> b -> c -> g(f(a, b), c)

            Can make:

            F (a -> b -> c)

            F (c -> d -> e)

            F (e -> f -> g)

            Want to make:

            F (a -> b -> d -> f -> g)

            Intermediate steps:

            A -> Z
                 Z = B -> C

            Get<A>
              And<Get<A>, B>
              And<And<Get<A>, B>, C>

              F<A, F<B, F<C, F<D, F<E, F<F, G>>>>>>

              F<A, B>
              F<F<A, F<B, C>>, F<B, C>>

            Head<A> {
                WithNext(head, next)
            }

            ContinuingOver<A, Function<B, NEXT>> {
                
            }
        */

        <D, E> Comp<Function<A, C>> and(BiFunction<C, D, E> next) {
            return null;
            // return new OfCurriedFunction<>(Z.fuse(initial, next));
        }
    }
}
