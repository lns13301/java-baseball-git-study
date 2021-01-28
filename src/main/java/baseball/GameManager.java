package baseball;

import utils.RandomUtils;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManager {
    private static final int EMPTY_COUNT = 0;
    private static final int HOME_RUN_COUNT = 3;
    private static final int START_VALUE = 1;
    private static final int END_VALUE = 9;

    private GameState gameState;

    public GameManager() {
        gameState = GameState.PLAY;
    }

    public void startGame(Scanner scanner) {
        while (gameState.equals(GameState.PLAY)) {
            doGame(scanner, makeRandomNumbers());
        }
    }

    public void doGame(Scanner scanner, List<String> gameNumbers) {
        while (true) {
            List<String> numbers = makeStringToListString(InputView.inputNumbers(scanner));

            showMatchResult(scanner, gameNumbers, numbers);

            if (isHomeRun(gameNumbers, numbers)) {
                break;
            }
        }
    }

    private List<String> makeStringToListString(String value) {
        List<String> result = new ArrayList<>();
        char[] chars = value.toCharArray();

        for (char number : chars) {
            result.add(Character.toString(number));
        }

        return result;
    }

    public List<String> makeRandomNumbers() {
        List<String> numbers = new ArrayList<>();

        for (int i = 0; i < HOME_RUN_COUNT; i++) {
            int randomValue = RandomUtils.nextInt(START_VALUE, END_VALUE);

            if (numbers.stream()
                    .noneMatch(number -> number.equals(Integer.toString(randomValue)))) {
                numbers.add(Integer.toString(randomValue));
            }

            if (numbers.size() == HOME_RUN_COUNT) {
                break;
            }
        }

        return numbers;
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
            gameState = InputView.inputContinue(scanner);
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
