package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleFusionTests {

    @Test
    void double_deep() {
        assertEquals(1.0, Z.with(1.0).getAsDouble());
    }

    @Test
    void double_to_dblFn() {
        assertEquals("1.0", Z.with(1.0).fuse(doubleToString).get());
    }

    @Test
    void double_to_dblToInt() {
        assertEquals(1, Z.with(1.0).fuse(doubleToInt).getAsInt());
    }

    @Test
    void double_to_dblToLong() {
        assertEquals(1L, Z.with(1.0).fuse(doubleToLong).getAsLong());
    }

    @Test
    void double_to_dblPred() {
        assertTrue(Z.with(1.0).fuse(isDouble(1.0)).getAsBoolean());
        assertFalse(Z.with(2.0).fuse(isDouble(1.0)).getAsBoolean());
    }

    @Evil
    @Test
    void double_to_dblCns() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.with(1.0).fuse(saveDoubleA).run();

            assertEquals(suppliedDouble, consumedDoubleA);
        }
    }

    @Test
    void double_to_dblUnop() {
        assertEquals(2.0, Z.with(1.0).fuse(addOneToDouble).getAsDouble());
    }

    @Test
    void double_to_dblBiop() {
        assertEquals(3.0, Z.with(1.0).fuse(addDoubles).applyAsDouble(2.0));
    }
}
