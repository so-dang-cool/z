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
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanConsumer;
import so.dang.cool.z.internal.combination.Combine.WithBooleanFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanPredicate;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToDoubleFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToIntFunction;
import so.dang.cool.z.internal.combination.Combine.WithBooleanToLongFunction;

interface BooleanFunctionCombos<A> {
    BooleanFunction<A> resolve();

    /* BooleanFunction<A> -> Function<A,B> */

    public default <B> BooleanFunction<B> fuseFunction(Function<A, B> next) {
        return (boolean b) -> next.apply(resolve().apply(b));
    }

    public default <B> BooleanFunction<B> fuse(Function<A, B> next) {
        return fuseFunction(next);
    }

    public default <B> WithBooleanFunction<B> fusingFunction(
        Function<A, B> next
    ) {
        return WithBooleanFunction.of(fuseFunction(next));
    }

    public default <B> WithBooleanFunction<B> fusing(Function<A, B> next) {
        return fusingFunction(next);
    }

    /* BooleanFunction<A> -> BiFunction<A,B,C> */

    public default <B, C> BooleanFunction<Function<B, C>> fuseBiFunction(
        BiFunction<A, B, C> next
    ) {
        return (boolean b1) -> (B b2) -> next.apply(resolve().apply(b1), b2);
    }

    public default <B, C> BooleanFunction<Function<B, C>> fuse(
        BiFunction<A, B, C> next
    ) {
        return fuseBiFunction(next);
    }

    // TODO: Implement with currying
    // fusingBiFunction(bifn)

    public default <B, C> BooleanFunction<C> fuseBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return (boolean b) -> next.apply(resolve().apply(b), secondArg);
    }

    public default <B, C> BooleanFunction<C> fuse(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fuseBiFunction(next, secondArg);
    }

    public default <B, C> WithBooleanFunction<C> fusingBiFunction(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return WithBooleanFunction.of(fuseBiFunction(next, secondArg));
    }

    public default <B, C> WithBooleanFunction<C> fusing(
        BiFunction<A, B, C> next,
        B secondArg
    ) {
        return fusingBiFunction(next, secondArg);
    }

    /* BooleanFunction<A> -> ToDoubleFunction<A> */

    public default BooleanToDoubleFunction fuseToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return (boolean b) -> next.applyAsDouble(resolve().apply(b));
    }

    public default BooleanToDoubleFunction fuse(ToDoubleFunction<A> next) {
        return fuseToDoubleFunction(next);
    }

    public default WithBooleanToDoubleFunction fusingToDoubleFunction(
        ToDoubleFunction<A> next
    ) {
        return WithBooleanToDoubleFunction.of(fuseToDoubleFunction(next));
    }

    public default WithBooleanToDoubleFunction fusing(
        ToDoubleFunction<A> next
    ) {
        return fusingToDoubleFunction(next);
    }

    /* BooleanFunction<A> -> ToDoubleBiFunction<A> */

    public default <B> BooleanFunction<ToDoubleFunction<B>> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next
    ) {
        return (boolean b1) ->
            (B b2) -> next.applyAsDouble(resolve().apply(b1), b2);
    }

    public default <B> BooleanFunction<ToDoubleFunction<B>> fuse(
        ToDoubleBiFunction<A, B> next
    ) {
        return fuseToDoubleBiFunction(next);
    }

    // TODO: Implement with currying
    // fusingToDoubleBiFunction(ToDoubleBiFunction<A, B> next)

    public default <B> BooleanToDoubleFunction fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return (boolean b1) ->
            next.applyAsDouble(resolve().apply(b1), secondArg);
    }

    public default <B> BooleanToDoubleFunction fuse(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToDoubleBiFunction(next, secondArg);
    }

    public default <B> WithBooleanToDoubleFunction fusingToDoubleBiFunction(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return WithBooleanToDoubleFunction.of(
            fuseToDoubleBiFunction(next, secondArg)
        );
    }

    public default <B> WithBooleanToDoubleFunction fusing(
        ToDoubleBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToDoubleBiFunction(next, secondArg);
    }

    /* BooleanFunction<A> -> ToIntFunction<A> */

    public default BooleanToIntFunction fuseToIntFunction(
        ToIntFunction<A> next
    ) {
        return (boolean b) -> next.applyAsInt(resolve().apply(b));
    }

    public default BooleanToIntFunction fuse(ToIntFunction<A> next) {
        return fuseToIntFunction(next);
    }

    public default WithBooleanToIntFunction fusingToIntFunction(
        ToIntFunction<A> next
    ) {
        return WithBooleanToIntFunction.of(fuseToIntFunction(next));
    }

    public default WithBooleanToIntFunction fusing(ToIntFunction<A> next) {
        return fusingToIntFunction(next);
    }

    /* BooleanFunction<A> -> ToIntBiFunction<A> */

    public default <B> BooleanFunction<ToIntFunction<B>> fuseToIntBiFunction(
        ToIntBiFunction<A, B> next
    ) {
        return (boolean b1) ->
            (B b2) -> next.applyAsInt(resolve().apply(b1), b2);
    }

    public default <B> BooleanFunction<ToIntFunction<B>> fuse(
        ToIntBiFunction<A, B> next
    ) {
        return fuseToIntBiFunction(next);
    }

    // TODO: Implement with currying
    // fusingToIntBiFunction(ToIntBiFunction<A, B> next)

    public default <B> BooleanToIntFunction fuseToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return (boolean b1) -> next.applyAsInt(resolve().apply(b1), secondArg);
    }

    public default <B> BooleanToIntFunction fuse(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToIntBiFunction(next, secondArg);
    }

    public default <B> WithBooleanToIntFunction fusingToIntBiFunction(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return WithBooleanToIntFunction.of(
            fuseToIntBiFunction(next, secondArg)
        );
    }

    public default <B> WithBooleanToIntFunction fusing(
        ToIntBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToIntBiFunction(next, secondArg);
    }

    /* BooleanFunction<A> -> ToLongFunction<A> */

    public default BooleanToLongFunction fuseToLongFunction(
        ToLongFunction<A> next
    ) {
        return (boolean b) -> next.applyAsLong(resolve().apply(b));
    }

    public default BooleanToLongFunction fuse(ToLongFunction<A> next) {
        return fuseToLongFunction(next);
    }

    public default WithBooleanToLongFunction fusingToLongFunction(
        ToLongFunction<A> next
    ) {
        return WithBooleanToLongFunction.of(fuseToLongFunction(next));
    }

    public default WithBooleanToLongFunction fusing(ToLongFunction<A> next) {
        return fusingToLongFunction(next);
    }

    /* BooleanFunction<A> -> ToLongBiFunction<A> */

    public default <B> BooleanFunction<ToLongFunction<B>> fuseToLongBiFunction(
        ToLongBiFunction<A, B> next
    ) {
        return (boolean b1) ->
            (B b2) -> next.applyAsLong(resolve().apply(b1), b2);
    }

    public default <B> BooleanFunction<ToLongFunction<B>> fuse(
        ToLongBiFunction<A, B> next
    ) {
        return fuseToLongBiFunction(next);
    }

    // TODO: Implement with currying
    // fusingToLongBiFunction(ToLongBiFunction<A, B> next)

    public default <B> BooleanToLongFunction fuseToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return (boolean b1) -> next.applyAsLong(resolve().apply(b1), secondArg);
    }

    public default <B> BooleanToLongFunction fuse(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fuseToLongBiFunction(next, secondArg);
    }

    public default <B> WithBooleanToLongFunction fusingToLongBiFunction(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return WithBooleanToLongFunction.of(
            fuseToLongBiFunction(next, secondArg)
        );
    }

    public default <B> WithBooleanToLongFunction fusing(
        ToLongBiFunction<A, B> next,
        B secondArg
    ) {
        return fusingToLongBiFunction(next, secondArg);
    }

    /* BooleanFunction<A> -> Predicate<A> */

    public default BooleanPredicate fusePredicate(Predicate<A> next) {
        return (boolean b) -> next.test(resolve().apply(b));
    }

    public default BooleanPredicate fuse(Predicate<A> next) {
        return fusePredicate(next);
    }

    public default WithBooleanPredicate fusingPredicate(Predicate<A> next) {
        return WithBooleanPredicate.of(fusePredicate(next));
    }

    public default WithBooleanPredicate fusing(Predicate<A> next) {
        return fusingPredicate(next);
    }

    /* BooleanFunction<A> -> BiPredicate<A> */

    public default <B> BooleanFunction<Predicate<B>> fuseBiPredicate(
        BiPredicate<A, B> next
    ) {
        return (boolean b1) -> (B b2) -> next.test(resolve().apply(b1), b2);
    }

    public default <B> BooleanFunction<Predicate<B>> fuse(
        BiPredicate<A, B> next
    ) {
        return fuseBiPredicate(next);
    }

    // TODO: Implement with currying
    // fusingBiPredicate(BiPredicate<A, B> next)

    public default <B> BooleanPredicate fuseBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return (boolean b1) -> next.test(resolve().apply(b1), secondArg);
    }

    public default <B> BooleanPredicate fuse(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return fuseBiPredicate(next, secondArg);
    }

    public default <B> WithBooleanPredicate fusingBiPredicate(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return WithBooleanPredicate.of(fuseBiPredicate(next, secondArg));
    }

    public default <B> WithBooleanPredicate fusing(
        BiPredicate<A, B> next,
        B secondArg
    ) {
        return fusingBiPredicate(next, secondArg);
    }

    /* BooleanFunction<A> -> Consumer<A> */

    public default BooleanConsumer fuseConsumer(Consumer<A> next) {
        return (boolean b) -> next.accept(resolve().apply(b));
    }

    public default BooleanConsumer fuse(Consumer<A> next) {
        return fuseConsumer(next);
    }

    public default WithBooleanConsumer fusingConsumer(Consumer<A> next) {
        return WithBooleanConsumer.of(fuseConsumer(next));
    }

    public default WithBooleanConsumer fusing(Consumer<A> next) {
        return fusingConsumer(next);
    }

    /* BooleanFunction<A> -> BiConsumer<A> */

    public default <B> BooleanFunction<Consumer<B>> fuseBiConsumer(
        BiConsumer<A, B> next
    ) {
        return (boolean b1) -> (B b2) -> next.accept(resolve().apply(b1), b2);
    }

    public default <B> BooleanFunction<Consumer<B>> fuse(
        BiConsumer<A, B> next
    ) {
        return fuseBiConsumer(next);
    }

    // TODO: Implement with currying
    // fusingBiConsumer(BiConsumer<A, B> next)

    public default <B> BooleanConsumer fuseBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return (boolean b1) -> next.accept(resolve().apply(b1), secondArg);
    }

    public default <B> BooleanConsumer fuse(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return fuseBiConsumer(next, secondArg);
    }

    public default <B> WithBooleanConsumer fusingBiConsumer(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return WithBooleanConsumer.of(fuseBiConsumer(next, secondArg));
    }

    public default <B> WithBooleanConsumer fusing(
        BiConsumer<A, B> next,
        B secondArg
    ) {
        return fusingBiConsumer(next, secondArg);
    }

    /* BooleanFunction<A> -> ObjDoubleConsumer<A> */

    public default BooleanFunction<DoubleConsumer> fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next
    ) {
        return (boolean b) -> (double d) -> next.accept(resolve().apply(b), d);
    }

    public default BooleanFunction<DoubleConsumer> fuse(
        ObjDoubleConsumer<A> next
    ) {
        return fuseObjDoubleConsumer(next);
    }

    // TODO: Implement with currying
    // public default BooleanFunction<DoubleConsumer> fusingObjDoubleConsumer(ObjDoubleConsumer<A> next) {...}

    public default BooleanConsumer fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return (boolean b) -> next.accept(resolve().apply(b), secondArg);
    }

    public default BooleanConsumer fuse(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fuseObjDoubleConsumer(next, secondArg);
    }

    public default WithBooleanConsumer fusingObjDoubleConsumer(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return WithBooleanConsumer.of(fuseObjDoubleConsumer(next, secondArg));
    }

    public default WithBooleanConsumer fusing(
        ObjDoubleConsumer<A> next,
        double secondArg
    ) {
        return fusingObjDoubleConsumer(next, secondArg);
    }

    /* BooleanFunction<A> -> ObjIntConsumer<A> */

    public default BooleanFunction<IntConsumer> fuseObjIntConsumer(
        ObjIntConsumer<A> next
    ) {
        return (boolean b) -> (int d) -> next.accept(resolve().apply(b), d);
    }

    public default BooleanFunction<IntConsumer> fuse(ObjIntConsumer<A> next) {
        return fuseObjIntConsumer(next);
    }

    // TODO: Implement with currying
    // public default BooleanFunction<IntConsumer> fusingObjIntConsumer(ObjIntConsumer<A> next) {...}

    public default BooleanConsumer fuseObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return (boolean b) -> next.accept(resolve().apply(b), secondArg);
    }

    public default BooleanConsumer fuse(ObjIntConsumer<A> next, int secondArg) {
        return fuseObjIntConsumer(next, secondArg);
    }

    public default WithBooleanConsumer fusingObjIntConsumer(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return WithBooleanConsumer.of(fuseObjIntConsumer(next, secondArg));
    }

    public default WithBooleanConsumer fusing(
        ObjIntConsumer<A> next,
        int secondArg
    ) {
        return fusingObjIntConsumer(next, secondArg);
    }

    /* BooleanFunction<A> -> ObjLongConsumer<A> */

    public default BooleanFunction<LongConsumer> fuseObjLongConsumer(
        ObjLongConsumer<A> next
    ) {
        return (boolean b) -> (long d) -> next.accept(resolve().apply(b), d);
    }

    public default BooleanFunction<LongConsumer> fuse(ObjLongConsumer<A> next) {
        return fuseObjLongConsumer(next);
    }

    // TODO: Implement with currying
    // public default BooleanFunction<LongConsumer> fusingObjLongConsumer(ObjLongConsumer<A> next) {...}

    public default BooleanConsumer fuseObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return (boolean b) -> next.accept(resolve().apply(b), secondArg);
    }

    public default BooleanConsumer fuse(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return fuseObjLongConsumer(next, secondArg);
    }

    public default WithBooleanConsumer fusingObjLongConsumer(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return WithBooleanConsumer.of(fuseObjLongConsumer(next, secondArg));
    }

    public default WithBooleanConsumer fusing(
        ObjLongConsumer<A> next,
        long secondArg
    ) {
        return fusingObjLongConsumer(next, secondArg);
    }
}
