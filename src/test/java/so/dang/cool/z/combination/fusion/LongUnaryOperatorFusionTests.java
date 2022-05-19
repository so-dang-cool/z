package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongUnaryOperatorFusionTests {

    @Test
    void longUnop() {
        assertEquals(4L, addThreeToLong.applyAsLong(1L));

        assertEquals(4L, Z.fuse(addThreeToLong).applyAsLong(1L));
    }

    @Test
    void longUnop_to_longFn() {
        Stream
            .of(
                Z.fuse(addThreeToLong, longToString),
                Z.fuse(addThreeToLong).fuse(longToString),
                Z.fuse(addThreeToLong).fusing(longToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("4", fusion.apply(1L));
                }
            );
    }

    @Test
    void longUnop_to_longToDbl() {
        Stream
            .of(
                Z.fuse(addThreeToLong, longToDouble),
                Z.fuse(addThreeToLong).fuse(longToDouble),
                Z.fuse(addThreeToLong).fusing(longToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(4.0, fusion.applyAsDouble(1L));
                }
            );
    }

    @Test
    void longUnop_to_longToInt() {
        Stream
            .of(
                Z.fuse(addThreeToLong, longToInt),
                Z.fuse(addThreeToLong).fuse(longToInt),
                Z.fuse(addThreeToLong).fusing(longToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.applyAsInt(1L));
                }
            );
    }

    @Test
    void longUnop_to_longPred() {
        Stream
            .of(
                Z.fuse(addThreeToLong, isLongThree),
                Z.fuse(addThreeToLong).fuse(isLongThree),
                Z.fuse(addThreeToLong).fusing(isLongThree)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(0L));
                }
            );
    }

    @Evil
    @Test
    void longUnop_to_longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.fuse(addThreeToLong, saveLongA),
                    Z.fuse(addThreeToLong).fuse(saveLongA),
                    Z.fuse(addThreeToLong).fusing(saveLongA)
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        fusion.accept(2L);

                        assertEquals(5L, consumedLongA);
                    }
                );
        }
    }

    @Test
    void longUnop_to_longUnop() {
        Stream
            .of(
                Z.fuse(addThreeToLong, addThreeToLong),
                Z.fuse(addThreeToLong).fuse(addThreeToLong),
                Z.fuse(addThreeToLong).fusing(addThreeToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(9L, fusion.applyAsLong(3L));
                }
            );
    }

    @Test
    void longUnop_to_longBiop() {
        Stream
            .of(
                Z.fuse(addThreeToLong, addLongs),
                Z.fuse(addThreeToLong).fuse(addLongs),
                Z.fuse(addThreeToLong).fusing(addLongs)
            )
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.apply(1L).applyAsLong(2L));
                }
            );

        Stream
            .of(
                Z.fuse(addThreeToLong).fuse(addLongs, 2L),
                Z.fuse(addThreeToLong).fusing(addLongs, 2L)
            )
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.applyAsLong(1L));
                }
            );
    }
}
