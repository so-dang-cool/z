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

    public default <D> Function<A, Function<B, D>> fuseFunction(
        Function<C, D> next
    ) {
        return (A a) -> (B b) -> next.apply(resolve().apply(a).apply(b));
    }

    public default <D> Function<A, Function<B, D>> fuse(Function<C, D> next) {
        return fuseFunction(next);
    }

    public default <D> WithBiFunction<A, B, D> fusingFunction(
        Function<C, D> next
    ) {
        return WithBiFunction.of(fuseFunction(next));
    }

    public default <D> WithBiFunction<A, B, D> fusing(Function<C, D> next) {
        return fusingFunction(next);
    }

    /* BiFunction<A, B, C> -> BiFunction<C, D, E> */

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

    // TODO: Implement with currying
    // fusingBiFunction(...) {...}
    // fusing(...) {...}

    public default <D, E> Function<A, Function<B, E>> fuseBiFunction(
        BiFunction<C, D, E> next,
        D secondArg
    ) {
        return (A a) ->
            (B b) -> next.apply(resolve().apply(a).apply(b), secondArg);
    }

    public default <D, E> Function<A, Function<B, E>> fuse(
        BiFunction<C, D, E> next,
        D secondArg
    ) {
        return fuseBiFunction(next, secondArg);
    }

    public default <D, E> WithBiFunction<A, B, E> fusingBiFunction(
        BiFunction<C, D, E> next,
        D secondArg
    ) {
        return WithBiFunction.of(fuseBiFunction(next, secondArg));
    }

    public default <D, E> WithBiFunction<A, B, E> fusing(
        BiFunction<C, D, E> next,
        D secondArg
    ) {
        return fusingBiFunction(next, secondArg);
    }

    /* BiFunction<A, B, C> -> ToDoubleFunction<C> */

    public default Function<A, ToDoubleFunction<B>> fuseToDoubleFunction(
        ToDoubleFunction<C> next
    ) {
        return (A a) ->
            (B b) -> next.applyAsDouble(resolve().apply(a).apply(b));
    }

    public default Function<A, ToDoubleFunction<B>> fuse(
        ToDoubleFunction<C> next
    ) {
        return fuseToDoubleFunction(next);
    }

    public default WithToDoubleBiFunction<A, B> fusingToDoubleFunction(
        ToDoubleFunction<C> next
    ) {
        return WithToDoubleBiFunction.of(fuseToDoubleFunction(next));
    }

    public default WithToDoubleBiFunction<A, B> fusing(
        ToDoubleFunction<C> next
    ) {
        return fusingToDoubleFunction(next);
    }

    /* BiFunction<A, B, C> -> ToDoubleBiFunction<C, D> */

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

    // TODO: Implement with currying
    // fusingToDoubleBifunction(toDblBifn)
    // fusing(toDblBifn)

    public default <D> Function<A, ToDoubleFunction<B>> fuseToDoubleBiFunction(
        ToDoubleBiFunction<C, D> next,
        D secondArg
    ) {
        return (A a) ->
            (B b) -> next.applyAsDouble(resolve().apply(a).apply(b), secondArg);
    }

    public default <D> Function<A, ToDoubleFunction<B>> fuse(
        ToDoubleBiFunction<C, D> next,
        D secondArg
    ) {
        return fuseToDoubleBiFunction(next, secondArg);
    }

    public default <D> WithToDoubleBiFunction<A, B> fusingToDoubleBiFunction(
        ToDoubleBiFunction<C, D> next,
        D secondArg
    ) {
        return WithToDoubleBiFunction.of(
            fuseToDoubleBiFunction(next, secondArg)
        );
    }

    public default <D> WithToDoubleBiFunction<A, B> fusing(
        ToDoubleBiFunction<C, D> next,
        D secondArg
    ) {
        return fusingToDoubleBiFunction(next, secondArg);
    }

    /* BiFunction<A, B, C> -> ToIntFunction<C> */

    public default Function<A, ToIntFunction<B>> fuseToIntFunction(
        ToIntFunction<C> next
    ) {
        return (A a) -> (B b) -> next.applyAsInt(resolve().apply(a).apply(b));
    }

    public default Function<A, ToIntFunction<B>> fuse(ToIntFunction<C> next) {
        return fuseToIntFunction(next);
    }

    public default WithToIntBiFunction<A, B> fusingToIntFunction(
        ToIntFunction<C> next
    ) {
        return WithToIntBiFunction.of(fuseToIntFunction(next));
    }

    public default WithToIntBiFunction<A, B> fusing(ToIntFunction<C> next) {
        return fusingToIntFunction(next);
    }

    /* BiFunction<A, B, C> -> ToIntBiFunction<C, D> */

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

    // TODO: Implement with currying
    // fusingToIntBifunction(toDblBifn)
    // fusing(toDblBifn)

    public default <D> Function<A, ToIntFunction<B>> fuseToIntBiFunction(
        ToIntBiFunction<C, D> next,
        D secondArg
    ) {
        return (A a) ->
            (B b) -> next.applyAsInt(resolve().apply(a).apply(b), secondArg);
    }

    public default <D> Function<A, ToIntFunction<B>> fuse(
        ToIntBiFunction<C, D> next,
        D secondArg
    ) {
        return fuseToIntBiFunction(next, secondArg);
    }

    public default <D> WithToIntBiFunction<A, B> fusingToIntBiFunction(
        ToIntBiFunction<C, D> next,
        D secondArg
    ) {
        return WithToIntBiFunction.of(fuseToIntBiFunction(next, secondArg));
    }

    public default <D> WithToIntBiFunction<A, B> fusing(
        ToIntBiFunction<C, D> next,
        D secondArg
    ) {
        return fusingToIntBiFunction(next, secondArg);
    }

    /* BiFunction<A, B, C> -> ToLongFunction<C> */

    public default Function<A, ToLongFunction<B>> fuseToLongFunction(
        ToLongFunction<C> next
    ) {
        return (A a) -> (B b) -> next.applyAsLong(resolve().apply(a).apply(b));
    }

    public default Function<A, ToLongFunction<B>> fuse(ToLongFunction<C> next) {
        return fuseToLongFunction(next);
    }

    public default WithToLongBiFunction<A, B> fusingToLongFunction(
        ToLongFunction<C> next
    ) {
        return WithToLongBiFunction.of(fuseToLongFunction(next));
    }

    public default WithToLongBiFunction<A, B> fusing(ToLongFunction<C> next) {
        return fusingToLongFunction(next);
    }

    /* BiFunction<A, B, C> -> ToLongBiFunction<C, D> */

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

    // TODO: Implement with currying
    // fusingToLongBifunction(toDblBifn)
    // fusing(toDblBifn)

    public default <D> Function<A, ToLongFunction<B>> fuseToLongBiFunction(
        ToLongBiFunction<C, D> next,
        D secondArg
    ) {
        return (A a) ->
            (B b) -> next.applyAsLong(resolve().apply(a).apply(b), secondArg);
    }

    public default <D> Function<A, ToLongFunction<B>> fuse(
        ToLongBiFunction<C, D> next,
        D secondArg
    ) {
        return fuseToLongBiFunction(next, secondArg);
    }

    public default <D> WithToLongBiFunction<A, B> fusingToLongBiFunction(
        ToLongBiFunction<C, D> next,
        D secondArg
    ) {
        return WithToLongBiFunction.of(fuseToLongBiFunction(next, secondArg));
    }

    public default <D> WithToLongBiFunction<A, B> fusing(
        ToLongBiFunction<C, D> next,
        D secondArg
    ) {
        return fusingToLongBiFunction(next, secondArg);
    }

    /* BiFunction<A, B, C> -> Predicate<C> */

    public default Function<A, Predicate<B>> fusePredicate(Predicate<C> next) {
        return (A a) -> (B b) -> next.test(resolve().apply(a).apply(b));
    }

    public default Function<A, Predicate<B>> fuse(Predicate<C> next) {
        return fusePredicate(next);
    }

    public default WithBiPredicate<A, B> fusingPredicate(Predicate<C> next) {
        return WithBiPredicate.of(fusePredicate(next));
    }

    public default WithBiPredicate<A, B> fusing(Predicate<C> next) {
        return fusingPredicate(next);
    }

    /* BiFunction<A, B, C> -> BiPredicate<C, D> */

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

    // TODO: Implement with currying
    // fusingToLongBifunction(toDblBifn)
    // fusing(toDblBifn)

    public default <D> Function<A, Predicate<B>> fuseBiPredicate(
        BiPredicate<C, D> next,
        D secondArg
    ) {
        return (A a) ->
            (B b) -> next.test(resolve().apply(a).apply(b), secondArg);
    }

    public default <D> Function<A, Predicate<B>> fuse(
        BiPredicate<C, D> next,
        D secondArg
    ) {
        return fuseBiPredicate(next, secondArg);
    }

    public default <D> WithBiPredicate<A, B> fusingBiPredicate(
        BiPredicate<C, D> next,
        D secondArg
    ) {
        return WithBiPredicate.of(fuseBiPredicate(next, secondArg));
    }

    public default <D> WithBiPredicate<A, B> fusing(
        BiPredicate<C, D> next,
        D secondArg
    ) {
        return fusingBiPredicate(next, secondArg);
    }

    /* BiFunction<A, B, C> -> Consumer<C> */

    public default Function<A, Consumer<B>> fuseConsumer(Consumer<C> next) {
        return (A a) -> (B b) -> next.accept(resolve().apply(a).apply(b));
    }

    public default Function<A, Consumer<B>> fuse(Consumer<C> next) {
        return fuseConsumer(next);
    }

    public default WithBiConsumer<A, B> fusingConsumer(Consumer<C> next) {
        return WithBiConsumer.of(fuseConsumer(next));
    }

    public default WithBiConsumer<A, B> fusing(Consumer<C> next) {
        return fusingConsumer(next);
    }

    /* BiFunction<A, B, C> -> BiConsumer<C, D> */

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

    // TODO: Implement with currying
    // fusingToLongBifunction(toDblBifn)
    // fusing(toDblBifn)

    public default <D> Function<A, Consumer<B>> fuseBiConsumer(
        BiConsumer<C, D> next,
        D secondArg
    ) {
        return (A a) ->
            (B b) -> next.accept(resolve().apply(a).apply(b), secondArg);
    }

    public default <D> Function<A, Consumer<B>> fuse(
        BiConsumer<C, D> next,
        D secondArg
    ) {
        return fuseBiConsumer(next, secondArg);
    }

    public default <D> WithBiConsumer<A, B> fusingBiConsumer(
        BiConsumer<C, D> next,
        D secondArg
    ) {
        return WithBiConsumer.of(fuseBiConsumer(next, secondArg));
    }

    public default <D> WithBiConsumer<A, B> fusing(
        BiConsumer<C, D> next,
        D secondArg
    ) {
        return fusingBiConsumer(next, secondArg);
    }

    /* BiFunction<A, B, C> -> ObjDoubleConsumer<C> */

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

    //TODO: Implement with currying
    // fusingObjDoubleConsumer(objDblCns)

    public default <D> Function<A, Consumer<B>> fuseObjDoubleConsumer(
        ObjDoubleConsumer<C> next,
        double secondArg
    ) {
        return (A a) ->
            (B b) -> next.accept(resolve().apply(a).apply(b), secondArg);
    }

    public default <D> Function<A, Consumer<B>> fuse(
        ObjDoubleConsumer<C> next,
        double secondArg
    ) {
        return fuseObjDoubleConsumer(next, secondArg);
    }

    public default <D> WithBiConsumer<A, B> fusingObjDoubleConsumer(
        ObjDoubleConsumer<C> next,
        double secondArg
    ) {
        return WithBiConsumer.of(fuseObjDoubleConsumer(next, secondArg));
    }

    public default <D> WithBiConsumer<A, B> fusing(
        ObjDoubleConsumer<C> next,
        double secondArg
    ) {
        return fusingObjDoubleConsumer(next, secondArg);
    }

    /* BiFunction<A, B, C> -> ObjIntConsumer<C> */

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

    //TODO: Implement with currying
    // fusingObjIntConsumer(objDblCns)

    public default <D> Function<A, Consumer<B>> fuseObjIntConsumer(
        ObjIntConsumer<C> next,
        int secondArg
    ) {
        return (A a) ->
            (B b) -> next.accept(resolve().apply(a).apply(b), secondArg);
    }

    public default <D> Function<A, Consumer<B>> fuse(
        ObjIntConsumer<C> next,
        int secondArg
    ) {
        return fuseObjIntConsumer(next, secondArg);
    }

    public default <D> WithBiConsumer<A, B> fusingObjIntConsumer(
        ObjIntConsumer<C> next,
        int secondArg
    ) {
        return WithBiConsumer.of(fuseObjIntConsumer(next, secondArg));
    }

    public default <D> WithBiConsumer<A, B> fusing(
        ObjIntConsumer<C> next,
        int secondArg
    ) {
        return fusingObjIntConsumer(next, secondArg);
    }

    /* BiFunction<A, B, C> -> ObjLongConsumer<C> */

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

    //TODO: Implement with currying
    // fusingObjLongConsumer(objDblCns)

    public default <D> Function<A, Consumer<B>> fuseObjLongConsumer(
        ObjLongConsumer<C> next,
        long secondArg
    ) {
        return (A a) ->
            (B b) -> next.accept(resolve().apply(a).apply(b), secondArg);
    }

    public default <D> Function<A, Consumer<B>> fuse(
        ObjLongConsumer<C> next,
        long secondArg
    ) {
        return fuseObjLongConsumer(next, secondArg);
    }

    public default <D> WithBiConsumer<A, B> fusingObjLongConsumer(
        ObjLongConsumer<C> next,
        long secondArg
    ) {
        return WithBiConsumer.of(fuseObjLongConsumer(next, secondArg));
    }

    public default <D> WithBiConsumer<A, B> fusing(
        ObjLongConsumer<C> next,
        long secondArg
    ) {
        return fusingObjLongConsumer(next, secondArg);
    }
}
