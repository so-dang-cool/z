package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class BiFunctionFusionTests {

    @Test
    void bifn() {
        assertEquals("greetings", concat.apply("greet", "ings"));
    }

    @Test
    void bifn_deep() {
        assertEquals("greetings", Z.fuse(concat).apply("greet").apply("ings"));
    }

    @Test
    void bifn_to_fn_deep() {
        assertEquals(
            "hey there",
            Z.fuse(concat).fuse(trim).apply(" hey ").apply("there ")
        );
    }

    @Test
    void bifn_to_bifn() {
        assertEquals(
            "かめはめ波",
            Z.fuse(concat).fuse(concat).apply("かめ").apply("はめ").apply("波")
        );
    }

    @Test
    void bifn_to_toDblFn() {
        assertEquals(
            1.5,
            Z.fuse(concat).fuse(stringToDouble).apply("1").applyAsDouble(".5")
        );
    }

    @Test
    void bifn_to_toDblBifn() {
        assertEquals(
            3.0,
            Z
                .fuse(concat)
                .fuse(addStringsAsDouble)
                .apply("1")
                .apply(".0")
                .applyAsDouble("2.0")
        );
    }

    @Test
    void bifn_to_toIntFn() {
        assertEquals(
            12,
            Z.fuse(concat).fuse(stringToInt).apply("1").applyAsInt("2")
        );
    }

    @Test
    void bifn_to_toIntBifn() {
        assertEquals(
            30,
            Z
                .fuse(concat)
                .fuse(addStringsAsInt)
                .apply("2")
                .apply("3")
                .applyAsInt("7")
        );
    }

    @Test
    void bifn_to_toLongFn() {
        assertEquals(
            34L,
            Z.fuse(concat).fuse(stringToLong).apply("3").applyAsLong("4")
        );
    }

    @Test
    void bifn_to_toLongBifn() {
        assertEquals(
            40L,
            Z
                .fuse(concat)
                .fuse(addStringsAsLong)
                .apply("3")
                .apply("4")
                .applyAsLong("6")
        );
    }

    @Test
    void bifn_to_pred() {
        assertTrue(Z.fuse(concat).fuse(isEmpty).apply("").test(""));
    }

    @Test
    void bifn_to_bipred() {
        assertTrue(
            Z
                .fuse(concat)
                .fuse(startsWith)
                .apply("ba")
                .apply("nana")
                .test("ban")
        );
    }

    @Evil
    @Test
    void bifn_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(concat).fuse(saveStringA).apply("yo").accept("yo");

            assertEquals("yoyo", consumedStringA);
        }
    }

    @Evil
    @Test
    void bifn_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z
                    .fuse(concat)
                    .fuse(saveStringsBandC)
                    .apply("hell")
                    .apply("o")
                    .accept("mother");

                assertEquals("hello", consumedStringB);
                assertEquals("mother", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bifn_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .fuse(concat)
                    .fuse(saveStringDDoubleB)
                    .apply("point")
                    .apply(" five")
                    .accept(0.5);

                assertEquals("point five", consumedStringD);
                assertEquals(0.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void bifn_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z
                    .fuse(concat)
                    .fuse(saveStringEIntB)
                    .apply("eleven")
                    .apply("teen")
                    .accept(111);

                assertEquals("eleventeen", consumedStringE);
                assertEquals(111, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void bifn_to_objLongFn() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .fuse(concat)
                    .fuse(saveStringFLongB)
                    .apply("twenty")
                    .apply("-two")
                    .accept(22L);

                assertEquals("twenty-two", consumedStringF);
                assertEquals(22L, consumedLongB);
            }
        }
    }

    @Test
    void bifn_to_unop() {
        assertEquals(
            "goodbye?",
            Z.fuse(concat).fuse(addQuestionMark).apply("good").apply("bye")
        );
    }

    @Test
    void bifn_to_biop() {
        assertEquals(
            "same-ish",
            Z
                .fuse(concat)
                .fuse(relation)
                .apply("yo")
                .apply(" mama")
                .apply("YO MAMA")
        );
    }
}
