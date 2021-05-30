package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithFunction;

interface ToDoubleFunctionCombos<A> {
    ToDoubleFunction<A> resolve();

    /* ToDoubleFunction<A> -> DoubleFunction<B> */

    public default <B> Function<A, B> fuseDoubleFunction(
        DoubleFunction<B> next
    ) {
        return (A a) -> next.apply(resolve().applyAsDouble(a));
    }

    public default <B> Function<A, B> fuse(DoubleFunction<B> next) {
        return fuseDoubleFunction(next);
    }

    public default <B> WithFunction<A, B> fusingDoubleFunction(
        DoubleFunction<B> next
    ) {
        return WithFunction.of(fuseDoubleFunction(next));
    }

    public default <B> WithFunction<A, B> fusing(DoubleFunction<B> next) {
        return fusingDoubleFunction(next);
    }
}
