package so.dang.cool.z.combinatorics.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleToLongFunctionFusionTests {
    @Test
    void dblToLong_to_longFn() {
        assertEquals("1", Z.fuse(doubleToLong, longToString).apply(1.6));
    }

    @Test
    void dblToLong_to_longToDbl() {
        assertEquals(1.0, Z.fuse(doubleToLong, longToDouble).applyAsDouble(1.7));
    }

    @Test
    void dblToLong_to_longToInt() {
        assertEquals(1, Z.fuse(doubleToLong, longToInt).applyAsInt(1.8));
    }

    @Test
    void dblToLong_to_longPred() {
        assertTrue(Z.fuse(doubleToLong, isLongThree).test(3.1));
    }

    @Evil
    @Test
    void dblToLong_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;
            
            Z.fuse(doubleToLong, saveLongA).accept(3.2);

            assertEquals(3L, consumedLongA);
        }
    }

    @Test
    void dblToLong_to_longUnop() {
        assertEquals(4L, Z.fuse(doubleToLong, addThreeToLong).applyAsLong(1.2));
    }

    @Test
    void dblToLong_to_longBiop() {
        assertEquals(4L, Z.fuse(doubleToLong, addLongs).apply(1.2).applyAsLong(3L));
    }    
}
