package so.dang.cool.z.internal.combination;

import java.util.function.DoubleFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoublePredicate;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;

interface DoubleToLongFunctionCombos {
    DoubleToLongFunction resolve();

    /* DoubleToLongFunction -> LongFunction<A> */

    public default <A> WithDoubleFunction<A> fuseLongFunction(
        LongFunction<A> next
    ) {
        return WithDoubleFunction.of(
            (double d) -> next.apply(resolve().applyAsLong(d))
        );
    }

    public default <A> WithDoubleFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    /* DoubleToLongFunction -> LongToDoubleFunction */

    public default WithDoubleUnaryOperator fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithDoubleUnaryOperator.of(
            (double d) -> next.applyAsDouble(resolve().applyAsLong(d))
        );
    }

    public default WithDoubleUnaryOperator fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    /* DoubleToLongFunction -> LongToIntFunction */

    public default WithDoubleToIntFunction fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithDoubleToIntFunction.of(
            (double d) -> next.applyAsInt(resolve().applyAsLong(d))
        );
    }

    public default WithDoubleToIntFunction fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    /* DoubleToLongFunction -> LongPredicate */

    public default WithDoublePredicate fuseLongPredicate(LongPredicate next) {
        return WithDoublePredicate.of(
            (double d) -> next.test(resolve().applyAsLong(d))
        );
    }

    public default WithDoublePredicate fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    /* DoubleToLongFunction -> LongConsumer */

    public default WithDoubleConsumer fuseLongConsumer(LongConsumer next) {
        return WithDoubleConsumer.of(
            (double d) -> next.accept(resolve().applyAsLong(d))
        );
    }

    public default WithDoubleConsumer fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    /* DoubleToLongFunction -> LongUnaryOperator */

    public default WithDoubleToLongFunction fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithDoubleToLongFunction.of(
            (double d) -> next.applyAsLong(resolve().applyAsLong(d))
        );
    }

    public default WithDoubleToLongFunction fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    /* DoubleToLongFunction -> LongBinaryOperator */

    public default DoubleFunction<LongUnaryOperator> fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (double d) ->
            (long n) -> next.applyAsLong(resolve().applyAsLong(d), n);
    }

    public default DoubleFunction<LongUnaryOperator> fuse(
        LongBinaryOperator next
    ) {
        return fuseLongBinaryOperator(next);
    }
}
