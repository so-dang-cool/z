package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToDoubleBiFunctionFusionTests {

    @Test
    void toDblBifn() {
        assertEquals(3.0, addStringsAsDouble.applyAsDouble("1.0", "2.0"));
    }

    @Test
    void toDblBifn_deep() {
        assertEquals(
            3.0,
            Z
                .with(addStringsAsDouble)
                .resolve()
                .apply("1.0")
                .applyAsDouble("2.0")
        );
    }

    @Test
    void toDblBifn_to_dblFn() {
        assertEquals(
            "3.0",
            Z.fuse(addStringsAsDouble, doubleToString).apply("1.0").apply("2.0")
        );
    }

    @Test
    void toDblBifn_to_dblFn_deep() {
        assertEquals(
            "3.0",
            Z
                .with(addStringsAsDouble)
                .fuse(doubleToString)
                .apply("1.0")
                .apply("2.0")
        );
    }

    @Test
    void toDblBifn_to_dblFn_deeper() {
        assertEquals(
            "3.0",
            Z
                .with(addStringsAsDouble)
                .fusing(doubleToString)
                .resolve()
                .apply("1.0")
                .apply("2.0")
        );
    }

    @Test
    void toDblBifn_to_dblToInt() {
        assertEquals(
            3,
            Z
                .fuse(addStringsAsDouble, doubleToInt)
                .apply("1.2")
                .applyAsInt("2.3")
        );
    }

    @Test
    void toDblBifn_to_dblToLong() {
        assertEquals(
            10L,
            Z
                .fuse(addStringsAsDouble, doubleToLong)
                .apply("4.5")
                .applyAsLong("5.6")
        );
    }

    @Test
    void toDblBifn_to_dblPred() {
        assertTrue(
            Z.fuse(addStringsAsDouble, isDoubleOne).apply("0.5").test("0.5")
        );
    }

    @Evil
    @Test
    void toDblBifn_to_dblCns() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(addStringsAsDouble, saveDoubleA).apply("1.0").accept("5.5");

            assertEquals(6.5, consumedDoubleA);
        }
    }

    @Test
    void toDblBifn_to_dblUnop() {
        assertEquals(
            7.5,
            Z
                .fuse(addStringsAsDouble, addOneToDouble)
                .apply("0.5")
                .applyAsDouble("6.0")
        );
    }

    @Test
    void toDblBifn_to_dblBiop() {
        assertEquals(
            4.0,
            Z
                .fuse(addStringsAsDouble, addDoubles)
                .apply("0.5")
                .apply("1.5")
                .applyAsDouble(2.0)
        );
    }
}
