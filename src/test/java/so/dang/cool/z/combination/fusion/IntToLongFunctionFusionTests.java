package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntToLongFunctionFusionTests {
    @Test
    void intToLong() {
        assertEquals(2L, intToLong.applyAsLong(2));
    }

    @Test
    void intToLong_deep() {
        assertEquals(2L, Z.with(intToLong).resolve().applyAsLong(2));
    }

    @Test
    void intToLong_to_longFn() {
        assertEquals("1", Z.fuse(intToLong, longToString).apply(1));
    }

    @Test
    void intToLong_to_longFn_deep() {
        assertEquals("1", Z.with(intToLong).fuse(longToString).apply(1));
    }

    @Test
    void intToLong_to_longFn_deeper() {
        assertEquals("1", Z.with(intToLong).fusing(longToString).resolve().apply(1));
    }

    @Test
    void intToLong_to_longToDbl() {
        assertEquals(1.0, Z.fuse(intToLong, longToDouble).applyAsDouble(1));
    }

    @Test
    void intToLong_to_longToInt() {
        assertEquals(1, Z.fuse(intToLong, longToInt).applyAsInt(1));
    }

    @Test
    void intToLong_to_longPred() {
        assertTrue(Z.fuse(intToLong, isLongThree).test(3));
    }

    @Evil
    @Test
    void  intToLong_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;
            
            Z.fuse(intToLong, saveLongA).accept(3);

            assertEquals(3L, consumedLongA);
        }
    }

    @Test
    void  intToLong_to_longUnop() {
        assertEquals(4L, Z.fuse(intToLong, addThreeToLong).applyAsLong(1));
    }

    @Test
    void  intToLong_to_longBiop() {
        assertEquals(4L, Z.fuse(intToLong, addLongs).apply(1).applyAsLong(3L));
    }    
}
