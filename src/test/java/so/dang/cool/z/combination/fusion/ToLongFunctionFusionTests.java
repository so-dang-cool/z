package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToLongFunctionFusionTests {

    @Test
    void toLongFn() {
        assertEquals(3L, stringToLong.applyAsLong("3"));

        assertEquals(3L, Z.with(stringToLong).applyAsLong("3"));
    }

    @Test
    void toLongFn_to_longFn() {
        Stream
            .of(
                Z.fuse(stringToLong, longToString),
                Z.with(stringToLong).fuse(longToString),
                Z.with(stringToLong).fusing(longToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply("1"));
                }
            );
    }

    @Test
    void toLongFn_to_longToDbl() {
        Stream
            .of(
                Z.fuse(stringToLong, longToDouble),
                Z.with(stringToLong).fuse(longToDouble),
                Z.with(stringToLong).fusing(longToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble("1"));
                }
            );
    }

    @Test
    void toLongFn_to_longToInt() {
        Stream
            .of(
                Z.fuse(stringToLong, longToInt),
                Z.with(stringToLong).fuse(longToInt),
                Z.with(stringToLong).fusing(longToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(1L, fusion.applyAsInt("1"));
                }
            );
    }

    @Test
    void toLongFn_to_longPred() {
        Stream
            .of(
                Z.fuse(stringToLong, isLongThree),
                Z.with(stringToLong).fuse(isLongThree),
                Z.with(stringToLong).fusing(isLongThree)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test("3"));
                }
            );
    }

    @Evil
    @Test
    void toLongFn_to_longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(
                    Z.fuse(stringToLong, saveLongA),
                    Z.with(stringToLong).fuse(saveLongA),
                    Z.with(stringToLong).fusing(saveLongA)
                )
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        fusion.accept("5");

                        assertEquals(5L, consumedLongA);
                    }
                );
        }
    }

    @Test
    void toLongFn_to_longUnop() {
        Stream
            .of(
                Z.fuse(stringToLong, addThreeToLong),
                Z.with(stringToLong).fuse(addThreeToLong),
                Z.with(stringToLong).fusing(addThreeToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(9L, fusion.applyAsLong("6"));
                }
            );
    }

    @Test
    void toLongFn_to_longBiop() {
        Stream
            .of(
                Z.fuse(stringToLong, addLongs),
                Z.with(stringToLong).fuse(addLongs)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.apply("1").applyAsLong(2L));
                }
            );

        Stream
            .of(
                Z.with(stringToLong).fuse(addLongs, 2L),
                Z.with(stringToLong).fusing(addLongs, 2L)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong("1"));
                }
            );
    }
}
