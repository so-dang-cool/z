package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BinaryOperatorFusionTests {

    @Test
    void biop() {
        assertEquals("same-ish", relation.apply("hi", "HI"));
    }

    @Test
    void biop_deep() {
        assertEquals(
            "same-ish",
            Z.fuseBinaryOperator(relation).apply("hi").apply("HI")
        );
    }

    @Test
    void biop_to_fn() {
        assertEquals(
            "same-ish",
            Z.fuse(relation).fuse(trim).apply("hey").apply("HEY")
        );
    }

    @Test
    void biop_to_toDblFn() {
        assertEquals(
            15.0,
            Z
                .fuse(concatAndAddTrailingZero)
                .fuse(stringToDouble)
                .apply("1")
                .applyAsDouble("5.")
        );
    }

    @Test
    void biop_to_toDblBifn() {
        assertEquals(
            12.0,
            Z
                .fuse(concatAndAddTrailingZero)
                .fuse(addStringsAsDouble)
                .apply("1")
                .apply("0.")
                .applyAsDouble("2.0")
        );
    }

    @Test
    void biop_to_toIntFn() {
        assertEquals(
            120,
            Z
                .fuse(concatAndAddTrailingZero)
                .fuse(stringToInt)
                .apply("1")
                .applyAsInt("2")
        );
    }

    @Test
    void biop_to_toIntBifn() {
        assertEquals(
            237,
            Z
                .fuse(concatAndAddTrailingZero)
                .fuse(addStringsAsInt)
                .apply("2")
                .apply("3")
                .applyAsInt("7")
        );
    }

    @Test
    void biop_to_toLongFn() {
        assertEquals(
            340L,
            Z
                .fuse(concatAndAddTrailingZero)
                .fuse(stringToLong)
                .apply("3")
                .applyAsLong("4")
        );
    }

    @Test
    void biop_to_toLongBifn() {
        assertEquals(
            346L,
            Z
                .fuse(concatAndAddTrailingZero)
                .fuse(addStringsAsLong)
                .apply("3")
                .apply("4")
                .applyAsLong("6")
        );
    }

    @Test
    void biop_to_pred() {
        assertFalse(Z.fuse(relation).fuse(isEmpty).apply("hey").test("HEY"));
    }

    @Test
    void biop_to_bipred() {
        assertTrue(
            Z
                .fuse(relation)
                .fuse(doesStartWith)
                .apply("hey")
                .apply("HEY")
                .test("same")
        );

        assertFalse(
            Z
                .fuse(relation)
                .fuse(doesNotStartWith)
                .apply("hey")
                .apply("HEY")
                .test("same")
        );
    }

    @Evil
    @Test
    void biop_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(relation).fuse(saveStringA).apply("hey").accept("HEY");

            assertEquals("same-ish", consumedStringA);
        }
    }

    @Evil
    @Test
    void biop_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .fuse(relation)
                    .fuse(saveStringsBandC)
                    .apply("hey")
                    .apply("HEY")
                    .accept("mother");

                assertEquals("same-ish", consumedStringB);
                assertEquals("mother", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void biop_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .fuse(relation)
                    .fuse(saveStringDDoubleB)
                    .apply("hey")
                    .apply("HEY")
                    .accept(0.5);

                assertEquals("same-ish", consumedStringD);
                assertEquals(0.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void biop_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z
                    .fuse(relation)
                    .fuse(saveStringEIntB)
                    .apply("hey")
                    .apply("HEY")
                    .accept(111);

                assertEquals("same-ish", consumedStringE);
                assertEquals(111, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void biop_to_objLongFn() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .fuse(relation)
                    .fuse(saveStringFLongB)
                    .apply("hey")
                    .apply("HEY")
                    .accept(22L);

                assertEquals("same-ish", consumedStringF);
                assertEquals(22L, consumedLongB);
            }
        }
    }

    @Test
    void biop_to_unop() {
        assertEquals(
            "same-ish?",
            Z.fuse(relation).fuse(addQuestionMark).apply("hey").apply("HEY")
        );
    }

    @Test
    void biop_to_biop() {
        assertEquals(
            "same-ish",
            Z
                .fuse(relation)
                .fuse(relation)
                .apply("hey")
                .apply("HEY")
                .apply("SAME-ISH")
        );
    }
}
