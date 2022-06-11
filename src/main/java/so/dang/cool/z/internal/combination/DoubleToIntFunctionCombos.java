package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoublePredicate;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;

interface DoubleToIntFunctionCombos {
    DoubleToIntFunction resolve();

    /* DoubleToIntFunction -> IntFunction<A> */

    public default <A> WithDoubleFunction<A> fuseIntFunction(
        IntFunction<A> next
    ) {
        return WithDoubleFunction.of(
            (double d) -> next.apply(resolve().applyAsInt(d))
        );
    }

    public default <A> WithDoubleFunction<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    /* DoubleToIntFunction -> IntToDoubleFunction */

    public default WithDoubleUnaryOperator fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithDoubleUnaryOperator.of(
            (double d) -> next.applyAsDouble(resolve().applyAsInt(d))
        );
    }

    public default WithDoubleUnaryOperator fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    /* DoubleToLongFunction -> IntToLongFunction */

    public default WithDoubleToLongFunction fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithDoubleToLongFunction.of(
            (double d) -> next.applyAsLong(resolve().applyAsInt(d))
        );
    }

    public default WithDoubleToLongFunction fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    /* DoubleToIntFunction -> IntPredicate */

    public default WithDoublePredicate fuseIntPredicate(IntPredicate next) {
        return WithDoublePredicate.of(
            (double d) -> next.test(resolve().applyAsInt(d))
        );
    }

    public default WithDoublePredicate fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    /* DoubleToIntFunction -> IntConsumer */

    public default WithDoubleConsumer fuseIntConsumer(IntConsumer next) {
        return WithDoubleConsumer.of(
            (double d) -> next.accept(resolve().applyAsInt(d))
        );
    }

    public default WithDoubleConsumer fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    /* DoubleToIntFunction -> IntUnaryOperator */

    public default WithDoubleToIntFunction fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithDoubleToIntFunction.of(
            (double d) -> next.applyAsInt(resolve().applyAsInt(d))
        );
    }

    public default WithDoubleToIntFunction fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    /* DoubleToIntFunction -> IntBinaryOperator */

    public default DoubleFunction<IntUnaryOperator> fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return (double d) ->
            (int i) -> next.applyAsInt(resolve().applyAsInt(d), i);
    }

    public default DoubleFunction<IntUnaryOperator> fuse(
        IntBinaryOperator next
    ) {
        return fuseIntBinaryOperator(next);
    }
}
