package so.dang.cool.z.function;

/**
 * Represents a predicate (boolean-valued function) of one boolean-valued
 * argument. This is a boolean-consuming primitive type specialization of
 * Predicate. 
 */
@FunctionalInterface
public interface BooleanPredicate {
    public static final BooleanPredicate ID = b -> b;
    public static final BooleanPredicate NOT = b -> !b;

    /**
     * Evaluates this predicate on the given value.
     */
    boolean test(boolean value);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.
     */
    default BooleanPredicate and(BooleanPredicate other) {
        return value -> this.test(value) && other.test(value);
    }

    /**
     * Returns a predicate that represents the logical negation of this predicate.
     */
    default BooleanPredicate negate() {
        return value -> !this.test(value);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.
     */
    default BooleanPredicate or(BooleanPredicate other) {
        return value -> this.test(value) || other.test(value);
    }
}
