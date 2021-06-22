package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class IntPredicateFusionTests {

    @Test
    void intPred() {
        assertTrue(isIntTwo.test(2));

        assertTrue(Z.with(isIntTwo).resolve().test(2));
    }

    @Test
    void intPred_to_fn() {
        Stream
            .of(
                Z.fuse(isIntTwo, booleanToString),
                Z.with(isIntTwo).fuse(booleanToString),
                Z.with(isIntTwo).fusing(booleanToString).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals("true", fusion.apply(2));
                }
            );
    }

    @Test
    void intPred_to_toDblFn() {
        Stream
            .of(
                Z.fuse(isIntTwo, maybeOneAsDouble),
                Z.with(isIntTwo).fuse(maybeOneAsDouble),
                Z.with(isIntTwo).fusing(maybeOneAsDouble).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(2));
                }
            );
    }

    @Test
    void intPred_to_toIntFn() {
        Stream
            .of(
                Z.fuse(isIntTwo, maybeTwoAsInt),
                Z.with(isIntTwo).fuse(maybeTwoAsInt),
                Z.with(isIntTwo).fusing(maybeTwoAsInt).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(2));
                }
            );
    }

    @Test
    void intPred_to_toLongFn() {
        Stream
            .of(
                Z.fuse(isIntTwo, maybeThreeAsLong),
                Z.with(isIntTwo).fuse(maybeThreeAsLong),
                Z.with(isIntTwo).fusing(maybeThreeAsLong).resolve()
            )
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(2));
                }
            );
    }

    @Test
    void intPred_to_boolPred() {
        Stream
            .of(
                Z.fuse(isIntTwo, not),
                Z.with(isIntTwo).fuse(not),
                Z.with(isIntTwo).fusing(not).resolve()
            )
            .forEach(
                fusion -> {
                    assertFalse(fusion.test(2));
                }
            );
    }

    @Evil
    @Test
    void intPred_to_cns() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.fuse(isIntTwo, saveBooleanA),
                    Z.with(isIntTwo).fuse(saveBooleanA),
                    Z.with(isIntTwo).fusing(saveBooleanA).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        fusion.accept(2);

                        assertTrue(consumedBooleanA);
                    }
                );
        }
    }
}
