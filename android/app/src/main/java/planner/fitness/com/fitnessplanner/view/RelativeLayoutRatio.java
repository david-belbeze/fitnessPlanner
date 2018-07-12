package planner.fitness.com.fitnessplanner.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import planner.fitness.com.fitnessplanner.R;

/**
 * This is a relative layout which allows to set a predefined layout ratio
 */

public class RelativeLayoutRatio extends RelativeLayout {

    private int width_ratio;
    private int height_ratio;

    public RelativeLayoutRatio(Context context) {
        this(context, null, 0);

        setDefaultWidthAndHeight();
    }

    public RelativeLayoutRatio(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RelativeLayoutRatio(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ViewRatio,
                defStyleAttr,
                0
        );

        try{
            String ratio = typedArray.getString(R.styleable.ViewRatio_ratio);

            if (ratio != null && ratio.contains(":")) {
                String[] temp = ratio.split(":");
                if (temp.length != 2) {
                    setDefaultWidthAndHeight();
                } else {
                    try {
                        width_ratio = Integer.parseInt(temp[0]);
                        height_ratio = Integer.parseInt(temp[1]);
                    } catch (NullPointerException e) {
                        setDefaultWidthAndHeight();
                    }
                }
            }
        } finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minw, widthMeasureSpec, 1);

        int wr = w - getPaddingLeft() - getPaddingRight();

        int h = resolveSizeAndState(
                getSuggestedMinimumHeight()
                        + getPaddingBottom()
                        + getPaddingTop()
                , heightMeasureSpec, 0);

        int hr; // = h - getPaddingTop() - getPaddingBottom();
        if (width_ratio != -1 && height_ratio != -1){
            hr = (int) ((float) wr * (float) height_ratio / (float) width_ratio);
            h = hr + getPaddingTop() + getPaddingBottom();
        }

        setMeasuredDimension(w, h);
    }

    /**
     * Set width and height to -1 : in that case no ratio is calculated
     */
    private void setDefaultWidthAndHeight(){
        width_ratio = -1;
        height_ratio = -1;
    }
}
