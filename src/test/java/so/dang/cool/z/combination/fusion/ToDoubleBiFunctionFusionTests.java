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
            Z
                .with(addStringsAsDouble)
                .resolve()
                .apply("1.0")
                .applyAsDouble("2.0")
        );
    }

    @Test
    void toDblBifn_to_dblFn() {
        Stream
            .of(
                Z.fuse(addStringsAsDouble, doubleToString),
                Z.with(addStringsAsDouble).fuse(doubleToString),
                Z.with(addStringsAsDouble).fusing(doubleToString).resolve()
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
                Z.with(addStringsAsDouble).fuse(doubleToInt),
                Z.with(addStringsAsDouble).fusing(doubleToInt).resolve()
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
                Z.with(addStringsAsDouble).fuse(doubleToLong),
                Z.with(addStringsAsDouble).fusing(doubleToLong).resolve()
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
                Z.with(addStringsAsDouble).fuse(isDoubleOne),
                Z.with(addStringsAsDouble).fusing(isDoubleOne).resolve()
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
                    Z.with(addStringsAsDouble).fuse(saveDoubleA),
                    Z.with(addStringsAsDouble).fusing(saveDoubleA).resolve()
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
                Z.with(addStringsAsDouble).fuse(addOneToDouble),
                Z.with(addStringsAsDouble).fusing(addOneToDouble).resolve()
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
                Z.with(addStringsAsDouble).fuse(addDoubles)
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
                Z.with(addStringsAsDouble).fuse(addDoubles, 2.0),
                Z.with(addStringsAsDouble).fusing(addDoubles, 2.0).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(4.0, fusion.apply("0.5").applyAsDouble("1.5"));
                }
            );
    }
}
