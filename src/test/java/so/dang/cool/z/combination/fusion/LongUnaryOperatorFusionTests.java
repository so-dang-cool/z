package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongUnaryOperatorFusionTests {
    @Test
    void longUnop_to_longFn() {
        assertEquals("4", Z.fuse(addThreeToLong, longToString).apply(1L));
    }

    @Test
    void longUnop_to_longToDbl() {
        assertEquals(4.0, Z.fuse(addThreeToLong, longToDouble).applyAsDouble(1L));
    }

    @Test
    void longUnop_to_longToInt() {
        assertEquals(4, Z.fuse(addThreeToLong, longToInt).applyAsInt(1L));
    }

    @Test
    void longUnop_to_longPred() {
        assertTrue(Z.fuse(addThreeToLong, isLongThree).test(0L));
    }

    @Evil
    @Test
    void longUnop_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(addThreeToLong, saveLongA).accept(2L);

            assertEquals(5L, consumedLongA);
        }
    }

    @Test
    void longUnop_to_longUnop() {
        assertEquals(9L, Z.fuse(addThreeToLong, addThreeToLong).applyAsLong(3L));
    }

    @Test
    void longUnop_to_longBiop() {
        assertEquals(6L, Z.fuse(addThreeToLong, addLongs).apply(1L).applyAsLong(2L));
    }
}
