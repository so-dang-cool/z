package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoublePredicate;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithDoubleUnaryOperator;

interface DoubleFunctionCombos<A> {
    DoubleFunction<A> resolve();

    /* DoubleFunction<A> -> Function<A,B> */

    public default <B> WithDoubleFunction<B> fuseFunction(Function<A, B> next) {
        return WithDoubleFunction.of(
            (double b) -> next.apply(resolve().apply(b))
        );
    }

    public default <B> WithDoubleFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    /* DoubleFunction<A> -> BiFunction<A,B,C> */

    public default <B, C> DoubleFunction<Function<B, C>> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return (double b1) -> (B b2) -> next.apply(resolve().apply(b1), b2);
    }

    public default <B, C> DoubleFunction<Function<B, C>> fuse(
        BiFunction<A, B, C> next
    ) {
        return fuseBiFunction(next);
    }

    /* DoubleFunction<A> -> ToDoubleFunction<A> */

    public default WithDoubleUnaryOperator fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithDoubleUnaryOperator.of(
            (double b) -> next.applyAsDouble(resolve().apply(b))
        );
    }

    public default WithDoubleUnaryOperator fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    /* DoubleFunction<A> -> ToDoubleBiFunction<A> */

    public default <B> DoubleFunction<ToDoubleFunction<B>> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return (double b1) ->
            (B b2) -> next.applyAsDouble(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<ToDoubleFunction<B>> fuse(
        ToDoubleBiFunction<A, B> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    /* DoubleFunction<A> -> ToIntFunction<A> */

    public default WithDoubleToIntFunction fuseToIntFunction(
        ToIntFunction<A> next
    ) {
        return WithDoubleToIntFunction.of(
            (double b) -> next.applyAsInt(resolve().apply(b))
        );
    }

    public default WithDoubleToIntFunction fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    /* DoubleFunction<A> -> ToIntBiFunction<A> */

    public default <B> DoubleFunction<ToIntFunction<B>> fuseToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return (double b1) ->
            (B b2) -> next.applyAsInt(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<ToIntFunction<B>> fuse(
        ToIntBiFunction<A, B> next
    ) {
        return fuseToIntBiFunction(next);
    }

    /* DoubleFunction<A> -> ToLongFunction<A> */

    public default WithDoubleToLongFunction fuseToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithDoubleToLongFunction.of(
            (double b) -> next.applyAsLong(resolve().apply(b))
        );
    }

    public default WithDoubleToLongFunction fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    /* DoubleFunction<A> -> ToLongBiFunction<A> */

    public default <B> DoubleFunction<ToLongFunction<B>> fuseToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return (double b1) ->
            (B b2) -> next.applyAsLong(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<ToLongFunction<B>> fuse(
        ToLongBiFunction<A, B> next
    ) {
        return fuseToLongBiFunction(next);
    }

    /* DoubleFunction<A> -> Predicate<A> */

    public default WithDoublePredicate fusePredicate(Predicate<A> next) {
        return WithDoublePredicate.of(
            (double b) -> next.test(resolve().apply(b))
        );
    }

    public default WithDoublePredicate fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    /* DoubleFunction<A> -> BiPredicate<A> */

    public default <B> DoubleFunction<Predicate<B>> fuseBiPredicate(
        BiPredicate<A, B> next
    ) {
        return (double b1) -> (B b2) -> next.test(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<Predicate<B>> fuse(
        BiPredicate<A, B> next
    ) {
        return fuseBiPredicate(next);
    }

    /* DoubleFunction<A> -> Consumer<A> */

    public default WithDoubleConsumer fuseConsumer(Consumer<A> next) {
        return WithDoubleConsumer.of(
            (double b) -> next.accept(resolve().apply(b))
        );
    }

    public default WithDoubleConsumer fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    /* DoubleFunction<A> -> BiConsumer<A> */

    public default <B> DoubleFunction<Consumer<B>> fuseBiConsumer(
        BiConsumer<A, B> next
    ) {
        return (double b1) -> (B b2) -> next.accept(resolve().apply(b1), b2);
    }

    public default <B> DoubleFunction<Consumer<B>> fuse(BiConsumer<A, B> next) {
        return fuseBiConsumer(next);
    }

    /* DoubleFunction<A> -> ObjDoubleConsumer<A> */

    public default DoubleFunction<DoubleConsumer> fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return (double b) -> (double d) -> next.accept(resolve().apply(b), d);
    }

    public default DoubleFunction<DoubleConsumer> fuse(
        ObjDoubleConsumer<A> next
    ) {
        return fuseObjDoubleConsumer(next);
    }

    /* DoubleFunction<A> -> ObjIntConsumer<A> */

    public default DoubleFunction<IntConsumer> fuseObjIntConsumer(
        ObjIntConsumer<A> next
    ) {
        return (double b) -> (int d) -> next.accept(resolve().apply(b), d);
    }

    public default DoubleFunction<IntConsumer> fuse(ObjIntConsumer<A> next) {
        return fuseObjIntConsumer(next);
    }

    /* DoubleFunction<A> -> ObjLongConsumer<A> */

    public default DoubleFunction<LongConsumer> fuseObjLongConsumer(
        ObjLongConsumer<A> next
    ) {
        return (double b) -> (long d) -> next.accept(resolve().apply(b), d);
    }

    public default DoubleFunction<LongConsumer> fuse(ObjLongConsumer<A> next) {
        return fuseObjLongConsumer(next);
    }
}
