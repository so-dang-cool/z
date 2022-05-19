package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanPredicateFusionTests {

    @Test
    void boolPred() {
        assertTrue(booleanId.test(true));

        assertTrue(Z.fuse(booleanId).test(true));
    }

    @Test
    void boolPred_to_boolFn() {
        Stream
            .of(
                Z.fuse(booleanId, booleanToString),
                Z.fuse(booleanId).fuse(booleanToString),
                Z.fuse(booleanId).fusing(booleanToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("true", fusion.apply(true));
                }
            );
    }

    @Test
    void boolPred_to_toDblFn() {
        Stream
            .of(
                Z.fuse(booleanId, maybeOneAsDouble),
                Z.fuse(booleanId).fuse(maybeOneAsDouble),
                Z.fuse(booleanId).fusing(maybeOneAsDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(true));
                }
            );
    }

    @Test
    void boolPred_to_toIntFn() {
        Stream
            .of(
                Z.fuse(booleanId, maybeTwoAsInt),
                Z.fuse(booleanId).fuse(maybeTwoAsInt),
                Z.fuse(booleanId).fusing(maybeTwoAsInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(true));
                }
            );
    }

    @Test
    void boolPred_to_boolToLongFn() {
        Stream
            .of(
                Z.fuse(booleanId, maybeThreeAsLong),
                Z.fuse(booleanId).fuse(maybeThreeAsLong),
                Z.fuse(booleanId).fusing(maybeThreeAsLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(true));
                }
            );
    }

    @Test
    void boolPred_to_boolPred() {
        Stream
            .of(
                Z.fuse(booleanId, not),
                Z.fuse(booleanId).fuse(not),
                Z.fuse(booleanId).fusing(not)
            )
            .forEach(
                fusion -> {
                    assertFalse(fusion.test(true));
                }
            );
    }

    @Evil
    @Test
    void boolPred_to_cns() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.fuse(booleanId, saveBooleanA),
                    Z.fuse(booleanId).fuse(saveBooleanA),
                    Z.fuse(booleanId).fusing(saveBooleanA)
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        fusion.accept(true);

                        assertTrue(consumedBooleanA);
                    }
                );
        }
    }
}
