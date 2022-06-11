package so.dang.cool.z.internal.combination;

import java.util.function.IntPredicate;
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

    public default <A> WithIntFunction<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithIntFunction.of((int i) -> next.apply(resolve().test(i)));
    }

    public default <A> WithIntFunction<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    /* IntPredicate -> BooleanToDoubleFunction */

    public default WithIntToDoubleFunction fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithIntToDoubleFunction.of(
            (int i) -> next.applyAsDouble(resolve().test(i))
        );
    }

    public default WithIntToDoubleFunction fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    /* IntPredicate -> BooleanToIntFunction */

    public default WithIntUnaryOperator fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithIntUnaryOperator.of(
            (int i) -> next.applyAsInt(resolve().test(i))
        );
    }

    public default WithIntUnaryOperator fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    /* IntPredicate -> BooleanToLongFunction */

    public default WithIntToLongFunction fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithIntToLongFunction.of(
            (int i) -> next.applyAsLong(resolve().test(i))
        );
    }

    public default WithIntToLongFunction fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    /* IntPredicate -> BooleanPredicate */

    public default WithIntPredicate fuseBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithIntPredicate.of((int i) -> next.test(resolve().test(i)));
    }

    public default WithIntPredicate fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    /* IntPredicate -> BooleanConsumer */

    public default WithIntConsumer fuseBooleanConsumer(BooleanConsumer next) {
        return WithIntConsumer.of((int i) -> next.accept(resolve().test(i)));
    }

    public default WithIntConsumer fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }
}
