package so.dang.cool.z.internal.combination;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanToIntFunctionCombos {
    BooleanToIntFunction resolve();

    /* BooleanToIntFunction<A> -> IntFunction<B> */

    public default <A> WithBooleanFunction<A> fuseIntFunction(
        IntFunction<A> next
    ) {
        return WithBooleanFunction.of(
            (boolean b) -> next.apply(resolve().applyAsInt(b))
        );
    }

    public default <A> WithBooleanFunction<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    /* BooleanToIntFunction<A> -> IntToDoubleFunction<B> */

    public default WithBooleanToDoubleFunction fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithBooleanToDoubleFunction.of(
            (boolean b) -> next.applyAsDouble(resolve().applyAsInt(b))
        );
    }

    public default WithBooleanToDoubleFunction fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    /* BooleanToIntFunction<A> -> IntToLongFunction<B> */

    public default WithBooleanToLongFunction fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithBooleanToLongFunction.of(
            (boolean b) -> next.applyAsLong(resolve().applyAsInt(b))
        );
    }

    public default WithBooleanToLongFunction fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    /* BooleanToIntFunction<A> -> IntPredicate<B> */

    public default WithBooleanPredicate fuseIntPredicate(IntPredicate next) {
        return WithBooleanPredicate.of(
            (boolean b) -> next.test(resolve().applyAsInt(b))
        );
    }

    public default WithBooleanPredicate fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    /* BooleanToIntFunction<A> -> IntConsumer<B> */

    public default WithBooleanConsumer fuseIntConsumer(IntConsumer next) {
        return WithBooleanConsumer.of(
            (boolean b) -> next.accept(resolve().applyAsInt(b))
        );
    }

    public default WithBooleanConsumer fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    /* BooleanToIntFunction<A> -> IntUnaryOperator<B> */

    public default WithBooleanToIntFunction fuseIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithBooleanToIntFunction.of(
            (boolean b) -> next.applyAsInt(resolve().applyAsInt(b))
        );
    }

    public default WithBooleanToIntFunction fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
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
}
