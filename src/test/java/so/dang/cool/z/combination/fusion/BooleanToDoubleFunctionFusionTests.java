package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanToDoubleFunctionFusionTests {

    @Test
    void boolToDbl() {
        assertEquals(1.0, maybeOneAsDouble.applyAsDouble(true));

        assertEquals(1.0, Z.with(maybeOneAsDouble).applyAsDouble(true));
    }

    @Test
    void boolToDbl_to_dblFn() {
        Stream
            .of(
                Z.fuse(maybeOneAsDouble, doubleToString),
                Z.with(maybeOneAsDouble).fuse(doubleToString),
                Z.with(maybeOneAsDouble).fusing(doubleToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("1.0", fusion.apply(true));
                }
            );
    }

    @Test
    void boolToDbl_to_dblToInt() {
        Stream
            .of(
                Z.fuse(maybeOneAsDouble, doubleToInt),
                Z.with(maybeOneAsDouble).fuse(doubleToInt),
                Z.with(maybeOneAsDouble).fusing(doubleToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(1, fusion.applyAsInt(true));
                }
            );
    }

    @Test
    void boolToDbl_to_dblToLong() {
        Stream
            .of(
                Z.fuse(maybeOneAsDouble, doubleToLong),
                Z.with(maybeOneAsDouble).fuse(doubleToLong),
                Z.with(maybeOneAsDouble).fusing(doubleToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(1L, fusion.applyAsLong(true));
                }
            );
    }

    @Test
    void boolToDbl_to_dblPred() {
        Stream
            .of(
                Z.fuse(maybeOneAsDouble, isDoubleOne),
                Z.with(maybeOneAsDouble).fuse(isDoubleOne),
                Z.with(maybeOneAsDouble).fusing(isDoubleOne)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(true));
                }
            );
    }

    @Evil
    @Test
    void boolToDbl_to_dblCns() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.fuse(maybeOneAsDouble, saveDoubleA),
                    Z.with(maybeOneAsDouble).fuse(saveDoubleA),
                    Z.with(maybeOneAsDouble).fusing(saveDoubleA)
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        fusion.accept(true);

                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Test
    void boolToDbl_to_dblUnop() {
        Stream
            .of(
                Z.fuse(maybeOneAsDouble, addOneToDouble),
                Z.with(maybeOneAsDouble).fuse(addOneToDouble),
                Z.with(maybeOneAsDouble).fusing(addOneToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(2.0, fusion.applyAsDouble(true));
                }
            );
    }

    @Test
    void boolToDbl_to_dblBiop() {
        Stream
            .of(
                Z.fuse(maybeOneAsDouble, addDoubles),
                Z.with(maybeOneAsDouble).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.apply(true).applyAsDouble(0.5));
                }
            );

        Stream
            .of(
                Z.with(maybeOneAsDouble).fuse(addDoubles, 0.5),
                Z.with(maybeOneAsDouble).fusing(addDoubles, 0.5)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.applyAsDouble(true));
                }
            );
    }
}
