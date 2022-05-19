package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanToLongFunctionFusionTests {

    @Test
    void boolToLong() {
        assertEquals(3L, maybeThreeAsLong.applyAsLong(true));

        assertEquals(3L, Z.fuse(maybeThreeAsLong).applyAsLong(true));
    }

    @Test
    void boolToLong_to_longFn() {
        Stream
            .of(
                Z.fuse(maybeThreeAsLong, longToString),
                Z.fuse(maybeThreeAsLong).fuse(longToString),
                Z.fuse(maybeThreeAsLong).fusing(longToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("3", fusion.apply(true));
                }
            );
    }

    @Test
    void boolToLong_to_longToDbl() {
        Stream
            .of(
                Z.fuse(maybeThreeAsLong, longToDouble),
                Z.fuse(maybeThreeAsLong).fuse(longToDouble),
                Z.fuse(maybeThreeAsLong).fusing(longToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.applyAsDouble(true));
                }
            );
    }

    @Test
    void boolToLong_to_longToInt() {
        Stream
            .of(
                Z.fuse(maybeThreeAsLong, longToInt),
                Z.fuse(maybeThreeAsLong).fuse(longToInt),
                Z.fuse(maybeThreeAsLong).fusing(longToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.applyAsInt(true));
                }
            );
    }

    @Test
    void boolToLong_to_longPred() {
        Stream
            .of(
                Z.fuse(maybeThreeAsLong, isLongThree),
                Z.fuse(maybeThreeAsLong).fuse(isLongThree),
                Z.fuse(maybeThreeAsLong).fusing(isLongThree)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(true));
                }
            );
    }

    @Evil
    @Test
    void boolToLong_to_longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.fuse(maybeThreeAsLong, saveLongA),
                    Z.fuse(maybeThreeAsLong).fuse(saveLongA),
                    Z.fuse(maybeThreeAsLong).fusing(saveLongA)
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        fusion.accept(true);

                        assertEquals(3L, consumedLongA);
                    }
                );
        }
    }

    @Test
    void boolToLong_to_longUnop() {
        Stream
            .of(
                Z.fuse(maybeThreeAsLong, addThreeToLong),
                Z.fuse(maybeThreeAsLong).fuse(addThreeToLong),
                Z.fuse(maybeThreeAsLong).fusing(addThreeToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.applyAsLong(true));
                }
            );
    }

    @Test
    void boolToLong_to_longBiop() {
        Stream
            .of(
                Z.fuse(maybeThreeAsLong, addLongs),
                Z.fuse(maybeThreeAsLong).fuse(addLongs)
            )
            .forEach(
                fusion -> {
                    assertEquals(7L, fusion.apply(true).applyAsLong(4L));
                }
            );

        Stream
            .of(
                Z.fuse(maybeThreeAsLong).fuse(addLongs, 4L),
                Z.fuse(maybeThreeAsLong).fusing(addLongs, 4L)
            )
            .forEach(
                fusion -> {
                    assertEquals(7L, fusion.applyAsLong(true));
                }
            );
    }
}
