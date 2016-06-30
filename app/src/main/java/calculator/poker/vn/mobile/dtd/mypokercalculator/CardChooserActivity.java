package calculator.poker.vn.mobile.dtd.mypokercalculator;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import junit.framework.Assert;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CardChooserActivity extends AppCompatActivity {

    private static final String TAG = "sonph.poker.calculator";
    private LinearLayout layoutCommunityCards;
    private EditText etCommunityCards;
    private LinearLayout layoutFirstPlayer;
    private LinearLayout layoutSecondPlayer;
    private EditText etFirstPlayer;
    private ArrayList<PlayingCardView> fpCardViews;
    private TextView tvFirstWinPercentage;
//    private TextView tvFirstLossPercentage;
    private EditText etSecondPlayer;
    private ArrayList<PlayingCardView> spCardViews;
    private TextView tvSecondWinPercentage;
//    private TextView tvSecondLossPercentage;
    private ArrayList<PlayingCardView> communityCardViews;
    private Button btStop;
    private Button btStart;
    private OddComputer oddComputer = null;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_card_chooser);

        processCommunityCards();

        layoutFirstPlayer = (LinearLayout) findViewById(R.id.layoutFirstPlayer);
        processFirstPlayer();

        layoutSecondPlayer = (LinearLayout) findViewById(R.id.layoutSecondPlayer);
        processSecondPlayer();

        btStop = (Button) findViewById(R.id.btStop);
        btStart = (Button) findViewById(R.id.btStart);
        btStart.setOnClickListener(new View.OnClickListener() {
            public int currentPercentage;

            @Override
            public void onClick(View v) {
                try {
                    KnownCards kcards = getKnownCards();
                    if (oddComputer != null) {
                        oddComputer.stop();
                    }
                    oddComputer = new OddComputer();
                    this.currentPercentage = 0;
                    IOddComputerListener listener = new IOddComputerListener() {
                        @Override
                        public void onProgressUpdate(final Result rs, final int nCompletedGames, final int nTotalGames) {
                            final int percentage = (int) ((100L * nCompletedGames) / nTotalGames);
                            if (percentage > currentPercentage) {
                                currentPercentage = percentage;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tvFirstWinPercentage.setText("Win: " + String.format("%.2f",rs.getXWinPercentage()) + "%") ;
                                        tvSecondWinPercentage.setText("Win: " + String.format("%.2f",rs.getYWinPercentage()) + "%");
                                        progressBar.setProgress(percentage);
                                    }
                                });
                            }

                        }

                        @Override
                        public void onCompleted(Result rs, int nTotalGames) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(100);
                                }
                            });
                        }

                        @Override
                        public void onCancelled(Result rs, int nCompletedGames) {

                        }

                        @Override
                        public void onGoodApproximationFound(Result rs, int nCompletedGames, int numGames) {
                            oddComputer.stop();
                            progressBar.setProgress(100);
                        }
                    };
                    oddComputer.startComputingOdd(listener, kcards);
                } catch (InvalidParameterException e) {
                    notifyInvalidInput(e);
                }
            }
        });

        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oddComputer != null) {
                    oddComputer.stop();
                    oddComputer = null;
                }
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    private KnownCards getKnownCards() {
        return new KnownCards(
                parseCardsFromString(etFirstPlayer.getText().toString()),
                parseCardsFromString(etSecondPlayer.getText().toString()),
                parseCardsFromString(etCommunityCards.getText().toString())
        );
    }

    private void processSecondPlayer() {
        etSecondPlayer = (EditText) layoutSecondPlayer.findViewById(R.id.etPlayerCards);
        spCardViews = getPlayerCardViews(layoutSecondPlayer);
        tvSecondWinPercentage = (TextView) layoutSecondPlayer.findViewById(R.id.tvWinPercentage);
//        tvSecondLossPercentage = (TextView) layoutSecondPlayer.findViewById(R.id.tvLosePercentage);

        etSecondPlayer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                parseCards(etSecondPlayer, spCardViews);
            }
        });
    }

    private void processFirstPlayer() {
        etFirstPlayer = (EditText) layoutFirstPlayer.findViewById(R.id.etPlayerCards);
        fpCardViews = getPlayerCardViews(layoutFirstPlayer);
        tvFirstWinPercentage = (TextView) layoutFirstPlayer.findViewById(R.id.tvWinPercentage);
//        tvFirstLossPercentage = (TextView) layoutFirstPlayer.findViewById(R.id.tvLosePercentage);

        etFirstPlayer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                parseCards(etFirstPlayer, fpCardViews);
            }
        });
    }

    private void processCommunityCards() {
        layoutCommunityCards = (LinearLayout) findViewById(R.id.layoutCommunityCards);
        addCardViews(layoutCommunityCards);
        etCommunityCards = (EditText) findViewById(R.id.etCommunityCards);
        etCommunityCards.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                parseCards(etCommunityCards, communityCardViews);
            }
        });
    }

    private void parseCards(EditText etCards, ArrayList<PlayingCardView> cardViews) {
        String s = etCards.getText().toString();
        Log.i(TAG,"editText: " + etCards.getId() + " | content: " + etCards.getText());
        if (s.length() % 2 != 0 || s.length() / 2 > cardViews.size()) {
            //nothing
        } else {
            try {
                List<Card> cards = parseCardsFromString(s);
                Log.i(TAG,"Parsed cards: ");
                for (int i = 0; i < cards.size(); i++) {
                    Card card = cards.get(i);
                    Log.i(TAG,"Card: " + card);
                    cardViews.get(i).setCard(card);
                }
                for(int i = cards.size() ; i < cardViews.size() ; i++) {
                    cardViews.get(i).setCard(null);
                }
                Log.i(TAG,"=================");
            } catch (InvalidParameterException e) {
                notifyInvalidInput(e);
            }
        }
    }

    private void notifyInvalidInput(InvalidParameterException e) {
        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
    }

    private List<Card> parseCardsFromString(String s) throws InvalidParameterException {
        List<Card> list = new ArrayList<>();
        Assert.assertTrue(s.length() % 2 == 0);
        int nCards = s.length() / 2;
        for (int i = 0; i < nCards; i++) {
            int startIndex = i * 2;
            list.add(Card.parse(s.charAt(startIndex), s.charAt(startIndex + 1)));
        }
        return list;
    }

    private void addCardViews(LinearLayout layoutCommunityCards) {
        communityCardViews = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PlayingCardView cardView = new PlayingCardView(getApplicationContext());
            int widthInPx = getResources().getDimensionPixelSize(R.dimen.card_width);
            int heightInPx = getResources().getDimensionPixelSize(R.dimen.card_height);
            cardView.setBackgroundResource(R.drawable.card_bg);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(widthInPx, heightInPx);
            params.setMargins(getResources().getDimensionPixelSize(R.dimen.std_pad),0,0,0);
            layoutCommunityCards.addView(cardView, params);
            communityCardViews.add(cardView);
        }
    }

    private ArrayList<PlayingCardView> getPlayerCardViews(LinearLayout layoutPlayer) {
        ArrayList<PlayingCardView> list = new ArrayList<>();
        list.add((PlayingCardView) layoutPlayer.findViewById(R.id.firstCard));
        list.add((PlayingCardView) layoutPlayer.findViewById(R.id.secondCard));
        return list;
    }
}
