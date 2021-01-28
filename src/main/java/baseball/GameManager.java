package baseball;

import view.OutputView;

import java.util.List;

public class GameManager {
    private static final int EMPTY_COUNT = 0;
    private static final int STRIKE_COUNT = 3;

    public void doGame() {

    }

    public String makeRandomNumber() {
        return null;
    }

    public void showMatchResult(List<String> GameNumbers, List<String> playerNumbers) {
        OutputView.printBall(getBallCount(GameNumbers, playerNumbers));
        OutputView.printStrike(getStrikeCount(GameNumbers, playerNumbers));
    }

    public boolean isStrike(List<String> GameNumbers, List<String> playerNumbers) {
        return getStrikeCount(GameNumbers, playerNumbers) == STRIKE_COUNT;
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
