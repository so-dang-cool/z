package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongFusionTests {
    @Test
    void long_to_longFn() {
        assertEquals("3", Z.fuse(3L, longToString).get());
    }

    @Test
    void long_to_longToDbl() {
        assertEquals(3.0, Z.fuse(3L, longToDouble).getAsDouble());
    }

    @Test
    void long_to_longToInt() {
        assertEquals(3, Z.fuse(3L, longToInt).getAsInt());
    }

    @Test
    void long_to_longPred() {
        assertTrue(Z.fuse(3L, isLongThree).getAsBoolean());
    }

    @Evil
    @Test
    void long_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(3L, saveLongA).run();

            assertEquals(suppliedLong, consumedLongA);
        }
    }

    @Test
    void long_to_longUnop() {
        assertEquals(6, Z.fuse(3L, addThreeToLong).getAsLong());
    }

    @Test
    void long_to_longBiop() {
        assertEquals(5, Z.fuse(3L, addLongs).applyAsLong(2L));
    }
}
