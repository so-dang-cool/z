package so.dang.cool.z.internal.combination;

import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithLongBinaryOperator;

interface LongBinaryOperatorCombos {
    LongFunction<LongUnaryOperator> resolve();

    /* LongBinaryOperator -> LongFunction<A> */

    public default <A> LongFunction<LongFunction<A>> fuseLongFunction(
        LongFunction<A> next
    ) {
        return (long n1) ->
            (long n2) -> next.apply(resolve().apply(n1).applyAsLong(n2));
    }

    public default <A> LongFunction<LongFunction<A>> fuse(
        LongFunction<A> next
    ) {
        return fuseLongFunction(next);
    }

    // TODO: Implement with currying
    // fusingLongFunction(LongFunction<A> next) { ... }

    /* LongBinaryOperator -> LongToIntFunction */

    public default LongFunction<LongToIntFunction> fuseLongToIntFunction(
        LongToIntFunction next
    ) {
        return (long n1) ->
            (long n2) -> next.applyAsInt(resolve().apply(n1).applyAsLong(n2));
    }

    public default LongFunction<LongToIntFunction> fuse(
        LongToIntFunction next
    ) {
        return fuseLongToIntFunction(next);
    }

    // TODO: Implement with currying
    // fusingLongToIntFunction(LongToIntFunction next) { ... }

    /* LongBinaryOperator -> LongToDoubleFunction */

    public default LongFunction<LongToDoubleFunction> fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return (long n1) ->
            (long n2) ->
                next.applyAsDouble(resolve().apply(n1).applyAsLong(n2));
    }

    public default LongFunction<LongToDoubleFunction> fuse(
        LongToDoubleFunction next
    ) {
        return fuseLongToDoubleFunction(next);
    }

    // TODO: Implement with currying
    // fusingLongToDoubleFunction(LongToDoubleFunction next) { ... }

    /* LongBinaryOperator -> LongPredicate */

    public default LongFunction<LongPredicate> fuseLongPredicate(
        LongPredicate next
    ) {
        return (long n1) ->
            (long n2) -> next.test(resolve().apply(n1).applyAsLong(n2));
    }

    public default LongFunction<LongPredicate> fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    // TODO: Implement with currying
    // fusingLongPredicate(LongPredicate next) { ... }

    /* LongBinaryOperator -> LongConsumer */

    public default LongFunction<LongConsumer> fuseLongConsumer(
        LongConsumer next
    ) {
        return (long n1) ->
            (long n2) -> next.accept(resolve().apply(n1).applyAsLong(n2));
    }

    public default LongFunction<LongConsumer> fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    // TODO: Implement with currying
    // fusingLongConsumer(LongConsumer next) { ... }

    /* LongBinaryOperator -> LongUnaryOperator */

    public default LongFunction<LongUnaryOperator> fuseLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return (long n1) ->
            (long n2) -> next.applyAsLong(resolve().apply(n1).applyAsLong(n2));
    }

    public default LongFunction<LongUnaryOperator> fuse(
        LongUnaryOperator next
    ) {
        return fuseLongUnaryOperator(next);
    }

    public default WithLongBinaryOperator fusingLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithLongBinaryOperator.of(fuseLongUnaryOperator(next));
    }

    public default WithLongBinaryOperator fusing(LongUnaryOperator next) {
        return fusingLongUnaryOperator(next);
    }

    /* LongBinaryOperator -> LongBinaryOperator */

    public default LongFunction<LongFunction<LongUnaryOperator>> fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (long n1) ->
            (long n2) ->
                (long d3) ->
                    next.applyAsLong(resolve().apply(n1).applyAsLong(n2), d3);
    }

    public default LongFunction<LongFunction<LongUnaryOperator>> fuse(
        LongBinaryOperator next
    ) {
        return fuseLongBinaryOperator(next);
    }

    // TODO: Implement with currying
    // fusingLongBinaryOperator(LongBinaryOperator next) { ... }

    public default LongFunction<LongUnaryOperator> fuseLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return (long n1) ->
            (long n2) ->
                next.applyAsLong(
                    resolve().apply(n1).applyAsLong(n2),
                    secondArg
                );
    }

    public default LongFunction<LongUnaryOperator> fuse(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fuseLongBinaryOperator(next, secondArg);
    }

    public default WithLongBinaryOperator fusingLongBinaryOperator(
        LongBinaryOperator next,
        long secondArg
    ) {
        return WithLongBinaryOperator.of(
            fuseLongBinaryOperator(next, secondArg)
        );
    }

    public default WithLongBinaryOperator fusing(
        LongBinaryOperator next,
        long secondArg
    ) {
        return fusingLongBinaryOperator(next, secondArg);
    }
}
