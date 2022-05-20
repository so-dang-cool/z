package so.dang.cool.z.internal.combination;

/**
 * Indicates functions that promote primitives to "boxed" values.
 */
public @interface PromotesPrimitive {
    Class<?>[] promoted();
}
