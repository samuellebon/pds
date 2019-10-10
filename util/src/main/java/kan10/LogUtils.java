package kan10;

public class LogUtils {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\033[38;5;197m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String	ANSI_MAGENTA = "\033[38;5;213m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static String red(String s) {
        return ANSI_RED + s + ANSI_RESET ;
    }

    public static String black(String s) {
        return ANSI_BLACK + s + ANSI_RESET ;
    }

    public static String green(String s) {
        return ANSI_GREEN + s + ANSI_RESET ;
    }

    public static String yellow(String s) {
        return ANSI_YELLOW + s + ANSI_RESET ;
    }

    public static String blue(String s) {
        return ANSI_BLUE + s + ANSI_RESET ;
    }

    public static String cyan(String s) {
        return ANSI_CYAN + s + ANSI_RESET ;
    }

    public static String magenta(String s) {
        return ANSI_MAGENTA + s + ANSI_RESET ;
    }

    public static String white(String s) {
        return ANSI_WHITE + s + ANSI_RESET ;
    }
}
