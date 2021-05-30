package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.Predicate;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithFunction;

interface PredicateCombos<A> {
    Predicate<A> resolve();

    /* Predicate<A> -> BooleanFunction<B> */

    public default <B> Function<A, B> fuseFunction(BooleanFunction<B> next) {
        return (A a) -> next.apply(resolve().test(a));
    }

    public default <B> Function<A, B> fuse(BooleanFunction<B> next) {
        return fuseFunction(next);
    }

    public default <B> WithFunction<A, B> fusingFunction(
        BooleanFunction<B> next
    ) {
        return WithFunction.of(fuseFunction(next));
    }

    public default <B> WithFunction<A, B> fusing(BooleanFunction<B> next) {
        return fusingFunction(next);
    }
}
