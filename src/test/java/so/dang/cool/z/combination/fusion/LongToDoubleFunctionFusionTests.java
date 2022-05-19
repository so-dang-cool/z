package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class LongToDoubleFunctionFusionTests {

    @Test
    void longToDbl() {
        assertEquals(3.0, longToDouble.applyAsDouble(3L));

        assertEquals(3.0, Z.with(longToDouble).applyAsDouble(3L));
    }

    @Test
    void longToDbl_to_dblFn() {
        Stream
            .of(
                Z.fuse(longToDouble, doubleToString),
                Z.with(longToDouble).fuse(doubleToString),
                Z.with(longToDouble).fusing(doubleToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("1.0", fusion.apply(1L));
                }
            );
    }

    @Test
    void longToDbl_to_dblToInt() {
        Stream
            .of(
                Z.fuse(longToDouble, doubleToInt),
                Z.with(longToDouble).fuse(doubleToInt),
                Z.with(longToDouble).fusing(doubleToInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(2L));
                }
            );
    }

    @Test
    void longToDbl_to_dblToLong() {
        Stream
            .of(
                Z.fuse(longToDouble, doubleToLong),
                Z.with(longToDouble).fuse(doubleToLong),
                Z.with(longToDouble).fusing(doubleToLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(3L));
                }
            );
    }

    @Test
    void longToDbl_to_dblPred() {
        Stream
            .of(
                Z.fuse(longToDouble, isDoubleOne),
                Z.with(longToDouble).fuse(isDoubleOne),
                Z.with(longToDouble).fusing(isDoubleOne)
            )
            .forEach(
                fusion -> {
                    assertTrue(fusion.test(1L));
                }
            );
    }

    @Evil
    @Test
    void longToDbl_to_dblCns() {
        synchronized (consumedDoubleA) {
            Stream
                .of(
                    Z.fuse(longToDouble, saveDoubleA),
                    Z.with(longToDouble).fuse(saveDoubleA),
                    Z.with(longToDouble).fusing(saveDoubleA)
                )
                .forEach(
                    fusion -> {
                        consumedDoubleA = 0.0;

                        fusion.accept(1L);

                        assertEquals(1.0, consumedDoubleA);
                    }
                );
        }
    }

    @Test
    void longToDbl_to_dblUnop() {
        Stream
            .of(
                Z.fuse(longToDouble, addOneToDouble),
                Z.with(longToDouble).fuse(addOneToDouble),
                Z.with(longToDouble).fusing(addOneToDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(2.0, fusion.applyAsDouble(1L));
                }
            );
    }

    @Test
    void longToDbl_to_dblBiop() {
        Stream
            .of(
                Z.fuse(longToDouble, addDoubles),
                Z.with(longToDouble).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.apply(1L).applyAsDouble(0.5));
                }
            );

        Stream
            .of(
                Z.with(longToDouble).fuse(addDoubles, 0.5),
                Z.with(longToDouble).fusing(addDoubles, 0.5)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.applyAsDouble(1L));
                }
            );
    }
}
