package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;

interface ToIntBiFunctionCombos<A, B> {
    Function<A, ToIntFunction<B>> resolve();

    /* ToIntBiFunction<A, B> -> IntFunction<C> */

    public default <C> Function<A, Function<B, C>> fuseIntFunction(
        IntFunction<C> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a).applyAsInt(b));
    }

    public default <C> Function<A, Function<B, C>> fuse(IntFunction<C> next) {
        return fuseIntFunction(next);
    }

    public default <C> WithBiFunction<A, B, C> fusingIntFunction(
        IntFunction<C> next
    ) {
        return WithBiFunction.of(fuseIntFunction(next));
    }

    public default <C> WithBiFunction<A, B, C> fusing(IntFunction<C> next) {
        return fusingIntFunction(next);
    }
}
