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

        assertEquals(2.0, Z.fuse(intToDouble).applyAsDouble(2));
    }

    @Test
    void intToDbl_to_dblFn() {
        Stream
            .of(
                Z.fuse(intToDouble, doubleToString),
                Z.fuse(intToDouble).fuse(doubleToString),
                Z.fuse(intToDouble).fusing(doubleToString)
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
                Z.fuse(intToDouble).fuse(doubleToInt),
                Z.fuse(intToDouble).fusing(doubleToInt)
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
                Z.fuse(intToDouble).fuse(doubleToLong),
                Z.fuse(intToDouble).fusing(doubleToLong)
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
                Z.fuse(intToDouble).fuse(isDoubleOne),
                Z.fuse(intToDouble).fusing(isDoubleOne)
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
                    Z.fuse(intToDouble).fuse(saveDoubleA),
                    Z.fuse(intToDouble).fusing(saveDoubleA)
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
                Z.fuse(intToDouble).fuse(addOneToDouble),
                Z.fuse(intToDouble).fusing(addOneToDouble)
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
                Z.fuse(intToDouble).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.apply(1).applyAsDouble(0.5));
                }
            );

        Stream
            .of(
                Z.fuse(intToDouble).fuse(addDoubles, 0.5),
                Z.fuse(intToDouble).fusing(addDoubles, 0.5)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.applyAsDouble(1));
                }
            );
    }
}
