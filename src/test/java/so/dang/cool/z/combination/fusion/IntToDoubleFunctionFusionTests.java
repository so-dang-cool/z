package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntToDoubleFunctionFusionTests {

    @Test
    void intToDbl() {
        assertEquals(2.0, intToDouble.applyAsDouble(2));

        assertEquals(2.0, Z.fuse(intToDouble).applyAsDouble(2));
    }

    @Test
    void intToDbl_to_dblFn() {
        assertEquals("1.0", Z.fuse(intToDouble).fuse(doubleToString).apply(1));
    }

    @Test
    void intToDbl_to_dblToInt() {
        assertEquals(2, Z.fuse(intToDouble).fuse(doubleToInt).applyAsInt(2));
    }

    @Test
    void intToDbl_to_dblToLong() {
        assertEquals(3L, Z.fuse(intToDouble).fuse(doubleToLong).applyAsLong(3));
    }

    @Test
    void intToDbl_to_dblPred() {
        assertTrue(Z.fuse(intToDouble).fuse(isDoubleOne).test(1));
    }

    @Evil
    @Test
    void intToDbl_to_dblCns() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(intToDouble).fuse(saveDoubleA).accept(1);

            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Test
    void intToDbl_to_dblUnop() {
        assertEquals(
            2.0,
            Z.fuse(intToDouble).fuse(addOneToDouble).applyAsDouble(1)
        );
    }

    @Test
    void intToDbl_to_dblBiop() {
        assertEquals(
            1.5,
            Z.fuse(intToDouble).fuse(addDoubles).apply(1).applyAsDouble(0.5)
        );
    }
}
