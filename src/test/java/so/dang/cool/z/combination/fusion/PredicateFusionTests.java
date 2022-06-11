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
        assertTrue(Z.fuse(isEmpty).test(""));
    }

    @Test
    void pred_to_boolFn() {
        Stream
            .of(Z.fuse(isEmpty).fuse(booleanToString))
            .forEach(
                fusion -> {
                    assertEquals("true", fusion.apply(""));
                }
            );
    }

    @Test
    void pred_to_toDblFn() {
        Stream
            .of(Z.fuse(isEmpty).fuse(maybeOneAsDouble))
            .forEach(
                fusion -> {
                    assertEquals(1.0, fusion.applyAsDouble(""));
                }
            );
    }

    @Test
    void pred_to_toIntFn() {
        Stream
            .of(Z.fuse(isEmpty).fuse(maybeTwoAsInt))
            .forEach(
                fusion -> {
                    assertEquals(2, fusion.applyAsInt(""));
                }
            );
    }

    @Test
    void pred_to_toLongFn() {
        Stream
            .of(Z.fuse(isEmpty).fuse(maybeThreeAsLong))
            .forEach(
                fusion -> {
                    assertEquals(3L, fusion.applyAsLong(""));
                }
            );
    }

    @Test
    void pred_to_boolPred() {
        Stream
            .of(Z.fuse(isEmpty).fuse(not))
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
                .of(Z.fuse(isEmpty).fuse(saveBooleanA))
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
