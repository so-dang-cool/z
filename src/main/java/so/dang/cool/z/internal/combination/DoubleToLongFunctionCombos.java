package so.dang.cool.z.internal.combination;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
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

    public default <A> DoubleFunction<A> fuseLongFunction(
        LongFunction<A> next
    ) {
        return (double d) -> next.apply(resolve().applyAsLong(d));
    }

    public default <A> DoubleFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    public default <A> WithDoubleFunction<A> fusingLongFunction(
        LongFunction<A> next
    ) {
        return WithDoubleFunction.of(fuseLongFunction(next));
    }

    public default <A> WithDoubleFunction<A> fusing(LongFunction<A> next) {
        return fusingLongFunction(next);
    }

    /* DoubleToLongFunction -> LongToDoubleFunction */

    public default DoubleUnaryOperator fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return (double d) -> next.applyAsDouble(resolve().applyAsLong(d));
    }

    public default DoubleUnaryOperator fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    public default WithDoubleUnaryOperator fusingLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithDoubleUnaryOperator.of(fuseLongToDoubleFunction(next));
    }

    public default WithDoubleUnaryOperator fusing(LongToDoubleFunction next) {
        return fusingLongToDoubleFunction(next);
    }

    /* DoubleToLongFunction -> LongToIntFunction */

    public default DoubleToIntFunction fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return (double d) -> next.applyAsInt(resolve().applyAsLong(d));
    }

    public default DoubleToIntFunction fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    public default WithDoubleToIntFunction fusingLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithDoubleToIntFunction.of(fuseLongToIntFunction(next));
    }

    public default WithDoubleToIntFunction fusing(LongToIntFunction next) {
        return fusingLongToIntFunction(next);
    }

    /* DoubleToLongFunction -> LongPredicate */

    public default DoublePredicate fuseLongPredicate(LongPredicate next) {
        return (double d) -> next.test(resolve().applyAsLong(d));
    }

    public default DoublePredicate fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    public default WithDoublePredicate fusingLongPredicate(LongPredicate next) {
        return WithDoublePredicate.of(fuseLongPredicate(next));
    }

    public default WithDoublePredicate fusing(LongPredicate next) {
        return fusingLongPredicate(next);
    }

    /* DoubleToLongFunction -> LongConsumer */

    public default DoubleConsumer fuseLongConsumer(LongConsumer next) {
        return (double d) -> next.accept(resolve().applyAsLong(d));
    }

    public default DoubleConsumer fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    public default WithDoubleConsumer fusingLongConsumer(LongConsumer next) {
        return WithDoubleConsumer.of(fuseLongConsumer(next));
    }

    public default WithDoubleConsumer fusing(LongConsumer next) {
        return fusingLongConsumer(next);
    }

    /* DoubleToLongFunction -> LongUnaryOperator */

    public default DoubleToLongFunction fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return (double d) -> next.applyAsLong(resolve().applyAsLong(d));
    }

    public default DoubleToLongFunction fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    public default WithDoubleToLongFunction fusingLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithDoubleToLongFunction.of(fuseLongUnaryOperator(next));
    }

    public default WithDoubleToLongFunction fusing(LongUnaryOperator next) {
        return fusingLongUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingLongBinaryOperator(LongBinaryOperator next) { ... }

    public default DoubleToLongFunction fuseLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return (double d) ->
            next.applyAsLong(resolve().applyAsLong(d), secondArg);
    }

    public default DoubleToLongFunction fuse(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fuseLongBinaryOperator(next, secondArg);
    }

    public default WithDoubleToLongFunction fusingLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return WithDoubleToLongFunction.of(
            fuseLongBinaryOperator(next, secondArg)
        );
    }

    public default WithDoubleToLongFunction fusing(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fusingLongBinaryOperator(next, secondArg);
    }
}
