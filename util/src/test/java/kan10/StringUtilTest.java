package kan10;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void testStringWithInvalidCharacterShouldBeKO () {
        assertTrue(StringUtil.containsInvalidCharacter("Th!is is not a v&lid St#ring"));
    }

    @Test
    public void testStringWithValidCharacterShouldBeOK() {
        assertFalse(StringUtil.containsInvalidCharacter("this is a valid String"));
    }

    @Test
    public void testStringWithMixCharacterShouldBeKO() {
        assertFalse(StringUtil.containsOnlyMetacharacter("Rêturn @false"));
    }

    @Test
    public void testStringWithoutMetacharacterShouldBeKO() {
        assertFalse(StringUtil.containsOnlyMetacharacter("return FALSE"));
    }

    @Test
    public void testStringWithMetacharacterOnlyShouldBeOK() {
        assertTrue(StringUtil.containsOnlyMetacharacter("#!@"));
    }
}