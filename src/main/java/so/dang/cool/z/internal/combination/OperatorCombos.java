package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;

interface OperatorCombos {
    Operator resolve();

    /* Operator -> Supplier<A> */

    public default <A> WithSupplier<A> absorbSupplier(Supplier<A> next) {
        return WithSupplier.of(
            () -> {
                resolve().run();
                return next.get();
            }
        );
    }

    public default <A> WithSupplier<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    /* Operator -> BooleanSupplier */

    public default WithBooleanSupplier absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithBooleanSupplier.of(
            () -> {
                resolve().run();
                return next.getAsBoolean();
            }
        );
    }

    public default WithBooleanSupplier absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* Operator -> DoubleSupplier */

    public default WithDoubleSupplier absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithDoubleSupplier.of(
            () -> {
                resolve().run();
                return next.getAsDouble();
            }
        );
    }

    public default WithDoubleSupplier absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    /* Operator -> IntSupplier */

    public default WithIntSupplier absorbIntSupplier(IntSupplier next) {
        return WithIntSupplier.of(
            () -> {
                resolve().run();
                return next.getAsInt();
            }
        );
    }

    public default WithIntSupplier absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* Operator -> LongSupplier */

    public default WithLongSupplier absorbLongSupplier(LongSupplier next) {
        return WithLongSupplier.of(
            () -> {
                resolve().run();
                return next.getAsLong();
            }
        );
    }

    public default WithLongSupplier absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* Operator -> Operator */

    public default WithOperator absorbOperator(Operator next) {
        return WithOperator.of(
            () -> {
                resolve().run();
                next.run();
            }
        );
    }

    public default WithOperator absorb(Operator next) {
        return absorbOperator(next);
    }
}
