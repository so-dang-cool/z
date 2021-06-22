package so.dang.cool.z.internal.combination;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanToIntFunctionCombos {
    BooleanToIntFunction resolve();

    /* BooleanToIntFunction<A> -> IntFunction<B> */

    public default <A> BooleanFunction<A> fuseIntFunction(IntFunction<A> next) {
        return (boolean b) -> next.apply(resolve().applyAsInt(b));
    }

    public default <A> BooleanFunction<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    public default <A> WithBooleanFunction<A> fusingIntFunction(
        IntFunction<A> next
    ) {
        return WithBooleanFunction.of(fuseIntFunction(next));
    }

    public default <A> WithBooleanFunction<A> fusing(IntFunction<A> next) {
        return fusingIntFunction(next);
    }

    /* BooleanToIntFunction<A> -> IntToDoubleFunction<B> */

    public default BooleanToDoubleFunction fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return (boolean b) -> next.applyAsDouble(resolve().applyAsInt(b));
    }

    public default BooleanToDoubleFunction fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    public default WithBooleanToDoubleFunction fusingIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithBooleanToDoubleFunction.of(fuseIntToDoubleFunction(next));
    }

    public default WithBooleanToDoubleFunction fusing(
        IntToDoubleFunction next
    ) {
        return fusingIntToDoubleFunction(next);
    }

    /* BooleanToIntFunction<A> -> IntToLongFunction<B> */

    public default BooleanToLongFunction fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return (boolean b) -> next.applyAsLong(resolve().applyAsInt(b));
    }

    public default BooleanToLongFunction fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    public default WithBooleanToLongFunction fusingIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithBooleanToLongFunction.of(fuseIntToLongFunction(next));
    }

    public default WithBooleanToLongFunction fusing(IntToLongFunction next) {
        return fusingIntToLongFunction(next);
    }

    /* BooleanToIntFunction<A> -> IntPredicate<B> */

    public default BooleanPredicate fuseIntPredicate(IntPredicate next) {
        return (boolean b) -> next.test(resolve().applyAsInt(b));
    }

    public default BooleanPredicate fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    public default WithBooleanPredicate fusingIntPredicate(IntPredicate next) {
        return WithBooleanPredicate.of(fuseIntPredicate(next));
    }

    public default WithBooleanPredicate fusing(IntPredicate next) {
        return fusingIntPredicate(next);
    }

    /* BooleanToIntFunction<A> -> IntConsumer<B> */

    public default BooleanConsumer fuseIntConsumer(IntConsumer next) {
        return (boolean b) -> next.accept(resolve().applyAsInt(b));
    }

    public default BooleanConsumer fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    public default WithBooleanConsumer fusingIntConsumer(IntConsumer next) {
        return WithBooleanConsumer.of(fuseIntConsumer(next));
    }

    public default WithBooleanConsumer fusing(IntConsumer next) {
        return fusingIntConsumer(next);
    }

    /* BooleanToIntFunction<A> -> IntUnaryOperator<B> */

    public default BooleanToIntFunction fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return (boolean b) -> next.applyAsInt(resolve().applyAsInt(b));
    }

    public default BooleanToIntFunction fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    public default WithBooleanToIntFunction fusingIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithBooleanToIntFunction.of(fuseIntUnaryOperator(next));
    }

    public default WithBooleanToIntFunction fusing(IntUnaryOperator next) {
        return fusingIntUnaryOperator(next);
    }

    /* BooleanToIntFunction -> IntBinaryOperator */

    public default BooleanFunction<IntUnaryOperator> fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return (boolean b) ->
            (int i) -> next.applyAsInt(resolve().applyAsInt(b), i);
    }

    public default BooleanFunction<IntUnaryOperator> fuse(
        IntBinaryOperator next
    ) {
        return fuseIntBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingIntBinaryOperator(IntBinaryOperator next) { ... }

    public default BooleanToIntFunction fuseIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return (boolean b) ->
            next.applyAsInt(resolve().applyAsInt(b), secondArg);
    }

    public default BooleanToIntFunction fuse(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fuseIntBinaryOperator(next, secondArg);
    }

    public default WithBooleanToIntFunction fusingIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return WithBooleanToIntFunction.of(
            fuseIntBinaryOperator(next, secondArg)
        );
    }

    public default WithBooleanToIntFunction fusing(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fusingIntBinaryOperator(next, secondArg);
    }
}
