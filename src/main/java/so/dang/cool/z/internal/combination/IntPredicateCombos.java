package so.dang.cool.z.internal.combination;

import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

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

    /* IntPredicate -> BooleanToDoubleFunction */

    public default IntToDoubleFunction fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return (int i) -> next.applyAsDouble(resolve().test(i));
    }

    public default IntToDoubleFunction fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    public default WithIntToDoubleFunction fusingBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithIntToDoubleFunction.of(fuseBooleanToDoubleFunction(next));
    }

    public default WithIntToDoubleFunction fusing(
        BooleanToDoubleFunction next
    ) {
        return fusingBooleanToDoubleFunction(next);
    }

    /* IntPredicate -> BooleanToIntFunction */

    public default IntUnaryOperator fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return (int i) -> next.applyAsInt(resolve().test(i));
    }

    public default IntUnaryOperator fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    public default WithIntUnaryOperator fusingBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithIntUnaryOperator.of(fuseBooleanToIntFunction(next));
    }

    public default WithIntUnaryOperator fusing(BooleanToIntFunction next) {
        return fusingBooleanToIntFunction(next);
    }

    /* IntPredicate -> BooleanToLongFunction */

    public default IntToLongFunction fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return (int i) -> next.applyAsLong(resolve().test(i));
    }

    public default IntToLongFunction fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    public default WithIntToLongFunction fusingBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithIntToLongFunction.of(fuseBooleanToLongFunction(next));
    }

    public default WithIntToLongFunction fusing(BooleanToLongFunction next) {
        return fusingBooleanToLongFunction(next);
    }

    /* IntPredicate -> BooleanPredicate */

    public default IntPredicate fuseIntPredicate(BooleanPredicate next) {
        return (int i) -> next.test(resolve().test(i));
    }

    public default IntPredicate fuse(BooleanPredicate next) {
        return fuseIntPredicate(next);
    }

    public default WithIntPredicate fusingIntPredicate(BooleanPredicate next) {
        return WithIntPredicate.of(fuseIntPredicate(next));
    }

    public default WithIntPredicate fusing(BooleanPredicate next) {
        return fusingIntPredicate(next);
    }

    /* IntPredicate -> BooleanConsumer */

    public default IntConsumer fuseBooleanConsumer(BooleanConsumer next) {
        return (int i) -> next.accept(resolve().test(i));
    }

    public default IntConsumer fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }

    public default WithIntConsumer fusingBooleanConsumer(BooleanConsumer next) {
        return WithIntConsumer.of(fuseBooleanConsumer(next));
    }

    public default WithIntConsumer fusing(BooleanConsumer next) {
        return fusingBooleanConsumer(next);
    }
}
