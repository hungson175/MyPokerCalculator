package calculator.poker.vn.mobile.dtd.mypokercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btStart;
    private TextView tvResult;
    private EditText etHand1;
    private EditText etHand2;
    private EditText etRevealedCards;
    private Button btStop;
    private TextView tvProgress;

    OddComputer oddComputer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btStart = (Button) findViewById(R.id.btStart);
        btStop = (Button) findViewById(R.id.btStopComputing);
        tvResult = (TextView) findViewById(R.id.tvResult);
        tvProgress = (TextView) findViewById(R.id.tvProgress);

        etHand1 = (EditText) findViewById(R.id.etHand1);
        etHand2 = (EditText) findViewById(R.id.etHand2);
        etRevealedCards = (EditText) findViewById(R.id.etRevealedCards);



        btStart.setOnClickListener(new View.OnClickListener() {
            int currentPercentage = 0;
            @Override
            public void onClick(View v) {
                try {
                    final KnownCards kcards = getKnownCards();
                    if ( MainActivity.this.oddComputer != null) {
                        MainActivity.this.oddComputer.stop();
                    }
                    MainActivity.this.oddComputer = new OddComputer();
                    this.currentPercentage = 0;
                    IOddComputerListener listener = new IOddComputerListener() {
                        @Override
                        public void onProgressUpdate(final Result rs, final int nCompletedGames, final int nTotalGames) {
                            int percentage = (int) ((100L * nCompletedGames) / nTotalGames);
                            if (percentage > currentPercentage) {
                                currentPercentage = percentage;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvResult.setText("Current result: " + rs.toString());
                                        tvProgress.setText("Percent: " + currentPercentage +"% (Completed: "+nCompletedGames+"/ Total: "+nTotalGames+")");
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCompleted(final Result rs, final int nTotalGames) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvResult.setText("Current result: " + rs.toString());
                                    tvProgress.setText("Done 100%- total: "+nTotalGames+ " games");
                                }
                            });

                        }

                        @Override
                        public void onCancelled(final Result rs, final int nCompletedGames) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvResult.setText("Current result: " + rs.toString());
                                    tvProgress.setText("Cancelled - tried: "+nCompletedGames+ " games");
                                }
                            });

                        }

                        @Override
                        public void onGoodApproximationFound(final Result rs, final int nCompletedGames, int numGames) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvResult.setText("Current result: " + rs.toString());
                                    tvProgress.setText("Cancelled with good approximation- tried: "+nCompletedGames+ " games");
                                    MainActivity.this.oddComputer.stop();
                                }
                            });
                        }
                    };
                    oddComputer.startComputingOdd(listener, kcards);
                } catch (InvalidParameterException e) {
                    notifyInvalidCards(e);
                }
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( MainActivity.this.oddComputer == null) return;
                MainActivity.this.oddComputer.stop();
            }
        });
    }

    private void notifyInvalidCards(InvalidParameterException e) {
        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
    }

    private KnownCards getKnownCards() throws  InvalidParameterException {
        return new KnownCards(
                parseCards(etHand1.getText().toString()),
                parseCards(etHand2.getText().toString()),
                parseCards(etRevealedCards.getText().toString())
        );
    }

    /**
     *
     * @param s the list of fullyVisibleHand
     * @return list of fullyVisibleHand if valid, null if invalid
     */
    private List<Card> parseCards(String s) {
        s = s.replace(" ", "").trim();
        char[] cc = s.toCharArray();
        if (cc.length % 2 != 0) return null;
        else {
            List<Card> cards = new ArrayList<Card>();
            int numCards = cc.length / 2;
            for(int i = 0; i < numCards ; i++) {
                try {
                    cards.add(Card.parse(cc[i * 2], cc[i * 2 + 1]));
                } catch (InvalidParameterException e) {
                    return null;
                }
            }
            return cards;
        }
    }
}
