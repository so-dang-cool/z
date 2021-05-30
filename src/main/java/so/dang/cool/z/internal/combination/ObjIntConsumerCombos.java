package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.IntConsumer;

interface ObjIntConsumerCombos<A> {
    Function<A, IntConsumer> resolve();
}
