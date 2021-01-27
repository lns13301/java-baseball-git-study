package view;

public class OutputView {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";

    public static void printStrike(int count) {
        System.out.print(count + "스트라이크");
    }

    public static void printBall(int count) {
        System.out.print(count + "볼" + SPACE);
    }

    public static void printNothing() {
        System.out.print("낫싱");
    }

    public static void printNewLine() {
        System.out.println(NEW_LINE);
    }

    public static void printGameEnd() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }
}
