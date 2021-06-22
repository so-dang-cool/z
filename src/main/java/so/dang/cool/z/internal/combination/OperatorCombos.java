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

    public default <A> Supplier<A> absorbSupplier(Supplier<A> next) {
        return () -> {
            resolve().run();
            return next.get();
        };
    }

    public default <A> Supplier<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    public default <A> WithSupplier<A> absorbingSupplier(Supplier<A> next) {
        return WithSupplier.of(absorbSupplier(next));
    }

    public default <A> WithSupplier<A> absorbing(Supplier<A> next) {
        return absorbingSupplier(next);
    }

    /* Operator -> BooleanSupplier */

    public default BooleanSupplier absorbBooleanSupplier(BooleanSupplier next) {
        return () -> {
            resolve().run();
            return next.getAsBoolean();
        };
    }

    public default BooleanSupplier absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    public default WithBooleanSupplier absorbingBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithBooleanSupplier.of(absorbBooleanSupplier(next));
    }

    public default WithBooleanSupplier absorbing(BooleanSupplier next) {
        return absorbingBooleanSupplier(next);
    }

    /* Operator -> DoubleSupplier */

    public default DoubleSupplier absorbDoubleSupplier(DoubleSupplier next) {
        return () -> {
            resolve().run();
            return next.getAsDouble();
        };
    }

    public default DoubleSupplier absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    public default WithDoubleSupplier absorbingDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithDoubleSupplier.of(absorbDoubleSupplier(next));
    }

    public default WithDoubleSupplier absorbing(DoubleSupplier next) {
        return absorbingDoubleSupplier(next);
    }

    /* Operator -> IntSupplier */

    public default IntSupplier absorbIntSupplier(IntSupplier next) {
        return () -> {
            resolve().run();
            return next.getAsInt();
        };
    }

    public default IntSupplier absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    public default WithIntSupplier absorbingIntSupplier(IntSupplier next) {
        return WithIntSupplier.of(absorbIntSupplier(next));
    }

    public default WithIntSupplier absorbing(IntSupplier next) {
        return absorbingIntSupplier(next);
    }

    /* Operator -> LongSupplier */

    public default LongSupplier absorbLongSupplier(LongSupplier next) {
        return () -> {
            resolve().run();
            return next.getAsLong();
        };
    }

    public default LongSupplier absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    public default WithLongSupplier absorbingLongSupplier(LongSupplier next) {
        return WithLongSupplier.of(absorbLongSupplier(next));
    }

    public default WithLongSupplier absorbing(LongSupplier next) {
        return absorbingLongSupplier(next);
    }

    /* Operator -> Operator */

    public default Operator absorbOperator(Operator next) {
        return () -> {
            resolve().run();
            next.run();
        };
    }

    public default Operator absorb(Operator next) {
        return absorbOperator(next);
    }

    public default WithOperator absorbingOperator(Operator next) {
        return WithOperator.of(absorbOperator(next));
    }

    public default WithOperator absorbing(Operator next) {
        return absorbingOperator(next);
    }
}
