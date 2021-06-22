package so.dang.cool.z.internal.combination;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithIntBinaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

interface IntUnaryOperatorCombos {
    IntUnaryOperator resolve();

    /* IntUnaryOperator -> IntFunction<A> */

    public default <A> IntFunction<A> fuseIntFunction(IntFunction<A> next) {
        return (int i) -> next.apply(resolve().applyAsInt(i));
    }

    public default <A> IntFunction<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    public default <A> WithIntFunction<A> fusingIntFunction(
        IntFunction<A> next
    ) {
        return WithIntFunction.of(fuseIntFunction(next));
    }

    public default <A> WithIntFunction<A> fusing(IntFunction<A> next) {
        return fusingIntFunction(next);
    }

    /* IntUnaryOperator -> IntToDoubleFunction */

    public default IntToDoubleFunction fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return (int i) -> next.applyAsDouble(resolve().applyAsInt(i));
    }

    public default IntToDoubleFunction fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    public default WithIntToDoubleFunction fusingIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithIntToDoubleFunction.of(fuseIntToDoubleFunction(next));
    }

    public default WithIntToDoubleFunction fusing(IntToDoubleFunction next) {
        return fusingIntToDoubleFunction(next);
    }

    /* IntUnaryOperator -> IntToLongFunction */

    public default IntToLongFunction fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return (int i) -> next.applyAsLong(resolve().applyAsInt(i));
    }

    public default IntToLongFunction fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    public default WithIntToLongFunction fusingIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithIntToLongFunction.of(fuseIntToLongFunction(next));
    }

    public default WithIntToLongFunction fusing(IntToLongFunction next) {
        return fusingIntToLongFunction(next);
    }

    /* IntUnaryOperator -> IntPredicate */

    public default IntPredicate fuseIntPredicate(IntPredicate next) {
        return (int i) -> next.test(resolve().applyAsInt(i));
    }

    public default IntPredicate fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    public default WithIntPredicate fusingIntPredicate(IntPredicate next) {
        return WithIntPredicate.of(fuseIntPredicate(next));
    }

    public default WithIntPredicate fusing(IntPredicate next) {
        return fusingIntPredicate(next);
    }

    /* IntUnaryOperator -> IntConsumer */

    public default IntConsumer fuseIntConsumer(IntConsumer next) {
        return (int i) -> next.accept(resolve().applyAsInt(i));
    }

    public default IntConsumer fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    public default WithIntConsumer fusingIntConsumer(IntConsumer next) {
        return WithIntConsumer.of(fuseIntConsumer(next));
    }

    public default WithIntConsumer fusing(IntConsumer next) {
        return fusingIntConsumer(next);
    }

    /* IntUnaryOperator -> IntUnaryOperator */

    public default IntUnaryOperator fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return (int i) -> next.applyAsInt(resolve().applyAsInt(i));
    }

    public default IntUnaryOperator fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    public default WithIntUnaryOperator fusingIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithIntUnaryOperator.of(fuseIntUnaryOperator(next));
    }

    public default WithIntUnaryOperator fusing(IntUnaryOperator next) {
        return fusingIntUnaryOperator(next);
    }

    /* IntUnaryOperator -> IntBinaryOperator */

    public default IntFunction<IntUnaryOperator> fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return (int d1) ->
            (int d2) -> next.applyAsInt(resolve().applyAsInt(d1), d2);
    }

    public default IntFunction<IntUnaryOperator> fuse(IntBinaryOperator next) {
        return fuseIntBinaryOperator(next);
    }

    public default WithIntBinaryOperator fusingIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return WithIntBinaryOperator.of(fuseIntBinaryOperator(next));
    }

    public default WithIntBinaryOperator fusing(IntBinaryOperator next) {
        return fusingIntBinaryOperator(next);
    }

    public default IntUnaryOperator fuseIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return (int i) -> next.applyAsInt(resolve().applyAsInt(i), secondArg);
    }

    public default IntUnaryOperator fuse(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fuseIntBinaryOperator(next, secondArg);
    }

    public default WithIntUnaryOperator fusingIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return WithIntUnaryOperator.of(fuseIntBinaryOperator(next, secondArg));
    }

    public default WithIntUnaryOperator fusing(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fusingIntBinaryOperator(next, secondArg);
    }
}
