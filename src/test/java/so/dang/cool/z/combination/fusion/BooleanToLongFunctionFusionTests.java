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
    }

    @Test
    void boolToLong_deep() {
        assertEquals(3L, Z.with(maybeThreeAsLong).resolve().applyAsLong(true));
    }

    @Test
    void boolToLong_to_longFn() {
        assertEquals("3", Z.fuse(maybeThreeAsLong, longToString).apply(true));
    }

    @Test
    void boolToLong_to_longFn_deep() {
        assertEquals("3", Z.with(maybeThreeAsLong).fuse(longToString).apply(true));
    }

    @Test
    void boolToLong_to_longFn_deeper() {
        assertEquals("3", Z.with(maybeThreeAsLong).fusing(longToString).resolve().apply(true));
    }

    @Test
    void boolToLong_to_longToDbl() {
        assertEquals(3.0, Z.fuse(maybeThreeAsLong, longToDouble).applyAsDouble(true));
    }

    @Test
    void boolToLong_to_longToInt() {
        assertEquals(3, Z.fuse(maybeThreeAsLong, longToInt).applyAsInt(true));
    }

    @Test
    void boolToLong_to_longPred() {
        assertTrue(Z.fuse(maybeThreeAsLong, isLongThree).test(true));
    }

    @Evil
    @Test
    void boolToLong_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;
            
            Z.fuse(maybeThreeAsLong, saveLongA).accept(true);

            assertEquals(3L, consumedLongA);
        }
    }

    @Test
    void boolToLong_to_longUnop() {
        assertEquals(6L, Z.fuse(maybeThreeAsLong, addThreeToLong).applyAsLong(true));
    }

    @Test
    void boolToLong_to_longBiop() {
        assertEquals(7L, Z.fuse(maybeThreeAsLong, addLongs).apply(true).applyAsLong(4L));
    }    
}
