package so.dang.cool.z.combination;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

import so.dang.cool.z.Z;
import so.dang.cool.z.annotation.Evil;

public class AssimilationTests {
    @Evil
    @Test
    public void assimilate2_test() {
        assertEquals("ab", Z.assimilate2(curriedConcat2).apply("a", "b"));
    }

    @Evil
    @Test
    public void assimilate3_test() {
        assertEquals("abc", Z.assimilate3(curriedConcat3).apply("a", "b", "c"));
    }

    @Evil
    @Test
    public void assimilate4_test() {
        assertEquals("abcd", Z.assimilate4(curriedConcat4).apply("a", "b", "c", "d"));
    }

    @Evil
    @Test
    public void assimilate5_test() {
        assertEquals("abcde", Z.assimilate5(curriedConcat5).apply("a", "b", "c", "d", "e"));
    }

    @Evil
    @Test
    public void assimilate6_test() {
        assertEquals("abcdef", Z.assimilate6(curriedConcat6).apply("a", "b", "c", "d", "e", "f"));
    }

    @Evil
    @Test
    public void assimilate7_test() {
        assertEquals("abcdefg", Z.assimilate7(curriedConcat7).apply("a", "b", "c", "d", "e", "f", "g"));
    }

    @Evil
    @Test
    public void assimilate8_test() {
        assertEquals("abcdefgh", Z.assimilate8(curriedConcat8).apply("a", "b", "c", "d", "e", "f", "g", "h"));
    }

    @Evil
    @Test
    public void assimilate9_test() {
        assertEquals("abcdefghi", Z.assimilate9(curriedConcat9).apply("a", "b", "c", "d", "e", "f", "g", "h", "i"));
    }

    @Evil
    @Test
    public void assimilate10_test() {
        assertEquals("abcdefghij", Z.assimilate10(curriedConcat10).apply("a", "b", "c", "d", "e", "f", "g", "h", "i", "j"));
    }

    @Evil
    @Test
    public void assimilate11_test() {
        assertEquals("abcdefghijk", Z.assimilate11(curriedConcat11).apply("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"));
    }

    @Evil
    @Test
    public void assimilate12_test() {
        assertEquals("abcdefghijkl", Z.assimilate12(curriedConcat12).apply("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l"));
    }
}
