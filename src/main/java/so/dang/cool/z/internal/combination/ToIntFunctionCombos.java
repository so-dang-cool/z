package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface ToIntFunctionCombos<A> {
    ToIntFunction<A> resolve();

    /* ToIntFunction<A> -> IntFunction<B> */

    public default <B> WithFunction<A, B> fuseIntFunction(IntFunction<B> next) {
        return WithFunction.of((A a) -> next.apply(resolve().applyAsInt(a)));
    }

    public default <B> WithFunction<A, B> fuse(IntFunction<B> next) {
        return fuseIntFunction(next);
    }

    /* ToIntFunction -> IntToDoubleFunction */

    public default WithToDoubleFunction<A> fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithToDoubleFunction.of(
            (A a) -> next.applyAsDouble(resolve().applyAsInt(a))
        );
    }

    public default WithToDoubleFunction<A> fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    /* ToIntFunction -> IntToLongFunction */

    public default WithToLongFunction<A> fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithToLongFunction.of(
            (A a) -> next.applyAsLong(resolve().applyAsInt(a))
        );
    }

    public default WithToLongFunction<A> fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    /* ToIntFunction -> IntPredicate */

    public default WithPredicate<A> fuseIntPredicate(IntPredicate next) {
        return WithPredicate.of((A a) -> next.test(resolve().applyAsInt(a)));
    }

    public default WithPredicate<A> fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    /* ToIntFunction -> IntConsumer */

    public default WithConsumer<A> fuseIntConsumer(IntConsumer next) {
        return WithConsumer.of((A a) -> next.accept(resolve().applyAsInt(a)));
    }

    public default WithConsumer<A> fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    /* ToIntFunction -> IntUnaryOperator */

    public default WithToIntFunction<A> fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithToIntFunction.of(
            (A a) -> next.applyAsInt(resolve().applyAsInt(a))
        );
    }

    public default WithToIntFunction<A> fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
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
}
