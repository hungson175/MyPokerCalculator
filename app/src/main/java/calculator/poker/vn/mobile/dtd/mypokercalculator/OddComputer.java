package calculator.poker.vn.mobile.dtd.mypokercalculator;

import android.util.Log;

import junit.framework.Assert;

import java.util.ArrayList;
import java.util.List;

import calculator.poker.vn.mobile.dtd.mypokercalculator.handtypes.HandType;

/**
 * Created by hungson175 on 6/28/2016.
 */
public class OddComputer {
    static  final  int NUM_GAMES = 100000;
    static  final  int STOP_CHECK_GAMES = NUM_GAMES / 100;
    public class ComputerThread extends  Thread {
        private static final double EPS = 1e-4;
        private final KnownCards kcards;
        private IOddComputerListener listener;
        private boolean shouldStop = false;

        public ComputerThread(IOddComputerListener listener, KnownCards kcards) {
            super("Computer Thread");
            this.listener = listener;
            this.kcards = kcards;
        }

        @Override
        public void run() {
            Result rs = new Result(0,0,0);
            CardsGenerator generator = new CardsGenerator(kcards.getAllCards());
            int cnt = 0;

            double minXWP = 100.00;
            double maxXWP = 0.00;
            long resetClockStart = System.currentTimeMillis();

            for(int i = 0 ; i < NUM_GAMES ; i++) {
                if (shouldStop ) break;

                List<Card> revealedByUserCards = kcards.getRevealedByUserCards();
                List<Card> genCards = generator.nRandomCards(9 - kcards.size());


                List<Card> xCards = new ArrayList<>(kcards.getXCards()); //immutable
                List<Card> yCards = new ArrayList<>(kcards.getYCards());

                Assert.assertTrue(xCards.size() + yCards.size() >= 2);
                int genCardSize = genCards.size();
                if ( xCards.size() == 0) {
                    Assert.assertEquals(2,yCards.size());
                    xCards.add(genCards.remove(0));
                    xCards.add(genCards.remove(0));
                    genCardSize -= 2;
                    Assert.assertEquals(genCardSize,genCards.size());
                } else if (yCards.size() == 0) {
                    Assert.assertEquals(2,xCards.size());
                    yCards.add(genCards.remove(0));
                    yCards.add(genCards.remove(0));
                    genCardSize -= 2;
                    Assert.assertEquals(genCardSize,genCards.size());
//                    Log.d("PHS.DEBUG","#gencards: "+genCards.size() + " | #ycards = " + yCards.size());

                }


                Assert.assertEquals(2, xCards.size());
                Assert.assertEquals(2, yCards.size());

                List<Card> revealedCards = Helpers.merge(revealedByUserCards, genCards);

//                Log.d("PHS.DEBUG","#gencards: "+genCards.size() + " | #xcards = " + xCards.size());
                HandType xHand = HandType.parse(new FullyVisibleHand(Helpers.merge(xCards, revealedCards)));
                HandType yHand = HandType.parse(new FullyVisibleHand(Helpers.merge(yCards, revealedCards)));
                int cmp = xHand.compareTo(yHand);
                if ( cmp > 0 ) rs.incXWin();
                else if ( cmp < 0) rs.incXLoss();
                else rs.incXEquals();

                if ( System.currentTimeMillis() - resetClockStart > 500 ) {
                    resetClockStart = System.currentTimeMillis();
                    Log.d("PHS.POKER.INFO","maxWP: " + maxXWP + " | minWP: " + minXWP);
                    if ( (maxXWP > minXWP) && (maxXWP - minXWP < 0.1)) {
                        listener.onGoodApproximationFound(rs,i,NUM_GAMES);
                    }
                    minXWP = 100.00;
                    maxXWP = 0.00;
                }

                double xwp = rs.getXWinPercentage();
                if ( xwp > EPS && xwp < minXWP ) {
                    minXWP = xwp;
                }
                if ( xwp > EPS && xwp > maxXWP ) {
                    maxXWP = xwp;
                }

                if ( listener != null && i % STOP_CHECK_GAMES == 0 ) {
                    listener.onProgressUpdate(rs, i, NUM_GAMES);
                }
                cnt++;
            }
            if ( listener != null ) {
                if (cnt == NUM_GAMES) {
                    listener.onCompleted(rs, NUM_GAMES);
                } else {
                    listener.onCancelled(rs, cnt);
                }
            }
        }

        public void signalStop() {
            this.shouldStop = true;
        }
    }

    ComputerThread worker = null;
    public void stop() {
        worker.signalStop();
    }

    public void startComputingOdd(IOddComputerListener listener, KnownCards kcards) {
        worker = new ComputerThread(listener,kcards);
        worker.start();
    }
}
