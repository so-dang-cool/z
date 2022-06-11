package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BiPredicateFusionTests {

    @Test
    void bipred() {
        assertTrue(startsWith.test("yolo", "yo"));
    }

    @Test
    void bipred_deep() {
        assertTrue(Z.fuse(startsWith).apply("yolo").test("yo"));
    }

    @Test
    void bipred_to_boolFn() {
        assertEquals(
            "true",
            Z.fuse(startsWith).fuse(booleanToString).apply("yolo").apply("yo")
        );
    }

    @Test
    void bipred_to_toDblFn() {
        assertEquals(
            1.0,
            Z
                .fuse(startsWith)
                .fuse(maybeOneAsDouble)
                .apply("yolo")
                .applyAsDouble("yo")
        );
    }

    @Test
    void bipred_to_toIntFn() {
        assertEquals(
            2,
            Z
                .fuse(startsWith)
                .fuse(maybeTwoAsInt)
                .apply("yolo")
                .applyAsInt("yo")
        );
    }

    @Test
    void bipred_to_toLongFn() {
        assertEquals(
            3L,
            Z
                .fuse(startsWith)
                .fuse(maybeThreeAsLong)
                .apply("yolo")
                .applyAsLong("yo")
        );
    }

    @Test
    void bipred_to_pred() {
        assertFalse(Z.fuse(startsWith).fuse(not).apply("yolo").test("yo"));
    }

    @Evil
    @Test
    void bipred_to_cns() {
        synchronized (consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(startsWith).fuse(saveBooleanA).apply("yolo").accept("yo");

            assertTrue(consumedBooleanA);
        }
    }

    @Test
    void bipred_to_unop() {
        assertTrue(Z.fuse(startsWith).fuse(booleanId).apply("yolo").test("yo"));
    }
}
