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
import so.dang.cool.z.internal.combination.Fusion;
import so.dang.cool.z.internal.function.DecFunction;
import so.dang.cool.z.internal.function.DodecFunction;
import so.dang.cool.z.internal.function.NonFunction;
import so.dang.cool.z.internal.function.OctFunction;
import so.dang.cool.z.internal.function.Operator;
import so.dang.cool.z.internal.function.QuadFunction;
import so.dang.cool.z.internal.function.QuinFunction;
import so.dang.cool.z.internal.function.SeptFunction;
import so.dang.cool.z.internal.function.SexFunction;
import so.dang.cool.z.internal.function.TriFunction;
import so.dang.cool.z.internal.function.UndecFunction;


/**
 * Z manipulates Java functional interfaces.
 * 
 * The Z class is the entry point for Z usage.
 * 
 * For more general guidance, see the notes on the package itself.
 */
public final class Z {
    private Z() {}

    public static <A> UnaryOperator<A> id(Class<A> clazz) {
        return (A a) -> a;
    }

    /*
        ┏┓
        ┏━━━━┓
        ┏━━━━━━━━┓
        ┏━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┃                        ┃
        ┃    ┏━╸╻ ╻┏━┓╻┏━┓┏┓╻    ┃
        ┃    ┣╸ ┃ ┃┗━┓┃┃ ┃┃┗┫    ┃
        ┃    ╹  ┗━┛┗━┛╹┗━┛╹ ╹    ┃
        ┃                        ┃
        ┗━━━━━━━━━━━━━━━━━━━━━━━━┛
    */

    /* Function */
    
    public static <A, B, C> Function<A, C> fuse(Function<A, B> initial, Function<B, C> next) {
        return (A a) -> next.apply(initial.apply(a));
    }
    
    public static <A, B, C, D> Function<A, Function<C, D>> fuse(Function<A, B> initial, BiFunction<B, C, D> next) {
        return (A a) -> (C c) -> next.apply(initial.apply(a), c);
    }
    
    public static <A, B> ToDoubleFunction<A> fuse(Function<A, B> initial, ToDoubleFunction<B> next) {
        return (A a) -> next.applyAsDouble(initial.apply(a));
    }
    
    public static <A, B, C> Function<A, ToDoubleFunction<C>> fuse(Function<A, B> initial, ToDoubleBiFunction<B, C> next) {
        return (A a) -> (C c) -> next.applyAsDouble(initial.apply(a), c);
    }
    
    public static <A, B> ToIntFunction<A> fuse(Function<A, B> initial, ToIntFunction<B> next) {
        return (A a) -> next.applyAsInt(initial.apply(a));
    }
    
    public static <A, B, C> Function<A, ToIntFunction<C>> fuse(Function<A, B> initial, ToIntBiFunction<B, C> next) {
        return (A a) -> (C c) -> next.applyAsInt(initial.apply(a), c);
    }
    
    public static <A, B> ToLongFunction<A> fuse(Function<A, B> initial, ToLongFunction<B> next) {
        return (A a) -> next.applyAsLong(initial.apply(a));
    }
    
    public static <A, B, C> Function<A, ToLongFunction<C>> fuse(Function<A, B> initial, ToLongBiFunction<B, C> next) {
        return (A a) -> (C c) -> next.applyAsLong(initial.apply(a), c);
    }

    public static <A, B> Predicate<A> fuse(Function<A, B> initial, Predicate<B> next) {
        return (A a) -> next.test(initial.apply(a));
    }

    public static <A, B, C> Function<A, Predicate<C>> fuse(Function<A, B> initial, BiPredicate<B, C> next) {
        return (A a) -> (C c) -> next.test(initial.apply(a), c);
    }

    public static <A, B> Consumer<A> fuse(Function<A, B> initial, Consumer<B> next) {
        return (A a) -> next.accept(initial.apply(a));
    }

    public static <A, B, C> Function<A, Consumer<C>> fuse(Function<A, B> initial, BiConsumer<B, C> next) {
        return (A a) -> (C c) -> next.accept(initial.apply(a), c);
    }

    public static <A, B> Function<A, DoubleConsumer> fuse(Function<A, B> initial, ObjDoubleConsumer<B> next) {
        return (A a) -> (double d) -> next.accept(initial.apply(a), d);
    }

    public static <A, B> Function<A, IntConsumer> fuse(Function<A, B> initial, ObjIntConsumer<B> next) {
        return (A a) -> (int i) -> next.accept(initial.apply(a), i);
    }

    public static <A, B> Function<A, LongConsumer> fuse(Function<A, B> initial, ObjLongConsumer<B> next) {
        return (A a) -> (long n) -> next.accept(initial.apply(a), n);
    }

    public static <A, B> Function<A, B> fuse(Function<A, B> initial, UnaryOperator<B> next) {
        return (A a) -> next.apply(initial.apply(a));
    }

    public static <A, B> Function<A, UnaryOperator<B>> fuse(Function<A, B> initial, BinaryOperator<B> next) {
        return (A a) -> (B b) -> next.apply(initial.apply(a), b);
    }
    
    /* BiFunction */

    public static <A, B, C, D> Function<A, Function<B, D>> fuse(BiFunction<A, B, C> initial, Function<C, D> next) {
        return (A a) -> (B b) -> next.apply(initial.apply(a, b));
    }

    public static <A, B, C, D, E> Function<A, Function<B, Function<D, E>>> fuse(BiFunction<A, B, C> initial, BiFunction<C, D, E> next) {
        return (A a) -> (B b) -> (D d) -> next.apply(initial.apply(a, b), d);
    }

    public static <A, B, C> Function<A, ToDoubleFunction<B>> fuse(BiFunction<A, B, C> initial, ToDoubleFunction<C> next) {
        return (A a) -> (B b) -> next.applyAsDouble(initial.apply(a, b));
    }

    public static <A, B, C, D> Function<A, Function<B, ToDoubleFunction<D>>> fuse(BiFunction<A, B, C> initial, ToDoubleBiFunction<C, D> next) {
        return (A a) -> (B b) -> (D d) -> next.applyAsDouble(initial.apply(a, b), d);
    }

    public static <A, B, C> Function<A, ToIntFunction<B>> fuse(BiFunction<A, B, C> initial, ToIntFunction<C> next) {
        return (A a) -> (B b) -> next.applyAsInt(initial.apply(a, b));
    }

    public static <A, B, C, D> Function<A, Function<B, ToIntFunction<D>>> fuse(BiFunction<A, B, C> initial, ToIntBiFunction<C, D> next) {
        return (A a) -> (B b) -> (D d) -> next.applyAsInt(initial.apply(a, b), d);
    }

    public static <A, B, C> Function<A, ToLongFunction<B>> fuse(BiFunction<A, B, C> initial, ToLongFunction<C> next) {
        return (A a) -> (B b) -> next.applyAsLong(initial.apply(a, b));
    }

    public static <A, B, C, D> Function<A, Function<B, ToLongFunction<D>>> fuse(BiFunction<A, B, C> initial, ToLongBiFunction<C, D> next) {
        return (A a) -> (B b) -> (D d) -> next.applyAsLong(initial.apply(a, b), d);
    }

    public static <A, B, C> Function<A, Predicate<B>> fuse(BiFunction<A, B, C> initial, Predicate<C> next) {
        return (A a) -> (B b) -> next.test(initial.apply(a, b));
    }

    public static <A, B, C, D> Function<A, Function<B, Predicate<D>>> fuse(BiFunction<A, B, C> initial, BiPredicate<C, D> next) {
        return (A a) -> (B b) -> (D d) -> next.test(initial.apply(a, b), d);
    }

    public static <A, B, C> Function<A, Consumer<B>> fuse(BiFunction<A, B, C> initial, Consumer<C> next) {
        return (A a) -> (B b) -> next.accept(initial.apply(a, b));
    }

    public static <A, B, C, D> Function<A, Function<B, Consumer<D>>> fuse(BiFunction<A, B, C> initial, BiConsumer<C, D> next) {
        return (A a) -> (B b) -> (D d) -> next.accept(initial.apply(a, b), d);
    }

    public static <A, B, C, D> Function<A, Function<B, DoubleConsumer>> fuse(BiFunction<A, B, C> initial, ObjDoubleConsumer<C> next) {
        return (A a) -> (B b) -> (double d) -> next.accept(initial.apply(a, b), d);
    }

    public static <A, B, C, D> Function<A, Function<B, IntConsumer>> fuse(BiFunction<A, B, C> initial, ObjIntConsumer<C> next) {
        return (A a) -> (B b) -> (int i) -> next.accept(initial.apply(a, b), i);
    }

    public static <A, B, C, D> Function<A, Function<B, LongConsumer>> fuse(BiFunction<A, B, C> initial, ObjLongConsumer<C> next) {
        return (A a) -> (B b) -> (long n) -> next.accept(initial.apply(a, b), n);
    }

    /* DoubleFunction */

    public static <A, B> DoubleFunction<B> fuse(DoubleFunction<A> initial, Function<A, B> next) {
        return (double d) -> next.apply(initial.apply(d));
    }

    public static <A, B, C> DoubleFunction<Function<B, C>> fuse(DoubleFunction<A> initial, BiFunction<A, B, C> next) {
        return (double d) -> (B b) -> next.apply(initial.apply(d), b);
    }

    public static <A> DoubleUnaryOperator fuse(DoubleFunction<A> initial, ToDoubleFunction<A> next) {
        return (double d) -> next.applyAsDouble(initial.apply(d));
    }

    public static <A, B> DoubleFunction<ToDoubleFunction<B>> fuse(DoubleFunction<A> initial, ToDoubleBiFunction<A, B> next) {
        return (double d) -> (B b) -> next.applyAsDouble(initial.apply(d), b);
    }

    public static <A> DoubleToIntFunction fuse(DoubleFunction<A> initial, ToIntFunction<A> next) {
        return (double d) -> next.applyAsInt(initial.apply(d));
    }

    public static <A, B> DoubleFunction<ToIntFunction<B>> fuse(DoubleFunction<A> initial, ToIntBiFunction<A, B> next) {
        return (double d) -> (B b) -> next.applyAsInt(initial.apply(d), b);
    }

    public static <A> DoubleToLongFunction fuse(DoubleFunction<A> initial, ToLongFunction<A> next) {
        return (double d) -> next.applyAsLong(initial.apply(d));
    }

    public static <A, B> DoubleFunction<ToLongFunction<B>> fuse(DoubleFunction<A> initial, ToLongBiFunction<A, B> next) {
        return (double d) -> (B b) -> next.applyAsLong(initial.apply(d), b);
    }

    public static <A> DoublePredicate fuse(DoubleFunction<A> initial, Predicate<A> next) {
        return (double d) -> next.test(initial.apply(d));
    }

    public static <A, B> DoubleFunction<Predicate<B>> fuse(DoubleFunction<A> initial, BiPredicate<A, B> next) {
        return (double d) -> (B b) -> next.test(initial.apply(d), b);
    }

    public static <A> DoubleConsumer fuse(DoubleFunction<A> initial, Consumer<A> next) {
        return (double d) -> next.accept(initial.apply(d));
    }

    public static <A, B> DoubleFunction<Consumer<B>> fuse(DoubleFunction<A> initial, BiConsumer<A, B> next) {
        return (double d) -> (B b) -> next.accept(initial.apply(d), b);
    }

    public static <A> DoubleFunction<DoubleConsumer> fuse(DoubleFunction<A> initial, ObjDoubleConsumer<A> next) {
        return (double d) -> (double d2) -> next.accept(initial.apply(d), d2);
    }

    public static <A> DoubleFunction<IntConsumer> fuse(DoubleFunction<A> initial, ObjIntConsumer<A> next) {
        return (double d) -> (int i) -> next.accept(initial.apply(d), i);
    }

    public static <A> DoubleFunction<LongConsumer> fuse(DoubleFunction<A> initial, ObjLongConsumer<A> next) {
        return (double d) -> (long n) -> next.accept(initial.apply(d), n);
    }

    /* DoubleToIntFunction */

    public static <A> DoubleFunction<A> fuse(DoubleToIntFunction initial, IntFunction<A> next) {
        return (double d) -> next.apply(initial.applyAsInt(d));
    }

    public static DoubleUnaryOperator fuse(DoubleToIntFunction initial, IntToDoubleFunction next) {
        return (double d) -> next.applyAsDouble(initial.applyAsInt(d));
    }

    public static DoubleToLongFunction fuse(DoubleToIntFunction initial, IntToLongFunction next) {
        return (double d) -> next.applyAsLong(initial.applyAsInt(d));
    }

    public static DoublePredicate fuse(DoubleToIntFunction initial, IntPredicate next) {
        return (double d) -> next.test(initial.applyAsInt(d));
    }

    public static DoubleConsumer fuse(DoubleToIntFunction initial, IntConsumer next) {
        return (double d) -> next.accept(initial.applyAsInt(d));
    }

    public static DoubleToIntFunction fuse(DoubleToIntFunction initial, IntUnaryOperator next) {
        return (double d) -> next.applyAsInt(initial.applyAsInt(d));
    }

    public static DoubleFunction<IntUnaryOperator> fuse(DoubleToIntFunction initial, IntBinaryOperator next) {
        return (double d) -> (int i) -> next.applyAsInt(initial.applyAsInt(d), i);
    }

    /* DoubleToLongFunction */

    public static <A> DoubleFunction<A> fuse(DoubleToLongFunction initial, LongFunction<A> next) {
        return (double d) -> next.apply(initial.applyAsLong(d));
    }

    public static DoubleUnaryOperator fuse(DoubleToLongFunction initial, LongToDoubleFunction next) {
        return (double d) -> next.applyAsDouble(initial.applyAsLong(d));
    }

    public static DoubleToIntFunction fuse(DoubleToLongFunction initial, LongToIntFunction next) {
        return (double d) -> next.applyAsInt(initial.applyAsLong(d));
    }

    public static DoublePredicate fuse(DoubleToLongFunction initial, LongPredicate next) {
        return (double d) -> next.test(initial.applyAsLong(d));
    }

    public static DoubleConsumer fuse(DoubleToLongFunction initial, LongConsumer next) {
        return (double d) -> next.accept(initial.applyAsLong(d));
    }

    public static DoubleToLongFunction fuse(DoubleToLongFunction initial, LongUnaryOperator next) {
        return (double d) -> next.applyAsLong(initial.applyAsLong(d));
    }

    public static DoubleFunction<LongUnaryOperator> fuse(DoubleToLongFunction initial, LongBinaryOperator next) {
        return (double d) -> (long n) -> next.applyAsLong(initial.applyAsLong(d), n);
    }

    /* ToDoubleFunction */

    public static <A, B> Function<A, B> fuse(ToDoubleFunction<A> initial, DoubleFunction<B> next) {
        return (A a) -> next.apply(initial.applyAsDouble(a));
    }

    public static <A> ToIntFunction<A> fuse(ToDoubleFunction<A> initial, DoubleToIntFunction next) {
        return (A a) -> next.applyAsInt(initial.applyAsDouble(a));
    }

    public static <A> ToLongFunction<A> fuse(ToDoubleFunction<A> initial, DoubleToLongFunction next) {
        return (A a) -> next.applyAsLong(initial.applyAsDouble(a));
    }

    public static <A> Predicate<A> fuse(ToDoubleFunction<A> initial, DoublePredicate next) {
        return (A a) -> next.test(initial.applyAsDouble(a));
    }

    public static <A> Consumer<A> fuse(ToDoubleFunction<A> initial, DoubleConsumer next) {
        return (A a) -> next.accept(initial.applyAsDouble(a));
    }

    public static <A> ToDoubleFunction<A> fuse(ToDoubleFunction<A> initial, DoubleUnaryOperator next) {
        return (A a) -> next.applyAsDouble(initial.applyAsDouble(a));
    }

    public static <A> Function<A, DoubleUnaryOperator> fuse(ToDoubleFunction<A> initial, DoubleBinaryOperator next) {
        return (A a) -> (double d) -> next.applyAsDouble(initial.applyAsDouble(a), d);
    }

    /* ToDoubleBiFunction */

    public static <A, B, C> Function<A, Function<B, C>> fuse(ToDoubleBiFunction<A, B> initial, DoubleFunction<C> next) {
        return (A a) -> (B b) -> next.apply(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(ToDoubleBiFunction<A, B> initial, DoubleToIntFunction next) {
        return (A a) -> (B b) -> next.applyAsInt(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(ToDoubleBiFunction<A, B> initial, DoubleToLongFunction next) {
        return (A a) -> (B b) -> next.applyAsLong(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, Predicate<B>> fuse(ToDoubleBiFunction<A, B> initial, DoublePredicate next) {
        return (A a) -> (B b) -> next.test(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, Consumer<B>> fuse(ToDoubleBiFunction<A, B> initial, DoubleConsumer next) {
        return (A a) -> (B b) -> next.accept(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(ToDoubleBiFunction<A, B> initial, DoubleUnaryOperator next) {
        return (A a) -> (B b) -> next.applyAsDouble(initial.applyAsDouble(a, b));
    }

    public static <A, B> Function<A, Function<B, DoubleUnaryOperator>> fuse(ToDoubleBiFunction<A, B> initial, DoubleBinaryOperator next) {
        return (A a) -> (B b) -> (double d) -> next.applyAsDouble(initial.applyAsDouble(a, b), d);
    }

    /* IntFunction */

    public static <A, B> IntFunction<B> fuse(IntFunction<A> initial, Function<A, B> next) {
        return (int i) -> next.apply(initial.apply(i));
    }

    public static <A, B, C> IntFunction<Function<B, C>> fuse(IntFunction<A> initial, BiFunction<A, B, C> next) {
        return (int i) -> (B b) -> next.apply(initial.apply(i), b);
    }

    public static <A> IntToDoubleFunction fuse(IntFunction<A> initial, ToDoubleFunction<A> next) {
        return (int d) -> next.applyAsDouble(initial.apply(d));
    }

    public static <A, B> IntFunction<ToDoubleFunction<B>> fuse(IntFunction<A> initial, ToDoubleBiFunction<A, B> next) {
        return (int i) -> (B b) -> next.applyAsDouble(initial.apply(i), b);
    }

    public static <A> IntUnaryOperator fuse(IntFunction<A> initial, ToIntFunction<A> next) {
        return (int i) -> next.applyAsInt(initial.apply(i));
    }

    public static <A, B> IntFunction<ToIntFunction<B>> fuse(IntFunction<A> initial, ToIntBiFunction<A, B> next) {
        return (int i) -> (B b) -> next.applyAsInt(initial.apply(i), b);
    }

    public static <A> IntToLongFunction fuse(IntFunction<A> initial, ToLongFunction<A> next) {
        return (int i) -> next.applyAsLong(initial.apply(i));
    }

    public static <A, B> IntFunction<ToLongFunction<B>> fuse(IntFunction<A> initial, ToLongBiFunction<A, B> next) {
        return (int i) -> (B b) -> next.applyAsLong(initial.apply(i), b);
    }

    public static <A> IntPredicate fuse(IntFunction<A> initial, Predicate<A> next) {
        return (int i) -> next.test(initial.apply(i));
    }

    public static <A, B> IntFunction<Predicate<B>> fuse(IntFunction<A> initial, BiPredicate<A, B> next) {
        return (int i) -> (B b) -> next.test(initial.apply(i), b);
    }

    public static <A> IntConsumer fuse(IntFunction<A> initial, Consumer<A> next) {
        return (int i) -> next.accept(initial.apply(i));
    }

    public static <A, B> IntFunction<Consumer<B>> fuse(IntFunction<A> initial, BiConsumer<A, B> next) {
        return (int i) -> (B b) -> next.accept(initial.apply(i), b);
    }

    public static <A> IntFunction<DoubleConsumer> fuse(IntFunction<A> initial, ObjDoubleConsumer<A> next) {
        return (int i) -> (double d) -> next.accept(initial.apply(i), d);
    }

    public static <A> IntFunction<IntConsumer> fuse(IntFunction<A> initial, ObjIntConsumer<A> next) {
        return (int i) -> (int i2) -> next.accept(initial.apply(i), i2);
    }

    public static <A> IntFunction<LongConsumer> fuse(IntFunction<A> initial, ObjLongConsumer<A> next) {
        return (int i) -> (long n) -> next.accept(initial.apply(i), n);
    }

    /* IntToDoubleFunction */

    public static <A> IntFunction<A> fuse(IntToDoubleFunction initial, DoubleFunction<A> next) {
        return (int i) -> next.apply(initial.applyAsDouble(i));
    }

    public static IntUnaryOperator fuse(IntToDoubleFunction initial, DoubleToIntFunction next) {
        return (int i) -> next.applyAsInt(initial.applyAsDouble(i));
    }

    public static IntToLongFunction fuse(IntToDoubleFunction initial, DoubleToLongFunction next) {
        return (int i) -> next.applyAsLong(initial.applyAsDouble(i));
    }

    public static IntPredicate fuse(IntToDoubleFunction initial, DoublePredicate next) {
        return (int i) -> next.test(initial.applyAsDouble(i));
    }

    public static IntConsumer fuse(IntToDoubleFunction initial, DoubleConsumer next) {
        return (int i) -> next.accept(initial.applyAsDouble(i));
    }

    public static IntToDoubleFunction fuse(IntToDoubleFunction initial, DoubleUnaryOperator next) {
        return (int i) -> next.applyAsDouble(initial.applyAsDouble(i));
    }

    public static IntFunction<DoubleUnaryOperator> fuse(IntToDoubleFunction initial, DoubleBinaryOperator next) {
        return (int i) -> (double d) -> next.applyAsDouble(initial.applyAsDouble(i), d);
    }

    /* IntToLongFunction */

    public static <A> IntFunction<A> fuse(IntToLongFunction initial, LongFunction<A> next) {
        return (int i) -> next.apply(initial.applyAsLong(i));
    }

    public static IntToDoubleFunction fuse(IntToLongFunction initial, LongToDoubleFunction next) {
        return (int i) -> next.applyAsDouble(initial.applyAsLong(i));
    }

    public static IntUnaryOperator fuse(IntToLongFunction initial, LongToIntFunction next) {
        return (int i) -> next.applyAsInt(initial.applyAsLong(i));
    }

    public static IntPredicate fuse(IntToLongFunction initial, LongPredicate next) {
        return (int i) -> next.test(initial.applyAsLong(i));
    }

    public static IntConsumer fuse(IntToLongFunction initial, LongConsumer next) {
        return (int i) -> next.accept(initial.applyAsLong(i));
    }

    public static IntToLongFunction fuse(IntToLongFunction initial, LongUnaryOperator next) {
        return (int i) -> next.applyAsLong(initial.applyAsLong(i));
    }

    public static IntFunction<LongUnaryOperator> fuse(IntToLongFunction initial, LongBinaryOperator next) {
        return (int i) -> (long n) -> next.applyAsLong(initial.applyAsLong(i), n);
    }

    /* ToIntFunction */

    public static <A, B> Function<A, B> fuse(ToIntFunction<A> initial, IntFunction<B> next) {
        return (A a) -> next.apply(initial.applyAsInt(a));
    }

    public static <A> ToDoubleFunction<A> fuse(ToIntFunction<A> initial, IntToDoubleFunction next) {
        return (A a) -> next.applyAsDouble(initial.applyAsInt(a));
    }

    public static <A> ToLongFunction<A> fuse(ToIntFunction<A> initial, IntToLongFunction next) {
        return (A a) -> next.applyAsLong(initial.applyAsInt(a));
    }

    public static <A> Predicate<A> fuse(ToIntFunction<A> initial, IntPredicate next) {
        return (A a) -> next.test(initial.applyAsInt(a));
    }

    public static <A> Consumer<A> fuse(ToIntFunction<A> initial, IntConsumer next) {
        return (A a) -> next.accept(initial.applyAsInt(a));
    }

    public static <A> ToIntFunction<A> fuse(ToIntFunction<A> initial, IntUnaryOperator next) {
        return (A a) -> next.applyAsInt(initial.applyAsInt(a));
    }

    public static <A, B> Function<A, IntUnaryOperator> fuse(ToIntFunction<A> initial, IntBinaryOperator next) {
        return (A a) -> (int i) -> next.applyAsInt(initial.applyAsInt(a), i);
    }

    /* ToIntBiFunction */

    public static <A, B, C> Function<A, Function<B, C>> fuse(ToIntBiFunction<A, B> initial, IntFunction<C> next) {
        return (A a) -> (B b) -> next.apply(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, ToDoubleFunction<B>> fuse(ToIntBiFunction<A, B> initial, IntToDoubleFunction next) {
        return (A a) -> (B b) -> next.applyAsDouble(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, ToLongFunction<B>> fuse(ToIntBiFunction<A, B> initial, IntToLongFunction next) {
        return (A a) -> (B b) -> next.applyAsLong(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, Predicate<B>> fuse(ToIntBiFunction<A, B> initial, IntPredicate next) {
        return (A a) -> (B b) -> next.test(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, Consumer<B>> fuse(ToIntBiFunction<A, B> initial, IntConsumer next) {
        return (A a) -> (B b) -> next.accept(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, ToIntFunction<B>> fuse(ToIntBiFunction<A, B> initial, IntUnaryOperator next) {
        return (A a) -> (B b) -> next.applyAsInt(initial.applyAsInt(a, b));
    }

    public static <A, B, C> Function<A, Function<B, IntUnaryOperator>> fuse(ToIntBiFunction<A, B> initial, IntBinaryOperator next) {
        return (A a) -> (B b) -> (int i) -> next.applyAsInt(initial.applyAsInt(a, b), i);
    }

    /* LongFunction */

    public static <A, B> LongFunction<B> fuse(LongFunction<A> initial, Function<A, B> next) {
        return (long n) -> next.apply(initial.apply(n));
    }

    public static <A, B, C> LongFunction<Function<B, C>> fuse(LongFunction<A> initial, BiFunction<A, B, C> next) {
        return (long n) -> (B b) -> next.apply(initial.apply(n), b);
    }

    public static <A> LongToDoubleFunction fuse(LongFunction<A> initial, ToDoubleFunction<A> next) {
        return (long n) -> next.applyAsDouble(initial.apply(n));
    }

    public static <A, B> LongFunction<ToDoubleFunction<B>> fuse(LongFunction<A> initial, ToDoubleBiFunction<A, B> next) {
        return (long n) -> (B b) -> next.applyAsDouble(initial.apply(n), b);
    }

    public static <A> LongToIntFunction fuse(LongFunction<A> initial, ToIntFunction<A> next) {
        return (long n) -> next.applyAsInt(initial.apply(n));
    }

    public static <A, B> LongFunction<ToIntFunction<B>> fuse(LongFunction<A> initial, ToIntBiFunction<A, B> next) {
        return (long n) -> (B b) -> next.applyAsInt(initial.apply(n), b);
    }

    public static <A> LongUnaryOperator fuse(LongFunction<A> initial, ToLongFunction<A> next) {
        return (long n) -> next.applyAsLong(initial.apply(n));
    }

    public static <A, B> LongFunction<ToLongFunction<B>> fuse(LongFunction<A> initial, ToLongBiFunction<A, B> next) {
        return (long n) -> (B b) -> next.applyAsLong(initial.apply(n), b);
    }

    public static <A> LongPredicate fuse(LongFunction<A> initial, Predicate<A> next) {
        return (long n) -> next.test(initial.apply(n));
    }

    public static <A, B> LongFunction<Predicate<B>> fuse(LongFunction<A> initial, BiPredicate<A, B> next) {
        return (long n) -> (B b) -> next.test(initial.apply(n), b);
    }

    public static <A> LongConsumer fuse(LongFunction<A> initial, Consumer<A> next) {
        return (long n) -> next.accept(initial.apply(n));
    }

    public static <A, B> LongFunction<Consumer<B>> fuse(LongFunction<A> initial, BiConsumer<A, B> next) {
        return (long n) -> (B b) -> next.accept(initial.apply(n), b);
    }

    public static <A> LongFunction<DoubleConsumer> fuse(LongFunction<A> initial, ObjDoubleConsumer<A> next) {
        return (long n) -> (double d) -> next.accept(initial.apply(n), d);
    }

    public static <A> LongFunction<IntConsumer> fuse(LongFunction<A> initial, ObjIntConsumer<A> next) {
        return (long n) -> (int i) -> next.accept(initial.apply(n), i);
    }

    public static <A> LongFunction<LongConsumer> fuse(LongFunction<A> initial, ObjLongConsumer<A> next) {
        return (long n) -> (long n2) -> next.accept(initial.apply(n), n2);
    }

    /* LongToDoubleFunction */

    public static <A> LongFunction<A> fuse(LongToDoubleFunction initial, DoubleFunction<A> next) {
        return (long n) -> next.apply(initial.applyAsDouble(n));
    }

    public static LongToIntFunction fuse(LongToDoubleFunction initial, DoubleToIntFunction next) {
        return (long n) -> next.applyAsInt(initial.applyAsDouble(n));
    }

    public static LongUnaryOperator fuse(LongToDoubleFunction initial, DoubleToLongFunction next) {
        return (long n) -> next.applyAsLong(initial.applyAsDouble(n));
    }

    public static LongPredicate fuse(LongToDoubleFunction initial, DoublePredicate next) {
        return (long n) -> next.test(initial.applyAsDouble(n));
    }

    public static LongConsumer fuse(LongToDoubleFunction initial, DoubleConsumer next) {
        return (long n) -> next.accept(initial.applyAsDouble(n));
    }

    public static LongToDoubleFunction fuse(LongToDoubleFunction initial, DoubleUnaryOperator next) {
        return (long n) -> next.applyAsDouble(initial.applyAsDouble(n));
    }

    public static LongFunction<DoubleUnaryOperator> fuse(LongToDoubleFunction initial, DoubleBinaryOperator next) {
        return (long n) -> (double d) -> next.applyAsDouble(initial.applyAsDouble(n), d);
    }

    /* LongToIntFunction */

    public static <A> LongFunction<A> fuse(LongToIntFunction initial, IntFunction<A> next) {
        return (long n) -> next.apply(initial.applyAsInt(n));
    }

    public static LongToDoubleFunction fuse(LongToIntFunction initial, IntToDoubleFunction next) {
        return (long n) -> next.applyAsDouble(initial.applyAsInt(n));
    }

    public static LongUnaryOperator fuse(LongToIntFunction initial, IntToLongFunction next) {
        return (long n) -> next.applyAsLong(initial.applyAsInt(n));
    }

    public static LongPredicate fuse(LongToIntFunction initial, IntPredicate next) {
        return (long n) -> next.test(initial.applyAsInt(n));
    }

    public static LongConsumer fuse(LongToIntFunction initial, IntConsumer next) {
        return (long n) -> next.accept(initial.applyAsInt(n));
    }

    public static LongToIntFunction fuse(LongToIntFunction initial, IntUnaryOperator next) {
        return (long n) -> next.applyAsInt(initial.applyAsInt(n));
    }

    public static LongFunction<IntUnaryOperator> fuse(LongToIntFunction initial, IntBinaryOperator next) {
        return (long n) -> (int i) -> next.applyAsInt(initial.applyAsInt(n), i);
    }

    /* ToLongFunction */

    public static <A, B> Function<A, B> fuse(ToLongFunction<A> initial, LongFunction<B> next) {
        return (A a) -> next.apply(initial.applyAsLong(a));
    }

    public static <A> ToDoubleFunction<A> fuse(ToLongFunction<A> initial, LongToDoubleFunction next) {
        return (A a) -> next.applyAsDouble(initial.applyAsLong(a));
    }

    public static <A> ToIntFunction<A> fuse(ToLongFunction<A> initial, LongToIntFunction next) {
        return (A a) -> next.applyAsInt(initial.applyAsLong(a));
    }

    public static <A> Predicate<A> fuse(ToLongFunction<A> initial, LongPredicate next) {
        return (A a) -> next.test(initial.applyAsLong(a));
    }

    public static <A> Consumer<A> fuse(ToLongFunction<A> initial, LongConsumer next) {
        return (A a) -> next.accept(initial.applyAsLong(a));
    }

    public static <A> ToLongFunction<A> fuse(ToLongFunction<A> initial, LongUnaryOperator next) {
        return (A a) -> next.applyAsLong(initial.applyAsLong(a));
    }

    public static <A> Function<A, LongUnaryOperator> fuse(ToLongFunction<A> initial, LongBinaryOperator next) {
        return (A a) -> (long n) -> next.applyAsLong(initial.applyAsLong(a), n);
    }

    /* ToLongBiFunction */

    public static <A, B, C> Function<A, Function<B, C>> fuse(ToLongBiFunction<A, B> initial, LongFunction<C> next) {
        return (A a) -> (B b) -> next.apply(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(ToLongBiFunction<A, B> initial, LongToDoubleFunction next) {
        return (A a) -> (B b) -> next.applyAsDouble(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(ToLongBiFunction<A, B> initial, LongToIntFunction next) {
        return (A a) -> (B b) -> next.applyAsInt(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, Predicate<B>> fuse(ToLongBiFunction<A, B> initial, LongPredicate next) {
        return (A a) -> (B b) -> next.test(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, Consumer<B>> fuse(ToLongBiFunction<A, B> initial, LongConsumer next) {
        return (A a) -> (B b) -> next.accept(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(ToLongBiFunction<A, B> initial, LongUnaryOperator next) {
        return (A a) -> (B b) -> next.applyAsLong(initial.applyAsLong(a, b));
    }

    public static <A, B> Function<A, Function<B, LongUnaryOperator>> fuse(ToLongBiFunction<A, B> initial, LongBinaryOperator next) {
        return (A a) -> (B b) -> (long n) -> next.applyAsLong(initial.applyAsLong(a, b), n);
    }

    /* Predicate */

    public static <A, B> Function<A, B> fuse(Predicate<A> initial, Function<Boolean, B> next) {
        return (A a) -> next.apply(initial.test(a));
    }

    public static <A, B, C> Function<A, Function<B, C>> fuse(Predicate<A> initial, BiFunction<Boolean, B, C> next) {
        return (A a) -> (B b) -> next.apply(initial.test(a), b);
    }

    public static <A> ToDoubleFunction<A> fuse(Predicate<A> initial, ToDoubleFunction<Boolean> next) {
        return (A a) -> next.applyAsDouble(initial.test(a));
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(Predicate<A> initial, ToDoubleBiFunction<Boolean, B> next) {
        return (A a) -> (B b) -> next.applyAsDouble(initial.test(a), b);
    }

    public static <A> ToIntFunction<A> fuse(Predicate<A> initial, ToIntFunction<Boolean> next) {
        return (A a) -> next.applyAsInt(initial.test(a));
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(Predicate<A> initial, ToIntBiFunction<Boolean, B> next) {
        return (A a) -> (B b) -> next.applyAsInt(initial.test(a), b);
    }

    public static <A> ToLongFunction<A> fuse(Predicate<A> initial, ToLongFunction<Boolean> next) {
        return (A a) -> next.applyAsLong(initial.test(a));
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(Predicate<A> initial, ToLongBiFunction<Boolean, B> next) {
        return (A a) -> (B b) -> next.applyAsLong(initial.test(a), b);
    }

    public static <A> Predicate<A> fuse(Predicate<A> initial, Predicate<Boolean> next) {
        return (A a) -> next.test(initial.test(a));
    }

    public static <A, B> Function<A, Predicate<B>> fuse(Predicate<A> initial, BiPredicate<Boolean, B> next) {
        return (A a) -> (B b) -> next.test(initial.test(a), b);
    }

    public static <A> Consumer<A> fuse(Predicate<A> initial, Consumer<Boolean> next) {
        return (A a) -> next.accept(initial.test(a));
    }

    public static <A, B> Function<A, Consumer<B>> fuse(Predicate<A> initial, BiConsumer<Boolean, B> next) {
        return (A a) -> (B b) -> next.accept(initial.test(a), b);
    }

    public static <A> Function<A, DoubleConsumer> fuse(Predicate<A> initial, ObjDoubleConsumer<Boolean> next) {
        return (A a) -> (double d) -> next.accept(initial.test(a), d);
    }

    public static <A> Function<A, IntConsumer> fuse(Predicate<A> initial, ObjIntConsumer<Boolean> next) {
        return (A a) -> (int i) -> next.accept(initial.test(a), i);
    }

    public static <A> Function<A, LongConsumer> fuse(Predicate<A> initial, ObjLongConsumer<Boolean> next) {
        return (A a) -> (long n) -> next.accept(initial.test(a), n);
    }

    public static <A> Predicate<A> fuse(Predicate<A> initial, UnaryOperator<Boolean> next) {
        return (A a) -> next.apply(initial.test(a));
    }

    public static <A> Function<A, Predicate<Boolean>> fuse(Predicate<A> initial, BinaryOperator<Boolean> next) {
        return (A a) -> (Boolean b) -> next.apply(initial.test(a), b);
    }

    /* <BiPredicate> */

    public static <A, B, C> Function<A, Function<B, C>> fuse(BiPredicate<A, B> initial, Function<Boolean, C> next) {
        return (A a) -> (B b) -> next.apply(initial.test(a, b));
    }

    public static <A, B, C, D> Function<A, Function<B, Function<C, D>>> fuse(BiPredicate<A, B> initial, BiFunction<Boolean, C, D> next) {
        return (A a) -> (B b) -> (C c) -> next.apply(initial.test(a, b), c);
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(BiPredicate<A, B> initial, ToDoubleFunction<Boolean> next) {
        return (A a) -> (B b) -> next.applyAsDouble(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, ToDoubleFunction<C>>> fuse(BiPredicate<A, B> initial, ToDoubleBiFunction<Boolean, C> next) {
        return (A a) -> (B b) -> (C c) -> next.applyAsDouble(initial.test(a, b), c);
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(BiPredicate<A, B> initial, ToIntFunction<Boolean> next) {
        return (A a) -> (B b) -> next.applyAsInt(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, ToIntFunction<C>>> fuse(BiPredicate<A, B> initial, ToIntBiFunction<Boolean, C> next) {
        return (A a) -> (B b) -> (C c) -> next.applyAsInt(initial.test(a, b), c);
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(BiPredicate<A, B> initial, ToLongFunction<Boolean> next) {
        return (A a) -> (B b) -> next.applyAsLong(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, ToLongFunction<C>>> fuse(BiPredicate<A, B> initial, ToLongBiFunction<Boolean, C> next) {
        return (A a) -> (B b) -> (C c) -> next.applyAsLong(initial.test(a, b), c);
    }

    public static <A, B> Function<A, Predicate<B>> fuse(BiPredicate<A, B> initial, Predicate<Boolean> next) {
        return (A a) -> (B b) -> next.test(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, Predicate<C>>> fuse(BiPredicate<A, B> initial, BiPredicate<Boolean, C> next) {
        return (A a) -> (B b) -> (C c) -> next.test(initial.test(a, b), c);
    }

    public static <A, B> Function<A, Consumer<B>> fuse(BiPredicate<A, B> initial, Consumer<Boolean> next) {
        return (A a) -> (B b) -> next.accept(initial.test(a, b));
    }

    public static <A, B, C> Function<A, Function<B, Consumer<C>>> fuse(BiPredicate<A, B> initial, BiConsumer<Boolean, C> next) {
        return (A a) -> (B b) -> (C c) -> next.accept(initial.test(a, b), c);
    }

    public static <A, B> Function<A, Function<B, DoubleConsumer>> fuse(BiPredicate<A, B> initial, ObjDoubleConsumer<Boolean> next) {
        return (A a) -> (B b) -> (double d) -> next.accept(initial.test(a, b), d);
    }

    public static <A, B> Function<A, Function<B, IntConsumer>> fuse(BiPredicate<A, B> initial, ObjIntConsumer<Boolean> next) {
        return (A a) -> (B b) -> (int i) -> next.accept(initial.test(a, b), i);
    }

    public static <A, B> Function<A, Function<B, LongConsumer>> fuse(BiPredicate<A, B> initial, ObjLongConsumer<Boolean> next) {
        return (A a) -> (B b) -> (long n) -> next.accept(initial.test(a, b), n);
    }

    public static <A, B> Function<A, Predicate<B>> fuse(BiPredicate<A, B> initial, UnaryOperator<Boolean> next) {
        return (A a) -> (B b) -> next.apply(initial.test(a, b));
    }

    public static <A, B> Function<A, Function<B, Predicate<Boolean>>> fuse(BiPredicate<A, B> initial, BinaryOperator<Boolean> next) {
        return (A a) -> (B b) -> (Boolean bool) -> next.apply(initial.test(a, b), bool);
    }

    /* <DoublePredicate> */

    public static <A> DoubleFunction<A> fuse(DoublePredicate initial, Function<Boolean, A> next) {
        return (double d) -> next.apply(initial.test(d));
    }

    public static <A, B> DoubleFunction<Function<A, B>> fuse(DoublePredicate initial, BiFunction<Boolean, A, B> next) {
        return (double d) -> (A a) -> next.apply(initial.test(d), a);
    }

    public static DoubleUnaryOperator fuse(DoublePredicate initial, ToDoubleFunction<Boolean> next) {
        return (double d) -> next.applyAsDouble(initial.test(d));
    }

    public static <A> DoubleFunction<ToDoubleFunction<A>> fuse(DoublePredicate initial, ToDoubleBiFunction<Boolean, A> next) {
        return (double d) -> (A a) -> next.applyAsDouble(initial.test(d), a);
    }

    public static DoubleToIntFunction fuse(DoublePredicate initial, ToIntFunction<Boolean> next) {
        return (double d) -> next.applyAsInt(initial.test(d));
    }

    public static <A> DoubleFunction<ToIntFunction<A>> fuse(DoublePredicate initial, ToIntBiFunction<Boolean, A> next) {
        return (double d) -> (A a) -> next.applyAsInt(initial.test(d), a);
    }

    public static DoubleToLongFunction fuse(DoublePredicate initial, ToLongFunction<Boolean> next) {
        return (double d) -> next.applyAsLong(initial.test(d));
    }

    public static <A> DoubleFunction<ToLongFunction<A>> fuse(DoublePredicate initial, ToLongBiFunction<Boolean, A> next) {
        return (double d) -> (A a) -> next.applyAsLong(initial.test(d), a);
    }

    public static DoublePredicate fuse(DoublePredicate initial, Predicate<Boolean> next) {
        return (double d) -> next.test(initial.test(d));
    }

    public static <A> DoubleFunction<Predicate<A>> fuse(DoublePredicate initial, BiPredicate<Boolean, A> next) {
        return (double d) -> (A a) -> next.test(initial.test(d), a);
    }

    public static DoubleConsumer fuse(DoublePredicate initial, Consumer<Boolean> next) {
        return (double d) -> next.accept(initial.test(d));
    }

    public static <A> DoubleFunction<Consumer<A>> fuse(DoublePredicate initial, BiConsumer<Boolean, A> next) {
        return (double d) -> (A a) -> next.accept(initial.test(d), a);
    }

    public static DoubleFunction<DoubleConsumer> fuse(DoublePredicate initial, ObjDoubleConsumer<Boolean> next) {
        return (double d1) -> (double d2) -> next.accept(initial.test(d1), d2);
    }

    public static DoubleFunction<IntConsumer> fuse(DoublePredicate initial, ObjIntConsumer<Boolean> next) {
        return (double d) -> (int i) -> next.accept(initial.test(d), i);
    }

    public static DoubleFunction<LongConsumer> fuse(DoublePredicate initial, ObjLongConsumer<Boolean> next) {
        return (double d) -> (long n) -> next.accept(initial.test(d), n);
    }

    public static DoublePredicate fuse(DoublePredicate initial, UnaryOperator<Boolean> next) {
        return (double d) -> next.apply(initial.test(d));
    }

    public static DoubleFunction<Predicate<Boolean>> fuse(DoublePredicate initial, BinaryOperator<Boolean> next) {
        return (double d) -> (Boolean b) -> next.apply(initial.test(d), b);
    }

    /* IntPredicate */

    public static <A> IntFunction<A> fuse(IntPredicate initial, Function<Boolean, A> next) {
        return (int i) -> next.apply(initial.test(i));
    }

    public static <A, B> IntFunction<Function<A, B>> fuse(IntPredicate initial, BiFunction<Boolean, A, B> next) {
        return (int i) -> (A a) -> next.apply(initial.test(i), a);
    }

    public static IntToDoubleFunction fuse(IntPredicate initial, ToDoubleFunction<Boolean> next) {
        return (int i) -> next.applyAsDouble(initial.test(i));
    }

    public static <A> IntFunction<ToDoubleFunction<A>> fuse(IntPredicate initial, ToDoubleBiFunction<Boolean, A> next) {
        return (int i) -> (A a) -> next.applyAsDouble(initial.test(i), a);
    }

    public static IntUnaryOperator fuse(IntPredicate initial, ToIntFunction<Boolean> next) {
        return (int i) -> next.applyAsInt(initial.test(i));
    }

    public static <A> IntFunction<ToIntFunction<A>> fuse(IntPredicate initial, ToIntBiFunction<Boolean, A> next) {
        return (int i) -> (A a) -> next.applyAsInt(initial.test(i), a);
    }

    public static IntToLongFunction fuse(IntPredicate initial, ToLongFunction<Boolean> next) {
        return (int i) -> next.applyAsLong(initial.test(i));
    }

    public static <A> IntFunction<ToLongFunction<A>> fuse(IntPredicate initial, ToLongBiFunction<Boolean, A> next) {
        return (int i) -> (A a) -> next.applyAsLong(initial.test(i), a);
    }

    public static IntPredicate fuse(IntPredicate initial, Predicate<Boolean> next) {
        return (int i) -> next.test(initial.test(i));
    }

    public static <A> IntFunction<Predicate<A>> fuse(IntPredicate initial, BiPredicate<Boolean, A> next) {
        return (int i) -> (A a) -> next.test(initial.test(i), a);
    }

    public static IntConsumer fuse(IntPredicate initial, Consumer<Boolean> next) {
        return (int i) -> next.accept(initial.test(i));
    }

    public static <A> IntFunction<Consumer<A>> fuse(IntPredicate initial, BiConsumer<Boolean, A> next) {
        return (int i) -> (A a) -> next.accept(initial.test(i), a);
    }

    public static IntFunction<DoubleConsumer> fuse(IntPredicate initial, ObjDoubleConsumer<Boolean> next) {
        return (int i) -> (double d) -> next.accept(initial.test(i), d);
    }

    public static IntFunction<IntConsumer> fuse(IntPredicate initial, ObjIntConsumer<Boolean> next) {
        return (int i1) -> (int i2) -> next.accept(initial.test(i1), i2);
    }

    public static IntFunction<LongConsumer> fuse(IntPredicate initial, ObjLongConsumer<Boolean> next) {
        return (int i) -> (long n) -> next.accept(initial.test(i), n);
    }

    public static IntPredicate fuse(IntPredicate initial, UnaryOperator<Boolean> next) {
        return (int i) -> next.apply(initial.test(i));
    }

    public static IntFunction<Predicate<Boolean>> fuse(IntPredicate initial, BinaryOperator<Boolean> next) {
        return (int i) -> (Boolean b) -> next.apply(initial.test(i), b);
    }

    /* LongPredicate */

    public static <A> LongFunction<A> fuse(LongPredicate initial, Function<Boolean, A> next) {
        return (long n) -> next.apply(initial.test(n));
    }

    public static <A, B> LongFunction<Function<A, B>> fuse(LongPredicate initial, BiFunction<Boolean, A, B> next) {
        return (long n) -> (A a) -> next.apply(initial.test(n), a);
    }

    public static LongToDoubleFunction fuse(LongPredicate initial, ToDoubleFunction<Boolean> next) {
        return (long n) -> next.applyAsDouble(initial.test(n));
    }

    public static <A> LongFunction<ToDoubleFunction<A>> fuse(LongPredicate initial, ToDoubleBiFunction<Boolean, A> next) {
        return (long n) -> (A a) -> next.applyAsDouble(initial.test(n), a);
    }

    public static LongToIntFunction fuse(LongPredicate initial, ToIntFunction<Boolean> next) {
        return (long n) -> next.applyAsInt(initial.test(n));
    }

    public static <A> LongFunction<ToIntFunction<A>> fuse(LongPredicate initial, ToIntBiFunction<Boolean, A> next) {
        return (long n) -> (A a) -> next.applyAsInt(initial.test(n), a);
    }

    public static LongUnaryOperator fuse(LongPredicate initial, ToLongFunction<Boolean> next) {
        return (long n) -> next.applyAsLong(initial.test(n));
    }

    public static <A> LongFunction<ToLongFunction<A>> fuse(LongPredicate initial, ToLongBiFunction<Boolean, A> next) {
        return (long n) -> (A a) -> next.applyAsLong(initial.test(n), a);
    }

    public static LongPredicate fuse(LongPredicate initial, Predicate<Boolean> next) {
        return (long n) -> next.test(initial.test(n));
    }

    public static <A> LongFunction<Predicate<A>> fuse(LongPredicate initial, BiPredicate<Boolean, A> next) {
        return (long n) -> (A a) -> next.test(initial.test(n), a);
    }

    public static LongConsumer fuse(LongPredicate initial, Consumer<Boolean> next) {
        return (long n) -> next.accept(initial.test(n));
    }

    public static <A> LongFunction<Consumer<A>> fuse(LongPredicate initial, BiConsumer<Boolean, A> next) {
        return (long n) -> (A a) -> next.accept(initial.test(n), a);
    }

    public static LongFunction<DoubleConsumer> fuse(LongPredicate initial, ObjDoubleConsumer<Boolean> next) {
        return (long n) -> (double d) -> next.accept(initial.test(n), d);
    }

    public static LongFunction<IntConsumer> fuse(LongPredicate initial, ObjIntConsumer<Boolean> next) {
        return (long n) -> (int i) -> next.accept(initial.test(n), i);
    }

    public static LongFunction<LongConsumer> fuse(LongPredicate initial, ObjLongConsumer<Boolean> next) {
        return (long n1) -> (long n2) -> next.accept(initial.test(n1), n2);
    }

    public static LongPredicate fuse(LongPredicate initial, UnaryOperator<Boolean> next) {
        return (long n) -> next.apply(initial.test(n));
    }

    public static LongFunction<Predicate<Boolean>> fuse(LongPredicate initial, BinaryOperator<Boolean> next) {
        return (long n) -> (Boolean b) -> next.apply(initial.test(n), b);
    }

    /* Consumer [SKIPPED] Covered in absorption */
    /* BiConsumer [SKIPPED] Covered in absorption */
    /* DoubleConsumer [SKIPPED] Covered in absorption */
    /* ObjDoubleConsumer [SKIPPED] Covered in absorption */
    /* IntConsumer [SKIPPED] Covered in absorption */
    /* ObjIntConsumer [SKIPPED] Covered in absorption */
    /* LongConsumer [SKIPPED] Covered in absorption */
    /* ObjLongConsumer [SKIPPED] Covered in absorption */

    /* Supplier */

    public static <A, B> Supplier<B> fuse(Supplier<A> initial, Function<A, B> next) {
        return () -> next.apply(initial.get());
    }

    public static <A, B, C> Function<B, C> fuse(Supplier<A> initial, BiFunction<A, B, C> next) {
        return (B b) -> next.apply(initial.get(), b);
    }

    public static <A> DoubleSupplier fuse(Supplier<A> initial, ToDoubleFunction<A> next) {
        return () -> next.applyAsDouble(initial.get());
    }

    public static <A, B> ToDoubleFunction<B> fuse(Supplier<A> initial, ToDoubleBiFunction<A, B> next) {
        return (B b) -> next.applyAsDouble(initial.get(), b);
    }

    public static <A> IntSupplier fuse(Supplier<A> initial, ToIntFunction<A> next) {
        return () -> next.applyAsInt(initial.get());
    }

    public static <A, B> ToIntFunction<B> fuse(Supplier<A> initial, ToIntBiFunction<A, B> next) {
        return (B b) -> next.applyAsInt(initial.get(), b);
    }

    public static <A> LongSupplier fuse(Supplier<A> initial, ToLongFunction<A> next) {
        return () -> next.applyAsLong(initial.get());
    }

    public static <A, B> ToLongFunction<B> fuse(Supplier<A> initial, ToLongBiFunction<A, B> next) {
        return (B b) -> next.applyAsLong(initial.get(), b);
    }

    public static <A> BooleanSupplier fuse(Supplier<A> initial, Predicate<A> next) {
        return () -> next.test(initial.get());
    }

    public static <A, B> Predicate<B> fuse(Supplier<A> initial, BiPredicate<A, B> next) {
        return (B b) -> next.test(initial.get(), b);
    }

    public static <A> Operator fuse(Supplier<A> initial, Consumer<A> next) {
        return () -> next.accept(initial.get());
    }

    public static <A, B> Consumer<B> fuse(Supplier<A> initial, BiConsumer<A, B> next) {
        return (B b) -> next.accept(initial.get(), b);
    }

    public static <A> DoubleConsumer fuse(Supplier<A> initial, ObjDoubleConsumer<A> next) {
        return (double d) -> next.accept(initial.get(), d);
    }

    public static <A> IntConsumer fuse(Supplier<A> initial, ObjIntConsumer<A> next) {
        return (int i) -> next.accept(initial.get(), i);
    }

    public static <A> LongConsumer fuse(Supplier<A> initial, ObjLongConsumer<A> next) {
        return (long n) -> next.accept(initial.get(), n);
    }

    /* BooleanSupplier */

    public static <A> Supplier<A> fuse(BooleanSupplier initial, Function<Boolean, A> next) {
        return () -> next.apply(initial.getAsBoolean());
    }

    public static <A, B> Function<A, B> fuse(BooleanSupplier initial, BiFunction<Boolean, A, B> next) {
        return (A a) -> next.apply(initial.getAsBoolean(), a);
    }
  
    public static DoubleSupplier fuse(BooleanSupplier initial, ToDoubleFunction<Boolean> next) {
        return () -> next.applyAsDouble(initial.getAsBoolean());
    }

    public static <A> ToDoubleFunction<A> fuse(BooleanSupplier initial, ToDoubleBiFunction<Boolean, A> next) {
        return (A a) -> next.applyAsDouble(initial.getAsBoolean(), a);
    }

    public static IntSupplier fuse(BooleanSupplier initial, ToIntFunction<Boolean> next) {
        return () -> next.applyAsInt(initial.getAsBoolean());
    }

    public static <A> ToIntFunction<A> fuse(BooleanSupplier initial, ToIntBiFunction<Boolean, A> next) {
        return (A a) -> next.applyAsInt(initial.getAsBoolean(), a);
    }

    public static LongSupplier fuse(BooleanSupplier initial, ToLongFunction<Boolean> next) {
        return () -> next.applyAsLong(initial.getAsBoolean());
    }

    public static <A> ToLongFunction<A> fuse(BooleanSupplier initial, ToLongBiFunction<Boolean, A> next) {
        return (A a) -> next.applyAsLong(initial.getAsBoolean(), a);
    }

    public static BooleanSupplier fuse(BooleanSupplier initial, Predicate<Boolean> next) {
        return () -> next.test(initial.getAsBoolean());
    }

    public static <A> Predicate<A> fuse(BooleanSupplier initial, BiPredicate<Boolean, A> next) {
        return (A a) -> next.test(initial.getAsBoolean(), a);
    }

    // TODO: Should there be a BooleanConsumer?
    public static Operator fuse(BooleanSupplier initial, Consumer<Boolean> next) {
        return () -> next.accept(initial.getAsBoolean());
    }

    public static <A> Consumer<A> fuse(BooleanSupplier initial, BiConsumer<Boolean, A> next) {
        return (A a) -> next.accept(initial.getAsBoolean(), a);
    }

    public static DoubleConsumer fuse(BooleanSupplier initial, ObjDoubleConsumer<Boolean> next) {
        return (double d) -> next.accept(initial.getAsBoolean(), d);
    }

    public static IntConsumer fuse(BooleanSupplier initial, ObjIntConsumer<Boolean> next) {
        return (int i) -> next.accept(initial.getAsBoolean(), i);
    }

    public static LongConsumer fuse(BooleanSupplier initial, ObjLongConsumer<Boolean> next) {
        return (long n) -> next.accept(initial.getAsBoolean(), n);
    }

    public static BooleanSupplier fuse(BooleanSupplier initial, UnaryOperator<Boolean> next) {
        return () -> next.apply(initial.getAsBoolean());
    }

    // TODO: Add a BooleanUnaryOperator interface? (But is this dumb?)
    public static UnaryOperator<Boolean> fuse(BooleanSupplier initial, BinaryOperator<Boolean> next) {
        return (Boolean b) -> next.apply(initial.getAsBoolean(), b);
    }

    /* DoubleSupplier */

    public static <A> Supplier<A> fuse(DoubleSupplier initial, DoubleFunction<A> next) {
        return () -> next.apply(initial.getAsDouble());
    }

    public static IntSupplier fuse(DoubleSupplier initial, DoubleToIntFunction next) {
        return () -> next.applyAsInt(initial.getAsDouble());
    }

    public static LongSupplier fuse(DoubleSupplier initial, DoubleToLongFunction next) {
        return () -> next.applyAsLong(initial.getAsDouble());
    }

    public static BooleanSupplier fuse(DoubleSupplier initial, DoublePredicate next) {
        return () -> next.test(initial.getAsDouble());
    }

    public static Operator fuse(DoubleSupplier initial, DoubleConsumer next) {
        return () -> next.accept(initial.getAsDouble());
    }

    public static DoubleSupplier fuse(DoubleSupplier initial, DoubleUnaryOperator next) {
        return () -> next.applyAsDouble(initial.getAsDouble());
    }

    public static DoubleUnaryOperator fuse(DoubleSupplier initial, DoubleBinaryOperator next) {
        return (double d) -> next.applyAsDouble(initial.getAsDouble(), d);
    }

    /* IntSupplier */

    public static <A> Supplier<A> fuse(IntSupplier initial, IntFunction<A> next) {
        return () -> next.apply(initial.getAsInt());
    }

    public static DoubleSupplier fuse(IntSupplier initial, IntToDoubleFunction next) {
        return () -> next.applyAsDouble(initial.getAsInt());
    }

    public static LongSupplier fuse(IntSupplier initial, IntToLongFunction next) {
        return () -> next.applyAsLong(initial.getAsInt());
    }

    public static BooleanSupplier fuse(IntSupplier initial, IntPredicate next) {
        return () -> next.test(initial.getAsInt());
    }

    public static Operator fuse(IntSupplier initial, IntConsumer next) {
        return () -> next.accept(initial.getAsInt());
    }

    public static IntSupplier fuse(IntSupplier initial, IntUnaryOperator next) {
        return () -> next.applyAsInt(initial.getAsInt());
    }

    public static IntUnaryOperator fuse(IntSupplier initial, IntBinaryOperator next) {
        return (int i) -> next.applyAsInt(initial.getAsInt(), i);
    }

    /* LongSupplier */

    public static <A> Supplier<A> fuse(LongSupplier initial, LongFunction<A> next) {
        return () -> next.apply(initial.getAsLong());
    }

    public static DoubleSupplier fuse(LongSupplier initial, LongToDoubleFunction next) {
        return () -> next.applyAsDouble(initial.getAsLong());
    }

    public static DoubleSupplier fuse(LongSupplier initial, LongToIntFunction next) {
        return () -> next.applyAsInt(initial.getAsLong());
    }

    public static BooleanSupplier fuse(LongSupplier initial, LongPredicate next) {
        return () -> next.test(initial.getAsLong());
    }

    public static Operator fuse(LongSupplier initial, LongConsumer next) {
        return () -> next.accept(initial.getAsLong());
    }

    public static LongSupplier fuse(LongSupplier initial, LongUnaryOperator next) {
        return () -> next.applyAsLong(initial.getAsLong());
    }

    public static LongUnaryOperator fuse(LongSupplier initial, LongBinaryOperator next) {
        return (long n) -> next.applyAsLong(initial.getAsLong(), n);
    }

    /* Operator [SKIPPED] Covered in absorption */

    /* UnaryOperator [SKIPPED] Overlap with Function<A, B> when A == B */

    /* BinaryOperator [SKIPPED] Overlap with BiFunction<A, B, C> when A == B == C */

    /* DoubleUnaryOperator */

    public static <A> DoubleFunction<A> fuse(DoubleUnaryOperator initial, DoubleFunction<A> next) {
        return (double d) -> next.apply(initial.applyAsDouble(d));
    }

    public static DoubleToIntFunction fuse(DoubleUnaryOperator initial, DoubleToIntFunction next) {
        return (double d) -> next.applyAsInt(initial.applyAsDouble(d));
    }

    public static DoubleToLongFunction fuse(DoubleUnaryOperator initial, DoubleToLongFunction next) {
        return (double d) -> next.applyAsLong(initial.applyAsDouble(d));
    }

    public static DoublePredicate fuse(DoubleUnaryOperator initial, DoublePredicate next) {
        return (double d) -> next.test(initial.applyAsDouble(d));
    }

    public static DoubleConsumer fuse(DoubleUnaryOperator initial, DoubleConsumer next) {
        return (double d) -> next.accept(initial.applyAsDouble(d));
    }

    public static DoubleUnaryOperator fuse(DoubleUnaryOperator initial, DoubleUnaryOperator next) {
        return (double d) -> next.applyAsDouble(initial.applyAsDouble(d));
    }

    public static DoubleFunction<DoubleUnaryOperator> fuse(DoubleUnaryOperator initial, DoubleBinaryOperator next) {
        return (double d) -> (double d2) -> next.applyAsDouble(initial.applyAsDouble(d), d2);
    }

    /* DoubleBinaryOperator */

    public static <A> DoubleFunction<DoubleFunction<A>> fuse(DoubleBinaryOperator initial, DoubleFunction<A> next) {
        return (double d) -> (double d2) -> next.apply(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleToIntFunction> fuse(DoubleBinaryOperator initial, DoubleToIntFunction next) {
        return (double d) -> (double d2) -> next.applyAsInt(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleToLongFunction> fuse(DoubleBinaryOperator initial, DoubleToLongFunction next) {
        return (double d) -> (double d2) -> next.applyAsLong(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoublePredicate> fuse(DoubleBinaryOperator initial, DoublePredicate next) {
        return (double d) -> (double d2) -> next.test(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleConsumer> fuse(DoubleBinaryOperator initial, DoubleConsumer next) {
        return (double d) -> (double d2) -> next.accept(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleUnaryOperator> fuse(DoubleBinaryOperator initial, DoubleUnaryOperator next) {
        return (double d) -> (double d2) -> next.applyAsDouble(initial.applyAsDouble(d, d2));
    }

    public static DoubleFunction<DoubleFunction<DoubleUnaryOperator>> fuse(DoubleBinaryOperator initial, DoubleBinaryOperator next) {
        return (double d) -> (double d2) -> (double d3) -> next.applyAsDouble(initial.applyAsDouble(d, d2), d3);
    }
    
    /* IntUnaryOperator */

    public static <A> IntFunction<A> fuse(IntUnaryOperator initial, IntFunction<A> next) {
        return (int i) -> next.apply(initial.applyAsInt(i));
    }

    public static IntToDoubleFunction fuse(IntUnaryOperator initial, IntToDoubleFunction next) {
        return (int i) -> next.applyAsDouble(initial.applyAsInt(i));
    }

    public static IntToLongFunction fuse(IntUnaryOperator initial, IntToLongFunction next) {
        return (int i) -> next.applyAsLong(initial.applyAsInt(i));
    }

    public static IntPredicate fuse(IntUnaryOperator initial, IntPredicate next) {
        return (int i) -> next.test(initial.applyAsInt(i));
    }

    public static IntConsumer fuse(IntUnaryOperator initial, IntConsumer next) {
        return (int i) -> next.accept(initial.applyAsInt(i));
    }

    public static IntUnaryOperator fuse(IntUnaryOperator initial, IntUnaryOperator next) {
        return (int i) -> next.applyAsInt(initial.applyAsInt(i));
    }

    public static IntFunction<IntUnaryOperator> fuse(IntUnaryOperator initial, IntBinaryOperator next) {
        return (int i) -> (int i2) -> next.applyAsInt(initial.applyAsInt(i), i2);
    }

    /* IntBinaryOperator */

    public static <A> IntFunction<IntFunction<A>> fuse(IntBinaryOperator initial, IntFunction<A> next) {
        return (int i) -> (int i2) -> next.apply(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntToDoubleFunction> fuse(IntBinaryOperator initial, IntToDoubleFunction next) {
        return (int i) -> (int i2) -> next.applyAsDouble(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntToLongFunction> fuse(IntBinaryOperator initial, IntToLongFunction next) {
        return (int i) -> (int i2) -> next.applyAsLong(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntPredicate> fuse(IntBinaryOperator initial, IntPredicate next) {
        return (int i) -> (int i2) -> next.test(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntConsumer> fuse(IntBinaryOperator initial, IntConsumer next) {
        return (int i) -> (int i2) -> next.accept(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntUnaryOperator> fuse(IntBinaryOperator initial, IntUnaryOperator next) {
        return (int i) -> (int i2) -> next.applyAsInt(initial.applyAsInt(i, i2));
    }

    public static IntFunction<IntFunction<IntUnaryOperator>> fuse(IntBinaryOperator initial, IntBinaryOperator next) {
        return (int i) -> (int i2) -> (int i3) -> next.applyAsInt(initial.applyAsInt(i, i2), i3);
    }

    /* LongUnaryOperator */

    public static <A> LongFunction<A> fuse(LongUnaryOperator initial, LongFunction<A> next) {
        return (long n) -> next.apply(initial.applyAsLong(n));
    }

    public static LongToDoubleFunction fuse(LongUnaryOperator initial, LongToDoubleFunction next) {
        return (long n) -> next.applyAsDouble(initial.applyAsLong(n));
    }

    public static LongToIntFunction fuse(LongUnaryOperator initial, LongToIntFunction next) {
        return (long n) -> next.applyAsInt(initial.applyAsLong(n));
    }

    public static LongPredicate fuse(LongUnaryOperator initial, LongPredicate next) {
        return (long n) -> next.test(initial.applyAsLong(n));
    }

    public static LongConsumer fuse(LongUnaryOperator initial, LongConsumer next) {
        return (long n) -> next.accept(initial.applyAsLong(n));
    }

    public static LongUnaryOperator fuse(LongUnaryOperator initial, LongUnaryOperator next) {
        return (long n) -> next.applyAsLong(initial.applyAsLong(n));
    }

    public static LongFunction<LongUnaryOperator> fuse(LongUnaryOperator initial, LongBinaryOperator next) {
        return (long n) -> (long n2) -> next.applyAsLong(initial.applyAsLong(n), n2);
    }

    /* LongBinaryOperator */

    public static <A> LongFunction<LongFunction<A>> fuse(LongBinaryOperator initial, LongFunction<A> next) {
        return (long n) -> (long n2) -> next.apply(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongToDoubleFunction> fuse(LongBinaryOperator initial, LongToDoubleFunction next) {
        return (long n) -> (long n2) -> next.applyAsDouble(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongToIntFunction> fuse(LongBinaryOperator initial, LongToIntFunction next) {
        return (long n) -> (long n2) -> next.applyAsInt(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongPredicate> fuse(LongBinaryOperator initial, LongPredicate next) {
        return (long n) -> (long n2) -> next.test(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongConsumer> fuse(LongBinaryOperator initial, LongConsumer next) {
        return (long n) -> (long n2) -> next.accept(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongUnaryOperator> fuse(LongBinaryOperator initial, LongUnaryOperator next) {
        return (long n) -> (long n2) -> next.applyAsLong(initial.applyAsLong(n, n2));
    }

    public static LongFunction<LongFunction<LongUnaryOperator>> fuse(LongBinaryOperator initial, LongBinaryOperator next) {
        return (long n) -> (long n2) -> (long n3) -> next.applyAsLong(initial.applyAsLong(n, n2), n3);
    }

    /*
        ┏┓
        ┏━━━━┓
        ┏━━━━━━━━┓
        ┏━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┃                                          ┃
        ┃    ┏━┓╻ ╻┏━┓┏━╸┏━┓   ┏━╸╻ ╻┏━┓╻┏━┓┏┓╻    ┃
        ┃    ┗━┓┃ ┃┣━┛┣╸ ┣┳┛   ┣╸ ┃ ┃┗━┓┃┃ ┃┃┗┫    ┃
        ┃    ┗━┛┗━┛╹  ┗━╸╹┗╸   ╹  ┗━┛┗━┛╹┗━┛╹ ╹    ┃
        ┃                                          ┃
        ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
    */

    public static <A> Fusion.WithFunction<A, A> withClass(Class<A> clazz) {
        return Fusion.WithFunction.of(Z.id(clazz));
    }

    public static <A> Fusion.WithFunction<A, A> with(Class<A> clazz) {
        return withClass(clazz);
    }

    public static <A> Fusion.WithSupplier<A> withObject(A initial) {
        return Fusion.WithSupplier.of(() -> initial);
    }

    public static <A> Fusion.WithSupplier<A> with(A initial) {
        return withObject(initial);
    }

    public static <A, B> Fusion.WithFunction<A, B> withFunction(Function<A, B> initial) {
        return Fusion.WithFunction.of(initial);
    }

    public static <A, B> Fusion.WithFunction<A, B> with(Function<A, B> initial) {
        return withFunction(initial);
    }

    public static <A, B, C> Fusion.WithBiFunction<A, B, C> withBiFunction(BiFunction<A, B, C> initial) {
        return Fusion.WithBiFunction.of(initial);
    }

    public static <A, B, C> Fusion.WithBiFunction<A, B, C> with(BiFunction<A, B, C> initial) {
        return withBiFunction(initial);
    }

    public static <A> Fusion.WithToDoubleFunction<A> withToDoubleFunction(ToDoubleFunction<A> initial) {
        return Fusion.WithToDoubleFunction.of(initial);
    }

    public static <A> Fusion.WithToDoubleFunction<A> with(ToDoubleFunction<A> initial) {
        return withToDoubleFunction(initial);
    }

    public static <A> Fusion.WithToIntFunction<A> withToIntFunction(ToIntFunction<A> initial) {
        return Fusion.WithToIntFunction.of(initial);
    }

    public static <A> Fusion.WithToIntFunction<A> with(ToIntFunction<A> initial) {
        return withToIntFunction(initial);
    }

    public static <A> Fusion.WithToLongFunction<A> withToLongFunction(ToLongFunction<A> initial) {
        return Fusion.WithToLongFunction.of(initial);
    }

    public static <A> Fusion.WithToLongFunction<A> with(ToLongFunction<A> initial) {
        return withToLongFunction(initial);
    }

    public static <A> Fusion.WithPredicate<A> withPredicate(Predicate<A> initial) {
        return Fusion.WithPredicate.of(initial);
    }

    public static <A> Fusion.WithPredicate<A> with(Predicate<A> initial) {
        return withPredicate(initial);
    }

    public static <A, B> Fusion.WithBiPredicate<A, B> withBiPredicate(BiPredicate<A, B> initial) {
        return Fusion.WithBiPredicate.of(initial);
    }

    public static <A, B> Fusion.WithBiPredicate<A, B> with(BiPredicate<A, B> initial) {
        return withBiPredicate(initial);
    }

    @Evil
    public static <A> Fusion.WithConsumer<A> withConsumer(Consumer<A> initial) {
        return Fusion.WithConsumer.of(initial);
    }

    @Evil
    public static <A> Fusion.WithConsumer<A> with(Consumer<A> initial) {
        return withConsumer(initial);
    }

    @Evil
    public static <A, B> Fusion.WithBiConsumer<A, B> withBiConsumer(BiConsumer<A, B> initial) {
        return Fusion.WithBiConsumer.of(initial);
    }

    @Evil
    public static <A, B> Fusion.WithBiConsumer<A, B> with(BiConsumer<A, B> initial) {
        return withBiConsumer(initial);
    }

    public static <A> Fusion.WithSupplier<A> withSupplier(Supplier<A> initial) {
        return Fusion.WithSupplier.of(initial);
    }

    public static <A> Fusion.WithSupplier<A> with(Supplier<A> initial) {
        return withSupplier(initial);
    }

    /* TODO: More combinators! */

    /* TODO: Do we need to deeply expose combinators for functional reductions? For example:
        public static <A, B> Continue.WithSup<B> fusing(Continue.WithSup<A> partial, Function<A, B> next) { ... }

        Otherwise, need to check if Continue.WithSup::fusing (and etc) are visible enough to use
     */

    /*
        ┏┓
        ┏━━━━┓
        ┏━━━━━━━━┓
        ┏━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┃                         ┃
        ┃    ┏━╸╻┏━┓┏━┓╻┏━┓┏┓╻    ┃
        ┃    ┣╸ ┃┗━┓┗━┓┃┃ ┃┃┗┫    ┃
        ┃    ╹  ╹┗━┛┗━┛╹┗━┛╹ ╹    ┃
        ┃                         ┃
        ┗━━━━━━━━━━━━━━━━━━━━━━━━━┛
    */

    public static <A, B, C>
        Function<A,
         Function<B, C>>
        split(BiFunction<A, B, C> initial)
    {
        return (A a) -> (B b) ->
            initial.apply(a, b);
    }

    public static <A, B, C, D>
        Function<A,
         Function<B,
          Function<C, D>>>
        split(TriFunction<A, B, C, D> initial)
    {
        return (A a) -> (B b) -> (C c) ->
            initial.apply(a, b, c);
    }

    public static <A, B, C, D, E>
        Function<A,
         Function<B,
          Function<C,
           Function<D, E>>>>
        split(QuadFunction<A, B, C, D, E> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) ->
            initial.apply(a, b, c, d);
    }

    public static <A, B, C, D, E, F>
        Function<A,
         Function<B,
          Function<C,
           Function<D,
            Function<E, F>>>>>
        split(QuinFunction<A, B, C, D, E, F> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) ->
            initial.apply(a, b, c, d, e);
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
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) ->
            initial.apply(a, b, c, d, e, f);
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
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) ->
            initial.apply(a, b, c, d, e, f, g);
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
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) -> (H h) ->
            initial.apply(a, b, c, d, e, f, g, h);
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
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) -> (H h) -> (I i) ->
            initial.apply(a, b, c, d, e, f, g, h, i);
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
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) -> (H h) -> (I i) -> (J j) ->
            initial.apply(a, b, c, d, e, f, g, h, i, j);
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
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) ->
                (G g) -> (H h) -> (I i) -> (J j) -> (K k) ->
                 initial.apply(a, b, c, d, e, f, g, h, i, j, k);
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
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) ->
                (G g) -> (H h) -> (I i) -> (J j) -> (K k) -> (L l) ->
                 initial.apply(a, b, c, d, e, f, g, h, i, j, k, l);
    }

    /*
        ┏┓
        ┏━━━━┓
        ┏━━━━━━━━┓
        ┏━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┃                                      ┃
        ┃    ┏━┓┏━┓┏━┓╻┏┳┓╻╻  ┏━┓╺┳╸╻┏━┓┏┓╻    ┃
        ┃    ┣━┫┗━┓┗━┓┃┃┃┃┃┃  ┣━┫ ┃ ┃┃ ┃┃┗┫    ┃
        ┃    ╹ ╹┗━┛┗━┛╹╹ ╹╹┗━╸╹ ╹ ╹ ╹┗━┛╹ ╹    ┃
        ┃                                      ┃
        ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
    */

    /* Multifunctions */

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

    /*
        ┏┓
        ┏━━━━┓
        ┏━━━━━━━━┓
        ┏━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓
        ┃                                    ┃
        ┃    ┏━┓┏┓ ┏━┓┏━┓┏━┓┏━┓╺┳╸╻┏━┓┏┓╻    ┃
        ┃    ┣━┫┣┻┓┗━┓┃ ┃┣┳┛┣━┛ ┃ ┃┃ ┃┃┗┫    ┃
        ┃    ╹ ╹┗━┛┗━┛┗━┛╹┗╸╹   ╹ ╹┗━┛╹ ╹    ┃
        ┃                                    ┃
        ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
    */

    /* Consumer */

    @Evil
    public static <A, B> Function<A, B> absorb(Consumer<A> initial, Supplier<B> next) {
        return (A a) -> {
            initial.accept(a);
            return next.get();
        };
    }

    @Evil
    public static <A> Predicate<A> absorb(Consumer<A> initial, BooleanSupplier next) {
        return (A a) -> {
            initial.accept(a);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static <A> ToDoubleFunction<A> absorb(Consumer<A> initial, DoubleSupplier next) {
        return (A a) -> {
            initial.accept(a);
            return next.getAsDouble();
        };
    }

    @Evil
    public static <A> ToIntFunction<A> absorb(Consumer<A> initial, IntSupplier next) {
        return (A a) -> {
            initial.accept(a);
            return next.getAsInt();
        };
    }

    @Evil
    public static <A> ToLongFunction<A> absorb(Consumer<A> initial, LongSupplier next) {
        return (A a) -> {
            initial.accept(a);
            return next.getAsLong();
        };
    }

    @Evil
    public static <A> Consumer<A> absorb(Consumer<A> initial, Operator next) {
        return (A a) -> {
            initial.accept(a);
            next.run();
        };
    }

    /* BiConsumer */

    @Evil
    public static <A, B, C> Function<A, Function<B, C>> absorb(BiConsumer<A, B> initial, Supplier<C> next) {
        return (A a) -> (B b) -> {
            initial.accept(a, b);
            return next.get();
        };
    }

    @Evil
    public static <A, B> Function<A, Predicate<B>> absorb(BiConsumer<A, B> initial, BooleanSupplier next) {
        return (A a) -> (B b) -> {
            initial.accept(a, b);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static <A, B> Function<A, ToDoubleFunction<B>> absorb(BiConsumer<A, B> initial, DoubleSupplier next) {
        return (A a) -> (B b) -> {
            initial.accept(a, b);
            return next.getAsDouble();
        };
    }

    @Evil
    public static <A, B> Function<A, ToIntFunction<B>> absorb(BiConsumer<A, B> initial, IntSupplier next) {
        return (A a) -> (B b) -> {
            initial.accept(a, b);
            return next.getAsInt();
        };
    }

    @Evil
    public static <A, B> Function<A, ToLongFunction<B>> absorb(BiConsumer<A, B> initial, LongSupplier next) {
        return (A a) -> (B b) -> {
            initial.accept(a, b);
            return next.getAsLong();
        };
    }

    @Evil
    public static <A, B> Function<A, Consumer<B>> absorb(BiConsumer<A, B> initial, Operator next) {
        return (A a) -> (B b) -> {
            initial.accept(a, b);
            next.run();
        };
    }

    /* DoubleConsumer */

    @Evil
    public static <A> DoubleFunction<A> absorb(DoubleConsumer initial, Supplier<A> next) {
        return (double d) -> {
            initial.accept(d);
            return next.get();
        };
    }

    @Evil
    public static DoublePredicate absorb(DoubleConsumer initial, BooleanSupplier next) {
        return (double d) -> {
            initial.accept(d);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static DoubleUnaryOperator absorb(DoubleConsumer initial, DoubleSupplier next) {
        return (double d) -> {
            initial.accept(d);
            return next.getAsDouble();
        };
    }

    @Evil
    public static DoubleToIntFunction absorb(DoubleConsumer initial, IntSupplier next) {
        return (double d) -> {
            initial.accept(d);
            return next.getAsInt();
        };
    }

    @Evil
    public static DoubleToLongFunction absorb(DoubleConsumer initial, LongSupplier next) {
        return (double d) -> {
            initial.accept(d);
            return next.getAsLong();
        };
    }

    @Evil
    public static DoubleConsumer absorb(DoubleConsumer initial, Operator next) {
        return (double d) -> {
            initial.accept(d);
            next.run();
        };
    }

    /* ObjDoubleConsumer */

    @Evil
    public static <A, B> Function<A, DoubleFunction<B>> absorb(ObjDoubleConsumer<A> initial, Supplier<B> next) {
        return (A a) -> (double d) -> {
            initial.accept(a, d);
            return next.get();
        };
    }

    @Evil
    public static <A> Function<A, DoublePredicate> absorb(ObjDoubleConsumer<A> initial, BooleanSupplier next) {
        return (A a) -> (double d) -> {
            initial.accept(a, d);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static <A> Function<A, DoubleUnaryOperator> absorb(ObjDoubleConsumer<A> initial, DoubleSupplier next) {
        return (A a) -> (double d) -> {
            initial.accept(a, d);
            return next.getAsDouble();
        };
    }

    @Evil
    public static <A> Function<A, DoubleToIntFunction> absorb(ObjDoubleConsumer<A> initial, IntSupplier next) {
        return (A a) -> (double d) -> {
            initial.accept(a, d);
            return next.getAsInt();
        };
    }

    @Evil
    public static <A> Function<A, DoubleToLongFunction> absorb(ObjDoubleConsumer<A> initial, LongSupplier next) {
        return (A a) -> (double d) -> {
            initial.accept(a, d);
            return next.getAsLong();
        };
    }

    @Evil
    public static <A> Function<A, DoubleConsumer> absorb(ObjDoubleConsumer<A> initial, Operator next) {
        return (A a) -> (double d) -> {
            initial.accept(a, d);
            next.run();
        };
    }

    /* IntConsumer */

    @Evil
    public static <A> IntFunction<A> absorb(IntConsumer initial, Supplier<A> next) {
        return (int i) -> {
            initial.accept(i);
            return next.get();
        };
    }

    @Evil
    public static IntPredicate absorb(IntConsumer initial, BooleanSupplier next) {
        return (int i) -> {
            initial.accept(i);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static IntToDoubleFunction absorb(IntConsumer initial, DoubleSupplier next) {
        return (int i) -> {
            initial.accept(i);
            return next.getAsDouble();
        };
    }

    @Evil
    public static IntUnaryOperator absorb(IntConsumer initial, IntSupplier next) {
        return (int i) -> {
            initial.accept(i);
            return next.getAsInt();
        };
    }

    @Evil
    public static IntToLongFunction absorb(IntConsumer initial, LongSupplier next) {
        return (int i) -> {
            initial.accept(i);
            return next.getAsLong();
        };
    }

    @Evil
    public static IntConsumer absorb(IntConsumer initial, Operator next) {
        return (int i) -> {
            initial.accept(i);
            next.run();
        };
    }

    /* ObjIntConsumer */

    @Evil
    public static <A, B> Function<A, IntFunction<B>> absorb(ObjIntConsumer<A> initial, Supplier<B> next) {
        return (A a) -> (int i) -> {
            initial.accept(a, i);
            return next.get();
        };
    }

    @Evil
    public static <A> Function<A, IntPredicate> absorb(ObjIntConsumer<A> initial, BooleanSupplier next) {
        return (A a) -> (int i) -> {
            initial.accept(a, i);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static <A> Function<A, IntToDoubleFunction> absorb(ObjIntConsumer<A> initial, DoubleSupplier next) {
        return (A a) -> (int i) -> {
            initial.accept(a, i);
            return next.getAsDouble();
        };
    }

    @Evil
    public static <A> Function<A, IntUnaryOperator> absorb(ObjIntConsumer<A> initial, IntSupplier next) {
        return (A a) -> (int i) -> {
            initial.accept(a, i);
            return next.getAsInt();
        };
    }

    @Evil
    public static <A> Function<A, IntToLongFunction> absorb(ObjIntConsumer<A> initial, LongSupplier next) {
        return (A a) -> (int i) -> {
            initial.accept(a, i);
            return next.getAsLong();
        };
    }

    @Evil
    public static <A> Function<A, IntConsumer> absorb(ObjIntConsumer<A> initial, Operator next) {
        return (A a) -> (int i) -> {
            initial.accept(a, i);
            next.run();
        };
    }

    /* LongConsumer */

    @Evil
    public static <A> LongFunction<A> absorb(LongConsumer initial, Supplier<A> next) {
        return (long n) -> {
            initial.accept(n);
            return next.get();
        };
    }

    @Evil
    public static LongPredicate absorb(LongConsumer initial, BooleanSupplier next) {
        return (long n) -> {
            initial.accept(n);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static LongToDoubleFunction absorb(LongConsumer initial, DoubleSupplier next) {
        return (long n) -> {
            initial.accept(n);
            return next.getAsDouble();
        };
    }

    @Evil
    public static LongToIntFunction absorb(LongConsumer initial, IntSupplier next) {
        return (long n) -> {
            initial.accept(n);
            return next.getAsInt();
        };
    }

    @Evil
    public static LongUnaryOperator absorb(LongConsumer initial, LongSupplier next) {
        return (long n) -> {
            initial.accept(n);
            return next.getAsLong();
        };
    }

    @Evil
    public static LongConsumer absorb(LongConsumer initial, Operator next) {
        return (long n) -> {
            initial.accept(n);
            next.run();
        };
    }

    /* ObjLongConsumer */

    @Evil
    public static <A, B> Function<A, LongFunction<B>> absorb(ObjLongConsumer<A> initial, Supplier<B> next) {
        return (A a) -> (long n) -> {
            initial.accept(a, n);
            return next.get();
        };
    }

    @Evil
    public static <A> Function<A, LongPredicate> absorb(ObjLongConsumer<A> initial, BooleanSupplier next) {
        return (A a) -> (long n) -> {
            initial.accept(a, n);
            return next.getAsBoolean();
        };
    }

    @Evil
    public static <A> Function<A, LongToDoubleFunction> absorb(ObjLongConsumer<A> initial, DoubleSupplier next) {
        return (A a) -> (long n) -> {
            initial.accept(a, n);
            return next.getAsDouble();
        };
    }

    @Evil
    public static <A> Function<A, LongToIntFunction> absorb(ObjLongConsumer<A> initial, IntSupplier next) {
        return (A a) -> (long n) -> {
            initial.accept(a, n);
            return next.getAsInt();
        };
    }

    @Evil
    public static <A> Function<A, LongUnaryOperator> absorb(ObjLongConsumer<A> initial, LongSupplier next) {
        return (A a) -> (long n) -> {
            initial.accept(a, n);
            return next.getAsLong();
        };
    }

    @Evil
    public static <A> Function<A, LongConsumer> absorb(ObjLongConsumer<A> initial, Operator next) {
        return (A a) -> (long n) -> {
            initial.accept(a, n);
            next.run();
        };
    }

    /* Operator */

    @Evil
    public static <A> Supplier<A> absorb(Operator initial, Supplier<A> next) {
        return () -> {
            initial.run();
            return next.get();
        };
    }

    @Evil
    public static BooleanSupplier absorb(Operator initial, BooleanSupplier next) {
        return () -> {
            initial.run();
            return next.getAsBoolean();
        };
    }

    @Evil
    public static DoubleSupplier absorb(Operator initial, DoubleSupplier next) {
        return () -> {
            initial.run();
            return next.getAsDouble();
        };
    }

    @Evil
    public static IntSupplier absorb(Operator initial, IntSupplier next) {
        return () -> {
            initial.run();
            return next.getAsInt();
        };
    }

    @Evil
    public static LongSupplier absorb(Operator initial, LongSupplier next) {
        return () -> {
            initial.run();
            return next.getAsLong();
        };
    }

    @Evil
    public static Operator absorb(Operator initial, Operator next) {
        return () -> {
            initial.run();
            next.run();
        };
    }
}
