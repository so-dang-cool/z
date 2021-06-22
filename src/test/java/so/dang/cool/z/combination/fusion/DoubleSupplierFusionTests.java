package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleSupplierFusionTests {

    @Test
    void dblSup() {
        assertEquals(suppliedDouble, getDouble.getAsDouble());
        assertEquals(suppliedDouble, Z.with(getDouble).resolve().getAsDouble());
    }

    @Test
    void dblSup_to_dblFn() {
        assertEquals("1.0", Z.fuse(getDouble, doubleToString).get());
        assertEquals("1.0", Z.with(getDouble).fuse(doubleToString).get());
        assertEquals(
            "1.0",
            Z.with(getDouble).fusing(doubleToString).resolve().get()
        );
    }

    @Test
    void dblSup_to_dblToInt() {
        assertEquals(1, Z.fuse(getDouble, doubleToInt).getAsInt());
        assertEquals(1, Z.with(getDouble).fuse(doubleToInt).getAsInt());
        assertEquals(
            1,
            Z.with(getDouble).fusing(doubleToInt).resolve().getAsInt()
        );
    }

    @Test
    void dblSup_to_dblToLong() {
        assertEquals(1L, Z.fuse(getDouble, doubleToLong).getAsLong());
        assertEquals(1L, Z.with(getDouble).fuse(doubleToLong).getAsLong());
        assertEquals(
            1L,
            Z.with(getDouble).fusing(doubleToLong).resolve().getAsLong()
        );
    }

    @Test
    void dblSup_to_dblPred() {
        assertTrue(Z.fuse(getDouble, isDoubleOne).getAsBoolean());
        assertTrue(Z.with(getDouble).fuse(isDoubleOne).getAsBoolean());
        assertTrue(
            Z.with(getDouble).fusing(isDoubleOne).resolve().getAsBoolean()
        );
    }

    @Evil
    @Test
    void dblSup_to_dblCns() {
        synchronized (consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(getDouble, saveDoubleA).run();
            Z.with(getDouble).fuse(saveDoubleA).run();
            Z.with(getDouble).fusing(saveDoubleA).resolve().run();

            assertEquals(suppliedDouble, consumedDoubleA);
        }
    }

    @Test
    void dblSup_to_dblUnop() {
        assertEquals(2.0, Z.fuse(getDouble, addOneToDouble).getAsDouble());
        assertEquals(2.0, Z.with(getDouble).fuse(addOneToDouble).getAsDouble());
        assertEquals(
            2.0,
            Z.with(getDouble).fusing(addOneToDouble).resolve().getAsDouble()
        );
    }

    @Test
    void dblSup_to_dblBiop() {
        Stream
            .of(
                Z.fuse(getDouble, addDoubles),
                Z.with(getDouble).fuse(addDoubles),
                Z.with(getDouble).fusing(addDoubles).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(3.5, fusion.applyAsDouble(2.5));
                }
            );

        Stream
            .of(
                Z.with(getDouble).fuse(addDoubles, 2.5),
                Z.with(getDouble).fusing(addDoubles, 2.5).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(3.5, fusion.getAsDouble());
                }
            );
    }
}
