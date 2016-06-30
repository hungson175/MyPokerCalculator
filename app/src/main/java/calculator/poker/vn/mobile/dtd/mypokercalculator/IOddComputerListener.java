package calculator.poker.vn.mobile.dtd.mypokercalculator;

/**
 * Created by hungson175 on 30/06/2016.
 */
public interface IOddComputerListener {
    public void onProgressUpdate(Result rs, int nCompletedGames, int nTotalGames);

    public void onCompleted(Result rs, int nTotalGames);

    public void onCancelled(Result rs, int nCompletedGames);

    public void onGoodApproximationFound(Result rs, int nCompletedGames, int numGames);
}
