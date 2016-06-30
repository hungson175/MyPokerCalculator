package calculator.poker.vn.mobile.dtd.mypokercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.security.InvalidParameterException;

public class TestCustomViewActivity extends AppCompatActivity {

    private EditText etCard;
    private Button btParse;
    private PlayingCardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_custom_view);
        cardView = (PlayingCardView) findViewById(R.id.myCard);
        cardView.setCard(new Card(CardNumber._A,CardType.S));
        etCard = (EditText) findViewById(R.id.etCard);
        btParse = (Button) findViewById(R.id.btParse);
        btParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = etCard.getText().toString().trim();
                if ( s.length() == 2 ) {
                    try {
                        Card card = Card.parse(s.charAt(0), s.charAt(1));
                        cardView.setCard(card);
                    } catch (InvalidParameterException e) {
                        //nothing
                    }
                }
            }
        });

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//
//        GridView grid = (GridView) findViewById(R.id.gridview);
//        Card[] CARDS = {
//                new Card(CardNumber._2,CardType.H),
//                new Card(CardNumber._3,CardType.H),
//                new Card(CardNumber._4,CardType.H),
//                new Card(CardNumber._5,CardType.H),
//                new Card(CardNumber._6,CardType.H),
//        };
//        MyCardsAdapter adapter = new MyCardsAdapter(CARDS);
//        grid.setAdapter(adapter);
    }

    private class MyCardsAdapter extends BaseAdapter {
        private final Card[] cards;

        public MyCardsAdapter(Card[] cards) {
            this.cards = cards;
        }

        @Override
        public int getCount() {
            return cards.length;
        }

        @Override
        public Card getItem(int position) {
            return cards[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            PlayingCardView v = (PlayingCardView) Helpers.inflateView(getApplicationContext(),R.layout.card_item);
            v.setCard(getItem(position));
            return v;
        }

        private class Holder {
        }
    }
}
