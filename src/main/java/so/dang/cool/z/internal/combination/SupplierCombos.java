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
import java.util.function.Supplier;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongBiFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleConsumer;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithFunction;
import so.dang.cool.z.internal.combination.Combine.WithIntConsumer;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongConsumer;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithPredicate;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;
import so.dang.cool.z.internal.combination.Combine.WithToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithToLongFunction;

interface SupplierCombos<A> {
    Supplier<A> resolve();

    /* Supplier<A> -> Function<A, B> */

    public default <B> WithSupplier<B> fuseFunction(Function<A, B> next) {
        return Combine.WithSupplier.of(() -> next.apply(resolve().get()));
    }

    public default <B> WithSupplier<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    /* Supplier<A> -> BiFunction<A, B, C> */

    public default <B, C> WithFunction<B, C> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return WithFunction.of((B b) -> next.apply(resolve().get(), b));
    }

    public default <B, C> WithFunction<B, C> fuse(BiFunction<A, B, C> next) {
        return fuseBiFunction(next);
    }

    /* Supplier<A> -> ToDoubleFunction<A> */

    public default WithDoubleSupplier fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithDoubleSupplier.of(() -> next.applyAsDouble(resolve().get()));
    }

    public default WithDoubleSupplier fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    /* Supplier<A> -> ToDoubleBiFunction<A, B> */

    public default <B> WithToDoubleFunction<B> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return WithToDoubleFunction.of(
            (B b) -> next.applyAsDouble(resolve().get(), b)
        );
    }

    public default <B> WithToDoubleFunction<B> fuse(
        ToDoubleBiFunction<A, B> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    /* Supplier<A> -> ToIntFunction<A> */

    public default WithIntSupplier fuseToIntFunction(ToIntFunction<A> next) {
        return WithIntSupplier.of(() -> next.applyAsInt(resolve().get()));
    }

    public default WithIntSupplier fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    /* Supplier<A> -> ToIntBiFunction<A, B> */

    public default <B> WithToIntFunction<B> fuseToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return WithToIntFunction.of(
            (B b) -> next.applyAsInt(resolve().get(), b)
        );
    }

    public default <B> WithToIntFunction<B> fuse(ToIntBiFunction<A, B> next) {
        return fuseToIntBiFunction(next);
    }

    /* Supplier<A> -> ToLongFunction<A> */

    public default WithLongSupplier fuseToLongFunction(ToLongFunction<A> next) {
        return WithLongSupplier.of(() -> next.applyAsLong(resolve().get()));
    }

    public default WithLongSupplier fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    /* Supplier<A> -> ToLongBiFunction<A, B> */

    public default <B> WithToLongFunction<B> fuseToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return WithToLongFunction.of(
            (B b) -> next.applyAsLong(resolve().get(), b)
        );
    }

    public default <B> WithToLongFunction<B> fuse(ToLongBiFunction<A, B> next) {
        return fuseToLongBiFunction(next);
    }

    /* Supplier<A> -> Predicate<A> */

    public default WithBooleanSupplier fusePredicate(Predicate<A> next) {
        return WithBooleanSupplier.of(() -> next.test(resolve().get()));
    }

    public default WithBooleanSupplier fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    /* Supplier<A> -> BiPredicate<A, B> */

    public default <B> WithPredicate<B> fuseBiPredicate(
        BiPredicate<A, B> next
    ) {
        return WithPredicate.of((B b) -> next.test(resolve().get(), b));
    }

    public default <B> WithPredicate<B> fuse(BiPredicate<A, B> next) {
        return fuseBiPredicate(next);
    }

    /* Supplier<A> -> Consumer<A> */

    public default WithOperator fuseConsumer(Consumer<A> next) {
        return WithOperator.of(() -> next.accept(resolve().get()));
    }

    public default WithOperator fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    /* Supplier<A> -> BiConsumer<A, B> */

    public default <B> WithConsumer<B> fuseBiConsumer(BiConsumer<A, B> next) {
        return WithConsumer.of((B b) -> next.accept(resolve().get(), b));
    }

    public default <B> WithConsumer<B> fuse(BiConsumer<A, B> next) {
        return fuseBiConsumer(next);
    }

    /* Supplier<A> -> ObjDoubleConsumer<A, B> */

    public default WithDoubleConsumer fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return WithDoubleConsumer.of(
            (double d) -> next.accept(resolve().get(), d)
        );
    }

    public default WithDoubleConsumer fuse(ObjDoubleConsumer<A> next) {
        return fuseObjDoubleConsumer(next);
    }

    /* Supplier<A> -> ObjIntConsumer<A, B> */

    public default WithIntConsumer fuseObjIntConsumer(ObjIntConsumer<A> next) {
        return WithIntConsumer.of((int i) -> next.accept(resolve().get(), i));
    }

    public default WithIntConsumer fuse(ObjIntConsumer<A> next) {
        return fuseObjIntConsumer(next);
    }

    /* Supplier<A> -> ObjLongConsumer<A, B> */

    public default WithLongConsumer fuseObjLongConsumer(
        ObjLongConsumer<A> next
    ) {
        return WithLongConsumer.of((long n) -> next.accept(resolve().get(), n));
    }

    public default WithLongConsumer fuse(ObjLongConsumer<A> next) {
        return fuseObjLongConsumer(next);
    }

    /* Supplier<A> -> UnaryOperator<A> */

    public default WithSupplier<A> fuseUnaryOperator(UnaryOperator<A> next) {
        return WithSupplier.of(() -> next.apply(resolve().get()));
    }

    public default WithSupplier<A> fuse(UnaryOperator<A> next) {
        return fuseUnaryOperator(next);
    }

    /* Supplier<A> -> BinaryOperator<A, A> */

    public default WithFunction<A, A> fuseBinaryOperator(
        BinaryOperator<A> next
    ) {
        return WithFunction.of((A a) -> next.apply(resolve().get(), a));
    }

    public default WithFunction<A, A> fuse(BinaryOperator<A> next) {
        return fuseBinaryOperator(next);
    }
}
