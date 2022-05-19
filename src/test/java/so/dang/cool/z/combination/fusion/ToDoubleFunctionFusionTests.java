package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToDoubleFunctionFusionTests {

    @Test
    void toDblFn() {
        assertEquals(1.5, stringToDouble.applyAsDouble("1.5"));

        assertEquals(1.5, Z.fuse(stringToDouble).applyAsDouble("1.5"));
    }

    @Test
    void toDblFn_to_dblFn() {
        Stream
            .of(
                Z.fuse(stringToDouble, doubleToString),
                Z.fuse(stringToDouble).fuse(doubleToString),
                Z.fuse(stringToDouble).fusing(doubleToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("4.5", fusion.apply("4.5"));
                }
            );
    }

    @Test
    void toDblFn_to_dblToInt() {
        Stream
            .of(
                Z.fuse(stringToDouble, doubleToInt),
                Z.fuse(stringToDouble).fuse(doubleToInt),
                Z.fuse(stringToDouble).fusing(doubleToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(4, fusion.applyAsInt("4.6"));
                }
            );
    }

    @Test
    void toDblFn_to_dblToLong() {
        Stream
            .of(
                Z.fuse(stringToDouble, doubleToLong),
                Z.fuse(stringToDouble).fuse(doubleToLong),
                Z.fuse(stringToDouble).fusing(doubleToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(4L, fusion.applyAsLong("4.7"));
                }
            );
    }

    @Test
    void toDblFn_to_dblPred() {
        Stream
            .of(
                Z.fuse(stringToDouble, isDoubleOne),
                Z.fuse(stringToDouble).fuse(isDoubleOne),
                Z.fuse(stringToDouble).fusing(isDoubleOne)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test("1.0"));
                }
            );
    }

    @Evil
    @Test
    void toDblFn_to_dblCns() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.fuse(stringToDouble, saveDoubleA),
                    Z.fuse(stringToDouble).fuse(saveDoubleA),
                    Z.fuse(stringToDouble).fusing(saveDoubleA)
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        fusion.accept("5.5");

                        assertEquals(5.5, consumedDoubleA);
                    }
                );
        }
    }

    @Test
    void toDblFn_to_dblUnop() {
        Stream
            .of(
                Z.fuse(stringToDouble, addOneToDouble),
                Z.fuse(stringToDouble).fuse(addOneToDouble),
                Z.fuse(stringToDouble).fusing(addOneToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(7.5, fusion.applyAsDouble("6.5"));
                }
            );
    }

    @Test
    void toDblFn_to_dblBiop() {
        Stream
            .of(
                Z.fuse(stringToDouble, addDoubles),
                Z.fuse(stringToDouble).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.apply("1.0").applyAsDouble(2.0));
                }
            );

        Stream
            .of(
                Z.fuse(stringToDouble).fuse(addDoubles, 2.0),
                Z.fuse(stringToDouble).fusing(addDoubles, 2.0)
            )
            .forEach(
                fusion -> {
                    assertEquals(3.0, fusion.applyAsDouble("1.0"));
                }
            );
    }
}
