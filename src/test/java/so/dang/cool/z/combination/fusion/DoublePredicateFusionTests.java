package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class DoublePredicateFusionTests {

    @Test
    void dblPred() {
        assertTrue(isDoubleOne.test(1.0));

        assertTrue(Z.with(isDoubleOne).test(1.0));
    }

    @Test
    void dblPred_to_boolFn() {
        Stream
            .of(
                Z.fuse(isDoubleOne, booleanToString),
                Z.with(isDoubleOne).fuse(booleanToString),
                Z.with(isDoubleOne).fusing(booleanToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("true", fusion.apply(1.0));
                }
            );
    }

    @Test
    void dblPred_to_boolToDblFn() {
        Stream
            .of(
                Z.fuse(isDoubleOne, maybeOneAsDouble),
                Z.with(isDoubleOne).fuse(maybeOneAsDouble),
                Z.with(isDoubleOne).fusing(maybeOneAsDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(1.0));
                }
            );
    }

    @Test
    void dblPred_to_toIntFn() {
        Stream
            .of(
                Z.fuse(isDoubleOne, maybeTwoAsInt),
                Z.with(isDoubleOne).fuse(maybeTwoAsInt),
                Z.with(isDoubleOne).fusing(maybeTwoAsInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(1.0));
                }
            );
    }

    @Test
    void dblPred_to_toLongFn() {
        Stream
            .of(
                Z.fuse(isDoubleOne, maybeThreeAsLong),
                Z.with(isDoubleOne).fuse(maybeThreeAsLong),
                Z.with(isDoubleOne).fusing(maybeThreeAsLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(1.0));
                }
            );
    }

    @Test
    void dblPred_to_boolPred() {
        Stream
            .of(
                Z.fuse(isDoubleOne, not),
                Z.with(isDoubleOne).fuse(not),
                Z.with(isDoubleOne).fusing(not)
            )
            .forEach(
                fusion -> {
                    assertFalse(fusion.test(1.0));
                }
            );
    }

    @Evil
    @Test
    void dblPred_to_cns() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.fuse(isDoubleOne, saveBooleanA),
                    Z.with(isDoubleOne).fuse(saveBooleanA),
                    Z.with(isDoubleOne).fusing(saveBooleanA)
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        fusion.accept(1.0);

                        assertTrue(consumedBooleanA);
                    }
                );
        }
    }
}
