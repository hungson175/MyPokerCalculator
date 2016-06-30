package calculator.poker.vn.mobile.dtd.mypokercalculator;

/**
 * Created by hungson175 on 6/28/2016.
 */
public class Result {
    int xWinCount;
    int xLossCount;
    int xEqualCount;

    public int getxEqualCount() {
        return xEqualCount;
    }

    public int getxLossCount() {
        return xLossCount;
    }

    public int getxWinCount() {
        return xWinCount;
    }

    public int getTotalGames() {
        return xWinCount + xLossCount + xEqualCount;
    }

    public Result(int xWinCount, int xLossCount, int xEqualCount) {
        this.xWinCount = xWinCount;
        this.xLossCount = xLossCount;
        this.xEqualCount = xEqualCount;
    }

    public void incXWin() {
        xWinCount++;
    }
    public void incXLoss() {
        xLossCount++;
    }

    public void incXEquals() {
        xEqualCount++;
    }
    @Override
    public String toString() {

        double xp = getTotalGames() == 0 ? 0.0 : 100.0 * getxWinCount() / getTotalGames();
        double ep = getTotalGames() == 0 ? 0.0 : 100.0 * getxEqualCount() / getTotalGames();
        double yp = getTotalGames() == 0 ? 0.0 : 100.0 * getxLossCount() / getTotalGames();
        return String.format("Hand X: %.2f , Equal: %.2f , Hand Y: %.2f ", xp, ep, yp);
    }

    public double getXWinPercentage() {
        return getTotalGames() == 0 ? 0.0 : 100.0 * getxWinCount() / getTotalGames();
    }

    public double getYWinPercentage() {
        return getTotalGames() == 0 ? 0.0 : 100.0 * getxLossCount() / getTotalGames();
    }
}
