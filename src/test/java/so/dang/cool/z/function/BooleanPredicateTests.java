package so.dang.cool.z.function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static so.dang.cool.z.combination.TestFunctions.*;

import org.junit.jupiter.api.Test;

public class BooleanPredicateTests {
    @Test
    public void id() {
        assertEquals(booleanId.test(true), BooleanPredicate.ID.test(true));
        assertEquals(booleanId.test(false), BooleanPredicate.ID.test(false));
        assertNotEquals(booleanId.test(true), BooleanPredicate.ID.test(false));
        assertNotEquals(booleanId.test(false), BooleanPredicate.ID.test(true));
    }

    @Test
    public void not() {
        assertEquals(not.test(true), BooleanPredicate.NOT.test(true));
        assertEquals(not.test(false), BooleanPredicate.NOT.test(false));
        assertNotEquals(not.test(true), BooleanPredicate.NOT.test(false));
        assertNotEquals(not.test(false), BooleanPredicate.NOT.test(true));
    }

    @Test
    public void and() {
        assertTrue(booleanId.and(booleanId).test(true));
        assertFalse(booleanId.and(not).test(true));
        assertFalse(not.and(booleanId).test(true));
        assertFalse(not.and(not).test(true));
    }

    @Test
    public void or() {
        assertTrue(booleanId.or(booleanId).test(true));
        assertTrue(booleanId.or(not).test(true));
        assertTrue(not.or(booleanId).test(true));
        assertFalse(not.or(not).test(true));
    }
    
    @Test
    public void negate() {
        assertFalse(booleanId.negate().test(true));
        assertTrue(not.negate().test(true));
    }
    
}
