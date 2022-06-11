package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface ConsumerCombos<A> {
    Consumer<A> resolve();

    /* Consumer<A> -> Function<B, C> */

    @Evil
    public default <B, C> WithBiFunction<A, B, C> absorbFunction(
        Function<B, C> next
    ) {
        return WithBiFunction.of(
            (A a) ->
                (B b) -> {
                    resolve().accept(a);
                    return next.apply(b);
                }
        );
    }

    @Evil
    public default <B, C> WithBiFunction<A, B, C> absorb(Function<B, C> next) {
        return absorbFunction(next);
    }

    /* Consumer<A> -> Supplier<B> */

    @Evil
    public default <B> WithFunction<A, B> absorbSupplier(Supplier<B> next) {
        return WithFunction.of(
            (A a) -> {
                resolve().accept(a);
                return next.get();
            }
        );
    }

    @Evil
    public default <B> WithFunction<A, B> absorb(Supplier<B> next) {
        return absorbSupplier(next);
    }

    /* Consumer<A> -> BooleanSupplier<B> */

    @Evil
    public default WithPredicate<A> absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithPredicate.of(
            (A a) -> {
                resolve().accept(a);
                return next.getAsBoolean();
            }
        );
    }

    @Evil
    public default WithPredicate<A> absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* Consumer<A> -> DoubleSupplier<B> */

    @Evil
    public default WithToDoubleFunction<A> absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithToDoubleFunction.of(
            (A a) -> {
                resolve().accept(a);
                return next.getAsDouble();
            }
        );
    }

    @Evil
    public default WithToDoubleFunction<A> absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    /* Consumer<A> -> IntSupplier<B> */

    @Evil
    public default WithToIntFunction<A> absorbIntSupplier(IntSupplier next) {
        return WithToIntFunction.of(
            (A a) -> {
                resolve().accept(a);
                return next.getAsInt();
            }
        );
    }

    @Evil
    public default WithToIntFunction<A> absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* Consumer<A> -> LongSupplier<B> */

    @Evil
    public default WithToLongFunction<A> absorbLongSupplier(LongSupplier next) {
        return WithToLongFunction.of(
            (A a) -> {
                resolve().accept(a);
                return next.getAsLong();
            }
        );
    }

    @Evil
    public default WithToLongFunction<A> absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* Consumer<A> -> Operator<B> */

    @Evil
    public default WithConsumer<A> absorbOperator(Operator next) {
        return WithConsumer.of(
            (A a) -> {
                resolve().accept(a);
                next.run();
            }
        );
    }

    @Evil
    public default WithConsumer<A> absorb(Operator next) {
        return absorbOperator(next);
    }
}
