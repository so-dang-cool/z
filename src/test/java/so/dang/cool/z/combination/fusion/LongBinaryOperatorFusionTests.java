package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongBinaryOperatorFusionTests {
    @Test
    void longBiop() {
        assertEquals(3L, addLongs.applyAsLong(1L, 2L));
    }

    @Test
    void longBiop_deep() {
        assertEquals(3L, Z.with(addLongs).resolve().apply(1L).applyAsLong(2L));
    }

    @Test
    void longBiop_to_longFn() {
        assertEquals("3", Z.fuse(addLongs, longToString).apply(1L).apply(2L));
    }

    @Test
    void longBiop_to_longFn_deep() {
        assertEquals("3", Z.with(addLongs).fuse(longToString).apply(1L).apply(2L));
    }

    @Test
    void longBiop_to_longToDbl() {
        assertEquals(3.0, Z.fuse(addLongs, longToDouble).apply(1L).applyAsDouble(2L));
    }

    @Test
    void longBiop_to_longToInt() {
        assertEquals(9, Z.fuse(addLongs, longToInt).apply(4L).applyAsInt(5L));
    }

    @Test
    void longBiop_to_longPred() {
        assertTrue(Z.fuse(addLongs, isLongThree).apply(-1L).test(4L));
    }

    @Evil
    @Test
    void longBiop_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(addLongs, saveLongA).apply(2L).accept(3L);

            assertEquals(5L, consumedLongA);
        }
    }

    @Test
    void longBiop_to_longUnop() {
        assertEquals(6L, Z.fuse(addLongs, addThreeToLong).apply(1L).applyAsLong(2L));
    }

    @Test
    void longBiop_to_longBiop() {
        assertEquals(6L, Z.fuse(addLongs, addLongs).apply(1L).apply(2L).applyAsLong(3L));
    }
}
