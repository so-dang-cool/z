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

        assertEquals(1.0, Z.fuse(maybeOneAsDouble).applyAsDouble(true));
    }

    @Test
    void boolToDbl_to_dblFn() {
        Stream
            .of(
                Z.fuse(maybeOneAsDouble, doubleToString),
                Z.fuse(maybeOneAsDouble).fuse(doubleToString)
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
                Z.fuse(maybeOneAsDouble).fuse(doubleToInt)
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
                Z.fuse(maybeOneAsDouble).fuse(doubleToLong)
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
                Z.fuse(maybeOneAsDouble).fuse(isDoubleOne)
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
                    Z.fuse(maybeOneAsDouble).fuse(saveDoubleA)
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
                Z.fuse(maybeOneAsDouble).fuse(addOneToDouble)
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
                Z.fuse(maybeOneAsDouble).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.apply(true).applyAsDouble(0.5));
                }
            );

        assertEquals(
            1.5,
            Z.fuse(maybeOneAsDouble).fuse(addDoubles, 0.5).applyAsDouble(true)
        );
    }
}
