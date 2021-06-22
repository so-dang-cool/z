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

        assertEquals(4L, Z.with(addThreeToLong).resolve().applyAsLong(1L));
    }

    @Test
    void longUnop_to_longFn() {
        Stream
            .of(
                Z.fuse(addThreeToLong, longToString),
                Z.with(addThreeToLong).fuse(longToString),
                Z.with(addThreeToLong).fusing(longToString).resolve()
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
                Z.with(addThreeToLong).fuse(longToDouble),
                Z.with(addThreeToLong).fusing(longToDouble).resolve()
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
                Z.with(addThreeToLong).fuse(longToInt),
                Z.with(addThreeToLong).fusing(longToInt).resolve()
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
                Z.with(addThreeToLong).fuse(isLongThree),
                Z.with(addThreeToLong).fusing(isLongThree).resolve()
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
                    Z.with(addThreeToLong).fuse(saveLongA),
                    Z.with(addThreeToLong).fusing(saveLongA).resolve()
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
                Z.with(addThreeToLong).fuse(addThreeToLong),
                Z.with(addThreeToLong).fusing(addThreeToLong).resolve()
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
                Z.with(addThreeToLong).fuse(addLongs),
                Z.with(addThreeToLong).fusing(addLongs).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.apply(1L).applyAsLong(2L));
                }
            );

        Stream
            .of(
                Z.with(addThreeToLong).fuse(addLongs, 2L),
                Z.with(addThreeToLong).fusing(addLongs, 2L).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.applyAsLong(1L));
                }
            );
    }
}
