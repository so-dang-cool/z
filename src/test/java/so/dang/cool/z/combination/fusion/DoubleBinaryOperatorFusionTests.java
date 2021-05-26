package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleBinaryOperatorFusionTests {
    @Test
    void dblBiop_to_dblFn() {
        assertEquals("3.0", Z.fuse(addDoubles, doubleToString).apply(1.0).apply(2.0));
    }

    @Test
    void dblBiop_to_dblFn_deep() {
        assertEquals("3.0", Z.with(addDoubles).fuse(doubleToString).apply(1.0).apply(2.0));
    }

    @Test
    void dblBiop_to_dblFn_deeper() {
        assertEquals("3.0", Z.with(addDoubles).fusing(doubleToString).resolve().apply(1.0).apply(2.0));
    }

    @Test
    void dblBiop_to_dblToInt() {
        assertEquals(3, Z.fuse(addDoubles, doubleToInt).apply(1.2).applyAsInt(2.3));
    }

    @Test
    void dblBiop_to_dblToLong() {
        assertEquals(10L, Z.fuse(addDoubles, doubleToLong).apply(4.5).applyAsLong(5.6));
    }

    @Test
    void dblBiop_to_dblPred() {
        assertTrue(Z.fuse(addDoubles, isDoubleOne).apply(0.5).test(0.5));
    }

    @Evil
    @Test
    void dblBiop_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(addDoubles, saveDoubleA).apply(1.0).accept(5.5);

            assertEquals(6.5, consumedDoubleA);
        }
    }
    
    @Test
    void dblBiop_to_dblUnop() {
        assertEquals(7.5, Z.fuse(addDoubles, addOneToDouble).apply(0.5).applyAsDouble(6.0));
    }

    @Test
    void dblBiop_to_dblBiop() {
        assertEquals(4.0, Z.fuse(addDoubles, addDoubles).apply(0.5).apply(1.5).applyAsDouble(2.0));
    }
}
