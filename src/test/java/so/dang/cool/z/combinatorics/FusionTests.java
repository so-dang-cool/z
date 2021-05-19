package so.dang.cool.z.combinatorics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class FusionTests {
    @Test
    void fn_to_fn() {
        assertEquals("hello", Z.fuse(trim, toLower).apply(" HeLlO "));
    }

    @Test
    void fn_to_bifn() {
        assertEquals("hello!", Z.fuse(trim, concat).apply(" hello ").apply("!"));
    }

    @Test
    void fn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(trim, stringToDouble).applyAsDouble(" 1.0 "));
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
    void fn_to_toIntBifn() {
        assertEquals(3, Z.fuse(trim, addStringsAsInt).apply(" 1 ").applyAsInt("2"));
    }

    @Test
    void fn_to_toLongFn() {
        assertEquals(1L, Z.fuse(trim, stringToLong).applyAsLong(" 1 "));
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
    void fn_to_toBiop() {
        assertEquals("same-ish", Z.fuse(trim, relation).apply(" yo man ").apply("YO MAN"));
    }

    @Test
    void bifn_to_fn() {
        assertEquals("hey there", Z.fuse(concat, trim).apply(" hey ").apply("there "));
    }

    @Test
    void bifn_to_bifn() {
        assertEquals("かめはめ波", Z.fuse(concat, concat).apply("かめ").apply("はめ").apply("波"));
    }

    @Test
    void bifn_to_toDblFn() {
        assertEquals(1.5, Z.fuse(concat, stringToDouble).apply("1").applyAsDouble(".5"));
    }

    @Test
    void bifn_to_toDblBifn() {
        assertEquals(3.0, Z.fuse(concat, addStringsAsDouble).apply("1").apply(".0").applyAsDouble("2.0"));
    }

    @Test
    void bifn_to_toIntFn() {
        assertEquals(12, Z.fuse(concat, stringToInt).apply("1").applyAsInt("2"));
    }

    @Test
    void bifn_to_toIntBifn() {
        assertEquals(30, Z.fuse(concat, addStringsAsInt).apply("2").apply("3").applyAsInt("7"));
    }

    @Test
    void bifn_to_toLongFn() {
        assertEquals(34L, Z.fuse(concat, stringToLong).apply("3").applyAsLong("4"));
    }

    @Test
    void bifn_to_toLongBifn() {
        assertEquals(40L, Z.fuse(concat, addStringsAsLong).apply("3").apply("4").applyAsLong("6"));
    }

    @Test
    void bifn_to_pred() {
        assertTrue(Z.fuse(concat, isEmpty).apply("").test(""));
    }

    @Test
    void bifn_to_bipred() {
        assertTrue(Z.fuse(concat, startsWith).apply("ba").apply("nana").test("ban"));
    }

    @Evil
    @Test
    void bifn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(concat, saveStringA).apply("yo").accept("yo");

            assertEquals("yoyo", consumedStringA);
        }
    }

    @Evil
    @Test
    void bifn_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(concat, saveStringsBandC).apply("hell").apply("o").accept("mother");

                assertEquals("hello", consumedStringB);
                assertEquals("mother", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bifn_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(concat, saveStringDDoubleB).apply("point").apply(" five").accept(0.5);

                assertEquals("point five", consumedStringD);
                assertEquals(0.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void bifn_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(concat, saveStringEIntB).apply("eleven").apply("teen").accept(111);

                assertEquals("eleventeen", consumedStringE);
                assertEquals(111, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void bifn_to_objLongFn() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(concat, saveStringFLongB).apply("twenty").apply("-two").accept(22L);

                assertEquals("twenty-two", consumedStringF);
                assertEquals(22L, consumedLongB);
            }
        }
    }

    @Test
    void bifn_to_unop() {
        assertEquals("goodbye?", Z.fuse(concat, addQuestionMark).apply("good").apply("bye"));
    }

    @Test
    void bifn_to_biop() {
        assertEquals("same-ish", Z.fuse(concat, relation).apply("yo").apply(" mama").apply("YO MAMA"));
    }

    @Test
    void dblFn_to_fn() {
        assertEquals("1.0!", Z.fuse(doubleToString, addExclamationMark).apply(1.0));
    }

    @Test
    void dblFn_to_bifn() {
        assertEquals("1.0.0", Z.fuse(doubleToString, concat).apply(1.0).apply(".0"));
    }

    @Test
    void dblFn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(doubleToString, stringToDouble).applyAsDouble(1.0));
    }

    @Test
    void dblFn_to_toDblBin() {
        assertEquals(3.0, Z.fuse(doubleToString, addStringsAsDouble).apply(1.0).applyAsDouble("2.0"));
    }

    @Test
    void dblFn_to_toInt() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(doubleToString, stringToInt).applyAsInt(1.0));
    }

    @Test
    void dblFn_to_toIntBifn() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(doubleToString, addStringsAsInt).apply(1.0).applyAsInt("2"));
    }

    @Test
    void dblFn_to_toLongFn() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(doubleToString, stringToLong).applyAsLong(1.0));
    }

    @Test
    void dblFn_to_toLongBifn() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(doubleToString, addStringsAsLong).apply(1.0).applyAsLong("2"));
    }

    @Test
    void dblFn_to_pred() {
        assertFalse(Z.fuse(doubleToString, isEmpty).test(1.0));
    }

    @Test
    void dblFn_to_bipred() {
        assertTrue(Z.fuse(doubleToString, startsWith).apply(1.0).test("1"));
    }

    @Evil
    @Test
    void dblFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(doubleToString, saveStringA).accept(1.5);

            assertEquals("1.5", consumedStringA);
        }
    }

    @Evil
    @Test
    void dblFn_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(doubleToString, saveStringsBandC).apply(2.5).accept("two and a half");

                assertEquals("2.5", consumedStringB);
                assertEquals("two and a half", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(doubleToString, saveStringDDoubleB).apply(3.5).accept(4.5);

                assertEquals("3.5", consumedStringD);
                assertEquals(4.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(doubleToString, saveStringEIntB).apply(5.5).accept(6);

                assertEquals("5.5", consumedStringE);
                assertEquals(6, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void dblFn_to_objLongCns() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(doubleToString, saveStringFLongB).apply(7.5).accept(8);

                assertEquals("7.5", consumedStringF);
                assertEquals(8, consumedLongB);
            }
        }
    }

    @Test
    void dblFn_to_unop() {
        assertEquals("9.5?", Z.fuse(doubleToString, addQuestionMark).apply(9.5));
    }

    @Test
    void dblFn_to_biop() {
        assertEquals("same-ish", Z.fuse(doubleToString, relation).apply(10.5).apply("10.5"));
    }

    @Test
    void dblToInt_to_intFn() {
        assertEquals("1", Z.fuse(doubleToInt, intToString).apply(1.1));
    }

    @Test
    void dblToInt_to_intToDbl() {
        assertEquals(1.0, Z.fuse(doubleToInt, intToDouble).applyAsDouble(1.2));
    }

    @Test
    void dblToInt_to_intToLong() {
        assertEquals(1L, Z.fuse(doubleToInt, intToLong).applyAsLong(1.3));
    }

    @Test
    void dblToInt_to_intPred() {
        assertTrue(Z.fuse(doubleToInt, isIntTwo).test(2.1));
    }

    @Evil
    @Test
    void dblToInt_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(doubleToInt, saveIntA).accept(2.2);

            assertEquals(2, consumedIntA);
        }
    }

    @Test
    void dblToInt_to_intUnop() {
        assertEquals(4, Z.fuse(doubleToInt, addTwoToInt).applyAsInt(2.3));
    }

    @Test
    void dblToInt_to_intBiop() {
        assertEquals(5, Z.fuse(doubleToInt, addInts).apply(2.4).applyAsInt(3));
    }

    @Test
    void dblToLong_to_longFn() {
        assertEquals("1", Z.fuse(doubleToLong, longToString).apply(1.6));
    }

    @Test
    void dblToLong_to_longToDbl() {
        assertEquals(1.0, Z.fuse(doubleToLong, longToDouble).applyAsDouble(1.7));
    }

    @Test
    void dblToLong_to_longToInt() {
        assertEquals(1, Z.fuse(doubleToLong, longToInt).applyAsInt(1.8));
    }

    @Test
    void dblToLong_to_longPred() {
        assertTrue(Z.fuse(doubleToLong, isLongThree).test(3.1));
    }

    @Evil
    @Test
    void dblToLong_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;
            
            Z.fuse(doubleToLong, saveLongA).accept(3.2);

            assertEquals(3L, consumedLongA);
        }
    }

    @Test
    void dblToLong_to_longUnop() {
        assertEquals(4L, Z.fuse(doubleToLong, addThreeToLong).applyAsLong(1.2));
    }

    @Test
    void dblToLong_to_longBiop() {
        assertEquals(4L, Z.fuse(doubleToLong, addLongs).apply(1.2).applyAsLong(3L));
    }

    @Test
    void toDblFn_to_dblFn() {
        assertEquals("4.5", Z.fuse(stringToDouble, doubleToString).apply("4.5"));
    }

    @Test
    void toDblFn_to_dblToInt() {
        assertEquals(4, Z.fuse(stringToDouble, doubleToInt).applyAsInt("4.6"));
    }

    @Test
    void toDblFn_to_dblToLong() {
        assertEquals(4L, Z.fuse(stringToDouble, doubleToLong).applyAsLong("4.7"));
    }

    @Test
    void toDblFn_to_dblPred() {
        assertTrue(Z.fuse(stringToDouble, isDoubleOne).test("1.0"));
    }

    @Evil
    @Test
    void toDblFn_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(stringToDouble, saveDoubleA).accept("5.5");

            assertEquals(5.5, consumedDoubleA);
        }
    }

    @Test
    void toDblFn_to_dblUnop() {
        assertEquals(7.5, Z.fuse(stringToDouble, addOneToDouble).applyAsDouble("6.5"));
    }

    @Test
    void toDblFn_to_dblBiop() {
        assertEquals(3.0, Z.fuse(stringToDouble, addDoubles).apply("1.0").applyAsDouble(2.0));
    }

    @Test
    void toDblBifn_to_dblFn() {
        assertEquals("3.0", Z.fuse(addStringsAsDouble, doubleToString).apply("1.0").apply("2.0"));
    }

    @Test
    void toDblBifn_to_dblToInt() {
        assertEquals(3, Z.fuse(addStringsAsDouble, doubleToInt).apply("1.2").applyAsInt("2.3"));
    }

    @Test
    void toDblBifn_to_dblToLong() {
        assertEquals(10L, Z.fuse(addStringsAsDouble, doubleToLong).apply("4.5").applyAsLong("5.6"));
    }

    @Test
    void toDblBifn_to_dblPred() {
        assertTrue(Z.fuse(addStringsAsDouble, isDoubleOne).apply("0.5").test("0.5"));
    }

    @Evil
    @Test
    void toDblBifn_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(addStringsAsDouble, saveDoubleA).apply("1.0").accept("5.5");

            assertEquals(6.5, consumedDoubleA);
        }
    }
    
    @Test
    void toDblBifn_to_dblUnop() {
        assertEquals(7.5, Z.fuse(addStringsAsDouble, addOneToDouble).apply("0.5").applyAsDouble("6.0"));
    }

    @Test
    void toDblBifn_to_dblBiop() {
        assertEquals(4.0, Z.fuse(addStringsAsDouble, addDoubles).apply("0.5").apply("1.5").applyAsDouble(2.0));
    }

    @Test
    void intFn_to_fn() {
        assertEquals("1!", Z.fuse(intToString, addExclamationMark).apply(1));
    }

    @Test
    void intFn_to_bifn() {
        assertEquals("1.0", Z.fuse(intToString, concat).apply(1).apply(".0"));
    }

    @Test
    void intFn_to_toDblFn() {
        assertEquals(1.0, Z.fuse(intToString, stringToDouble).applyAsDouble(1));
    }

    @Test
    void intFn_to_toDblBin() {
        assertEquals(3.0, Z.fuse(intToString, addStringsAsDouble).apply(1).applyAsDouble("2.0"));
    }

    @Test
    void intFn_to_toInt() {
        assertEquals(1, Z.fuse(intToString, stringToInt).applyAsInt(1));
    }

    @Test
    void intFn_to_toIntBifn() {
        assertEquals(3, Z.fuse(intToString, addStringsAsInt).apply(1).applyAsInt("2"));
    }

    @Test
    void intFn_to_toLongFn() {
        assertEquals(1L, Z.fuse(intToString, stringToLong).applyAsLong(1));
    }

    @Test
    void intFn_to_toLongBifn() {
        assertEquals(3L, Z.fuse(intToString, addStringsAsLong).apply(1).applyAsLong("2"));
    }

    @Test
    void intFn_to_pred() {
        assertFalse(Z.fuse(intToString, isEmpty).test(1));
    }

    @Test
    void intFn_to_bipred() {
        assertTrue(Z.fuse(intToString, startsWith).apply(11).test("1"));
    }

    @Evil
    @Test
    void intFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(intToString, saveStringA).accept(123);

            assertEquals("123", consumedStringA);
        }
    }

    @Evil
    @Test
    void intFn_to_bicns() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                Z.fuse(intToString, saveStringsBandC).apply(34).accept("thirty four");

                assertEquals("34", consumedStringB);
                assertEquals("thirty four", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objDblCns() {
        synchronized(consumedStringD) {
            synchronized(consumedDoubleB) {
                consumedStringD = "";
                consumedDoubleB = 0.0;

                Z.fuse(intToString, saveStringDDoubleB).apply(45).accept(4.5);

                assertEquals("45", consumedStringD);
                assertEquals(4.5, consumedDoubleB);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objIntCns() {
        synchronized(consumedStringE) {
            synchronized(consumedIntB) {
                consumedStringE = "";
                consumedIntB = 0;

                Z.fuse(intToString, saveStringEIntB).apply(56).accept(67);

                assertEquals("56", consumedStringE);
                assertEquals(67, consumedIntB);
            }
        }
    }

    @Evil
    @Test
    void intFn_to_objLongCns() {
        synchronized(consumedStringF) {
            synchronized(consumedLongB) {
                consumedStringF = "";
                consumedLongB = 0L;

                Z.fuse(intToString, saveStringFLongB).apply(78).accept(89L);

                assertEquals("78", consumedStringF);
                assertEquals(89L, consumedLongB);
            }
        }
    }

    @Test
    void intFn_to_unop() {
        assertEquals("10?", Z.fuse(intToString, addQuestionMark).apply(10));
    }

    @Test
    void intFn_to_biop() {
        assertEquals("same-ish", Z.fuse(intToString, relation).apply(234).apply("234"));
    }

    @Test
    void intToDbl_to_dblFn() {
        assertEquals("1.0", Z.fuse(intToDouble, doubleToString).apply(1));
    }

    @Test
    void intToDbl_to_dblToInt() {
        assertEquals(2, Z.fuse(intToDouble, doubleToInt).applyAsInt(2));
    }

    @Test
    void intToDbl_to_dblToLong() {
        assertEquals(3L, Z.fuse(intToDouble, doubleToLong).applyAsLong(3));
    }

    @Test
    void intToDbl_to_dblPred() {
        assertTrue(Z.fuse(intToDouble, isDoubleOne).test(1));
    }

    @Evil
    @Test
    void intToDbl_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(intToDouble, saveDoubleA).accept(1);

            assertEquals(1.0, consumedDoubleA);
        }
    }

    @Test
    void intToDbl_to_dblUnop() {
        assertEquals(2.0, Z.fuse(intToDouble, addOneToDouble).applyAsDouble(1));
    }

    @Test
    void intToDbl_to_dblBiop() {
        assertEquals(1.5, Z.fuse(intToDouble, addDoubles).apply(1).applyAsDouble(0.5));
    }

    @Test
    void intToLong_to_longFn() {
        assertEquals("1", Z.fuse(intToLong, longToString).apply(1));
    }

    @Test
    void intToLong_to_longToDbl() {
        assertEquals(1.0, Z.fuse(intToLong, longToDouble).applyAsDouble(1));
    }

    @Test
    void intToLong_to_longToInt() {
        assertEquals(1, Z.fuse(intToLong, longToInt).applyAsInt(1));
    }

    @Test
    void intToLong_to_longPred() {
        assertTrue(Z.fuse(intToLong, isLongThree).test(3));
    }

    @Evil
    @Test
    void  intToLong_to_longCns() {
        synchronized(consumedLongA) {
            consumedLongA = 0L;
            
            Z.fuse(intToLong, saveLongA).accept(3);

            assertEquals(3L, consumedLongA);
        }
    }

    @Test
    void  intToLong_to_longUnop() {
        assertEquals(4L, Z.fuse(intToLong, addThreeToLong).applyAsLong(1));
    }

    @Test
    void  intToLong_to_longBiop() {
        assertEquals(4L, Z.fuse(intToLong, addLongs).apply(1).applyAsLong(3L));
    }

    @Test
    void toIntFn_to_intFn() {
        assertEquals("1", Z.fuse(stringToInt, intToString).apply("1"));
    }

    @Test
    void toIntFn_to_intToDbl() {
        assertEquals(1.0, Z.fuse(stringToInt, intToDouble).applyAsDouble("1"));
    }

    @Test
    void toIntFn_to_intToLong() {
        assertEquals(1L, Z.fuse(stringToInt, intToLong).applyAsLong("1"));
    }

    @Test
    void toIntFn_to_intPred() {
        assertTrue(Z.fuse(stringToInt, isIntTwo).test("2"));
    }

    @Evil
    @Test
    void toIntFn_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(stringToInt, saveIntA).accept("5");

            assertEquals(5, consumedIntA);
        }
    }

    @Test
    void toIntFn_to_intUnop() {
        assertEquals(8, Z.fuse(stringToInt, addTwoToInt).applyAsInt("6"));
    }

    @Test
    void toIntFn_to_intBiop() {
        assertEquals(3, Z.fuse(stringToInt, addInts).apply("1").applyAsInt(2));
    }

    @Test
    void toIntBifn_to_intFn() {
        assertEquals("3", Z.fuse(addStringsAsInt, intToString).apply("1").apply("2"));
    }

    @Test
    void toIntBifn_to_intToDbl() {
        assertEquals(3.0, Z.fuse(addStringsAsInt, intToDouble).apply("1").applyAsDouble("2"));
    }

    @Test
    void toIntBifn_to_dblToLong() {
        assertEquals(9L, Z.fuse(addStringsAsInt, intToLong).apply("4").applyAsLong("5"));
    }

    @Test
    void toIntBifn_to_intPred() {
        assertTrue(Z.fuse(addStringsAsInt, isIntTwo).apply("-1").test("3"));
    }

    @Evil
    @Test
    void toIntBifn_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(addStringsAsInt, saveIntA).apply("2").accept("3");

            assertEquals(5, consumedIntA);
        }
    }
    
    @Test
    void toIntBifn_to_intUnop() {
        assertEquals(6, Z.fuse(addStringsAsInt, addTwoToInt).apply("1").applyAsInt("3"));
    }

    @Test
    void toIntBifn_to_intBiop() {
        assertEquals(6, Z.fuse(addStringsAsInt, addInts).apply("1").apply("2").applyAsInt(3));
    }

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
        assertThrows(NumberFormatException.class, () -> Z.fuse(getString, stringToDouble).getAsDouble());
    }

    @Test
    void sup_to_toDblBifn() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(getString, addStringsAsDouble).applyAsDouble("1.0"));
    }

    @Test
    void sup_to_toIntFn() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(getString, stringToInt).getAsInt());
    }

    @Test
    void sup_to_toIntBifn() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(getString, addStringsAsInt).applyAsInt("1"));
    }

    @Test
    void sup_to_toLongFn() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(getString, stringToLong).getAsLong());
    }

    @Test
    void sup_to_toLongBifn() {
        assertThrows(NumberFormatException.class, () -> Z.fuse(getString, addStringsAsLong).applyAsLong("1"));
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

    // TODO: BooleanSupplier
    // TODO: DoubleSupplier
    // TODO: IntSupplier
    // TODO: LongSupplier
    // TODO: UnaryOperator
    // TODO: BinaryOperator
    // TODO: DoubleUnaryOperator
    // TODO: DoubleBinaryOperator
    // TODO: IntUnaryOperator
    // TODO: IntBinaryOperator
    // TODO: LongUnaryOperator
    // TODO: LongBinaryOperator
}
