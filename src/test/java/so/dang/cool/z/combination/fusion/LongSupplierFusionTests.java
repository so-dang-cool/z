package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongSupplierFusionTests {

    @Test
    void longSup() {
        assertEquals(suppliedLong, getLong.getAsLong());
    }

    @Test
    void longSup_deep() {
        assertEquals(suppliedLong, Z.with(getLong).resolve().getAsLong());
    }

    @Test
    void longSup_to_longFn() {
        assertEquals("3", Z.fuse(getLong, longToString).get());
    }

    @Test
    void longSup_to_longFn_deep() {
        assertEquals("3", Z.with(getLong).fuse(longToString).get());
    }

    @Test
    void longSup_to_longFn_deeper() {
        assertEquals("3", Z.with(getLong).fusing(longToString).resolve().get());
    }

    @Test
    void longSup_to_longToDbl() {
        assertEquals(3.0, Z.fuse(getLong, longToDouble).getAsDouble());
    }

    @Test
    void longSup_to_longToInt() {
        assertEquals(3, Z.fuse(getLong, longToInt).getAsInt());
    }

    @Test
    void longSup_to_longPred() {
        assertTrue(Z.fuse(getLong, isLongThree).getAsBoolean());
    }

    @Evil
    @Test
    void longSup_to_longCns() {
        synchronized (consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(getLong, saveLongA).run();

            assertEquals(suppliedLong, consumedLongA);
        }
    }

    @Test
    void longSup_to_longUnop() {
        assertEquals(6, Z.fuse(getLong, addThreeToLong).getAsLong());
    }

    @Test
    void longSup_to_longBiop() {
        assertEquals(5, Z.fuse(getLong, addLongs).applyAsLong(2L));
    }
}
