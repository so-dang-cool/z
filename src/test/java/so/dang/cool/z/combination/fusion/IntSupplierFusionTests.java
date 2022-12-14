package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntSupplierFusionTests {

    @Test
    void intSup() {
        assertEquals(suppliedInt, getInt.getAsInt());
        assertEquals(suppliedInt, Z.fuse(getInt).getAsInt());
    }

    @Test
    void intSup_to_intFn() {
        assertEquals("2", Z.fuse(getInt).fuse(intToString).get());
    }

    @Test
    void intSup_to_intToDbl() {
        assertEquals(2.0, Z.fuse(getInt).fuse(intToDouble).getAsDouble());
    }

    @Test
    void intSup_to_intToLong() {
        assertEquals(2L, Z.fuse(getInt).fuse(intToLong).getAsLong());
    }

    @Test
    void intSup_to_intPred() {
        assertTrue(Z.fuse(getInt).fuse(isInt(2)).getAsBoolean());
        assertFalse(Z.fuse(getInt).fuse(isInt(9999)).getAsBoolean());
    }

    @Evil
    @Test
    void intSup_to_intCns() {
        synchronized (consumedIntA) {
            consumedIntA = 0;
            Z.fuse(getInt).fuse(saveIntA).run();
            assertEquals(suppliedInt, consumedIntA);
        }
    }

    @Test
    void intSup_to_intUnop() {
        assertEquals(4, Z.fuse(getInt).fuse(addTwoToInt).getAsInt());
    }

    @Test
    void intSup_to_intBiop() {
        assertEquals(4, Z.fuse(getInt).fuse(addInts).applyAsInt(2));
    }
}
