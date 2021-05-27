package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToLongFunctionFusionTests {
    @Test
    void toLongFn() {
        assertEquals(3L, stringToLong.applyAsLong("3"));
    }

    @Test
    void toLongFn_deep() {
        assertEquals(3L, Z.with(stringToLong).resolve().applyAsLong("3"));
    }

    @Test
    void toLongFn_to_longFn() {
        assertEquals("1", Z.fuse(stringToLong, longToString).apply("1"));
    }

    @Test
    void toLongFn_to_longFn_deep() {
        assertEquals("1", Z.with(stringToLong).fuse(longToString).apply("1"));
    }

    @Test
    void toLongFn_to_longFn_deeper() {
        assertEquals("1", Z.with(stringToLong).fusing(longToString).resolve().apply("1"));
    }

    @Test
    void toLongFn_to_longToDbl() {
        assertEquals(1.0, Z.fuse(stringToLong, longToDouble).applyAsDouble("1"));
    }

    @Test
    void toLongFn_to_longToInt() {
        assertEquals(1L, Z.fuse(stringToLong, longToInt).applyAsInt("1"));
    }

    @Test
    void toLongFn_to_longPred() {
        assertTrue(Z.fuse(stringToLong, isLongThree).test("3"));
    }

    @Evil
    @Test
    void toLongFn_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(stringToLong, saveLongA).accept("5");

            assertEquals(5L, consumedLongA);
        }
    }

    @Test
    void toLongFn_to_longUnop() {
        assertEquals(9L, Z.fuse(stringToLong, addThreeToLong).applyAsLong("6"));
    }

    @Test
    void toLongFn_to_longBiop() {
        assertEquals(3L, Z.fuse(stringToLong, addLongs).apply("1").applyAsLong(2L));
    }    
}
