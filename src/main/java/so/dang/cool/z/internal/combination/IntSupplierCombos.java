package so.dang.cool.z.internal.combination;

import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
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

    public default <A> WithSupplier<A> fuseIntFunction(IntFunction<A> next) {
        return WithSupplier.of(() -> next.apply(resolve().getAsInt()));
    }

    public default <A> WithSupplier<A> fuse(IntFunction<A> next) {
        return fuseIntFunction(next);
    }

    /* IntSupplier -> IntToDoubleFunction */

    public default WithDoubleSupplier fuseIntToDoubleFunction(
        IntToDoubleFunction next
    ) {
        return WithDoubleSupplier.of(
            () -> next.applyAsDouble(resolve().getAsInt())
        );
    }

    public default WithDoubleSupplier fuse(IntToDoubleFunction next) {
        return fuseIntToDoubleFunction(next);
    }

    /* IntSupplier -> IntToLongFunction */

    public default WithLongSupplier fuseIntToLongFunction(
        IntToLongFunction next
    ) {
        return WithLongSupplier.of(
            () -> next.applyAsLong(resolve().getAsInt())
        );
    }

    public default WithLongSupplier fuse(IntToLongFunction next) {
        return fuseIntToLongFunction(next);
    }

    /* IntSupplier -> IntPredicate */

    public default WithBooleanSupplier fuseIntPredicate(IntPredicate next) {
        return WithBooleanSupplier.of(() -> next.test(resolve().getAsInt()));
    }

    public default WithBooleanSupplier fuse(IntPredicate next) {
        return fuseIntPredicate(next);
    }

    /* IntSupplier -> IntConsumer */

    public default WithOperator fuseIntConsumer(IntConsumer next) {
        return WithOperator.of(() -> next.accept(resolve().getAsInt()));
    }

    public default WithOperator fuse(IntConsumer next) {
        return fuseIntConsumer(next);
    }

    /* IntSupplier -> IntUnaryOperator */

    public default WithIntSupplier fuseIntUnaryOperator(IntUnaryOperator next) {
        return WithIntSupplier.of(() -> next.applyAsInt(resolve().getAsInt()));
    }

    public default WithIntSupplier fuse(IntUnaryOperator next) {
        return fuseIntUnaryOperator(next);
    }

    /* IntSupplier -> IntBinaryOperator */

    public default WithIntUnaryOperator fuseIntBinaryOperator(
        IntBinaryOperator next
    ) {
        return WithIntUnaryOperator.of(
            (int i) -> next.applyAsInt(resolve().getAsInt(), i)
        );
    }

    public default WithIntUnaryOperator fuse(IntBinaryOperator next) {
        return fuseIntBinaryOperator(next);
    }
}
