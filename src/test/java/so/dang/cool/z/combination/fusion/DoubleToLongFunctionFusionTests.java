package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleToLongFunctionFusionTests {

    @Test
    void dblToLong() {
        assertEquals(1L, doubleToLong.applyAsLong(1.0));

        assertEquals(1L, Z.with(doubleToLong).applyAsLong(1.0));
    }

    @Test
    void dblToLong_to_longFn() {
        Stream
            .of(
                Z.fuse(doubleToLong, longToString),
                Z.with(doubleToLong).fuse(longToString),
                Z.with(doubleToLong).fusing(longToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply(1.6));
                }
            );
    }

    @Test
    void dblToLong_to_longToDbl() {
        Stream
            .of(
                Z.fuse(doubleToLong, longToDouble),
                Z.with(doubleToLong).fuse(longToDouble),
                Z.with(doubleToLong).fusing(longToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(1.7));
                }
            );
    }

    @Test
    void dblToLong_to_longToInt() {
        Stream
            .of(
                Z.fuse(doubleToLong, longToInt),
                Z.with(doubleToLong).fuse(longToInt),
                Z.with(doubleToLong).fusing(longToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(1, fusion.applyAsInt(1.8));
                }
            );
    }

    @Test
    void dblToLong_to_longPred() {
        Stream
            .of(
                Z.fuse(doubleToLong, isLongThree),
                Z.with(doubleToLong).fuse(isLongThree),
                Z.with(doubleToLong).fusing(isLongThree)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(3.1));
                }
            );
    }

    @Evil
    @Test
    void dblToLong_to_longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.fuse(doubleToLong, saveLongA),
                    Z.with(doubleToLong).fuse(saveLongA),
                    Z.with(doubleToLong).fusing(saveLongA)
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        fusion.accept(3.2);

                        assertEquals(3L, consumedLongA);
                    }
                );
        }
    }

    @Test
    void dblToLong_to_longUnop() {
        Stream
            .of(
                Z.fuse(doubleToLong, addThreeToLong),
                Z.with(doubleToLong).fuse(addThreeToLong),
                Z.with(doubleToLong).fusing(addThreeToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong(1.2));
                }
            );
    }

    @Test
    void dblToLong_to_longBiop() {
        Stream
            .of(
                Z.fuse(doubleToLong, addLongs),
                Z.with(doubleToLong).fuse(addLongs)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.apply(1.2).applyAsLong(3L));
                }
            );

        Stream
            .of(
                Z.with(doubleToLong).fuse(addLongs, 3L),
                Z.with(doubleToLong).fusing(addLongs, 3L)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong(1.2));
                }
            );
    }
}
