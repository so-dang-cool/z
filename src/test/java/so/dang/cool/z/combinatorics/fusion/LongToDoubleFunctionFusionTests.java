package so.dang.cool.z.combinatorics.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongToDoubleFunctionFusionTests {
    @Test
    void longToDbl_to_dblFn() {
        assertEquals("1.0", Z.fuse(longToDouble, doubleToString).apply(1L));
    }

    @Test
    void longToDbl_to_dblToInt() {
        assertEquals(2, Z.fuse(longToDouble, doubleToInt).applyAsInt(2L));
    }

    @Test
    void longToDbl_to_dblToLong() {
        assertEquals(3L, Z.fuse(longToDouble, doubleToLong).applyAsLong(3L));
    }

    @Test
    void longToDbl_to_dblPred() {
        assertTrue(Z.fuse(longToDouble, isDoubleOne).test(1L));
    }

    @Evil
    @Test
    void longToDbl_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(longToDouble, saveDoubleA).accept(1L);

            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Test
    void longToDbl_to_dblUnop() {
        assertEquals(2.0, Z.fuse(longToDouble, addOneToDouble).applyAsDouble(1L));
    }

    @Test
    void longToDbl_to_dblBiop() {
        assertEquals(1.5, Z.fuse(longToDouble, addDoubles).apply(1L).applyAsDouble(0.5));
    }    
}
