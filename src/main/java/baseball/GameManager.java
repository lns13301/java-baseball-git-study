package baseball;

import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.Scanner;

public class GameManager {
    private static final int EMPTY_COUNT = 0;
    private static final int HOME_RUN_COUNT = 3;

    public void doGame() {

    }

    public String makeRandomNumber() {
        return null;
    }

    public void showMatchResult(Scanner scanner, List<String> GameNumbers, List<String> playerNumbers) {
        if (isNothing(GameNumbers, playerNumbers)) {
            OutputView.printNothing();

            return;
        }

        OutputView.printBall(getBallCount(GameNumbers, playerNumbers));
        OutputView.printStrike(getStrikeCount(GameNumbers, playerNumbers));

        if (isHomeRun(GameNumbers, playerNumbers)) {
            OutputView.printGameEnd();
            InputView.inputContinue(scanner);
        }
    }

    public boolean isHomeRun(List<String> GameNumbers, List<String> playerNumbers) {
        return getStrikeCount(GameNumbers, playerNumbers) == HOME_RUN_COUNT;
    }

    public boolean isNothing(List<String> GameNumbers, List<String> playerNumbers) {
        return isHomeRun(GameNumbers, playerNumbers)
                && getBallCount(GameNumbers, playerNumbers) == EMPTY_COUNT;
    }

    private int getBallCount(List<String> GameNumbers, List<String> playerNumbers) {
        long matchCount = GameNumbers.stream()
                .filter(number -> playerNumbers.stream()
                        .anyMatch(pNumber -> pNumber.equals(number)))
                .count();

        return (int) matchCount - getStrikeCount(GameNumbers, playerNumbers);
    }

    private int getStrikeCount(List<String> GameNumbers, List<String> playerNumbers) {
        int strikeCount = EMPTY_COUNT;

        for (int i = 0; i < GameNumbers.size(); i++) {
            if (GameNumbers.get(i).equals(playerNumbers.get(i))) {
                strikeCount++;
            }
        }

        return strikeCount;
    }
}
