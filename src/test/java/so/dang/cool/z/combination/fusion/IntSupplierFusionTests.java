package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntSupplierFusionTests {

    @Test
    void intSup() {
        assertEquals(suppliedInt, getInt.getAsInt());
    }

    @Test
    void intSup_deep() {
        assertEquals(suppliedInt, Z.with(getInt).resolve().getAsInt());
    }

    @Test
    void intSup_to_intFn() {
        assertEquals("2", Z.fuse(getInt, intToString).get());
    }

    @Test
    void intSup_to_intFn_deep() {
        assertEquals("2", Z.with(getInt).fuse(intToString).get());
    }

    @Test
    void intSup_to_intFn_deeper() {
        assertEquals("2", Z.with(getInt).fusing(intToString).resolve().get());
    }

    @Test
    void intSup_to_intToDbl() {
        assertEquals(2.0, Z.fuse(getInt, intToDouble).getAsDouble());
    }

    @Test
    void intSup_to_intToLong() {
        assertEquals(2L, Z.fuse(getInt, intToLong).getAsLong());
    }

    @Test
    void intSup_to_intPred() {
        assertTrue(Z.fuse(getInt, isIntTwo).getAsBoolean());
    }

    @Evil
    @Test
    void intSup_to_intCns() {
        synchronized (consumedIntA) {
            consumedIntA = 0;

            Z.fuse(getInt, saveIntA).run();

            assertEquals(suppliedInt, consumedIntA);
        }
    }

    @Test
    void intSup_to_intUnop() {
        assertEquals(4, Z.fuse(getInt, addTwoToInt).getAsInt());
    }

    @Test
    void intSup_to_intBiop() {
        assertEquals(4, Z.fuse(getInt, addInts).applyAsInt(2));
    }
}
