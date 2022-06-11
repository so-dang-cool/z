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
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;

interface BiConsumerCombos<A, B> {
    Function<A, Consumer<B>> resolve();

    /* BiConsumer<A, B> -> Supplier<C> */

    @Evil
    public default <C> WithBiFunction<A, B, C> absorbSupplier(
        Supplier<C> next
    ) {
        return WithBiFunction.of(
            (A a) ->
                (B b) -> {
                    resolve().apply(a).accept(b);
                    return next.get();
                }
        );
    }

    @Evil
    public default <C> WithBiFunction<A, B, C> absorb(Supplier<C> next) {
        return absorbSupplier(next);
    }

    /* BiConsumer<A, B> -> BooleanSupplier */

    @Evil
    public default WithBiPredicate<A, B> absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithBiPredicate.of(
            (A a) ->
                (B b) -> {
                    resolve().apply(a).accept(b);
                    return next.getAsBoolean();
                }
        );
    }

    @Evil
    public default WithBiPredicate<A, B> absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    /* BiConsumer<A, B> -> DoubleSupplier */

    @Evil
    @PromotesPrimitive(promoted = Double.class)
    public default WithBiFunction<A, B, Double> absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return WithBiFunction.of(
            (A a) ->
                (B b) -> {
                    resolve().apply(a).accept(b);
                    return next.getAsDouble();
                }
        );
    }

    @Evil
    @PromotesPrimitive(promoted = Double.class)
    public default WithBiFunction<A, B, Double> absorb(DoubleSupplier next) {
        return absorbDoubleSupplier(next);
    }

    /* BiConsumer<A, B> -> IntSupplier */

    @Evil
    @PromotesPrimitive(promoted = Integer.class)
    public default WithBiFunction<A, B, Integer> absorbIntSupplier(
        IntSupplier next
    ) {
        return WithBiFunction.of(
            (A a) ->
                (B b) -> {
                    resolve().apply(a).accept(b);
                    return next.getAsInt();
                }
        );
    }

    @Evil
    @PromotesPrimitive(promoted = Integer.class)
    public default WithBiFunction<A, B, Integer> absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    /* BiConsumer<A, B> -> LongSupplier */

    @Evil
    @PromotesPrimitive(promoted = Long.class)
    public default WithBiFunction<A, B, Long> absorbLongSupplier(
        LongSupplier next
    ) {
        return WithBiFunction.of(
            (A a) ->
                (B b) -> {
                    resolve().apply(a).accept(b);
                    return next.getAsLong();
                }
        );
    }

    @Evil
    @PromotesPrimitive(promoted = Long.class)
    public default WithBiFunction<A, B, Long> absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    /* BiConsumer<A, B> -> Operator */

    @Evil
    public default WithBiConsumer<A, B> absorbOperator(Operator next) {
        return WithBiConsumer.of(
            (A a) ->
                (B b) -> {
                    resolve().apply(a).accept(b);
                    next.run();
                }
        );
    }

    @Evil
    public default WithBiConsumer<A, B> absorb(Operator next) {
        return absorbOperator(next);
    }
}
