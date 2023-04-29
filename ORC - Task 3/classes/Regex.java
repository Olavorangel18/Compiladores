package classes;

public class Regex {

    private static final String num_regex = "(\\d)+";
    private static final String op_regex = "(\\+|-|\\*|/)";
    private static final String minus_regex = "(\\-)+";
    private static final String plus_regex = "(\\+)+";
    private static final String star_regex = "(\\*)+";
    private static final String slash_regex = "(/)+";

    public static boolean isNum(String token) {
        return token.matches(num_regex);
    }

    public static boolean isOP(String token) {
        return token.matches(op_regex);
    }

    public static boolean isMinus(String token) {
        return token.matches(minus_regex);
    }

    public static boolean isPlus(String token) {
        return token.matches(plus_regex);
    }

    public static boolean isStar(String token) {
        return token.matches(star_regex);
    }

    public static boolean isSlash(String token) {
        return token.matches(slash_regex);
    }

}