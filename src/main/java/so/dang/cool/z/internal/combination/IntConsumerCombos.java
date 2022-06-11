package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntPredicate;
import so.dang.cool.z.internal.combination.Combine.WithIntToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntUnaryOperator;

interface IntConsumerCombos {
    IntConsumer resolve();

    /* IntConsumer -> Supplier<A> */

    @Evil
    public default <A> WithIntFunction<A> absorbSupplier(Supplier<A> next) {
        return WithIntFunction.of(
            (int i) -> {
                resolve().accept(i);
                return next.get();
            }
        );
    }

    @Evil
    public default <A> WithIntFunction<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    /* IntConsumer -> BooleanSupplier<A> */

    @Evil
    public default WithIntPredicate absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithIntPredicate.of(
            (int i) -> {
                resolve().accept(i);
                return next.getAsBoolean();
            }
        );
    }

    @Evil
    public default WithIntPredicate absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* IntConsumer -> DoubleSupplier<B> */

    @Evil
    public default WithIntToDoubleFunction absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithIntToDoubleFunction.of(
            (int i) -> {
                resolve().accept(i);
                return next.getAsDouble();
            }
        );
    }

    @Evil
    public default WithIntToDoubleFunction absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    /* IntConsumer -> IntSupplier<B> */

    @Evil
    public default WithIntUnaryOperator absorbIntSupplier(IntSupplier next) {
        return WithIntUnaryOperator.of(
            (int i) -> {
                resolve().accept(i);
                return next.getAsInt();
            }
        );
    }

    @Evil
    public default WithIntUnaryOperator absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* IntConsumer -> LongSupplier<B> */

    @Evil
    public default WithIntToLongFunction absorbLongSupplier(LongSupplier next) {
        return WithIntToLongFunction.of(
            (int i) -> {
                resolve().accept(i);
                return next.getAsLong();
            }
        );
    }

    @Evil
    public default WithIntToLongFunction absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* IntConsumer -> Operator<B> */

    @Evil
    public default WithIntConsumer absorbOperator(Operator next) {
        return WithIntConsumer.of(
            (int i) -> {
                resolve().accept(i);
                next.run();
            }
        );
    }

    @Evil
    public default WithIntConsumer absorb(Operator next) {
        return absorbOperator(next);
    }
}
