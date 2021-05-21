package so.dang.cool.z.combinatorics.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToLongBiFunctionFusionTests {
    @Test
    void toLongBifn_to_longFn() {
        assertEquals("3", Z.fuse(addStringsAsLong, longToString).apply("1").apply("2"));
    }

    @Test
    void toLongBifn_to_longToDbl() {
        assertEquals(3.0, Z.fuse(addStringsAsLong, longToDouble).apply("1").applyAsDouble("2"));
    }

    @Test
    void toLongBifn_to_longToInt() {
        assertEquals(9, Z.fuse(addStringsAsLong, longToInt).apply("4").applyAsInt("5"));
    }

    @Test
    void toLongBifn_to_longPred() {
        assertTrue(Z.fuse(addStringsAsLong, isLongThree).apply("-1").test("4"));
    }

    @Evil
    @Test
    void toLongBifn_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(addStringsAsLong, saveLongA).apply("2").accept("3");

            assertEquals(5L, consumedLongA);
        }
    }

    @Test
    void toLongBifn_to_longUnop() {
        assertEquals(6L, Z.fuse(addStringsAsLong, addThreeToLong).apply("1").applyAsLong("2"));
    }

    @Test
    void toLongBifn_to_longBiop() {
        assertEquals(6L, Z.fuse(addStringsAsLong, addLongs).apply("1").apply("2").applyAsLong(3L));
    }
}
