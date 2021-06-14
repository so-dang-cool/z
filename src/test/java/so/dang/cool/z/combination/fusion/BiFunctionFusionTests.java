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
        assertEquals(
            "greetings",
            Z.with(concat).resolve().apply("greet").apply("ings")
        );
    }

    @Test
    void bifn_to_fn() {
        assertEquals(
            "hey there",
            Z.fuse(concat, trim).apply(" hey ").apply("there ")
        );
    }

    @Test
    void bifn_to_fn_deep() {
        assertEquals(
            "hey there",
            Z.with(concat).fuse(trim).apply(" hey ").apply("there ")
        );
    }

    @Test
    void bifn_to_fn_deeper() {
        assertEquals(
            "hey there",
            Z.with(concat).fusing(trim).resolve().apply(" hey ").apply("there ")
        );
    }

    @Test
    void bifn_to_bifn() {
        assertEquals(
            "かめはめ波",
            Z.fuse(concat, concat).apply("かめ").apply("はめ").apply("波")
        );

        assertEquals(
            "かめはめ波",
            Z.with(concat).fuse(concat).apply("かめ").apply("はめ").apply("波")
        );

        // TODO: Z.with(concat).fusing(concat).resolve()

        assertEquals(
            "かめはめ波",
            Z.with(concat).fuse(concat, "波").apply("かめ").apply("はめ")
        );

        assertEquals(
            "かめはめ波",
            Z
                .with(concat)
                .fusing(concat, "波")
                .resolve()
                .apply("かめ")
                .apply("はめ")
        );
    }

    @Test
    void bifn_to_toDblFn() {
        assertEquals(
            1.5,
            Z.fuse(concat, stringToDouble).apply("1").applyAsDouble(".5")
        );

        assertEquals(
            1.5,
            Z.with(concat).fuse(stringToDouble).apply("1").applyAsDouble(".5")
        );

        assertEquals(
            1.5,
            Z
                .with(concat)
                .fusing(stringToDouble)
                .resolve()
                .apply("1")
                .applyAsDouble(".5")
        );
    }

    @Test
    void bifn_to_toDblBifn() {
        assertEquals(
            3.0,
            Z
                .fuse(concat, addStringsAsDouble)
                .apply("1")
                .apply(".0")
                .applyAsDouble("2.0")
        );

        assertEquals(
            3.0,
            Z
                .with(concat)
                .fuse(addStringsAsDouble)
                .apply("1")
                .apply(".0")
                .applyAsDouble("2.0")
        );

        // TODO: Z.with(concat).fusing(addStringsAsDouble).resolve()

        assertEquals(
            3.0,
            Z
                .with(concat)
                .fuse(addStringsAsDouble, "2.0")
                .apply("1")
                .applyAsDouble(".0")
        );

        assertEquals(
            3.0,
            Z
                .with(concat)
                .fusing(addStringsAsDouble, "2.0")
                .resolve()
                .apply("1")
                .applyAsDouble(".0")
        );
    }

    @Test
    void bifn_to_toIntFn() {
        assertEquals(
            12,
            Z.fuse(concat, stringToInt).apply("1").applyAsInt("2")
        );

        assertEquals(
            12,
            Z.with(concat).fuse(stringToInt).apply("1").applyAsInt("2")
        );

        assertEquals(
            12,
            Z
                .with(concat)
                .fusing(stringToInt)
                .resolve()
                .apply("1")
                .applyAsInt("2")
        );
    }

    @Test
    void bifn_to_toIntBifn() {
        assertEquals(
            30,
            Z
                .fuse(concat, addStringsAsInt)
                .apply("2")
                .apply("3")
                .applyAsInt("7")
        );

        assertEquals(
            30,
            Z
                .with(concat)
                .fuse(addStringsAsInt)
                .apply("2")
                .apply("3")
                .applyAsInt("7")
        );

        // TODO: Implement with currying
        // assertEquals(
        //     30,
        //     Z
        //         .with(concat)
        //         .fusing(addStringsAsInt)
        //         .resolve()
        //         .apply("2")
        //         .apply("3")
        //         .applyAsInt("7")
        // );

        assertEquals(
            30,
            Z.with(concat).fuse(addStringsAsInt, "7").apply("2").applyAsInt("3")
        );

        assertEquals(
            30,
            Z
                .with(concat)
                .fusing(addStringsAsInt, "7")
                .resolve()
                .apply("2")
                .applyAsInt("3")
        );
    }

    @Test
    void bifn_to_toLongFn() {
        assertEquals(
            34L,
            Z.fuse(concat, stringToLong).apply("3").applyAsLong("4")
        );

        assertEquals(
            34L,
            Z.with(concat).fuse(stringToLong).apply("3").applyAsLong("4")
        );

        assertEquals(
            34L,
            Z
                .with(concat)
                .fusing(stringToLong)
                .resolve()
                .apply("3")
                .applyAsLong("4")
        );
    }

    @Test
    void bifn_to_toLongBifn() {
        assertEquals(
            40L,
            Z
                .fuse(concat, addStringsAsLong)
                .apply("3")
                .apply("4")
                .applyAsLong("6")
        );

        assertEquals(
            40L,
            Z
                .with(concat)
                .fuse(addStringsAsLong)
                .apply("3")
                .apply("4")
                .applyAsLong("6")
        );

        // TODO: Implement with currying
        // assertEquals(
        //     40L,
        //     Z
        //         .with(concat)
        //         .fusing(addStringsAsLong)
        //         .resolve()
        //         .apply("3")
        //         .apply("4")
        //         .applyAsLong("6")
        // );

        assertEquals(
            40L,
            Z
                .with(concat)
                .fuse(addStringsAsLong, "6")
                .apply("3")
                .applyAsLong("4")
        );

        assertEquals(
            40L,
            Z
                .with(concat)
                .fusing(addStringsAsLong, "6")
                .resolve()
                .apply("3")
                .applyAsLong("4")
        );
    }

    @Test
    void bifn_to_pred() {
        assertTrue(Z.fuse(concat, isEmpty).apply("").test(""));

        assertTrue(Z.with(concat).fuse(isEmpty).apply("").test(""));

        assertTrue(Z.with(concat).fusing(isEmpty).resolve().apply("").test(""));
    }

    @Test
    void bifn_to_bipred() {
        assertTrue(
            Z.fuse(concat, startsWith).apply("ba").apply("nana").test("ban")
        );

        assertTrue(
            Z
                .with(concat)
                .fuse(startsWith)
                .apply("ba")
                .apply("nana")
                .test("ban")
        );

        // TODO: Implement with currying
        // assertTrue(
        //     Z.with(concat).fusing(startsWith).resolve().apply("ba").apply("nana").test("ban")
        // );

        assertTrue(
            Z.with(concat).fuse(startsWith, "ban").apply("ba").test("nana")
        );

        assertTrue(
            Z
                .with(concat)
                .fusing(startsWith, "ban")
                .resolve()
                .apply("ba")
                .test("nana")
        );
    }

    @Evil
    @Test
    void bifn_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.fuse(concat, saveStringA).apply("yo").accept("yo");

            assertEquals("yoyo", consumedStringA);

            /* deep */

            consumedStringA = "";

            Z.with(concat).fuse(saveStringA).apply("yo").accept("yo");

            assertEquals("yoyo", consumedStringA);

            /* deeper */

            consumedStringA = "";

            Z
                .with(concat)
                .fusing(saveStringA)
                .resolve()
                .apply("yo")
                .accept("yo");

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
                    .fuse(concat, saveStringsBandC)
                    .apply("hell")
                    .apply("o")
                    .accept("mother");

                assertEquals("hello", consumedStringB);
                assertEquals("mother", consumedStringC);

                /* deep */

                consumedStringB = "";
                consumedStringC = "";

                Z
                    .with(concat)
                    .fuse(saveStringsBandC)
                    .apply("hell")
                    .apply("o")
                    .accept("mother");

                assertEquals("hello", consumedStringB);
                assertEquals("mother", consumedStringC);

                // TODO: Implement with currying
                // /* deeper */

                // consumedStringB = "";
                // consumedStringC = "";

                // Z
                //     .with(concat)
                //     .fusing(saveStringsBandC)
                //     .resolve()
                //     .apply("hell")
                //     .apply("o")
                //     .accept("mother");

                // assertEquals("hello", consumedStringB);
                // assertEquals("mother", consumedStringC);

                /* deep */

                consumedStringB = "";
                consumedStringC = "";

                Z
                    .with(concat)
                    .fuse(saveStringsBandC, "mother")
                    .apply("hell")
                    .accept("o");

                assertEquals("hello", consumedStringB);
                assertEquals("mother", consumedStringC);

                /* deeper */

                consumedStringB = "";
                consumedStringC = "";

                Z
                    .with(concat)
                    .fusing(saveStringsBandC, "mother")
                    .resolve()
                    .apply("hell")
                    .accept("o");

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
                    .fuse(concat, saveStringDDoubleB)
                    .apply("point")
                    .apply(" five")
                    .accept(0.5);

                assertEquals("point five", consumedStringD);
                assertEquals(0.5, consumedDoubleB);

                /* deep */

                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .with(concat)
                    .fuse(saveStringDDoubleB)
                    .apply("point")
                    .apply(" five")
                    .accept(0.5);

                assertEquals("point five", consumedStringD);
                assertEquals(0.5, consumedDoubleB);

                // TODO: Implement with currying
                // /* deeper */

                // consumedStringD = "";
                // consumedDoubleB = 0.0;

                // Z
                //     .with(concat)
                //     .fusing(saveStringDDoubleB)
                //     .resolve()
                //     .apply("point")
                //     .apply(" five")
                //     .accept(0.5);

                // assertEquals("point five", consumedStringD);
                // assertEquals(0.5, consumedDoubleB);

                /* deep */

                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .with(concat)
                    .fuse(saveStringDDoubleB, 0.5)
                    .apply("point")
                    .accept(" five");

                assertEquals("point five", consumedStringD);
                assertEquals(0.5, consumedDoubleB);

                /* deeper */

                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z
                    .with(concat)
                    .fusing(saveStringDDoubleB, 0.5)
                    .resolve()
                    .apply("point")
                    .accept(" five");

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
                    .fuse(concat, saveStringEIntB)
                    .apply("eleven")
                    .apply("teen")
                    .accept(111);

                assertEquals("eleventeen", consumedStringE);
                assertEquals(111, consumedIntB);

                /* deep */

                consumedStringE = "";
                consumedIntB = 0;

                Z
                    .with(concat)
                    .fuse(saveStringEIntB)
                    .apply("eleven")
                    .apply("teen")
                    .accept(111);

                assertEquals("eleventeen", consumedStringE);
                assertEquals(111, consumedIntB);

                // TODO: Implement with currying
                // /* deeper */

                // consumedStringE = "";
                // consumedIntB = 0;

                // Z
                //     .with(concat)
                //     .fusing(saveStringEIntB)
                //     .resolve()
                //     .apply("eleven")
                //     .apply("teen")
                //     .accept(111);

                // assertEquals("eleventeen", consumedStringE);
                // assertEquals(111, consumedIntB);

                /* deep */

                consumedStringE = "";
                consumedIntB = 0;

                Z
                    .with(concat)
                    .fuse(saveStringEIntB, 111)
                    .apply("eleven")
                    .accept("teen");

                assertEquals("eleventeen", consumedStringE);
                assertEquals(111, consumedIntB);

                /* deeper */

                consumedStringE = "";
                consumedIntB = 0;

                Z
                    .with(concat)
                    .fusing(saveStringEIntB, 111)
                    .resolve()
                    .apply("eleven")
                    .accept("teen");

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
                    .fuse(concat, saveStringFLongB)
                    .apply("twenty")
                    .apply("-two")
                    .accept(22L);

                assertEquals("twenty-two", consumedStringF);
                assertEquals(22L, consumedLongB);

                /* deep */

                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .with(concat)
                    .fuse(saveStringFLongB)
                    .apply("twenty")
                    .apply("-two")
                    .accept(22L);

                assertEquals("twenty-two", consumedStringF);
                assertEquals(22L, consumedLongB);

                // TODO: Implement with currying
                // /* deeper */

                // consumedStringF = "";
                // consumedLongB = 0L;

                // Z
                //     .with(concat)
                //     .fusing(saveStringFLongB)
                //     .resolve()
                //     .apply("twenty")
                //     .apply("-two")
                //     .accept(22L);

                // assertEquals("twenty-two", consumedStringF);
                // assertEquals(22L, consumedLongB);

                /* deep */

                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .with(concat)
                    .fuse(saveStringFLongB, 22L)
                    .apply("twenty")
                    .accept("-two");

                assertEquals("twenty-two", consumedStringF);
                assertEquals(22L, consumedLongB);

                /* deeper */

                consumedStringF = "";
                consumedLongB = 0L;

                Z
                    .with(concat)
                    .fusing(saveStringFLongB, 22L)
                    .resolve()
                    .apply("twenty")
                    .accept("-two");

                assertEquals("twenty-two", consumedStringF);
                assertEquals(22L, consumedLongB);
            }
        }
    }

    @Test
    void bifn_to_unop() {
        assertEquals(
            "goodbye?",
            Z.fuse(concat, addQuestionMark).apply("good").apply("bye")
        );

        assertEquals(
            "goodbye?",
            Z.with(concat).fuse(addQuestionMark).apply("good").apply("bye")
        );

        assertEquals(
            "goodbye?",
            Z
                .with(concat)
                .fusing(addQuestionMark)
                .resolve()
                .apply("good")
                .apply("bye")
        );
    }

    @Test
    void bifn_to_biop() {
        assertEquals(
            "same-ish",
            Z.fuse(concat, relation).apply("yo").apply(" mama").apply("YO MAMA")
        );

        assertEquals(
            "same-ish",
            Z
                .with(concat)
                .fuse(relation)
                .apply("yo")
                .apply(" mama")
                .apply("YO MAMA")
        );
        // TODO: Implement with currying
        // assertEquals(
        //     "same-ish",
        //     Z.with(concat).fusing(relation).resolve().apply("yo").apply(" mama").apply("YO MAMA")
        // );
    }
}
