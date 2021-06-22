package so.dang.cool.z.internal.combination;

import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongPredicate;
import so.dang.cool.z.internal.combination.Combine.WithLongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;

interface LongUnaryOperatorCombos {
    LongUnaryOperator resolve();

    /* LongUnaryOperator -> LongFunction<A> */

    public default <A> LongFunction<A> fuseLongFunction(LongFunction<A> next) {
        return (long n) -> next.apply(resolve().applyAsLong(n));
    }

    public default <A> LongFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    public default <A> WithLongFunction<A> fusingLongFunction(
        LongFunction<A> next
    ) {
        return WithLongFunction.of(fuseLongFunction(next));
    }

    public default <A> WithLongFunction<A> fusing(LongFunction<A> next) {
        return fusingLongFunction(next);
    }

    /* LongUnaryOperator -> LongToIntFunction */

    public default LongToIntFunction fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return (long n) -> next.applyAsInt(resolve().applyAsLong(n));
    }

    public default LongToIntFunction fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    public default WithLongToIntFunction fusingLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithLongToIntFunction.of(fuseLongToIntFunction(next));
    }

    public default WithLongToIntFunction fusing(LongToIntFunction next) {
        return fusingLongToIntFunction(next);
    }

    /* LongUnaryOperator -> LongToDoubleFunction */

    public default LongToDoubleFunction fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return (long n) -> next.applyAsDouble(resolve().applyAsLong(n));
    }

    public default LongToDoubleFunction fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    public default WithLongToDoubleFunction fusingLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithLongToDoubleFunction.of(fuseLongToDoubleFunction(next));
    }

    public default WithLongToDoubleFunction fusing(LongToDoubleFunction next) {
        return fusingLongToDoubleFunction(next);
    }

    /* LongUnaryOperator -> LongPredicate */

    public default LongPredicate fuseLongPredicate(LongPredicate next) {
        return (long n) -> next.test(resolve().applyAsLong(n));
    }

    public default LongPredicate fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    public default WithLongPredicate fusingLongPredicate(LongPredicate next) {
        return WithLongPredicate.of(fuseLongPredicate(next));
    }

    public default WithLongPredicate fusing(LongPredicate next) {
        return fusingLongPredicate(next);
    }

    /* LongUnaryOperator -> LongConsumer */

    public default LongConsumer fuseLongConsumer(LongConsumer next) {
        return (long n) -> next.accept(resolve().applyAsLong(n));
    }

    public default LongConsumer fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    public default WithLongConsumer fusingLongConsumer(LongConsumer next) {
        return WithLongConsumer.of(fuseLongConsumer(next));
    }

    public default WithLongConsumer fusing(LongConsumer next) {
        return fusingLongConsumer(next);
    }

    /* LongUnaryOperator -> LongUnaryOperator */

    public default LongUnaryOperator fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return (long n) -> next.applyAsLong(resolve().applyAsLong(n));
    }

    public default LongUnaryOperator fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    public default WithLongUnaryOperator fusingLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithLongUnaryOperator.of(fuseLongUnaryOperator(next));
    }

    public default WithLongUnaryOperator fusing(LongUnaryOperator next) {
        return fusingLongUnaryOperator(next);
    }

    /* LongUnaryOperator -> LongBinaryOperator */

    public default LongFunction<LongUnaryOperator> fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (long d1) ->
            (long d2) -> next.applyAsLong(resolve().applyAsLong(d1), d2);
    }

    public default LongFunction<LongUnaryOperator> fuse(
        LongBinaryOperator next
    ) {
        return fuseLongBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingLongBinaryOperator(LongBinaryOperator next) { ... }

    public default LongUnaryOperator fuseLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return (long n) ->
            next.applyAsLong(resolve().applyAsLong(n), secondArg);
    }

    public default LongUnaryOperator fuse(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fuseLongBinaryOperator(next, secondArg);
    }

    public default WithLongUnaryOperator fusingLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return WithLongUnaryOperator.of(
            fuseLongBinaryOperator(next, secondArg)
        );
    }

    public default WithLongUnaryOperator fusing(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fusingLongBinaryOperator(next, secondArg);
    }
}
