package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleToLongFunctionFusionTests {

    @Test
    void dblToLong() {
        assertEquals(1L, doubleToLong.applyAsLong(1.0));

        assertEquals(1L, Z.fuse(doubleToLong).applyAsLong(1.0));
    }

    @Test
    void dblToLong_to_longFn() {
        assertEquals("1", Z.fuse(doubleToLong).fuse(longToString).apply(1.6));
    }

    @Test
    void dblToLong_to_longToDbl() {
        assertEquals(
            1.0,
            Z.fuse(doubleToLong).fuse(longToDouble).applyAsDouble(1.7)
        );
    }

    @Test
    void dblToLong_to_longToInt() {
        assertEquals(1, Z.fuse(doubleToLong).fuse(longToInt).applyAsInt(1.8));
    }

    @Test
    void dblToLong_to_longPred() {
        assertTrue(Z.fuse(doubleToLong).fuse(isLong(3L)).test(3.1));
        assertFalse(Z.fuse(doubleToLong).fuse(isLong(9999L)).test(3.1));
    }

    @Evil
    @Test
    void dblToLong_to_longCns() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(doubleToLong).fuse(saveLongA).accept(3.2);

            assertEquals(3L, consumedLongA);
        }
    }

    @Test
    void dblToLong_to_longUnop() {
        assertEquals(
            4L,
            Z.fuse(doubleToLong).fuse(addThreeToLong).applyAsLong(1.2)
        );
    }

    @Test
    void dblToLong_to_longBiop() {
        assertEquals(
            4L,
            Z.fuse(doubleToLong).fuse(addLongs).apply(1.2).applyAsLong(3L)
        );
    }
}
