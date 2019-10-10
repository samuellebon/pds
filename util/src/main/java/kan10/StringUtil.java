package kan10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static boolean containsInvalidCharacter(String s) {
        Pattern p = Pattern.compile("[^A-Za-z0-9 ]+");
        Matcher m = p.matcher(s);
        return m.find();
    }

    public static boolean containsOnlyMetacharacter(String s) {
        Pattern p = Pattern.compile("^\\W*$");
        Matcher m = p.matcher(s);
        return m.find();
    }
}
