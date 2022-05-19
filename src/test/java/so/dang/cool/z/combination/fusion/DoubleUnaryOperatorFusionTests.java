package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
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
        Stream
            .of(
                Z.fuse(addOneToDouble, doubleToString),
                Z.fuse(addOneToDouble).fuse(doubleToString),
                Z.fuse(addOneToDouble).fusing(doubleToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("4.5", fusion.apply(3.5));
                }
            );
    }

    @Test
    void dblUnop_to_dblToInt() {
        Stream
            .of(
                Z.fuse(addOneToDouble, doubleToInt),
                Z.fuse(addOneToDouble).fuse(doubleToInt),
                Z.fuse(addOneToDouble).fusing(doubleToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.applyAsInt(3.6));
                }
            );
    }

    @Test
    void dblUnop_to_dblToLong() {
        Stream
            .of(
                Z.fuse(addOneToDouble, doubleToLong),
                Z.fuse(addOneToDouble).fuse(doubleToLong),
                Z.fuse(addOneToDouble).fusing(doubleToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong(3.7));
                }
            );
    }

    @Test
    void dblUnop_to_dblPred() {
        Stream
            .of(
                Z.fuse(addOneToDouble, isDoubleOne),
                Z.fuse(addOneToDouble).fuse(isDoubleOne),
                Z.fuse(addOneToDouble).fusing(isDoubleOne)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(0.0));
                }
            );
    }

    @Evil
    @Test
    void dblUnop_to_dblCns() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.fuse(addOneToDouble, saveDoubleA),
                    Z.fuse(addOneToDouble).fuse(saveDoubleA),
                    Z.fuse(addOneToDouble).fusing(saveDoubleA)
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        fusion.accept(4.5);

                        assertEquals(5.5, consumedDoubleA);
                    }
                );
        }
    }

    @Test
    void dblUnop_to_dblUnop() {
        Stream
            .of(
                Z.fuse(addOneToDouble, addOneToDouble),
                Z.fuse(addOneToDouble).fuse(addOneToDouble),
                Z.fuse(addOneToDouble).fusing(addOneToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(7.5, fusion.applyAsDouble(5.5));
                }
            );
    }

    @Test
    void dblUnop_to_dblBiop() {
        Stream
            .of(
                Z.fuse(addOneToDouble, addDoubles),
                Z.fuse(addOneToDouble).fuse(addDoubles),
                Z.fuse(addOneToDouble).fusing(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(4.5, fusion.apply(1.0).applyAsDouble(2.5));
                }
            );

        Stream
            .of(
                Z.fuse(addOneToDouble).fuse(addDoubles, 2.5),
                Z.fuse(addOneToDouble).fusing(addDoubles, 2.5)
            )
            .forEach(
                fusion -> {
                    assertEquals(4.5, fusion.applyAsDouble(1.0));
                }
            );
    }
}
