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

        assertEquals(3.0, Z.fuse(longToDouble).applyAsDouble(3L));
    }

    @Test
    void longToDbl_to_dblFn() {
        Stream
            .of(
                Z.fuse(longToDouble, doubleToString),
                Z.fuse(longToDouble).fuse(doubleToString),
                Z.fuse(longToDouble).fusing(doubleToString)
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
                Z.fuse(longToDouble).fuse(doubleToInt),
                Z.fuse(longToDouble).fusing(doubleToInt)
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
                Z.fuse(longToDouble).fuse(doubleToLong),
                Z.fuse(longToDouble).fusing(doubleToLong)
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
                Z.fuse(longToDouble).fuse(isDoubleOne),
                Z.fuse(longToDouble).fusing(isDoubleOne)
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
                    Z.fuse(longToDouble).fuse(saveDoubleA),
                    Z.fuse(longToDouble).fusing(saveDoubleA)
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
                Z.fuse(longToDouble).fuse(addOneToDouble),
                Z.fuse(longToDouble).fusing(addOneToDouble)
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
                Z.fuse(longToDouble).fuse(addDoubles)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.apply(1L).applyAsDouble(0.5));
                }
            );

        Stream
            .of(
                Z.fuse(longToDouble).fuse(addDoubles, 0.5),
                Z.fuse(longToDouble).fusing(addDoubles, 0.5)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.5, fusion.applyAsDouble(1L));
                }
            );
    }
}
