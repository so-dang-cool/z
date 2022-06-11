package so.dang.cool.z.internal.combination;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongPredicate;
import so.dang.cool.z.internal.combination.Combine.WithLongToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;

interface LongToIntFunctionCombos {
    LongToIntFunction resolve();

    /* LongToIntFunction -> IntFunction<A> */

    public default <A> WithLongFunction<A> fuseIntFunction(
        IntFunction<A> next
    ) {
        return WithLongFunction.of(
            (long n) -> next.apply(resolve().applyAsInt(n))
        );
    }

    public default <A> WithLongFunction<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    /* LongToIntFunction -> IntToDoubleFunction */

    public default WithLongToDoubleFunction fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithLongToDoubleFunction.of(
            (long n) -> next.applyAsDouble(resolve().applyAsInt(n))
        );
    }

    public default WithLongToDoubleFunction fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    /* LongToDoubleFunction -> IntToLongFunction */

    public default WithLongUnaryOperator fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithLongUnaryOperator.of(
            (long n) -> next.applyAsLong(resolve().applyAsInt(n))
        );
    }

    public default WithLongUnaryOperator fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    /* LongToIntFunction -> IntPredicate */

    public default WithLongPredicate fuseIntPredicate(IntPredicate next) {
        return WithLongPredicate.of(
            (long n) -> next.test(resolve().applyAsInt(n))
        );
    }

    public default WithLongPredicate fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    /* LongToIntFunction -> IntConsumer */

    public default WithLongConsumer fuseIntConsumer(IntConsumer next) {
        return WithLongConsumer.of(
            (long n) -> next.accept(resolve().applyAsInt(n))
        );
    }

    public default WithLongConsumer fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    /* LongToIntFunction -> IntUnaryOperator */

    public default WithLongToIntFunction fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithLongToIntFunction.of(
            (long n) -> next.applyAsInt(resolve().applyAsInt(n))
        );
    }

    public default WithLongToIntFunction fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    /* LongToIntFunction -> IntBinaryOperator */

    public default LongFunction<IntUnaryOperator> fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return (long n) ->
            (int i) -> next.applyAsInt(resolve().applyAsInt(n), i);
    }

    public default LongFunction<IntUnaryOperator> fuse(IntBinaryOperator next) {
        return fuseIntBinaryOperator(next);
    }
}
