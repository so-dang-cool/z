package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
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
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongBiFunction;

interface BiFunctionCombos<A, B, C> {
    Function<A, Function<B, C>> resolve();

    /* BiFunction<A, B, C> -> Function<C, D> */

    public default <D> WithBiFunction<A, B, D> fuseFunction(
        Function<C, D> next
    ) {
        return WithBiFunction.of(
            (A a, B b) -> next.apply(resolve().apply(a).apply(b))
        );
    }

    public default <D> WithBiFunction<A, B, D> fuse(Function<C, D> next) {
        return fuseFunction(next);
    }

    /* BiFunction<A, B, C> -> BiFunction<C, D, E> */

    // TODO: Implement with currying?
    public default <D, E> Function<A, Function<B, Function<D, E>>> fuseBiFunction(
        BiFunction<C, D, E> next
    ) {
        return (A a) ->
            (B b) -> (D d) -> next.apply(resolve().apply(a).apply(b), d);
    }

    public default <D, E> Function<A, Function<B, Function<D, E>>> fuse(
        BiFunction<C, D, E> next
    ) {
        return fuseBiFunction(next);
    }

    /* BiFunction<A, B, C> -> ToDoubleFunction<C> */

    public default WithToDoubleBiFunction<A, B> fuseToDoubleFunction(
        ToDoubleFunction<C> next
    ) {
        return WithToDoubleBiFunction.of(
            (A a, B b) -> next.applyAsDouble(resolve().apply(a).apply(b))
        );
    }

    public default WithToDoubleBiFunction<A, B> fuse(ToDoubleFunction<C> next) {
        return fuseToDoubleFunction(next);
    }

    /* BiFunction<A, B, C> -> ToDoubleBiFunction<C, D> */

    // TODO: Implement with currying?
    public default <D> Function<A, Function<B, ToDoubleFunction<D>>> fuseToDoubleBiFunction(
        ToDoubleBiFunction<C, D> next
    ) {
        return (A a) ->
            (B b) ->
                (D d) -> next.applyAsDouble(resolve().apply(a).apply(b), d);
    }

    public default <D> Function<A, Function<B, ToDoubleFunction<D>>> fuse(
        ToDoubleBiFunction<C, D> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    /* BiFunction<A, B, C> -> ToIntFunction<C> */

    public default WithToIntBiFunction<A, B> fuseToIntFunction(
        ToIntFunction<C> next
    ) {
        return WithToIntBiFunction.of(
            (A a, B b) -> next.applyAsInt(resolve().apply(a).apply(b))
        );
    }

    public default WithToIntBiFunction<A, B> fuse(ToIntFunction<C> next) {
        return fuseToIntFunction(next);
    }

    /* BiFunction<A, B, C> -> ToIntBiFunction<C, D> */

    // TODO: Implement with currying?
    public default <D> Function<A, Function<B, ToIntFunction<D>>> fuseToIntBiFunction(
        ToIntBiFunction<C, D> next
    ) {
        return (A a) ->
            (B b) -> (D d) -> next.applyAsInt(resolve().apply(a).apply(b), d);
    }

    public default <D> Function<A, Function<B, ToIntFunction<D>>> fuse(
        ToIntBiFunction<C, D> next
    ) {
        return fuseToIntBiFunction(next);
    }

    /* BiFunction<A, B, C> -> ToLongFunction<C> */

    public default WithToLongBiFunction<A, B> fuseToLongFunction(
        ToLongFunction<C> next
    ) {
        return WithToLongBiFunction.of(
            (A a) -> (B b) -> next.applyAsLong(resolve().apply(a).apply(b))
        );
    }

    public default WithToLongBiFunction<A, B> fuse(ToLongFunction<C> next) {
        return fuseToLongFunction(next);
    }

    /* BiFunction<A, B, C> -> ToLongBiFunction<C, D> */

    // TODO: Implement with currying?
    public default <D> Function<A, Function<B, ToLongFunction<D>>> fuseToLongBiFunction(
        ToLongBiFunction<C, D> next
    ) {
        return (A a) ->
            (B b) -> (D d) -> next.applyAsLong(resolve().apply(a).apply(b), d);
    }

    public default <D> Function<A, Function<B, ToLongFunction<D>>> fuse(
        ToLongBiFunction<C, D> next
    ) {
        return fuseToLongBiFunction(next);
    }

    /* BiFunction<A, B, C> -> Predicate<C> */

    public default WithBiPredicate<A, B> fusePredicate(Predicate<C> next) {
        return WithBiPredicate.of(
            (A a, B b) -> next.test(resolve().apply(a).apply(b))
        );
    }

    public default WithBiPredicate<A, B> fuse(Predicate<C> next) {
        return fusePredicate(next);
    }

    /* BiFunction<A, B, C> -> BiPredicate<C, D> */

    // TODO: Implement with currying?
    public default <D> Function<A, Function<B, Predicate<D>>> fuseBiPredicate(
        BiPredicate<C, D> next
    ) {
        return (A a) ->
            (B b) -> (D d) -> next.test(resolve().apply(a).apply(b), d);
    }

    public default <D> Function<A, Function<B, Predicate<D>>> fuse(
        BiPredicate<C, D> next
    ) {
        return fuseBiPredicate(next);
    }

    /* BiFunction<A, B, C> -> Consumer<C> */

    public default WithBiConsumer<A, B> fuseConsumer(Consumer<C> next) {
        return WithBiConsumer.of(
            (A a, B b) -> next.accept(resolve().apply(a).apply(b))
        );
    }

    public default WithBiConsumer<A, B> fuse(Consumer<C> next) {
        return fuseConsumer(next);
    }

    /* BiFunction<A, B, C> -> BiConsumer<C, D> */

    // TODO: Implement with currying?
    public default <D> Function<A, Function<B, Consumer<D>>> fuseBiConsumer(
        BiConsumer<C, D> next
    ) {
        return (A a) ->
            (B b) -> (D d) -> next.accept(resolve().apply(a).apply(b), d);
    }

    public default <D> Function<A, Function<B, Consumer<D>>> fuse(
        BiConsumer<C, D> next
    ) {
        return fuseBiConsumer(next);
    }

    /* BiFunction<A, B, C> -> ObjDoubleConsumer<C> */

    //TODO: Implement with currying?
    public default <D> Function<A, Function<B, DoubleConsumer>> fuseObjDoubleConsumer(
        ObjDoubleConsumer<C> next
    ) {
        return (A a) ->
            (B b) -> (double d) -> next.accept(resolve().apply(a).apply(b), d);
    }

    public default <D> Function<A, Function<B, DoubleConsumer>> fuse(
        ObjDoubleConsumer<C> next
    ) {
        return fuseObjDoubleConsumer(next);
    }

    /* BiFunction<A, B, C> -> ObjIntConsumer<C> */

    //TODO: Implement with currying?
    public default <D> Function<A, Function<B, IntConsumer>> fuseObjIntConsumer(
        ObjIntConsumer<C> next
    ) {
        return (A a) ->
            (B b) -> (int d) -> next.accept(resolve().apply(a).apply(b), d);
    }

    public default <D> Function<A, Function<B, IntConsumer>> fuse(
        ObjIntConsumer<C> next
    ) {
        return fuseObjIntConsumer(next);
    }

    /* BiFunction<A, B, C> -> ObjLongConsumer<C> */

    //TODO: Implement with currying?
    public default <D> Function<A, Function<B, LongConsumer>> fuseObjLongConsumer(
        ObjLongConsumer<C> next
    ) {
        return (A a) ->
            (B b) -> (long d) -> next.accept(resolve().apply(a).apply(b), d);
    }

    public default <D> Function<A, Function<B, LongConsumer>> fuse(
        ObjLongConsumer<C> next
    ) {
        return fuseObjLongConsumer(next);
    }
}
