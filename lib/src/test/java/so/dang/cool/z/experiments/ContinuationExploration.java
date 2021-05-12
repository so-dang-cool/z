package so.dang.cool.z.experiments;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

public class ContinuationExploration {
    @Test
    public void try_continuing() {
        var seed = Continue.WithFn.of((String s) -> s);
        var leaf1 = Continue.FnToFn.of(String::toUpperCase, seed);
        var leaf2 = Continue.FnToFn.of(s -> s.concat("!!!"), leaf1);

        var excitedGreeting = leaf2.resolve();

        assertEquals("HEY!!!", excitedGreeting.apply("hey"));
    }

    @Test
    public void try_sup2fn() {
        var seed = Continue.WithSup.of(() -> "AYY%SPACE%LMAO");
        var leaf1 = Continue.SupToFn.of(s -> s.replace("%SPACE%", " "), seed);
        var leaf2 = Continue.SupToFn.of(String::toLowerCase, leaf1);

        var calmGreeting = leaf2.resolve();

        assertEquals("ayy lmao", calmGreeting.get());
    }
}

abstract class Continue<A, Fn, Prev> {
    private Continue() {}

    public abstract Fn resolve();

    public static class WithFn<A, B> extends Continue<B, Function<A, B>, Void> {
        private final Function<A, B> function;
        private WithFn(Function<A, B> function) {
            this.function = function;
        }

        public static <A, B> WithFn<A, B> of(Function<A, B> function) {
            return new WithFn<>(function);
        }

        @Override
        public Function<A, B> resolve() {
            return function;
        }
    }

    public static class FnToFn<A, B, C, Prev> extends Continue<C, Function<A, C>, Continue<B, Function<A, B>, Prev>> {
        private final Continue<B, Function<A, B>, Prev> continuation;
        private final Function<B, C> function;

        private FnToFn(Function<B, C> function, Continue<B, Function<A, B>, Prev> continuation) {
            this.function = function;
            this.continuation = continuation;
        }

        public static <A, B, C, PrevCont> FnToFn<A, B, C, PrevCont> of(Function<B, C> function, Continue<B, Function<A, B>, PrevCont> continuation) {
            return new FnToFn<>(function, continuation);
        }

        @Override
        public Function<A, C> resolve() {
            return Z.fuse(continuation.resolve(), function);
        }
    }

    public static class WithSup<A> extends Continue<A, Supplier<A>, Void> {
        private final Supplier<A> supplier;
        private WithSup(Supplier<A> supplier) {
            this.supplier = supplier;
        }

        public static <A> WithSup<A> of(Supplier<A> supplier) {
            return new WithSup<>(supplier);
        }

        @Override
        public Supplier<A> resolve() {
            return supplier;
        }
    }

    public static class SupToFn<A, B, Prev> extends Continue<B, Supplier<B>, Continue<A, Supplier<A>, Prev>> {
        private final Continue<A, Supplier<A>, Prev> continuation;
        private final Function<A, B> function;
        
        private SupToFn(Function<A, B> function, Continue<A, Supplier<A>, Prev> continuation) {
            this.function = function;
            this.continuation = continuation;
        }

        public static <A, B, Prev> SupToFn<A, B, Prev> of(Function<A, B> function, Continue<A, Supplier<A>, Prev> continuation) {
            return new SupToFn<>(function, continuation);
        }

        @Override
        public Supplier<B> resolve() {
            return Z.fuse(continuation.resolve(), function);
        }
    }
}
