package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;

interface BiConsumerCombos<A, B> {
    Function<A, Consumer<B>> resolve();

    /* BiConsumer<A, B> -> Supplier<C> */

    @Evil
    public default <C> Function<A, Function<B, C>> absorbSupplier(
        Supplier<C> next
    ) {
        return (A a) ->
            (B b) -> {
                resolve().apply(a).accept(b);
                return next.get();
            };
    }

    @Evil
    public default <C> Function<A, Function<B, C>> absorb(Supplier<C> next) {
        return absorbSupplier(next);
    }

    @Evil
    public default <C> WithBiFunction<A, B, C> absorbingSupplier(
        Supplier<C> next
    ) {
        return WithBiFunction.of(absorbSupplier(next));
    }

    @Evil
    public default <C> WithBiFunction<A, B, C> absorbing(Supplier<C> next) {
        return absorbingSupplier(next);
    }

    /* BiConsumer<A, B> -> BooleanSupplier */

    @Evil
    public default Function<A, Predicate<B>> absorbBooleanSupplier(
        BooleanSupplier next
    ) {
        return (A a) ->
            (B b) -> {
                resolve().apply(a).accept(b);
                return next.getAsBoolean();
            };
    }

    @Evil
    public default Function<A, Predicate<B>> absorb(BooleanSupplier next) {
        return absorbBooleanSupplier(next);
    }

    @Evil
    public default WithBiPredicate<A, B> absorbingBooleanSupplier(
        BooleanSupplier next
    ) {
        return WithBiPredicate.of(absorbBooleanSupplier(next));
    }

    @Evil
    public default WithBiPredicate<A, B> absorbing(BooleanSupplier next) {
        return absorbingBooleanSupplier(next);
    }

    /* BiConsumer<A, B> -> DoubleSupplier */

    @Evil
    public default Function<A, ToDoubleFunction<B>> absorbDoubleSupplier(
        DoubleSupplier next
    ) {
        return (A a) ->
            (B b) -> {
                resolve().apply(a).accept(b);
                return next.getAsDouble();
            };
    }

    @Evil
    public default Function<A, ToDoubleFunction<B>> absorb(
        DoubleSupplier next
    ) {
        return absorbDoubleSupplier(next);
    }

    @Evil
    public default WithBiFunction<A, B, Double> absorbingDoubleSupplier(
        DoubleSupplier next
    ) {
        // TODO: Better currying support needed for a better solution here (and return value)
        return WithBiFunction.of(
            (A a, B b) -> absorbDoubleSupplier(next).apply(a).applyAsDouble(b)
        );
    }

    @Evil
    public default WithBiFunction<A, B, Double> absorbing(DoubleSupplier next) {
        return absorbingDoubleSupplier(next);
    }

    /* BiConsumer<A, B> -> IntSupplier */

    @Evil
    public default Function<A, ToIntFunction<B>> absorbIntSupplier(
        IntSupplier next
    ) {
        return (A a) ->
            (B b) -> {
                resolve().apply(a).accept(b);
                return next.getAsInt();
            };
    }

    @Evil
    public default Function<A, ToIntFunction<B>> absorb(IntSupplier next) {
        return absorbIntSupplier(next);
    }

    @Evil
    public default WithBiFunction<A, B, Integer> absorbingIntSupplier(
        IntSupplier next
    ) {
        // TODO: Better currying support needed for a better solution here (and return value)
        return WithBiFunction.of(
            (A a, B b) -> absorbIntSupplier(next).apply(a).applyAsInt(b)
        );
    }

    @Evil
    public default WithBiFunction<A, B, Integer> absorbing(IntSupplier next) {
        return absorbingIntSupplier(next);
    }

    /* BiConsumer<A, B> -> LongSupplier */

    @Evil
    public default Function<A, ToLongFunction<B>> absorbLongSupplier(
        LongSupplier next
    ) {
        return (A a) ->
            (B b) -> {
                resolve().apply(a).accept(b);
                return next.getAsLong();
            };
    }

    @Evil
    public default Function<A, ToLongFunction<B>> absorb(LongSupplier next) {
        return absorbLongSupplier(next);
    }

    @Evil
    public default WithBiFunction<A, B, Long> absorbingLongSupplier(
        LongSupplier next
    ) {
        // TODO: Better currying support needed for a better solution here (and return value)
        return WithBiFunction.of(
            (A a, B b) -> absorbLongSupplier(next).apply(a).applyAsLong(b)
        );
    }

    @Evil
    public default WithBiFunction<A, B, Long> absorbing(LongSupplier next) {
        return absorbingLongSupplier(next);
    }

    /* BiConsumer<A, B> -> Operator */

    @Evil
    public default Function<A, Consumer<B>> absorbOperator(Operator next) {
        return (A a) ->
            (B b) -> {
                resolve().apply(a).accept(b);
                next.run();
            };
    }

    @Evil
    public default Function<A, Consumer<B>> absorb(Operator next) {
        return absorbOperator(next);
    }

    @Evil
    public default WithBiConsumer<A, B> absorbingOperator(Operator next) {
        return WithBiConsumer.of(absorbOperator(next));
    }

    @Evil
    public default WithBiConsumer<A, B> absorbing(Operator next) {
        return absorbingOperator(next);
    }
}
