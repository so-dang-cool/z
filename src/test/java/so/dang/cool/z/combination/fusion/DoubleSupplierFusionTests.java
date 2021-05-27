package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleSupplierFusionTests {
    @Test
    void dblSup() {
        assertEquals(suppliedDouble, getDouble.getAsDouble());
    }

    @Test
    void dblSup_deep() {
        assertEquals(suppliedDouble, Z.with(getDouble).resolve().getAsDouble());
    }

    @Test
    void dblSup_to_dblFn() {
        assertEquals("1.0", Z.fuse(getDouble, doubleToString).get());
    }

    @Test
    void dblSup_to_dblFn_deep() {
        assertEquals("1.0", Z.with(getDouble).fuse(doubleToString).get());
    }

    @Test
    void dblSup_to_dblFn_deeper() {
        assertEquals("1.0", Z.with(getDouble).fusing(doubleToString).resolve().get());
    }

    @Test
    void dblSup_to_dblToInt() {
        assertEquals(1, Z.fuse(getDouble, doubleToInt).getAsInt());
    }

    @Test
    void dblSup_to_dblToLong() {
        assertEquals(1L, Z.fuse(getDouble, doubleToLong).getAsLong());
    }

    @Test
    void dblSup_to_dblPred() {
        assertTrue(Z.fuse(getDouble, isDoubleOne).getAsBoolean());
    }

    @Evil
    @Test
    void dblSup_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(getDouble, saveDoubleA).run();

            assertEquals(suppliedDouble, consumedDoubleA);
        }
    }

    @Test
    void dblSup_to_dblUnop() {
        assertEquals(2.0, Z.fuse(getDouble, addOneToDouble).getAsDouble());
    }

    @Test
    void dblSup_to_dblBiop() {
        assertEquals(3.0, Z.fuse(getDouble, addDoubles).applyAsDouble(2.0));
    }
}
