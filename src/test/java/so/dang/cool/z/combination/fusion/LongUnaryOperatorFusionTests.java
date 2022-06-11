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
            .of(Z.fuse(addThreeToLong).fuse(longToString))
            .forEach(
                fusion -> {
                    assertEquals("4", fusion.apply(1L));
                }
            );
    }

    @Test
    void longUnop_to_longToDbl() {
        Stream
            .of(Z.fuse(addThreeToLong).fuse(longToDouble))
            .forEach(
                fusion -> {
                    assertEquals(4.0, fusion.applyAsDouble(1L));
                }
            );
    }

    @Test
    void longUnop_to_longToInt() {
        Stream
            .of(Z.fuse(addThreeToLong).fuse(longToInt))
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.applyAsInt(1L));
                }
            );
    }

    @Test
    void longUnop_to_longPred() {
        Stream
            .of(Z.fuse(addThreeToLong).fuse(isLongThree))
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
                .of(Z.fuse(addThreeToLong).fuse(saveLongA))
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
            .of(Z.fuse(addThreeToLong).fuse(addThreeToLong))
            .forEach(
                fusion -> {
                    assertEquals(9L, fusion.applyAsLong(3L));
                }
            );
    }

    @Test
    void longUnop_to_longBiop() {
        Stream
            .of(Z.fuse(addThreeToLong).fuse(addLongs))
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.apply(1L).applyAsLong(2L));
                }
            );
    }
}
