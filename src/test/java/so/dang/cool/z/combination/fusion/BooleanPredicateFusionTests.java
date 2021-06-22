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

        assertTrue(Z.with(booleanId).resolve().test(true));
    }

    @Test
    void boolPred_to_boolFn() {
        Stream
            .of(
                Z.fuse(booleanId, booleanToString),
                Z.with(booleanId).fuse(booleanToString),
                Z.with(booleanId).fusing(booleanToString).resolve()
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
                Z.with(booleanId).fuse(maybeOneAsDouble),
                Z.with(booleanId).fusing(maybeOneAsDouble).resolve()
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
                Z.with(booleanId).fuse(maybeTwoAsInt),
                Z.with(booleanId).fusing(maybeTwoAsInt).resolve()
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
                Z.with(booleanId).fuse(maybeThreeAsLong),
                Z.with(booleanId).fusing(maybeThreeAsLong).resolve()
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
                Z.with(booleanId).fuse(not),
                Z.with(booleanId).fusing(not).resolve()
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
                    Z.with(booleanId).fuse(saveBooleanA),
                    Z.with(booleanId).fusing(saveBooleanA).resolve()
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
