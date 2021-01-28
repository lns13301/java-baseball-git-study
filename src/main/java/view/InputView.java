package view;

import baseball.GameState;

import java.util.Scanner;

public class InputView {
    private static final String GAME_RESTART_VALUE = "1";

    public static String inputNumbers(Scanner scanner) {
        System.out.print("숫자를 입력해주세요 : ");

        return scanner.next();
    }

    public static GameState inputContinue(Scanner scanner) {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");

        if (scanner.next().equals(GAME_RESTART_VALUE)) {
            return GameState.PLAY;
        }

        return GameState.END;
    }
}
