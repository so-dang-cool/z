package so.dang.cool.z;

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

abstract class Fusion {
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
}
