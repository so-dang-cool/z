package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongPredicate;
import so.dang.cool.z.internal.combination.Combine.WithLongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;

interface LongToDoubleFunctionCombos {
    LongToDoubleFunction resolve();

    /* LongToDoubleFunction -> DoubleFunction<A> */

    public default <A> WithLongFunction<A> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithLongFunction.of(
            (long n) -> next.apply(resolve().applyAsDouble(n))
        );
    }

    public default <A> WithLongFunction<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    /* LongToDoubleFunction -> DoubleToLongFunction */

    public default WithLongUnaryOperator fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithLongUnaryOperator.of(
            (long n) -> next.applyAsLong(resolve().applyAsDouble(n))
        );
    }

    public default WithLongUnaryOperator fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    /* LongToDoubleFunction -> DoubleToIntFunction */

    public default WithLongToIntFunction fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithLongToIntFunction.of(
            (long n) -> next.applyAsInt(resolve().applyAsDouble(n))
        );
    }

    public default WithLongToIntFunction fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    /* LongToDoubleFunction -> DoublePredicate */

    public default WithLongPredicate fuseDoublePredicate(DoublePredicate next) {
        return WithLongPredicate.of(
            (long n) -> next.test(resolve().applyAsDouble(n))
        );
    }

    public default WithLongPredicate fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    /* LongToDoubleFunction -> DoubleConsumer */

    public default WithLongConsumer fuseDoubleConsumer(DoubleConsumer next) {
        return WithLongConsumer.of(
            (long n) -> next.accept(resolve().applyAsDouble(n))
        );
    }

    public default WithLongConsumer fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    /* LongToDoubleFunction -> DoubleUnaryOperator */

    public default WithLongToDoubleFunction fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithLongToDoubleFunction.of(
            (long n) -> next.applyAsDouble(resolve().applyAsDouble(n))
        );
    }

    public default WithLongToDoubleFunction fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    /* LongToDoubleFunction -> DoubleBinaryOperator */

    public default LongFunction<DoubleUnaryOperator> fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return (long n) ->
            (double d) -> next.applyAsDouble(resolve().applyAsDouble(n), d);
    }

    public default LongFunction<DoubleUnaryOperator> fuse(
        DoubleBinaryOperator next
    ) {
        return fuseDoubleBinaryOperator(next);
    }
}
