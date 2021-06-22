package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoubleBinaryOperatorFusionTests {

    @Test
    void dblBiop() {
        assertEquals(3.0, addDoubles.applyAsDouble(1.0, 2.0));

        assertEquals(
            3.0,
            Z.with(addDoubles).resolve().apply(1.0).applyAsDouble(2.0)
        );
    }

    @Test
    void dblBiop_to_dblFn() {
        Stream
            .of(
                Z.fuse(addDoubles, doubleToString),
                Z.with(addDoubles).fuse(doubleToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("3.0", fusion.apply(1.0).apply(2.0));
                }
            );
    }

    @Test
    void dblBiop_to_dblToInt() {
        Stream
            .of(
                Z.fuse(addDoubles, doubleToInt),
                Z.with(addDoubles).fuse(doubleToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(3, fusion.apply(1.2).applyAsInt(2.3));
                }
            );
    }

    @Test
    void dblBiop_to_dblToLong() {
        Stream
            .of(
                Z.fuse(addDoubles, doubleToLong),
                Z.with(addDoubles).fuse(doubleToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(10L, fusion.apply(4.5).applyAsLong(5.6));
                }
            );
    }

    @Test
    void dblBiop_to_dblPred() {
        Stream
            .of(
                Z.fuse(addDoubles, isDoubleOne),
                Z.with(addDoubles).fuse(isDoubleOne)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.apply(0.5).test(0.5));
                }
            );
    }

    @Evil
    @Test
    void dblBiop_to_dblCns() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.fuse(addDoubles, saveDoubleA),
                    Z.with(addDoubles).fuse(saveDoubleA)
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        fusion.apply(1.0).accept(5.5);

                        assertEquals(6.5, consumedDoubleA);
                    }
                );
        }
    }

    @Test
    void dblBiop_to_dblUnop() {
        Stream
            .of(
                Z.fuse(addDoubles, addOneToDouble),
                Z.with(addDoubles).fuse(addOneToDouble),
                Z.with(addDoubles).fusing(addOneToDouble).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(7.5, fusion.apply(0.5).applyAsDouble(6.0));
                }
            );
    }

    @Test
    void dblBiop_to_dblBiop() {
        Stream
            .of(
                Z.fuse(addDoubles, addDoubles),
                Z.with(addDoubles).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(
                        4.0,
                        fusion.apply(0.5).apply(1.5).applyAsDouble(2.0)
                    );
                }
            );

        Stream
            .of(
                Z.with(addDoubles).fuse(addDoubles, 2.0),
                Z.with(addDoubles).fusing(addDoubles, 2.0).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(4.0, fusion.apply(0.5).applyAsDouble(1.5));
                }
            );
    }
}
