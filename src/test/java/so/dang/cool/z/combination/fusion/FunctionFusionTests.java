package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class FunctionFusionTests {

    @Test
    void fn() {
        assertEquals("hello", trim.apply(" hello "));
    }

    @Test
    void fn_deep() {
        assertEquals("hello", Z.fuse(trim).apply(" hello "));
    }

    @Test
    void fn_to_fn() {
        assertEquals("hello", Z.fuse(trim).fuse(toLower).apply(" HeLlO "));
    }

    @Test
    void fn_to_bifn() {
        assertEquals(
            "hello!",
            Z.fuse(trim).fuse(concat).apply(" hello ").apply("!")
        );
    }

    @Test
    void fn_to_toDblFn() {
        assertEquals(
            1.0,
            Z.fuse(trim).fuse(stringToDouble).applyAsDouble(" 1.0 ")
        );
    }

    @Test
    void fn_to_toDblBifn() {
        assertEquals(
            3.0,
            Z
                .fuse(trim)
                .fuse(addStringsAsDouble)
                .apply(" 1.0 ")
                .applyAsDouble("2.0")
        );
    }

    @Test
    void fn_to_toIntFn() {
        assertEquals(1, Z.fuse(trim).fuse(stringToInt).applyAsInt(" 1 "));
    }

    @Test
    void fn_to_toIntBifn() {
        assertEquals(
            3,
            Z.fuse(trim).fuse(addStringsAsInt).apply(" 1 ").applyAsInt("2")
        );
    }

    @Test
    void fn_to_toLongFn() {
        assertEquals(1L, Z.fuse(trim).fuse(stringToLong).applyAsLong(" 1 "));
    }

    @Test
    void fn_to_toLongBifn() {
        assertEquals(
            3L,
            Z.fuse(trim).fuse(addStringsAsLong).apply(" 1 ").applyAsLong("2")
        );
    }

    @Test
    void fn_to_pred() {
        assertTrue(Z.fuse(trim).fuse(isEmpty).test(" "));
    }

    @Test
    void fn_to_bipred() {
        assertTrue(
            Z.fuse(trim).fuse(doesStartWith).apply(" hello ").test("hell")
        );
        assertFalse(
            Z.fuse(trim).fuse(doesNotStartWith).apply(" hello ").test("hell")
        );
    }

    @Evil
    @Test
    void fn_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(trim).fuse(saveStringA).accept(" hello ");

            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void fn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .fuse(trim)
                    .fuse(saveStringsBandC)
                    .apply(" greetings ")
                    .accept("earthlings");

                assertEquals("greetings", consumedStringB);
                assertEquals("earthlings", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .fuse(trim)
                    .fuse(saveStringDDoubleB)
                    .apply(" five ")
                    .accept(5.0);

                assertEquals("five", consumedStringD);
                assertEquals(5.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(trim).fuse(saveStringEIntB).apply(" six ").accept(6);

                assertEquals("six", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objLongFn() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(trim).fuse(saveStringFLongB).apply(" seven ").accept(7L);

                assertEquals("seven", consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void fn_to_toUnop() {
        assertEquals(
            "goodbye?",
            Z.fuse(trim).fuse(addQuestionMark).apply(" goodbye ")
        );
    }

    @Test
    void fn_to_toBiop() {
        assertEquals(
            "same-ish",
            Z.fuse(trim).fuse(relation).apply(" yo man ").apply("YO MAN")
        );
    }
}
