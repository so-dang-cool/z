package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BooleanFunctionFusionTests {

    @Test
    void boolFn() {
        assertEquals("true", booleanToString.apply(true));
    }

    @Test
    void boolFn_deep() {
        assertEquals("true", Z.with(booleanToString).apply(true));
    }

    @Test
    void boolFn_to_fn() {
        assertEquals(
            "true!",
            Z.fuse(booleanToString, addExclamationMark).apply(true)
        );
    }

    @Test
    void boolFn_to_fn_deep() {
        assertEquals(
            "true!",
            Z.with(booleanToString).fuse(addExclamationMark).apply(true)
        );
    }

    @Test
    void boolFn_to_fn_deeper() {
        assertEquals(
            "true!",
            Z.with(booleanToString).fusing(addExclamationMark).apply(true)
        );
    }

    @Test
    void boolFn_to_bifn() {
        assertEquals(
            "true...",
            Z.fuse(booleanToString, concat).apply(true).apply("...")
        );

        assertEquals(
            "true...",
            Z.with(booleanToString).fuse(concat).apply(true).apply("...")
        );

        // TODO: Implement with currying
        // assertEquals(
        //     "true...",
        //     Z.with(booleanToString).fusing(concat).apply(true).apply("...")
        // );

        assertEquals(
            "true...",
            Z.with(booleanToString).fuse(concat, "...").apply(true)
        );

        assertEquals(
            "true...",
            Z.with(booleanToString).fusing(concat, "...").apply(true)
        );
    }

    @Test
    void boolFn_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(maybeOneAsString, stringToDouble).applyAsDouble(true)
        );

        assertEquals(
            1.0,
            Z.with(maybeOneAsString).fuse(stringToDouble).applyAsDouble(true)
        );

        assertEquals(
            1.0,
            Z.with(maybeOneAsString).fusing(stringToDouble).applyAsDouble(true)
        );
    }

    @Test
    void boolFn_to_toDblBin() {
        assertEquals(
            3.0,
            Z
                .fuse(maybeOneAsString, addStringsAsDouble)
                .apply(true)
                .applyAsDouble("2.0")
        );

        assertEquals(
            3.0,
            Z
                .with(maybeOneAsString)
                .fuse(addStringsAsDouble)
                .apply(true)
                .applyAsDouble("2.0")
        );

        // TODO: Implement with currying
        // assertEquals(
        //     3.0,
        //     Z
        //         .with(maybeOneAsString)
        //         .fusing(addStringsAsDouble)
        //
        //         .apply(true)
        //         .applyAsDouble("2.0")
        // );

        assertEquals(
            3.0,
            Z
                .with(maybeOneAsString)
                .fuse(addStringsAsDouble, "2.0")
                .applyAsDouble(true)
        );

        assertEquals(
            3.0,
            Z
                .with(maybeOneAsString)
                .fusing(addStringsAsDouble, "2.0")
                .applyAsDouble(true)
        );
    }

    @Test
    void boolFn_to_toInt() {
        assertEquals(1, Z.fuse(maybeOneAsString, stringToInt).applyAsInt(true));

        assertEquals(
            1,
            Z.with(maybeOneAsString).fuse(stringToInt).applyAsInt(true)
        );

        assertEquals(
            1,
            Z.with(maybeOneAsString).fusing(stringToInt).applyAsInt(true)
        );
    }

    @Test
    void boolFn_to_toIntBifn() {
        assertEquals(
            3,
            Z
                .fuse(maybeOneAsString, addStringsAsInt)
                .apply(true)
                .applyAsInt("2")
        );

        assertEquals(
            3,
            Z
                .with(maybeOneAsString)
                .fuse(addStringsAsInt)
                .apply(true)
                .applyAsInt("2")
        );

        // TODO: Implement with currying
        // assertEquals(
        //     3,
        //     Z
        //         .with(maybeOneAsString)
        //         .fusing(addStringsAsInt)
        //
        //         .apply(true)
        //         .applyAsInt("2")
        // );

        assertEquals(
            3,
            Z.with(maybeOneAsString).fuse(addStringsAsInt, "2").applyAsInt(true)
        );

        assertEquals(
            3,
            Z
                .with(maybeOneAsString)
                .fusing(addStringsAsInt, "2")
                .applyAsInt(true)
        );
    }

    @Test
    void boolFn_to_toLongFn() {
        assertEquals(
            1L,
            Z.fuse(maybeOneAsString, stringToLong).applyAsLong(true)
        );

        assertEquals(
            1L,
            Z.with(maybeOneAsString).fuse(stringToLong).applyAsLong(true)
        );

        assertEquals(
            1L,
            Z.with(maybeOneAsString).fusing(stringToLong).applyAsLong(true)
        );
    }

    @Test
    void boolFn_to_toLongBifn() {
        assertEquals(
            3L,
            Z
                .fuse(maybeOneAsString, addStringsAsLong)
                .apply(true)
                .applyAsLong("2")
        );

        assertEquals(
            3L,
            Z
                .with(maybeOneAsString)
                .fuse(addStringsAsLong)
                .apply(true)
                .applyAsLong("2")
        );

        // TODO: Implement with currying
        // assertEquals(
        //     3L,
        //     Z
        //         .with(maybeOneAsString)
        //         .fusing(addStringsAsLong)
        //
        //         .apply(true)
        //         .applyAsLong("2")
        // );

        assertEquals(
            3L,
            Z
                .with(maybeOneAsString)
                .fuse(addStringsAsLong, "2")
                .applyAsLong(true)
        );

        assertEquals(
            3L,
            Z
                .with(maybeOneAsString)
                .fusing(addStringsAsLong, "2")
                .applyAsLong(true)
        );
    }

    @Test
    void boolFn_to_pred() {
        assertFalse(Z.fuse(booleanToString, isEmpty).test(true));

        assertFalse(Z.with(booleanToString).fuse(isEmpty).test(true));

        assertFalse(Z.with(booleanToString).fusing(isEmpty).test(true));
    }

    @Test
    void boolFn_to_bipred() {
        assertTrue(Z.fuse(booleanToString, startsWith).apply(true).test("t"));

        assertTrue(
            Z.with(booleanToString).fuse(startsWith).apply(true).test("t")
        );

        // TODO: Implement with currying
        // assertTrue(Z.with(booleanToString).fusing(startsWith).apply(true).test("t"));

        assertTrue(Z.with(booleanToString).fuse(startsWith, "t").test(true));

        assertTrue(Z.with(booleanToString).fusing(startsWith, "t").test(true));
    }

    @Evil
    @Test
    void boolFn_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(booleanToString, saveStringA).accept(true);

            assertEquals("true", consumedStringA);

            /* deep */

            consumedStringA = "";

            Z.with(booleanToString).fuse(saveStringA).accept(true);

            assertEquals("true", consumedStringA);

            /* deeper */

            consumedStringA = "";

            Z.with(booleanToString).fusing(saveStringA).accept(true);

            assertEquals("true", consumedStringA);
        }
    }

    @Evil
    @Test
    void boolFn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .fuse(booleanToString, saveStringsBandC)
                    .apply(true)
                    .accept("true and a half");

                assertEquals("true", consumedStringB);
                assertEquals("true and a half", consumedStringC);

                /* deep */
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .with(booleanToString)
                    .fuse(saveStringsBandC)
                    .apply(true)
                    .accept("true and a half");

                assertEquals("true", consumedStringB);
                assertEquals("true and a half", consumedStringC);

                // TODO: Implement with currying
                /* deeper */
                // consumedStringB = "";
                // consumedStringC = "";

                // Z
                //     .with(booleanToString)
                //     .fusing(saveStringsBandC)
                //     .resolve
                //     .apply(true)
                //     .accept("true and a half");

                // assertEquals("true", consumedStringB);
                // assertEquals("true and a half", consumedStringC);

                /* deep */
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .with(booleanToString)
                    .fuse(saveStringsBandC, "true and a half")
                    .accept(true);

                assertEquals("true", consumedStringB);
                assertEquals("true and a half", consumedStringC);

                /* deeper */
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .with(booleanToString)
                    .fusing(saveStringsBandC, "true and a half")
                    .accept(true);

                assertEquals("true", consumedStringB);
                assertEquals("true and a half", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .fuse(booleanToString, saveStringDDoubleB)
                    .apply(true)
                    .accept(4.5);

                assertEquals("true", consumedStringD);
                assertEquals(4.5, consumedDoubleB);

                /* deep */
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .with(booleanToString)
                    .fuse(saveStringDDoubleB)
                    .apply(true)
                    .accept(4.5);

                assertEquals("true", consumedStringD);
                assertEquals(4.5, consumedDoubleB);

                // TODO: Implement with currying
                /* deeper */
                // consumedStringD = "";
                // consumedDoubleB = 0.0;

                // Z
                //     .with(booleanToString)
                //     .fusing(saveStringDDoubleB)
                //
                //     .apply(true)
                //     .accept(4.5);

                // assertEquals("true", consumedStringD);
                // assertEquals(4.5, consumedDoubleB);

                /* deep */
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .with(booleanToString)
                    .fuse(saveStringDDoubleB, 4.5)
                    .accept(true);

                assertEquals("true", consumedStringD);
                assertEquals(4.5, consumedDoubleB);

                /* deeper */
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .with(booleanToString)
                    .fusing(saveStringDDoubleB, 4.5)
                    .accept(true);

                assertEquals("true", consumedStringD);
                assertEquals(4.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(booleanToString, saveStringEIntB).apply(true).accept(6);

                assertEquals("true", consumedStringE);
                assertEquals(6, consumedIntB);

                /* deep */
                consumedStringE = "";
                consumedIntB = 0;

                Z
                    .with(booleanToString)
                    .fuse(saveStringEIntB)
                    .apply(true)
                    .accept(6);

                assertEquals("true", consumedStringE);
                assertEquals(6, consumedIntB);

                // TODO: Implement with currying
                // /* deeper */
                // consumedStringE = "";
                // consumedIntB = 0;

                // Z.with(booleanToString).fusing(saveStringEIntB).apply(true).accept(6);

                // assertEquals("true", consumedStringE);
                // assertEquals(6, consumedIntB);

                /* deep */
                consumedStringE = "";
                consumedIntB = 0;

                Z.with(booleanToString).fuse(saveStringEIntB, 6).accept(true);

                assertEquals("true", consumedStringE);
                assertEquals(6, consumedIntB);

                /* deeper */
                consumedStringE = "";
                consumedIntB = 0;

                Z.with(booleanToString).fusing(saveStringEIntB, 6).accept(true);

                assertEquals("true", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void boolFn_to_objLongCns() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(booleanToString, saveStringFLongB).apply(true).accept(8);

                assertEquals("true", consumedStringF);
                assertEquals(8, consumedLongB);

                /* deep */
                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .with(booleanToString)
                    .fuse(saveStringFLongB)
                    .apply(true)
                    .accept(8);

                assertEquals("true", consumedStringF);
                assertEquals(8, consumedLongB);

                // TODO: Implement with currying
                // /* deeper */
                // consumedStringF = "";
                // consumedLongB = 0L;

                // Z.with(booleanToString).fusing(saveStringFLongB).apply(true).accept(8);

                // assertEquals("true", consumedStringF);
                // assertEquals(8, consumedLongB);

                /* deep */
                consumedStringF = "";
                consumedLongB = 0L;

                Z.with(booleanToString).fuse(saveStringFLongB, 8).accept(true);

                assertEquals("true", consumedStringF);
                assertEquals(8, consumedLongB);

                /* deeper */
                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .with(booleanToString)
                    .fusing(saveStringFLongB, 8)
                    .accept(true);

                assertEquals("true", consumedStringF);
                assertEquals(8, consumedLongB);
            }
        }
    }

    @Test
    void boolFn_to_unop() {
        assertEquals(
            "true?",
            Z.fuse(booleanToString, addQuestionMark).apply(true)
        );

        assertEquals(
            "true?",
            Z.with(booleanToString).fuse(addQuestionMark).apply(true)
        );

        assertEquals(
            "true?",
            Z.with(booleanToString).fusing(addQuestionMark).apply(true)
        );
    }

    @Test
    void boolFn_to_biop() {
        assertEquals(
            "same-ish",
            Z.fuse(booleanToString, relation).apply(true).apply("TRUE")
        );

        assertEquals(
            "same-ish",
            Z.with(booleanToString).fuse(relation).apply(true).apply("TRUE")
        );

        // TODO: Implement with currying
        // assertEquals(
        //     "same-ish",
        //     Z.with(booleanToString).fusing(relation).apply(true).apply("TRUE")
        // );

        assertEquals(
            "same-ish",
            Z.with(booleanToString).fuse(relation, "TRUE").apply(true)
        );

        assertEquals(
            "same-ish",
            Z.with(booleanToString).fusing(relation, "TRUE").apply(true)
        );
    }
}
