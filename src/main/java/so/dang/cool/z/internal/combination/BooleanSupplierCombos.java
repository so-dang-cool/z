package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;

interface BooleanSupplierCombos {
    public BooleanSupplier resolve();

    /* BooleanSupplier -> BooleanFunction<A> */

    public default <A> WithSupplier<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithSupplier.of(() -> next.apply(resolve().getAsBoolean()));
    }

    public default <A> WithSupplier<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    /* BooleanSupplier -> BooleanToDoubleFunction */

    public default WithDoubleSupplier fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithDoubleSupplier.of(
            () -> next.applyAsDouble(resolve().getAsBoolean())
        );
    }

    public default WithDoubleSupplier fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    /* BooleanSupplier -> BooleanToIntFunction */

    public default WithIntSupplier fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithIntSupplier.of(
            () -> next.applyAsInt(resolve().getAsBoolean())
        );
    }

    public default WithIntSupplier fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    /* BooleanSupplier -> BooleanToLongFunction */

    public default WithLongSupplier fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithLongSupplier.of(
            () -> next.applyAsLong(resolve().getAsBoolean())
        );
    }

    public default WithLongSupplier fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    /* BooleanSupplier -> BooleanPredicate */

    public default WithBooleanSupplier fuseBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithBooleanSupplier.of(
            () -> next.test(resolve().getAsBoolean())
        );
    }

    public default WithBooleanSupplier fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    /* BooleanSupplier -> BooleanConsumer */

    public default WithOperator fuseBiPredicate(BooleanConsumer next) {
        return WithOperator.of(() -> next.accept(resolve().getAsBoolean()));
    }

    public default WithOperator fuse(BooleanConsumer next) {
        return fuseBiPredicate(next);
    }
}
