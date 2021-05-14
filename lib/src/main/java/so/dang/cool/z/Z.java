package so.dang.cool.z;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoubleSupplier;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.function.UnaryOperator;

import so.dang.cool.z.annotation.Evil;
import so.dang.cool.z.continuation.Continue;
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

public final class Z extends Fusion {
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

    public static <A> Continue.WithFn<A, A> withClass(Class<A> clazz) {
        return Continue.WithFn.of(Z.id(clazz));
    }

    public static <A> Continue.WithFn<A, A> with(Class<A> clazz) {
        return withClass(clazz);
    }

    public static <A> Continue.WithSup<A> withObj(A initial) {
        return Continue.WithSup.of(() -> initial);
    }

    public static <A> Continue.WithSup<A> with(A initial) {
        return withObj(initial);
    }

    public static <A, B> Continue.WithFn<A, B> withFn(Function<A, B> initial) {
        return Continue.WithFn.of(initial);
    }

    public static <A, B> Continue.WithFn<A, B> with(Function<A, B> initial) {
        return withFn(initial);
    }

    public static <A, B, C> Continue.WithBifn<A, B, C> withBifn(BiFunction<A, B, C> initial) {
        return Continue.WithBifn.of(initial);
    }

    public static <A, B, C> Continue.WithBifn<A, B, C> with(BiFunction<A, B, C> initial) {
        return withBifn(initial);
    }

    public static <A> Continue.WithPred<A> withPred(Predicate<A> initial) {
        return Continue.WithPred.of(initial);
    }

    public static <A> Continue.WithPred<A> with(Predicate<A> initial) {
        return withPred(initial);
    }

    public static <A> Continue.WithSup<A> withSup(Supplier<A> initial) {
        return Continue.WithSup.of(initial);
    }

    public static <A> Continue.WithSup<A> with(Supplier<A> initial) {
        return withSup(initial);
    }

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
        Function<A, Function<B, C>>
        split(BiFunction<A, B, C> initial)
    {
        return (A a) -> (B b) -> initial.apply(a, b);
    }

    public static <A, B, C, D>
        Function<A, Function<B, Function<C, D>>>
        split(TriFunction<A, B, C, D> initial)
    {
        return (A a) -> (B b) -> (C c) -> initial.apply(a, b, c);
    }

    public static <A, B, C, D, E>
        Function<A, Function<B, Function<C, Function<D, E>>>>
        split(QuadFunction<A, B, C, D, E> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> initial.apply(a, b, c, d);
    }

    public static <A, B, C, D, E, F>
        Function<A, Function<B, Function<C, Function<D, Function<E, F>>>>>
        split(QuinFunction<A, B, C, D, E, F> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> initial.apply(a, b, c, d, e);
    }

    public static <A, B, C, D, E, F, G>
        Function<A, Function<B, Function<C, Function<D, Function<E, Function<F, G>>>>>>
        split(SexFunction<A, B, C, D, E, F, G> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) ->
            initial.apply(a, b, c, d, e, f);
    }

    public static <A, B, C, D, E, F, G, H>
        Function<A, Function<B, Function<C, Function<D, Function<E, Function<F, Function<G, H>>>>>>>
        split(SeptFunction<A, B, C, D, E, F, G, H> initial)
    {
        return (A a) -> (B b) -> (C c) -> (D d) -> (E e) -> (F f) -> (G g) ->
            initial.apply(a, b, c, d, e, f, g);
    }

    public static <A, B, C, D, E, F, G, H, I>
        Function<A, Function<B, Function<C, Function<D, Function<E, Function<F, Function<G, Function<H, I>>>>>>>>
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
        return (A a) ->
         (B b) ->
          (C c) ->
           (D d) ->
            (E e) ->
             (F f) ->
              (G g) ->
               (H h) ->
                (I i) ->
                 (J j) ->
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
        return (A a) ->
         (B b) ->
          (C c) ->
           (D d) ->
            (E e) ->
             (F f) ->
              (G g) ->
               (H h) ->
                (I i) ->
                 (J j) ->
                  (K k) ->
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
        return (A a) ->
         (B b) ->
          (C c) ->
           (D d) ->
            (E e) ->
             (F f) ->
              (G g) ->
               (H h) ->
                (I i) ->
                 (J j) ->
                  (K k) ->
                   (L l) ->
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
        assimilate2(Function<A, Function<B, C>> curried)
    {
        return (A a, B b) -> curried.apply(a).apply(b); 
    }

    @Evil
    public static <A, B, C, D>
        TriFunction<A, B, C, D>
        assimilate3(Function<A, Function<B, Function<C, D>>> curried)
    {
        return (A a, B b, C c) -> curried.apply(a).apply(b).apply(c); 
    }

    @Evil
    public static <A, B, C, D, E>
        QuadFunction<A, B, C, D, E>
        assimilate4(Function<A, Function<B, Function<C, Function<D, E>>>> curried)
    {
        return (A a, B b, C c, D d) -> curried.apply(a).apply(b).apply(c).apply(d); 
    }

    @Evil
    public static <A, B, C, D, E, F>
        QuinFunction<A, B, C, D, E, F>
        assimilate5(Function<A, Function<B, Function<C, Function<D, Function<E, F>>>>> curried)
    {
        return (A a, B b, C c, D d, E e) -> curried.apply(a).apply(b).apply(c).apply(d).apply(e);
    }

    @Evil
    public static <A, B, C, D, E, F, G>
        SexFunction<A, B, C, D, E, F, G>
        assimilate6(Function<A, Function<B, Function<C, Function<D, Function<E, Function<F, G>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f) -> curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f);
    }

    @Evil
    public static <A, B, C, D, E, F, G, H>
        SeptFunction<A, B, C, D, E, F, G, H>
        assimilate7(Function<A, Function<B, Function<C, Function<D, Function<E, Function<F, Function<G, H>>>>>>> curried)
    {
        return (A a, B b, C c, D d, E e, F f, G g) -> curried.apply(a).apply(b).apply(c).apply(d).apply(e).apply(f).apply(g);
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
