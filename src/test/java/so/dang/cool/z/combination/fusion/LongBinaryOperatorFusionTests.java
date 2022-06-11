package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongBinaryOperatorFusionTests {

    @Test
    void longBiop() {
        assertEquals(3L, addLongs.applyAsLong(1L, 2L));

        assertEquals(3L, Z.fuse(addLongs).apply(1L).applyAsLong(2L));
    }

    @Test
    void longBiop_to_longFn() {
        assertEquals(
            "3",
            Z.fuse(addLongs).fuse(longToString).apply(1L).apply(2L)
        );
    }

    @Test
    void longBiop_to_longToDbl() {
        Stream
            .of(Z.fuse(addLongs).fuse(longToDouble))
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.apply(1L).applyAsDouble(2L));
                }
            );
    }

    @Test
    void longBiop_to_longToInt() {
        assertEquals(
            9,
            Z.fuse(addLongs).fuse(longToInt).apply(4L).applyAsInt(5L)
        );
    }

    @Test
    void longBiop_to_longPred() {
        Stream
            .of(Z.fuse(addLongs).fuse(isLongThree))
            .forEach(
                fusion -> {
                    assertTrue(fusion.apply(-1L).test(4L));
                }
            );
    }

    @Evil
    @Test
    void longBiop_to_longCns() {
        synchronized (consumedLongA) {
            Stream
                .of(Z.fuse(addLongs).fuse(saveLongA))
                .forEach(
                    fusion -> {
                        consumedLongA = 0L;

                        fusion.apply(2L).accept(3L);

                        assertEquals(5L, consumedLongA);
                    }
                );
        }
    }

    @Test
    void longBiop_to_longUnop() {
        Stream
            .of(Z.fuse(addLongs).fuse(addThreeToLong))
            .forEach(
                fusion -> {
                    assertEquals(6L, fusion.apply(1L).applyAsLong(2L));
                }
            );
    }

    @Test
    void longBiop_to_longBiop() {
        Stream
            .of(Z.fuse(addLongs).fuse(addLongs))
            .forEach(
                fusion -> {
                    assertEquals(
                        6L,
                        fusion.apply(1L).apply(2L).applyAsLong(3L)
                    );
                }
            );
    }
}
