package so.dang.cool.z.internal.combination;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithIntBinaryOperator;

interface IntBinaryOperatorCombos {
    IntFunction<IntUnaryOperator> resolve();

    /* IntBinaryOperator -> IntFunction<A> */

    public default <A> IntFunction<IntFunction<A>> fuseIntFunction(
        IntFunction<A> next
    ) {
        return (int i1) ->
            (int i2) -> next.apply(resolve().apply(i1).applyAsInt(i2));
    }

    public default <A> IntFunction<IntFunction<A>> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    /* IntBinaryOperator -> IntToDoubleFunction */

    public default IntFunction<IntToDoubleFunction> fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return (int i1) ->
            (int i2) -> next.applyAsDouble(resolve().apply(i1).applyAsInt(i2));
    }

    public default IntFunction<IntToDoubleFunction> fuse(
        IntToDoubleFunction next
    ) {
        return fuseIntToDoubleFunction(next);
    }

    /* IntBinaryOperator -> IntToLongFunction */

    public default IntFunction<IntToLongFunction> fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return (int i1) ->
            (int i2) -> next.applyAsLong(resolve().apply(i1).applyAsInt(i2));
    }

    public default IntFunction<IntToLongFunction> fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    /* IntBinaryOperator -> IntPredicate */

    public default IntFunction<IntPredicate> fuseIntPredicate(
        IntPredicate next
    ) {
        return (int i1) ->
            (int i2) -> next.test(resolve().apply(i1).applyAsInt(i2));
    }

    public default IntFunction<IntPredicate> fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    /* IntBinaryOperator -> IntConsumer */

    public default IntFunction<IntConsumer> fuseIntConsumer(IntConsumer next) {
        return (int i1) ->
            (int i2) -> next.accept(resolve().apply(i1).applyAsInt(i2));
    }

    public default IntFunction<IntConsumer> fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    /* IntBinaryOperator -> IntUnaryOperator */

    public default WithIntBinaryOperator fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithIntBinaryOperator.of(
            (int i1) ->
                (int i2) -> next.applyAsInt(resolve().apply(i1).applyAsInt(i2))
        );
    }

    public default WithIntBinaryOperator fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    /* IntBinaryOperator -> IntBinaryOperator */

    public default IntFunction<IntFunction<IntUnaryOperator>> fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return (int i1) ->
            (int i2) ->
                (int d3) ->
                    next.applyAsInt(resolve().apply(i1).applyAsInt(i2), d3);
    }

    public default IntFunction<IntFunction<IntUnaryOperator>> fuse(
        IntBinaryOperator next
    ) {
        return fuseIntBinaryOperator(next);
    }
}
