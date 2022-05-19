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

        assertEquals(1.5, Z.with(addOneToDouble).applyAsDouble(0.5));
    }

    @Test
    void dblUnop_to_dblFn() {
        Stream
            .of(
                Z.fuse(addOneToDouble, doubleToString),
                Z.with(addOneToDouble).fuse(doubleToString),
                Z.with(addOneToDouble).fusing(doubleToString)
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
                Z.with(addOneToDouble).fuse(doubleToInt),
                Z.with(addOneToDouble).fusing(doubleToInt)
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
                Z.with(addOneToDouble).fuse(doubleToLong),
                Z.with(addOneToDouble).fusing(doubleToLong)
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
                Z.with(addOneToDouble).fuse(isDoubleOne),
                Z.with(addOneToDouble).fusing(isDoubleOne)
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
                    Z.with(addOneToDouble).fuse(saveDoubleA),
                    Z.with(addOneToDouble).fusing(saveDoubleA)
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
                Z.with(addOneToDouble).fuse(addOneToDouble),
                Z.with(addOneToDouble).fusing(addOneToDouble)
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
                Z.with(addOneToDouble).fuse(addDoubles),
                Z.with(addOneToDouble).fusing(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(4.5, fusion.apply(1.0).applyAsDouble(2.5));
                }
            );

        Stream
            .of(
                Z.with(addOneToDouble).fuse(addDoubles, 2.5),
                Z.with(addOneToDouble).fusing(addDoubles, 2.5)
            )
            .forEach(
                fusion -> {
                    assertEquals(4.5, fusion.applyAsDouble(1.0));
                }
            );
    }
}
