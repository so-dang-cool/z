package so.dang.cool.z.internal.combination;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import so.dang.cool.z.function.BooleanConsumer;
import so.dang.cool.z.function.BooleanFunction;
import so.dang.cool.z.function.BooleanPredicate;
import so.dang.cool.z.function.BooleanToDoubleFunction;
import so.dang.cool.z.function.BooleanToIntFunction;
import so.dang.cool.z.function.BooleanToLongFunction;
import so.dang.cool.z.function.Operator;
import so.dang.cool.z.internal.combination.Combine.WithBooleanSupplier;
import so.dang.cool.z.internal.combination.Combine.WithDoubleSupplier;
import so.dang.cool.z.internal.combination.Combine.WithIntSupplier;
import so.dang.cool.z.internal.combination.Combine.WithLongSupplier;
import so.dang.cool.z.internal.combination.Combine.WithOperator;
import so.dang.cool.z.internal.combination.Combine.WithSupplier;

interface BooleanSupplierCombos {
    public BooleanSupplier resolve();

    /* BooleanSupplier -> BooleanFunction<A> */

    public default <A> Supplier<A> fuseBooleanFunction(
        BooleanFunction<A> next
    ) {
        return () -> next.apply(resolve().getAsBoolean());
    }

    public default <A> Supplier<A> fuse(BooleanFunction<A> next) {
        return fuseBooleanFunction(next);
    }

    public default <A> WithSupplier<A> fusingBooleanFunction(
        BooleanFunction<A> next
    ) {
        return WithSupplier.of(fuseBooleanFunction(next));
    }

    public default <A> WithSupplier<A> fusing(BooleanFunction<A> next) {
        return fusingBooleanFunction(next);
    }

    /* BooleanSupplier -> BooleanToDoubleFunction */

    public default DoubleSupplier fuseBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return () -> next.applyAsDouble(resolve().getAsBoolean());
    }

    public default DoubleSupplier fuse(BooleanToDoubleFunction next) {
        return fuseBooleanToDoubleFunction(next);
    }

    public default WithDoubleSupplier fusingBooleanToDoubleFunction(
        BooleanToDoubleFunction next
    ) {
        return WithDoubleSupplier.of(fuseBooleanToDoubleFunction(next));
    }

    public default WithDoubleSupplier fusing(BooleanToDoubleFunction next) {
        return fusingBooleanToDoubleFunction(next);
    }

    /* BooleanSupplier -> BooleanToIntFunction */

    public default IntSupplier fuseBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return () -> next.applyAsInt(resolve().getAsBoolean());
    }

    public default IntSupplier fuse(BooleanToIntFunction next) {
        return fuseBooleanToIntFunction(next);
    }

    public default WithIntSupplier fusingBooleanToIntFunction(
        BooleanToIntFunction next
    ) {
        return WithIntSupplier.of(fuseBooleanToIntFunction(next));
    }

    public default WithIntSupplier fusing(BooleanToIntFunction next) {
        return fusingBooleanToIntFunction(next);
    }

    /* BooleanSupplier -> BooleanToLongFunction */

    public default LongSupplier fuseBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return () -> next.applyAsLong(resolve().getAsBoolean());
    }

    public default LongSupplier fuse(BooleanToLongFunction next) {
        return fuseBooleanToLongFunction(next);
    }

    public default WithLongSupplier fusingBooleanToLongFunction(
        BooleanToLongFunction next
    ) {
        return WithLongSupplier.of(fuse(next));
    }

    public default WithLongSupplier fusing(BooleanToLongFunction next) {
        return fusingBooleanToLongFunction(next);
    }

    /* BooleanSupplier -> BooleanPredicate */

    public default BooleanSupplier fuseBooleanPredicate(BooleanPredicate next) {
        return () -> next.test(resolve().getAsBoolean());
    }

    public default BooleanSupplier fuse(BooleanPredicate next) {
        return fuseBooleanPredicate(next);
    }

    public default WithBooleanSupplier fusingBooleanPredicate(
        BooleanPredicate next
    ) {
        return WithBooleanSupplier.of(fuse(next));
    }

    public default WithBooleanSupplier fusing(BooleanPredicate next) {
        return fusingBooleanPredicate(next);
    }

    /* BooleanSupplier -> BooleanConsumer */

    public default Operator fuseBiPredicate(BooleanConsumer next) {
        return () -> next.accept(resolve().getAsBoolean());
    }

    public default Operator fuse(BooleanConsumer next) {
        return fuseBiPredicate(next);
    }

    public default WithOperator fusingBiPredicate(BooleanConsumer next) {
        return WithOperator.of(fuseBiPredicate(next));
    }

    public default WithOperator fusing(BooleanConsumer next) {
        return fusingBiPredicate(next);
    }
}
