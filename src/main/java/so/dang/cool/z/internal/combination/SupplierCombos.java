package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
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
import so.dang.cool.z.function.Operator;
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
import so.dang.cool.z.internal.combination.Combine.WithUnaryOperator;

interface SupplierCombos<A> {
    Supplier<A> resolve();

    /* Supplier<A> -> Function<A, B> */

    public default <B> Supplier<B> fuseFunction(Function<A, B> next) {
        return () -> next.apply(resolve().get());
    }

    public default <B> Supplier<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithSupplier<B> fusingFunction(Function<A, B> next) {
        return WithSupplier.of(fuse(next));
    }

    public default <B> WithSupplier<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }

    /* Supplier<A> -> BiFunction<A, B, C> */

    public default <B, C> Function<B, C> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return (B b) -> next.apply(resolve().get(), b);
    }

    public default <B, C> Function<B, C> fuse(BiFunction<A, B, C> next) {
        return fuseBiFunction(next);
    }

    public default <B, C> WithFunction<B, C> fusingBiFunction(
        BiFunction<A, B, C> next
    ) {
        return WithFunction.of(fuseBiFunction(next));
    }

    public default <B, C> WithFunction<B, C> fusing(BiFunction<A, B, C> next) {
        return fusingBiFunction(next);
    }

    public default <B, C> Supplier<C> fuseBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return () -> next.apply(resolve().get(), secondArg);
    }

    public default <B, C> Supplier<C> fuse(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fuseBiFunction(next, secondArg);
    }

    public default <B, C> WithSupplier<C> fusingBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return WithSupplier.of(fuseBiFunction(next, secondArg));
    }

    public default <B, C> WithSupplier<C> fusing(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fusingBiFunction(next, secondArg);
    }

    /* Supplier<A> -> ToDoubleFunction<A> */

    public default DoubleSupplier fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return () -> next.applyAsDouble(resolve().get());
    }

    public default DoubleSupplier fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    public default WithDoubleSupplier fusingToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithDoubleSupplier.of(fuseToDoubleFunction(next));
    }

    public default WithDoubleSupplier fusing(ToDoubleFunction<A> next) {
        return fusingToDoubleFunction(next);
    }

    /* Supplier<A> -> ToDoubleBiFunction<A, B> */

    public default <B> ToDoubleFunction<B> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return (B b) -> next.applyAsDouble(resolve().get(), b);
    }

    public default <B> ToDoubleFunction<B> fuse(ToDoubleBiFunction<A, B> next) {
        return fuseToDoubleBiFunction(next);
    }

    public default <B> WithToDoubleFunction<B> fusingToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return WithToDoubleFunction.of(fuseToDoubleBiFunction(next));
    }

    public default <B> WithToDoubleFunction<B> fusing(
        ToDoubleBiFunction<A, B> next
    ) {
        return fusingToDoubleBiFunction(next);
    }

    public default <B> DoubleSupplier fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return () -> next.applyAsDouble(resolve().get(), secondArg);
    }

    public default <B> DoubleSupplier fuse(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToDoubleBiFunction(next, secondArg);
    }

    public default <B> WithDoubleSupplier fusingToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return WithDoubleSupplier.of(fuseToDoubleBiFunction(next, secondArg));
    }

    public default <B> WithDoubleSupplier fusing(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToDoubleBiFunction(next, secondArg);
    }

    /* Supplier<A> -> ToIntFunction<A> */

    public default IntSupplier fuseToIntFunction(ToIntFunction<A> next) {
        return () -> next.applyAsInt(resolve().get());
    }

    public default IntSupplier fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    public default WithIntSupplier fusingToIntFunction(ToIntFunction<A> next) {
        return WithIntSupplier.of(fuseToIntFunction(next));
    }

    public default WithIntSupplier fusing(ToIntFunction<A> next) {
        return fusingToIntFunction(next);
    }

    /* Supplier<A> -> ToIntBiFunction<A, B> */

    public default <B> ToIntFunction<B> fuseToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return (B b) -> next.applyAsInt(resolve().get(), b);
    }

    public default <B> ToIntFunction<B> fuse(ToIntBiFunction<A, B> next) {
        return fuseToIntBiFunction(next);
    }

    public default <B> WithToIntFunction<B> fusingToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return WithToIntFunction.of(fuseToIntBiFunction(next));
    }

    public default <B> WithToIntFunction<B> fusing(ToIntBiFunction<A, B> next) {
        return fusingToIntBiFunction(next);
    }

    public default <B> IntSupplier fuseToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return () -> next.applyAsInt(resolve().get(), secondArg);
    }

    public default <B> IntSupplier fuse(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToIntBiFunction(next, secondArg);
    }

    public default <B> WithIntSupplier fusingToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return WithIntSupplier.of(fuseToIntBiFunction(next, secondArg));
    }

    public default <B> WithIntSupplier fusing(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToIntBiFunction(next, secondArg);
    }

    /* Supplier<A> -> ToLongFunction<A> */

    public default LongSupplier fuseToLongFunction(ToLongFunction<A> next) {
        return () -> next.applyAsLong(resolve().get());
    }

    public default LongSupplier fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    public default WithLongSupplier fusingToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithLongSupplier.of(fuseToLongFunction(next));
    }

    public default WithLongSupplier fusing(ToLongFunction<A> next) {
        return fusingToLongFunction(next);
    }

    /* Supplier<A> -> ToLongBiFunction<A, B> */

    public default <B> ToLongFunction<B> fuseToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return (B b) -> next.applyAsLong(resolve().get(), b);
    }

    public default <B> ToLongFunction<B> fuse(ToLongBiFunction<A, B> next) {
        return fuseToLongBiFunction(next);
    }

    public default <B> WithToLongFunction<B> fusingToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return WithToLongFunction.of(fuseToLongBiFunction(next));
    }

    public default <B> WithToLongFunction<B> fusing(
        ToLongBiFunction<A, B> next
    ) {
        return fusingToLongBiFunction(next);
    }

    public default <B> LongSupplier fuseToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return () -> next.applyAsLong(resolve().get(), secondArg);
    }

    public default <B> LongSupplier fuse(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToLongBiFunction(next, secondArg);
    }

    public default <B> WithLongSupplier fusingToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return WithLongSupplier.of(fuseToLongBiFunction(next, secondArg));
    }

    public default <B> WithLongSupplier fusing(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToLongBiFunction(next, secondArg);
    }

    /* Supplier<A> -> Predicate<A> */

    public default BooleanSupplier fusePredicate(Predicate<A> next) {
        return () -> next.test(resolve().get());
    }

    public default BooleanSupplier fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    public default WithBooleanSupplier fusingPredicate(Predicate<A> next) {
        return WithBooleanSupplier.of(fusePredicate(next));
    }

    public default WithBooleanSupplier fusing(Predicate<A> next) {
        return fusingPredicate(next);
    }

    /* Supplier<A> -> BiPredicate<A, B> */

    public default <B> Predicate<B> fuseBiPredicate(BiPredicate<A, B> next) {
        return (B b) -> next.test(resolve().get(), b);
    }

    public default <B> Predicate<B> fuse(BiPredicate<A, B> next) {
        return fuseBiPredicate(next);
    }

    public default <B> WithPredicate<B> fusingBiPredicate(
        BiPredicate<A, B> next
    ) {
        return WithPredicate.of(fuseBiPredicate(next));
    }

    public default <B> WithPredicate<B> fusing(BiPredicate<A, B> next) {
        return fusingBiPredicate(next);
    }

    public default <B> BooleanSupplier fuseBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return () -> next.test(resolve().get(), secondArg);
    }

    public default <B> BooleanSupplier fuse(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return fuseBiPredicate(next, secondArg);
    }

    public default <B> WithBooleanSupplier fusingBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return WithBooleanSupplier.of(fuseBiPredicate(next, secondArg));
    }

    public default <B> WithBooleanSupplier fusing(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return fusingBiPredicate(next, secondArg);
    }

    /* Supplier<A> -> Consumer<A> */

    public default Operator fuseConsumer(Consumer<A> next) {
        return () -> next.accept(resolve().get());
    }

    public default Operator fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    public default WithOperator fusingConsumer(Consumer<A> next) {
        return WithOperator.of(fuseConsumer(next));
    }

    public default WithOperator fusing(Consumer<A> next) {
        return fusingConsumer(next);
    }

    /* Supplier<A> -> BiConsumer<A, B> */

    public default <B> Consumer<B> fuseBiConsumer(BiConsumer<A, B> next) {
        return (B b) -> next.accept(resolve().get(), b);
    }

    public default <B> Consumer<B> fuse(BiConsumer<A, B> next) {
        return fuseBiConsumer(next);
    }

    public default <B> WithConsumer<B> fusingBiConsumer(BiConsumer<A, B> next) {
        return WithConsumer.of(fuseBiConsumer(next));
    }

    public default <B> WithConsumer<B> fusing(BiConsumer<A, B> next) {
        return fusingBiConsumer(next);
    }

    public default <B> Operator fuseBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return () -> next.accept(resolve().get(), secondArg);
    }

    public default <B> Operator fuse(BiConsumer<A, B> next, B secondArg) {
        return fuseBiConsumer(next, secondArg);
    }

    public default <B> WithOperator fusingBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return WithOperator.of(fuseBiConsumer(next, secondArg));
    }

    public default <B> WithOperator fusing(BiConsumer<A, B> next, B secondArg) {
        return fusingBiConsumer(next, secondArg);
    }

    /* Supplier<A> -> ObjDoubleConsumer<A, B> */

    public default DoubleConsumer fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return (double d) -> next.accept(resolve().get(), d);
    }

    public default DoubleConsumer fuse(ObjDoubleConsumer<A> next) {
        return fuseObjDoubleConsumer(next);
    }

    public default WithDoubleConsumer fusingObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return WithDoubleConsumer.of(fuseObjDoubleConsumer(next));
    }

    public default WithDoubleConsumer fusing(ObjDoubleConsumer<A> next) {
        return fusingObjDoubleConsumer(next);
    }

    public default Operator fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return () -> next.accept(resolve().get(), secondArg);
    }

    public default Operator fuse(ObjDoubleConsumer<A> next, double secondArg) {
        return fuseObjDoubleConsumer(next, secondArg);
    }

    public default WithOperator fusingObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return WithOperator.of(fuseObjDoubleConsumer(next, secondArg));
    }

    public default WithOperator fusing(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fusingObjDoubleConsumer(next, secondArg);
    }

    /* Supplier<A> -> ObjIntConsumer<A, B> */

    public default IntConsumer fuseObjIntConsumer(ObjIntConsumer<A> next) {
        return (int i) -> next.accept(resolve().get(), i);
    }

    public default IntConsumer fuse(ObjIntConsumer<A> next) {
        return fuseObjIntConsumer(next);
    }

    public default WithIntConsumer fusingObjIntConsumer(
        ObjIntConsumer<A> next
    ) {
        return WithIntConsumer.of(fuseObjIntConsumer(next));
    }

    public default WithIntConsumer fusing(ObjIntConsumer<A> next) {
        return fusingObjIntConsumer(next);
    }

    public default Operator fuseObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return () -> next.accept(resolve().get(), secondArg);
    }

    public default Operator fuse(ObjIntConsumer<A> next, int secondArg) {
        return fuseObjIntConsumer(next, secondArg);
    }

    public default WithOperator fusingObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return WithOperator.of(fuseObjIntConsumer(next, secondArg));
    }

    public default WithOperator fusing(ObjIntConsumer<A> next, int secondArg) {
        return fusingObjIntConsumer(next, secondArg);
    }

    /* Supplier<A> -> ObjLongConsumer<A, B> */

    public default LongConsumer fuseObjLongConsumer(ObjLongConsumer<A> next) {
        return (long n) -> next.accept(resolve().get(), n);
    }

    public default LongConsumer fuse(ObjLongConsumer<A> next) {
        return fuseObjLongConsumer(next);
    }

    public default WithLongConsumer fusingObjLongConsumer(
        ObjLongConsumer<A> next
    ) {
        return WithLongConsumer.of(fuseObjLongConsumer(next));
    }

    public default WithLongConsumer fusing(ObjLongConsumer<A> next) {
        return fusingObjLongConsumer(next);
    }

    public default Operator fuseObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return () -> next.accept(resolve().get(), secondArg);
    }

    public default Operator fuse(ObjLongConsumer<A> next, long secondArg) {
        return fuseObjLongConsumer(next, secondArg);
    }

    public default WithOperator fusingObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return WithOperator.of(fuseObjLongConsumer(next, secondArg));
    }

    public default WithOperator fusing(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return fusingObjLongConsumer(next, secondArg);
    }

    /* Supplier<A> -> UnaryOperator<A> */

    public default Supplier<A> fuseUnaryOperator(UnaryOperator<A> next) {
        return () -> next.apply(resolve().get());
    }

    public default Supplier<A> fuse(UnaryOperator<A> next) {
        return fuseUnaryOperator(next);
    }

    public default WithSupplier<A> fusingUnaryOperator(UnaryOperator<A> next) {
        return WithSupplier.of(fuseUnaryOperator(next));
    }

    public default WithSupplier<A> fusing(UnaryOperator<A> next) {
        return fusingUnaryOperator(next);
    }

    /* Supplier<A> -> BinaryOperator<A, A> */

    public default UnaryOperator<A> fuseBinaryOperator(BinaryOperator<A> next) {
        return (A a) -> next.apply(resolve().get(), a);
    }

    public default UnaryOperator<A> fuse(BinaryOperator<A> next) {
        return fuseBinaryOperator(next);
    }

    public default WithUnaryOperator<A> fusingBinaryOperator(
        BinaryOperator<A> next
    ) {
        return WithUnaryOperator.of(fuseBinaryOperator(next));
    }

    public default WithUnaryOperator<A> fusing(BinaryOperator<A> next) {
        return fusingBinaryOperator(next);
    }

    public default Supplier<A> fuseBinaryOperator(
        BinaryOperator<A> next,
        A secondArg
    ) {
        return () -> next.apply(resolve().get(), secondArg);
    }

    public default Supplier<A> fuse(BinaryOperator<A> next, A secondArg) {
        return fuseBinaryOperator(next, secondArg);
    }

    public default WithSupplier<A> fusingBinaryOperator(
        BinaryOperator<A> next,
        A secondArg
    ) {
        return WithSupplier.of(fuseBinaryOperator(next, secondArg));
    }

    public default WithSupplier<A> fusing(BinaryOperator<A> next, A secondArg) {
        return fusingBinaryOperator(next, secondArg);
    }
}
