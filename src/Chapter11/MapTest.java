package Chapter11;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author Evan
 * @date 2018/09/11 07:27
 */
public class MapTest {
    public static void main(String[] args) {
        Random random = new Random(47);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            int r = random.nextInt(20);
            Integer freq = map.get(r);
            map.put(r, freq == null ? 1 : freq + 1);
        }
        System.out.println(map);
        for (Integer i : map.keySet()) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
