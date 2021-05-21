package so.dang.cool.z.function;

import so.dang.cool.z.annotation.Evil;

/**
 * Represents a function that accepts no arguments and produces no result.
 * Unlike most other functional interfaces, {@code Operator} is expected to
 * operate via side-effects.
 */
@Evil
@FunctionalInterface
public interface Operator {
    /**
     * Runs this operation.
     */
    void run();
}
