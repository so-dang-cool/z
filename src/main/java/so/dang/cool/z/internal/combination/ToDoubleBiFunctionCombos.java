package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;

interface ToDoubleBiFunctionCombos<A, B> {
    Function<A, ToDoubleFunction<B>> resolve();

    /* ToDoubleBiFunction<A, B> -> DoubleFunction<C> */

    public default <C> Function<A, Function<B, C>> fuseDoubleFunction(
        DoubleFunction<C> next
    ) {
        return (A a) ->
            (B b) -> next.apply(resolve().apply(a).applyAsDouble(b));
    }

    public default <C> Function<A, Function<B, C>> fuse(
        DoubleFunction<C> next
    ) {
        return fuseDoubleFunction(next);
    }

    public default <C> WithBiFunction<A, B, C> fusingDoubleFunction(
        DoubleFunction<C> next
    ) {
        return WithBiFunction.of(fuseDoubleFunction(next));
    }

    public default <C> WithBiFunction<A, B, C> fusing(DoubleFunction<C> next) {
        return fusingDoubleFunction(next);
    }
}
