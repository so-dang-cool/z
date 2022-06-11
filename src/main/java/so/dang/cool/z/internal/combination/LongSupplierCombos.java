package so.dang.cool.z.internal.combination;

import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;

interface LongSupplierCombos {
    public LongSupplier resolve();

    /* LongSupplier -> LongFunction<A> */

    public default <A> WithSupplier<A> fuseLongFunction(LongFunction<A> next) {
        return WithSupplier.of(() -> next.apply(resolve().getAsLong()));
    }

    public default <A> WithSupplier<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    /* LongSupplier -> LongToDoubleFunction */

    public default WithDoubleSupplier fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithDoubleSupplier.of(
            () -> next.applyAsDouble(resolve().getAsLong())
        );
    }

    public default WithDoubleSupplier fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    /* LongSupplier -> LongToIntFunction */

    public default WithIntSupplier fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithIntSupplier.of(() -> next.applyAsInt(resolve().getAsLong()));
    }

    public default WithIntSupplier fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    /* LongSupplier -> LongPredicate */

    public default WithBooleanSupplier fuseLongPredicate(LongPredicate next) {
        return WithBooleanSupplier.of(() -> next.test(resolve().getAsLong()));
    }

    public default WithBooleanSupplier fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    /* LongSupplier -> LongConsumer */

    public default WithOperator fuseLongConsumer(LongConsumer next) {
        return WithOperator.of(() -> next.accept(resolve().getAsLong()));
    }

    public default WithOperator fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    /* LongSupplier -> LongUnaryOperator */

    public default WithLongSupplier fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithLongSupplier.of(
            () -> next.applyAsLong(resolve().getAsLong())
        );
    }

    public default WithLongSupplier fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    /* LongSupplier -> LongBinaryOperator */

    public default WithLongUnaryOperator fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return WithLongUnaryOperator.of(
            (long n) -> next.applyAsLong(resolve().getAsLong(), n)
        );
    }

    public default WithLongUnaryOperator fuse(LongBinaryOperator next) {
        return fuseLongBinaryOperator(next);
    }
}
