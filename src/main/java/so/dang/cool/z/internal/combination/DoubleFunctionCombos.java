package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.Function;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;

interface DoubleFunctionCombos<A> {
    DoubleFunction<A> resolve();

    /* DoubleFunction<A> -> Function<A,B> */

    public default <B> DoubleFunction<B> fuseFunction(Function<A, B> next) {
        return (double d) -> next.apply(resolve().apply(d));
    }

    public default <B> DoubleFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithDoubleFunction<B> fusingFunction(
        Function<A, B> next
    ) {
        return WithDoubleFunction.of(fuseFunction(next));
    }

    public default <B> WithDoubleFunction<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }
}
