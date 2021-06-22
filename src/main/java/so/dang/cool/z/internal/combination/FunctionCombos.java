package so.dang.cool.z.internal.combination;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
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

    public default <C> Function<A, C> fuseFunction(Function<B, C> next) {
        return (A a) -> next.apply(resolve().apply(a));
    }

    public default <C> Function<A, C> fuse(Function<B, C> next) {
        return fuseFunction(next);
    }

    public default <C> WithFunction<A, C> fusingFunction(Function<B, C> next) {
        return WithFunction.of(fuseFunction(next));
    }

    public default <C> WithFunction<A, C> fusing(Function<B, C> next) {
        return fusingFunction(next);
    }

    /* Function<A, B> -> BiFunction<B, C, D> */

    public default <C, D> Function<A, Function<C, D>> fuseBiFunction(
        BiFunction<B, C, D> next
    ) {
        return (A a) -> (C c) -> next.apply(resolve().apply(a), c);
    }

    public default <C, D> Function<A, Function<C, D>> fuse(
        BiFunction<B, C, D> next
    ) {
        return fuseBiFunction(next);
    }

    public default <C, D> WithBiFunction<A, C, D> fusingBiFunction(
        BiFunction<B, C, D> next
    ) {
        return WithBiFunction.of(fuseBiFunction(next));
    }

    public default <C, D> WithBiFunction<A, C, D> fusing(
        BiFunction<B, C, D> next
    ) {
        return fusingBiFunction(next);
    }

    /* Function<A, B> -> BiFunction<B, C, D>, C */

    public default <C, D> Function<A, D> fuseBiFunction(
        BiFunction<B, C, D> next,
        C secondArg
    ) {
        return (A a) -> next.apply(resolve().apply(a), secondArg);
    }

    public default <C, D> Function<A, D> fuse(
        BiFunction<B, C, D> next,
        C secondArg
    ) {
        return fuseBiFunction(next, secondArg);
    }

    public default <C, D> WithFunction<A, D> fusingBiFunction(
        BiFunction<B, C, D> next,
        C secondArg
    ) {
        return WithFunction.of(fuseBiFunction(next, secondArg));
    }

    public default <C, D> WithFunction<A, D> fusing(
        BiFunction<B, C, D> next,
        C secondArg
    ) {
        return fusingBiFunction(next, secondArg);
    }

    /* Function<A, B> -> DoubleFunction<B> */

    public default ToDoubleFunction<A> fuseToDoubleFunction(
        ToDoubleFunction<B> next
    ) {
        return (A a) -> next.applyAsDouble(resolve().apply(a));
    }

    public default ToDoubleFunction<A> fuse(ToDoubleFunction<B> next) {
        return fuseToDoubleFunction(next);
    }

    public default WithToDoubleFunction<A> fusingToDoubleFunction(
        ToDoubleFunction<B> next
    ) {
        return WithToDoubleFunction.of(fuseToDoubleFunction(next));
    }

    public default WithToDoubleFunction<A> fusing(ToDoubleFunction<B> next) {
        return fusingToDoubleFunction(next);
    }

    /* Function<A, B> -> ToDoubleBiFunction<B, C> */

    public default <C> Function<A, ToDoubleFunction<C>> fuseToDoubleBiFunction(
        ToDoubleBiFunction<B, C> next
    ) {
        return (A a) -> (C c) -> next.applyAsDouble(resolve().apply(a), c);
    }

    public default <C> Function<A, ToDoubleFunction<C>> fuse(
        ToDoubleBiFunction<B, C> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    public default <C> WithToDoubleBiFunction<A, C> fusingToDoubleBiFunction(
        ToDoubleBiFunction<B, C> next
    ) {
        return WithToDoubleBiFunction.of(fuseToDoubleBiFunction(next));
    }

    public default <C> WithToDoubleBiFunction<A, C> fusing(
        ToDoubleBiFunction<B, C> next
    ) {
        return fusingToDoubleBiFunction(next);
    }

    /* Function<A, B> -> ToDoubleBiFunction<B>, B */

    public default <C> ToDoubleFunction<A> fuseToDoubleBiFunction(
        ToDoubleBiFunction<B, C> next,
        C secondArg
    ) {
        return (A a) -> next.applyAsDouble(resolve().apply(a), secondArg);
    }

    public default <C> ToDoubleFunction<A> fuse(
        ToDoubleBiFunction<B, C> next,
        C secondArg
    ) {
        return fuseToDoubleBiFunction(next, secondArg);
    }

    public default <C> WithToDoubleFunction<A> fusingToDoubleBiFunction(
        ToDoubleBiFunction<B, C> next,
        C secondArg
    ) {
        return WithToDoubleFunction.of(fuseToDoubleBiFunction(next, secondArg));
    }

    public default <C> WithToDoubleFunction<A> fusing(
        ToDoubleBiFunction<B, C> next,
        C secondArg
    ) {
        return fusingToDoubleBiFunction(next, secondArg);
    }

    /* Function<A, B> -> ToIntFunction<B> */

    public default ToIntFunction<A> fuseToIntFunction(ToIntFunction<B> next) {
        return (A a) -> next.applyAsInt(resolve().apply(a));
    }

    public default ToIntFunction<A> fuse(ToIntFunction<B> next) {
        return fuseToIntFunction(next);
    }

    public default WithToIntFunction<A> fusingToIntFunction(
        ToIntFunction<B> next
    ) {
        return WithToIntFunction.of(fuseToIntFunction(next));
    }

    public default WithToIntFunction<A> fusing(ToIntFunction<B> next) {
        return fusingToIntFunction(next);
    }

    /* Function<A, B> -> ToIntBiFunction<B, C> */

    public default <C> Function<A, ToIntFunction<C>> fuseToIntBiFunction(
        ToIntBiFunction<B, C> next
    ) {
        return (A a) -> (C c) -> next.applyAsInt(resolve().apply(a), c);
    }

    public default <C> Function<A, ToIntFunction<C>> fuse(
        ToIntBiFunction<B, C> next
    ) {
        return fuseToIntBiFunction(next);
    }

    public default <C> WithToIntBiFunction<A, C> fusingToIntBiFunction(
        ToIntBiFunction<B, C> next
    ) {
        return WithToIntBiFunction.of(fuseToIntBiFunction(next));
    }

    public default <C> WithToIntBiFunction<A, C> fusing(
        ToIntBiFunction<B, C> next
    ) {
        return fusingToIntBiFunction(next);
    }

    /* Function<A, B> -> ToIntBiFunction<B>, B */

    public default <C> ToIntFunction<A> fuseToIntBiFunction(
        ToIntBiFunction<B, C> next,
        C secondArg
    ) {
        return (A a) -> next.applyAsInt(resolve().apply(a), secondArg);
    }

    public default <C> ToIntFunction<A> fuse(
        ToIntBiFunction<B, C> next,
        C secondArg
    ) {
        return fuseToIntBiFunction(next, secondArg);
    }

    public default <C> WithToIntFunction<A> fusingToIntBiFunction(
        ToIntBiFunction<B, C> next,
        C secondArg
    ) {
        return WithToIntFunction.of(fuseToIntBiFunction(next, secondArg));
    }

    public default <C> WithToIntFunction<A> fusing(
        ToIntBiFunction<B, C> next,
        C secondArg
    ) {
        return fusingToIntBiFunction(next, secondArg);
    }

    /* Function<A, B> -> ToLongFunction<B> */

    public default ToLongFunction<A> fuseToLongFunction(
        ToLongFunction<B> next
    ) {
        return (A a) -> next.applyAsLong(resolve().apply(a));
    }

    public default ToLongFunction<A> fuse(ToLongFunction<B> next) {
        return fuseToLongFunction(next);
    }

    public default WithToLongFunction<A> fusingToLongFunction(
        ToLongFunction<B> next
    ) {
        return WithToLongFunction.of(fuseToLongFunction(next));
    }

    public default WithToLongFunction<A> fusing(ToLongFunction<B> next) {
        return fusingToLongFunction(next);
    }

    /* Function<A, B> -> ToLongBiFunction<B, C> */

    public default <C> Function<A, ToLongFunction<C>> fuseToLongBiFunction(
        ToLongBiFunction<B, C> next
    ) {
        return (A a) -> (C c) -> next.applyAsLong(resolve().apply(a), c);
    }

    public default <C> Function<A, ToLongFunction<C>> fuse(
        ToLongBiFunction<B, C> next
    ) {
        return fuseToLongBiFunction(next);
    }

    public default <C> WithToLongBiFunction<A, C> fusingToLongBiFunction(
        ToLongBiFunction<B, C> next
    ) {
        return WithToLongBiFunction.of(fuseToLongBiFunction(next));
    }

    public default <C> WithToLongBiFunction<A, C> fusing(
        ToLongBiFunction<B, C> next
    ) {
        return fusingToLongBiFunction(next);
    }

    /* Function<A, B> -> ToLongBiFunction<B>, B */

    public default <C> ToLongFunction<A> fuseToLongBiFunction(
        ToLongBiFunction<B, C> next,
        C secondArg
    ) {
        return (A a) -> next.applyAsLong(resolve().apply(a), secondArg);
    }

    public default <C> ToLongFunction<A> fuse(
        ToLongBiFunction<B, C> next,
        C secondArg
    ) {
        return fuseToLongBiFunction(next, secondArg);
    }

    public default <C> WithToLongFunction<A> fusingToLongBiFunction(
        ToLongBiFunction<B, C> next,
        C secondArg
    ) {
        return WithToLongFunction.of(fuseToLongBiFunction(next, secondArg));
    }

    public default <C> WithToLongFunction<A> fusing(
        ToLongBiFunction<B, C> next,
        C secondArg
    ) {
        return fusingToLongBiFunction(next, secondArg);
    }

    /* Function<A, B> -> Predicate<B> */

    public default Predicate<A> fusePredicate(Predicate<B> next) {
        return (A a) -> next.test(resolve().apply(a));
    }

    public default Predicate<A> fuse(Predicate<B> next) {
        return fusePredicate(next);
    }

    public default WithPredicate<A> fusingPredicate(Predicate<B> next) {
        return WithPredicate.of(fusePredicate(next));
    }

    public default WithPredicate<A> fusing(Predicate<B> next) {
        return fusingPredicate(next);
    }

    /* Function<A, B> -> BiPredicate<B, C> */

    public default <C> Function<A, Predicate<C>> fuseBiPredicate(
        BiPredicate<B, C> next
    ) {
        return (A a) -> (C c) -> next.test(resolve().apply(a), c);
    }

    public default <C> Function<A, Predicate<C>> fuse(BiPredicate<B, C> next) {
        return fuseBiPredicate(next);
    }

    public default <C> WithBiPredicate<A, C> fusingBiPredicate(
        BiPredicate<B, C> next
    ) {
        return WithBiPredicate.of(
            (A a, C c) -> next.test(resolve().apply(a), c)
        );
    }

    public default <C> WithBiPredicate<A, C> fusing(BiPredicate<B, C> next) {
        return fusingBiPredicate(next);
    }

    /* Function<A, B> -> BiPredicate<B, C>, C */

    public default <C> Predicate<A> fuseBiPredicate(
        BiPredicate<B, C> next,
        C secondArg
    ) {
        return (A a) -> next.test(resolve().apply(a), secondArg);
    }

    public default <C> Predicate<A> fuse(BiPredicate<B, C> next, C secondArg) {
        return fuseBiPredicate(next, secondArg);
    }

    public default <C> WithPredicate<A> fusingBiPredicate(
        BiPredicate<B, C> next,
        C secondArg
    ) {
        return WithPredicate.of(fuseBiPredicate(next, secondArg));
    }

    public default <C> WithPredicate<A> fusing(
        BiPredicate<B, C> next,
        C secondArg
    ) {
        return fusingBiPredicate(next, secondArg);
    }

    /* Function<A, B> -> Consumer<B> */

    public default Consumer<A> fuseConsumer(Consumer<B> next) {
        return (A a) -> next.accept(resolve().apply(a));
    }

    public default Consumer<A> fuse(Consumer<B> next) {
        return fuseConsumer(next);
    }

    public default WithConsumer<A> fusingConsumer(Consumer<B> next) {
        return WithConsumer.of(fuseConsumer(next));
    }

    public default WithConsumer<A> fusing(Consumer<B> next) {
        return fusingConsumer(next);
    }

    /* Function<A, B> -> BiConsumer<B, C> */

    public default <C> Function<A, Consumer<C>> fuseBiConsumer(
        BiConsumer<B, C> next
    ) {
        return (A a) -> (C c) -> next.accept(resolve().apply(a), c);
    }

    public default <C> Function<A, Consumer<C>> fuse(BiConsumer<B, C> next) {
        return fuseBiConsumer(next);
    }

    public default <C> WithBiConsumer<A, C> fusingBiConsumer(
        BiConsumer<B, C> next
    ) {
        return WithBiConsumer.of(fuseBiConsumer(next));
    }

    public default <C> WithBiConsumer<A, C> fusing(BiConsumer<B, C> next) {
        return fusingBiConsumer(next);
    }

    /* Function<A, B> -> BiConsumer<B, C>, C */

    public default <C> Consumer<A> fuseBiConsumer(
        BiConsumer<B, C> next,
        C secondArg
    ) {
        return (A a) -> next.accept(resolve().apply(a), secondArg);
    }

    public default <C> Consumer<A> fuse(BiConsumer<B, C> next, C secondArg) {
        return fuseBiConsumer(next, secondArg);
    }

    public default <C> WithConsumer<A> fusingBiConsumer(
        BiConsumer<B, C> next,
        C secondArg
    ) {
        return WithConsumer.of(fuseBiConsumer(next, secondArg));
    }

    public default <C> WithConsumer<A> fusing(
        BiConsumer<B, C> next,
        C secondArg
    ) {
        return fusingBiConsumer(next, secondArg);
    }

    /* Function<A, B> -> ObjDoubleConsumer<B> */

    public default <C> Function<A, DoubleConsumer> fuseObjDoubleConsumer(
        ObjDoubleConsumer<B> next
    ) {
        return (A a) -> (double d) -> next.accept(resolve().apply(a), d);
    }

    public default <C> Function<A, DoubleConsumer> fuse(
        ObjDoubleConsumer<B> next
    ) {
        return fuseObjDoubleConsumer(next);
    }

    public default <C> WithObjDoubleConsumer<A> fusingObjDoubleConsumer(
        ObjDoubleConsumer<B> next
    ) {
        return WithObjDoubleConsumer.of(
            (A a, double d) -> next.accept(resolve().apply(a), d)
        );
    }

    public default <C> WithObjDoubleConsumer<A> fusing(
        ObjDoubleConsumer<B> next
    ) {
        return fusingObjDoubleConsumer(next);
    }

    /* Function<A, B> -> ObjDoubleConsumer<B>, double */

    public default <C> Consumer<A> fuseObjDoubleConsumer(
        ObjDoubleConsumer<B> next,
        double d
    ) {
        return (A a) -> next.accept(resolve().apply(a), d);
    }

    public default <C> Consumer<A> fuse(ObjDoubleConsumer<B> next, double d) {
        return fuseObjDoubleConsumer(next, d);
    }

    public default <C> WithConsumer<A> fusingObjDoubleConsumer(
        ObjDoubleConsumer<B> next,
        double d
    ) {
        return WithConsumer.of(fuseObjDoubleConsumer(next, d));
    }

    public default <C> WithConsumer<A> fusing(
        ObjDoubleConsumer<B> next,
        double d
    ) {
        return fusingObjDoubleConsumer(next, d);
    }

    /* Function<A, B> -> ObjIntConsumer<B> */

    public default <C> Function<A, IntConsumer> fuseObjIntConsumer(
        ObjIntConsumer<B> next
    ) {
        return (A a) -> (int i) -> next.accept(resolve().apply(a), i);
    }

    public default <C> Function<A, IntConsumer> fuse(ObjIntConsumer<B> next) {
        return fuseObjIntConsumer(next);
    }

    public default <C> WithObjIntConsumer<A> fusingObjIntConsumer(
        ObjIntConsumer<B> next
    ) {
        return WithObjIntConsumer.of(
            (A a, int i) -> next.accept(resolve().apply(a), i)
        );
    }

    public default <C> WithObjIntConsumer<A> fusing(ObjIntConsumer<B> next) {
        return fusingObjIntConsumer(next);
    }

    /* Function<A, B> -> ObjIntConsumer<B>, int */

    public default <C> Consumer<A> fuseObjIntConsumer(
        ObjIntConsumer<B> next,
        int i
    ) {
        return (A a) -> next.accept(resolve().apply(a), i);
    }

    public default <C> Consumer<A> fuse(ObjIntConsumer<B> next, int i) {
        return fuseObjIntConsumer(next, i);
    }

    public default <C> WithConsumer<A> fusingObjIntConsumer(
        ObjIntConsumer<B> next,
        int i
    ) {
        return WithConsumer.of(fuseObjIntConsumer(next, i));
    }

    public default <C> WithConsumer<A> fusing(ObjIntConsumer<B> next, int i) {
        return fusingObjIntConsumer(next, i);
    }

    /* Function<A, B> -> ObjLongConsumer<B> */

    public default <C> Function<A, LongConsumer> fuseObjLongConsumer(
        ObjLongConsumer<B> next
    ) {
        return (A a) -> (long n) -> next.accept(resolve().apply(a), n);
    }

    public default <C> Function<A, LongConsumer> fuse(ObjLongConsumer<B> next) {
        return fuseObjLongConsumer(next);
    }

    public default <C> WithObjLongConsumer<A> fusingObjLongConsumer(
        ObjLongConsumer<B> next
    ) {
        return WithObjLongConsumer.of(
            (A a, long n) -> next.accept(resolve().apply(a), n)
        );
    }

    public default <C> WithObjLongConsumer<A> fusing(ObjLongConsumer<B> next) {
        return fusingObjLongConsumer(next);
    }

    /* Function<A, B> -> ObjLongConsumer<B>, long */

    public default <C> Consumer<A> fuseObjLongConsumer(
        ObjLongConsumer<B> next,
        long n
    ) {
        return (A a) -> next.accept(resolve().apply(a), n);
    }

    public default <C> Consumer<A> fuse(ObjLongConsumer<B> next, long n) {
        return fuseObjLongConsumer(next, n);
    }

    public default <C> WithConsumer<A> fusingObjLongConsumer(
        ObjLongConsumer<B> next,
        long n
    ) {
        return WithConsumer.of(fuseObjLongConsumer(next, n));
    }

    public default <C> WithConsumer<A> fusing(ObjLongConsumer<B> next, long n) {
        return fusingObjLongConsumer(next, n);
    }

    /* Function<A, B> -> UnaryOperator<B> */

    public default Function<A, B> fuseUnaryOperator(UnaryOperator<B> next) {
        return (A a) -> next.apply(resolve().apply(a));
    }

    public default Function<A, B> fuse(UnaryOperator<B> next) {
        return fuseUnaryOperator(next);
    }

    public default WithFunction<A, B> fusingUnaryOperator(
        UnaryOperator<B> next
    ) {
        return WithFunction.of(fuseUnaryOperator(next));
    }

    public default WithFunction<A, B> fusing(UnaryOperator<B> next) {
        return fusingUnaryOperator(next);
    }

    /* Function<A, B> -> BinaryOperator<B> */

    public default Function<A, UnaryOperator<B>> fuseBinaryOperator(
        BinaryOperator<B> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a), b);
    }

    public default Function<A, UnaryOperator<B>> fuse(BinaryOperator<B> next) {
        return fuseBinaryOperator(next);
    }

    public default WithBiFunction<A, B, B> fusingBinaryOperator(
        BinaryOperator<B> next
    ) {
        // TODO: simplify with currying
        return WithBiFunction.of(
            (A a, B b) -> next.apply(resolve().apply(a), b)
        );
    }

    public default WithBiFunction<A, B, B> fusing(BinaryOperator<B> next) {
        return fusingBinaryOperator(next);
    }

    /* Function<A, B> -> BinaryOperator<B> */

    public default Function<A, B> fuseBinaryOperator(
        BinaryOperator<B> next,
        B secondArg
    ) {
        return (A a) -> next.apply(resolve().apply(a), secondArg);
    }

    public default Function<A, B> fuse(BinaryOperator<B> next, B secondArg) {
        return fuseBinaryOperator(next, secondArg);
    }

    public default WithFunction<A, B> fusingBinaryOperator(
        BinaryOperator<B> next,
        B secondArg
    ) {
        return WithFunction.of(fuseBinaryOperator(next, secondArg));
    }

    public default WithFunction<A, B> fusing(
        BinaryOperator<B> next,
        B secondArg
    ) {
        return fusingBinaryOperator(next, secondArg);
    }
    /* TODO: Function<A, B> -> [Multi]Function<B...> */
}
