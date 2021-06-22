package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntToDoubleFunctionFusionTests {

    @Test
    void intToDbl() {
        assertEquals(2.0, intToDouble.applyAsDouble(2));

        assertEquals(2.0, Z.with(intToDouble).resolve().applyAsDouble(2));
    }

    @Test
    void intToDbl_to_dblFn() {
        Stream
            .of(
                Z.fuse(intToDouble, doubleToString),
                Z.with(intToDouble).fuse(doubleToString),
                Z.with(intToDouble).fusing(doubleToString).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals("1.0", fusion.apply(1));
                }
            );
    }

    @Test
    void intToDbl_to_dblToInt() {
        Stream
            .of(
                Z.fuse(intToDouble, doubleToInt),
                Z.with(intToDouble).fuse(doubleToInt),
                Z.with(intToDouble).fusing(doubleToInt).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(2));
                }
            );
    }

    @Test
    void intToDbl_to_dblToLong() {
        Stream
            .of(
                Z.fuse(intToDouble, doubleToLong),
                Z.with(intToDouble).fuse(doubleToLong),
                Z.with(intToDouble).fusing(doubleToLong).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(3));
                }
            );
    }

    @Test
    void intToDbl_to_dblPred() {
        Stream
            .of(
                Z.fuse(intToDouble, isDoubleOne),
                Z.with(intToDouble).fuse(isDoubleOne),
                Z.with(intToDouble).fusing(isDoubleOne).resolve()
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(1));
                }
            );
    }

    @Evil
    @Test
    void intToDbl_to_dblCns() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.fuse(intToDouble, saveDoubleA),
                    Z.with(intToDouble).fuse(saveDoubleA),
                    Z.with(intToDouble).fusing(saveDoubleA).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        fusion.accept(1);

                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Test
    void intToDbl_to_dblUnop() {
        Stream
            .of(
                Z.fuse(intToDouble, addOneToDouble),
                Z.with(intToDouble).fuse(addOneToDouble),
                Z.with(intToDouble).fusing(addOneToDouble).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(2.0, fusion.applyAsDouble(1));
                }
            );
    }

    @Test
    void intToDbl_to_dblBiop() {
        Stream
            .of(
                Z.fuse(intToDouble, addDoubles),
                Z.with(intToDouble).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.apply(1).applyAsDouble(0.5));
                }
            );

        Stream
            .of(
                Z.with(intToDouble).fuse(addDoubles, 0.5),
                Z.with(intToDouble).fusing(addDoubles, 0.5).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.applyAsDouble(1));
                }
            );
    }
}
