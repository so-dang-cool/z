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
            .of(Z.fuse(intToLong).fuse(longToString))
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply(1));
                }
            );
    }

    @Test
    void intToLong_to_longToDbl() {
        Stream
            .of(Z.fuse(intToLong).fuse(longToDouble))
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(1));
                }
            );
    }

    @Test
    void intToLong_to_longToInt() {
        Stream
            .of(Z.fuse(intToLong).fuse(longToInt))
            .forEach(
                fusion -> {
                    assertEquals(1, fusion.applyAsInt(1));
                }
            );
    }

    @Test
    void intToLong_to_longPred() {
        Stream
            .of(Z.fuse(intToLong).fuse(isLongThree))
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
                .of(Z.fuse(intToLong).fuse(saveLongA))
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
            .of(Z.fuse(intToLong).fuse(addThreeToLong))
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong(1));
                }
            );
    }

    @Test
    void intToLong_to_longBiop() {
        assertEquals(
            4L,
            Z.fuse(intToLong).fuse(addLongs).apply(1).applyAsLong(3L)
        );
    }
}
