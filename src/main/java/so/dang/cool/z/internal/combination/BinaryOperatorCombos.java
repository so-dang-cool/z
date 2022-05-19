package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.UnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;

public interface BinaryOperatorCombos<A> {
    public Function<A, UnaryOperator<A>> resolve();

    /* BinaryOperator -> Function<A, B> */

    public default <B> Function<A, Function<A, B>> fuseFunction(
        Function<A, B> next
    ) {
        return (A a1) -> (A a2) -> next.apply(resolve().apply(a1).apply(a2));
    }

    public default <B> Function<A, Function<A, B>> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithBiFunction<A, A, B> fusingFunction(
        Function<A, B> next
    ) {
        return WithBiFunction.of(fuse(next));
    }

    public default <B> WithBiFunction<A, A, B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }
}
