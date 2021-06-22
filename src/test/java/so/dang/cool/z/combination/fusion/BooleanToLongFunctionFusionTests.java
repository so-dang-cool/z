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

        assertEquals(3L, Z.with(maybeThreeAsLong).resolve().applyAsLong(true));
    }

    @Test
    void boolToLong_to_longFn() {
        Stream
            .of(
                Z.fuse(maybeThreeAsLong, longToString),
                Z.with(maybeThreeAsLong).fuse(longToString),
                Z.with(maybeThreeAsLong).fusing(longToString).resolve()
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
                Z.with(maybeThreeAsLong).fuse(longToDouble),
                Z.with(maybeThreeAsLong).fusing(longToDouble).resolve()
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
                Z.with(maybeThreeAsLong).fuse(longToInt),
                Z.with(maybeThreeAsLong).fusing(longToInt).resolve()
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
                Z.with(maybeThreeAsLong).fuse(isLongThree),
                Z.with(maybeThreeAsLong).fusing(isLongThree).resolve()
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
                    Z.with(maybeThreeAsLong).fuse(saveLongA),
                    Z.with(maybeThreeAsLong).fusing(saveLongA).resolve()
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
                Z.with(maybeThreeAsLong).fuse(addThreeToLong),
                Z.with(maybeThreeAsLong).fusing(addThreeToLong).resolve()
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
                Z.with(maybeThreeAsLong).fuse(addLongs)
            )
            .forEach(
                fusion -> {
                    assertEquals(7L, fusion.apply(true).applyAsLong(4L));
                }
            );

        Stream
            .of(
                Z.with(maybeThreeAsLong).fuse(addLongs, 4L),
                Z.with(maybeThreeAsLong).fusing(addLongs, 4L).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(7L, fusion.applyAsLong(true));
                }
            );
    }
}
