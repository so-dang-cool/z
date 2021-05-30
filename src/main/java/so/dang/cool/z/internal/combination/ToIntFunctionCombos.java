package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithFunction;

interface ToIntFunctionCombos<A> {
    ToIntFunction<A> resolve();

    /* ToIntFunction<A> -> IntFunction<B> */

    public default <B> Function<A, B> fuseIntFunction(IntFunction<B> next) {
        return (A a) -> next.apply(resolve().applyAsInt(a));
    }

    public default <B> Function<A, B> fuse(IntFunction<B> next) {
        return fuseIntFunction(next);
    }

    public default <B> WithFunction<A, B> fusingIntFunction(
        IntFunction<B> next
    ) {
        return WithFunction.of(fuseIntFunction(next));
    }

    public default <B> WithFunction<A, B> fusing(IntFunction<B> next) {
        return fusingIntFunction(next);
    }
}
