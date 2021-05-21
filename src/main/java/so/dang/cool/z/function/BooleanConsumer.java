package so.dang.cool.z.function;

/**
 * Represents an operation that accepts a single boolean-valued argument and
 * returns no result. This is a primitive type specialization of
 * {@code Consumer} for boolean. Unlike most other functional interfaces,
 * {@code BooleanConsumer} is expected to operate via side-effects.
 */
@FunctionalInterface
public interface BooleanConsumer {
    /**
     * Performs this operation on the given value.
     */
    void accept(boolean value);

    /**
     * Returns a composed BooleanConsumer that performs, in sequence, this
     * operation followed by the after operation. If performing either
     * operation throws an exception, it is relayed to the caller of the
     * composed operation. If performing this operation throws an exception,
     * the after operation will not be performed.
     * 
     * @param after the operation to perform after this operation
     * @return a composed BooleanConsumer that performs in sequence this
     *         operation followed by the after operation
     */
    default BooleanConsumer andThen​(BooleanConsumer after) {
        return b -> {
            this.accept(b);
            after.accept(b);
        };
    }
}
