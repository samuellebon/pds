package kan10;

import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectUtilTest {

    @Test
    public void testWithNotNullObjectShouldBeKO() {
        assertFalse(ObjectUtil.isNull(new String()));
    }

    @Test
    public void testWithNullObjectShouldBeOK() {
        String s = null;
        assertTrue(ObjectUtil.isNull(s));
    }
}