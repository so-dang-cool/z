package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanToDoubleFunctionFusionTests {
    @Test
    void boolToDbl() {
        assertEquals(1.0, maybeOneAsDouble.applyAsDouble(true));
    }

    @Test
    void boolToDbl_deep() {
        assertEquals(1.0, Z.with(maybeOneAsDouble).resolve().applyAsDouble(true));
    }

    @Test
    void boolToDbl_to_dblFn() {
        assertEquals("1.0", Z.fuse(maybeOneAsDouble, doubleToString).apply(true));
    }

    @Test
    void boolToDbl_to_dblFn_deep() {
        assertEquals("1.0", Z.with(maybeOneAsDouble).fuse(doubleToString).apply(true));
    }

    @Test
    void boolToDbl_to_dblFn_deeper() {
        assertEquals("1.0", Z.with(maybeOneAsDouble).fusing(doubleToString).resolve().apply(true));
    }

    @Test
    void boolToDbl_to_dblToInt() {
        assertEquals(1, Z.fuse(maybeOneAsDouble, doubleToInt).applyAsInt(true));
    }

    @Test
    void boolToDbl_to_dblToLong() {
        assertEquals(1L, Z.fuse(maybeOneAsDouble, doubleToLong).applyAsLong(true));
    }

    @Test
    void boolToDbl_to_dblPred() {
        assertTrue(Z.fuse(maybeOneAsDouble, isDoubleOne).test(true));
    }

    @Evil
    @Test
    void boolToDbl_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(maybeOneAsDouble, saveDoubleA).accept(true);

            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Test
    void boolToDbl_to_dblUnop() {
        assertEquals(2.0, Z.fuse(maybeOneAsDouble, addOneToDouble).applyAsDouble(true));
    }

    @Test
    void boolToDbl_to_dblBiop() {
        assertEquals(1.5, Z.fuse(maybeOneAsDouble, addDoubles).apply(true).applyAsDouble(0.5));
    }    
}
