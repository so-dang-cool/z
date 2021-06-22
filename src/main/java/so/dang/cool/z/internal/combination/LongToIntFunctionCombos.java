package so.dang.cool.z.internal.combination;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
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

interface LongToIntFunctionCombos {
    LongToIntFunction resolve();

    /* LongToIntFunction -> IntFunction<A> */

    public default <A> LongFunction<A> fuseFunction(IntFunction<A> next) {
        return (long n) -> next.apply(resolve().applyAsInt(n));
    }

    public default <A> LongFunction<A> fuse(IntFunction<A> next) {
        return fuseFunction(next);
    }

    public default <A> WithLongFunction<A> fusingFunction(IntFunction<A> next) {
        return WithLongFunction.of(fuseFunction(next));
    }

    public default <A> WithLongFunction<A> fusing(IntFunction<A> next) {
        return fusingFunction(next);
    }

    /* LongToIntFunction -> IntToDoubleFunction */

    public default LongToDoubleFunction fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return (long n) -> next.applyAsDouble(resolve().applyAsInt(n));
    }

    public default LongToDoubleFunction fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    public default WithLongToDoubleFunction fusingIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithLongToDoubleFunction.of(fuseIntToDoubleFunction(next));
    }

    public default WithLongToDoubleFunction fusing(IntToDoubleFunction next) {
        return fusingIntToDoubleFunction(next);
    }

    /* LongToDoubleFunction -> IntToLongFunction */

    public default LongUnaryOperator fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return (long n) -> next.applyAsLong(resolve().applyAsInt(n));
    }

    public default LongUnaryOperator fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    public default WithLongUnaryOperator fusingIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithLongUnaryOperator.of(fuseIntToLongFunction(next));
    }

    public default WithLongUnaryOperator fusing(IntToLongFunction next) {
        return fusingIntToLongFunction(next);
    }

    /* LongToIntFunction -> IntPredicate */

    public default LongPredicate fuseIntPredicate(IntPredicate next) {
        return (long n) -> next.test(resolve().applyAsInt(n));
    }

    public default LongPredicate fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    public default WithLongPredicate fusingIntPredicate(IntPredicate next) {
        return WithLongPredicate.of(fuseIntPredicate(next));
    }

    public default WithLongPredicate fusing(IntPredicate next) {
        return fusingIntPredicate(next);
    }

    /* LongToIntFunction -> IntConsumer */

    public default LongConsumer fuseIntConsumer(IntConsumer next) {
        return (long n) -> next.accept(resolve().applyAsInt(n));
    }

    public default LongConsumer fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    public default WithLongConsumer fusingIntConsumer(IntConsumer next) {
        return WithLongConsumer.of(fuseIntConsumer(next));
    }

    public default WithLongConsumer fusing(IntConsumer next) {
        return fusingIntConsumer(next);
    }

    /* LongToIntFunction -> IntUnaryOperator */

    public default LongToIntFunction fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return (long n) -> next.applyAsInt(resolve().applyAsInt(n));
    }

    public default LongToIntFunction fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    public default WithLongToIntFunction fusingIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithLongToIntFunction.of(fuseIntUnaryOperator(next));
    }

    public default WithLongToIntFunction fusing(IntUnaryOperator next) {
        return fusingIntUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingIntBinaryOperator(IntBinaryOperator next) { ... }

    public default LongToIntFunction fuseIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return (long n) -> next.applyAsInt(resolve().applyAsInt(n), secondArg);
    }

    public default LongToIntFunction fuse(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fuseIntBinaryOperator(next, secondArg);
    }

    public default WithLongToIntFunction fusingIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return WithLongToIntFunction.of(fuseIntBinaryOperator(next, secondArg));
    }

    public default WithLongToIntFunction fusing(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fusingIntBinaryOperator(next, secondArg);
    }
}
