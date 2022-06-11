package so.dang.cool.z.internal.combination;

import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanToLongFunctionCombos {
    BooleanToLongFunction resolve();

    /* BooleanToLongFunction -> LongFunction<A> */

    public default <A> WithBooleanFunction<A> fuseLongFunction(
        LongFunction<A> next
    ) {
        return WithBooleanFunction.of(
            (boolean b) -> next.apply(resolve().applyAsLong(b))
        );
    }

    public default <A> WithBooleanFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    /* BooleanToLongFunction -> LongToDoubleFunction */

    public default WithBooleanToDoubleFunction fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithBooleanToDoubleFunction.of(
            (boolean b) -> next.applyAsDouble(resolve().applyAsLong(b))
        );
    }

    public default WithBooleanToDoubleFunction fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    /* BooleanToLongFunction -> LongToIntFunction */

    public default WithBooleanToIntFunction fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithBooleanToIntFunction.of(
            (boolean b) -> next.applyAsInt(resolve().applyAsLong(b))
        );
    }

    public default WithBooleanToIntFunction fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    /* BooleanToLongFunction -> LongPredicate */

    public default WithBooleanPredicate fuseLongPredicate(LongPredicate next) {
        return WithBooleanPredicate.of(
            (boolean b) -> next.test(resolve().applyAsLong(b))
        );
    }

    public default WithBooleanPredicate fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    /* BooleanToLongFunction -> LongConsumer */

    public default WithBooleanConsumer fuseLongConsumer(LongConsumer next) {
        return WithBooleanConsumer.of(
            (boolean b) -> next.accept(resolve().applyAsLong(b))
        );
    }

    public default WithBooleanConsumer fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    /* BooleanToLongFunction -> LongUnaryOperator */

    public default WithBooleanToLongFunction fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithBooleanToLongFunction.of(
            (boolean b) -> next.applyAsLong(resolve().applyAsLong(b))
        );
    }

    public default WithBooleanToLongFunction fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    /* BooleanToLongFunction -> LongBinaryOperator */

    public default BooleanFunction<LongUnaryOperator> fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (boolean b) ->
            (long n) -> next.applyAsLong(resolve().applyAsLong(b), n);
    }

    public default BooleanFunction<LongUnaryOperator> fuse(
        LongBinaryOperator next
    ) {
        return fuseLongBinaryOperator(next);
    }
}
