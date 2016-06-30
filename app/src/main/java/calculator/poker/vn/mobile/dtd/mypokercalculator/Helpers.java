package calculator.poker.vn.mobile.dtd.mypokercalculator;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hungson175 on 6/26/2016.
 */
public class Helpers {
    /**
     * Sort increasing order
     * @param cards
     */
    public static void sortCardsByNumberInc(List<Card> cards) {
        Collections.sort(cards, new Comparator<Card>() {
            @Override
            public int compare(Card lhs, Card rhs) {
                return lhs.getNumber().ordinal() - rhs.getNumber().ordinal();
            }
        });
    }

    public static<T> List<T> merge(List<T> first, List<T> second) throws InvalidParameterException {
        if ( first == null || second == null)
            throw new InvalidParameterException();
        ArrayList<T> mergeList = new ArrayList<>();
        for (T e : first) {
            mergeList.add(e);
        }
        for (T e : second) {
            mergeList.add(e);
        }
        return mergeList;
    }

    public static String printCards(List<Card> cards) {
        StringBuilder sb = new StringBuilder();
        for(Card card : cards) {
            sb.append(card.toString()+",");
        }
        return sb.toString();
    }

    public static View inflateView(Context context, int layout) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return layoutInflater.inflate(layout,null,false);
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float dp2px(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float px2dp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }
}
