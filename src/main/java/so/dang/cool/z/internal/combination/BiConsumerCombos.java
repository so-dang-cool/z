package so.dang.cool.z.internal.combination;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;

interface BiConsumerCombos<A, B> {
    Function<A, Consumer<B>> resolve();

    /* TODO: Consumer<A> -> Function<B, C> */

    /* Consumer<A> -> Supplier<B> */

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
}
