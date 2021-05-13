package so.dang.cool.z.continuation;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import so.dang.cool.z.Z;

public abstract class Continue<A, Fn, Prev> {
    private Continue() {}

    public abstract Fn resolve();

    /* Function -> ... [TODO: Incomplete] */

    public static final class WithFn<A, B> extends Continue<B, Function<A, B>, Void> {
        private final Function<A, B> initial;
        private WithFn(Function<A, B> initial) {
            this.initial = initial;
        }

        public static <A, B> WithFn<A, B> of(Function<A, B> initial) {
            return new WithFn<>(initial);
        }

        @Override
        public Function<A, B> resolve() {
            return initial;
        }

        /* Function<A, B> -> Function<B, C> */

        public <C> Function<A, C> fuseFn(Function<B, C> next) {
            return Z.fuse(resolve(), next);
        }

        public <C> Function<A, C> fuse(Function<B, C> next) {
            return fuseFn(next);
        }

        public <C> WithFn<A, C> fusingFn(Function<B, C> next) {
            return WithFn.of(fuseFn(next));
        }

        public <C> WithFn<A, C> fusing(Function<B, C> next) {
            return fusingFn(next);
        }

        /* Function<A, B> -> BiFunction<B, C, D> */

        public <C, D> Function<A, Function<C, D>> fuse(BiFunction<B, C, D> next) {
            return Z.fuse(resolve(), next);
        }

        // public <C, D> FnFusingBifn<A, B, C, Void, WithFn<A, B>> fusing(BiFunction<B, C, D> next) {
        //     return FnFusingBifn.of(this, next);
        // }

        /* Function<A, B> -> Predicate<B> */

        public Predicate<A> fusePred(Predicate<B> next) {
            return Z.fuse(resolve(), next);
        }

        public Predicate<A> fuse(Predicate<B> next) {
            return fusePred(next);
        }
    }

    /* Supplier -> ... [TODO: Incomplete]  */

    public static final class WithSup<A> extends Continue<A, Supplier<A>, Void> {
        private final Supplier<A> initial;
        private WithSup(Supplier<A> initial) {
            this.initial = initial;
        }

        public static <A> WithSup<A> of(Supplier<A> initial) {
            return new WithSup<>(initial);
        }

        @Override
        public Supplier<A> resolve() {
            return initial;
        }

        /* Supplier<A> -> Function<A, B> */

        public <B> Supplier<B> fuseFn(Function<A, B> next) {
            return Z.fuse(resolve(), next);
        }

        public <B> Supplier<B> fuse(Function<A, B> next) {
            return fuseFn(next);
        }

        public <B> WithSup<B> fusingFn(Function<A, B> next) {
            return WithSup.of(fuse(next));
        }

        public <B> WithSup<B> fusing(Function<A, B> next) {
            return fusingFn(next);
        }

        /* Supplier<A> -> BiFunction<A, B, C> */

        public <B, C> Function<B, C> fuseBifn(BiFunction<A, B, C> next) {
            return Z.fuse(resolve(), next);
        }

        public <B, C> Function<B, C> fuse(BiFunction<A, B, C> next) {
            return fuseBifn(next);
        }

        public <B, C> WithFn<B, C> fusingBifn(BiFunction<A, B, C> next) {
            return WithFn.of(fuseBifn(next));
        }

        public <B, C> WithFn<B, C> fusing(BiFunction<A, B, C> next) {
            return fusingBifn(next);
        }
    }
}
