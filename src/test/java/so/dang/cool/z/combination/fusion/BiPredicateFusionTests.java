package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import java.util.stream.Stream;
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
        assertTrue(Z.with(startsWith).resolve().apply("yolo").test("yo"));
    }

    @Test
    void bipred_to_boolFn() {
        assertEquals(
            "true",
            Z.fuse(startsWith, booleanToString).apply("yolo").apply("yo")
        );
    }

    @Test
    void bipred_to_boolFn_deep() {
        assertEquals(
            "true",
            Z.with(startsWith).fuse(booleanToString).apply("yolo").apply("yo")
        );
    }

    @Test
    void bipred_to_boolFn_deeper() {
        assertEquals(
            "true",
            Z
                .with(startsWith)
                .fusing(booleanToString)
                .resolve()
                .apply("yolo")
                .apply("yo")
        );
    }

    @Test
    void bipred_to_toDblFn() {
        Stream
            .of(
                Z.fuse(startsWith, maybeOneAsDouble),
                Z.with(startsWith).fuse(maybeOneAsDouble),
                Z.with(startsWith).fusing(maybeOneAsDouble).resolve()
            )
            .forEach(
                fusion ->
                    assertEquals(1.0, fusion.apply("yolo").applyAsDouble("yo"))
            );
    }

    @Test
    void bipred_to_toIntFn() {
        Stream
            .of(
                Z.fuse(startsWith, maybeTwoAsInt),
                Z.with(startsWith).fuse(maybeTwoAsInt),
                Z.with(startsWith).fusing(maybeTwoAsInt).resolve()
            )
            .forEach(
                fusion -> assertEquals(2, fusion.apply("yolo").applyAsInt("yo"))
            );
    }

    @Test
    void bipred_to_toLongFn() {
        Stream
            .of(
                Z.fuse(startsWith, maybeThreeAsLong),
                Z.with(startsWith).fuse(maybeThreeAsLong),
                Z.with(startsWith).fusing(maybeThreeAsLong).resolve()
            )
            .forEach(
                fusion ->
                    assertEquals(3L, fusion.apply("yolo").applyAsLong("yo"))
            );
    }

    @Test
    void bipred_to_pred() {
        Stream
            .of(
                Z.fuse(startsWith, not),
                Z.with(startsWith).fuse(not),
                Z.with(startsWith).fusing(not).resolve()
            )
            .forEach(fusion -> assertFalse(fusion.apply("yolo").test("yo")));
    }

    @Evil
    @Test
    void bipred_to_cns() {
        synchronized (consumedBooleanA) {
            Stream
                .of(
                    Z.fuse(startsWith, saveBooleanA),
                    Z.with(startsWith).fuse(saveBooleanA),
                    Z.with(startsWith).fusing(saveBooleanA).resolve()
                )
                .forEach(
                    fusion -> {
                        consumedBooleanA = false;

                        Z
                            .fuse(startsWith, saveBooleanA)
                            .apply("yolo")
                            .accept("yo");

                        assertTrue(consumedBooleanA);
                    }
                );
        }
    }

    @Test
    void bipred_to_unop() {
        Stream
            .of(
                Z.fuse(startsWith, booleanId),
                Z.with(startsWith).fuse(booleanId),
                Z.with(startsWith).fusing(booleanId).resolve()
            )
            .forEach(fusion -> assertTrue(fusion.apply("yolo").test("yo")));
    }
}
