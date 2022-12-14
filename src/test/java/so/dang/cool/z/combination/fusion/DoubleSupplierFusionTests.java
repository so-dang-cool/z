package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleSupplierFusionTests {

    @Test
    void dblSup() {
        assertEquals(suppliedDouble, getDouble.getAsDouble());
        assertEquals(suppliedDouble, Z.fuse(getDouble).getAsDouble());
    }

    @Test
    void dblSup_to_dblFn() {
        assertEquals("1.0", Z.fuse(getDouble).fuse(doubleToString).get());
    }

    @Test
    void dblSup_to_dblToInt() {
        assertEquals(1, Z.fuse(getDouble).fuse(doubleToInt).getAsInt());
    }

    @Test
    void dblSup_to_dblToLong() {
        assertEquals(1L, Z.fuse(getDouble).fuse(doubleToLong).getAsLong());
    }

    @Test
    void dblSup_to_dblPred() {
        assertTrue(Z.fuse(getDouble).fuse(isDouble(1.0)).getAsBoolean());
        assertFalse(Z.fuse(getDouble).fuse(isDouble(2.0)).getAsBoolean());
    }

    @Evil
    @Test
    void dblSup_to_dblCns() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;
            Z.fuse(getDouble).fuse(saveDoubleA).run();
            assertEquals(suppliedDouble, consumedDoubleA);
        }
    }

    @Test
    void dblSup_to_dblUnop() {
        assertEquals(2.0, Z.fuse(getDouble).fuse(addOneToDouble).getAsDouble());
    }

    @Test
    void dblSup_to_dblBiop() {
        assertEquals(
            3.5,
            Z.fuse(getDouble).fuse(addDoubles).applyAsDouble(2.5)
        );
    }
}
