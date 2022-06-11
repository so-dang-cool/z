package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

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
        assertEquals(
            "3",
            Z.fuse(maybeThreeAsLong).fuse(longToString).apply(true)
        );
    }

    @Test
    void boolToLong_to_longToDbl() {
        assertEquals(
            3.0,
            Z.fuse(maybeThreeAsLong).fuse(longToDouble).applyAsDouble(true)
        );
    }

    @Test
    void boolToLong_to_longToInt() {
        assertEquals(
            3,
            Z.fuse(maybeThreeAsLong).fuse(longToInt).applyAsInt(true)
        );
    }

    @Test
    void boolToLong_to_longPred() {
        assertTrue(Z.fuse(maybeThreeAsLong).fuse(isLongThree).test(true));
    }

    @Evil
    @Test
    void boolToLong_to_longCns() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(maybeThreeAsLong).fuse(saveLongA).accept(true);

            assertEquals(3L, consumedLongA);
        }
    }

    @Test
    void boolToLong_to_longUnop() {
        assertEquals(
            6L,
            Z.fuse(maybeThreeAsLong).fuse(addThreeToLong).applyAsLong(true)
        );
    }

    @Test
    void boolToLong_to_longBiop() {
        assertEquals(
            7L,
            Z.fuse(maybeThreeAsLong).fuse(addLongs).apply(true).applyAsLong(4L)
        );
    }
}
