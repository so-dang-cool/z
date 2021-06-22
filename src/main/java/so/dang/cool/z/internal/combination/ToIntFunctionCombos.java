package so.dang.cool.z.internal.combination;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface ToIntFunctionCombos<A> {
    ToIntFunction<A> resolve();

    /* ToIntFunction<A> -> IntFunction<B> */

    public default <B> Function<A, B> fuseIntFunction(IntFunction<B> next) {
        return (A a) -> next.apply(resolve().applyAsInt(a));
    }

    public default <B> Function<A, B> fuse(IntFunction<B> next) {
        return fuseIntFunction(next);
    }

    public default <B> WithFunction<A, B> fusingIntFunction(
        IntFunction<B> next
    ) {
        return WithFunction.of(fuseIntFunction(next));
    }

    public default <B> WithFunction<A, B> fusing(IntFunction<B> next) {
        return fusingIntFunction(next);
    }

    /* ToIntFunction -> IntToDoubleFunction */

    public default ToDoubleFunction<A> fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return (A a) -> next.applyAsDouble(resolve().applyAsInt(a));
    }

    public default ToDoubleFunction<A> fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    public default WithToDoubleFunction<A> fusingIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithToDoubleFunction.of(fuseIntToDoubleFunction(next));
    }

    public default WithToDoubleFunction<A> fusing(IntToDoubleFunction next) {
        return fusingIntToDoubleFunction(next);
    }

    /* ToIntFunction -> IntToLongFunction */

    public default ToLongFunction<A> fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return (A a) -> next.applyAsLong(resolve().applyAsInt(a));
    }

    public default ToLongFunction<A> fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    public default WithToLongFunction<A> fusingIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithToLongFunction.of(fuseIntToLongFunction(next));
    }

    public default WithToLongFunction<A> fusing(IntToLongFunction next) {
        return fusingIntToLongFunction(next);
    }

    /* ToIntFunction -> IntPredicate */

    public default Predicate<A> fuseIntPredicate(IntPredicate next) {
        return (A a) -> next.test(resolve().applyAsInt(a));
    }

    public default Predicate<A> fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    public default WithPredicate<A> fusingIntPredicate(IntPredicate next) {
        return WithPredicate.of(fuseIntPredicate(next));
    }

    public default WithPredicate<A> fusing(IntPredicate next) {
        return fusingIntPredicate(next);
    }

    /* ToIntFunction -> IntConsumer */

    public default Consumer<A> fuseIntConsumer(IntConsumer next) {
        return (A a) -> next.accept(resolve().applyAsInt(a));
    }

    public default Consumer<A> fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    public default WithConsumer<A> fusingIntConsumer(IntConsumer next) {
        return WithConsumer.of(fuseIntConsumer(next));
    }

    public default WithConsumer<A> fusing(IntConsumer next) {
        return fusingIntConsumer(next);
    }

    /* ToIntFunction -> IntUnaryOperator */

    public default ToIntFunction<A> fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return (A a) -> next.applyAsInt(resolve().applyAsInt(a));
    }

    public default ToIntFunction<A> fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    public default WithToIntFunction<A> fusingIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithToIntFunction.of(fuseIntUnaryOperator(next));
    }

    public default WithToIntFunction<A> fusing(IntUnaryOperator next) {
        return fusingIntUnaryOperator(next);
    }

    /* ToIntFunction -> IntBinaryOperator */

    public default Function<A, IntUnaryOperator> fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return (A a) -> (int i) -> next.applyAsInt(resolve().applyAsInt(a), i);
    }

    public default Function<A, IntUnaryOperator> fuse(IntBinaryOperator next) {
        return fuseIntBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingIntBinaryOperator(IntBinaryOperator next) { ... }

    public default ToIntFunction<A> fuseIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return (A a) -> next.applyAsInt(resolve().applyAsInt(a), secondArg);
    }

    public default ToIntFunction<A> fuse(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fuseIntBinaryOperator(next, secondArg);
    }

    public default WithToIntFunction<A> fusingIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return WithToIntFunction.of(fuseIntBinaryOperator(next, secondArg));
    }

    public default WithToIntFunction<A> fusing(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fusingIntBinaryOperator(next, secondArg);
    }
}
