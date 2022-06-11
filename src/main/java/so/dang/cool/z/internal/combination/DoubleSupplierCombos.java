package so.dang.cool.z.internal.combination;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;

interface DoubleSupplierCombos {
    public DoubleSupplier resolve();

    /* DoubleSupplier -> DoubleFunction<A> */

    public default <A> WithSupplier<A> fuseDoubleFunction(
        DoubleFunction<A> next
    ) {
        return WithSupplier.of(() -> next.apply(resolve().getAsDouble()));
    }

    public default <A> WithSupplier<A> fuse(DoubleFunction<A> next) {
        return fuseDoubleFunction(next);
    }

    /* DoubleSupplier -> DoubleToIntFunction */

    public default WithIntSupplier fuseDoubleToIntFunction(
        DoubleToIntFunction next
    ) {
        return WithIntSupplier.of(
            () -> next.applyAsInt(resolve().getAsDouble())
        );
    }

    public default WithIntSupplier fuse(DoubleToIntFunction next) {
        return fuseDoubleToIntFunction(next);
    }

    /* DoubleSupplier -> DoubleToLongFunction */

    public default WithLongSupplier fuseDoubleToLongFunction(
        DoubleToLongFunction next
    ) {
        return WithLongSupplier.of(
            () -> next.applyAsLong(resolve().getAsDouble())
        );
    }

    public default WithLongSupplier fuse(DoubleToLongFunction next) {
        return fuseDoubleToLongFunction(next);
    }

    /* DoubleSupplier -> DoublePredicate */

    public default WithBooleanSupplier fuseDoublePredicate(
        DoublePredicate next
    ) {
        return WithBooleanSupplier.of(() -> next.test(resolve().getAsDouble()));
    }

    public default WithBooleanSupplier fuse(DoublePredicate next) {
        return fuseDoublePredicate(next);
    }

    /* DoubleSupplier -> DoubleConsumer */

    public default WithOperator fuseDoubleConsumer(DoubleConsumer next) {
        return WithOperator.of(() -> next.accept(resolve().getAsDouble()));
    }

    public default WithOperator fuse(DoubleConsumer next) {
        return fuseDoubleConsumer(next);
    }

    /* DoubleSupplier -> DoubleUnaryOperator */

    public default WithDoubleSupplier fuseDoubleUnaryOperator(
        DoubleUnaryOperator next
    ) {
        return WithDoubleSupplier.of(
            () -> next.applyAsDouble(resolve().getAsDouble())
        );
    }

    public default WithDoubleSupplier fuse(DoubleUnaryOperator next) {
        return fuseDoubleUnaryOperator(next);
    }

    /* DoubleSupplier -> DoubleBinaryOperator */

    public default WithDoubleUnaryOperator fuseDoubleBinaryOperator(
        DoubleBinaryOperator next
    ) {
        return WithDoubleUnaryOperator.of(
            (double d) -> next.applyAsDouble(resolve().getAsDouble(), d)
        );
    }

    public default WithDoubleUnaryOperator fuse(DoubleBinaryOperator next) {
        return fuseDoubleBinaryOperator(next);
    }
}
