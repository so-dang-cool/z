package so.dang.cool.z.combination.fusion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;
import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class ObjectFusionTests {

    @Test
    void class_deep() {
        assertEquals("hi", Z.with(String.class).apply("hi"));
    }

    @Test
    void class_to_fn_deep() {
        assertEquals(
            suppliedString.toLowerCase(),
            Z.with(String.class).fuse(toLower).apply("Z")
        );
    }

    @Test
    void object_deep() {
        assertEquals("hi", Z.with("hi").get());
    }

    @Test
    void object_to_fn() {
        assertEquals(
            suppliedString.toLowerCase(),
            Z.withObject("Z").fuse(toLower).get()
        );
    }

    @Test
    void object_to_fn_deep() {
        assertEquals(
            suppliedString.toLowerCase(),
            Z.with("Z").fuse(toLower).get()
        );
    }

    @Test
    void object_to_bifn() {
        assertEquals("Z!", Z.with("Z").fuse(concat).apply("!"));
    }

    @Test
    void object_to_toDblFn() {
        assertEquals(1.0, Z.with("1.0").fuse(stringToDouble).getAsDouble());
    }

    @Test
    void object_to_toDblBifn() {
        assertEquals(
            2.0,
            Z.with("1.0").fuse(addStringsAsDouble).applyAsDouble("1.0")
        );
    }

    @Test
    void object_to_toIntFn() {
        assertEquals(1, Z.with("1").fuse(stringToInt).getAsInt());
    }

    @Test
    void object_to_toIntBifn() {
        assertEquals(2, Z.with("1").fuse(addStringsAsInt).applyAsInt("1"));
    }

    @Test
    void object_to_toLongFn() {
        assertEquals(1L, Z.with("1").fuse(stringToLong).getAsLong());
    }

    @Test
    void object_to_toLongBifn() {
        assertEquals(2L, Z.with("1").fuse(addStringsAsLong).applyAsLong("1"));
    }

    @Test
    void object_to_pred() {
        assertFalse(Z.with("Z").fuse(isEmpty).getAsBoolean());
    }

    @Test
    void object_to_bipred() {
        assertTrue(Z.with("Z").fuse(startsWith).test("Z"));
    }

    @Evil
    @Test
    void object_to_cns() {
        synchronized (consumedStringA) {
            consumedStringA = "";

            Z.with("Z").fuse(saveStringA).run();

            assertEquals(suppliedString, consumedStringA);
        }
    }

    @Evil
    @Test
    void object_to_bicns() {
        synchronized (consumedStringB) {
            synchronized (consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.with("Z").fuse(saveStringsBandC).accept("z");

                assertEquals(suppliedString, consumedStringB);
                assertEquals("z", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void object_to_objDblCns() {
        synchronized (consumedStringD) {
            synchronized (consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.with("Z").fuse(saveStringDDoubleB).accept(5.0);

                assertEquals(suppliedString, consumedStringD);
                assertEquals(5.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void object_to_objIntCns() {
        synchronized (consumedStringE) {
            synchronized (consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.with("Z").fuse(saveStringEIntB).accept(6);

                assertEquals(suppliedString, consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void object_to_objLongFn() {
        synchronized (consumedStringF) {
            synchronized (consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.with("Z").fuse(saveStringFLongB).accept(7L);

                assertEquals(suppliedString, consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void object_to_toUnop() {
        assertEquals("Z?", Z.with("Z").fuse(addQuestionMark).get());
    }

    @Test
    void object_to_toBiop() {
        assertEquals("same-ish", Z.with("Z").fuse(relation).apply("z"));
    }
}
