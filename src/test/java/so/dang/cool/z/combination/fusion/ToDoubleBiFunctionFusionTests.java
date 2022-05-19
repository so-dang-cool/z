package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ToDoubleBiFunctionFusionTests {

    @Test
    void toDblBifn() {
        assertEquals(3.0, addStringsAsDouble.applyAsDouble("1.0", "2.0"));

        assertEquals(
            3.0,
            Z.fuse(addStringsAsDouble).apply("1.0").applyAsDouble("2.0")
        );
    }

    @Test
    void toDblBifn_to_dblFn() {
        Stream
            .of(
                Z.fuse(addStringsAsDouble, doubleToString),
                Z.fuse(addStringsAsDouble).fuse(doubleToString),
                Z.fuse(addStringsAsDouble).fusing(doubleToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("3.0", fusion.apply("1.0").apply("2.0"));
                }
            );
    }

    @Test
    void toDblBifn_to_dblToInt() {
        Stream
            .of(
                Z.fuse(addStringsAsDouble, doubleToInt),
                Z.fuse(addStringsAsDouble).fuse(doubleToInt),
                Z.fuse(addStringsAsDouble).fusing(doubleToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.apply("1.2").applyAsInt("2.3"));
                }
            );
    }

    @Test
    void toDblBifn_to_dblToLong() {
        Stream
            .of(
                Z.fuse(addStringsAsDouble, doubleToLong),
                Z.fuse(addStringsAsDouble).fuse(doubleToLong),
                Z.fuse(addStringsAsDouble).fusing(doubleToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(10L, fusion.apply("4.5").applyAsLong("5.6"));
                }
            );
    }

    @Test
    void toDblBifn_to_dblPred() {
        Stream
            .of(
                Z.fuse(addStringsAsDouble, isDoubleOne),
                Z.fuse(addStringsAsDouble).fuse(isDoubleOne),
                Z.fuse(addStringsAsDouble).fusing(isDoubleOne)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.apply("0.5").test("0.5"));
                }
            );
    }

    @Evil
    @Test
    void toDblBifn_to_dblCns() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.fuse(addStringsAsDouble, saveDoubleA),
                    Z.fuse(addStringsAsDouble).fuse(saveDoubleA),
                    Z.fuse(addStringsAsDouble).fusing(saveDoubleA)
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        fusion.apply("1.0").accept("5.5");

                        assertEquals(6.5, consumedDoubleA);
                    }
                );
        }
    }

    @Test
    void toDblBifn_to_dblUnop() {
        Stream
            .of(
                Z.fuse(addStringsAsDouble, addOneToDouble),
                Z.fuse(addStringsAsDouble).fuse(addOneToDouble),
                Z.fuse(addStringsAsDouble).fusing(addOneToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(7.5, fusion.apply("0.5").applyAsDouble("6.0"));
                }
            );
    }

    @Test
    void toDblBifn_to_dblBiop() {
        Stream
            .of(
                Z.fuse(addStringsAsDouble, addDoubles),
                Z.fuse(addStringsAsDouble).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(
                        4.0,
                        fusion.apply("0.5").apply("1.5").applyAsDouble(2.0)
                    );
                }
            );

        Stream
            .of(
                Z.fuse(addStringsAsDouble).fuse(addDoubles, 2.0),
                Z.fuse(addStringsAsDouble).fusing(addDoubles, 2.0)
            )
            .forEach(
                fusion -> {
                    assertEquals(4.0, fusion.apply("0.5").applyAsDouble("1.5"));
                }
            );
    }
}
