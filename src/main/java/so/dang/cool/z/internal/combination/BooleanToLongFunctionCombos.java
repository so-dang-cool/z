package so.dang.cool.z.internal.combination;

import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
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

    public default <A> BooleanFunction<A> fuseLongFunction(
        LongFunction<A> next
    ) {
        return (boolean b) -> next.apply(resolve().applyAsLong(b));
    }

    public default <A> BooleanFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    public default <A> WithBooleanFunction<A> fusingLongFunction(
        LongFunction<A> next
    ) {
        return WithBooleanFunction.of(fuseLongFunction(next));
    }

    public default <A> WithBooleanFunction<A> fusing(LongFunction<A> next) {
        return fusingLongFunction(next);
    }

    /* BooleanToLongFunction -> LongToDoubleFunction */

    public default BooleanToDoubleFunction fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return (boolean b) -> next.applyAsDouble(resolve().applyAsLong(b));
    }

    public default BooleanToDoubleFunction fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    public default WithBooleanToDoubleFunction fusingLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithBooleanToDoubleFunction.of(fuseLongToDoubleFunction(next));
    }

    public default WithBooleanToDoubleFunction fusing(
        LongToDoubleFunction next
    ) {
        return fusingLongToDoubleFunction(next);
    }

    /* BooleanToLongFunction -> LongToIntFunction */

    public default BooleanToIntFunction fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return (boolean b) -> next.applyAsInt(resolve().applyAsLong(b));
    }

    public default BooleanToIntFunction fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    public default WithBooleanToIntFunction fusingLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithBooleanToIntFunction.of(fuseLongToIntFunction(next));
    }

    public default WithBooleanToIntFunction fusing(LongToIntFunction next) {
        return fusingLongToIntFunction(next);
    }

    /* BooleanToLongFunction -> LongPredicate */

    public default BooleanPredicate fuseLongPredicate(LongPredicate next) {
        return (boolean b) -> next.test(resolve().applyAsLong(b));
    }

    public default BooleanPredicate fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    public default WithBooleanPredicate fusingLongPredicate(
        LongPredicate next
    ) {
        return WithBooleanPredicate.of(fuseLongPredicate(next));
    }

    public default WithBooleanPredicate fusing(LongPredicate next) {
        return fusingLongPredicate(next);
    }

    /* BooleanToLongFunction -> LongConsumer */

    public default BooleanConsumer fuseLongConsumer(LongConsumer next) {
        return (boolean b) -> next.accept(resolve().applyAsLong(b));
    }

    public default BooleanConsumer fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    public default WithBooleanConsumer fusingLongConsumer(LongConsumer next) {
        return WithBooleanConsumer.of(fuseLongConsumer(next));
    }

    public default WithBooleanConsumer fusing(LongConsumer next) {
        return fusingLongConsumer(next);
    }

    /* BooleanToLongFunction -> LongUnaryOperator */

    public default BooleanToLongFunction fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return (boolean b) -> next.applyAsLong(resolve().applyAsLong(b));
    }

    public default BooleanToLongFunction fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    public default WithBooleanToLongFunction fusingLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithBooleanToLongFunction.of(fuseLongUnaryOperator(next));
    }

    public default WithBooleanToLongFunction fusing(LongUnaryOperator next) {
        return fusingLongUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingLongBinaryOperator(LongBinaryOperator next) { ... }

    public default BooleanToLongFunction fuseLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return (boolean b) ->
            next.applyAsLong(resolve().applyAsLong(b), secondArg);
    }

    public default BooleanToLongFunction fuse(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fuseLongBinaryOperator(next, secondArg);
    }

    public default WithBooleanToLongFunction fusingLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return WithBooleanToLongFunction.of(
            fuseLongBinaryOperator(next, secondArg)
        );
    }

    public default WithBooleanToLongFunction fusing(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fusingLongBinaryOperator(next, secondArg);
    }
}
