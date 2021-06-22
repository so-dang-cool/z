package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongBinaryOperator;
import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;
import java.util.function.Supplier;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongUnaryOperator;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;

interface LongSupplierCombos {
    public LongSupplier resolve();

    /* LongSupplier -> LongFunction<A> */

    public default <A> Supplier<A> fuseLongFunction(LongFunction<A> next) {
        return () -> next.apply(resolve().getAsLong());
    }

    public default <A> Supplier<A> fuse(LongFunction<A> next) {
        return fuseLongFunction(next);
    }

    public default <A> WithSupplier<A> fusingLongFunction(
        LongFunction<A> next
    ) {
        return WithSupplier.of(fuse(next));
    }

    public default <A> WithSupplier<A> fusing(LongFunction<A> next) {
        return fusingLongFunction(next);
    }

    /* LongSupplier -> LongToDoubleFunction */

    public default DoubleSupplier fuseLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return () -> next.applyAsDouble(resolve().getAsLong());
    }

    public default DoubleSupplier fuse(LongToDoubleFunction next) {
        return fuseLongToDoubleFunction(next);
    }

    public default WithDoubleSupplier fusingLongToDoubleFunction(
        LongToDoubleFunction next
    ) {
        return WithDoubleSupplier.of(fuse(next));
    }

    public default WithDoubleSupplier fusing(LongToDoubleFunction next) {
        return fusingLongToDoubleFunction(next);
    }

    /* LongSupplier -> LongToIntFunction */

    public default IntSupplier fuseLongToIntFunction(LongToIntFunction next) {
        return () -> next.applyAsInt(resolve().getAsLong());
    }

    public default IntSupplier fuse(LongToIntFunction next) {
        return fuseLongToIntFunction(next);
    }

    public default WithIntSupplier fusingLongToIntFunction(
        LongToIntFunction next
    ) {
        return WithIntSupplier.of(fuse(next));
    }

    public default WithIntSupplier fusing(LongToIntFunction next) {
        return fusingLongToIntFunction(next);
    }

    /* LongSupplier -> LongPredicate */

    public default BooleanSupplier fuseLongPredicate(LongPredicate next) {
        return () -> next.test(resolve().getAsLong());
    }

    public default BooleanSupplier fuse(LongPredicate next) {
        return fuseLongPredicate(next);
    }

    public default WithBooleanSupplier fusingLongPredicate(LongPredicate next) {
        return WithBooleanSupplier.of(fuse(next));
    }

    public default WithBooleanSupplier fusing(LongPredicate next) {
        return fusingLongPredicate(next);
    }

    /* LongSupplier -> LongConsumer */

    public default Operator fuseLongConsumer(LongConsumer next) {
        return () -> next.accept(resolve().getAsLong());
    }

    public default Operator fuse(LongConsumer next) {
        return fuseLongConsumer(next);
    }

    public default WithOperator fusingLongConsumer(LongConsumer next) {
        return WithOperator.of(fuse(next));
    }

    public default WithOperator fusing(LongConsumer next) {
        return fusingLongConsumer(next);
    }

    /* LongSupplier -> LongUnaryOperator */

    public default LongSupplier fuseLongUnaryOperator(LongUnaryOperator next) {
        return () -> next.applyAsLong(resolve().getAsLong());
    }

    public default LongSupplier fuse(LongUnaryOperator next) {
        return fuseLongUnaryOperator(next);
    }

    public default WithLongSupplier fusingLongUnaryOperator(
        LongUnaryOperator next
    ) {
        return WithLongSupplier.of(fuse(next));
    }

    public default WithLongSupplier fusing(LongUnaryOperator next) {
        return fusingLongUnaryOperator(next);
    }

    /* LongSupplier -> LongBinaryOperator */

    public default LongUnaryOperator fuseLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return (long n) -> next.applyAsLong(resolve().getAsLong(), n);
    }

    public default LongUnaryOperator fuse(LongBinaryOperator next) {
        return fuseLongBinaryOperator(next);
    }

    public default WithLongUnaryOperator fusingLongBinaryOperator(
        LongBinaryOperator next
    ) {
        return WithLongUnaryOperator.of(fuse(next));
    }

    public default WithLongUnaryOperator fusing(LongBinaryOperator next) {
        return fusingLongBinaryOperator(next);
    }
}
