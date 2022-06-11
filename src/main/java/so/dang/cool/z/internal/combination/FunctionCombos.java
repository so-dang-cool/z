package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
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
import java.util.function.UnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithBiConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithBiPredicate;
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithObjDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithObjIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithObjLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongBiFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface FunctionCombos<A, B> {
    Function<A, B> resolve();

    /* Function<A, B> -> Function<B, C> */

    public default <C> WithFunction<A, C> fuseFunction(Function<B, C> next) {
        return WithFunction.of((A a) -> next.apply(resolve().apply(a)));
    }

    public default <C> WithFunction<A, C> fuse(Function<B, C> next) {
        return fuseFunction(next);
    }

    /* Function<A, B> -> BiFunction<B, C, D> */

    public default <C, D> WithBiFunction<A, C, D> fuseBiFunction(
        BiFunction<B, C, D> next
    ) {
        return WithBiFunction.of(
            (A a) -> (C c) -> next.apply(resolve().apply(a), c)
        );
    }

    public default <C, D> WithBiFunction<A, C, D> fuse(
        BiFunction<B, C, D> next
    ) {
        return fuseBiFunction(next);
    }

    /* Function<A, B> -> DoubleFunction<B> */

    public default WithToDoubleFunction<A> fuseToDoubleFunction(
        ToDoubleFunction<B> next
    ) {
        return WithToDoubleFunction.of(
            (A a) -> next.applyAsDouble(resolve().apply(a))
        );
    }

    public default WithToDoubleFunction<A> fuse(ToDoubleFunction<B> next) {
        return fuseToDoubleFunction(next);
    }

    /* Function<A, B> -> ToDoubleBiFunction<B, C> */

    public default <C> WithToDoubleBiFunction<A, C> fuseToDoubleBiFunction(
        ToDoubleBiFunction<B, C> next
    ) {
        return WithToDoubleBiFunction.of(
            (A a, C c) -> next.applyAsDouble(resolve().apply(a), c)
        );
    }

    public default <C> WithToDoubleBiFunction<A, C> fuse(
        ToDoubleBiFunction<B, C> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    /* Function<A, B> -> ToIntFunction<B> */

    public default WithToIntFunction<A> fuseToIntFunction(
        ToIntFunction<B> next
    ) {
        return WithToIntFunction.of(
            (A a) -> next.applyAsInt(resolve().apply(a))
        );
    }

    public default WithToIntFunction<A> fuse(ToIntFunction<B> next) {
        return fuseToIntFunction(next);
    }

    /* Function<A, B> -> ToIntBiFunction<B, C> */

    public default <C> WithToIntBiFunction<A, C> fuseToIntBiFunction(
        ToIntBiFunction<B, C> next
    ) {
        return WithToIntBiFunction.of(
            (A a) -> (C c) -> next.applyAsInt(resolve().apply(a), c)
        );
    }

    public default <C> WithToIntBiFunction<A, C> fuse(
        ToIntBiFunction<B, C> next
    ) {
        return fuseToIntBiFunction(next);
    }

    /* Function<A, B> -> ToLongFunction<B> */

    public default WithToLongFunction<A> fuseToLongFunction(
        ToLongFunction<B> next
    ) {
        return WithToLongFunction.of(
            (A a) -> next.applyAsLong(resolve().apply(a))
        );
    }

    public default WithToLongFunction<A> fuse(ToLongFunction<B> next) {
        return fuseToLongFunction(next);
    }

    /* Function<A, B> -> ToLongBiFunction<B, C> */

    public default <C> WithToLongBiFunction<A, C> fuseToLongBiFunction(
        ToLongBiFunction<B, C> next
    ) {
        return WithToLongBiFunction.of(
            (A a) -> (C c) -> next.applyAsLong(resolve().apply(a), c)
        );
    }

    public default <C> WithToLongBiFunction<A, C> fuse(
        ToLongBiFunction<B, C> next
    ) {
        return fuseToLongBiFunction(next);
    }

    /* Function<A, B> -> Predicate<B> */

    public default WithPredicate<A> fusePredicate(Predicate<B> next) {
        return WithPredicate.of((A a) -> next.test(resolve().apply(a)));
    }

    public default WithPredicate<A> fuse(Predicate<B> next) {
        return fusePredicate(next);
    }

    /* Function<A, B> -> BiPredicate<B, C> */

    public default <C> WithBiPredicate<A, C> fuseBiPredicate(
        BiPredicate<B, C> next
    ) {
        return WithBiPredicate.of(
            (A a) -> (C c) -> next.test(resolve().apply(a), c)
        );
    }

    public default <C> WithBiPredicate<A, C> fuse(BiPredicate<B, C> next) {
        return fuseBiPredicate(next);
    }

    /* Function<A, B> -> Consumer<B> */

    public default WithConsumer<A> fuseConsumer(Consumer<B> next) {
        return WithConsumer.of((A a) -> next.accept(resolve().apply(a)));
    }

    public default WithConsumer<A> fuse(Consumer<B> next) {
        return fuseConsumer(next);
    }

    /* Function<A, B> -> BiConsumer<B, C> */

    public default <C> WithBiConsumer<A, C> fuseBiConsumer(
        BiConsumer<B, C> next
    ) {
        return WithBiConsumer.of(
            (A a) -> (C c) -> next.accept(resolve().apply(a), c)
        );
    }

    public default <C> WithBiConsumer<A, C> fuse(BiConsumer<B, C> next) {
        return fuseBiConsumer(next);
    }

    /* Function<A, B> -> ObjDoubleConsumer<B> */

    public default <C> WithObjDoubleConsumer<A> fuseObjDoubleConsumer(
        ObjDoubleConsumer<B> next
    ) {
        return WithObjDoubleConsumer.of(
            (A a, double d) -> next.accept(resolve().apply(a), d)
        );
    }

    public default <C> WithObjDoubleConsumer<A> fuse(
        ObjDoubleConsumer<B> next
    ) {
        return fuseObjDoubleConsumer(next);
    }

    /* Function<A, B> -> ObjIntConsumer<B> */

    public default <C> WithObjIntConsumer<A> fuseObjIntConsumer(
        ObjIntConsumer<B> next
    ) {
        return WithObjIntConsumer.of(
            (A a) -> (int i) -> next.accept(resolve().apply(a), i)
        );
    }

    public default <C> WithObjIntConsumer<A> fuse(ObjIntConsumer<B> next) {
        return fuseObjIntConsumer(next);
    }

    /* Function<A, B> -> ObjLongConsumer<B> */

    public default <C> WithObjLongConsumer<A> fuseObjLongConsumer(
        ObjLongConsumer<B> next
    ) {
        return WithObjLongConsumer.of(
            (A a, long n) -> next.accept(resolve().apply(a), n)
        );
    }

    public default <C> WithObjLongConsumer<A> fuse(ObjLongConsumer<B> next) {
        return fuseObjLongConsumer(next);
    }

    /* Function<A, B> -> UnaryOperator<B> */

    public default WithFunction<A, B> fuseUnaryOperator(UnaryOperator<B> next) {
        return WithFunction.of((A a) -> next.apply(resolve().apply(a)));
    }

    public default WithFunction<A, B> fuse(UnaryOperator<B> next) {
        return fuseUnaryOperator(next);
    }

    /* Function<A, B> -> BinaryOperator<B> */

    // Required? It's not possible for WithBifunction::of to accept both
    // Function<A, Function<B, C>> and Function<A, UnaryOperator<B>> because
    // They have the same erasure.
    public default WithBiFunction<A, B, B> fuseBinaryOperator(
        BinaryOperator<B> next
    ) {
        return WithBiFunction.of(
            (A a, B b) -> next.apply(resolve().apply(a), b)
        );
    }

    public default WithBiFunction<A, B, B> fuse(BinaryOperator<B> next) {
        return fuseBinaryOperator(next);
    }
}
