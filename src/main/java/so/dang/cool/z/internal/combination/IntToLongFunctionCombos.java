package so.dang.cool.z.internal.combination;

import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

interface IntToLongFunctionCombos {
    IntToLongFunction resolve();

    /* IntToLongFunction -> LongFunction<A> */

    public default <A> IntFunction<A> fuseLongFunction(LongFunction<A> next) {
        return (int i) -> next.apply(resolve().applyAsLong(i));
    }

    public default <A> IntFunction<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    public default <A> WithIntFunction<A> fusingLongFunction(
        LongFunction<A> next
    ) {
        return WithIntFunction.of(fuseLongFunction(next));
    }

    public default <A> WithIntFunction<A> fusing(LongFunction<A> next) {
        return fusingLongFunction(next);
    }

    /* IntToLongFunction -> LongToDoubleFunction */

    public default IntToDoubleFunction fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return (int i) -> next.applyAsDouble(resolve().applyAsLong(i));
    }

    public default IntToDoubleFunction fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    public default WithIntToDoubleFunction fusingLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithIntToDoubleFunction.of(fuseLongToDoubleFunction(next));
    }

    public default WithIntToDoubleFunction fusing(LongToDoubleFunction next) {
        return fusingLongToDoubleFunction(next);
    }

    /* IntToLongFunction -> LongToIntFunction */

    public default IntUnaryOperator fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return (int i) -> next.applyAsInt(resolve().applyAsLong(i));
    }

    public default IntUnaryOperator fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    public default WithIntUnaryOperator fusingLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithIntUnaryOperator.of(fuseLongToIntFunction(next));
    }

    public default WithIntUnaryOperator fusing(LongToIntFunction next) {
        return fusingLongToIntFunction(next);
    }

    /* IntToLongFunction -> LongPredicate */

    public default IntPredicate fuseLongPredicate(LongPredicate next) {
        return (int i) -> next.test(resolve().applyAsLong(i));
    }

    public default IntPredicate fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    public default WithIntPredicate fusingLongPredicate(LongPredicate next) {
        return WithIntPredicate.of(fuseLongPredicate(next));
    }

    public default WithIntPredicate fusing(LongPredicate next) {
        return fusingLongPredicate(next);
    }

    /* IntToLongFunction -> LongConsumer */

    public default IntConsumer fuseLongConsumer(LongConsumer next) {
        return (int i) -> next.accept(resolve().applyAsLong(i));
    }

    public default IntConsumer fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    public default WithIntConsumer fusingLongConsumer(LongConsumer next) {
        return WithIntConsumer.of(fuseLongConsumer(next));
    }

    public default WithIntConsumer fusing(LongConsumer next) {
        return fusingLongConsumer(next);
    }

    /* IntToLongFunction -> LongUnaryOperator */

    public default IntToLongFunction fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return (int i) -> next.applyAsLong(resolve().applyAsLong(i));
    }

    public default IntToLongFunction fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    public default WithIntToLongFunction fusingLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithIntToLongFunction.of(fuseLongUnaryOperator(next));
    }

    public default WithIntToLongFunction fusing(LongUnaryOperator next) {
        return fusingLongUnaryOperator(next);
    }

    /* IntToLongFunction -> LongBinaryOperator */

    public default IntFunction<LongUnaryOperator> fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (int i) ->
            (long n) -> next.applyAsLong(resolve().applyAsLong(i), n);
    }

    public default IntFunction<LongUnaryOperator> fuse(
        LongBinaryOperator next
    ) {
        return fuseLongBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingLongBinaryOperator(LongBinaryOperator next) { ... }

    public default IntToLongFunction fuseLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return (int i) -> next.applyAsLong(resolve().applyAsLong(i), secondArg);
    }

    public default IntToLongFunction fuse(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fuseLongBinaryOperator(next, secondArg);
    }

    public default WithIntToLongFunction fusingLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return WithIntToLongFunction.of(
            fuseLongBinaryOperator(next, secondArg)
        );
    }

    public default WithIntToLongFunction fusing(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fusingLongBinaryOperator(next, secondArg);
    }
}
