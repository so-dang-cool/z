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

    /* Object */

    public static <A, B> Combine.WithSupplier<B> fuseObject(
        A initial,
        Function<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B, C> Combine.WithFunction<B, C> fuseObject(
        A initial,
        BiFunction<A, B, C> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithDoubleSupplier fuseObject(
        A initial,
        ToDoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Combine.WithToDoubleFunction<B> fuseObject(
        A initial,
        ToDoubleBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithIntSupplier fuseObject(
        A initial,
        ToIntFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Combine.WithToIntFunction<B> fuseObject(
        A initial,
        ToIntBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithLongSupplier fuseObject(
        A initial,
        ToLongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Combine.WithToLongFunction<B> fuseObject(
        A initial,
        ToLongBiFunction<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithBooleanSupplier fuseObject(
        A initial,
        Predicate<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Combine.WithPredicate<B> fuseObject(
        A initial,
        BiPredicate<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithOperator fuseObject(
        A initial,
        Consumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A, B> Combine.WithConsumer<B> fuseObject(
        A initial,
        BiConsumer<A, B> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithDoubleConsumer fuseObject(
        A initial,
        ObjDoubleConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithIntConsumer fuseObject(
        A initial,
        ObjIntConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithLongConsumer fuseObject(
        A initial,
        ObjLongConsumer<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithSupplier<A> fuseObject(
        A initial,
        UnaryOperator<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static <A> Combine.WithUnaryOperator<A> fuseObject(
        A initial,
        BinaryOperator<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* boolean */

    public static <A> Combine.WithSupplier<A> fuse(
        boolean initial,
        BooleanFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithDoubleSupplier fuse(
        boolean initial,
        BooleanToDoubleFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithIntSupplier fuse(
        boolean initial,
        BooleanToIntFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithLongSupplier fuse(
        boolean initial,
        BooleanToLongFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithBooleanSupplier fuse(
        boolean initial,
        BooleanPredicate next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithOperator fuse(
        boolean initial,
        BooleanConsumer next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* double */

    public static <A> Combine.WithSupplier<A> fuse(
        double initial,
        DoubleFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithIntSupplier fuse(
        double initial,
        DoubleToIntFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithLongSupplier fuse(
        double initial,
        DoubleToLongFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithBooleanSupplier fuse(
        double initial,
        DoublePredicate next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithOperator fuse(
        double initial,
        DoubleConsumer next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithDoubleSupplier fuse(
        double initial,
        DoubleUnaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithDoubleUnaryOperator fuse(
        double initial,
        DoubleBinaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* int */

    public static <A> Combine.WithSupplier<A> fuse(
        int initial,
        IntFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithDoubleSupplier fuse(
        int initial,
        IntToDoubleFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithLongSupplier fuse(
        int initial,
        IntToLongFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithBooleanSupplier fuse(
        int initial,
        IntPredicate next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithOperator fuse(int initial, IntConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithIntSupplier fuse(
        int initial,
        IntUnaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithIntUnaryOperator fuse(
        int initial,
        IntBinaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    /* long */

    public static <A> Combine.WithSupplier<A> fuse(
        long initial,
        LongFunction<A> next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithDoubleSupplier fuse(
        long initial,
        LongToDoubleFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithIntSupplier fuse(
        long initial,
        LongToIntFunction next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithBooleanSupplier fuse(
        long initial,
        LongPredicate next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithOperator fuse(long initial, LongConsumer next) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithLongSupplier fuse(
        long initial,
        LongUnaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

    public static Combine.WithLongUnaryOperator fuse(
        long initial,
        LongBinaryOperator next
    ) {
        return Z.with(initial).fuse(next);
    }

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

    public static <A, B, C> Combine.WithFunction<A, C> fuse(
        Function<A, B> initial,
        Function<B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Combine.WithBiFunction<A, C, D> fuse(
        Function<A, B> initial,
        BiFunction<B, C, D> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithToDoubleFunction<A> fuse(
        Function<A, B> initial,
        ToDoubleFunction<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Combine.WithToDoubleBiFunction<A, C> fuse(
        Function<A, B> initial,
        ToDoubleBiFunction<B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithToIntFunction<A> fuse(
        Function<A, B> initial,
        ToIntFunction<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Combine.WithToIntBiFunction<A, C> fuse(
        Function<A, B> initial,
        ToIntBiFunction<B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithToLongFunction<A> fuse(
        Function<A, B> initial,
        ToLongFunction<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Combine.WithToLongBiFunction<A, C> fuse(
        Function<A, B> initial,
        ToLongBiFunction<B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithPredicate<A> fuse(
        Function<A, B> initial,
        Predicate<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Combine.WithBiPredicate<A, C> fuse(
        Function<A, B> initial,
        BiPredicate<B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithConsumer<A> fuse(
        Function<A, B> initial,
        Consumer<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Combine.WithBiConsumer<A, C> fuse(
        Function<A, B> initial,
        BiConsumer<B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithObjDoubleConsumer<A> fuse(
        Function<A, B> initial,
        ObjDoubleConsumer<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithObjIntConsumer<A> fuse(
        Function<A, B> initial,
        ObjIntConsumer<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithObjLongConsumer<A> fuse(
        Function<A, B> initial,
        ObjLongConsumer<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithFunction<A, B> fuse(
        Function<A, B> initial,
        UnaryOperator<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Combine.WithBiFunction<A, B, B> fuse(
        Function<A, B> initial,
        BinaryOperator<B> next
    ) {
        return Z.fuse(initial).fuse(next);
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

    // TODO: "fusing" and "With[Etc]" cleanup starts here!
    public static <A, B, C, D> Function<A, Function<B, D>> fuse(
        BiFunction<A, B, C> initial,
        Function<C, D> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D, E> Function<A, Function<B, Function<D, E>>> fuse(
        BiFunction<A, B, C> initial,
        BiFunction<C, D, E> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToDoubleFunction<B>> fuse(
        BiFunction<A, B, C> initial,
        ToDoubleFunction<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, ToDoubleFunction<D>>> fuse(
        BiFunction<A, B, C> initial,
        ToDoubleBiFunction<C, D> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToIntFunction<B>> fuse(
        BiFunction<A, B, C> initial,
        ToIntFunction<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, ToIntFunction<D>>> fuse(
        BiFunction<A, B, C> initial,
        ToIntBiFunction<C, D> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToLongFunction<B>> fuse(
        BiFunction<A, B, C> initial,
        ToLongFunction<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, ToLongFunction<D>>> fuse(
        BiFunction<A, B, C> initial,
        ToLongBiFunction<C, D> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Predicate<B>> fuse(
        BiFunction<A, B, C> initial,
        Predicate<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, Predicate<D>>> fuse(
        BiFunction<A, B, C> initial,
        BiPredicate<C, D> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Consumer<B>> fuse(
        BiFunction<A, B, C> initial,
        Consumer<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, Consumer<D>>> fuse(
        BiFunction<A, B, C> initial,
        BiConsumer<C, D> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, DoubleConsumer>> fuse(
        BiFunction<A, B, C> initial,
        ObjDoubleConsumer<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, IntConsumer>> fuse(
        BiFunction<A, B, C> initial,
        ObjIntConsumer<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C, D> Function<A, Function<B, LongConsumer>> fuse(
        BiFunction<A, B, C> initial,
        ObjLongConsumer<C> next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> BooleanFunction<B> fuse(
        BooleanFunction<A> initial,
        Function<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> BooleanFunction<Function<B, C>> fuse(
        BooleanFunction<A> initial,
        BiFunction<A, B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanToDoubleFunction fuse(
        BooleanFunction<A> initial,
        ToDoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> BooleanFunction<ToDoubleFunction<B>> fuse(
        BooleanFunction<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanToIntFunction fuse(
        BooleanFunction<A> initial,
        ToIntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> BooleanFunction<ToIntFunction<B>> fuse(
        BooleanFunction<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanToLongFunction fuse(
        BooleanFunction<A> initial,
        ToLongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> BooleanFunction<ToLongFunction<B>> fuse(
        BooleanFunction<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanPredicate fuse(
        BooleanFunction<A> initial,
        Predicate<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> BooleanFunction<Predicate<B>> fuse(
        BooleanFunction<A> initial,
        BiPredicate<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanConsumer fuse(
        BooleanFunction<A> initial,
        Consumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> BooleanFunction<Consumer<B>> fuse(
        BooleanFunction<A> initial,
        BiConsumer<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanFunction<DoubleConsumer> fuse(
        BooleanFunction<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanFunction<IntConsumer> fuse(
        BooleanFunction<A> initial,
        ObjIntConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanFunction<LongConsumer> fuse(
        BooleanFunction<A> initial,
        ObjLongConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> BooleanFunction<A> fuse(
        BooleanToDoubleFunction initial,
        DoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToIntFunction fuse(
        BooleanToDoubleFunction initial,
        DoubleToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToLongFunction fuse(
        BooleanToDoubleFunction initial,
        DoubleToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanPredicate fuse(
        BooleanToDoubleFunction initial,
        DoublePredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanConsumer fuse(
        BooleanToDoubleFunction initial,
        DoubleConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToDoubleFunction fuse(
        BooleanToDoubleFunction initial,
        DoubleUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanFunction<DoubleUnaryOperator> fuse(
        BooleanToDoubleFunction initial,
        DoubleBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> BooleanFunction<A> fuse(
        BooleanToIntFunction initial,
        IntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToDoubleFunction fuse(
        BooleanToIntFunction initial,
        IntToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToLongFunction fuse(
        BooleanToIntFunction initial,
        IntToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanPredicate fuse(
        BooleanToIntFunction initial,
        IntPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanConsumer fuse(
        BooleanToIntFunction initial,
        IntConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToIntFunction fuse(
        BooleanToIntFunction initial,
        IntUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanFunction<IntUnaryOperator> fuse(
        BooleanToIntFunction initial,
        IntBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> BooleanFunction<A> fuse(
        BooleanToLongFunction initial,
        LongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToDoubleFunction fuse(
        BooleanToLongFunction initial,
        LongToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToIntFunction fuse(
        BooleanToLongFunction initial,
        LongToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanPredicate fuse(
        BooleanToLongFunction initial,
        LongPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanConsumer fuse(
        BooleanToLongFunction initial,
        LongConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToLongFunction fuse(
        BooleanToLongFunction initial,
        LongUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanFunction<LongUnaryOperator> fuse(
        BooleanToLongFunction initial,
        LongBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> DoubleFunction<B> fuse(
        DoubleFunction<A> initial,
        Function<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> DoubleFunction<Function<B, C>> fuse(
        DoubleFunction<A> initial,
        BiFunction<A, B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleUnaryOperator fuse(
        DoubleFunction<A> initial,
        ToDoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> DoubleFunction<ToDoubleFunction<B>> fuse(
        DoubleFunction<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleToIntFunction fuse(
        DoubleFunction<A> initial,
        ToIntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> DoubleFunction<ToIntFunction<B>> fuse(
        DoubleFunction<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleToLongFunction fuse(
        DoubleFunction<A> initial,
        ToLongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> DoubleFunction<ToLongFunction<B>> fuse(
        DoubleFunction<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoublePredicate fuse(
        DoubleFunction<A> initial,
        Predicate<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> DoubleFunction<Predicate<B>> fuse(
        DoubleFunction<A> initial,
        BiPredicate<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleConsumer fuse(
        DoubleFunction<A> initial,
        Consumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> DoubleFunction<Consumer<B>> fuse(
        DoubleFunction<A> initial,
        BiConsumer<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleFunction<DoubleConsumer> fuse(
        DoubleFunction<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleFunction<IntConsumer> fuse(
        DoubleFunction<A> initial,
        ObjIntConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleFunction<LongConsumer> fuse(
        DoubleFunction<A> initial,
        ObjLongConsumer<A> next
    ) {
        return (double d) -> (long n) -> next.accept(initial.apply(d), n);
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

    public static <A> DoubleFunction<A> fuse(
        DoubleToIntFunction initial,
        IntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        DoubleToIntFunction initial,
        IntToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleToLongFunction fuse(
        DoubleToIntFunction initial,
        IntToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoublePredicate fuse(
        DoubleToIntFunction initial,
        IntPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleConsumer fuse(
        DoubleToIntFunction initial,
        IntConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleToIntFunction fuse(
        DoubleToIntFunction initial,
        IntUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<IntUnaryOperator> fuse(
        DoubleToIntFunction initial,
        IntBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> DoubleFunction<A> fuse(
        DoubleToLongFunction initial,
        LongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        DoubleToLongFunction initial,
        LongToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleToIntFunction fuse(
        DoubleToLongFunction initial,
        LongToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoublePredicate fuse(
        DoubleToLongFunction initial,
        LongPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleConsumer fuse(
        DoubleToLongFunction initial,
        LongConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleToLongFunction fuse(
        DoubleToLongFunction initial,
        LongUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<LongUnaryOperator> fuse(
        DoubleToLongFunction initial,
        LongBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> Function<A, B> fuse(
        ToDoubleFunction<A> initial,
        DoubleFunction<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToIntFunction<A> fuse(
        ToDoubleFunction<A> initial,
        DoubleToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToLongFunction<A> fuse(
        ToDoubleFunction<A> initial,
        DoubleToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Predicate<A> fuse(
        ToDoubleFunction<A> initial,
        DoublePredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Consumer<A> fuse(
        ToDoubleFunction<A> initial,
        DoubleConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToDoubleFunction<A> fuse(
        ToDoubleFunction<A> initial,
        DoubleUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Function<A, DoubleUnaryOperator> fuse(
        ToDoubleFunction<A> initial,
        DoubleBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleFunction<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, Predicate<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoublePredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, Consumer<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, Function<B, DoubleUnaryOperator>> fuse(
        ToDoubleBiFunction<A, B> initial,
        DoubleBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> IntFunction<B> fuse(
        IntFunction<A> initial,
        Function<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> IntFunction<Function<B, C>> fuse(
        IntFunction<A> initial,
        BiFunction<A, B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntToDoubleFunction fuse(
        IntFunction<A> initial,
        ToDoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> IntFunction<ToDoubleFunction<B>> fuse(
        IntFunction<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntUnaryOperator fuse(
        IntFunction<A> initial,
        ToIntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> IntFunction<ToIntFunction<B>> fuse(
        IntFunction<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntToLongFunction fuse(
        IntFunction<A> initial,
        ToLongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> IntFunction<ToLongFunction<B>> fuse(
        IntFunction<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntPredicate fuse(
        IntFunction<A> initial,
        Predicate<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> IntFunction<Predicate<B>> fuse(
        IntFunction<A> initial,
        BiPredicate<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntConsumer fuse(
        IntFunction<A> initial,
        Consumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> IntFunction<Consumer<B>> fuse(
        IntFunction<A> initial,
        BiConsumer<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntFunction<DoubleConsumer> fuse(
        IntFunction<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntFunction<IntConsumer> fuse(
        IntFunction<A> initial,
        ObjIntConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntFunction<LongConsumer> fuse(
        IntFunction<A> initial,
        ObjLongConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> IntFunction<A> fuse(
        IntToDoubleFunction initial,
        DoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntUnaryOperator fuse(
        IntToDoubleFunction initial,
        DoubleToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntToLongFunction fuse(
        IntToDoubleFunction initial,
        DoubleToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntPredicate fuse(
        IntToDoubleFunction initial,
        DoublePredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntConsumer fuse(
        IntToDoubleFunction initial,
        DoubleConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntToDoubleFunction fuse(
        IntToDoubleFunction initial,
        DoubleUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<DoubleUnaryOperator> fuse(
        IntToDoubleFunction initial,
        DoubleBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> IntFunction<A> fuse(
        IntToLongFunction initial,
        LongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntToDoubleFunction fuse(
        IntToLongFunction initial,
        LongToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntUnaryOperator fuse(
        IntToLongFunction initial,
        LongToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntPredicate fuse(
        IntToLongFunction initial,
        LongPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntConsumer fuse(
        IntToLongFunction initial,
        LongConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntToLongFunction fuse(
        IntToLongFunction initial,
        LongUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<LongUnaryOperator> fuse(
        IntToLongFunction initial,
        LongBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> Function<A, B> fuse(
        ToIntFunction<A> initial,
        IntFunction<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToDoubleFunction<A> fuse(
        ToIntFunction<A> initial,
        IntToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToLongFunction<A> fuse(
        ToIntFunction<A> initial,
        IntToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Predicate<A> fuse(
        ToIntFunction<A> initial,
        IntPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Consumer<A> fuse(
        ToIntFunction<A> initial,
        IntConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToIntFunction<A> fuse(
        ToIntFunction<A> initial,
        IntUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, IntUnaryOperator> fuse(
        ToIntFunction<A> initial,
        IntBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        ToIntBiFunction<A, B> initial,
        IntFunction<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToDoubleFunction<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToLongFunction<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Predicate<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Consumer<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, ToIntFunction<B>> fuse(
        ToIntBiFunction<A, B> initial,
        IntUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<A, Function<B, IntUnaryOperator>> fuse(
        ToIntBiFunction<A, B> initial,
        IntBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> LongFunction<B> fuse(
        LongFunction<A> initial,
        Function<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> LongFunction<Function<B, C>> fuse(
        LongFunction<A> initial,
        BiFunction<A, B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongToDoubleFunction fuse(
        LongFunction<A> initial,
        ToDoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> LongFunction<ToDoubleFunction<B>> fuse(
        LongFunction<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongToIntFunction fuse(
        LongFunction<A> initial,
        ToIntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> LongFunction<ToIntFunction<B>> fuse(
        LongFunction<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongUnaryOperator fuse(
        LongFunction<A> initial,
        ToLongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> LongFunction<ToLongFunction<B>> fuse(
        LongFunction<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongPredicate fuse(
        LongFunction<A> initial,
        Predicate<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> LongFunction<Predicate<B>> fuse(
        LongFunction<A> initial,
        BiPredicate<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongConsumer fuse(
        LongFunction<A> initial,
        Consumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> LongFunction<Consumer<B>> fuse(
        LongFunction<A> initial,
        BiConsumer<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongFunction<DoubleConsumer> fuse(
        LongFunction<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongFunction<IntConsumer> fuse(
        LongFunction<A> initial,
        ObjIntConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongFunction<LongConsumer> fuse(
        LongFunction<A> initial,
        ObjLongConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> LongFunction<A> fuse(
        LongToDoubleFunction initial,
        DoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongToIntFunction fuse(
        LongToDoubleFunction initial,
        DoubleToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongUnaryOperator fuse(
        LongToDoubleFunction initial,
        DoubleToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongPredicate fuse(
        LongToDoubleFunction initial,
        DoublePredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongConsumer fuse(
        LongToDoubleFunction initial,
        DoubleConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongToDoubleFunction fuse(
        LongToDoubleFunction initial,
        DoubleUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<DoubleUnaryOperator> fuse(
        LongToDoubleFunction initial,
        DoubleBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> LongFunction<A> fuse(
        LongToIntFunction initial,
        IntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongToDoubleFunction fuse(
        LongToIntFunction initial,
        IntToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongUnaryOperator fuse(
        LongToIntFunction initial,
        IntToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongPredicate fuse(
        LongToIntFunction initial,
        IntPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongConsumer fuse(
        LongToIntFunction initial,
        IntConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongToIntFunction fuse(
        LongToIntFunction initial,
        IntUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<IntUnaryOperator> fuse(
        LongToIntFunction initial,
        IntBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> Function<A, B> fuse(
        ToLongFunction<A> initial,
        LongFunction<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToDoubleFunction<A> fuse(
        ToLongFunction<A> initial,
        LongToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToIntFunction<A> fuse(
        ToLongFunction<A> initial,
        LongToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Predicate<A> fuse(
        ToLongFunction<A> initial,
        LongPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Consumer<A> fuse(
        ToLongFunction<A> initial,
        LongConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToLongFunction<A> fuse(
        ToLongFunction<A> initial,
        LongUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Function<A, LongUnaryOperator> fuse(
        ToLongFunction<A> initial,
        LongBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        ToLongBiFunction<A, B> initial,
        LongFunction<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, Predicate<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, Consumer<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(
        ToLongBiFunction<A, B> initial,
        LongUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, Function<B, LongUnaryOperator>> fuse(
        ToLongBiFunction<A, B> initial,
        LongBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> Function<A, B> fuse(
        Predicate<A> initial,
        BooleanFunction<B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToDoubleFunction<A> fuse(
        Predicate<A> initial,
        BooleanToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToIntFunction<A> fuse(
        Predicate<A> initial,
        BooleanToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> ToLongFunction<A> fuse(
        Predicate<A> initial,
        BooleanToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Predicate<A> fuse(
        Predicate<A> initial,
        BooleanPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Consumer<A> fuse(
        Predicate<A> initial,
        BooleanConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B, C> Function<A, Function<B, C>> fuse(
        BiPredicate<A, B> initial,
        BooleanFunction<C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, Predicate<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Function<A, Consumer<B>> fuse(
        BiPredicate<A, B> initial,
        BooleanConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> BooleanFunction<A> fuse(
        BooleanPredicate initial,
        BooleanFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToDoubleFunction fuse(
        BooleanPredicate initial,
        BooleanToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToIntFunction fuse(
        BooleanPredicate initial,
        BooleanToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanToLongFunction fuse(
        BooleanPredicate initial,
        BooleanToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanPredicate fuse(
        BooleanPredicate initial,
        BooleanPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanConsumer fuse(
        BooleanPredicate initial,
        BooleanConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> DoubleFunction<A> fuse(
        DoublePredicate initial,
        BooleanFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        DoublePredicate initial,
        BooleanToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleToIntFunction fuse(
        DoublePredicate initial,
        BooleanToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleToLongFunction fuse(
        DoublePredicate initial,
        BooleanToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoublePredicate fuse(
        DoublePredicate initial,
        BooleanPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleConsumer fuse(
        DoublePredicate initial,
        BooleanConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> IntFunction<A> fuse(
        IntPredicate initial,
        BooleanFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntToDoubleFunction fuse(
        IntPredicate initial,
        BooleanToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntUnaryOperator fuse(
        IntPredicate initial,
        BooleanToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntToLongFunction fuse(
        IntPredicate initial,
        BooleanToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntPredicate fuse(
        IntPredicate initial,
        BooleanPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntConsumer fuse(IntPredicate initial, BooleanConsumer next) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> LongFunction<A> fuse(
        LongPredicate initial,
        BooleanFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongToDoubleFunction fuse(
        LongPredicate initial,
        BooleanToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongToIntFunction fuse(
        LongPredicate initial,
        BooleanToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongUnaryOperator fuse(
        LongPredicate initial,
        BooleanToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongPredicate fuse(
        LongPredicate initial,
        BooleanPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongConsumer fuse(
        LongPredicate initial,
        BooleanConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A, B> Supplier<B> fuse(
        Supplier<A> initial,
        Function<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B, C> Function<B, C> fuse(
        Supplier<A> initial,
        BiFunction<A, B, C> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleSupplier fuse(
        Supplier<A> initial,
        ToDoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> ToDoubleFunction<B> fuse(
        Supplier<A> initial,
        ToDoubleBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntSupplier fuse(
        Supplier<A> initial,
        ToIntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> ToIntFunction<B> fuse(
        Supplier<A> initial,
        ToIntBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongSupplier fuse(
        Supplier<A> initial,
        ToLongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> ToLongFunction<B> fuse(
        Supplier<A> initial,
        ToLongBiFunction<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> BooleanSupplier fuse(
        Supplier<A> initial,
        Predicate<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Predicate<B> fuse(
        Supplier<A> initial,
        BiPredicate<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> Operator fuse(Supplier<A> initial, Consumer<A> next) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A, B> Consumer<B> fuse(
        Supplier<A> initial,
        BiConsumer<A, B> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> DoubleConsumer fuse(
        Supplier<A> initial,
        ObjDoubleConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> IntConsumer fuse(
        Supplier<A> initial,
        ObjIntConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static <A> LongConsumer fuse(
        Supplier<A> initial,
        ObjLongConsumer<A> next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> Supplier<A> fuse(
        BooleanSupplier initial,
        BooleanFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        BooleanSupplier initial,
        BooleanToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntSupplier fuse(
        BooleanSupplier initial,
        BooleanToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongSupplier fuse(
        BooleanSupplier initial,
        BooleanToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanSupplier fuse(
        BooleanSupplier initial,
        BooleanPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static Operator fuse(BooleanSupplier initial, BooleanConsumer next) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> Supplier<A> fuse(
        DoubleSupplier initial,
        DoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntSupplier fuse(
        DoubleSupplier initial,
        DoubleToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongSupplier fuse(
        DoubleSupplier initial,
        DoubleToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanSupplier fuse(
        DoubleSupplier initial,
        DoublePredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static Operator fuse(DoubleSupplier initial, DoubleConsumer next) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        DoubleSupplier initial,
        DoubleUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        DoubleSupplier initial,
        DoubleBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    /* IntSupplier */

    public static Combine.WithIntSupplier fuseIntSupplier(IntSupplier initial) {
        return Combine.WithIntSupplier.of(initial);
    }

    public static Combine.WithIntSupplier fuse(IntSupplier initial) {
        return fuseIntSupplier(initial);
    }

    public static <A> Supplier<A> fuse(
        IntSupplier initial,
        IntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        IntSupplier initial,
        IntToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongSupplier fuse(
        IntSupplier initial,
        IntToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanSupplier fuse(IntSupplier initial, IntPredicate next) {
        return Z.fuse(initial).fuse(next);
    }

    public static Operator fuse(IntSupplier initial, IntConsumer next) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntSupplier fuse(IntSupplier initial, IntUnaryOperator next) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntUnaryOperator fuse(
        IntSupplier initial,
        IntBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> Supplier<A> fuse(
        LongSupplier initial,
        LongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleSupplier fuse(
        LongSupplier initial,
        LongToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntSupplier fuse(
        LongSupplier initial,
        LongToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static BooleanSupplier fuse(
        LongSupplier initial,
        LongPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static Operator fuse(LongSupplier initial, LongConsumer next) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongSupplier fuse(
        LongSupplier initial,
        LongUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongUnaryOperator fuse(
        LongSupplier initial,
        LongBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    /* UnaryOperator [Much SKIPPED] Overlap with Function<A, A> (Erasure applies) */

    public static <A> Combine.WithUnaryOperator<A> fuseUnaryOperator(
        UnaryOperator<A> initial
    ) {
        return Combine.WithUnaryOperator.of(initial);
    }

    public static <A> Combine.WithUnaryOperator<A> fuse(
        UnaryOperator<A> initial
    ) {
        return fuseUnaryOperator(initial);
    }

    /* BinaryOperator [Much SKIPPED] Overlap with BiFunction<A, A, A> (Erasure applies) */

    public static <A> Combine.WithBinaryOperator<A> fuseBinaryOperator(
        BinaryOperator<A> initial
    ) {
        return Combine.WithBinaryOperator.of(initial);
    }

    public static <A> Combine.WithBinaryOperator<A> fuse(
        BinaryOperator<A> initial
    ) {
        return fuseBinaryOperator(initial);
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

    public static <A> DoubleFunction<A> fuse(
        DoubleUnaryOperator initial,
        DoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleToIntFunction fuse(
        DoubleUnaryOperator initial,
        DoubleToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleToLongFunction fuse(
        DoubleUnaryOperator initial,
        DoubleToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoublePredicate fuse(
        DoubleUnaryOperator initial,
        DoublePredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleConsumer fuse(
        DoubleUnaryOperator initial,
        DoubleConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleUnaryOperator fuse(
        DoubleUnaryOperator initial,
        DoubleUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<DoubleUnaryOperator> fuse(
        DoubleUnaryOperator initial,
        DoubleBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> DoubleFunction<DoubleFunction<A>> fuse(
        DoubleBinaryOperator initial,
        DoubleFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<DoubleToIntFunction> fuse(
        DoubleBinaryOperator initial,
        DoubleToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<DoubleToLongFunction> fuse(
        DoubleBinaryOperator initial,
        DoubleToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<DoublePredicate> fuse(
        DoubleBinaryOperator initial,
        DoublePredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<DoubleConsumer> fuse(
        DoubleBinaryOperator initial,
        DoubleConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<DoubleUnaryOperator> fuse(
        DoubleBinaryOperator initial,
        DoubleUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static DoubleFunction<DoubleFunction<DoubleUnaryOperator>> fuse(
        DoubleBinaryOperator initial,
        DoubleBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> IntFunction<A> fuse(
        IntUnaryOperator initial,
        IntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntToDoubleFunction fuse(
        IntUnaryOperator initial,
        IntToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntToLongFunction fuse(
        IntUnaryOperator initial,
        IntToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntPredicate fuse(
        IntUnaryOperator initial,
        IntPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntConsumer fuse(IntUnaryOperator initial, IntConsumer next) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntUnaryOperator fuse(
        IntUnaryOperator initial,
        IntUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<IntUnaryOperator> fuse(
        IntUnaryOperator initial,
        IntBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> IntFunction<IntFunction<A>> fuse(
        IntBinaryOperator initial,
        IntFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<IntToDoubleFunction> fuse(
        IntBinaryOperator initial,
        IntToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<IntToLongFunction> fuse(
        IntBinaryOperator initial,
        IntToLongFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<IntPredicate> fuse(
        IntBinaryOperator initial,
        IntPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<IntConsumer> fuse(
        IntBinaryOperator initial,
        IntConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<IntUnaryOperator> fuse(
        IntBinaryOperator initial,
        IntUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static IntFunction<IntFunction<IntUnaryOperator>> fuse(
        IntBinaryOperator initial,
        IntBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> LongFunction<A> fuse(
        LongUnaryOperator initial,
        LongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongToDoubleFunction fuse(
        LongUnaryOperator initial,
        LongToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongToIntFunction fuse(
        LongUnaryOperator initial,
        LongToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongPredicate fuse(
        LongUnaryOperator initial,
        LongPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongConsumer fuse(
        LongUnaryOperator initial,
        LongConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongUnaryOperator fuse(
        LongUnaryOperator initial,
        LongUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<LongUnaryOperator> fuse(
        LongUnaryOperator initial,
        LongBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static <A> LongFunction<LongFunction<A>> fuse(
        LongBinaryOperator initial,
        LongFunction<A> next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<LongToDoubleFunction> fuse(
        LongBinaryOperator initial,
        LongToDoubleFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<LongToIntFunction> fuse(
        LongBinaryOperator initial,
        LongToIntFunction next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<LongPredicate> fuse(
        LongBinaryOperator initial,
        LongPredicate next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<LongConsumer> fuse(
        LongBinaryOperator initial,
        LongConsumer next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<LongUnaryOperator> fuse(
        LongBinaryOperator initial,
        LongUnaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
    }

    public static LongFunction<LongFunction<LongUnaryOperator>> fuse(
        LongBinaryOperator initial,
        LongBinaryOperator next
    ) {
        return Z.fuse(initial).fuse(next);
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

    public static Combine.WithBooleanSupplier with(boolean initial) {
        return Combine.WithBooleanSupplier.of(() -> initial);
    }

    public static Combine.WithDoubleSupplier with(double initial) {
        return Combine.WithDoubleSupplier.of(() -> initial);
    }

    public static Combine.WithIntSupplier with(int initial) {
        return Combine.WithIntSupplier.of(() -> initial);
    }

    public static Combine.WithLongSupplier with(long initial) {
        return Combine.WithLongSupplier.of(() -> initial);
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
        return Z.fuse(initial).resolve();
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> split(
        ToDoubleBiFunction<A, B> initial
    ) {
        return splitToDoubleBiFunction(initial);
    }

    public static <A, B> Function<A, ToIntFunction<B>> splitToIntBiFunction(
        ToIntBiFunction<A, B> initial
    ) {
        return Z.fuse(initial).resolve();
    }

    public static <A, B> Function<A, ToIntFunction<B>> split(
        ToIntBiFunction<A, B> initial
    ) {
        return splitToIntBiFunction(initial);
    }

    public static <A, B> Function<A, ToLongFunction<B>> splitToLongBiFunction(
        ToLongBiFunction<A, B> initial
    ) {
        return Z.fuse(initial).resolve();
    }

    public static <A, B> Function<A, ToLongFunction<B>> split(
        ToLongBiFunction<A, B> initial
    ) {
        return splitToLongBiFunction(initial);
    }

    public static <A, B> Function<A, Predicate<B>> splitBiPredicate(
        BiPredicate<A, B> initial
    ) {
        return Z.fuse(initial).resolve();
    }

    public static <A, B> Function<A, Predicate<B>> split(
        BiPredicate<A, B> initial
    ) {
        return splitBiPredicate(initial);
    }

    public static <A> Function<A, DoubleConsumer> splitObjDoubleConsumer(
        ObjDoubleConsumer<A> initial
    ) {
        return Z.fuse(initial).resolve();
    }

    public static <A> Function<A, DoubleConsumer> split(
        ObjDoubleConsumer<A> initial
    ) {
        return splitObjDoubleConsumer(initial);
    }

    public static <A> Function<A, IntConsumer> splitObjIntConsumer(
        ObjIntConsumer<A> initial
    ) {
        return Z.fuse(initial).resolve();
    }

    public static <A> Function<A, IntConsumer> split(
        ObjIntConsumer<A> initial
    ) {
        return splitObjIntConsumer(initial);
    }

    public static <A> Function<A, LongConsumer> splitObjLongConsumer(
        ObjLongConsumer<A> initial
    ) {
        return Z.fuse(initial).resolve();
    }

    public static <A> Function<A, LongConsumer> split(
        ObjLongConsumer<A> initial
    ) {
        return splitObjLongConsumer(initial);
    }

    public static <A, B> Function<A, Consumer<B>> splitBiConsumer(
        BiConsumer<A, B> initial
    ) {
        return Z.fuse(initial).resolve();
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

    @Evil
    public static <A, B, C> Function<A, Function<B, C>> absorb(
        Consumer<A> initial,
        Function<B, C> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, B> absorb(
        Consumer<A> initial,
        Supplier<B> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Predicate<A> absorb(
        Consumer<A> initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> ToDoubleFunction<A> absorb(
        Consumer<A> initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> ToIntFunction<A> absorb(
        Consumer<A> initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> ToLongFunction<A> absorb(
        Consumer<A> initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Consumer<A> absorb(Consumer<A> initial, Operator next) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A, B, C> Function<A, Function<B, C>> absorb(
        BiConsumer<A, B> initial,
        Supplier<C> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, Predicate<B>> absorb(
        BiConsumer<A, B> initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, ToDoubleFunction<B>> absorb(
        BiConsumer<A, B> initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, ToIntFunction<B>> absorb(
        BiConsumer<A, B> initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, ToLongFunction<B>> absorb(
        BiConsumer<A, B> initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A, B> Function<A, Consumer<B>> absorb(
        BiConsumer<A, B> initial,
        Operator next
    ) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A> BooleanFunction<A> absorb(
        BooleanConsumer initial,
        Supplier<A> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static BooleanPredicate absorb(
        BooleanConsumer initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static BooleanToDoubleFunction absorb(
        BooleanConsumer initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static BooleanToIntFunction absorb(
        BooleanConsumer initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static BooleanToLongFunction absorb(
        BooleanConsumer initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static BooleanConsumer absorb(
        BooleanConsumer initial,
        Operator next
    ) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A> DoubleFunction<A> absorb(
        DoubleConsumer initial,
        Supplier<A> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static DoublePredicate absorb(
        DoubleConsumer initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static DoubleUnaryOperator absorb(
        DoubleConsumer initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static DoubleToIntFunction absorb(
        DoubleConsumer initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static DoubleToLongFunction absorb(
        DoubleConsumer initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static DoubleConsumer absorb(DoubleConsumer initial, Operator next) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A, B> Function<A, DoubleFunction<B>> absorb(
        ObjDoubleConsumer<A> initial,
        Supplier<B> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, DoublePredicate> absorb(
        ObjDoubleConsumer<A> initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, DoubleUnaryOperator> absorb(
        ObjDoubleConsumer<A> initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, DoubleToIntFunction> absorb(
        ObjDoubleConsumer<A> initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, DoubleToLongFunction> absorb(
        ObjDoubleConsumer<A> initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, DoubleConsumer> absorb(
        ObjDoubleConsumer<A> initial,
        Operator next
    ) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A> IntFunction<A> absorb(
        IntConsumer initial,
        Supplier<A> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static IntPredicate absorb(
        IntConsumer initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static IntToDoubleFunction absorb(
        IntConsumer initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static IntUnaryOperator absorb(
        IntConsumer initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static IntToLongFunction absorb(
        IntConsumer initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static IntConsumer absorb(IntConsumer initial, Operator next) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A, B> Function<A, IntFunction<B>> absorb(
        ObjIntConsumer<A> initial,
        Supplier<B> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, IntPredicate> absorb(
        ObjIntConsumer<A> initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, IntToDoubleFunction> absorb(
        ObjIntConsumer<A> initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, IntUnaryOperator> absorb(
        ObjIntConsumer<A> initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, IntToLongFunction> absorb(
        ObjIntConsumer<A> initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, IntConsumer> absorb(
        ObjIntConsumer<A> initial,
        Operator next
    ) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A> LongFunction<A> absorb(
        LongConsumer initial,
        Supplier<A> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static LongPredicate absorb(
        LongConsumer initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static LongToDoubleFunction absorb(
        LongConsumer initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static LongToIntFunction absorb(
        LongConsumer initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static LongUnaryOperator absorb(
        LongConsumer initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static LongConsumer absorb(LongConsumer initial, Operator next) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A, B> Function<A, LongFunction<B>> absorb(
        ObjLongConsumer<A> initial,
        Supplier<B> next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, LongPredicate> absorb(
        ObjLongConsumer<A> initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, LongToDoubleFunction> absorb(
        ObjLongConsumer<A> initial,
        DoubleSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, LongToIntFunction> absorb(
        ObjLongConsumer<A> initial,
        IntSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, LongUnaryOperator> absorb(
        ObjLongConsumer<A> initial,
        LongSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static <A> Function<A, LongConsumer> absorb(
        ObjLongConsumer<A> initial,
        Operator next
    ) {
        return Z.fuse(initial).absorb(next);
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

    @Evil
    public static <A> Supplier<A> absorb(Operator initial, Supplier<A> next) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static BooleanSupplier absorb(
        Operator initial,
        BooleanSupplier next
    ) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static DoubleSupplier absorb(Operator initial, DoubleSupplier next) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static IntSupplier absorb(Operator initial, IntSupplier next) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static LongSupplier absorb(Operator initial, LongSupplier next) {
        return Z.fuse(initial).absorb(next);
    }

    @Evil
    public static Operator absorb(Operator initial, Operator next) {
        return Z.fuse(initial).absorb(next);
    }
}
