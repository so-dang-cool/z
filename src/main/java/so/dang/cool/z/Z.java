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
import so.dang.cool.z.annotation.Experimental;
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

    /* Object */

    public static <A, B> Supplier<B> fuseObject(
        A initial,
        Function<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<B, C> fuseObject(
        A initial,
        BiFunction<A, B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> DoubleSupplier fuseObject(
        A initial,
        ToDoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> ToDoubleFunction<B> fuseObject(
        A initial,
        ToDoubleBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> IntSupplier fuseObject(A initial, ToIntFunction<A> next) {
        return () -> next.applyAsInt(initial);
    }

    public static <A, B> ToIntFunction<B> fuseObject(
        A initial,
        ToIntBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> LongSupplier fuseObject(
        A initial,
        ToLongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> ToLongFunction<B> fuseObject(
        A initial,
        ToLongBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> BooleanSupplier fuseObject(A initial, Predicate<A> next) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Predicate<B> fuseObject(
        A initial,
        BiPredicate<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Operator fuseObject(A initial, Consumer<A> next) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Consumer<B> fuseObject(
        A initial,
        BiConsumer<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> DoubleConsumer fuseObject(
        A initial,
        ObjDoubleConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> IntConsumer fuseObject(
        A initial,
        ObjIntConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> LongConsumer fuseObject(
        A initial,
        ObjLongConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Supplier<A> fuseObject(A initial, UnaryOperator<A> next) {
        return Z.with(initial).fuse(next);
    }

    public static <A> UnaryOperator<A> fuseObject(
        A initial,
        BinaryOperator<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* boolean */

    public static <A> Supplier<A> fuse(
        boolean initial,
        BooleanFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, B> fuse(
        boolean initial,
        BiFunction<Boolean, A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        boolean initial,
        BooleanToDoubleFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> ToDoubleFunction<A> fuse(
        boolean initial,
        ToDoubleBiFunction<Boolean, A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static IntSupplier fuse(boolean initial, BooleanToIntFunction next) {
        return Z.with(initial).fuse(next);
    }

    public static <A> ToIntFunction<A> fuse(
        boolean initial,
        ToIntBiFunction<Boolean, A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static LongSupplier fuse(
        boolean initial,
        BooleanToLongFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> ToLongFunction<A> fuse(
        boolean initial,
        ToLongBiFunction<Boolean, A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanSupplier fuse(boolean initial, BooleanPredicate next) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Predicate<A> fuse(
        boolean initial,
        BiPredicate<Boolean, A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Operator fuse(boolean initial, BooleanConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Consumer<A> fuse(
        boolean initial,
        BiConsumer<Boolean, A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* double */

    public static <A> Supplier<A> fuse(double initial, DoubleFunction<A> next) {
        return Z.with(initial).fuse(next);
    }

    public static IntSupplier fuse(double initial, DoubleToIntFunction next) {
        return Z.with(initial).fuse(next);
    }

    public static LongSupplier fuse(double initial, DoubleToLongFunction next) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanSupplier fuse(double initial, DoublePredicate next) {
        return Z.with(initial).fuse(next);
    }

    public static Operator fuse(double initial, DoubleConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        double initial,
        DoubleUnaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        double initial,
        DoubleBinaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* int */

    public static <A> Supplier<A> fuse(int initial, IntFunction<A> next) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleSupplier fuse(int initial, IntToDoubleFunction next) {
        return Z.with(initial).fuse(next);
    }

    public static LongSupplier fuse(int initial, IntToLongFunction next) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanSupplier fuse(int initial, IntPredicate next) {
        return Z.with(initial).fuse(next);
    }

    public static Operator fuse(int initial, IntConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static IntSupplier fuse(int initial, IntUnaryOperator next) {
        return Z.with(initial).fuse(next);
    }

    public static IntUnaryOperator fuse(int initial, IntBinaryOperator next) {
        return Z.with(initial).fuse(next);
    }

    /* long */

    public static <A> Supplier<A> fuse(long initial, LongFunction<A> next) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleSupplier fuse(long initial, LongToDoubleFunction next) {
        return Z.with(initial).fuse(next);
    }

    public static IntSupplier fuse(long initial, LongToIntFunction next) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanSupplier fuse(long initial, LongPredicate next) {
        return Z.with(initial).fuse(next);
    }

    public static Operator fuse(long initial, LongConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static LongSupplier fuse(long initial, LongUnaryOperator next) {
        return Z.with(initial).fuse(next);
    }

    public static LongUnaryOperator fuse(
        long initial,
        LongBinaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* Function */

    public static <A, B, C> Function<A, C> fuse(
        Function<A, B> initial,
        Function<B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<C, D>> fuse(
        Function<A, B> initial,
        BiFunction<B, C, D> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> ToDoubleFunction<A> fuse(
        Function<A, B> initial,
        ToDoubleFunction<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToDoubleFunction<C>> fuse(
        Function<A, B> initial,
        ToDoubleBiFunction<B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> ToIntFunction<A> fuse(
        Function<A, B> initial,
        ToIntFunction<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToIntFunction<C>> fuse(
        Function<A, B> initial,
        ToIntBiFunction<B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> ToLongFunction<A> fuse(
        Function<A, B> initial,
        ToLongFunction<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToLongFunction<C>> fuse(
        Function<A, B> initial,
        ToLongBiFunction<B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Predicate<A> fuse(
        Function<A, B> initial,
        Predicate<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Predicate<C>> fuse(
        Function<A, B> initial,
        BiPredicate<B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Consumer<A> fuse(
        Function<A, B> initial,
        Consumer<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Consumer<C>> fuse(
        Function<A, B> initial,
        BiConsumer<B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, DoubleConsumer> fuse(
        Function<A, B> initial,
        ObjDoubleConsumer<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, IntConsumer> fuse(
        Function<A, B> initial,
        ObjIntConsumer<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, LongConsumer> fuse(
        Function<A, B> initial,
        ObjLongConsumer<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, B> fuse(
        Function<A, B> initial,
        UnaryOperator<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, UnaryOperator<B>> fuse(
        Function<A, B> initial,
        BinaryOperator<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* BiFunction */

    public static <A, B, C, D> Function<A, Function<B, D>> fuse(
        BiFunction<A, B, C> initial,
        Function<C, D> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D, E> Function<A, Function<B, Function<D, E>>> fuse(
        BiFunction<A, B, C> initial,
        BiFunction<C, D, E> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToDoubleFunction<B>> fuse(
        BiFunction<A, B, C> initial,
        ToDoubleFunction<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, ToDoubleFunction<D>>> fuse(
        BiFunction<A, B, C> initial,
        ToDoubleBiFunction<C, D> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToIntFunction<B>> fuse(
        BiFunction<A, B, C> initial,
        ToIntFunction<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, ToIntFunction<D>>> fuse(
        BiFunction<A, B, C> initial,
        ToIntBiFunction<C, D> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToLongFunction<B>> fuse(
        BiFunction<A, B, C> initial,
        ToLongFunction<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, ToLongFunction<D>>> fuse(
        BiFunction<A, B, C> initial,
        ToLongBiFunction<C, D> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Predicate<B>> fuse(
        BiFunction<A, B, C> initial,
        Predicate<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, Predicate<D>>> fuse(
        BiFunction<A, B, C> initial,
        BiPredicate<C, D> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Consumer<B>> fuse(
        BiFunction<A, B, C> initial,
        Consumer<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, Consumer<D>>> fuse(
        BiFunction<A, B, C> initial,
        BiConsumer<C, D> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, DoubleConsumer>> fuse(
        BiFunction<A, B, C> initial,
        ObjDoubleConsumer<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, IntConsumer>> fuse(
        BiFunction<A, B, C> initial,
        ObjIntConsumer<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, LongConsumer>> fuse(
        BiFunction<A, B, C> initial,
        ObjLongConsumer<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* BooleanFunction */

    public static <A, B> BooleanFunction<B> fuse(
        BooleanFunction<A> initial,
        Function<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> BooleanFunction<Function<B, C>> fuse(
        BooleanFunction<A> initial,
        BiFunction<A, B, C> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b1) -> (B b2) -> next.apply(initial.apply(b1), b2);
    }

    public static <A> BooleanToDoubleFunction fuse(
        BooleanFunction<A> initial,
        ToDoubleFunction<A> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsDouble(initial.apply(b));
    }

    public static <A, B> BooleanFunction<ToDoubleFunction<B>> fuse(
        BooleanFunction<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b1) ->
            (B b2) -> next.applyAsDouble(initial.apply(b1), b2);
    }

    public static <A> BooleanToIntFunction fuse(
        BooleanFunction<A> initial,
        ToIntFunction<A> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsInt(initial.apply(b));
    }

    public static <A, B> BooleanFunction<ToIntFunction<B>> fuse(
        BooleanFunction<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b1) -> (B b2) -> next.applyAsInt(initial.apply(b1), b2);
    }

    public static <A> BooleanToLongFunction fuse(
        BooleanFunction<A> initial,
        ToLongFunction<A> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsLong(initial.apply(b));
    }

    public static <A, B> BooleanFunction<ToLongFunction<B>> fuse(
        BooleanFunction<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b1) ->
            (B b2) -> next.applyAsLong(initial.apply(b1), b2);
    }

    public static <A> BooleanPredicate fuse(
        BooleanFunction<A> initial,
        Predicate<A> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.test(initial.apply(b));
    }

    public static <A, B> BooleanFunction<Predicate<B>> fuse(
        BooleanFunction<A> initial,
        BiPredicate<A, B> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b1) -> (B b2) -> next.test(initial.apply(b1), b2);
    }

    public static <A> BooleanConsumer fuse(
        BooleanFunction<A> initial,
        Consumer<A> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.accept(initial.apply(b));
    }

    public static <A, B> BooleanFunction<Consumer<B>> fuse(
        BooleanFunction<A> initial,
        BiConsumer<A, B> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b1) -> (B b2) -> next.accept(initial.apply(b1), b2);
    }

    public static <A> BooleanFunction<DoubleConsumer> fuse(
        BooleanFunction<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (double d) -> next.accept(initial.apply(b), d);
    }

    public static <A> BooleanFunction<IntConsumer> fuse(
        BooleanFunction<A> initial,
        ObjIntConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (int i) -> next.accept(initial.apply(b), i);
    }

    public static <A> BooleanFunction<LongConsumer> fuse(
        BooleanFunction<A> initial,
        ObjLongConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithBooleanFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (long n) -> next.accept(initial.apply(b), n);
    }

    /* BooleanToDoubleFunction */

    public static <A> BooleanFunction<A> fuse(
        BooleanToDoubleFunction initial,
        DoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanToIntFunction fuse(
        BooleanToDoubleFunction initial,
        DoubleToIntFunction next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsInt(initial.applyAsDouble(b));
    }

    public static BooleanToLongFunction fuse(
        BooleanToDoubleFunction initial,
        DoubleToLongFunction next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsLong(initial.applyAsDouble(b));
    }

    public static BooleanPredicate fuse(
        BooleanToDoubleFunction initial,
        DoublePredicate next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.test(initial.applyAsDouble(b));
    }

    public static BooleanConsumer fuse(
        BooleanToDoubleFunction initial,
        DoubleConsumer next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.accept(initial.applyAsDouble(b));
    }

    public static BooleanToDoubleFunction fuse(
        BooleanToDoubleFunction initial,
        DoubleUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsDouble(initial.applyAsDouble(b));
    }

    public static BooleanFunction<DoubleUnaryOperator> fuse(
        BooleanToDoubleFunction initial,
        DoubleBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) ->
            (double d) -> next.applyAsDouble(initial.applyAsDouble(b), d);
    }

    /* BooleanToIntFunction */

    public static <A> BooleanFunction<A> fuse(
        BooleanToIntFunction initial,
        IntFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanToDoubleFunction fuse(
        BooleanToIntFunction initial,
        IntToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsDouble(initial.applyAsInt(b));
    }

    public static BooleanToLongFunction fuse(
        BooleanToIntFunction initial,
        IntToLongFunction next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsLong(initial.applyAsInt(b));
    }

    public static BooleanPredicate fuse(
        BooleanToIntFunction initial,
        IntPredicate next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.test(initial.applyAsInt(b));
    }

    public static BooleanConsumer fuse(
        BooleanToIntFunction initial,
        IntConsumer next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.accept(initial.applyAsInt(b));
    }

    public static BooleanToIntFunction fuse(
        BooleanToIntFunction initial,
        IntUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsInt(initial.applyAsInt(b));
    }

    public static BooleanFunction<IntUnaryOperator> fuse(
        BooleanToIntFunction initial,
        IntBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithBooleanToIntFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) ->
            (int i) -> next.applyAsInt(initial.applyAsInt(b), i);
    }

    /* BooleanToLongFunction */

    public static <A> BooleanFunction<A> fuse(
        BooleanToLongFunction initial,
        LongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanToDoubleFunction fuse(
        BooleanToLongFunction initial,
        LongToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithBooleanToLongFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsDouble(initial.applyAsLong(b));
    }

    public static BooleanToIntFunction fuse(
        BooleanToLongFunction initial,
        LongToIntFunction next
    ) {
        // TODO: Defer to Combine.WithBooleanToLongFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsInt(initial.applyAsLong(b));
    }

    public static BooleanPredicate fuse(
        BooleanToLongFunction initial,
        LongPredicate next
    ) {
        // TODO: Defer to Combine.WithBooleanToLongFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.test(initial.applyAsLong(b));
    }

    public static BooleanConsumer fuse(
        BooleanToLongFunction initial,
        LongConsumer next
    ) {
        // TODO: Defer to Combine.WithBooleanToLongFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.accept(initial.applyAsLong(b));
    }

    public static BooleanToLongFunction fuse(
        BooleanToLongFunction initial,
        LongUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithBooleanToLongFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsLong(initial.applyAsLong(b));
    }

    public static BooleanFunction<LongUnaryOperator> fuse(
        BooleanToLongFunction initial,
        LongBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithBooleanToLongFunction
        // return Z.with(initial).fuse(next);
        return (boolean b) ->
            (long n) -> next.applyAsLong(initial.applyAsLong(b), n);
    }

    /* DoubleFunction */

    public static <A, B> DoubleFunction<B> fuse(
        DoubleFunction<A> initial,
        Function<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> DoubleFunction<Function<B, C>> fuse(
        DoubleFunction<A> initial,
        BiFunction<A, B, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> (B b) -> next.apply(initial.apply(d), b);
    }

    public static <A> DoubleUnaryOperator fuse(
        DoubleFunction<A> initial,
        ToDoubleFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsDouble(initial.apply(d));
    }

    public static <A, B> DoubleFunction<ToDoubleFunction<B>> fuse(
        DoubleFunction<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> (B b) -> next.applyAsDouble(initial.apply(d), b);
    }

    public static <A> DoubleToIntFunction fuse(
        DoubleFunction<A> initial,
        ToIntFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsInt(initial.apply(d));
    }

    public static <A, B> DoubleFunction<ToIntFunction<B>> fuse(
        DoubleFunction<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> (B b) -> next.applyAsInt(initial.apply(d), b);
    }

    public static <A> DoubleToLongFunction fuse(
        DoubleFunction<A> initial,
        ToLongFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsLong(initial.apply(d));
    }

    public static <A, B> DoubleFunction<ToLongFunction<B>> fuse(
        DoubleFunction<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> (B b) -> next.applyAsLong(initial.apply(d), b);
    }

    public static <A> DoublePredicate fuse(
        DoubleFunction<A> initial,
        Predicate<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.test(initial.apply(d));
    }

    public static <A, B> DoubleFunction<Predicate<B>> fuse(
        DoubleFunction<A> initial,
        BiPredicate<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> (B b) -> next.test(initial.apply(d), b);
    }

    public static <A> DoubleConsumer fuse(
        DoubleFunction<A> initial,
        Consumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.accept(initial.apply(d));
    }

    public static <A, B> DoubleFunction<Consumer<B>> fuse(
        DoubleFunction<A> initial,
        BiConsumer<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> (B b) -> next.accept(initial.apply(d), b);
    }

    public static <A> DoubleFunction<DoubleConsumer> fuse(
        DoubleFunction<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> (double d2) -> next.accept(initial.apply(d), d2);
    }

    public static <A> DoubleFunction<IntConsumer> fuse(
        DoubleFunction<A> initial,
        ObjIntConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> (int i) -> next.accept(initial.apply(d), i);
    }

    public static <A> DoubleFunction<LongConsumer> fuse(
        DoubleFunction<A> initial,
        ObjLongConsumer<A> next
    ) {
        return (double d) -> (long n) -> next.accept(initial.apply(d), n);
    }

    /* DoubleToIntFunction */

    public static <A> DoubleFunction<A> fuse(
        DoubleToIntFunction initial,
        IntFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleToIntFunction
        // return Z.with(initial).fuse(next);
        return Z.with(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        DoubleToIntFunction initial,
        IntToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleToIntFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsDouble(initial.applyAsInt(d));
    }

    public static DoubleToLongFunction fuse(
        DoubleToIntFunction initial,
        IntToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleToIntFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsLong(initial.applyAsInt(d));
    }

    public static DoublePredicate fuse(
        DoubleToIntFunction initial,
        IntPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleToIntFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.test(initial.applyAsInt(d));
    }

    public static DoubleConsumer fuse(
        DoubleToIntFunction initial,
        IntConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleToIntFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.accept(initial.applyAsInt(d));
    }

    public static DoubleToIntFunction fuse(
        DoubleToIntFunction initial,
        IntUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleToIntFunction
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsInt(initial.applyAsInt(d));
    }

    public static DoubleFunction<IntUnaryOperator> fuse(
        DoubleToIntFunction initial,
        IntBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleToIntFunction
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (int i) -> next.applyAsInt(initial.applyAsInt(d), i);
    }

    /* DoubleToLongFunction */

    public static <A> DoubleFunction<A> fuse(
        DoubleToLongFunction initial,
        LongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        DoubleToLongFunction initial,
        LongToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsDouble(initial.applyAsLong(d));
    }

    public static DoubleToIntFunction fuse(
        DoubleToLongFunction initial,
        LongToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsInt(initial.applyAsLong(d));
    }

    public static DoublePredicate fuse(
        DoubleToLongFunction initial,
        LongPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.test(initial.applyAsLong(d));
    }

    public static DoubleConsumer fuse(
        DoubleToLongFunction initial,
        LongConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.accept(initial.applyAsLong(d));
    }

    public static DoubleToLongFunction fuse(
        DoubleToLongFunction initial,
        LongUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsLong(initial.applyAsLong(d));
    }

    public static DoubleFunction<LongUnaryOperator> fuse(
        DoubleToLongFunction initial,
        LongBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (long n) -> next.applyAsLong(initial.applyAsLong(d), n);
    }

    /* ToDoubleFunction */

    public static <A, B> Function<A, B> fuse(
        ToDoubleFunction<A> initial,
        DoubleFunction<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> ToIntFunction<A> fuse(
        ToDoubleFunction<A> initial,
        DoubleToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsInt(initial.applyAsDouble(a));
    }

    public static <A> ToLongFunction<A> fuse(
        ToDoubleFunction<A> initial,
        DoubleToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsLong(initial.applyAsDouble(a));
    }

    public static <A> Predicate<A> fuse(
        ToDoubleFunction<A> initial,
        DoublePredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.test(initial.applyAsDouble(a));
    }

    public static <A> Consumer<A> fuse(
        ToDoubleFunction<A> initial,
        DoubleConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.accept(initial.applyAsDouble(a));
    }

    public static <A> ToDoubleFunction<A> fuse(
        ToDoubleFunction<A> initial,
        DoubleUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsDouble(initial.applyAsDouble(a));
    }

    public static <A> Function<A, DoubleUnaryOperator> fuse(
        ToDoubleFunction<A> initial,
        DoubleBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (double d) -> next.applyAsDouble(initial.applyAsDouble(a), d);
    }

    /* ToDoubleBiFunction */

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleFunction<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsInt(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsLong(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, Predicate<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoublePredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.test(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, Consumer<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.accept(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) -> next.applyAsDouble(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, Function<B, DoubleUnaryOperator>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) ->
                (double d) ->
                    next.applyAsDouble(initial.applyAsDouble(a, b), d);
    }

    /* IntFunction */

    public static <A, B> IntFunction<B> fuse(
        IntFunction<A> initial,
        Function<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> IntFunction<Function<B, C>> fuse(
        IntFunction<A> initial,
        BiFunction<A, B, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (B b) -> next.apply(initial.apply(i), b);
    }

    public static <A> IntToDoubleFunction fuse(
        IntFunction<A> initial,
        ToDoubleFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int d) -> next.applyAsDouble(initial.apply(d));
    }

    public static <A, B> IntFunction<ToDoubleFunction<B>> fuse(
        IntFunction<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (B b) -> next.applyAsDouble(initial.apply(i), b);
    }

    public static <A> IntUnaryOperator fuse(
        IntFunction<A> initial,
        ToIntFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsInt(initial.apply(i));
    }

    public static <A, B> IntFunction<ToIntFunction<B>> fuse(
        IntFunction<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (B b) -> next.applyAsInt(initial.apply(i), b);
    }

    public static <A> IntToLongFunction fuse(
        IntFunction<A> initial,
        ToLongFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsLong(initial.apply(i));
    }

    public static <A, B> IntFunction<ToLongFunction<B>> fuse(
        IntFunction<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (B b) -> next.applyAsLong(initial.apply(i), b);
    }

    public static <A> IntPredicate fuse(
        IntFunction<A> initial,
        Predicate<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.test(initial.apply(i));
    }

    public static <A, B> IntFunction<Predicate<B>> fuse(
        IntFunction<A> initial,
        BiPredicate<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (B b) -> next.test(initial.apply(i), b);
    }

    public static <A> IntConsumer fuse(
        IntFunction<A> initial,
        Consumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.accept(initial.apply(i));
    }

    public static <A, B> IntFunction<Consumer<B>> fuse(
        IntFunction<A> initial,
        BiConsumer<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (B b) -> next.accept(initial.apply(i), b);
    }

    public static <A> IntFunction<DoubleConsumer> fuse(
        IntFunction<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (double d) -> next.accept(initial.apply(i), d);
    }

    public static <A> IntFunction<IntConsumer> fuse(
        IntFunction<A> initial,
        ObjIntConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (int i2) -> next.accept(initial.apply(i), i2);
    }

    public static <A> IntFunction<LongConsumer> fuse(
        IntFunction<A> initial,
        ObjLongConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (long n) -> next.accept(initial.apply(i), n);
    }

    /* IntToDoubleFunction */

    public static <A> IntFunction<A> fuse(
        IntToDoubleFunction initial,
        DoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static IntUnaryOperator fuse(
        IntToDoubleFunction initial,
        DoubleToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsInt(initial.applyAsDouble(i));
    }

    public static IntToLongFunction fuse(
        IntToDoubleFunction initial,
        DoubleToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsLong(initial.applyAsDouble(i));
    }

    public static IntPredicate fuse(
        IntToDoubleFunction initial,
        DoublePredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.test(initial.applyAsDouble(i));
    }

    public static IntConsumer fuse(
        IntToDoubleFunction initial,
        DoubleConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.accept(initial.applyAsDouble(i));
    }

    public static IntToDoubleFunction fuse(
        IntToDoubleFunction initial,
        DoubleUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsDouble(initial.applyAsDouble(i));
    }

    public static IntFunction<DoubleUnaryOperator> fuse(
        IntToDoubleFunction initial,
        DoubleBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) ->
            (double d) -> next.applyAsDouble(initial.applyAsDouble(i), d);
    }

    /* IntToLongFunction */

    public static <A> IntFunction<A> fuse(
        IntToLongFunction initial,
        LongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static IntToDoubleFunction fuse(
        IntToLongFunction initial,
        LongToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsDouble(initial.applyAsLong(i));
    }

    public static IntUnaryOperator fuse(
        IntToLongFunction initial,
        LongToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsInt(initial.applyAsLong(i));
    }

    public static IntPredicate fuse(
        IntToLongFunction initial,
        LongPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.test(initial.applyAsLong(i));
    }

    public static IntConsumer fuse(
        IntToLongFunction initial,
        LongConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.accept(initial.applyAsLong(i));
    }

    public static IntToLongFunction fuse(
        IntToLongFunction initial,
        LongUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsLong(initial.applyAsLong(i));
    }

    public static IntFunction<LongUnaryOperator> fuse(
        IntToLongFunction initial,
        LongBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) ->
            (long n) -> next.applyAsLong(initial.applyAsLong(i), n);
    }

    /* ToIntFunction */

    public static <A, B> Function<A, B> fuse(
        ToIntFunction<A> initial,
        IntFunction<B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return Z.with(initial).fuse(next);
    }

    public static <A> ToDoubleFunction<A> fuse(
        ToIntFunction<A> initial,
        IntToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsDouble(initial.applyAsInt(a));
    }

    public static <A> ToLongFunction<A> fuse(
        ToIntFunction<A> initial,
        IntToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsLong(initial.applyAsInt(a));
    }

    public static <A> Predicate<A> fuse(
        ToIntFunction<A> initial,
        IntPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.test(initial.applyAsInt(a));
    }

    public static <A> Consumer<A> fuse(
        ToIntFunction<A> initial,
        IntConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.accept(initial.applyAsInt(a));
    }

    public static <A> ToIntFunction<A> fuse(
        ToIntFunction<A> initial,
        IntUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsInt(initial.applyAsInt(a));
    }

    public static <A, B> Function<A, IntUnaryOperator> fuse(
        ToIntFunction<A> initial,
        IntBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (int i) -> next.applyAsInt(initial.applyAsInt(a), i);
    }

    /* ToIntBiFunction */

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        ToIntBiFunction<A, B> initial,
        IntFunction<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToDoubleFunction<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsDouble(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, ToLongFunction<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsLong(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, Predicate<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.test(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, Consumer<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.accept(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, ToIntFunction<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsInt(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, Function<B, IntUnaryOperator>> fuse(
        ToIntBiFunction<A, B> initial,
        IntBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) -> (int i) -> next.applyAsInt(initial.applyAsInt(a, b), i);
    }

    /* LongFunction */

    public static <A, B> LongFunction<B> fuse(
        LongFunction<A> initial,
        Function<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> LongFunction<Function<B, C>> fuse(
        LongFunction<A> initial,
        BiFunction<A, B, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (B b) -> next.apply(initial.apply(n), b);
    }

    public static <A> LongToDoubleFunction fuse(
        LongFunction<A> initial,
        ToDoubleFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsDouble(initial.apply(n));
    }

    public static <A, B> LongFunction<ToDoubleFunction<B>> fuse(
        LongFunction<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (B b) -> next.applyAsDouble(initial.apply(n), b);
    }

    public static <A> LongToIntFunction fuse(
        LongFunction<A> initial,
        ToIntFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsInt(initial.apply(n));
    }

    public static <A, B> LongFunction<ToIntFunction<B>> fuse(
        LongFunction<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (B b) -> next.applyAsInt(initial.apply(n), b);
    }

    public static <A> LongUnaryOperator fuse(
        LongFunction<A> initial,
        ToLongFunction<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsLong(initial.apply(n));
    }

    public static <A, B> LongFunction<ToLongFunction<B>> fuse(
        LongFunction<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (B b) -> next.applyAsLong(initial.apply(n), b);
    }

    public static <A> LongPredicate fuse(
        LongFunction<A> initial,
        Predicate<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.test(initial.apply(n));
    }

    public static <A, B> LongFunction<Predicate<B>> fuse(
        LongFunction<A> initial,
        BiPredicate<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (B b) -> next.test(initial.apply(n), b);
    }

    public static <A> LongConsumer fuse(
        LongFunction<A> initial,
        Consumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.accept(initial.apply(n));
    }

    public static <A, B> LongFunction<Consumer<B>> fuse(
        LongFunction<A> initial,
        BiConsumer<A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (B b) -> next.accept(initial.apply(n), b);
    }

    public static <A> LongFunction<DoubleConsumer> fuse(
        LongFunction<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (double d) -> next.accept(initial.apply(n), d);
    }

    public static <A> LongFunction<IntConsumer> fuse(
        LongFunction<A> initial,
        ObjIntConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (int i) -> next.accept(initial.apply(n), i);
    }

    public static <A> LongFunction<LongConsumer> fuse(
        LongFunction<A> initial,
        ObjLongConsumer<A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (long n2) -> next.accept(initial.apply(n), n2);
    }

    /* LongToDoubleFunction */

    public static <A> LongFunction<A> fuse(
        LongToDoubleFunction initial,
        DoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static LongToIntFunction fuse(
        LongToDoubleFunction initial,
        DoubleToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsInt(initial.applyAsDouble(n));
    }

    public static LongUnaryOperator fuse(
        LongToDoubleFunction initial,
        DoubleToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsLong(initial.applyAsDouble(n));
    }

    public static LongPredicate fuse(
        LongToDoubleFunction initial,
        DoublePredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.test(initial.applyAsDouble(n));
    }

    public static LongConsumer fuse(
        LongToDoubleFunction initial,
        DoubleConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.accept(initial.applyAsDouble(n));
    }

    public static LongToDoubleFunction fuse(
        LongToDoubleFunction initial,
        DoubleUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsDouble(initial.applyAsDouble(n));
    }

    public static LongFunction<DoubleUnaryOperator> fuse(
        LongToDoubleFunction initial,
        DoubleBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) ->
            (double d) -> next.applyAsDouble(initial.applyAsDouble(n), d);
    }

    /* LongToIntFunction */

    public static <A> LongFunction<A> fuse(
        LongToIntFunction initial,
        IntFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static LongToDoubleFunction fuse(
        LongToIntFunction initial,
        IntToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsDouble(initial.applyAsInt(n));
    }

    public static LongUnaryOperator fuse(
        LongToIntFunction initial,
        IntToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsLong(initial.applyAsInt(n));
    }

    public static LongPredicate fuse(
        LongToIntFunction initial,
        IntPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.test(initial.applyAsInt(n));
    }

    public static LongConsumer fuse(
        LongToIntFunction initial,
        IntConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.accept(initial.applyAsInt(n));
    }

    public static LongToIntFunction fuse(
        LongToIntFunction initial,
        IntUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsInt(initial.applyAsInt(n));
    }

    public static LongFunction<IntUnaryOperator> fuse(
        LongToIntFunction initial,
        IntBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (int i) -> next.applyAsInt(initial.applyAsInt(n), i);
    }

    /* ToLongFunction */

    public static <A, B> Function<A, B> fuse(
        ToLongFunction<A> initial,
        LongFunction<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> ToDoubleFunction<A> fuse(
        ToLongFunction<A> initial,
        LongToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsDouble(initial.applyAsLong(a));
    }

    public static <A> ToIntFunction<A> fuse(
        ToLongFunction<A> initial,
        LongToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsInt(initial.applyAsLong(a));
    }

    public static <A> Predicate<A> fuse(
        ToLongFunction<A> initial,
        LongPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.test(initial.applyAsLong(a));
    }

    public static <A> Consumer<A> fuse(
        ToLongFunction<A> initial,
        LongConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.accept(initial.applyAsLong(a));
    }

    public static <A> ToLongFunction<A> fuse(
        ToLongFunction<A> initial,
        LongUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsLong(initial.applyAsLong(a));
    }

    public static <A> Function<A, LongUnaryOperator> fuse(
        ToLongFunction<A> initial,
        LongBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (long n) -> next.applyAsLong(initial.applyAsLong(a), n);
    }

    /* ToLongBiFunction */

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        ToLongBiFunction<A, B> initial,
        LongFunction<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsDouble(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsInt(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, Predicate<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.test(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, Consumer<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.accept(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsLong(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, Function<B, LongUnaryOperator>> fuse(
        ToLongBiFunction<A, B> initial,
        LongBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) -> (long n) -> next.applyAsLong(initial.applyAsLong(a, b), n);
    }

    /* Predicate */

    public static <A, B> Function<A, B> fuse(
        Predicate<A> initial,
        BooleanFunction<B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        Predicate<A> initial,
        BiFunction<Boolean, B, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.apply(initial.test(a), b);
    }

    public static <A> ToDoubleFunction<A> fuse(
        Predicate<A> initial,
        BooleanToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsDouble(initial.test(a));
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(
        Predicate<A> initial,
        ToDoubleBiFunction<Boolean, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsDouble(initial.test(a), b);
    }

    public static <A> ToIntFunction<A> fuse(
        Predicate<A> initial,
        BooleanToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsInt(initial.test(a));
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(
        Predicate<A> initial,
        ToIntBiFunction<Boolean, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsInt(initial.test(a), b);
    }

    public static <A> ToLongFunction<A> fuse(
        Predicate<A> initial,
        BooleanToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsLong(initial.test(a));
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(
        Predicate<A> initial,
        ToLongBiFunction<Boolean, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsLong(initial.test(a), b);
    }

    public static <A> Predicate<A> fuse(
        Predicate<A> initial,
        BooleanPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.test(initial.test(a));
    }

    public static <A, B> Function<A, Predicate<B>> fuse(
        Predicate<A> initial,
        BiPredicate<Boolean, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.test(initial.test(a), b);
    }

    public static <A> Consumer<A> fuse(
        Predicate<A> initial,
        BooleanConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.accept(initial.test(a));
    }

    public static <A, B> Function<A, Consumer<B>> fuse(
        Predicate<A> initial,
        BiConsumer<Boolean, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.accept(initial.test(a), b);
    }

    public static <A> Function<A, DoubleConsumer> fuse(
        Predicate<A> initial,
        ObjDoubleConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (double d) -> next.accept(initial.test(a), d);
    }

    public static <A> Function<A, IntConsumer> fuse(
        Predicate<A> initial,
        ObjIntConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (int i) -> next.accept(initial.test(a), i);
    }

    public static <A> Function<A, LongConsumer> fuse(
        Predicate<A> initial,
        ObjLongConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (long n) -> next.accept(initial.test(a), n);
    }

    public static <A> Function<A, BooleanPredicate> fuse(
        Predicate<A> initial,
        BinaryOperator<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (boolean b) -> next.apply(initial.test(a), b);
    }

    /* <BiPredicate> */

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        BiPredicate<A, B> initial,
        BooleanFunction<C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, Function<C, D>>> fuse(
        BiPredicate<A, B> initial,
        BiFunction<Boolean, C, D> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> (C c) -> next.apply(initial.test(a, b), c);
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsDouble(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, ToDoubleFunction<C>>> fuse(
        BiPredicate<A, B> initial,
        ToDoubleBiFunction<Boolean, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) -> (C c) -> next.applyAsDouble(initial.test(a, b), c);
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsInt(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, ToIntFunction<C>>> fuse(
        BiPredicate<A, B> initial,
        ToIntBiFunction<Boolean, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) -> (C c) -> next.applyAsInt(initial.test(a, b), c);
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.applyAsLong(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, ToLongFunction<C>>> fuse(
        BiPredicate<A, B> initial,
        ToLongBiFunction<Boolean, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) -> (C c) -> next.applyAsLong(initial.test(a, b), c);
    }

    public static <A, B> Function<A, Predicate<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.test(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, Predicate<C>>> fuse(
        BiPredicate<A, B> initial,
        BiPredicate<Boolean, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> (C c) -> next.test(initial.test(a, b), c);
    }

    public static <A, B> Function<A, Consumer<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> next.accept(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, Consumer<C>>> fuse(
        BiPredicate<A, B> initial,
        BiConsumer<Boolean, C> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> (C c) -> next.accept(initial.test(a, b), c);
    }

    public static <A, B> Function<A, Function<B, DoubleConsumer>> fuse(
        BiPredicate<A, B> initial,
        ObjDoubleConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) -> (double d) -> next.accept(initial.test(a, b), d);
    }

    public static <A, B> Function<A, Function<B, IntConsumer>> fuse(
        BiPredicate<A, B> initial,
        ObjIntConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> (int i) -> next.accept(initial.test(a, b), i);
    }

    public static <A, B> Function<A, Function<B, LongConsumer>> fuse(
        BiPredicate<A, B> initial,
        ObjLongConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (B b) -> (long n) -> next.accept(initial.test(a, b), n);
    }

    public static <A, B> Function<A, Function<B, BooleanPredicate>> fuse(
        BiPredicate<A, B> initial,
        BinaryOperator<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) ->
            (B b) -> (boolean bool) -> next.apply(initial.test(a, b), bool);
    }

    /* <BooleanPredicate> */

    public static <A> BooleanFunction<A> fuse(
        BooleanPredicate initial,
        BooleanFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> BooleanFunction<Function<A, B>> fuse(
        BooleanPredicate initial,
        BiFunction<Boolean, A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (A a) -> next.apply(initial.test(b), a);
    }

    public static BooleanToDoubleFunction fuse(
        BooleanPredicate initial,
        BooleanToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsDouble(initial.test(b));
    }

    public static <A> BooleanFunction<ToDoubleFunction<A>> fuse(
        BooleanPredicate initial,
        ToDoubleBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (A a) -> next.applyAsDouble(initial.test(b), a);
    }

    public static BooleanToIntFunction fuse(
        BooleanPredicate initial,
        BooleanToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsInt(initial.test(b));
    }

    public static <A> BooleanFunction<ToIntFunction<A>> fuse(
        BooleanPredicate initial,
        ToIntBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (A a) -> next.applyAsInt(initial.test(b), a);
    }

    public static BooleanToLongFunction fuse(
        BooleanPredicate initial,
        BooleanToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.applyAsLong(initial.test(b));
    }

    public static <A> BooleanFunction<ToLongFunction<A>> fuse(
        BooleanPredicate initial,
        ToLongBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (A a) -> next.applyAsLong(initial.test(b), a);
    }

    public static <A> BooleanFunction<Predicate<A>> fuse(
        BooleanPredicate initial,
        BiPredicate<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (A a) -> next.test(initial.test(b), a);
    }

    public static BooleanPredicate fuse(
        BooleanPredicate initial,
        BooleanPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.test(initial.test(b));
    }

    public static BooleanConsumer fuse(
        BooleanPredicate initial,
        BooleanConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> next.accept(initial.test(b));
    }

    public static <A> BooleanFunction<Consumer<A>> fuse(
        BooleanPredicate initial,
        BiConsumer<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (boolean b) -> (A a) -> next.accept(initial.test(b), a);
    }

    /* <DoublePredicate> */

    public static <A> DoubleFunction<A> fuse(
        DoublePredicate initial,
        BooleanFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> DoubleFunction<Function<A, B>> fuse(
        DoublePredicate initial,
        BiFunction<Boolean, A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (A a) -> next.apply(initial.test(d), a);
    }

    public static DoubleUnaryOperator fuse(
        DoublePredicate initial,
        BooleanToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsDouble(initial.test(d));
    }

    public static <A> DoubleFunction<ToDoubleFunction<A>> fuse(
        DoublePredicate initial,
        ToDoubleBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (A a) -> next.applyAsDouble(initial.test(d), a);
    }

    public static DoubleToIntFunction fuse(
        DoublePredicate initial,
        BooleanToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsInt(initial.test(d));
    }

    public static <A> DoubleFunction<ToIntFunction<A>> fuse(
        DoublePredicate initial,
        ToIntBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (A a) -> next.applyAsInt(initial.test(d), a);
    }

    public static DoubleToLongFunction fuse(
        DoublePredicate initial,
        BooleanToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsLong(initial.test(d));
    }

    public static <A> DoubleFunction<ToLongFunction<A>> fuse(
        DoublePredicate initial,
        ToLongBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (A a) -> next.applyAsLong(initial.test(d), a);
    }

    public static DoublePredicate fuse(
        DoublePredicate initial,
        BooleanPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.test(initial.test(d));
    }

    public static <A> DoubleFunction<Predicate<A>> fuse(
        DoublePredicate initial,
        BiPredicate<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (A a) -> next.test(initial.test(d), a);
    }

    public static DoubleConsumer fuse(
        DoublePredicate initial,
        BooleanConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.accept(initial.test(d));
    }

    public static <A> DoubleFunction<Consumer<A>> fuse(
        DoublePredicate initial,
        BiConsumer<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (A a) -> next.accept(initial.test(d), a);
    }

    public static DoubleFunction<DoubleConsumer> fuse(
        DoublePredicate initial,
        ObjDoubleConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d1) -> (double d2) -> next.accept(initial.test(d1), d2);
    }

    public static DoubleFunction<IntConsumer> fuse(
        DoublePredicate initial,
        ObjIntConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (int i) -> next.accept(initial.test(d), i);
    }

    public static DoubleFunction<LongConsumer> fuse(
        DoublePredicate initial,
        ObjLongConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (long n) -> next.accept(initial.test(d), n);
    }

    public static DoubleFunction<BooleanPredicate> fuse(
        DoublePredicate initial,
        BinaryOperator<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> (boolean b) -> next.apply(initial.test(d), b);
    }

    /* IntPredicate */

    public static <A> IntFunction<A> fuse(
        IntPredicate initial,
        BooleanFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> IntFunction<Function<A, B>> fuse(
        IntPredicate initial,
        BiFunction<Boolean, A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (A a) -> next.apply(initial.test(i), a);
    }

    public static IntToDoubleFunction fuse(
        IntPredicate initial,
        BooleanToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsDouble(initial.test(i));
    }

    public static <A> IntFunction<ToDoubleFunction<A>> fuse(
        IntPredicate initial,
        ToDoubleBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (A a) -> next.applyAsDouble(initial.test(i), a);
    }

    public static IntUnaryOperator fuse(
        IntPredicate initial,
        BooleanToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsInt(initial.test(i));
    }

    public static <A> IntFunction<ToIntFunction<A>> fuse(
        IntPredicate initial,
        ToIntBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (A a) -> next.applyAsInt(initial.test(i), a);
    }

    public static IntToLongFunction fuse(
        IntPredicate initial,
        BooleanToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsLong(initial.test(i));
    }

    public static <A> IntFunction<ToLongFunction<A>> fuse(
        IntPredicate initial,
        ToLongBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (A a) -> next.applyAsLong(initial.test(i), a);
    }

    public static IntPredicate fuse(
        IntPredicate initial,
        BooleanPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.test(initial.test(i));
    }

    public static <A> IntFunction<Predicate<A>> fuse(
        IntPredicate initial,
        BiPredicate<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (A a) -> next.test(initial.test(i), a);
    }

    public static IntConsumer fuse(IntPredicate initial, BooleanConsumer next) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.accept(initial.test(i));
    }

    public static <A> IntFunction<Consumer<A>> fuse(
        IntPredicate initial,
        BiConsumer<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (A a) -> next.accept(initial.test(i), a);
    }

    public static IntFunction<DoubleConsumer> fuse(
        IntPredicate initial,
        ObjDoubleConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (double d) -> next.accept(initial.test(i), d);
    }

    public static IntFunction<IntConsumer> fuse(
        IntPredicate initial,
        ObjIntConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i1) -> (int i2) -> next.accept(initial.test(i1), i2);
    }

    public static IntFunction<LongConsumer> fuse(
        IntPredicate initial,
        ObjLongConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (long n) -> next.accept(initial.test(i), n);
    }

    public static IntFunction<BooleanPredicate> fuse(
        IntPredicate initial,
        BinaryOperator<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (boolean b) -> next.apply(initial.test(i), b);
    }

    /* LongPredicate */

    public static <A> LongFunction<A> fuse(
        LongPredicate initial,
        BooleanFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> LongFunction<Function<A, B>> fuse(
        LongPredicate initial,
        BiFunction<Boolean, A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (A a) -> next.apply(initial.test(n), a);
    }

    public static LongToDoubleFunction fuse(
        LongPredicate initial,
        BooleanToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsDouble(initial.test(n));
    }

    public static <A> LongFunction<ToDoubleFunction<A>> fuse(
        LongPredicate initial,
        ToDoubleBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (A a) -> next.applyAsDouble(initial.test(n), a);
    }

    public static LongToIntFunction fuse(
        LongPredicate initial,
        BooleanToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsInt(initial.test(n));
    }

    public static <A> LongFunction<ToIntFunction<A>> fuse(
        LongPredicate initial,
        ToIntBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (A a) -> next.applyAsInt(initial.test(n), a);
    }

    public static LongUnaryOperator fuse(
        LongPredicate initial,
        BooleanToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsLong(initial.test(n));
    }

    public static <A> LongFunction<ToLongFunction<A>> fuse(
        LongPredicate initial,
        ToLongBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (A a) -> next.applyAsLong(initial.test(n), a);
    }

    public static LongPredicate fuse(
        LongPredicate initial,
        BooleanPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.test(initial.test(n));
    }

    public static <A> LongFunction<Predicate<A>> fuse(
        LongPredicate initial,
        BiPredicate<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (A a) -> next.test(initial.test(n), a);
    }

    public static LongConsumer fuse(
        LongPredicate initial,
        BooleanConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.accept(initial.test(n));
    }

    public static <A> LongFunction<Consumer<A>> fuse(
        LongPredicate initial,
        BiConsumer<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (A a) -> next.accept(initial.test(n), a);
    }

    public static LongFunction<DoubleConsumer> fuse(
        LongPredicate initial,
        ObjDoubleConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (double d) -> next.accept(initial.test(n), d);
    }

    public static LongFunction<IntConsumer> fuse(
        LongPredicate initial,
        ObjIntConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (int i) -> next.accept(initial.test(n), i);
    }

    public static LongFunction<LongConsumer> fuse(
        LongPredicate initial,
        ObjLongConsumer<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n1) -> (long n2) -> next.accept(initial.test(n1), n2);
    }

    public static LongFunction<BooleanPredicate> fuse(
        LongPredicate initial,
        BinaryOperator<Boolean> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (boolean b) -> next.apply(initial.test(n), b);
    }

    /* Supplier */

    public static <A, B> Supplier<B> fuse(
        Supplier<A> initial,
        Function<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Function<B, C> fuse(
        Supplier<A> initial,
        BiFunction<A, B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> DoubleSupplier fuse(
        Supplier<A> initial,
        ToDoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> ToDoubleFunction<B> fuse(
        Supplier<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> IntSupplier fuse(
        Supplier<A> initial,
        ToIntFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> ToIntFunction<B> fuse(
        Supplier<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> LongSupplier fuse(
        Supplier<A> initial,
        ToLongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> ToLongFunction<B> fuse(
        Supplier<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> BooleanSupplier fuse(
        Supplier<A> initial,
        Predicate<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Predicate<B> fuse(
        Supplier<A> initial,
        BiPredicate<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Operator fuse(Supplier<A> initial, Consumer<A> next) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Consumer<B> fuse(
        Supplier<A> initial,
        BiConsumer<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> DoubleConsumer fuse(
        Supplier<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> IntConsumer fuse(
        Supplier<A> initial,
        ObjIntConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> LongConsumer fuse(
        Supplier<A> initial,
        ObjLongConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* BooleanSupplier */

    public static <A> Supplier<A> fuse(
        BooleanSupplier initial,
        BooleanFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Function<A, B> fuse(
        BooleanSupplier initial,
        BiFunction<Boolean, A, B> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.apply(initial.getAsBoolean(), a);
    }

    public static DoubleSupplier fuse(
        BooleanSupplier initial,
        BooleanToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return () -> next.applyAsDouble(initial.getAsBoolean());
    }

    public static <A> ToDoubleFunction<A> fuse(
        BooleanSupplier initial,
        ToDoubleBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsDouble(initial.getAsBoolean(), a);
    }

    public static IntSupplier fuse(
        BooleanSupplier initial,
        BooleanToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return () -> next.applyAsInt(initial.getAsBoolean());
    }

    public static <A> ToIntFunction<A> fuse(
        BooleanSupplier initial,
        ToIntBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsInt(initial.getAsBoolean(), a);
    }

    public static LongSupplier fuse(
        BooleanSupplier initial,
        BooleanToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return () -> next.applyAsLong(initial.getAsBoolean());
    }

    public static <A> ToLongFunction<A> fuse(
        BooleanSupplier initial,
        ToLongBiFunction<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.applyAsLong(initial.getAsBoolean(), a);
    }

    public static BooleanSupplier fuse(
        BooleanSupplier initial,
        BooleanPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return () -> next.test(initial.getAsBoolean());
    }

    public static <A> Predicate<A> fuse(
        BooleanSupplier initial,
        BiPredicate<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.test(initial.getAsBoolean(), a);
    }

    public static Operator fuse(BooleanSupplier initial, BooleanConsumer next) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return () -> next.accept(initial.getAsBoolean());
    }

    public static <A> Consumer<A> fuse(
        BooleanSupplier initial,
        BiConsumer<Boolean, A> next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> next.accept(initial.getAsBoolean(), a);
    }

    /* DoubleSupplier */

    public static <A> Supplier<A> fuse(
        DoubleSupplier initial,
        DoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static IntSupplier fuse(
        DoubleSupplier initial,
        DoubleToIntFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static LongSupplier fuse(
        DoubleSupplier initial,
        DoubleToLongFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanSupplier fuse(
        DoubleSupplier initial,
        DoublePredicate next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Operator fuse(DoubleSupplier initial, DoubleConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        DoubleSupplier initial,
        DoubleUnaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        DoubleSupplier initial,
        DoubleBinaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* IntSupplier */

    public static <A> Supplier<A> fuse(
        IntSupplier initial,
        IntFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        IntSupplier initial,
        IntToDoubleFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static LongSupplier fuse(
        IntSupplier initial,
        IntToLongFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanSupplier fuse(IntSupplier initial, IntPredicate next) {
        return Z.with(initial).fuse(next);
    }

    public static Operator fuse(IntSupplier initial, IntConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static IntSupplier fuse(IntSupplier initial, IntUnaryOperator next) {
        return Z.with(initial).fuse(next);
    }

    public static IntUnaryOperator fuse(
        IntSupplier initial,
        IntBinaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* LongSupplier */

    public static <A> Supplier<A> fuse(
        LongSupplier initial,
        LongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        LongSupplier initial,
        LongToDoubleFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static IntSupplier fuse(
        LongSupplier initial,
        LongToIntFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static BooleanSupplier fuse(
        LongSupplier initial,
        LongPredicate next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Operator fuse(LongSupplier initial, LongConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static LongSupplier fuse(
        LongSupplier initial,
        LongUnaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static LongUnaryOperator fuse(
        LongSupplier initial,
        LongBinaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* UnaryOperator [SKIPPED] Overlap with Function<A, A> (Erasure applies) */

    /* BinaryOperator [SKIPPED] Overlap with BiFunction<A, A, A> (Erasure applies) */

    /* DoubleUnaryOperator */

    public static <A> DoubleFunction<A> fuse(
        DoubleUnaryOperator initial,
        DoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleToIntFunction fuse(
        DoubleUnaryOperator initial,
        DoubleToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsInt(initial.applyAsDouble(d));
    }

    public static DoubleToLongFunction fuse(
        DoubleUnaryOperator initial,
        DoubleToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsLong(initial.applyAsDouble(d));
    }

    public static DoublePredicate fuse(
        DoubleUnaryOperator initial,
        DoublePredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.test(initial.applyAsDouble(d));
    }

    public static DoubleConsumer fuse(
        DoubleUnaryOperator initial,
        DoubleConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.accept(initial.applyAsDouble(d));
    }

    public static DoubleUnaryOperator fuse(
        DoubleUnaryOperator initial,
        DoubleUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) -> next.applyAsDouble(initial.applyAsDouble(d));
    }

    public static DoubleFunction<DoubleUnaryOperator> fuse(
        DoubleUnaryOperator initial,
        DoubleBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (double d2) -> next.applyAsDouble(initial.applyAsDouble(d), d2);
    }

    /* DoubleBinaryOperator */

    public static <A> DoubleFunction<DoubleFunction<A>> fuse(
        DoubleBinaryOperator initial,
        DoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static DoubleFunction<DoubleToIntFunction> fuse(
        DoubleBinaryOperator initial,
        DoubleToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (double d2) -> next.applyAsInt(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleToLongFunction> fuse(
        DoubleBinaryOperator initial,
        DoubleToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (double d2) -> next.applyAsLong(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoublePredicate> fuse(
        DoubleBinaryOperator initial,
        DoublePredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (double d2) -> next.test(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleConsumer> fuse(
        DoubleBinaryOperator initial,
        DoubleConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (double d2) -> next.accept(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleUnaryOperator> fuse(
        DoubleBinaryOperator initial,
        DoubleUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (double d2) -> next.applyAsDouble(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleFunction<DoubleUnaryOperator>> fuse(
        DoubleBinaryOperator initial,
        DoubleBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (double d) ->
            (double d2) ->
                (double d3) ->
                    next.applyAsDouble(initial.applyAsDouble(d, d2), d3);
    }

    /* IntUnaryOperator */

    public static <A> IntFunction<A> fuse(
        IntUnaryOperator initial,
        IntFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static IntToDoubleFunction fuse(
        IntUnaryOperator initial,
        IntToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsDouble(initial.applyAsInt(i));
    }

    public static IntToLongFunction fuse(
        IntUnaryOperator initial,
        IntToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsLong(initial.applyAsInt(i));
    }

    public static IntPredicate fuse(
        IntUnaryOperator initial,
        IntPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.test(initial.applyAsInt(i));
    }

    public static IntConsumer fuse(IntUnaryOperator initial, IntConsumer next) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.accept(initial.applyAsInt(i));
    }

    public static IntUnaryOperator fuse(
        IntUnaryOperator initial,
        IntUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> next.applyAsInt(initial.applyAsInt(i));
    }

    public static IntFunction<IntUnaryOperator> fuse(
        IntUnaryOperator initial,
        IntBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) ->
            (int i2) -> next.applyAsInt(initial.applyAsInt(i), i2);
    }

    /* IntBinaryOperator */

    public static <A> IntFunction<IntFunction<A>> fuse(
        IntBinaryOperator initial,
        IntFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static IntFunction<IntToDoubleFunction> fuse(
        IntBinaryOperator initial,
        IntToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) ->
            (int i2) -> next.applyAsDouble(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntToLongFunction> fuse(
        IntBinaryOperator initial,
        IntToLongFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) ->
            (int i2) -> next.applyAsLong(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntPredicate> fuse(
        IntBinaryOperator initial,
        IntPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (int i2) -> next.test(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntConsumer> fuse(
        IntBinaryOperator initial,
        IntConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) -> (int i2) -> next.accept(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntUnaryOperator> fuse(
        IntBinaryOperator initial,
        IntUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) ->
            (int i2) -> next.applyAsInt(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntFunction<IntUnaryOperator>> fuse(
        IntBinaryOperator initial,
        IntBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (int i) ->
            (int i2) ->
                (int i3) -> next.applyAsInt(initial.applyAsInt(i, i2), i3);
    }

    /* LongUnaryOperator */

    public static <A> LongFunction<A> fuse(
        LongUnaryOperator initial,
        LongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static LongToDoubleFunction fuse(
        LongUnaryOperator initial,
        LongToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsDouble(initial.applyAsLong(n));
    }

    public static LongToIntFunction fuse(
        LongUnaryOperator initial,
        LongToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsInt(initial.applyAsLong(n));
    }

    public static LongPredicate fuse(
        LongUnaryOperator initial,
        LongPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.test(initial.applyAsLong(n));
    }

    public static LongConsumer fuse(
        LongUnaryOperator initial,
        LongConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.accept(initial.applyAsLong(n));
    }

    public static LongUnaryOperator fuse(
        LongUnaryOperator initial,
        LongUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> next.applyAsLong(initial.applyAsLong(n));
    }

    public static LongFunction<LongUnaryOperator> fuse(
        LongUnaryOperator initial,
        LongBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) ->
            (long n2) -> next.applyAsLong(initial.applyAsLong(n), n2);
    }

    /* LongBinaryOperator */

    public static <A> LongFunction<LongFunction<A>> fuse(
        LongBinaryOperator initial,
        LongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static LongFunction<LongToDoubleFunction> fuse(
        LongBinaryOperator initial,
        LongToDoubleFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) ->
            (long n2) -> next.applyAsDouble(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongToIntFunction> fuse(
        LongBinaryOperator initial,
        LongToIntFunction next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) ->
            (long n2) -> next.applyAsInt(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongPredicate> fuse(
        LongBinaryOperator initial,
        LongPredicate next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (long n2) -> next.test(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongConsumer> fuse(
        LongBinaryOperator initial,
        LongConsumer next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) -> (long n2) -> next.accept(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongUnaryOperator> fuse(
        LongBinaryOperator initial,
        LongUnaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) ->
            (long n2) -> next.applyAsLong(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongFunction<LongUnaryOperator>> fuse(
        LongBinaryOperator initial,
        LongBinaryOperator next
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (long n) ->
            (long n2) ->
                (long n3) -> next.applyAsLong(initial.applyAsLong(n, n2), n3);
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

    @Experimental
    public static <A> Combine.WithFunction<A, A> withClass(Class<A> clazz) {
        return Combine.WithFunction.of(Z.id(clazz));
    }

    @Experimental
    public static <A> Combine.WithFunction<A, A> with(Class<A> clazz) {
        return withClass(clazz);
    }

    @Experimental
    public static <A> Combine.WithSupplier<A> withObject(A initial) {
        return Combine.WithSupplier.of(() -> initial);
    }

    @Experimental
    public static <A> Combine.WithSupplier<A> with(A initial) {
        return withObject(initial);
    }

    @Experimental
    public static Combine.WithBooleanSupplier with(boolean initial) {
        return Combine.WithBooleanSupplier.of(() -> initial);
    }

    @Experimental
    public static Combine.WithDoubleSupplier with(double initial) {
        return Combine.WithDoubleSupplier.of(() -> initial);
    }

    @Experimental
    public static Combine.WithIntSupplier with(int initial) {
        return Combine.WithIntSupplier.of(() -> initial);
    }

    @Experimental
    public static Combine.WithLongSupplier with(long initial) {
        return Combine.WithLongSupplier.of(() -> initial);
    }

    @Experimental
    public static <A, B> Combine.WithFunction<A, B> withFunction(
        Function<A, B> initial
    ) {
        return Combine.WithFunction.of(initial);
    }

    @Experimental
    public static <A, B> Combine.WithFunction<A, B> with(
        Function<A, B> initial
    ) {
        return withFunction(initial);
    }

    @Experimental
    public static <A, B, C> Combine.WithBiFunction<A, B, C> withBiFunction(
        BiFunction<A, B, C> initial
    ) {
        return Combine.WithBiFunction.of(initial);
    }

    @Experimental
    public static <A, B, C> Combine.WithBiFunction<A, B, C> with(
        BiFunction<A, B, C> initial
    ) {
        return withBiFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithBooleanFunction<A> withBooleanFunction(
        BooleanFunction<A> initial
    ) {
        return Combine.WithBooleanFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithBooleanFunction<A> with(
        BooleanFunction<A> initial
    ) {
        return withBooleanFunction(initial);
    }

    @Experimental
    public static Combine.WithBooleanToDoubleFunction withBooleanToDoubleFunction(
        BooleanToDoubleFunction initial
    ) {
        return Combine.WithBooleanToDoubleFunction.of(initial);
    }

    @Experimental
    public static Combine.WithBooleanToDoubleFunction with(
        BooleanToDoubleFunction initial
    ) {
        return withBooleanToDoubleFunction(initial);
    }

    @Experimental
    public static Combine.WithBooleanToLongFunction withBooleanToLongFunction(
        BooleanToLongFunction initial
    ) {
        return Combine.WithBooleanToLongFunction.of(initial);
    }

    @Experimental
    public static Combine.WithBooleanToLongFunction with(
        BooleanToLongFunction initial
    ) {
        return withBooleanToLongFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithBooleanToIntFunction withBooleanToIntFunction(
        BooleanToIntFunction initial
    ) {
        return Combine.WithBooleanToIntFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithBooleanToIntFunction with(
        BooleanToIntFunction initial
    ) {
        return withBooleanToIntFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithDoubleFunction<A> withDoubleFunction(
        DoubleFunction<A> initial
    ) {
        return Combine.WithDoubleFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithDoubleFunction<A> with(
        DoubleFunction<A> initial
    ) {
        return withDoubleFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithDoubleToIntFunction withDoubleToIntFunction(
        DoubleToIntFunction initial
    ) {
        return Combine.WithDoubleToIntFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithDoubleToIntFunction with(
        DoubleToIntFunction initial
    ) {
        return withDoubleToIntFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithDoubleToLongFunction withDoubleToLongFunction(
        DoubleToLongFunction initial
    ) {
        return Combine.WithDoubleToLongFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithDoubleToLongFunction with(
        DoubleToLongFunction initial
    ) {
        return withDoubleToLongFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithToDoubleFunction<A> withToDoubleFunction(
        ToDoubleFunction<A> initial
    ) {
        return Combine.WithToDoubleFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithToDoubleFunction<A> with(
        ToDoubleFunction<A> initial
    ) {
        return withToDoubleFunction(initial);
    }

    @Experimental
    public static <A, B> Combine.WithToDoubleBiFunction<A, B> withToDoubleBiFunction(
        ToDoubleBiFunction<A, B> initial
    ) {
        return Combine.WithToDoubleBiFunction.of(initial);
    }

    @Experimental
    public static <A, B> Combine.WithToDoubleBiFunction<A, B> with(
        ToDoubleBiFunction<A, B> initial
    ) {
        return withToDoubleBiFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithIntFunction<A> withIntFunction(
        IntFunction<A> initial
    ) {
        return Combine.WithIntFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithIntFunction<A> with(IntFunction<A> initial) {
        return withIntFunction(initial);
    }

    @Experimental
    public static Combine.WithIntToDoubleFunction withIntToDoubleFunction(
        IntToDoubleFunction initial
    ) {
        return Combine.WithIntToDoubleFunction.of(initial);
    }

    @Experimental
    public static Combine.WithIntToDoubleFunction with(
        IntToDoubleFunction initial
    ) {
        return withIntToDoubleFunction(initial);
    }

    @Experimental
    public static Combine.WithIntToLongFunction withIntToLongFunction(
        IntToLongFunction initial
    ) {
        return Combine.WithIntToLongFunction.of(initial);
    }

    @Experimental
    public static Combine.WithIntToLongFunction with(
        IntToLongFunction initial
    ) {
        return withIntToLongFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithToIntFunction<A> withToIntFunction(
        ToIntFunction<A> initial
    ) {
        return Combine.WithToIntFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithToIntFunction<A> with(
        ToIntFunction<A> initial
    ) {
        return withToIntFunction(initial);
    }

    @Experimental
    public static <A, B> Combine.WithToIntBiFunction<A, B> withToIntBiFunction(
        ToIntBiFunction<A, B> initial
    ) {
        return Combine.WithToIntBiFunction.of(initial);
    }

    @Experimental
    public static <A, B> Combine.WithToIntBiFunction<A, B> with(
        ToIntBiFunction<A, B> initial
    ) {
        return withToIntBiFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithLongFunction<A> withLongFunction(
        LongFunction<A> initial
    ) {
        return Combine.WithLongFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithLongFunction<A> with(
        LongFunction<A> initial
    ) {
        return withLongFunction(initial);
    }

    @Experimental
    public static Combine.WithLongToDoubleFunction withLongToDoubleFunction(
        LongToDoubleFunction initial
    ) {
        return Combine.WithLongToDoubleFunction.of(initial);
    }

    @Experimental
    public static Combine.WithLongToDoubleFunction with(
        LongToDoubleFunction initial
    ) {
        return withLongToDoubleFunction(initial);
    }

    @Experimental
    public static Combine.WithLongToIntFunction withLongToIntFunction(
        LongToIntFunction initial
    ) {
        return Combine.WithLongToIntFunction.of(initial);
    }

    @Experimental
    public static Combine.WithLongToIntFunction with(
        LongToIntFunction initial
    ) {
        return withLongToIntFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithToLongFunction<A> withToLongFunction(
        ToLongFunction<A> initial
    ) {
        return Combine.WithToLongFunction.of(initial);
    }

    @Experimental
    public static <A> Combine.WithToLongFunction<A> with(
        ToLongFunction<A> initial
    ) {
        return withToLongFunction(initial);
    }

    @Experimental
    public static <A, B> Combine.WithToLongBiFunction<A, B> withToLongBiFunction(
        ToLongBiFunction<A, B> initial
    ) {
        return Combine.WithToLongBiFunction.of(initial);
    }

    @Experimental
    public static <A, B> Combine.WithToLongBiFunction<A, B> with(
        ToLongBiFunction<A, B> initial
    ) {
        return withToLongBiFunction(initial);
    }

    @Experimental
    public static <A> Combine.WithPredicate<A> withPredicate(
        Predicate<A> initial
    ) {
        return Combine.WithPredicate.of(initial);
    }

    @Experimental
    public static <A> Combine.WithPredicate<A> with(Predicate<A> initial) {
        return withPredicate(initial);
    }

    @Experimental
    public static <A, B> Combine.WithBiPredicate<A, B> withBiPredicate(
        BiPredicate<A, B> initial
    ) {
        return Combine.WithBiPredicate.of(initial);
    }

    @Experimental
    public static <A, B> Combine.WithBiPredicate<A, B> with(
        BiPredicate<A, B> initial
    ) {
        return withBiPredicate(initial);
    }

    @Experimental
    public static Combine.WithBooleanPredicate withBooleanPredicate(
        BooleanPredicate initial
    ) {
        return Combine.WithBooleanPredicate.of(initial);
    }

    @Experimental
    public static Combine.WithBooleanPredicate with(BooleanPredicate initial) {
        return withBooleanPredicate(initial);
    }

    @Experimental
    public static Combine.WithDoublePredicate withDoublePredicate(
        DoublePredicate initial
    ) {
        return Combine.WithDoublePredicate.of(initial);
    }

    @Experimental
    public static Combine.WithDoublePredicate with(DoublePredicate initial) {
        return withDoublePredicate(initial);
    }

    @Experimental
    public static Combine.WithIntPredicate withIntPredicate(
        IntPredicate initial
    ) {
        return Combine.WithIntPredicate.of(initial);
    }

    @Experimental
    public static Combine.WithIntPredicate with(IntPredicate initial) {
        return withIntPredicate(initial);
    }

    @Experimental
    public static Combine.WithLongPredicate withLongPredicate(
        LongPredicate initial
    ) {
        return Combine.WithLongPredicate.of(initial);
    }

    @Experimental
    public static Combine.WithLongPredicate with(LongPredicate initial) {
        return withLongPredicate(initial);
    }

    @Evil
    @Experimental
    public static <A> Combine.WithConsumer<A> withConsumer(
        Consumer<A> initial
    ) {
        return Combine.WithConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static <A> Combine.WithConsumer<A> with(Consumer<A> initial) {
        return withConsumer(initial);
    }

    @Evil
    @Experimental
    public static <A, B> Combine.WithBiConsumer<A, B> withBiConsumer(
        BiConsumer<A, B> initial
    ) {
        return Combine.WithBiConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static <A, B> Combine.WithBiConsumer<A, B> with(
        BiConsumer<A, B> initial
    ) {
        return withBiConsumer(initial);
    }

    @Evil
    @Experimental
    public static Combine.WithBooleanConsumer withBooleanConsumer(
        BooleanConsumer initial
    ) {
        return Combine.WithBooleanConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static Combine.WithBooleanConsumer with(BooleanConsumer initial) {
        return withBooleanConsumer(initial);
    }

    @Evil
    @Experimental
    public static Combine.WithDoubleConsumer withDoubleConsumer(
        DoubleConsumer initial
    ) {
        return Combine.WithDoubleConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static Combine.WithDoubleConsumer with(DoubleConsumer initial) {
        return withDoubleConsumer(initial);
    }

    @Evil
    @Experimental
    public static <A> Combine.WithObjDoubleConsumer<A> withObjDoubleConsumer(
        ObjDoubleConsumer<A> initial
    ) {
        return Combine.WithObjDoubleConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static <A> Combine.WithObjDoubleConsumer<A> with(
        ObjDoubleConsumer<A> initial
    ) {
        return withObjDoubleConsumer(initial);
    }

    @Evil
    @Experimental
    public static Combine.WithIntConsumer withIntConsumer(IntConsumer initial) {
        return Combine.WithIntConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static Combine.WithIntConsumer with(IntConsumer initial) {
        return withIntConsumer(initial);
    }

    @Evil
    @Experimental
    public static <A> Combine.WithObjIntConsumer<A> withObjIntConsumer(
        ObjIntConsumer<A> initial
    ) {
        return Combine.WithObjIntConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static <A> Combine.WithObjIntConsumer<A> with(
        ObjIntConsumer<A> initial
    ) {
        return withObjIntConsumer(initial);
    }

    @Evil
    @Experimental
    public static Combine.WithLongConsumer withLongConsumer(
        LongConsumer initial
    ) {
        return Combine.WithLongConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static Combine.WithLongConsumer with(LongConsumer initial) {
        return withLongConsumer(initial);
    }

    @Evil
    @Experimental
    public static <A> Combine.WithObjLongConsumer<A> withObjLongConsumer(
        ObjLongConsumer<A> initial
    ) {
        return Combine.WithObjLongConsumer.of(initial);
    }

    @Evil
    @Experimental
    public static <A> Combine.WithObjLongConsumer<A> with(
        ObjLongConsumer<A> initial
    ) {
        return withObjLongConsumer(initial);
    }

    @Experimental
    public static <A> Combine.WithSupplier<A> withSupplier(
        Supplier<A> initial
    ) {
        return Combine.WithSupplier.of(initial);
    }

    @Experimental
    public static <A> Combine.WithSupplier<A> with(Supplier<A> initial) {
        return withSupplier(initial);
    }

    @Experimental
    public static Combine.WithBooleanSupplier withBooleanSupplier(
        BooleanSupplier initial
    ) {
        return Combine.WithBooleanSupplier.of(initial);
    }

    @Experimental
    public static Combine.WithBooleanSupplier with(BooleanSupplier initial) {
        return withBooleanSupplier(initial);
    }

    @Experimental
    public static Combine.WithDoubleSupplier withDoubleSupplier(
        DoubleSupplier initial
    ) {
        return Combine.WithDoubleSupplier.of(initial);
    }

    @Experimental
    public static Combine.WithDoubleSupplier with(DoubleSupplier initial) {
        return withDoubleSupplier(initial);
    }

    @Experimental
    public static Combine.WithIntSupplier withIntSupplier(IntSupplier initial) {
        return Combine.WithIntSupplier.of(initial);
    }

    @Experimental
    public static Combine.WithIntSupplier with(IntSupplier initial) {
        return withIntSupplier(initial);
    }

    @Experimental
    public static Combine.WithLongSupplier withLongSupplier(
        LongSupplier initial
    ) {
        return Combine.WithLongSupplier.of(initial);
    }

    @Experimental
    public static Combine.WithLongSupplier with(LongSupplier initial) {
        return withLongSupplier(initial);
    }

    @Experimental
    public static <A> Combine.WithUnaryOperator<A> withUnaryOperator(
        UnaryOperator<A> initial
    ) {
        return Combine.WithUnaryOperator.of(initial);
    }

    @Experimental
    public static <A> Combine.WithUnaryOperator<A> with(
        UnaryOperator<A> initial
    ) {
        return withUnaryOperator(initial);
    }

    @Experimental
    public static <A> Combine.WithBinaryOperator<A> withBinaryOperator(
        BinaryOperator<A> initial
    ) {
        return Combine.WithBinaryOperator.of(initial);
    }

    @Experimental
    public static <A> Combine.WithBinaryOperator<A> with(
        BinaryOperator<A> initial
    ) {
        return withBinaryOperator(initial);
    }

    @Experimental
    public static Combine.WithDoubleUnaryOperator withDoubleUnaryOperator(
        DoubleUnaryOperator initial
    ) {
        return Combine.WithDoubleUnaryOperator.of(initial);
    }

    @Experimental
    public static Combine.WithDoubleUnaryOperator with(
        DoubleUnaryOperator initial
    ) {
        return withDoubleUnaryOperator(initial);
    }

    @Experimental
    public static Combine.WithDoubleBinaryOperator withDoubleBinaryOperator(
        DoubleBinaryOperator initial
    ) {
        return Combine.WithDoubleBinaryOperator.of(initial);
    }

    @Experimental
    public static Combine.WithDoubleBinaryOperator with(
        DoubleBinaryOperator initial
    ) {
        return withDoubleBinaryOperator(initial);
    }

    @Experimental
    public static Combine.WithIntUnaryOperator withIntUnaryOperator(
        IntUnaryOperator initial
    ) {
        return Combine.WithIntUnaryOperator.of(initial);
    }

    @Experimental
    public static Combine.WithIntUnaryOperator with(IntUnaryOperator initial) {
        return withIntUnaryOperator(initial);
    }

    @Experimental
    public static Combine.WithIntBinaryOperator withIntBinaryOperator(
        IntBinaryOperator initial
    ) {
        return Combine.WithIntBinaryOperator.of(initial);
    }

    @Experimental
    public static Combine.WithIntBinaryOperator with(
        IntBinaryOperator initial
    ) {
        return withIntBinaryOperator(initial);
    }

    @Experimental
    public static Combine.WithLongUnaryOperator withLongUnaryOperator(
        LongUnaryOperator initial
    ) {
        return Combine.WithLongUnaryOperator.of(initial);
    }

    @Experimental
    public static Combine.WithLongUnaryOperator with(
        LongUnaryOperator initial
    ) {
        return withLongUnaryOperator(initial);
    }

    @Experimental
    public static Combine.WithLongBinaryOperator withLongBinaryOperator(
        LongBinaryOperator initial
    ) {
        return Combine.WithLongBinaryOperator.of(initial);
    }

    @Experimental
    public static Combine.WithLongBinaryOperator with(
        LongBinaryOperator initial
    ) {
        return withLongBinaryOperator(initial);
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

    // autofmt:off

    public static <A, B, C>
        Function<A,
         Function<B, C>>
        split2(BiFunction<A, B, C> initial)
    {
        return Z.with(initial).resolve();
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
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
        return Z.with(initial).resolve();
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> split(
        ToDoubleBiFunction<A, B> initial
    ) {
        return splitToDoubleBiFunction(initial);
    }

    public static <A, B> Function<A, ToIntFunction<B>> splitToIntBiFunction(
        ToIntBiFunction<A, B> initial
    ) {
        return Z.with(initial).resolve();
    }

    public static <A, B> Function<A, ToIntFunction<B>> split(
        ToIntBiFunction<A, B> initial
    ) {
        return splitToIntBiFunction(initial);
    }

    public static <A, B> Function<A, ToLongFunction<B>> splitToLongBiFunction(
        ToLongBiFunction<A, B> initial
    ) {
        return Z.with(initial).resolve();
    }

    public static <A, B> Function<A, ToLongFunction<B>> split(
        ToLongBiFunction<A, B> initial
    ) {
        return splitToLongBiFunction(initial);
    }

    public static <A, B> Function<A, Predicate<B>> splitBiPredicate(
        BiPredicate<A, B> initial
    ) {
        return Z.with(initial).resolve();
    }

    public static <A, B> Function<A, Predicate<B>> split(
        BiPredicate<A, B> initial
    ) {
        return splitBiPredicate(initial);
    }

    public static <A> Function<A, DoubleConsumer> splitObjDoubleConsumer(
        ObjDoubleConsumer<A> initial
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (double d) -> initial.accept(a, d);
    }

    public static <A> Function<A, DoubleConsumer> split(
        ObjDoubleConsumer<A> initial
    ) {
        return splitObjDoubleConsumer(initial);
    }

    public static <A> Function<A, IntConsumer> splitObjIntConsumer(
        ObjIntConsumer<A> initial
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (int i) -> initial.accept(a, i);
    }

    public static <A> Function<A, IntConsumer> split(
        ObjIntConsumer<A> initial
    ) {
        return splitObjIntConsumer(initial);
    }

    public static <A> Function<A, LongConsumer> splitObjLongConsumer(
        ObjLongConsumer<A> initial
    ) {
        // TODO: Defer to Combine.WithDoubleTo[ETC]
        // return Z.with(initial).fuse(next);
        return (A a) -> (long n) -> initial.accept(a, n);
    }

    public static <A> Function<A, LongConsumer> split(
        ObjLongConsumer<A> initial
    ) {
        return splitObjLongConsumer(initial);
    }

    public static <A, B> Function<A, Consumer<B>> splitBiConsumer(
        BiConsumer<A, B> initial
    ) {
        return Z.with(initial).resolve();
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

    /* Multifunctions */

    // autofmt:off

    @Evil
    public static <A, B, C>
        BiFunction<A, B, C>
        assimilate2(
            Function<A,
             Function<B, C>> curried)
    {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).fusing(next).resolveWide();
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

    /* Consumer */

    @Evil
    public static <A, B, C> Function<A, Function<B, C>> absorb(
        Consumer<A> initial,
        Function<B, C> next
    ) {
        return Z.with(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, B> absorb(
        Consumer<A> initial,
        Supplier<B> next
    ) {
        return Z.with(initial).absorb(next);
    }

    @Evil
    public static <A> Predicate<A> absorb(
        Consumer<A> initial,
        BooleanSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) -> {
            initial.accept(a);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static <A> ToDoubleFunction<A> absorb(
        Consumer<A> initial,
        DoubleSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) -> {
            initial.accept(a);
            return next.getAsDouble();
        };
    }

    @Evil
    public static <A> ToIntFunction<A> absorb(
        Consumer<A> initial,
        IntSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) -> {
            initial.accept(a);
            return next.getAsInt();
        };
    }

    @Evil
    public static <A> ToLongFunction<A> absorb(
        Consumer<A> initial,
        LongSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) -> {
            initial.accept(a);
            return next.getAsLong();
        };
    }

    @Evil
    public static <A> Consumer<A> absorb(Consumer<A> initial, Operator next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) -> {
            initial.accept(a);
            next.run();
        };
    }

    /* BiConsumer */

    @Evil
    public static <A, B, C> Function<A, Function<B, C>> absorb(
        BiConsumer<A, B> initial,
        Supplier<C> next
    ) {
        return Z.with(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, Predicate<B>> absorb(
        BiConsumer<A, B> initial,
        BooleanSupplier next
    ) {
        return Z.with(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, ToDoubleFunction<B>> absorb(
        BiConsumer<A, B> initial,
        DoubleSupplier next
    ) {
        return Z.with(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, ToIntFunction<B>> absorb(
        BiConsumer<A, B> initial,
        IntSupplier next
    ) {
        return Z.with(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, ToLongFunction<B>> absorb(
        BiConsumer<A, B> initial,
        LongSupplier next
    ) {
        return Z.with(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, Consumer<B>> absorb(
        BiConsumer<A, B> initial,
        Operator next
    ) {
        return Z.with(initial).absorb(next);
    }

    /* DoubleConsumer */

    @Evil
    public static <A> DoubleFunction<A> absorb(
        DoubleConsumer initial,
        Supplier<A> next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (double d) -> {
            initial.accept(d);
            return next.get();
        };
    }

    @Evil
    public static DoublePredicate absorb(
        DoubleConsumer initial,
        BooleanSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (double d) -> {
            initial.accept(d);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static DoubleUnaryOperator absorb(
        DoubleConsumer initial,
        DoubleSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (double d) -> {
            initial.accept(d);
            return next.getAsDouble();
        };
    }

    @Evil
    public static DoubleToIntFunction absorb(
        DoubleConsumer initial,
        IntSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (double d) -> {
            initial.accept(d);
            return next.getAsInt();
        };
    }

    @Evil
    public static DoubleToLongFunction absorb(
        DoubleConsumer initial,
        LongSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (double d) -> {
            initial.accept(d);
            return next.getAsLong();
        };
    }

    @Evil
    public static DoubleConsumer absorb(DoubleConsumer initial, Operator next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (double d) -> {
            initial.accept(d);
            next.run();
        };
    }

    /* ObjDoubleConsumer */

    @Evil
    public static <A, B> Function<A, DoubleFunction<B>> absorb(
        ObjDoubleConsumer<A> initial,
        Supplier<B> next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (double d) -> {
                initial.accept(a, d);
                return next.get();
            };
    }

    @Evil
    public static <A> Function<A, DoublePredicate> absorb(
        ObjDoubleConsumer<A> initial,
        BooleanSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (double d) -> {
                initial.accept(a, d);
                return next.getAsBoolean();
            };
    }

    @Evil
    public static <A> Function<A, DoubleUnaryOperator> absorb(
        ObjDoubleConsumer<A> initial,
        DoubleSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (double d) -> {
                initial.accept(a, d);
                return next.getAsDouble();
            };
    }

    @Evil
    public static <A> Function<A, DoubleToIntFunction> absorb(
        ObjDoubleConsumer<A> initial,
        IntSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (double d) -> {
                initial.accept(a, d);
                return next.getAsInt();
            };
    }

    @Evil
    public static <A> Function<A, DoubleToLongFunction> absorb(
        ObjDoubleConsumer<A> initial,
        LongSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (double d) -> {
                initial.accept(a, d);
                return next.getAsLong();
            };
    }

    @Evil
    public static <A> Function<A, DoubleConsumer> absorb(
        ObjDoubleConsumer<A> initial,
        Operator next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (double d) -> {
                initial.accept(a, d);
                next.run();
            };
    }

    /* IntConsumer */

    @Evil
    public static <A> IntFunction<A> absorb(
        IntConsumer initial,
        Supplier<A> next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (int i) -> {
            initial.accept(i);
            return next.get();
        };
    }

    @Evil
    public static IntPredicate absorb(
        IntConsumer initial,
        BooleanSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (int i) -> {
            initial.accept(i);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static IntToDoubleFunction absorb(
        IntConsumer initial,
        DoubleSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (int i) -> {
            initial.accept(i);
            return next.getAsDouble();
        };
    }

    @Evil
    public static IntUnaryOperator absorb(
        IntConsumer initial,
        IntSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (int i) -> {
            initial.accept(i);
            return next.getAsInt();
        };
    }

    @Evil
    public static IntToLongFunction absorb(
        IntConsumer initial,
        LongSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (int i) -> {
            initial.accept(i);
            return next.getAsLong();
        };
    }

    @Evil
    public static IntConsumer absorb(IntConsumer initial, Operator next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (int i) -> {
            initial.accept(i);
            next.run();
        };
    }

    /* ObjIntConsumer */

    @Evil
    public static <A, B> Function<A, IntFunction<B>> absorb(
        ObjIntConsumer<A> initial,
        Supplier<B> next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (int i) -> {
                initial.accept(a, i);
                return next.get();
            };
    }

    @Evil
    public static <A> Function<A, IntPredicate> absorb(
        ObjIntConsumer<A> initial,
        BooleanSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (int i) -> {
                initial.accept(a, i);
                return next.getAsBoolean();
            };
    }

    @Evil
    public static <A> Function<A, IntToDoubleFunction> absorb(
        ObjIntConsumer<A> initial,
        DoubleSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (int i) -> {
                initial.accept(a, i);
                return next.getAsDouble();
            };
    }

    @Evil
    public static <A> Function<A, IntUnaryOperator> absorb(
        ObjIntConsumer<A> initial,
        IntSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (int i) -> {
                initial.accept(a, i);
                return next.getAsInt();
            };
    }

    @Evil
    public static <A> Function<A, IntToLongFunction> absorb(
        ObjIntConsumer<A> initial,
        LongSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (int i) -> {
                initial.accept(a, i);
                return next.getAsLong();
            };
    }

    @Evil
    public static <A> Function<A, IntConsumer> absorb(
        ObjIntConsumer<A> initial,
        Operator next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (int i) -> {
                initial.accept(a, i);
                next.run();
            };
    }

    /* LongConsumer */

    @Evil
    public static <A> LongFunction<A> absorb(
        LongConsumer initial,
        Supplier<A> next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (long n) -> {
            initial.accept(n);
            return next.get();
        };
    }

    @Evil
    public static LongPredicate absorb(
        LongConsumer initial,
        BooleanSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (long n) -> {
            initial.accept(n);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static LongToDoubleFunction absorb(
        LongConsumer initial,
        DoubleSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (long n) -> {
            initial.accept(n);
            return next.getAsDouble();
        };
    }

    @Evil
    public static LongToIntFunction absorb(
        LongConsumer initial,
        IntSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (long n) -> {
            initial.accept(n);
            return next.getAsInt();
        };
    }

    @Evil
    public static LongUnaryOperator absorb(
        LongConsumer initial,
        LongSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (long n) -> {
            initial.accept(n);
            return next.getAsLong();
        };
    }

    @Evil
    public static LongConsumer absorb(LongConsumer initial, Operator next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (long n) -> {
            initial.accept(n);
            next.run();
        };
    }

    /* ObjLongConsumer */

    @Evil
    public static <A, B> Function<A, LongFunction<B>> absorb(
        ObjLongConsumer<A> initial,
        Supplier<B> next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (long n) -> {
                initial.accept(a, n);
                return next.get();
            };
    }

    @Evil
    public static <A> Function<A, LongPredicate> absorb(
        ObjLongConsumer<A> initial,
        BooleanSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (long n) -> {
                initial.accept(a, n);
                return next.getAsBoolean();
            };
    }

    @Evil
    public static <A> Function<A, LongToDoubleFunction> absorb(
        ObjLongConsumer<A> initial,
        DoubleSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (long n) -> {
                initial.accept(a, n);
                return next.getAsDouble();
            };
    }

    @Evil
    public static <A> Function<A, LongToIntFunction> absorb(
        ObjLongConsumer<A> initial,
        IntSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (long n) -> {
                initial.accept(a, n);
                return next.getAsInt();
            };
    }

    @Evil
    public static <A> Function<A, LongUnaryOperator> absorb(
        ObjLongConsumer<A> initial,
        LongSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (long n) -> {
                initial.accept(a, n);
                return next.getAsLong();
            };
    }

    @Evil
    public static <A> Function<A, LongConsumer> absorb(
        ObjLongConsumer<A> initial,
        Operator next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return (A a) ->
            (long n) -> {
                initial.accept(a, n);
                next.run();
            };
    }

    /* Operator */

    @Evil
    public static <A> Supplier<A> absorb(Operator initial, Supplier<A> next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return () -> {
            initial.run();
            return next.get();
        };
    }

    @Evil
    public static BooleanSupplier absorb(
        Operator initial,
        BooleanSupplier next
    ) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return () -> {
            initial.run();
            return next.getAsBoolean();
        };
    }

    @Evil
    public static DoubleSupplier absorb(Operator initial, DoubleSupplier next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return () -> {
            initial.run();
            return next.getAsDouble();
        };
    }

    @Evil
    public static IntSupplier absorb(Operator initial, IntSupplier next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return () -> {
            initial.run();
            return next.getAsInt();
        };
    }

    @Evil
    public static LongSupplier absorb(Operator initial, LongSupplier next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return () -> {
            initial.run();
            return next.getAsLong();
        };
    }

    @Evil
    public static Operator absorb(Operator initial, Operator next) {
        // TODO: Defer to Combine.With[ETC]
        // return Z.with(initial).absorb(next);
        return () -> {
            initial.run();
            next.run();
        };
    }
}
