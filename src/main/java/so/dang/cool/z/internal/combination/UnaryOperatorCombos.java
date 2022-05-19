package so.dang.cool.z.internal.combination;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithFunction;

public interface UnaryOperatorCombos<A> {
    public UnaryOperator<A> resolve();

    /* UnaryOperator -> Function<A, B> */

    public default <B> Function<A, B> fuseFunction(Function<A, B> next) {
        return (A a) -> next.apply(resolve().apply(a));
    }

    public default <B> Function<A, B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithFunction<A, B> fusingFunction(Function<A, B> next) {
        return WithFunction.of(fuse(next));
    }

    public default <B> WithFunction<A, B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }

    /* UnaryOperator -> BiFunction<A, B, C> */

    public default <B, C> Function<A, Function<B, C>> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return (A a) -> (B c) -> next.apply(resolve().apply(a), c);
    }

    public default <B, C> Function<A, Function<B, C>> fuse(
        BiFunction<A, B, C> next
    ) {
        return fuseBiFunction(next);
    }

    public default <B, C> WithBiFunction<A, B, C> fusingBiFunction(
        BiFunction<A, B, C> next
    ) {
        return WithBiFunction.of(fuse(next));
    }

    public default <B, C> WithBiFunction<A, B, C> fusing(
        BiFunction<A, B, C> next
    ) {
        return fusingBiFunction(next);
    }
}
