package so.dang.cool.z.combinatorics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combinatorics.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

@Evil
public class AbsorptionTests {
    @Evil
    @Test
    void cns_to_sup() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            assertEquals(suppliedString, Z.absorb(saveStringA, getString).apply("hello"));
            assertEquals("hello", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_boolSup() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            assertEquals(suppliedBoolean, Z.absorb(saveStringA, getBoolean).test("salut"));
            assertEquals("salut", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_dblSup() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            assertEquals(suppliedDouble, Z.absorb(saveStringA, getDouble).applyAsDouble("hola"));
            assertEquals("hola", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_intSup() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            assertEquals(suppliedInt, Z.absorb(saveStringA, getInt).applyAsInt("안녕하세요"));
            assertEquals("안녕하세요", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_longSup() {
        synchronized(consumedStringA) {
            consumedStringA = "";

            assertEquals(suppliedLong, Z.absorb(saveStringA, getLong).applyAsLong("kamusta"));
            assertEquals("kamusta", consumedStringA);
        }
    }

    @Evil
    @Test
    void cns_to_op() {
        synchronized(consumedStringA) {
            synchronized(wasOperated) {
                consumedStringA = "";
                wasOperated = false;

                Z.absorb(saveStringA, doOperation).accept("merhaba");

                assertEquals("merhaba", consumedStringA);
                assertTrue(wasOperated);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_sup() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(suppliedString, Z.absorb(saveStringsBandC, getString).apply("cześć").apply("喂"));
                assertEquals("cześć", consumedStringB);
                assertEquals("喂", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_boolSup() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(suppliedBoolean, Z.absorb(saveStringsBandC, getBoolean).apply("hei").test("hej"));
                assertEquals("hei", consumedStringB);
                assertEquals("hej", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_dblSup() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(suppliedDouble, Z.absorb(saveStringsBandC, getDouble).apply("buenas").applyAsDouble("dias"));
                assertEquals("buenas", consumedStringB);
                assertEquals("dias", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_intSup() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(suppliedInt, Z.absorb(saveStringsBandC, getInt).apply("안녕").applyAsInt("하세요"));
                assertEquals("안녕", consumedStringB);
                assertEquals("하세요", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_longSup() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                consumedStringB = "";
                consumedStringC = "";

                assertEquals(suppliedLong, Z.absorb(saveStringsBandC, getLong).apply("ça").applyAsLong("va"));
                assertEquals("ça", consumedStringB);
                assertEquals("va", consumedStringC);
            }
        }
    }

    @Evil
    @Test
    void bicns_to_op() {
        synchronized(consumedStringB) {
            synchronized(consumedStringC) {
                synchronized(wasOperated) {
                    consumedStringB = "";
                    consumedStringC = "";
                    wasOperated = false;

                    Z.absorb(saveStringsBandC, doOperation).apply("...").accept("!!!");

                    assertEquals("...", consumedStringB);
                    assertEquals("!!!", consumedStringC);
                    assertTrue(wasOperated);
                }
            }
        }
    }

    // TODO: DoubleConsumer
    // TODO: ObjDoubleConsumer
    // TODO: IntConsumer
    // TODO: ObjIntConsumer
    // TODO: LongConsumer
    // TODO: ObjLongConsumer
}
