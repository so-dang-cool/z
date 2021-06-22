package so.dang.cool.z.internal.combination;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongBiFunction;

interface BiPredicateCombos<A, B> {
    Function<A, Predicate<B>> resolve();

    /* BiPredicate<A, B> -> BooleanFunction<C> */

    public default <C> Function<A, Function<B, C>> fuseBooleanFunction(
        BooleanFunction<C> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a).test(b));
    }

    public default <C> Function<A, Function<B, C>> fuse(
        BooleanFunction<C> next
    ) {
        return fuseBooleanFunction(next);
    }

    public default <C> WithBiFunction<A, B, C> fusingBooleanFunction(
        BooleanFunction<C> next
    ) {
        return WithBiFunction.of(fuseBooleanFunction(next));
    }

    public default <C> WithBiFunction<A, B, C> fusing(BooleanFunction<C> next) {
        return fusingBooleanFunction(next);
    }

    /* BiPredicate<A, B> -> BooleanToDoubleFunction<C> */

    public default Function<A, ToDoubleFunction<B>> fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return (A a) -> (B b) -> next.applyAsDouble(resolve().apply(a).test(b));
    }

    public default <C> Function<A, ToDoubleFunction<B>> fuse(
        BooleanToDoubleFunction next
    ) {
        return fuseBooleanToDoubleFunction(next);
    }

    public default <C> WithToDoubleBiFunction<A, B> fusingBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithToDoubleBiFunction.of(fuseBooleanToDoubleFunction(next));
    }

    public default <C> WithToDoubleBiFunction<A, B> fusing(
        BooleanToDoubleFunction next
    ) {
        return fusingBooleanToDoubleFunction(next);
    }

    /* BiPredicate<A, B> -> BooleanToIntFunction<C> */

    public default Function<A, ToIntFunction<B>> fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return (A a) -> (B b) -> next.applyAsInt(resolve().apply(a).test(b));
    }

    public default <C> Function<A, ToIntFunction<B>> fuse(
        BooleanToIntFunction next
    ) {
        return fuseBooleanToIntFunction(next);
    }

    public default <C> WithToIntBiFunction<A, B> fusingBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithToIntBiFunction.of(fuseBooleanToIntFunction(next));
    }

    public default <C> WithToIntBiFunction<A, B> fusing(
        BooleanToIntFunction next
    ) {
        return fusingBooleanToIntFunction(next);
    }

    /* BiPredicate<A, B> -> BooleanToLongFunction<C> */

    public default Function<A, ToLongFunction<B>> fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return (A a) -> (B b) -> next.applyAsLong(resolve().apply(a).test(b));
    }

    public default <C> Function<A, ToLongFunction<B>> fuse(
        BooleanToLongFunction next
    ) {
        return fuseBooleanToLongFunction(next);
    }

    public default <C> WithToLongBiFunction<A, B> fusingBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithToLongBiFunction.of(fuseBooleanToLongFunction(next));
    }

    public default <C> WithToLongBiFunction<A, B> fusing(
        BooleanToLongFunction next
    ) {
        return fusingBooleanToLongFunction(next);
    }

    /* BiPredicate<A, B> -> BooleanPredicate<C> */

    public default Function<A, Predicate<B>> fuseBooleanPredicate(
        BooleanPredicate next
    ) {
        return (A a) -> (B b) -> next.test(resolve().apply(a).test(b));
    }

    public default <C> Function<A, Predicate<B>> fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    public default <C> WithBiPredicate<A, B> fusingBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithBiPredicate.of(fuseBooleanPredicate(next));
    }

    public default <C> WithBiPredicate<A, B> fusing(BooleanPredicate next) {
        return fusingBooleanPredicate(next);
    }

    /* BiPredicate<A, B> -> BooleanConsumer<C> */

    public default Function<A, Consumer<B>> fuseBooleanConsumer(
        BooleanConsumer next
    ) {
        return (A a) -> (B b) -> next.accept(resolve().apply(a).test(b));
    }

    public default <C> Function<A, Consumer<B>> fuse(BooleanConsumer next) {
        return fuseBooleanConsumer(next);
    }

    public default <C> WithBiConsumer<A, B> fusingBooleanConsumer(
        BooleanConsumer next
    ) {
        return WithBiConsumer.of(fuseBooleanConsumer(next));
    }

    public default <C> WithBiConsumer<A, B> fusing(BooleanConsumer next) {
        return fusingBooleanConsumer(next);
    }
}
