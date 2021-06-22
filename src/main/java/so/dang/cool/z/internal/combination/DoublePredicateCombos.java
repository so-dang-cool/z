package so.dang.cool.z.internal.combination;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
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

    public default <A> DoubleFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return (double d) -> next.apply(resolve().test(d));
    }

    public default <A> DoubleFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    public default <A> WithDoubleFunction<A> fusingBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithDoubleFunction.of(fuseBooleanFunction(next));
    }

    public default <A> WithDoubleFunction<A> fusing(BooleanFunction<A> next) {
        return fusingBooleanFunction(next);
    }

    /* DoublePredicate -> BooleanToDoubleFunction */

    public default DoubleUnaryOperator fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return (double d) -> next.applyAsDouble(resolve().test(d));
    }

    public default DoubleUnaryOperator fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    public default WithDoubleUnaryOperator fusingBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithDoubleUnaryOperator.of(fuseBooleanToDoubleFunction(next));
    }

    public default WithDoubleUnaryOperator fusing(
        BooleanToDoubleFunction next
    ) {
        return fusingBooleanToDoubleFunction(next);
    }

    /* DoublePredicate -> BooleanToIntFunction */

    public default DoubleToIntFunction fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return (double d) -> next.applyAsInt(resolve().test(d));
    }

    public default DoubleToIntFunction fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    public default WithDoubleToIntFunction fusingBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithDoubleToIntFunction.of(fuseBooleanToIntFunction(next));
    }

    public default WithDoubleToIntFunction fusing(BooleanToIntFunction next) {
        return fusingBooleanToIntFunction(next);
    }

    /* DoublePredicate -> BooleanToLongFunction */

    public default DoubleToLongFunction fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return (double d) -> next.applyAsLong(resolve().test(d));
    }

    public default DoubleToLongFunction fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    public default WithDoubleToLongFunction fusingBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithDoubleToLongFunction.of(fuseBooleanToLongFunction(next));
    }

    public default WithDoubleToLongFunction fusing(BooleanToLongFunction next) {
        return fusingBooleanToLongFunction(next);
    }

    /* DoublePredicate -> BooleanPredicate */

    public default DoublePredicate fuseDoublePredicate(BooleanPredicate next) {
        return (double d) -> next.test(resolve().test(d));
    }

    public default DoublePredicate fuse(BooleanPredicate next) {
        return fuseDoublePredicate(next);
    }

    public default WithDoublePredicate fusingDoublePredicate(
        BooleanPredicate next
    ) {
        return WithDoublePredicate.of(fuseDoublePredicate(next));
    }

    public default WithDoublePredicate fusing(BooleanPredicate next) {
        return fusingDoublePredicate(next);
    }

    /* DoublePredicate -> BooleanConsumer */

    public default DoubleConsumer fuseBooleanConsumer(BooleanConsumer next) {
        return (double d) -> next.accept(resolve().test(d));
    }

    public default DoubleConsumer fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }

    public default WithDoubleConsumer fusingBooleanConsumer(
        BooleanConsumer next
    ) {
        return WithDoubleConsumer.of(fuseBooleanConsumer(next));
    }

    public default WithDoubleConsumer fusing(BooleanConsumer next) {
        return fusingBooleanConsumer(next);
    }
}
