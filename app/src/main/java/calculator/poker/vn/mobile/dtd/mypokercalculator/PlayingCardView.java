package calculator.poker.vn.mobile.dtd.mypokercalculator;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import junit.framework.Assert;

/**
 * Created by hungson175 on 29/06/2016.
 */
public class PlayingCardView  extends RelativeLayout {

    private Card card;
    private int layoutWidth;
    private int layoutHeight;
    private TextView tvCardNumber;
    private int fontSize;
    private ImageView ivType;


    public PlayingCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        this.tvCardNumber = new TextView(getContext());
        this.ivType = new ImageView(getContext());
    }

    public PlayingCardView(Context context) {
        super(context);
        initView();
    }

    public void setCard(Card card) {
        this.card = card;
        if ( card != null)
            redraw();
        else {
            this.tvCardNumber.setText("");
            this.ivType.setImageResource(android.R.color.transparent);
            this.setBackgroundResource(R.drawable.card_back_bg);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.layoutWidth = w;
        this.layoutHeight = h;

        this.fontSize = calculateFontSize(w,h);


        RelativeLayout.LayoutParams tvParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        tvParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        tvParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        this.addView(tvCardNumber,tvParams);


        int imageSize = calculateImageSize(w,h);
        RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(imageSize,imageSize);
        ivParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addView(ivType, ivParams);

        setBackground(getContext().getDrawable(R.drawable.card_back_bg));
        invalidate();
    }

    private int calculateImageSize(int w, int h) {
        return Math.min(w,h)/2;
    }

    private int calculateFontSize(int w, int h) {
        return w/6;
    }


    private void redraw() {
        Assert.assertNotNull(card);
        this.setBackgroundResource(R.drawable.card_bg);
        tvCardNumber.setText(card.getNumber().getDrawText());
        tvCardNumber.setTextColor(ContextCompat.getColor(getContext(),card.getType().getColorResourceId()));
        ivType.setImageResource(card.getType().getImageResourceId());
        invalidate();
    }
}
