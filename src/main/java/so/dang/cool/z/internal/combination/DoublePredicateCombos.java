package so.dang.cool.z.internal.combination;

import java.util.function.DoublePredicate;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoublePredicate;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;

interface DoublePredicateCombos {
    DoublePredicate resolve();

    /* DoublePredicate -> BooleanFunction<A> */

    public default <A> WithDoubleFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithDoubleFunction.of(
            (double d) -> next.apply(resolve().test(d))
        );
    }

    public default <A> WithDoubleFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    /* DoublePredicate -> BooleanToDoubleFunction */

    public default WithDoubleUnaryOperator fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithDoubleUnaryOperator.of(
            (double d) -> next.applyAsDouble(resolve().test(d))
        );
    }

    public default WithDoubleUnaryOperator fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    /* DoublePredicate -> BooleanToIntFunction */

    public default WithDoubleToIntFunction fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithDoubleToIntFunction.of(
            (double d) -> next.applyAsInt(resolve().test(d))
        );
    }

    public default WithDoubleToIntFunction fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    /* DoublePredicate -> BooleanToLongFunction */

    public default WithDoubleToLongFunction fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithDoubleToLongFunction.of(
            (double d) -> next.applyAsLong(resolve().test(d))
        );
    }

    public default WithDoubleToLongFunction fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    /* DoublePredicate -> BooleanPredicate */

    public default WithDoublePredicate fuseDoublePredicate(
        BooleanPredicate next
    ) {
        return WithDoublePredicate.of(
            (double d) -> next.test(resolve().test(d))
        );
    }

    public default WithDoublePredicate fuse(BooleanPredicate next) {
        return fuseDoublePredicate(next);
    }

    /* DoublePredicate -> BooleanConsumer */

    public default WithDoubleConsumer fuseBooleanConsumer(
        BooleanConsumer next
    ) {
        return WithDoubleConsumer.of(
            (double d) -> next.accept(resolve().test(d))
        );
    }

    public default WithDoubleConsumer fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }
}
