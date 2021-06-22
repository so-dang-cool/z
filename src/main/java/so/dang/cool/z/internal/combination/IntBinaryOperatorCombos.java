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

    // TODO: Implement with currying
    // fusingIntFunction(IntFunction<A> next) { ... }

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

    // TODO: Implement with currying
    // fusingIntToDoubleFunction(IntToDoubleFunction next) { ... }

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

    // TODO: Implement with currying
    // fusingIntToLongFunction(IntToLongFunction next) { ... }

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

    // TODO: Implement with currying
    // fusingIntPredicate(IntPredicate next) { ... }

    /* IntBinaryOperator -> IntConsumer */

    public default IntFunction<IntConsumer> fuseIntConsumer(IntConsumer next) {
        return (int i1) ->
            (int i2) -> next.accept(resolve().apply(i1).applyAsInt(i2));
    }

    public default IntFunction<IntConsumer> fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    // TODO: Implement with currying
    // fusingIntConsumer(IntConsumer next) { ... }

    /* IntBinaryOperator -> IntUnaryOperator */

    public default IntFunction<IntUnaryOperator> fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return (int i1) ->
            (int i2) -> next.applyAsInt(resolve().apply(i1).applyAsInt(i2));
    }

    public default IntFunction<IntUnaryOperator> fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    public default WithIntBinaryOperator fusingIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithIntBinaryOperator.of(fuseIntUnaryOperator(next));
    }

    public default WithIntBinaryOperator fusing(IntUnaryOperator next) {
        return fusingIntUnaryOperator(next);
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

    // TODO: Implement with currying
    // fusingIntBinaryOperator(IntBinaryOperator next) { ... }

    public default IntFunction<IntUnaryOperator> fuseIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return (int i1) ->
            (int i2) ->
                next.applyAsInt(resolve().apply(i1).applyAsInt(i2), secondArg);
    }

    public default IntFunction<IntUnaryOperator> fuse(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fuseIntBinaryOperator(next, secondArg);
    }

    public default WithIntBinaryOperator fusingIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return WithIntBinaryOperator.of(fuseIntBinaryOperator(next, secondArg));
    }

    public default WithIntBinaryOperator fusing(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fusingIntBinaryOperator(next, secondArg);
    }
}
