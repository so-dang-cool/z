package so.dang.cool.z;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
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
import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.function.DecFunction;
import so.dang.cool.z.function.DodecFunction;
import so.dang.cool.z.function.NonFunction;
import so.dang.cool.z.function.OctFunction;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.function.QuadFunction;
import so.dang.cool.z.function.QuinFunction;
import so.dang.cool.z.function.SeptFunction;
import so.dang.cool.z.function.SexFunction;
import so.dang.cool.z.function.TriFunction;
import so.dang.cool.z.function.UndecFunction;
import so.dang.cool.z.internal.combination.Combine;

/**
 * A collection of techniques for combining or manipulating Java functions.
 *
 * <ol>
 *   <li>{@code Z.fuse(fnA, fnB)} - combines two functions into one.</li>
 *   <li>{@code Z.absorb(fnA, fnB)} - combines two not-naturally-joining functions into one.</li>
 *   <li>{@code Z.split(fn)} - transforms a multi-argument function into a curried form.</li>
 *   <li>{@code Z.assimilate[N](fn)} - transforms a curried function into a multi-argument form.</li>
 * </ol>
 *
 * For more general guidance, see the notes on the package itself.
 */
public final class Z {

    private Z() {}

    /**
     * Returns an identity function for the given class. Identity functions are
     * functions that return the value they are given. One use is modeling an
     * "either transform or stay the same" choice between two functions.
     */
    public static <A> UnaryOperator<A> id(Class<A> clazz) {
        return (A a) -> a;
    }

    /**
     * Flip the order of the arguments of a BiFunction.
     */
    public static <A, B, C> BiFunction<B, A, C> flip(
        BiFunction<A, B, C> initial
    ) {
        return (B b, A a) -> initial.apply(a, b);
    }

    /**
     * Flip the order of the first two arguments of a curried Function.
     */
    public static <A, B, C> Function<B, Function<A, C>> flip(
        Function<A, Function<B, C>> initial
    ) {
        return (B b) -> (A a) -> initial.apply(a).apply(b);
    }

    // ┏┓
    // ┏━━━━┓
    // ┏━━━━━━━━┓
    // ┏━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┃                        ┃
    // ┃    ┏━╸╻ ╻┏━┓╻┏━┓┏┓╻    ┃
    // ┃    ┣╸ ┃ ┃┗━┓┃┃ ┃┃┗┫    ┃
    // ┃    ╹  ┗━┛┗━┛╹┗━┛╹ ╹    ┃
    // ┃                        ┃
    // ┗━━━━━━━━━━━━━━━━━━━━━━━━┛

    // Functional interfaces into combinators.

    /* Function */

    public static <A, B> Combine.WithFunction<A, B> fuseFunction(
        Function<A, B> initial
    ) {
        return Combine.WithFunction.of(initial);
    }

    public static <A, B> Combine.WithFunction<A, B> fuse(
        Function<A, B> initial
    ) {
        return fuseFunction(initial);
    }

    /* BiFunction */

    public static <A, B, C> Combine.WithBiFunction<A, B, C> fuseBiFunction(
        BiFunction<A, B, C> initial
    ) {
        return Combine.WithBiFunction.of(initial);
    }

    public static <A, B, C> Combine.WithBiFunction<A, B, C> fuse(
        BiFunction<A, B, C> initial
    ) {
        return fuseBiFunction(initial);
    }

    /* BooleanFunction */

    public static <A> Combine.WithBooleanFunction<A> fuseBooleanFunction(
        BooleanFunction<A> initial
    ) {
        return Combine.WithBooleanFunction.of(initial);
    }

    public static <A> Combine.WithBooleanFunction<A> fuse(
        BooleanFunction<A> initial
    ) {
        return fuseBooleanFunction(initial);
    }

    /* BooleanToDoubleFunction */

    public static Combine.WithBooleanToDoubleFunction fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction initial
    ) {
        return Combine.WithBooleanToDoubleFunction.of(initial);
    }

    public static Combine.WithBooleanToDoubleFunction fuse(
        BooleanToDoubleFunction initial
    ) {
        return fuseBooleanToDoubleFunction(initial);
    }

    /* BooleanToIntFunction */

    public static <A> Combine.WithBooleanToIntFunction fuseBooleanToIntFunction(
        BooleanToIntFunction initial
    ) {
        return Combine.WithBooleanToIntFunction.of(initial);
    }

    public static <A> Combine.WithBooleanToIntFunction fuse(
        BooleanToIntFunction initial
    ) {
        return fuseBooleanToIntFunction(initial);
    }

    /* BooleanToLongFunction */

    public static Combine.WithBooleanToLongFunction fuseBooleanToLongFunction(
        BooleanToLongFunction initial
    ) {
        return Combine.WithBooleanToLongFunction.of(initial);
    }

    public static Combine.WithBooleanToLongFunction fuse(
        BooleanToLongFunction initial
    ) {
        return fuseBooleanToLongFunction(initial);
    }

    /* DoubleFunction */

    public static <A> Combine.WithDoubleFunction<A> fuseDoubleFunction(
        DoubleFunction<A> initial
    ) {
        return Combine.WithDoubleFunction.of(initial);
    }

    public static <A> Combine.WithDoubleFunction<A> fuse(
        DoubleFunction<A> initial
    ) {
        return fuseDoubleFunction(initial);
    }

    /* DoubleToIntFunction */

    public static <A> Combine.WithDoubleToIntFunction fuseDoubleToIntFunction(
        DoubleToIntFunction initial
    ) {
        return Combine.WithDoubleToIntFunction.of(initial);
    }

    public static <A> Combine.WithDoubleToIntFunction fuse(
        DoubleToIntFunction initial
    ) {
        return fuseDoubleToIntFunction(initial);
    }

    /* DoubleToLongFunction */

    public static <A> Combine.WithDoubleToLongFunction fuseDoubleToLongFunction(
        DoubleToLongFunction initial
    ) {
        return Combine.WithDoubleToLongFunction.of(initial);
    }

    public static <A> Combine.WithDoubleToLongFunction fuse(
        DoubleToLongFunction initial
    ) {
        return fuseDoubleToLongFunction(initial);
    }

    /* ToDoubleFunction */

    public static <A> Combine.WithToDoubleFunction<A> fuseToDoubleFunction(
        ToDoubleFunction<A> initial
    ) {
        return Combine.WithToDoubleFunction.of(initial);
    }

    public static <A> Combine.WithToDoubleFunction<A> fuse(
        ToDoubleFunction<A> initial
    ) {
        return fuseToDoubleFunction(initial);
    }

    /* ToDoubleBiFunction */

    public static <A, B> Combine.WithToDoubleBiFunction<A, B> fuseToDoubleBiFunction(
        ToDoubleBiFunction<A, B> initial
    ) {
        return Combine.WithToDoubleBiFunction.of(initial);
    }

    public static <A, B> Combine.WithToDoubleBiFunction<A, B> fuse(
        ToDoubleBiFunction<A, B> initial
    ) {
        return fuseToDoubleBiFunction(initial);
    }

    /* IntFunction */

    public static <A> Combine.WithIntFunction<A> fuseIntFunction(
        IntFunction<A> initial
    ) {
        return Combine.WithIntFunction.of(initial);
    }

    public static <A> Combine.WithIntFunction<A> fuse(IntFunction<A> initial) {
        return fuseIntFunction(initial);
    }

    /* IntToDoubleFunction */

    public static Combine.WithIntToDoubleFunction fuseIntToDoubleFunction(
        IntToDoubleFunction initial
    ) {
        return Combine.WithIntToDoubleFunction.of(initial);
    }

    public static Combine.WithIntToDoubleFunction fuse(
        IntToDoubleFunction initial
    ) {
        return fuseIntToDoubleFunction(initial);
    }

    /* IntToLongFunction */

    public static Combine.WithIntToLongFunction fuseIntToLongFunction(
        IntToLongFunction initial
    ) {
        return Combine.WithIntToLongFunction.of(initial);
    }

    public static Combine.WithIntToLongFunction fuse(
        IntToLongFunction initial
    ) {
        return fuseIntToLongFunction(initial);
    }

    /* ToIntFunction */

    public static <A> Combine.WithToIntFunction<A> fuseToIntFunction(
        ToIntFunction<A> initial
    ) {
        return Combine.WithToIntFunction.of(initial);
    }

    public static <A> Combine.WithToIntFunction<A> fuse(
        ToIntFunction<A> initial
    ) {
        return fuseToIntFunction(initial);
    }

    /* ToIntBiFunction */

    public static <A, B> Combine.WithToIntBiFunction<A, B> fuseToIntBiFunction(
        ToIntBiFunction<A, B> initial
    ) {
        return Combine.WithToIntBiFunction.of(initial);
    }

    public static <A, B> Combine.WithToIntBiFunction<A, B> fuse(
        ToIntBiFunction<A, B> initial
    ) {
        return fuseToIntBiFunction(initial);
    }

    /* LongFunction */

    public static <A> Combine.WithLongFunction<A> fuseLongFunction(
        LongFunction<A> initial
    ) {
        return Combine.WithLongFunction.of(initial);
    }

    public static <A> Combine.WithLongFunction<A> fuse(
        LongFunction<A> initial
    ) {
        return fuseLongFunction(initial);
    }

    /* LongToDoubleFunction */

    public static Combine.WithLongToDoubleFunction fuseLongToDoubleFunction(
        LongToDoubleFunction initial
    ) {
        return Combine.WithLongToDoubleFunction.of(initial);
    }

    public static Combine.WithLongToDoubleFunction fuse(
        LongToDoubleFunction initial
    ) {
        return fuseLongToDoubleFunction(initial);
    }

    /* LongToIntFunction */

    public static Combine.WithLongToIntFunction fuseLongToIntFunction(
        LongToIntFunction initial
    ) {
        return Combine.WithLongToIntFunction.of(initial);
    }

    public static Combine.WithLongToIntFunction fuse(
        LongToIntFunction initial
    ) {
        return fuseLongToIntFunction(initial);
    }

    /* ToLongFunction */

    public static <A> Combine.WithToLongFunction<A> fuseToLongFunction(
        ToLongFunction<A> initial
    ) {
        return Combine.WithToLongFunction.of(initial);
    }

    public static <A> Combine.WithToLongFunction<A> fuse(
        ToLongFunction<A> initial
    ) {
        return fuseToLongFunction(initial);
    }

    /* ToLongBiFunction */

    public static <A, B> Combine.WithToLongBiFunction<A, B> fuseToLongBiFunction(
        ToLongBiFunction<A, B> initial
    ) {
        return Combine.WithToLongBiFunction.of(initial);
    }

    public static <A, B> Combine.WithToLongBiFunction<A, B> fuse(
        ToLongBiFunction<A, B> initial
    ) {
        return fuseToLongBiFunction(initial);
    }

    /* Predicate */

    public static <A> Combine.WithPredicate<A> fusePredicate(
        Predicate<A> initial
    ) {
        return Combine.WithPredicate.of(initial);
    }

    public static <A> Combine.WithPredicate<A> fuse(Predicate<A> initial) {
        return fusePredicate(initial);
    }

    /* <BiPredicate> */

    public static <A, B> Combine.WithBiPredicate<A, B> fuseBiPredicate(
        BiPredicate<A, B> initial
    ) {
        return Combine.WithBiPredicate.of(initial);
    }

    public static <A, B> Combine.WithBiPredicate<A, B> fuse(
        BiPredicate<A, B> initial
    ) {
        return fuseBiPredicate(initial);
    }

    /* BooleanPredicate */

    public static Combine.WithBooleanPredicate fuseBooleanPredicate(
        BooleanPredicate initial
    ) {
        return Combine.WithBooleanPredicate.of(initial);
    }

    public static Combine.WithBooleanPredicate fuse(BooleanPredicate initial) {
        return fuseBooleanPredicate(initial);
    }

    /* <DoublePredicate> */

    public static Combine.WithDoublePredicate fuseDoublePredicate(
        DoublePredicate initial
    ) {
        return Combine.WithDoublePredicate.of(initial);
    }

    public static Combine.WithDoublePredicate fuse(DoublePredicate initial) {
        return fuseDoublePredicate(initial);
    }

    /* IntPredicate */

    public static Combine.WithIntPredicate fuseIntPredicate(
        IntPredicate initial
    ) {
        return Combine.WithIntPredicate.of(initial);
    }

    public static Combine.WithIntPredicate fuse(IntPredicate initial) {
        return fuseIntPredicate(initial);
    }

    /* LongPredicate */

    public static Combine.WithLongPredicate fuseLongPredicate(
        LongPredicate initial
    ) {
        return Combine.WithLongPredicate.of(initial);
    }

    public static Combine.WithLongPredicate fuse(LongPredicate initial) {
        return fuseLongPredicate(initial);
    }

    /* Supplier */

    public static <A> Combine.WithSupplier<A> fuseSupplier(
        Supplier<A> initial
    ) {
        return Combine.WithSupplier.of(initial);
    }

    public static <A> Combine.WithSupplier<A> fuse(Supplier<A> initial) {
        return fuseSupplier(initial);
    }

    /* BooleanSupplier */

    public static Combine.WithBooleanSupplier fuseBooleanSupplier(
        BooleanSupplier initial
    ) {
        return Combine.WithBooleanSupplier.of(initial);
    }

    public static Combine.WithBooleanSupplier fuse(BooleanSupplier initial) {
        return fuseBooleanSupplier(initial);
    }

    /* DoubleSupplier */

    public static Combine.WithDoubleSupplier fuseDoubleSupplier(
        DoubleSupplier initial
    ) {
        return Combine.WithDoubleSupplier.of(initial);
    }

    public static Combine.WithDoubleSupplier fuse(DoubleSupplier initial) {
        return fuseDoubleSupplier(initial);
    }

    /* IntSupplier */

    public static Combine.WithIntSupplier fuseIntSupplier(IntSupplier initial) {
        return Combine.WithIntSupplier.of(initial);
    }

    public static Combine.WithIntSupplier fuse(IntSupplier initial) {
        return fuseIntSupplier(initial);
    }

    /* LongSupplier */

    public static Combine.WithLongSupplier fuseLongSupplier(
        LongSupplier initial
    ) {
        return Combine.WithLongSupplier.of(initial);
    }

    public static Combine.WithLongSupplier fuse(LongSupplier initial) {
        return fuseLongSupplier(initial);
    }

    /* UnaryOperator [Note: May have erasure overlap with Function<A, A>] */

    public static <A> Combine.WithFunction<A, A> fuseUnaryOperator(
        UnaryOperator<A> initial
    ) {
        return Combine.WithFunction.of(initial);
    }

    /* BinaryOperator [Note: May have erasure overlap with BiFunction<A, A, A>] */

    public static <A> Combine.WithBiFunction<A, A, A> fuseBinaryOperator(
        BinaryOperator<A> initial
    ) {
        return Combine.WithBiFunction.of(initial);
    }

    /* DoubleUnaryOperator */

    public static Combine.WithDoubleUnaryOperator fuseDoubleUnaryOperator(
        DoubleUnaryOperator initial
    ) {
        return Combine.WithDoubleUnaryOperator.of(initial);
    }

    public static Combine.WithDoubleUnaryOperator fuse(
        DoubleUnaryOperator initial
    ) {
        return fuseDoubleUnaryOperator(initial);
    }

    /* DoubleBinaryOperator */

    public static Combine.WithDoubleBinaryOperator fuseDoubleBinaryOperator(
        DoubleBinaryOperator initial
    ) {
        return Combine.WithDoubleBinaryOperator.of(initial);
    }

    public static Combine.WithDoubleBinaryOperator fuse(
        DoubleBinaryOperator initial
    ) {
        return fuseDoubleBinaryOperator(initial);
    }

    /* IntUnaryOperator */

    public static Combine.WithIntUnaryOperator fuseIntUnaryOperator(
        IntUnaryOperator initial
    ) {
        return Combine.WithIntUnaryOperator.of(initial);
    }

    public static Combine.WithIntUnaryOperator fuse(IntUnaryOperator initial) {
        return fuseIntUnaryOperator(initial);
    }

    /* IntBinaryOperator */

    public static Combine.WithIntBinaryOperator fuseIntBinaryOperator(
        IntBinaryOperator initial
    ) {
        return Combine.WithIntBinaryOperator.of(initial);
    }

    public static Combine.WithIntBinaryOperator fuse(
        IntBinaryOperator initial
    ) {
        return fuseIntBinaryOperator(initial);
    }

    /* LongUnaryOperator */

    public static Combine.WithLongUnaryOperator fuseLongUnaryOperator(
        LongUnaryOperator initial
    ) {
        return Combine.WithLongUnaryOperator.of(initial);
    }

    public static Combine.WithLongUnaryOperator fuse(
        LongUnaryOperator initial
    ) {
        return fuseLongUnaryOperator(initial);
    }

    /* LongBinaryOperator */

    public static Combine.WithLongBinaryOperator fuseLongBinaryOperator(
        LongBinaryOperator initial
    ) {
        return Combine.WithLongBinaryOperator.of(initial);
    }

    public static Combine.WithLongBinaryOperator fuse(
        LongBinaryOperator initial
    ) {
        return fuseLongBinaryOperator(initial);
    }

    // ┏┓
    // ┏━━━━┓
    // ┏━━━━━━━━┓
    // ┏━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┃                                          ┃
    // ┃    ┏━┓╻ ╻┏━┓┏━╸┏━┓   ┏━╸╻ ╻┏━┓╻┏━┓┏┓╻    ┃
    // ┃    ┗━┓┃ ┃┣━┛┣╸ ┣┳┛   ┣╸ ┃ ┃┗━┓┃┃ ┃┃┗┫    ┃
    // ┃    ┗━┛┗━┛╹  ┗━╸╹┗╸   ╹  ┗━┛┗━┛╹┗━┛╹ ╹    ┃
    // ┃                                          ┃
    // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛

    // Classes, objects, and primitives into combinators.

    public static <A> Combine.WithFunction<A, A> withClass(Class<A> clazz) {
        return Combine.WithFunction.of(Z.id(clazz));
    }

    public static <A> Combine.WithFunction<A, A> with(Class<A> clazz) {
        return withClass(clazz);
    }

    public static <A> Combine.WithSupplier<A> withObject(A initial) {
        return Combine.WithSupplier.of(() -> initial);
    }

    public static <A> Combine.WithSupplier<A> with(A initial) {
        return withObject(initial);
    }

    public static Combine.WithBooleanSupplier withBoolean(boolean initial) {
        return Combine.WithBooleanSupplier.of(() -> initial);
    }

    public static Combine.WithBooleanSupplier with(boolean initial) {
        return withBoolean(initial);
    }

    public static Combine.WithDoubleSupplier withDouble(double initial) {
        return Combine.WithDoubleSupplier.of(() -> initial);
    }

    public static Combine.WithDoubleSupplier with(double initial) {
        return withDouble(initial);
    }

    public static Combine.WithIntSupplier withInt(int initial) {
        return Combine.WithIntSupplier.of(() -> initial);
    }

    public static Combine.WithIntSupplier with(int initial) {
        return withInt(initial);
    }

    public static Combine.WithLongSupplier withLong(long initial) {
        return Combine.WithLongSupplier.of(() -> initial);
    }

    public static Combine.WithLongSupplier with(long initial) {
        return withLong(initial);
    }

    // ┏┓
    // ┏━━━━┓
    // ┏━━━━━━━━┓
    // ┏━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┃                         ┃
    // ┃    ┏━╸╻┏━┓┏━┓╻┏━┓┏┓╻    ┃
    // ┃    ┣╸ ┃┗━┓┗━┓┃┃ ┃┃┗┫    ┃
    // ┃    ╹  ╹┗━┛┗━┛╹┗━┛╹ ╹    ┃
    // ┃                         ┃
    // ┗━━━━━━━━━━━━━━━━━━━━━━━━━┛

    // Multi-argument functions into curried forms.

    // autofmt:off

    public static <A, B, C>
        Function<A,
         Function<B, C>>
        split2(BiFunction<A, B, C> initial)
    {
        return Z.fuse(initial).resolve();
    }

    public static <A, B, C>
        Function<A,
         Function<B, C>>
        split(BiFunction<A, B, C> initial)
    {
        return split2(initial);
    }

    public static <A, B, C, D>
        Function<A,
         Function<B,
          Function<C, D>>>
        split3(TriFunction<A, B, C, D> initial)
    {
        return (A a) -> (B b) -> (C c) ->
            initial.apply(a, b, c);
    }

    public static <A, B, C, D>
        Function<A,
         Function<B,
          Function<C, D>>>
        split(TriFunction<A, B, C, D> initial)
    {
        return split3(initial);
    }

    public static <A, B, C, D, E>
        Function<A,
         Function<B,
          Function<C,
           Function<D, E>>>>
        split4(QuadFunction<A, B, C, D, E> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) ->
            initial.apply(a, b, c, d);
    }

    public static <A, B, C, D, E>
        Function<A,
         Function<B,
          Function<C,
           Function<D, E>>>>
        split(QuadFunction<A, B, C, D, E> initial)
    {
        return split4(initial);
    }

    public static <A, B, C, D, E, F>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E, F>>>>>
        split5(QuinFunction<A, B, C, D, E, F> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) ->
            initial.apply(a, b, c, d, e);
    }

    public static <A, B, C, D, E, F>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E, F>>>>>
        split(QuinFunction<A, B, C, D, E, F> initial)
    {
        return split5(initial);
    }

    public static <A, B, C, D, E, F, G>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F, G>>>>>>
        split6(SexFunction<A, B, C, D, E, F, G> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) ->
            initial.apply(a, b, c, d, e, f);
    }

    public static <A, B, C, D, E, F, G>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F, G>>>>>>
        split(SexFunction<A, B, C, D, E, F, G> initial)
    {
        return split6(initial);
    }

    public static <A, B, C, D, E, F, G, H>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G, H>>>>>>>
        split7(SeptFunction<A, B, C, D, E, F, G, H> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) ->
            initial.apply(a, b, c, d, e, f, g);
    }

    public static <A, B, C, D, E, F, G, H>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G, H>>>>>>>
        split(SeptFunction<A, B, C, D, E, F, G, H> initial)
    {
        return split7(initial);
    }

    public static <A, B, C, D, E, F, G, H, I>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H, I>>>>>>>>
        split8(OctFunction<A, B, C, D, E, F, G, H, I> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) -> (H h) ->
            initial.apply(a, b, c, d, e, f, g, h);
    }

    public static <A, B, C, D, E, F, G, H, I>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H, I>>>>>>>>
        split(OctFunction<A, B, C, D, E, F, G, H, I> initial)
    {
        return split8(initial);
    }

    public static <A, B, C, D, E, F, G, H, I, J>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H,
                Function<I, J>>>>>>>>>
        split9(NonFunction<A, B, C, D, E, F, G, H, I, J> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) -> (H h) -> (I i) ->
            initial.apply(a, b, c, d, e, f, g, h, i);
    }

    public static <A, B, C, D, E, F, G, H, I, J>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H,
                Function<I, J>>>>>>>>>
        split(NonFunction<A, B, C, D, E, F, G, H, I, J> initial)
    {
        return split9(initial);
    }

    public static <A, B, C, D, E, F, G, H, I, J, K>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H,
                Function<I,
                 Function<J, K>>>>>>>>>>
        split10(DecFunction<A, B, C, D, E, F, G, H, I, J, K> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) -> (H h) -> (I i) -> (J j) ->
            initial.apply(a, b, c, d, e, f, g, h, i, j);
    }

    public static <A, B, C, D, E, F, G, H, I, J, K>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H,
                Function<I,
                 Function<J, K>>>>>>>>>>
        split(DecFunction<A, B, C, D, E, F, G, H, I, J, K> initial)
    {
        return split10(initial);
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H,
                Function<I,
                 Function<J,
                  Function<K, L>>>>>>>>>>>
        split11(UndecFunction<A, B, C, D, E, F, G, H, I, J, K, L> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) ->
                (G g) -> (H h) -> (I i) -> (J j) -> (K k) ->
                 initial.apply(a, b, c, d, e, f, g, h, i, j, k);
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H,
                Function<I,
                 Function<J,
                  Function<K, L>>>>>>>>>>>
        split(UndecFunction<A, B, C, D, E, F, G, H, I, J, K, L> initial)
    {
        return split11(initial);
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H,
                Function<I,
                 Function<J,
                  Function<K,
                   Function<L, M>>>>>>>>>>>>
        split12(DodecFunction<A, B, C, D, E, F, G, H, I, J, K, L, M> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) ->
                (G g) -> (H h) -> (I i) -> (J j) -> (K k) -> (L l) ->
                 initial.apply(a, b, c, d, e, f, g, h, i, j, k, l);
    }

    public static <A, B, C, D, E, F, G, H, I, J, K, L, M>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E,
             Function<F,
              Function<G,
               Function<H,
                Function<I,
                 Function<J,
                  Function<K,
                   Function<L, M>>>>>>>>>>>>
        split(DodecFunction<A, B, C, D, E, F, G, H, I, J, K, L, M> initial)
    {
        return split12(initial);
    }

	// autofmt:on

    public static <A, B> Function<A, ToDoubleFunction<B>> splitToDoubleBiFunction(
        ToDoubleBiFunction<A, B> initial
    ) {
        return Z.fuse(initial);
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> split(
        ToDoubleBiFunction<A, B> initial
    ) {
        return splitToDoubleBiFunction(initial);
    }

    public static <A, B> Function<A, ToIntFunction<B>> splitToIntBiFunction(
        ToIntBiFunction<A, B> initial
    ) {
        return Z.fuse(initial);
    }

    public static <A, B> Function<A, ToIntFunction<B>> split(
        ToIntBiFunction<A, B> initial
    ) {
        return splitToIntBiFunction(initial);
    }

    public static <A, B> Function<A, ToLongFunction<B>> splitToLongBiFunction(
        ToLongBiFunction<A, B> initial
    ) {
        return Z.fuse(initial);
    }

    public static <A, B> Function<A, ToLongFunction<B>> split(
        ToLongBiFunction<A, B> initial
    ) {
        return splitToLongBiFunction(initial);
    }

    public static <A, B> Function<A, Predicate<B>> splitBiPredicate(
        BiPredicate<A, B> initial
    ) {
        return Z.fuse(initial);
    }

    public static <A, B> Function<A, Predicate<B>> split(
        BiPredicate<A, B> initial
    ) {
        return splitBiPredicate(initial);
    }

    public static <A> Function<A, DoubleConsumer> splitObjDoubleConsumer(
        ObjDoubleConsumer<A> initial
    ) {
        return Z.fuse(initial);
    }

    public static <A> Function<A, DoubleConsumer> split(
        ObjDoubleConsumer<A> initial
    ) {
        return splitObjDoubleConsumer(initial);
    }

    public static <A> Function<A, IntConsumer> splitObjIntConsumer(
        ObjIntConsumer<A> initial
    ) {
        return Z.fuse(initial);
    }

    public static <A> Function<A, IntConsumer> split(
        ObjIntConsumer<A> initial
    ) {
        return splitObjIntConsumer(initial);
    }

    public static <A> Function<A, LongConsumer> splitObjLongConsumer(
        ObjLongConsumer<A> initial
    ) {
        return Z.fuse(initial);
    }

    public static <A> Function<A, LongConsumer> split(
        ObjLongConsumer<A> initial
    ) {
        return splitObjLongConsumer(initial);
    }

    public static <A, B> Function<A, Consumer<B>> splitBiConsumer(
        BiConsumer<A, B> initial
    ) {
        return Z.fuse(initial);
    }

    public static <A, B> Function<A, Consumer<B>> split(
        BiConsumer<A, B> initial
    ) {
        return splitBiConsumer(initial);
    }

    // ┏┓
    // ┏━━━━┓
    // ┏━━━━━━━━┓
    // ┏━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┃                                      ┃
    // ┃    ┏━┓┏━┓┏━┓╻┏┳┓╻╻  ┏━┓╺┳╸╻┏━┓┏┓╻    ┃
    // ┃    ┣━┫┗━┓┗━┓┃┃┃┃┃┃  ┣━┫ ┃ ┃┃ ┃┃┗┫    ┃
    // ┃    ╹ ╹┗━┛┗━┛╹╹ ╹╹┗━╸╹ ╹ ╹ ╹┗━┛╹ ╹    ┃
    // ┃                                      ┃
    // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛

    // Curried functions into multi-argument functions.

    /* Multifunctions */

    // autofmt:off

    @Evil
    public static <A, B, C>
        BiFunction<A, B, C>
        assimilate2(
            Function<A,
             Function<B, C>> curried)
    {
        return (A a, B b) ->
            curried.apply(a).apply(b); 
    }

    @Evil
    public static <A, B, C, D>
        TriFunction<A, B, C, D>
        assimilate3(
            Function<A,
             Function<B,
              Function<C, D>>> curried)
    {
        return (A a, B b, C c) ->
            curried.apply(a).apply(b).apply(c); 
    }

    @Evil
    public static <A, B, C, D, E>
        QuadFunction<A, B, C, D, E>
        assimilate4(
            Function<A,
             Function<B,
              Function<C,
               Function<D, E>>>> curried)
    {
        return (A a, B b, C c, D d) ->
            curried.apply(a).apply(b).apply(c).apply(d); 
    }

    @Evil
    public static <A, B, C, D, E, F>
        QuinFunction<A, B, C, D, E, F>
        assimilate5(
            Function<A,
             Function<B,
              Function<C,
               Function<D,
                Function<E, F>>>>> curried)
    {
        return (A a, B b, C c, D d, E e) ->
            curried.apply(a).apply(b).apply(c).apply(d).apply(e);
    }

    @Evil
    public static <A, B, C, D, E, F, G>
        SexFunction<A, B, C, D, E, F, G>
        assimilate6(
            Function<A,
             Function<B,
              Function<C,
               Function<D,
                Function<E,
                 Function<F, G>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f) ->
            curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f);
    }

    @Evil
    public static <A, B, C, D, E, F, G, H>
        SeptFunction<A, B, C, D, E, F, G, H>
        assimilate7(
            Function<A,
             Function<B,
              Function<C,
               Function<D,
                Function<E,
                 Function<F,
                  Function<G, H>>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f, G g) ->
            curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f).apply(g);
    }

    @Evil
    public static <A, B, C, D, E, F, G, H, I>
        OctFunction<A, B, C, D, E, F, G, H, I>
        assimilate8(
            Function<A,
             Function<B,
              Function<C,
               Function<D,
                Function<E,
                 Function<F,
                  Function<G,
                   Function<H, I>>>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f, G g, H h) ->
            curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f).apply(g).apply(h);
    }

    @Evil
    public static <A, B, C, D, E, F, G, H, I, J>
        NonFunction<A, B, C, D, E, F, G, H, I, J>
        assimilate9(
            Function<A,
             Function<B,
              Function<C,
               Function<D,
                Function<E,
                 Function<F,
                  Function<G,
                   Function<H,
                    Function<I, J>>>>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f, G g, H h, I i) ->
            curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f).apply(g).apply(h).apply(i);
    }

    @Evil
    public static <A, B, C, D, E, F, G, H, I, J, K>
        DecFunction<A, B, C, D, E, F, G, H, I, J, K>
        assimilate10(
            Function<A,
             Function<B,
              Function<C,
               Function<D,
                Function<E,
                 Function<F,
                  Function<G,
                   Function<H,
                    Function<I,
                     Function<J, K>>>>>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) ->
            curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f).apply(g).apply(h).apply(i).apply(j);
    }

    @Evil
    public static <A, B, C, D, E, F, G, H, I, J, K, L>
        UndecFunction<A, B, C, D, E, F, G, H, I, J, K, L>
        assimilate11(
            Function<A,
             Function<B,
              Function<C,
               Function<D,
                Function<E,
                 Function<F,
                  Function<G,
                   Function<H,
                    Function<I,
                     Function<J,
                      Function<K, L>>>>>>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k) ->
            curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f).apply(g).apply(h).apply(i).apply(j).apply(k);
    }

    @Evil
    public static <A, B, C, D, E, F, G, H, I, J, K, L, M>
        DodecFunction<A, B, C, D, E, F, G, H, I, J, K, L, M>
        assimilate12(
            Function<A,
             Function<B,
              Function<C,
               Function<D,
                Function<E,
                 Function<F,
                  Function<G,
                   Function<H,
                    Function<I,
                     Function<J,
                      Function<K,
                       Function<L, M>>>>>>>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l) ->
            curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f).apply(g).apply(h).apply(i).apply(j).apply(k).apply(l);
    }

	// autofmt:on

    // ┏┓
    // ┏━━━━┓
    // ┏━━━━━━━━┓
    // ┏━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
    // ┃                                    ┃
    // ┃    ┏━┓┏┓ ┏━┓┏━┓┏━┓┏━┓╺┳╸╻┏━┓┏┓╻    ┃
    // ┃    ┣━┫┣┻┓┗━┓┃ ┃┣┳┛┣━┛ ┃ ┃┃ ┃┃┗┫    ┃
    // ┃    ╹ ╹┗━┛┗━┛┗━┛╹┗╸╹   ╹ ╹┗━┛╹ ╹    ┃
    // ┃                                    ┃
    // ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛

    // Terminal functions into combinators.

    /* Consumer */

    @Evil
    public static <A> Combine.WithConsumer<A> fuseConsumer(
        Consumer<A> initial
    ) {
        return Combine.WithConsumer.of(initial);
    }

    @Evil
    public static <A> Combine.WithConsumer<A> fuse(Consumer<A> initial) {
        return fuseConsumer(initial);
    }

    /* BiConsumer */

    @Evil
    public static <A, B> Combine.WithBiConsumer<A, B> fuseBiConsumer(
        BiConsumer<A, B> initial
    ) {
        return Combine.WithBiConsumer.of(initial);
    }

    @Evil
    public static <A, B> Combine.WithBiConsumer<A, B> fuse(
        BiConsumer<A, B> initial
    ) {
        return fuseBiConsumer(initial);
    }

    /* BooleanConsumer */

    @Evil
    public static Combine.WithBooleanConsumer fuseBooleanConsumer(
        BooleanConsumer initial
    ) {
        return Combine.WithBooleanConsumer.of(initial);
    }

    @Evil
    public static Combine.WithBooleanConsumer fuse(BooleanConsumer initial) {
        return fuseBooleanConsumer(initial);
    }

    /* DoubleConsumer */

    @Evil
    public static Combine.WithDoubleConsumer fuseDoubleConsumer(
        DoubleConsumer initial
    ) {
        return Combine.WithDoubleConsumer.of(initial);
    }

    @Evil
    public static Combine.WithDoubleConsumer fuse(DoubleConsumer initial) {
        return fuseDoubleConsumer(initial);
    }

    /* ObjDoubleConsumer */

    @Evil
    public static <A> Combine.WithObjDoubleConsumer<A> fuseObjDoubleConsumer(
        ObjDoubleConsumer<A> initial
    ) {
        return Combine.WithObjDoubleConsumer.of(initial);
    }

    @Evil
    public static <A> Combine.WithObjDoubleConsumer<A> fuse(
        ObjDoubleConsumer<A> initial
    ) {
        return fuseObjDoubleConsumer(initial);
    }

    /* IntConsumer */

    @Evil
    public static Combine.WithIntConsumer fuseIntConsumer(IntConsumer initial) {
        return Combine.WithIntConsumer.of(initial);
    }

    @Evil
    public static Combine.WithIntConsumer fuse(IntConsumer initial) {
        return fuseIntConsumer(initial);
    }

    /* ObjIntConsumer */

    @Evil
    public static <A> Combine.WithObjIntConsumer<A> fuseObjIntConsumer(
        ObjIntConsumer<A> initial
    ) {
        return Combine.WithObjIntConsumer.of(initial);
    }

    @Evil
    public static <A> Combine.WithObjIntConsumer<A> fuse(
        ObjIntConsumer<A> initial
    ) {
        return fuseObjIntConsumer(initial);
    }

    /* LongConsumer */

    @Evil
    public static Combine.WithLongConsumer fuseLongConsumer(
        LongConsumer initial
    ) {
        return Combine.WithLongConsumer.of(initial);
    }

    @Evil
    public static Combine.WithLongConsumer fuse(LongConsumer initial) {
        return fuseLongConsumer(initial);
    }

    /* ObjLongConsumer */

    @Evil
    public static <A> Combine.WithObjLongConsumer<A> fuseObjLongConsumer(
        ObjLongConsumer<A> initial
    ) {
        return Combine.WithObjLongConsumer.of(initial);
    }

    @Evil
    public static <A> Combine.WithObjLongConsumer<A> fuse(
        ObjLongConsumer<A> initial
    ) {
        return fuseObjLongConsumer(initial);
    }

    /* Operator */

    @Evil
    public static Combine.WithOperator fuseOperator(Operator initial) {
        return Combine.WithOperator.of(initial);
    }

    @Evil
    public static Combine.WithOperator fuse(Operator initial) {
        return fuseOperator(initial);
    }
}
