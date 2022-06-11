package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongFusionTests {

    @Test
    void long_deep() {
        assertEquals(3L, Z.with(3L).getAsLong());
    }

    @Test
    void long_to_longFn() {
        assertEquals("3", Z.with(3L).fuse(longToString).get());
    }

    @Test
    void long_to_longToDbl() {
        assertEquals(3.0, Z.with(3L).fuse(longToDouble).getAsDouble());
    }

    @Test
    void long_to_longToInt() {
        assertEquals(3, Z.with(3L).fuse(longToInt).getAsInt());
    }

    @Test
    void long_to_longPred() {
        assertTrue(Z.with(3L).fuse(isLongThree).getAsBoolean());
    }

    @Evil
    @Test
    void long_to_longCns() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            Z.with(3L).fuse(saveLongA).run();

            assertEquals(suppliedLong, consumedLongA);
        }
    }

    @Test
    void long_to_longUnop() {
        assertEquals(6, Z.with(3L).fuse(addThreeToLong).getAsLong());
    }

    @Test
    void long_to_longBiop() {
        assertEquals(5, Z.with(3L).fuse(addLongs).applyAsLong(2L));
    }
}
