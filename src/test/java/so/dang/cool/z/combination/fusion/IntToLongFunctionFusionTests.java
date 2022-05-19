package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntToLongFunctionFusionTests {

    @Test
    void intToLong() {
        assertEquals(2L, intToLong.applyAsLong(2));

        assertEquals(2L, Z.fuse(intToLong).applyAsLong(2));
    }

    @Test
    void intToLong_to_longFn() {
        Stream
            .of(
                Z.fuse(intToLong, longToString),
                Z.fuse(intToLong).fuse(longToString),
                Z.fuse(intToLong).fusing(longToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply(1));
                }
            );
    }

    @Test
    void intToLong_to_longToDbl() {
        Stream
            .of(
                Z.fuse(intToLong, longToDouble),
                Z.fuse(intToLong).fuse(longToDouble),
                Z.fuse(intToLong).fusing(longToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(1));
                }
            );
    }

    @Test
    void intToLong_to_longToInt() {
        Stream
            .of(
                Z.fuse(intToLong, longToInt),
                Z.fuse(intToLong).fuse(longToInt),
                Z.fuse(intToLong).fusing(longToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(1, fusion.applyAsInt(1));
                }
            );
    }

    @Test
    void intToLong_to_longPred() {
        Stream
            .of(
                Z.fuse(intToLong, isLongThree),
                Z.fuse(intToLong).fuse(isLongThree),
                Z.fuse(intToLong).fusing(isLongThree)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(3));
                }
            );
    }

    @Evil
    @Test
    void intToLong_to_longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.fuse(intToLong, saveLongA),
                    Z.fuse(intToLong).fuse(saveLongA),
                    Z.fuse(intToLong).fusing(saveLongA)
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        fusion.accept(3);

                        assertEquals(3L, consumedLongA);
                    }
                );
        }
    }

    @Test
    void intToLong_to_longUnop() {
        Stream
            .of(
                Z.fuse(intToLong, addThreeToLong),
                Z.fuse(intToLong).fuse(addThreeToLong),
                Z.fuse(intToLong).fusing(addThreeToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong(1));
                }
            );
    }

    @Test
    void intToLong_to_longBiop() {
        Stream
            .of(Z.fuse(intToLong, addLongs), Z.fuse(intToLong).fuse(addLongs))
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.apply(1).applyAsLong(3L));
                }
            );

        Stream
            .of(
                Z.fuse(intToLong).fuse(addLongs, 3L),
                Z.fuse(intToLong).fusing(addLongs, 3L)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong(1));
                }
            );
    }
}
