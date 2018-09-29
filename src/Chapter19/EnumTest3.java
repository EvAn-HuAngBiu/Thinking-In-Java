package Chapter19;

import java.util.Random;

import static Chapter19.EnumTest3.Outcome.DRAW;
import static Chapter19.EnumTest3.Outcome.LOSE;
import static Chapter19.EnumTest3.Outcome.WIN;

/**
 * @author Evan
 * @date 2018/09/24 09:55
 */
public class EnumTest3 {
    public enum Outcome {DRAW, LOSE, WIN}

    public enum RoShamBo {
        PAPER(DRAW, LOSE, WIN),
        SCISSORS(WIN, DRAW, LOSE),
        ROCK(LOSE, WIN, DRAW);

        private Outcome vPaper, vScissors, vRock;

        RoShamBo(Outcome vPaper, Outcome vScissors, Outcome vRock) {
            this.vPaper = vPaper;
            this.vScissors = vScissors;
            this.vRock = vRock;
        }

        public Outcome compete(RoShamBo r) {
            switch (r) {
                case PAPER:
                    return vPaper;
                case SCISSORS:
                    return vScissors;
                case ROCK:
                    return vRock;
                default:
                    throw new RuntimeException("Unknown type");
            }
        }
    }

    private static Random rand = new Random(System.currentTimeMillis());

    public static RoShamBo random(Class<RoShamBo> clazz) {
        return clazz.getEnumConstants()[rand.nextInt(3)];
    }

    public static void main(String[] args) {
        int win = 0, lose = 0, draw = 0;
        for (long i = 0; i < 100; i++) {
            RoShamBo r1 = random(RoShamBo.class), r2 = random(RoShamBo.class);
            Outcome result = r1.compete(r2);
            if (result == WIN) {
                win++;
            } else if (result == DRAW) {
                draw++;
            } else {
                lose++;
            }
            System.out.println(r1.name() + " vs " + r2.name() + ", {" + result + "}");
        }

        System.out.println();
        System.out.format("Statistics: Win = %d, Draw = %d, Lose = %d", win, draw, lose);
    }
}
