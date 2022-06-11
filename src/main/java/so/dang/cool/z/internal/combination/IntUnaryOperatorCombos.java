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

    public default <A> WithIntFunction<A> fuseIntFunction(IntFunction<A> next) {
        return WithIntFunction.of(
            (int i) -> next.apply(resolve().applyAsInt(i))
        );
    }

    public default <A> WithIntFunction<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    /* IntUnaryOperator -> IntToDoubleFunction */

    public default WithIntToDoubleFunction fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithIntToDoubleFunction.of(
            (int i) -> next.applyAsDouble(resolve().applyAsInt(i))
        );
    }

    public default WithIntToDoubleFunction fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    /* IntUnaryOperator -> IntToLongFunction */

    public default WithIntToLongFunction fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithIntToLongFunction.of(
            (int i) -> next.applyAsLong(resolve().applyAsInt(i))
        );
    }

    public default WithIntToLongFunction fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    /* IntUnaryOperator -> IntPredicate */

    public default WithIntPredicate fuseIntPredicate(IntPredicate next) {
        return WithIntPredicate.of(
            (int i) -> next.test(resolve().applyAsInt(i))
        );
    }

    public default WithIntPredicate fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    /* IntUnaryOperator -> IntConsumer */

    public default WithIntConsumer fuseIntConsumer(IntConsumer next) {
        return WithIntConsumer.of(
            (int i) -> next.accept(resolve().applyAsInt(i))
        );
    }

    public default WithIntConsumer fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    /* IntUnaryOperator -> IntUnaryOperator */

    public default WithIntUnaryOperator fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithIntUnaryOperator.of(
            (int i) -> next.applyAsInt(resolve().applyAsInt(i))
        );
    }

    public default WithIntUnaryOperator fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    /* IntUnaryOperator -> IntBinaryOperator */

    public default WithIntBinaryOperator fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return WithIntBinaryOperator.of(
            (int d1) ->
                (int d2) -> next.applyAsInt(resolve().applyAsInt(d1), d2)
        );
    }

    public default WithIntBinaryOperator fuse(IntBinaryOperator next) {
        return fuseIntBinaryOperator(next);
    }
}
