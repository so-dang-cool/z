package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleUnaryOperatorFusionTests {

    @Test
    void dblUnop() {
        assertEquals(1.5, addOneToDouble.applyAsDouble(0.5));

        assertEquals(1.5, Z.fuse(addOneToDouble).applyAsDouble(0.5));
    }

    @Test
    void dblUnop_to_dblFn() {
        assertEquals(
            "4.5",
            Z.fuse(addOneToDouble).fuse(doubleToString).apply(3.5)
        );
    }

    @Test
    void dblUnop_to_dblToInt() {
        assertEquals(
            4,
            Z.fuse(addOneToDouble).fuse(doubleToInt).applyAsInt(3.6)
        );
    }

    @Test
    void dblUnop_to_dblToLong() {
        assertEquals(
            4L,
            Z.fuse(addOneToDouble).fuse(doubleToLong).applyAsLong(3.7)
        );
    }

    @Test
    void dblUnop_to_dblPred() {
        assertTrue(Z.fuse(addOneToDouble).fuse(isDoubleOne).test(0.0));
    }

    @Evil
    @Test
    void dblUnop_to_dblCns() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(addOneToDouble).fuse(saveDoubleA).accept(4.5);

            assertEquals(5.5, consumedDoubleA);
        }
    }

    @Test
    void dblUnop_to_dblUnop() {
        assertEquals(
            7.5,
            Z.fuse(addOneToDouble).fuse(addOneToDouble).applyAsDouble(5.5)
        );
    }

    @Test
    void dblUnop_to_dblBiop() {
        assertEquals(
            4.5,
            Z
                .fuse(addOneToDouble)
                .fuse(addDoubles)
                .apply(1.0)
                .applyAsDouble(2.5)
        );
    }
}
