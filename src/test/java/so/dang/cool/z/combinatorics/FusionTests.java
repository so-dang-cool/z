package so.dang.cool.z.combinatorics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class FusionTests {
    // BooleanFunction [TODO]

    // LongFunction

    @Test
    void longFn_to_fn() {
        assertEquals("1!", Z.fuse(longToString, addExclamationMark).apply(1L));
    }

    @Test
    void longFn_to_bifn() {
        assertEquals("1.0", Z.fuse(longToString, concat).apply(1L).apply(".0"));
    }

    @Test
    void longFn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(longToString, stringToDouble).applyAsDouble(1L));
    }

    @Test
    void longFn_to_toDblBin() {
        assertEquals(3.0, Z.fuse(longToString, addStringsAsDouble).apply(1L).applyAsDouble("2.0"));
    }

    @Test
    void longFn_to_toInt() {
        assertEquals(1, Z.fuse(longToString, stringToInt).applyAsInt(1L));
    }

    @Test
    void longFn_to_toIntBifn() {
        assertEquals(3, Z.fuse(longToString, addStringsAsInt).apply(1L).applyAsInt("2"));
    }

    @Test
    void longFn_to_toLongFn() {
        assertEquals(1L, Z.fuse(longToString, stringToLong).applyAsLong(1L));
    }

    @Test
    void longFn_to_toLongBifn() {
        assertEquals(3L, Z.fuse(longToString, addStringsAsLong).apply(1L).applyAsLong("2"));
    }

    @Test
    void longFn_to_pred() {
        assertFalse(Z.fuse(longToString, isEmpty).test(1L));
    }

    @Test
    void longFn_to_bipred() {
        assertTrue(Z.fuse(longToString, startsWith).apply(11L).test("1"));
    }

    @Evil
    @Test
    void longFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(longToString, saveStringA).accept(345L);

            assertEquals("345", consumedStringA);
        }
    }

    @Evil
    @Test
    void longFn_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(longToString, saveStringsBandC).apply(123L).accept("one twenty three");

                assertEquals("123", consumedStringB);
                assertEquals("one twenty three", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(longToString, saveStringDDoubleB).apply(234L).accept(234.0);

                assertEquals("234", consumedStringD);
                assertEquals(234.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(longToString, saveStringEIntB).apply(345L).accept(456);

                assertEquals("345", consumedStringE);
                assertEquals(456, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void longFn_to_objLongCns() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(longToString, saveStringFLongB).apply(567L).accept(678L);

                assertEquals("567", consumedStringF);
                assertEquals(678L, consumedLongB);
            }
        }
    }

    @Test
    void longFn_to_unop() {
        assertEquals("100?", Z.fuse(longToString, addQuestionMark).apply(100L));
    }

    @Test
    void longFn_to_biop() {
        assertEquals("same-ish", Z.fuse(longToString, relation).apply(789L).apply("789"));
    }

    // LongToDoubleFunction

    @Test
    void longToDbl_to_dblFn() {
        assertEquals("1.0", Z.fuse(longToDouble, doubleToString).apply(1L));
    }

    @Test
    void longToDbl_to_dblToInt() {
        assertEquals(2, Z.fuse(longToDouble, doubleToInt).applyAsInt(2L));
    }

    @Test
    void longToDbl_to_dblToLong() {
        assertEquals(3L, Z.fuse(longToDouble, doubleToLong).applyAsLong(3L));
    }

    @Test
    void longToDbl_to_dblPred() {
        assertTrue(Z.fuse(longToDouble, isDoubleOne).test(1L));
    }

    @Evil
    @Test
    void longToDbl_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(longToDouble, saveDoubleA).accept(1L);

            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Test
    void longToDbl_to_dblUnop() {
        assertEquals(2.0, Z.fuse(longToDouble, addOneToDouble).applyAsDouble(1L));
    }

    @Test
    void longToDbl_to_dblBiop() {
        assertEquals(1.5, Z.fuse(longToDouble, addDoubles).apply(1L).applyAsDouble(0.5));
    }

    // LongToIntFunction
    
    @Test
    void longToInt_to_intFn() {
        assertEquals("1", Z.fuse(longToInt, intToString).apply(1L));
    }

    @Test
    void longToInt_to_intToDbl() {
        assertEquals(1.0, Z.fuse(longToInt, intToDouble).applyAsDouble(1L));
    }

    @Test
    void longToInt_to_intToLong() {
        assertEquals(1, Z.fuse(longToInt, intToLong).applyAsLong(1L));
    }

    @Test
    void longToInt_to_intPred() {
        assertTrue(Z.fuse(longToInt, isIntTwo).test(2L));
    }

    @Evil
    @Test
    void  longToInt_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;
            
            Z.fuse(longToInt, saveIntA).accept(3L);

            assertEquals(3, consumedIntA);
        }
    }

    @Test
    void  longToInt_to_intUnop() {
        assertEquals(3, Z.fuse(longToInt, addTwoToInt).applyAsInt(1L));
    }

    @Test
    void  longToInt_to_intBiop() {
        assertEquals(4, Z.fuse(longToInt, addInts).apply(1L).applyAsInt(3));
    }

    // ToLongFunction

    @Test
    void toLongFn_to_longFn() {
        assertEquals("1", Z.fuse(stringToLong, longToString).apply("1"));
    }

    @Test
    void toLongFn_to_longToDbl() {
        assertEquals(1.0, Z.fuse(stringToLong, longToDouble).applyAsDouble("1"));
    }

    @Test
    void toLongFn_to_longToInt() {
        assertEquals(1L, Z.fuse(stringToLong, longToInt).applyAsInt("1"));
    }

    @Test
    void toLongFn_to_longPred() {
        assertTrue(Z.fuse(stringToLong, isLongThree).test("3"));
    }

    @Evil
    @Test
    void toLongFn_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(stringToLong, saveLongA).accept("5");

            assertEquals(5L, consumedLongA);
        }
    }

    @Test
    void toLongFn_to_longUnop() {
        assertEquals(9L, Z.fuse(stringToLong, addThreeToLong).applyAsLong("6"));
    }

    @Test
    void toLongFn_to_longBiop() {
        assertEquals(3L, Z.fuse(stringToLong, addLongs).apply("1").applyAsLong(2L));
    }

    // ToLongBiFunction

    @Test
    void toLongBifn_to_longFn() {
        assertEquals("3", Z.fuse(addStringsAsLong, longToString).apply("1").apply("2"));
    }

    @Test
    void toLongBifn_to_longToDbl() {
        assertEquals(3.0, Z.fuse(addStringsAsLong, longToDouble).apply("1").applyAsDouble("2"));
    }

    @Test
    void toLongBifn_to_longToInt() {
        assertEquals(9, Z.fuse(addStringsAsLong, longToInt).apply("4").applyAsInt("5"));
    }

    @Test
    void toLongBifn_to_longPred() {
        assertTrue(Z.fuse(addStringsAsLong, isLongThree).apply("-1").test("4"));
    }

    @Evil
    @Test
    void toLongBifn_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(addStringsAsLong, saveLongA).apply("2").accept("3");

            assertEquals(5L, consumedLongA);
        }
    }

    @Test
    void toLongBifn_to_longUnop() {
        assertEquals(6L, Z.fuse(addStringsAsLong, addThreeToLong).apply("1").applyAsLong("2"));
    }

    @Test
    void toLongBifn_to_longBiop() {
        assertEquals(6L, Z.fuse(addStringsAsLong, addLongs).apply("1").apply("2").applyAsLong(3L));
    }

    // Predicate

    @Test
    void pred_to_fn() {
        assertEquals("true", Z.fuse(isEmpty, booleanToString).apply(""));
    }

    @Test
    void pred_to_bifn() {
        assertEquals("HI", Z.fuse(isEmpty, maybeToUpper).apply("").apply("hi"));
    }

    @Test
    void pred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(isEmpty, maybeOneAsDouble).applyAsDouble(""));
    }

    @Test
    void pred_to_toDblBifn() {
        assertEquals(2.0, Z.fuse(isEmpty, maybeAddOneToStringAsDouble).apply("").applyAsDouble("1.0"));
    }

    @Test
    void pred_to_toIntFn() {
        assertEquals(2, Z.fuse(isEmpty, maybeTwoAsInt).applyAsInt(""));
    }

    @Test
    void pred_to_toIntBifn() {
        assertEquals(3, Z.fuse(isEmpty, maybeAddTwoToStringAsInt).apply("").applyAsInt("1"));
    }

    @Test
    void pred_to_toLongFn() {
        assertEquals(3L, Z.fuse(isEmpty, maybeThreeAsLong).applyAsLong(""));
    }

    @Test
    void pred_to_toLongBifn() {
        assertEquals(4L, Z.fuse(isEmpty, maybeAddThreeToStringAsLong).apply("").applyAsLong("1"));
    }

    @Test
    void pred_to_pred() {
        assertFalse(Z.fuse(isEmpty, not).test(""));
    }

    @Test
    void pred_to_bipred() {
        assertTrue(Z.fuse(isEmpty, maybeNotFromString).apply("").test("false"));
    }

    @Evil
    @Test
    void pred_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isEmpty, saveBooleanA).accept("");

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void pred_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(isEmpty, saveBooleanBAndStringG).apply("").accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void pred_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(isEmpty, saveBooleanCDoubleC).apply("").accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void pred_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(isEmpty, saveBooleanDIntC).apply("").accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void pred_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(isEmpty, saveBooleanELongC).apply("").accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void pred_to_toUnop() {
        assertTrue(Z.fuse(isEmpty, booleanId).test(""));
    }

    @Test
    void pred_to_toBiop() {
        assertTrue(Z.fuse(isEmpty, maybeNot).apply("").test(false));
    }

    // BiPredicate

    @Test
    void bipred_to_fn() {
        assertEquals("true", Z.fuse(startsWith, booleanToString).apply("yolo").apply("yo"));
    }

    @Test
    void bipred_to_bifn() {
        assertEquals("YO", Z.fuse(startsWith, maybeToUpper).apply("yolo").apply("yo").apply("yo"));
    }

    @Test
    void bipred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(startsWith, maybeOneAsDouble).apply("yolo").applyAsDouble("yo"));
    }

    @Test
    void bipred_to_toDblBifn() {
        assertEquals(3.0, Z.fuse(startsWith, maybeAddOneToStringAsDouble).apply("yolo").apply("yo").applyAsDouble("2.0"));
    }

    @Test
    void bipred_to_toIntFn() {
        assertEquals(2, Z.fuse(startsWith, maybeTwoAsInt).apply("yolo").applyAsInt("yo"));
    }

    @Test
    void bipred_to_toIntBifn() {
        assertEquals(9, Z.fuse(startsWith, maybeAddTwoToStringAsInt).apply("yolo").apply("yo").applyAsInt("7"));
    }

    @Test
    void bipred_to_toLongFn() {
        assertEquals(3L, Z.fuse(startsWith, maybeThreeAsLong).apply("yolo").applyAsLong("yo"));
    }

    @Test
    void bipred_to_toLongBifn() {
        assertEquals(9L, Z.fuse(startsWith, maybeAddThreeToStringAsLong).apply("yolo").apply("yo").applyAsLong("6"));
    }

    @Test
    void bipred_to_pred() {
        assertFalse(Z.fuse(startsWith, not).apply("yolo").test("yo"));
    }

    @Test
    void bipred_to_bipred() {
        assertTrue(Z.fuse(startsWith, maybeNotFromString).apply("yolo").apply("yo").test("false"));
    }

    @Evil
    @Test
    void bipred_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(startsWith, saveBooleanA).apply("yolo").accept("yo");

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void bipred_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(startsWith, saveBooleanBAndStringG).apply("yolo").apply("yo").accept("mother");

                assertTrue(consumedBooleanB);
                assertEquals("mother", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void bipred_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(startsWith, saveBooleanCDoubleC).apply("yolo").apply("yo").accept(0.5);

                assertTrue(consumedBooleanC);
                assertEquals(0.5, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void bipred_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(startsWith, saveBooleanDIntC).apply("yolo").apply("yo").accept(111);

                assertTrue(consumedBooleanD);
                assertEquals(111, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void bipred_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(startsWith, saveBooleanELongC).apply("yolo").apply("yo").accept(22L);

                assertTrue(consumedBooleanE);
                assertEquals(22L, consumedLongC);
            }
        }
    }

    @Test
    void bipred_to_unop() {
        assertTrue(Z.fuse(startsWith, booleanId).apply("yolo").test("yo"));
    }

    @Test
    void bipred_to_biop() {
        assertTrue(Z.fuse(startsWith, maybeNot).apply("yolo").apply("yo").test(false));
    }

    // DoublePredicate

    @Test
    void dblPred_to_fn() {
        assertEquals("true", Z.fuse(isDoubleOne, booleanToString).apply(1.0));
    }

    @Test
    void dblPred_to_bifn() {
        assertEquals("HI", Z.fuse(isDoubleOne, maybeToUpper).apply(1.0).apply("hi"));
    }

    @Test
    void dblPred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(isDoubleOne, maybeOneAsDouble).applyAsDouble(1.0));
    }

    @Test
    void dblPred_to_toDblBifn() {
        assertEquals(2.0, Z.fuse(isDoubleOne, maybeAddOneToStringAsDouble).apply(1.0).applyAsDouble("1.0"));
    }

    @Test
    void dblPred_to_toIntFn() {
        assertEquals(2, Z.fuse(isDoubleOne, maybeTwoAsInt).applyAsInt(1.0));
    }

    @Test
    void dblPred_to_toIntBifn() {
        assertEquals(3, Z.fuse(isDoubleOne, maybeAddTwoToStringAsInt).apply(1.0).applyAsInt("1"));
    }

    @Test
    void dblPred_to_toLongFn() {
        assertEquals(3L, Z.fuse(isDoubleOne, maybeThreeAsLong).applyAsLong(1.0));
    }

    @Test
    void dblPred_to_toLongBifn() {
        assertEquals(4L, Z.fuse(isDoubleOne, maybeAddThreeToStringAsLong).apply(1.0).applyAsLong("1"));
    }

    @Test
    void dblPred_to_pred() {
        assertFalse(Z.fuse(isDoubleOne, not).test(1.0));
    }

    @Test
    void dblPred_to_bipred() {
        assertTrue(Z.fuse(isDoubleOne, maybeNotFromString).apply(1.0).test("false"));
    }

    @Evil
    @Test
    void dblPred_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isDoubleOne, saveBooleanA).accept(1.0);

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void dblPred_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(isDoubleOne, saveBooleanBAndStringG).apply(1.0).accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void dblPred_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(isDoubleOne, saveBooleanCDoubleC).apply(1.0).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void dblPred_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(isDoubleOne, saveBooleanDIntC).apply(1.0).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void dblPred_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(isDoubleOne, saveBooleanELongC).apply(1.0).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void dblPred_to_toUnop() {
        assertTrue(Z.fuse(isDoubleOne, booleanId).test(1.0));
    }

    @Test
    void dblPred_to_toBiop() {
        assertTrue(Z.fuse(isDoubleOne, maybeNot).apply(1.0).test(false));
    }

    // IntPredicate

    @Test
    void intPred_to_fn() {
        assertEquals("true", Z.fuse(isIntTwo, booleanToString).apply(2));
    }

    @Test
    void intPred_to_bifn() {
        assertEquals("HI", Z.fuse(isIntTwo, maybeToUpper).apply(2).apply("hi"));
    }

    @Test
    void intPred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(isIntTwo, maybeOneAsDouble).applyAsDouble(2));
    }

    @Test
    void intPred_to_toDblBifn() {
        assertEquals(2.0, Z.fuse(isIntTwo, maybeAddOneToStringAsDouble).apply(2).applyAsDouble("1.0"));
    }

    @Test
    void intPred_to_toIntFn() {
        assertEquals(2, Z.fuse(isIntTwo, maybeTwoAsInt).applyAsInt(2));
    }

    @Test
    void intPred_to_toIntBifn() {
        assertEquals(3, Z.fuse(isIntTwo, maybeAddTwoToStringAsInt).apply(2).applyAsInt("1"));
    }

    @Test
    void intPred_to_toLongFn() {
        assertEquals(3L, Z.fuse(isIntTwo, maybeThreeAsLong).applyAsLong(2));
    }

    @Test
    void intPred_to_toLongBifn() {
        assertEquals(4L, Z.fuse(isIntTwo, maybeAddThreeToStringAsLong).apply(2).applyAsLong("1"));
    }

    @Test
    void intPred_to_pred() {
        assertFalse(Z.fuse(isIntTwo, not).test(2));
    }

    @Test
    void intPred_to_bipred() {
        assertTrue(Z.fuse(isIntTwo, maybeNotFromString).apply(2).test("false"));
    }

    @Evil
    @Test
    void intPred_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isIntTwo, saveBooleanA).accept(2);

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void intPred_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(isIntTwo, saveBooleanBAndStringG).apply(2).accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void intPred_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(isIntTwo, saveBooleanCDoubleC).apply(2).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void intPred_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(isIntTwo, saveBooleanDIntC).apply(2).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void intPred_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(isIntTwo, saveBooleanELongC).apply(2).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void intPred_to_toUnop() {
        assertTrue(Z.fuse(isIntTwo, booleanId).test(2));
    }

    @Test
    void intPred_to_toBiop() {
        assertTrue(Z.fuse(isIntTwo, maybeNot).apply(2).test(false));
    }

    // LongPredicate

    @Test
    void longPred_to_fn() {
        assertEquals("true", Z.fuse(isLongThree, booleanToString).apply(3L));
    }

    @Test
    void longPred_to_bifn() {
        assertEquals("HI", Z.fuse(isLongThree, maybeToUpper).apply(3L).apply("hi"));
    }

    @Test
    void longPred_to_toDblFn() {
        assertEquals(1.0, Z.fuse(isLongThree, maybeOneAsDouble).applyAsDouble(3L));
    }

    @Test
    void longPred_to_toDblBifn() {
        assertEquals(2.0, Z.fuse(isLongThree, maybeAddOneToStringAsDouble).apply(3L).applyAsDouble("1.0"));
    }

    @Test
    void longPred_to_toIntFn() {
        assertEquals(2, Z.fuse(isLongThree, maybeTwoAsInt).applyAsInt(3L));
    }

    @Test
    void longPred_to_toIntBifn() {
        assertEquals(3, Z.fuse(isLongThree, maybeAddTwoToStringAsInt).apply(3L).applyAsInt("1"));
    }

    @Test
    void longPred_to_toLongFn() {
        assertEquals(3L, Z.fuse(isLongThree, maybeThreeAsLong).applyAsLong(3L));
    }

    @Test
    void longPred_to_toLongBifn() {
        assertEquals(4L, Z.fuse(isLongThree, maybeAddThreeToStringAsLong).apply(3L).applyAsLong("1"));
    }

    @Test
    void longPred_to_pred() {
        assertFalse(Z.fuse(isLongThree, not).test(3L));
    }

    @Test
    void longPred_to_bipred() {
        assertTrue(Z.fuse(isLongThree, maybeNotFromString).apply(3L).test("false"));
    }

    @Evil
    @Test
    void longPred_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(isLongThree, saveBooleanA).accept(3L);

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void longPred_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(isLongThree, saveBooleanBAndStringG).apply(3L).accept("yolo");

                assertTrue(consumedBooleanB);
                assertEquals("yolo", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void longPred_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(isLongThree, saveBooleanCDoubleC).apply(3L).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void longPred_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(isLongThree, saveBooleanDIntC).apply(3L).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void longPred_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(isLongThree, saveBooleanELongC).apply(3L).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void longPred_to_toUnop() {
        assertTrue(Z.fuse(isLongThree, booleanId).test(3L));
    }

    @Test
    void longPred_to_toBiop() {
        assertTrue(Z.fuse(isLongThree, maybeNot).apply(3L).test(false));
    }

    // Supplier

    @Test
    void sup_to_fn() {
        assertEquals(suppliedString.toLowerCase(), Z.fuse(getString, toLower).get());
    }

    @Test
    void sup_to_bifn() {
        assertEquals("Z!", Z.fuse(getString, concat).apply("!"));
    }

    @Test
    void sup_to_toDblFn() {
        assertEquals(1.0, Z.fuse(() -> "1.0", stringToDouble).getAsDouble());
    }

    @Test
    void sup_to_toDblBifn() {
        assertEquals(2.0, Z.fuse(() -> "1.0", addStringsAsDouble).applyAsDouble("1.0"));
    }

    @Test
    void sup_to_toIntFn() {
        assertEquals(1, Z.fuse(() -> "1", stringToInt).getAsInt());
    }

    @Test
    void sup_to_toIntBifn() {
        assertEquals(2, Z.fuse(() -> "1", addStringsAsInt).applyAsInt("1"));
    }

    @Test
    void sup_to_toLongFn() {
        assertEquals(1L, Z.fuse(() -> "1", stringToLong).getAsLong());
    }

    @Test
    void sup_to_toLongBifn() {
        assertEquals(2L, Z.fuse(() -> "1", addStringsAsLong).applyAsLong("1"));
    }

    @Test
    void sup_to_pred() {
        assertFalse(Z.fuse(getString, isEmpty).getAsBoolean());
    }

    @Test
    void sup_to_bipred() {
        assertTrue(Z.fuse(getString, startsWith).test("Z"));
    }

    @Evil
    @Test
    void sup_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(getString, saveStringA).run();

            assertEquals(suppliedString, consumedStringA);
        }
    }

    @Evil
    @Test
    void sup_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(getString, saveStringsBandC).accept("z");

                assertEquals(suppliedString, consumedStringB);
                assertEquals("z", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void sup_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(getString, saveStringDDoubleB).accept(5.0);

                assertEquals(suppliedString, consumedStringD);
                assertEquals(5.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void sup_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(getString, saveStringEIntB).accept(6);

                assertEquals(suppliedString, consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void sup_to_objLongFn() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(getString, saveStringFLongB).accept(7L);

                assertEquals(suppliedString, consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void sup_to_toUnop() {
        assertEquals("Z?", Z.fuse(getString, addQuestionMark).get());
    }

    @Test
    void sup_to_toBiop() {
        assertEquals("same-ish", Z.fuse(getString, relation).apply("z"));
    }

    // BooleanSupplier

    @Test
    void boolSup_to_fn() {
        assertEquals("true", Z.fuse(getBooleanTrue, booleanToString).get());
    }

    @Test
    void boolSup_to_bifn() {
        assertEquals("HI", Z.fuse(getBooleanTrue, maybeToUpper).apply("hi"));
    }

    @Test
    void boolSup_to_toDblFn() {
        assertEquals(1.0, Z.fuse(getBooleanTrue, maybeOneAsDouble).getAsDouble());
    }

    @Test
    void boolSup_to_toDblBifn() {
        assertEquals(3.0, Z.fuse(getBooleanTrue, maybeAddOneToStringAsDouble).applyAsDouble("2.0"));
    }

    @Test
    void boolSup_to_toIntFn() {
        assertEquals(2, Z.fuse(getBooleanTrue, maybeTwoAsInt).getAsInt());
    }

    @Test
    void boolSup_to_toIntBifn() {
        assertEquals(4, Z.fuse(getBooleanTrue, maybeAddTwoToStringAsInt).applyAsInt("2"));
    }

    @Test
    void boolSup_to_toLongFn() {
        assertEquals(3L, Z.fuse(getBooleanTrue, maybeThreeAsLong).getAsLong());
    }

    @Test
    void boolSup_to_toLongBifn() {
        assertEquals(7L, Z.fuse(getBooleanTrue, maybeAddThreeToStringAsLong).applyAsLong("4"));
    }

    @Test
    void boolSup_to_pred() {
        assertFalse(Z.fuse(getBooleanTrue, not).getAsBoolean());
    }

    @Test
    void boolSup_to_bipred() {
        assertTrue(Z.fuse(getBooleanTrue, maybeNotFromString).test("false"));
    }

    @Evil
    @Test
    void boolSup_to_cns() {
        synchronized(consumedBooleanA) {
            consumedBooleanA = false;

            Z.fuse(getBooleanTrue, saveBooleanA).run();

            assertTrue(consumedBooleanA);
        }
    }

    @Evil
    @Test
    void boolSup_to_bicns() {
        synchronized(consumedBooleanB) {
            synchronized(consumedStringG) {
                consumedBooleanB = false;
                consumedStringG = "";

                Z.fuse(getBooleanTrue, saveBooleanBAndStringG).accept("z");

                assertTrue(consumedBooleanB);
                assertEquals("z", consumedStringG);
            }
        }
    }

    @Evil
    @Test
    void boolSup_to_objDblCns() {
        synchronized(consumedBooleanC) {
            synchronized(consumedDoubleC) {
                consumedBooleanC = false;
                consumedDoubleC = 0.0;

                Z.fuse(getBooleanTrue, saveBooleanCDoubleC).accept(5.0);

                assertTrue(consumedBooleanC);
                assertEquals(5.0, consumedDoubleC);
            }
        }
    }

    @Evil
    @Test
    void boolSup_to_objIntCns() {
        synchronized(consumedBooleanD) {
            synchronized(consumedIntC) {
                consumedBooleanD = false;
                consumedIntC = 0;

                Z.fuse(getBooleanTrue, saveBooleanDIntC).accept(6);

                assertTrue(consumedBooleanD);
                assertEquals(6, consumedIntC);
            }
        }
    }

    @Evil
    @Test
    void boolSup_to_objLongFn() {
        synchronized(consumedBooleanE) {
            synchronized(consumedLongC) {
                consumedBooleanE = false;
                consumedLongC = 0L;

                Z.fuse(getBooleanTrue, saveBooleanELongC).accept(7L);

                assertTrue(consumedBooleanE);
                assertEquals(7L, consumedLongC);
            }
        }
    }

    @Test
    void boolSup_to_toUnop() {
        assertTrue(Z.fuse(getBooleanTrue, booleanId).getAsBoolean());
    }

    @Test
    void boolSup_to_toBiop() {
        assertTrue(Z.fuse(getBooleanTrue, maybeNot).applyAsBoolean(false));
    }

    // DoubleSupplier

    @Test
    void dblSup_to_dblFn() {
        assertEquals("1.0", Z.fuse(getDouble, doubleToString).get());
    }

    @Test
    void dblSup_to_dblToInt() {
        assertEquals(1, Z.fuse(getDouble, doubleToInt).getAsInt());
    }

    @Test
    void dblSup_to_dblToLong() {
        assertEquals(1L, Z.fuse(getDouble, doubleToLong).getAsLong());
    }

    @Test
    void dblSup_to_dblPred() {
        assertTrue(Z.fuse(getDouble, isDoubleOne).getAsBoolean());
    }

    @Evil
    @Test
    void dblSup_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(getDouble, saveDoubleA).run();

            assertEquals(suppliedDouble, consumedDoubleA);
        }
    }

    @Test
    void dblSup_to_dblUnop() {
        assertEquals(2.0, Z.fuse(getDouble, addOneToDouble).getAsDouble());
    }

    @Test
    void dblSup_to_dblBiop() {
        assertEquals(3.0, Z.fuse(getDouble, addDoubles).applyAsDouble(2.0));
    }

    // IntSupplier

    @Test
    void intSup_to_intFn() {
        assertEquals("2", Z.fuse(getInt, intToString).get());
    }

    @Test
    void intSup_to_intToDbl() {
        assertEquals(2.0, Z.fuse(getInt, intToDouble).getAsDouble());
    }

    @Test
    void intSup_to_intToLong() {
        assertEquals(2L, Z.fuse(getInt, intToLong).getAsLong());
    }

    @Test
    void intSup_to_intPred() {
        assertTrue(Z.fuse(getInt, isIntTwo).getAsBoolean());
    }

    @Evil
    @Test
    void intSup_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(getInt, saveIntA).run();

            assertEquals(suppliedInt, consumedIntA);
        }
    }

    @Test
    void intSup_to_intUnop() {
        assertEquals(4, Z.fuse(getInt, addTwoToInt).getAsInt());
    }

    @Test
    void intSup_to_intBiop() {
        assertEquals(4, Z.fuse(getInt, addInts).applyAsInt(2));
    }

    // LongSupplier

    @Test
    void longSup_to_longFn() {
        assertEquals("3", Z.fuse(getLong, longToString).get());
    }

    @Test
    void longSup_to_longToDbl() {
        assertEquals(3.0, Z.fuse(getLong, longToDouble).getAsDouble());
    }

    @Test
    void longSup_to_longToInt() {
        assertEquals(3, Z.fuse(getLong, longToInt).getAsInt());
    }

    @Test
    void longSup_to_longPred() {
        assertTrue(Z.fuse(getLong, isLongThree).getAsBoolean());
    }

    @Evil
    @Test
    void longSup_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(getLong, saveLongA).run();

            assertEquals(suppliedLong, consumedLongA);
        }
    }

    @Test
    void longSup_to_longUnop() {
        assertEquals(6, Z.fuse(getLong, addThreeToLong).getAsLong());
    }

    @Test
    void longSup_to_longBiop() {
        assertEquals(5, Z.fuse(getLong, addLongs).applyAsLong(2L));
    }

    // UnaryOperator

    @Test
    void unop_to_fn() {
        assertEquals("hello?", Z.fuse(addQuestionMark, toLower).apply("HeLlO"));
    }

    @Test
    void unop_to_bifn() {
        assertEquals("hello?!", Z.fuse(addQuestionMark, concat).apply("hello").apply("!"));
    }

    @Test
    void unop_to_toDblFn() {
        assertEquals(1.0, Z.fuse(addTrailingZero, stringToDouble).applyAsDouble("1."));
    }

    @Test
    void unop_to_toDblBifn() {
        assertEquals(3.0, Z.fuse(addTrailingZero, addStringsAsDouble).apply("1.").applyAsDouble("2.0"));
    }

    @Test
    void unop_to_toIntFn() {
        assertEquals(10, Z.fuse(addTrailingZero, stringToInt).applyAsInt("1"));
    }

    @Test
    void unop_to_toIntBifn() {
        assertEquals(12, Z.fuse(addTrailingZero, addStringsAsInt).apply("1").applyAsInt("2"));
    }

    @Test
    void unop_to_toLongFn() {
        assertEquals(10L, Z.fuse(addTrailingZero, stringToLong).applyAsLong("1"));
    }

    @Test
    void unop_to_toLongBifn() {
        assertEquals(12L, Z.fuse(addTrailingZero, addStringsAsLong).apply("1").applyAsLong("2"));
    }

    @Test
    void unop_to_pred() {
        assertFalse(Z.fuse(addQuestionMark, isEmpty).test(""));
    }

    @Test
    void unop_to_bipred() {
        assertTrue(Z.fuse(addQuestionMark, startsWith).apply("hello").test("hell"));
    }

    @Evil
    @Test
    void unop_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(addQuestionMark, saveStringA).accept("hello");

            assertEquals("hello?", consumedStringA);
        }
    }

    @Evil
    @Test
    void unop_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(addQuestionMark, saveStringsBandC).apply("greetings").accept("earthlings");

                assertEquals("greetings?", consumedStringB);
                assertEquals("earthlings", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void unop_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(addQuestionMark, saveStringDDoubleB).apply("five").accept(5.0);

                assertEquals("five?", consumedStringD);
                assertEquals(5.0, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void unop_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(addQuestionMark, saveStringEIntB).apply("six").accept(6);

                assertEquals("six?", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void unop_to_objLongFn() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(addQuestionMark, saveStringFLongB).apply("seven").accept(7L);

                assertEquals("seven?", consumedStringF);
                assertEquals(7L, consumedLongB);
            }
        }
    }

    @Test
    void unop_to_unop() {
        assertEquals("goodbye??", Z.fuse(addQuestionMark, addQuestionMark).apply("goodbye"));
    }

    @Test
    void unop_to_biop() {
        assertEquals("same-ish", Z.fuse(addQuestionMark, relation).apply("yo man").apply("YO MAN?"));
    }

    // BinaryOperator

    @Test
    void biop_to_fn() {
        assertEquals("same-ish", Z.fuse(relation, trim).apply("hey").apply("HEY"));
    }

    @Test
    void biop_to_bifn() {
        assertEquals("same-ish-ness", Z.fuse(relation, concat).apply("hey").apply("HEY").apply("-ness"));
    }

    @Test
    void biop_to_toDblFn() {
        assertEquals(15.0, Z.fuse(concatAndAddTrailingZero, stringToDouble).apply("1").applyAsDouble("5."));
    }

    @Test
    void biop_to_toDblBifn() {
        assertEquals(12.0, Z.fuse(concatAndAddTrailingZero, addStringsAsDouble).apply("1").apply("0.").applyAsDouble("2.0"));
    }

    @Test
    void biop_to_toIntFn() {
        assertEquals(120, Z.fuse(concatAndAddTrailingZero, stringToInt).apply("1").applyAsInt("2"));
    }

    @Test
    void biop_to_toIntBifn() {
        assertEquals(237, Z.fuse(concatAndAddTrailingZero, addStringsAsInt).apply("2").apply("3").applyAsInt("7"));
    }

    @Test
    void biop_to_toLongFn() {
        assertEquals(340L, Z.fuse(concatAndAddTrailingZero, stringToLong).apply("3").applyAsLong("4"));
    }

    @Test
    void biop_to_toLongBifn() {
        assertEquals(346L, Z.fuse(concatAndAddTrailingZero, addStringsAsLong).apply("3").apply("4").applyAsLong("6"));
    }

    @Test
    void biop_to_pred() {
        assertFalse(Z.fuse(relation, isEmpty).apply("hey").test("HEY"));
    }

    @Test
    void biop_to_bipred() {
        assertTrue(Z.fuse(relation, startsWith).apply("hey").apply("HEY").test("same"));
    }

    @Evil
    @Test
    void biop_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(relation, saveStringA).apply("hey").accept("HEY");

            assertEquals("same-ish", consumedStringA);
        }
    }

    @Evil
    @Test
    void biop_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(relation, saveStringsBandC).apply("hey").apply("HEY").accept("mother");

                assertEquals("same-ish", consumedStringB);
                assertEquals("mother", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void biop_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(relation, saveStringDDoubleB).apply("hey").apply("HEY").accept(0.5);

                assertEquals("same-ish", consumedStringD);
                assertEquals(0.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void biop_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(relation, saveStringEIntB).apply("hey").apply("HEY").accept(111);

                assertEquals("same-ish", consumedStringE);
                assertEquals(111, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void biop_to_objLongFn() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(relation, saveStringFLongB).apply("hey").apply("HEY").accept(22L);

                assertEquals("same-ish", consumedStringF);
                assertEquals(22L, consumedLongB);
            }
        }
    }

    @Test
    void biop_to_unop() {
        assertEquals("same-ish?", Z.fuse(relation, addQuestionMark).apply("hey").apply("HEY"));
    }

    @Test
    void biop_to_biop() {
        assertEquals("same-ish", Z.fuse(relation, relation).apply("hey").apply("HEY").apply("SAME-ISH"));
    }

    // DoubleUnaryOperator


    @Test
    void dblUnop_to_dblFn() {
        assertEquals("4.5", Z.fuse(addOneToDouble, doubleToString).apply(3.5));
    }

    @Test
    void dblUnop_to_dblToInt() {
        assertEquals(4, Z.fuse(addOneToDouble, doubleToInt).applyAsInt(3.6));
    }

    @Test
    void dblUnop_to_dblToLong() {
        assertEquals(4L, Z.fuse(addOneToDouble, doubleToLong).applyAsLong(3.7));
    }

    @Test
    void dblUnop_to_dblPred() {
        assertTrue(Z.fuse(addOneToDouble, isDoubleOne).test(0.0));
    }

    @Evil
    @Test
    void dblUnop_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(addOneToDouble, saveDoubleA).accept(4.5);

            assertEquals(5.5, consumedDoubleA);
        }
    }

    @Test
    void dblUnop_to_dblUnop() {
        assertEquals(7.5, Z.fuse(addOneToDouble, addOneToDouble).applyAsDouble(5.5));
    }

    @Test
    void dblUnop_to_dblBiop() {
        assertEquals(4.0, Z.fuse(addOneToDouble, addDoubles).apply(1.0).applyAsDouble(2.0));
    }

    // DoubleBinaryOperator

    @Test
    void dblBiop_to_dblFn() {
        assertEquals("3.0", Z.fuse(addDoubles, doubleToString).apply(1.0).apply(2.0));
    }

    @Test
    void dblBiop_to_dblToInt() {
        assertEquals(3, Z.fuse(addDoubles, doubleToInt).apply(1.2).applyAsInt(2.3));
    }

    @Test
    void dblBiop_to_dblToLong() {
        assertEquals(10L, Z.fuse(addDoubles, doubleToLong).apply(4.5).applyAsLong(5.6));
    }

    @Test
    void dblBiop_to_dblPred() {
        assertTrue(Z.fuse(addDoubles, isDoubleOne).apply(0.5).test(0.5));
    }

    @Evil
    @Test
    void dblBiop_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(addDoubles, saveDoubleA).apply(1.0).accept(5.5);

            assertEquals(6.5, consumedDoubleA);
        }
    }
    
    @Test
    void dblBiop_to_dblUnop() {
        assertEquals(7.5, Z.fuse(addDoubles, addOneToDouble).apply(0.5).applyAsDouble(6.0));
    }

    @Test
    void dblBiop_to_dblBiop() {
        assertEquals(4.0, Z.fuse(addDoubles, addDoubles).apply(0.5).apply(1.5).applyAsDouble(2.0));
    }
    
    // IntUnaryOperator

    @Test
    void intUnop_to_intFn() {
        assertEquals("3", Z.fuse(addTwoToInt, intToString).apply(1));
    }

    @Test
    void intUnop_to_intToDbl() {
        assertEquals(3.0, Z.fuse(addTwoToInt, intToDouble).applyAsDouble(1));
    }

    @Test
    void intUnop_to_intToLong() {
        assertEquals(3L, Z.fuse(addTwoToInt, intToLong).applyAsLong(1));
    }

    @Test
    void intUnop_to_intPred() {
        assertTrue(Z.fuse(addTwoToInt, isIntTwo).test(0));
    }

    @Evil
    @Test
    void intUnop_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(addTwoToInt, saveIntA).accept(3);

            assertEquals(5, consumedIntA);
        }
    }

    @Test
    void intUnop_to_intUnop() {
        assertEquals(8, Z.fuse(addTwoToInt, addTwoToInt).applyAsInt(4));
    }

    @Test
    void intUnop_to_intBiop() {
        assertEquals(3, Z.fuse(addTwoToInt, addInts).apply(0).applyAsInt(1));
    }

    // IntBinaryOperator

    @Test
    void intBiop_to_intFn() {
        assertEquals("3", Z.fuse(addInts, intToString).apply(1).apply(2));
    }

    @Test
    void intBiop_to_intToDbl() {
        assertEquals(3.0, Z.fuse(addInts, intToDouble).apply(1).applyAsDouble(2));
    }

    @Test
    void intBiop_to_dblToLong() {
        assertEquals(9L, Z.fuse(addInts, intToLong).apply(4).applyAsLong(5));
    }

    @Test
    void intBiop_to_intPred() {
        assertTrue(Z.fuse(addInts, isIntTwo).apply(-1).test(3));
    }

    @Evil
    @Test
    void intBiop_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(addInts, saveIntA).apply(2).accept(3);

            assertEquals(5, consumedIntA);
        }
    }
    
    @Test
    void intBiop_to_intUnop() {
        assertEquals(6, Z.fuse(addInts, addTwoToInt).apply(1).applyAsInt(3));
    }

    @Test
    void intBiop_to_intBiop() {
        assertEquals(6, Z.fuse(addInts, addInts).apply(1).apply(2).applyAsInt(3));
    }

    // LongUnaryOperator

    @Test
    void longUnop_to_longFn() {
        assertEquals("4", Z.fuse(addThreeToLong, longToString).apply(1L));
    }

    @Test
    void longUnop_to_longToDbl() {
        assertEquals(4.0, Z.fuse(addThreeToLong, longToDouble).applyAsDouble(1L));
    }

    @Test
    void longUnop_to_longToInt() {
        assertEquals(4, Z.fuse(addThreeToLong, longToInt).applyAsInt(1L));
    }

    @Test
    void longUnop_to_longPred() {
        assertTrue(Z.fuse(addThreeToLong, isLongThree).test(0L));
    }

    @Evil
    @Test
    void longUnop_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(addThreeToLong, saveLongA).accept(2L);

            assertEquals(5L, consumedLongA);
        }
    }

    @Test
    void longUnop_to_longUnop() {
        assertEquals(9L, Z.fuse(addThreeToLong, addThreeToLong).applyAsLong(3L));
    }

    @Test
    void longUnop_to_longBiop() {
        assertEquals(6L, Z.fuse(addThreeToLong, addLongs).apply(1L).applyAsLong(2L));
    }

    // LongBinaryOperator

    @Test
    void longBiop_to_longFn() {
        assertEquals("3", Z.fuse(addLongs, longToString).apply(1L).apply(2L));
    }

    @Test
    void longBiop_to_longToDbl() {
        assertEquals(3.0, Z.fuse(addLongs, longToDouble).apply(1L).applyAsDouble(2L));
    }

    @Test
    void longBiop_to_longToInt() {
        assertEquals(9, Z.fuse(addLongs, longToInt).apply(4L).applyAsInt(5L));
    }

    @Test
    void longBiop_to_longPred() {
        assertTrue(Z.fuse(addLongs, isLongThree).apply(-1L).test(4L));
    }

    @Evil
    @Test
    void longBiop_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;

            Z.fuse(addLongs, saveLongA).apply(2L).accept(3L);

            assertEquals(5L, consumedLongA);
        }
    }

    @Test
    void longBiop_to_longUnop() {
        assertEquals(6L, Z.fuse(addLongs, addThreeToLong).apply(1L).applyAsLong(2L));
    }

    @Test
    void longBiop_to_longBiop() {
        assertEquals(6L, Z.fuse(addLongs, addLongs).apply(1L).apply(2L).applyAsLong(3L));
    }
}
