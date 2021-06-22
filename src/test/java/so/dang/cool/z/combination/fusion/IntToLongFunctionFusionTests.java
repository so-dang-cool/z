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

        assertEquals(2L, Z.with(intToLong).resolve().applyAsLong(2));
    }

    @Test
    void intToLong_to_longFn() {
        Stream
            .of(
                Z.fuse(intToLong, longToString),
                Z.with(intToLong).fuse(longToString),
                Z.with(intToLong).fusing(longToString).resolve()
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
                Z.with(intToLong).fuse(longToDouble),
                Z.with(intToLong).fusing(longToDouble).resolve()
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
                Z.with(intToLong).fuse(longToInt),
                Z.with(intToLong).fusing(longToInt).resolve()
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
                Z.with(intToLong).fuse(isLongThree),
                Z.with(intToLong).fusing(isLongThree).resolve()
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
                    Z.with(intToLong).fuse(saveLongA),
                    Z.with(intToLong).fusing(saveLongA).resolve()
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
                Z.with(intToLong).fuse(addThreeToLong),
                Z.with(intToLong).fusing(addThreeToLong).resolve()
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
            .of(Z.fuse(intToLong, addLongs), Z.with(intToLong).fuse(addLongs))
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.apply(1).applyAsLong(3L));
                }
            );

        Stream
            .of(
                Z.with(intToLong).fuse(addLongs, 3L),
                Z.with(intToLong).fusing(addLongs, 3L).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong(1));
                }
            );
    }
}
