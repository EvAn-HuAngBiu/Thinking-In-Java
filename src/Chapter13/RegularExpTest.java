package Chapter13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Evan
 * @date 2018/09/12 18:52
 */
public class RegularExpTest {
    public static void main(String[] args) {
        String testStr1 = "Hello, welcome to my new program";

        String t1 = "Hello";
        StringBuilder t2 = new StringBuilder("Hello");
        // contentEquals can be used between two CharSequence object rather than just String
        System.out.println(t1.contentEquals(t2));
        // regionMatcher can match a substring and another string
        // toffset indicate the beginning of substring, ooffset indicate the beginning of another string
        System.out.println(testStr1.regionMatches(7, "welcome", 0, 7));

        System.out.println("------------------------------------------------");
        String patStr1 = "\\w+";
        Pattern pat1 = Pattern.compile(patStr1);
        Matcher mat1 = pat1.matcher("Hello, World");
        System.out.println("The whole str meet the RegExp: " + mat1.matches());
        System.out.println("The beginning of the str meet the RegExp: " + mat1.lookingAt());
        while (mat1.find()) {
            System.out.format("%d-%d {%s}\n", mat1.start(), mat1.end(), mat1.group());
        }

        System.out.println("------------------------------------------------");
        String patStr2 = "(\\d{4})-(\\d{2})-(\\d{2})";
        Pattern pat2 = Pattern.compile(patStr2);
        Matcher mat2 = pat2.matcher("2018-09-12");
        while (mat2.find()) {
            for (int i = 0; i <= mat2.groupCount(); i++) {
                System.out.format("Group %d {%s}\n", i, mat2.group(i));
            }
        }

        System.out.println("------------------------------------------------");
        String patStr3 = "(?<year>\\d{4})-(?<month>\\d{2})-(?<day>\\d{2})";
        Pattern pat3 = Pattern.compile(patStr3);
        Matcher mat3 = pat3.matcher("2018-10-01");
        while (mat3.find()) {
            System.out.format("Group <year> {%s}\n", mat3.group("year"));
            System.out.format("Group <month> {%s}\n", mat3.group("month"));
            System.out.format("Group <day> {%s}\n", mat3.group("day"));
        }

        System.out.println("------------------------------------------------");

    }
}
