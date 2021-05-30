package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.Predicate;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;

interface BiPredicateCombos<A, B> {
    Function<A, Predicate<B>> resolve();

    /* BiPredicate<A, B> -> BooleanFunction<C> */

    public default <C> Function<A, Function<B, C>> fuseBooleanFunction(
        BooleanFunction<C> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a).test(b));
    }

    public default <C> Function<A, Function<B, C>> fuse(
        BooleanFunction<C> next
    ) {
        return fuseBooleanFunction(next);
    }

    public default <C> WithBiFunction<A, B, C> fusingBooleanFunction(
        BooleanFunction<C> next
    ) {
        return WithBiFunction.of(fuseBooleanFunction(next));
    }

    public default <C> WithBiFunction<A, B, C> fusing(BooleanFunction<C> next) {
        return fusingBooleanFunction(next);
    }
}
