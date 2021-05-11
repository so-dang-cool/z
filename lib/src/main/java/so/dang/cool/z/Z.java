package so.dang.cool.z;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
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

import so.dang.cool.z.continuation.FunctionContinuation;
import so.dang.cool.z.continuation.SupplierContinuation;
import so.dang.cool.z.function.Operator;

public class Z {
    public static <A> SupplierContinuation<A> with(A initial) {
        return SupplierContinuation.of(initial);
    }

    /*
        ================
        ┏━╸╻ ╻┏━┓╻┏━┓┏┓╻
        ┣╸ ┃ ┃┗━┓┃┃ ┃┃┗┫
        ╹  ┗━┛┗━┛╹┗━┛╹ ╹
        ================
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

    public static DoubleUnaryOperator fuse(DoubleToLongFunction initial, LongToDoubleFunction next) {
        return (double d) -> next.applyAsDouble(initial.applyAsLong(d));
    }

    public static DoubleToLongFunction fuse(DoubleToLongFunction initial, LongToIntFunction next) {
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

    /* IntFunction [TODO] */
    /* IntToDoubleFunction [TODO] */
    /* IntToLongFunction [TODO] */
    /* ToIntFunction [TODO] */
    /* ToIntBiFunction [TODO] */
    /* LongFunction [TODO] */
    /* LongToDoubleFunction [TODO] */
    /* LongToIntFunction [TODO] */
    /* ToLongFunction [TODO] */
    /* ToLongBiFunction [TODO] */
    /* Predicate [TODO] */
    /* BiPredicate [TODO: INCOMPLETE] */

    public static <A, B, C> Function<A, Function<B, C>> fuse(BiPredicate<A, B> initial, Function<Boolean, C> next) {
        return (A a) -> (B b) -> next.apply(initial.test(a, b));
    }

    public static <A, B> Function<A, Predicate<B>> fuse(BiPredicate<A, B> initial, Predicate<Boolean> next) {
        return (A a) -> (B b) -> next.test(initial.test(a, b));
    }

    public static <A, B> Function<A, Consumer<B>> fuse(BiPredicate<A, B> initial, Consumer<Boolean> next) {
        return (A a) -> (B b) -> next.accept(initial.test(a, b));
    }

    public static <A, B> Function<A, ToDoubleFunction<B>> fuse(BiPredicate<A, B> initial, ToDoubleFunction<Boolean> next) {
        return (A a) -> (B b) -> next.applyAsDouble(initial.test(a, b));
    }

    public static <A, B> Function<A, ToIntFunction<B>> fuse(BiPredicate<A, B> initial, ToIntFunction<Boolean> next) {
        return (A a) -> (B b) -> next.applyAsInt(initial.test(a, b));
    }

    public static <A, B> Function<A, ToLongFunction<B>> fuse(BiPredicate<A, B> initial, ToLongFunction<Boolean> next) {
        return (A a) -> (B b) -> next.applyAsLong(initial.test(a, b));
    }

    /* DoublePredicate [TODO] */
    /* IntPredicate [TODO] */
    /* LongPredicate [TODO] */
    /* Consumer [SKIPPED] TODO: For all consumers... What even is fusion with a consumer? */
    /* BiConsumer [SKIPPED] */
    /* DoubleConsumer [SKIPPED] */
    /* ObjDoubleConsumer [SKIPPED] */
    /* IntConsumer [SKIPPED] */
    /* ObjIntConsumer [SKIPPED] */
    /* LongConsumer [SKIPPED] */
    /* ObjLongConsumer [SKIPPED] */

    /* Supplier [TODO: INCOMPLETE] */

    public static <A, B> Supplier<B> fuse(Supplier<A> initial, Function<A, B> next) {
        return () -> next.apply(initial.get());
    }

    public static <A> BooleanSupplier fuse(Supplier<A> initial, Predicate<A> next) {
        return () -> next.test(initial.get());
    }

    public static <A> Operator fuse(Supplier<A> initial, Consumer<A> next) {
        return () -> next.accept(initial.get());
    }

    /* BooleanSupplier [SKIPPED] Because Supplier<Boolean> matches.
       Unlike double/int/long, there's no auto-boxing optimizations to be had here. */
    /* DoubleSupplier [TODO] */
    /* IntSupplier [TODO] */
    /* LongSupplier [TODO] */
    /* UnaryOperator [TODO] */
    /* BinaryOperator [TODO] */
    /* DoubleUnaryOperator [TODO] */
    /* DoubleBinaryOperator [TODO] */
    /* IntUnaryOperator [TODO] */
    /* IntBinaryOperator [TODO] */
    /* LongUnaryOperator [TODO] */
    /* LongBinaryOperator [TODO] */

    /*
        ==================================
        ┏━┓╻ ╻┏━┓┏━╸┏━┓   ┏━╸╻ ╻┏━┓╻┏━┓┏┓╻
        ┗━┓┃ ┃┣━┛┣╸ ┣┳┛   ┣╸ ┃ ┃┗━┓┃┃ ┃┃┗┫
        ┗━┛┗━┛╹  ┗━╸╹┗╸   ╹  ┗━┛┗━┛╹┗━┛╹ ╹
        ==================================
     */

    public static <A, B, C> FunctionContinuation<A, C> fusing(Function<A, B> initial, Function<B, C> next) {
        return FunctionContinuation.of(initial, next);
    }

    /* BiFunctionContinuationContinuation [TODO] */
    /* DoubleFunctionContinuation [TODO] */
    /* DoubleToIntFunctionContinuation [TODO] */
    /* DoubleToLongFunctionContinuation [TODO] */
    /* ToDoubleFunctionContinuation [TODO] */
    /* ToDoubleBiFunctionContinuation [TODO] */
    /* IntFunctionContinuation [TODO] */
    /* IntToDoubleFunctionContinuation [TODO] */
    /* IntToLongFunctionContinuation [TODO] */
    /* ToIntFunctionContinuation [TODO] */
    /* ToIntBiFunctionContinuation [TODO] */
    /* LongFunctionContinuation [TODO] */
    /* LongToDoubleFunctionContinuation [TODO] */
    /* LongToIntFunctionContinuation [TODO] */
    /* ToLongFunctionContinuation [TODO] */
    /* ToLongBiFunctionContinuation [TODO] */
    /* PredicateContinuation [TODO] */
    /* BiPredicateContinuation [TODO] */
    /* DoublePredicateContinuation [TODO] */
    /* IntPredicateContinuation [TODO] */
    /* LongPredicateContinuation [TODO] */
    /* ConsumerContinuation [TODO] */
    /* BiConsumerContinuation [TODO] */
    /* DoubleConsumerContinuation [TODO] */
    /* ObjDoubleConsumerContinuation [TODO] */
    /* IntConsumerContinuation [TODO] */
    /* ObjIntConsumerContinuation [TODO] */
    /* LongConsumerContinuation [TODO] */
    /* ObjLongConsumerContinuation [TODO] */
    
    public static <A, B> SupplierContinuation<B> fusing(Supplier<A> initial, Function<A, B> next) {
        return SupplierContinuation.of(initial, next);
    }

    /* BooleanSupplierContinuation [TODO] */
    /* DoubleSupplierContinuation [TODO] */
    /* IntSupplierContinuation [TODO] */
    /* LongSupplierContinuation [TODO] */
    /* OperatorContinuation [TODO] */
    /* UnaryOperatorContinuation [TODO] */
    /* BinaryOperatorContinuation [TODO] */
    /* DoubleUnaryOperatorContinuation [TODO] */
    /* DoubleBinaryOperatorContinuation [TODO] */
    /* IntUnaryOperatorContinuation [TODO] */
    /* IntBinaryOperatorContinuation [TODO] */
    /* LongUnaryOperatorContinuation [TODO] */
    /* LongBinaryOperatorContinuation [TODO] */
}
