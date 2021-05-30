package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;

interface BooleanFunctionCombos<A> {
    BooleanFunction<A> resolve();

    /* BooleanFunction<A> -> Function<A,B> */

    public default <B> BooleanFunction<B> fuseFunction(Function<A, B> next) {
        return (boolean b) -> next.apply(resolve().apply(b));
    }

    public default <B> BooleanFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithBooleanFunction<B> fusingFunction(
        Function<A, B> next
    ) {
        return WithBooleanFunction.of(fuseFunction(next));
    }

    public default <B> WithBooleanFunction<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }
}
