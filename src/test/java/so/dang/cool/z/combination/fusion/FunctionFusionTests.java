package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class FunctionFusionTests {
    @Test
    void fn_to_fn() {
        assertEquals("hello", Z.fuse(trim, toLower).apply(" HeLlO "));
    }
    
    @Test
    void fn_to_fn_deep() {
        assertEquals("hello", Z.with(trim).fuse(toLower).apply(" HeLlO "));
    }
    
    @Test
    void fn_to_fn_deeper() {
        assertEquals("hello", Z.with(trim).fusing(toLower).resolve().apply(" HeLlO "));
    }

    @Test
    void fn_to_bifn() {
        assertEquals("hello!", Z.fuse(trim, concat).apply(" hello ").apply("!"));
    }

    @Test
    void fn_to_bifn_deep() {
        assertEquals("hello!", Z.with(trim).fuse(concat).apply(" hello ").apply("!"));
    }

    @Test
    void fn_to_bifn_deeper() {
        assertEquals("hello!", Z.with(trim).fusing(concat).resolve().apply(" hello ").apply("!"));
    }

    @Test
    void fn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(trim, stringToDouble).applyAsDouble(" 1.0 "));
    }

    @Test
    void fn_to_toDblFn_deep() {
        assertEquals(1.0, Z.with(trim).fuse(stringToDouble).applyAsDouble(" 1.0 "));
    }

    @Test
    void fn_to_toDblFn_deeper() {
        assertEquals(1.0, Z.with(trim).fusing(stringToDouble).resolve().applyAsDouble(" 1.0 "));
    }

    @Test
    void fn_to_toDblBifn() {
        assertEquals(3.0, Z.fuse(trim, addStringsAsDouble).apply(" 1.0 ").applyAsDouble("2.0"));
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
    void fn_to_toIntFn_deeper() {
        assertEquals(1, Z.with(trim).fusing(stringToInt).resolve().applyAsInt(" 1 "));
    }

    @Test
    void fn_to_toIntBifn() {
        assertEquals(3, Z.fuse(trim, addStringsAsInt).apply(" 1 ").applyAsInt("2"));
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
    void fn_to_toLongFn_deeper() {
        assertEquals(1L, Z.with(trim).fusing(stringToLong).resolve().applyAsLong(" 1 "));
    }

    @Test
    void fn_to_toLongBifn() {
        assertEquals(3L, Z.fuse(trim, addStringsAsLong).apply(" 1 ").applyAsLong("2"));
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
    void fn_to_pred_deeper() {
        assertTrue(Z.with(trim).fusing(isEmpty).resolve().test(" "));
    }

    @Test
    void fn_to_bipred() {
        assertTrue(Z.fuse(trim, startsWith).apply(" hello ").test("hell"));
    }

    @Evil
    @Test
    void fn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(trim, saveStringA).accept(" hello ");

            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void fn_to_cns_deep() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.with(trim).fuse(saveStringA).accept(" hello ");

            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void fn_to_cns_deeper() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.with(trim).fusing(saveStringA).resolve().accept(" hello ");

            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void fn_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(trim, saveStringsBandC).apply(" greetings ").accept("earthlings");

                assertEquals("greetings", consumedStringB);
                assertEquals("earthlings", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void fn_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
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
    void fn_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
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
    void fn_to_objLongFn() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(trim, saveStringFLongB).apply(" seven ").accept(7L);

                assertEquals("seven", consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void fn_to_toUnop() {
        assertEquals("goodbye?", Z.fuse(trim, addQuestionMark).apply(" goodbye "));
    }

    @Test
    void fn_to_toUnop_deep() {
        assertEquals("goodbye?", Z.with(trim).fuse(addQuestionMark).apply(" goodbye "));
    }

    @Test
    void fn_to_toUnop_deeper() {
        assertEquals("goodbye?", Z.with(trim).fusing(addQuestionMark).resolve().apply(" goodbye "));
    }

    @Test
    void fn_to_toBiop() {
        assertEquals("same-ish", Z.fuse(trim, relation).apply(" yo man ").apply("YO MAN"));
    }

    @Test
    void fn_to_toBiop_deep() {
        assertEquals("same-ish", Z.with(trim).fuse(relation).apply(" yo man ").apply("YO MAN"));
    }

    @Test
    void fn_to_toBiop_deeper() {
        assertEquals("same-ish", Z.with(trim).fusing(relation).resolve().apply(" yo man ").apply("YO MAN"));
    }
}
