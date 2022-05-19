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

        assertEquals(1L, Z.fuse(doubleToLong).applyAsLong(1.0));
    }

    @Test
    void dblToLong_to_longFn() {
        Stream
            .of(
                Z.fuse(doubleToLong, longToString),
                Z.fuse(doubleToLong).fuse(longToString),
                Z.fuse(doubleToLong).fusing(longToString)
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
                Z.fuse(doubleToLong).fuse(longToDouble),
                Z.fuse(doubleToLong).fusing(longToDouble)
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
                Z.fuse(doubleToLong).fuse(longToInt),
                Z.fuse(doubleToLong).fusing(longToInt)
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
                Z.fuse(doubleToLong).fuse(isLongThree),
                Z.fuse(doubleToLong).fusing(isLongThree)
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
                    Z.fuse(doubleToLong).fuse(saveLongA),
                    Z.fuse(doubleToLong).fusing(saveLongA)
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
                Z.fuse(doubleToLong).fuse(addThreeToLong),
                Z.fuse(doubleToLong).fusing(addThreeToLong)
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
                Z.fuse(doubleToLong).fuse(addLongs)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.apply(1.2).applyAsLong(3L));
                }
            );

        Stream
            .of(
                Z.fuse(doubleToLong).fuse(addLongs, 3L),
                Z.fuse(doubleToLong).fusing(addLongs, 3L)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong(1.2));
                }
            );
    }
}
