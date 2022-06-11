package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoublePredicate;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;

interface DoubleConsumerCombos {
    DoubleConsumer resolve();

    /* DoubleConsumer -> Supplier<A> */

    @Evil
    public default <A> WithDoubleFunction<A> absorbSupplier(Supplier<A> next) {
        return WithDoubleFunction.of(
            (double d) -> {
                resolve().accept(d);
                return next.get();
            }
        );
    }

    @Evil
    public default <A> WithDoubleFunction<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    /* DoubleConsumer -> BooleanSupplier<A> */

    @Evil
    public default WithDoublePredicate absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithDoublePredicate.of(
            (double d) -> {
                resolve().accept(d);
                return next.getAsBoolean();
            }
        );
    }

    @Evil
    public default WithDoublePredicate absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* DoubleConsumer -> DoubleSupplier<B> */

    @Evil
    public default WithDoubleUnaryOperator absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithDoubleUnaryOperator.of(
            (double d) -> {
                resolve().accept(d);
                return next.getAsDouble();
            }
        );
    }

    @Evil
    public default WithDoubleUnaryOperator absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    /* DoubleConsumer -> IntSupplier<B> */

    @Evil
    public default WithDoubleToIntFunction absorbIntSupplier(IntSupplier next) {
        return WithDoubleToIntFunction.of(
            (double d) -> {
                resolve().accept(d);
                return next.getAsInt();
            }
        );
    }

    @Evil
    public default WithDoubleToIntFunction absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* DoubleConsumer -> LongSupplier<B> */

    @Evil
    public default WithDoubleToLongFunction absorbLongSupplier(
        LongSupplier next
    ) {
        return WithDoubleToLongFunction.of(
            (double d) -> {
                resolve().accept(d);
                return next.getAsLong();
            }
        );
    }

    @Evil
    public default WithDoubleToLongFunction absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* DoubleConsumer -> Operator<B> */

    @Evil
    public default WithDoubleConsumer absorbOperator(Operator next) {
        return WithDoubleConsumer.of(
            (double d) -> {
                resolve().accept(d);
                next.run();
            }
        );
    }

    @Evil
    public default WithDoubleConsumer absorb(Operator next) {
        return absorbOperator(next);
    }
}
