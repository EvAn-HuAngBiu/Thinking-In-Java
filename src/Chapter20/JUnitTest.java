package Chapter20;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Evan
 * @date 2018/09/25 16:26
 */
public class JUnitTest {
    public static int count = 0;

    @Test
    public void testValue() {
        assertEquals(2, 1 + 1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"lower, mid, upper"})
    public void testLowerCase(String s) {
        assertTrue(isLowerCase(s));
    }

    @RepeatedTest(10)
    public void repeatTest() {
        System.out.println("Test " + count++);
    }


    public boolean isLowerCase(String s) {
        return s.toLowerCase().equals(s);
    }

    public static void main(String[] args) {

    }
}
