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

        assertEquals(3L, Z.fuse(stringToLong).applyAsLong("3"));
    }

    @Test
    void toLongFn_to_longFn() {
        Stream
            .of(Z.fuse(stringToLong).fuse(longToString))
            .forEach(
                fusion -> {
                    assertEquals("1", fusion.apply("1"));
                }
            );
    }

    @Test
    void toLongFn_to_longToDbl() {
        Stream
            .of(Z.fuse(stringToLong).fuse(longToDouble))
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble("1"));
                }
            );
    }

    @Test
    void toLongFn_to_longToInt() {
        Stream
            .of(Z.fuse(stringToLong).fuse(longToInt))
            .forEach(
                fusion -> {
                    assertEquals(1L, fusion.applyAsInt("1"));
                }
            );
    }

    @Test
    void toLongFn_to_longPred() {
        Stream
            .of(Z.fuse(stringToLong).fuse(isLongThree))
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
                .of(Z.fuse(stringToLong).fuse(saveLongA))
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
            .of(Z.fuse(stringToLong).fuse(addThreeToLong))
            .forEach(
                fusion -> {
                    assertEquals(9L, fusion.applyAsLong("6"));
                }
            );
    }

    @Test
    void toLongFn_to_longBiop() {
        Stream
            .of(Z.fuse(stringToLong).fuse(addLongs))
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.apply("1").applyAsLong(2L));
                }
            );
    }
}
