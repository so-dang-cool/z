package so.dang.cool.z.internal.combination;

import java.util.function.DoubleConsumer;
import java.util.function.Function;

public interface ObjDoubleConsumerCombos<A> {
    Function<A, DoubleConsumer> resolve();
}
