package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class PredicateFusionTests {

    @Test
    void pred() {
        assertTrue(isEmpty.test(""));
        assertTrue(Z.with(isEmpty).test(""));
    }

    @Test
    void pred_to_boolFn() {
        Stream
            .of(
                Z.fuse(isEmpty, booleanToString),
                Z.with(isEmpty).fuse(booleanToString),
                Z.with(isEmpty).fusing(booleanToString)
            )
            .forEach(
                fusion -> {
                    assertEquals("true", fusion.apply(""));
                }
            );
    }

    @Test
    void pred_to_toDblFn() {
        Stream
            .of(
                Z.fuse(isEmpty, maybeOneAsDouble),
                Z.with(isEmpty).fuse(maybeOneAsDouble),
                Z.with(isEmpty).fusing(maybeOneAsDouble)
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(""));
                }
            );
    }

    @Test
    void pred_to_toIntFn() {
        Stream
            .of(
                Z.fuse(isEmpty, maybeTwoAsInt),
                Z.with(isEmpty).fuse(maybeTwoAsInt),
                Z.with(isEmpty).fusing(maybeTwoAsInt)
            )
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(""));
                }
            );
    }

    @Test
    void pred_to_toLongFn() {
        Stream
            .of(
                Z.fuse(isEmpty, maybeThreeAsLong),
                Z.with(isEmpty).fuse(maybeThreeAsLong),
                Z.with(isEmpty).fusing(maybeThreeAsLong)
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(""));
                }
            );
    }

    @Test
    void pred_to_boolPred() {
        Stream
            .of(
                Z.fuse(isEmpty, not),
                Z.with(isEmpty).fuse(not),
                Z.with(isEmpty).fusing(not)
            )
            .forEach(
                fusion -> {
                    assertFalse(fusion.test(""));
                }
            );
    }

    @Evil
    @Test
    void pred_to_cns() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.fuse(isEmpty, saveBooleanA),
                    Z.with(isEmpty).fuse(saveBooleanA),
                    Z.with(isEmpty).fusing(saveBooleanA)
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        fusion.accept("");

                        assertTrue(consumedBooleanA);
                    }
                );
        }
    }
}
