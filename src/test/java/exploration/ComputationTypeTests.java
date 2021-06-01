package exploration;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

public class ComputationTypeTests {
    @Test
    void test1() {

    }
}

abstract class Comp<IN, NEXT> {
    abstract Function<IN, NEXT> resolve();

    final class Continuing<A, B, NEXTER>
        extends Comp<A, NEXTER>
    {
        transient Function<A, B> initial;
        transient Comp<B, NEXTER> next;

        Continuing(Function<A, B> initial) { this.initial = initial; }

        public <C> Comp<B, NEXTER> fusing(Function<B, C> next) {
            return this.next = new Continuing<>(next);
        }

        public Comp<B, NEXTER> complete() {
            return this.next = new Ending<B, NEXTER>((B b) -> b);
        }

        @Override
        Function<A, NEXTER> resolve() {
            return a -> next.resolve().apply(initial.apply(a));
        }
    }

    final class Ending<A, B>
        extends Comp<A, B>
    {
        transient Function<A, B> terminal;

        Ending(Function<A, B> terminal) { this.terminal = terminal; }

        @Override
        Function<A, B> resolve() {
            return terminal;
        }

        
    }
}
