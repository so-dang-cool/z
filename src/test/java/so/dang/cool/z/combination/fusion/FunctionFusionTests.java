package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        assertEquals("hello", Z.with(trim).apply(" hello "));
    }

    @Test
    void fn_to_fn() {
        assertEquals("hello", Z.fuse(trim, toLower).apply(" HeLlO "));
    }

    @Test
    void fn_to_fn_deep() {
        assertEquals("hello", Z.with(trim).fuse(toLower).apply(" HeLlO "));
    }

    @Test
    void fn_to_bifn() {
        assertEquals(
            "hello!",
            Z.fuse(trim, concat).apply(" hello ").apply("!")
        );
    }

    @Test
    void fn_to_bifn_deep() {
        assertEquals(
            "hello!",
            Z.with(trim).fuse(concat).apply(" hello ").apply("!")
        );
    }

    @Test
    void fn_to_bifn_deep2() {
        assertEquals("hello!", Z.with(trim).fuse(concat, "!").apply(" hello "));
    }

    @Test
    void fn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(trim, stringToDouble).applyAsDouble(" 1.0 "));
    }

    @Test
    void fn_to_toDblFn_deep() {
        assertEquals(
            1.0,
            Z.with(trim).fuse(stringToDouble).applyAsDouble(" 1.0 ")
        );
    }

    @Test
    void fn_to_toDblBifn() {
        assertEquals(
            3.0,
            Z.fuse(trim, addStringsAsDouble).apply(" 1.0 ").applyAsDouble("2.0")
        );
    }

    @Test
    void fn_to_toDblBifn_deep() {
        assertEquals(
            3.0,
            Z
                .with(trim)
                .fuse(addStringsAsDouble)
                .apply(" 1.0 ")
                .applyAsDouble("2.0")
        );
    }

    @Test
    void fn_to_toDblBifn_deep2() {
        assertEquals(
            3.0,
            Z.with(trim).fuse(addStringsAsDouble, "2.0").applyAsDouble(" 1.0 ")
        );
    }

    @Test
    void fn_to_toIntFn() {
        assertEquals(1, Z.fuse(trim, stringToInt).applyAsInt(" 1 "));
    }

    @Test
    void fn_to_toIntFn_deep() {
        assertEquals(1, Z.with(trim).fuse(stringToInt).applyAsInt(" 1 "));
    }

    @Test
    void fn_to_toIntBifn() {
        assertEquals(
            3,
            Z.fuse(trim, addStringsAsInt).apply(" 1 ").applyAsInt("2")
        );
    }

    @Test
    void fn_to_toIntBifn_deep() {
        assertEquals(
            3,
            Z.with(trim).fuse(addStringsAsInt).apply(" 1 ").applyAsInt("2")
        );
    }

    @Test
    void fn_to_toIntBifn_deep2() {
        assertEquals(
            3,
            Z.with(trim).fuse(addStringsAsInt, "2").applyAsInt("1")
        );
    }

    @Test
    void fn_to_toLongFn() {
        assertEquals(1L, Z.fuse(trim, stringToLong).applyAsLong(" 1 "));
    }

    @Test
    void fn_to_toLongFn_deep() {
        assertEquals(1L, Z.with(trim).fuse(stringToLong).applyAsLong(" 1 "));
    }

    @Test
    void fn_to_toLongBifn() {
        assertEquals(
            3L,
            Z.fuse(trim, addStringsAsLong).apply(" 1 ").applyAsLong("2")
        );
    }

    @Test
    void fn_to_toLongBifn_deep() {
        assertEquals(
            3L,
            Z.with(trim).fuse(addStringsAsLong).apply(" 1 ").applyAsLong("2")
        );
    }

    @Test
    void fn_to_toLongBifn_deep2() {
        assertEquals(
            3L,
            Z.with(trim).fuse(addStringsAsLong, "2").applyAsLong(" 1 ")
        );
    }

    @Test
    void fn_to_pred() {
        assertTrue(Z.fuse(trim, isEmpty).test(" "));
    }

    @Test
    void fn_to_pred_deep() {
        assertTrue(Z.with(trim).fuse(isEmpty).test(" "));
    }

    @Test
    void fn_to_bipred() {
        assertTrue(Z.fuse(trim, startsWith).apply(" hello ").test("hell"));
    }

    @Test
    void fn_to_bipred_deep() {
        assertTrue(Z.with(trim).fuse(startsWith).apply(" hello ").test("hell"));
    }

    @Test
    void fn_to_bipred_deep2() {
        assertTrue(Z.with(trim).fuse(startsWith, "hell").test(" hello "));
    }

    @Evil
    @Test
    void fn_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(trim, saveStringA).accept(" hello ");

            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void fn_to_cns_deep() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.with(trim).fuse(saveStringA).accept(" hello ");

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
                    .fuse(trim, saveStringsBandC)
                    .apply(" greetings ")
                    .accept("earthlings");

                assertEquals("greetings", consumedStringB);
                assertEquals("earthlings", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void fn_to_bicns_deep() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .with(trim)
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
    void fn_to_bicns_deep2() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .with(trim)
                    .fuse(saveStringsBandC, "earthlings")
                    .accept(" greetings ");

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

                Z.fuse(trim, saveStringDDoubleB).apply(" five ").accept(5.0);

                assertEquals("five", consumedStringD);
                assertEquals(5.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objDblCns_deep() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .with(trim)
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
    void fn_to_objDblCns_deep2() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.with(trim).fuse(saveStringDDoubleB, 5.0).accept(" five ");

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

                Z.fuse(trim, saveStringEIntB).apply(" six ").accept(6);

                assertEquals("six", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objIntCns_deep() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.with(trim).fuse(saveStringEIntB).apply(" six ").accept(6);

                assertEquals("six", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objIntCns_deep2() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.with(trim).fuse(saveStringEIntB, 6).accept(" six ");

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

                Z.fuse(trim, saveStringFLongB).apply(" seven ").accept(7L);

                assertEquals("seven", consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objLongFn_deep() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.with(trim).fuse(saveStringFLongB).apply(" seven ").accept(7L);

                assertEquals("seven", consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objLongFn_deep2() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.with(trim).fuse(saveStringFLongB, 7L).accept(" seven ");

                assertEquals("seven", consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void fn_to_toUnop() {
        assertEquals(
            "goodbye?",
            Z.fuse(trim, addQuestionMark).apply(" goodbye ")
        );
    }

    @Test
    void fn_to_toUnop_deep() {
        assertEquals(
            "goodbye?",
            Z.with(trim).fuse(addQuestionMark).apply(" goodbye ")
        );
    }

    @Test
    void fn_to_toBiop() {
        assertEquals(
            "same-ish",
            Z.fuse(trim, relation).apply(" yo man ").apply("YO MAN")
        );
    }

    @Test
    void fn_to_toBiop_deep() {
        assertEquals(
            "same-ish",
            Z.with(trim).fuse(relation).apply(" yo man ").apply("YO MAN")
        );
    }

    @Test
    void fn_to_toBiop_deep2() {
        assertEquals(
            "same-ish",
            Z.with(trim).fuse(relation, "YO MAN").apply(" yo man ")
        );
    }
}
