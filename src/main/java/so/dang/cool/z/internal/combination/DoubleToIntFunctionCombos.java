package so.dang.cool.z.internal.combination;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
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

    public default <A> DoubleFunction<A> fuseIntFunction(IntFunction<A> next) {
        return (double d) -> next.apply(resolve().applyAsInt(d));
    }

    public default <A> DoubleFunction<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    public default <A> WithDoubleFunction<A> fusingIntFunction(
        IntFunction<A> next
    ) {
        return WithDoubleFunction.of(fuseIntFunction(next));
    }

    public default <A> WithDoubleFunction<A> fusing(IntFunction<A> next) {
        return fusingIntFunction(next);
    }

    /* DoubleToIntFunction -> IntToDoubleFunction */

    public default DoubleUnaryOperator fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return (double d) -> next.applyAsDouble(resolve().applyAsInt(d));
    }

    public default DoubleUnaryOperator fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    public default WithDoubleUnaryOperator fusingIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithDoubleUnaryOperator.of(fuseIntToDoubleFunction(next));
    }

    public default WithDoubleUnaryOperator fusing(IntToDoubleFunction next) {
        return fusingIntToDoubleFunction(next);
    }

    /* DoubleToLongFunction -> IntToLongFunction */

    public default DoubleToLongFunction fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return (double d) -> next.applyAsLong(resolve().applyAsInt(d));
    }

    public default DoubleToLongFunction fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    public default WithDoubleToLongFunction fusingIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithDoubleToLongFunction.of(fuseIntToLongFunction(next));
    }

    public default WithDoubleToLongFunction fusing(IntToLongFunction next) {
        return fusingIntToLongFunction(next);
    }

    /* DoubleToIntFunction -> IntPredicate */

    public default DoublePredicate fuseIntPredicate(IntPredicate next) {
        return (double d) -> next.test(resolve().applyAsInt(d));
    }

    public default DoublePredicate fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    public default WithDoublePredicate fusingIntPredicate(IntPredicate next) {
        return WithDoublePredicate.of(fuseIntPredicate(next));
    }

    public default WithDoublePredicate fusing(IntPredicate next) {
        return fusingIntPredicate(next);
    }

    /* DoubleToIntFunction -> IntConsumer */

    public default DoubleConsumer fuseIntConsumer(IntConsumer next) {
        return (double d) -> next.accept(resolve().applyAsInt(d));
    }

    public default DoubleConsumer fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    public default WithDoubleConsumer fusingIntConsumer(IntConsumer next) {
        return WithDoubleConsumer.of(fuseIntConsumer(next));
    }

    public default WithDoubleConsumer fusing(IntConsumer next) {
        return fusingIntConsumer(next);
    }

    /* DoubleToIntFunction -> IntUnaryOperator */

    public default DoubleToIntFunction fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return (double d) -> next.applyAsInt(resolve().applyAsInt(d));
    }

    public default DoubleToIntFunction fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    public default WithDoubleToIntFunction fusingIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithDoubleToIntFunction.of(fuseIntUnaryOperator(next));
    }

    public default WithDoubleToIntFunction fusing(IntUnaryOperator next) {
        return fusingIntUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingIntBinaryOperator(IntBinaryOperator next) { ... }

    public default DoubleToIntFunction fuseIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return (double d) ->
            next.applyAsInt(resolve().applyAsInt(d), secondArg);
    }

    public default DoubleToIntFunction fuse(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fuseIntBinaryOperator(next, secondArg);
    }

    public default WithDoubleToIntFunction fusingIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return WithDoubleToIntFunction.of(
            fuseIntBinaryOperator(next, secondArg)
        );
    }

    public default WithDoubleToIntFunction fusing(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fusingIntBinaryOperator(next, secondArg);
    }
}
