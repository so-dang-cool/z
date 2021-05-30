package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;

interface BiFunctionCombos<A, B, C> {
    Function<A, Function<B, C>> resolve();

    /*
        Thinking about how to do curried combos...

        BiFunction<A, B, C> -> BiFunction<C, D, E>
        = Function<A, Function<B, Function<D, E>>>

        Function<A, B> -> BiFunction<B, C, D>
        = Function<A, Function<C, D>>
    */

    /* BiFunction<A, B, C> -> Function<C, D> */

    public default <D> Function<A, Function<B, D>> fuseFunction(
        Function<C, D> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a).apply(b));
    }

    public default <D> Function<A, Function<B, D>> fuse(Function<C, D> next) {
        return fuseFunction(next);
    }

    public default <D> WithBiFunction<A, B, D> fusingFunction(
        Function<C, D> next
    ) {
        return WithBiFunction.of(fuseFunction(next));
    }

    public default <D> WithBiFunction<A, B, D> fusing(Function<C, D> next) {
        return fusingFunction(next);
    }
}
