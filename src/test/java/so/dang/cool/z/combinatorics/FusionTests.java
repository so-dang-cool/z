package so.dang.cool.z.combinatorics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;

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
    void fn_to_toPred() {
        assertTrue(Z.fuse(trim, isEmpty).test(" "));
    }

    @Test
    void fn_to_toBipred() {
        assertTrue(Z.fuse(trim, startsWith).apply(" hello ").test("hell"));
    }

    @Test
    void fn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(trim, saveStringA).accept(" hello ");

            assertEquals("hello", consumedStringA);
        }
    }

    @Test
    void fn_to_Bicns() {
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
    void bifn_to_toPred() {
        assertTrue(Z.fuse(concat, isEmpty).apply("").test(""));
    }

    @Test
    void bifn_to_toBipred() {
        assertTrue(Z.fuse(concat, startsWith).apply("ba").apply("nana").test("ban"));
    }

    @Test
    void bifn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(concat, saveStringA).apply("yo").accept("yo");

            assertEquals("yoyo", consumedStringA);
        }
    }

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
    void bifn_to_toUnop() {
        assertEquals("goodbye?", Z.fuse(concat, addQuestionMark).apply("good").apply("bye"));
    }

    @Test
    void bifn_to_toBiop() {
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

    @Test
    void dblFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(doubleToString, saveStringA).accept(1.5);

            assertEquals("1.5", consumedStringA);
        }
    }

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
    void dblFn_to_toUnop() {
        assertEquals("9.5?", Z.fuse(doubleToString, addQuestionMark).apply(9.5));
    }

    @Test
    void dblFn_to_toBiop() {
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

    @Test
    void dblToInt_to_intCns() {
        synchronized(consumedIntA) {
            consumedIntA = 0;

            Z.fuse(doubleToInt, saveIntegerA).accept(2.2);

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
    void toDbl_to_dblFn() {
        assertEquals("4.5", Z.fuse(stringToDouble, doubleToString).apply("4.5"));
    }

    @Test
    void toDbl_to_dblToInt() {
        assertEquals(4, Z.fuse(stringToDouble, doubleToInt).applyAsInt("4.6"));
    }

    @Test
    void toDbl_to_dblToLong() {
        assertEquals(4L, Z.fuse(stringToDouble, doubleToLong).applyAsLong("4.7"));
    }

    @Test
    void toDbl_to_dblPred() {
        assertTrue(Z.fuse(stringToDouble, isDoubleOne).test("1.0"));
    }

    @Test
    void toDbl_to_dblCns() {
        synchronized(consumedDoubleA) {
            consumedDoubleA = 0.0;

            Z.fuse(stringToDouble, saveDoubleA).accept("5.5");

            assertEquals(5.5, consumedDoubleA);
        }
    }

    @Test
    void toDbl_to_dblUnop() {
        assertEquals(7.5, Z.fuse(stringToDouble, addOneToDouble).applyAsDouble("6.5"));
    }

    @Test
    void toDbl_to_dblBiop() {
        assertEquals(3.0, Z.fuse(stringToDouble, addDoubles).apply("1.0").applyAsDouble(2.0));
    }
    
    // TODO: ToDoubleBiFunction

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

    @Test
    void intFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(intToString, saveStringA).accept(123);

            assertEquals("123", consumedStringA);
        }
    }

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
    void intFn_to_toUnop() {
        assertEquals("10?", Z.fuse(intToString, addQuestionMark).apply(10));
    }

    @Test
    void intFn_to_toBiop() {
        assertEquals("same-ish", Z.fuse(intToString, relation).apply(234).apply("234"));
    }

    // TODO: IntToDoubleFunction
    // TODO: IntToLongFunction
    // TODO: ToIntFunction
    // TODO: ToIntBiFunction

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

    @Test
    void longFn_to_cns() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            Z.fuse(longToString, saveStringA).accept(345L);

            assertEquals("345", consumedStringA);
        }
    }

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
    void longFn_to_toUnop() {
        assertEquals("100?", Z.fuse(longToString, addQuestionMark).apply(100L));
    }

    @Test
    void longFn_to_toBiop() {
        assertEquals("same-ish", Z.fuse(longToString, relation).apply(789L).apply("789"));
    }

    // TODO: LongToDoubleFunction
    // TODO: LongToIntFunction
    // TODO: ToLongFunction
    // TODO: ToLongBiFunction
    // TODO: Predicate
    // TODO: BiPredicate
    // TODO: DoublePredicate
    // TODO: IntPredicate
    // TODO: LongPredicate
    // TODO: Consumer
    // TODO: BiConsumer
    // TODO: DoubleConsumer
    // TODO: ObjDoubleConsumer
    // TODO: IntConsumer
    // TODO: ObjIntConsumer
    // TODO: LongConsumer
    // TODO: ObjLongConsumer
    // TODO: Supplier
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
