package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;

interface IntSupplierCombos {
    public IntSupplier resolve();

    /* IntSupplier -> IntFunction<A> */

    public default <A> Supplier<A> fuseIntFunction(IntFunction<A> next) {
        return () -> next.apply(resolve().getAsInt());
    }

    public default <A> Supplier<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    public default <A> WithSupplier<A> fusingIntFunction(IntFunction<A> next) {
        return WithSupplier.of(fuse(next));
    }

    public default <A> WithSupplier<A> fusing(IntFunction<A> next) {
        return fusingIntFunction(next);
    }

    /* IntSupplier -> IntToDoubleFunction */

    public default DoubleSupplier fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return () -> next.applyAsDouble(resolve().getAsInt());
    }

    public default DoubleSupplier fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    public default WithDoubleSupplier fusingIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithDoubleSupplier.of(fuse(next));
    }

    public default WithDoubleSupplier fusing(IntToDoubleFunction next) {
        return fusingIntToDoubleFunction(next);
    }

    /* IntSupplier -> IntToLongFunction */

    public default LongSupplier fuseIntToLongFunction(IntToLongFunction next) {
        return () -> next.applyAsLong(resolve().getAsInt());
    }

    public default LongSupplier fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    public default WithLongSupplier fusingIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithLongSupplier.of(fuse(next));
    }

    public default WithLongSupplier fusing(IntToLongFunction next) {
        return fusingIntToLongFunction(next);
    }

    /* IntSupplier -> IntPredicate */

    public default BooleanSupplier fuseIntPredicate(IntPredicate next) {
        return () -> next.test(resolve().getAsInt());
    }

    public default BooleanSupplier fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    public default WithBooleanSupplier fusingIntPredicate(IntPredicate next) {
        return WithBooleanSupplier.of(fuse(next));
    }

    public default WithBooleanSupplier fusing(IntPredicate next) {
        return fusingIntPredicate(next);
    }

    /* IntSupplier -> IntConsumer */

    public default Operator fuseIntConsumer(IntConsumer next) {
        return () -> next.accept(resolve().getAsInt());
    }

    public default Operator fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    public default WithOperator fusingIntConsumer(IntConsumer next) {
        return WithOperator.of(fuse(next));
    }

    public default WithOperator fusing(IntConsumer next) {
        return fusingIntConsumer(next);
    }

    /* IntSupplier -> IntUnaryOperator */

    public default IntSupplier fuseIntUnaryOperator(IntUnaryOperator next) {
        return () -> next.applyAsInt(resolve().getAsInt());
    }

    public default IntSupplier fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    public default WithIntSupplier fusingIntUnaryOperator(
        IntUnaryOperator next
    ) {
        return WithIntSupplier.of(fuse(next));
    }

    public default WithIntSupplier fusing(IntUnaryOperator next) {
        return fusingIntUnaryOperator(next);
    }

    /* IntSupplier -> IntBinaryOperator */

    public default IntUnaryOperator fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return (int i) -> next.applyAsInt(resolve().getAsInt(), i);
    }

    public default IntUnaryOperator fuse(IntBinaryOperator next) {
        return fuseIntBinaryOperator(next);
    }

    public default WithIntUnaryOperator fusingIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return WithIntUnaryOperator.of(fuse(next));
    }

    public default WithIntUnaryOperator fusing(IntBinaryOperator next) {
        return fusingIntBinaryOperator(next);
    }

    public default IntSupplier fuseIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return () -> next.applyAsInt(resolve().getAsInt(), secondArg);
    }

    public default IntSupplier fuse(IntBinaryOperator next, int secondArg) {
        return fuseIntBinaryOperator(next, secondArg);
    }

    public default WithIntSupplier fusingIntBinaryOperator(
        IntBinaryOperator next,
        int secondArg
    ) {
        return WithIntSupplier.of(fuse(next, secondArg));
    }

    public default WithIntSupplier fusing(
        IntBinaryOperator next,
        int secondArg
    ) {
        return fusingIntBinaryOperator(next, secondArg);
    }
}
