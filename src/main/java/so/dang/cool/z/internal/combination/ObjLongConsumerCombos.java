package so.dang.cool.z.internal.combination;

import java.util.function.Function;
import java.util.function.LongConsumer;

public interface ObjLongConsumerCombos<A> {
    Function<A, LongConsumer> resolve();
}
