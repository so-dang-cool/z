package so.dang.cool.z.internal.combination;

import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;

interface IntPredicateCombos {
    IntPredicate resolve();

    /* IntPredicate -> BooleanFunction<A> */

    public default <A> IntFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return (int i) -> next.apply(resolve().test(i));
    }

    public default <A> IntFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    public default <A> WithIntFunction<A> fusingBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithIntFunction.of(fuseBooleanFunction(next));
    }

    public default <A> WithIntFunction<A> fusing(BooleanFunction<A> next) {
        return fusingBooleanFunction(next);
    }
}
