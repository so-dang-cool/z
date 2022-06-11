package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanConsumerCombos {
    BooleanConsumer resolve();

    /* BooleanConsumer -> Supplier<A> */

    @Evil
    public default <A> WithBooleanFunction<A> absorbSupplier(Supplier<A> next) {
        return WithBooleanFunction.of(
            (boolean b) -> {
                resolve().accept(b);
                return next.get();
            }
        );
    }

    @Evil
    public default <A> WithBooleanFunction<A> absorb(Supplier<A> next) {
        return absorbSupplier(next);
    }

    /* BooleanConsumer -> BooleanSupplier<A> */

    @Evil
    public default WithBooleanPredicate absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithBooleanPredicate.of(
            (boolean b) -> {
                resolve().accept(b);
                return next.getAsBoolean();
            }
        );
    }

    @Evil
    public default WithBooleanPredicate absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* BooleanConsumer -> DoubleSupplier<B> */

    @Evil
    public default WithBooleanToDoubleFunction absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithBooleanToDoubleFunction.of(
            (boolean b) -> {
                resolve().accept(b);
                return next.getAsDouble();
            }
        );
    }

    @Evil
    public default WithBooleanToDoubleFunction absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    /* BooleanConsumer -> IntSupplier<B> */

    @Evil
    public default WithBooleanToIntFunction absorbIntSupplier(
        IntSupplier next
    ) {
        return WithBooleanToIntFunction.of(
            (boolean b) -> {
                resolve().accept(b);
                return next.getAsInt();
            }
        );
    }

    @Evil
    public default WithBooleanToIntFunction absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* BooleanConsumer -> LongSupplier<B> */

    @Evil
    public default WithBooleanToLongFunction absorbLongSupplier(
        LongSupplier next
    ) {
        return WithBooleanToLongFunction.of(
            (boolean b) -> {
                resolve().accept(b);
                return next.getAsLong();
            }
        );
    }

    @Evil
    public default WithBooleanToLongFunction absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* BooleanConsumer -> Operator<B> */

    @Evil
    public default WithBooleanConsumer absorbOperator(Operator next) {
        return WithBooleanConsumer.of(
            (boolean b) -> {
                resolve().accept(b);
                next.run();
            }
        );
    }

    @Evil
    public default WithBooleanConsumer absorb(Operator next) {
        return absorbOperator(next);
    }
}
