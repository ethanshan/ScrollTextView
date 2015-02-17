package net.codingpark.testrolltext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * The custom TextView widget support text auto scroll
 */
public class ScrollTextView extends TextView {

    private static final String TAG         = ScrollTextView.class.getSimpleName();

    /**
     * Scroll text from bottom to up
     */
    public static final int DIRECTION_BOTTOM_TO_UP      = 0;
    /**
     * Scroll text from up to bottom
     */
    public static final int DIRECTION_UP_TO_BOTTOM      = 1;
    /**
     * Scroll text from left to right
     */
    public static final int DIRECTION_LEFT_TO_RIGHT     = 2;
    /**
     * Scroll text from right to left
     */
    public static final int DIRECTION_RIGHT_TO_LEFT     = 3;

    /**
     * Text scroll low speed
     */
    public static final int SCROLL_SPEED_LOW            = 0;

    /**
     * Text scroll middle speed
     */
    public static final int SCROLL_SPEED_MIDDLE         = 1;

    /**
     * Text scroll high speed
     */
    public static final int SCROLL_SPEED_HIGH           = 2;

    /**
     * The text scroll direction
     */
    //private int direction                   = DIRECTION_BOTTOM_TO_UP;
    //private int direction                   = DIRECTION_UP_TO_BOTTOM;
    private int direction                   = DIRECTION_RIGHT_TO_LEFT;

    /**
     * The scroll speed level
     */
    //private int scroll_speed                = SCROLL_SPEED_MIDDLE;
    private int scroll_speed                = SCROLL_SPEED_HIGH;

    private float step                      = 0f;

    private Paint mPaint                    = null;

    private String text                     =
              //"2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010"
             "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010";

    private float width                     = 0;

    /**
     * The list to store the text on every line(Top Down direction)
     */
    private List<String> textList           = new ArrayList<>();


    /**
     * The constructor
     * @param context The application context
     */
    public ScrollTextView(Context context) {
        super(context);
        init();
    }

    /**
     * The constructor
     * @param context The application context
     * @param attrs The ScrollTextView AttributeSet object
     */
    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * The constructor
     * @param context The application context
     * @param attrs The ScrollTextView AttributeSet object
     * @param defStyleAttr The ScrollTextView default style resource id
     */
    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new TextPaint();
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(50f);
        mPaint.setAntiAlias(true);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            throw new IllegalStateException("ScrollLayout only can mCurScreen run at EXACTLY mode!");
        }

        float length = 0;
        if(text == null || text.length()==0){
            return ;
        }

        //下面的代码是根据宽度和字体大小，来计算textview显示的行数。

        textList.clear();

        StringBuilder builder = new StringBuilder();
        for(int i=0;i<text.length();i++){
            if(length<width){
                builder.append(text.charAt(i));
                length += mPaint.measureText(text.substring(i, i+1));
                if(i==text.length()-1){
                    textList.add(builder.toString());
                }
            }else{
                textList.add(builder.toString().substring(0,builder.toString().length()-1));
                builder.delete(0, builder.length()-1) ;
                length= mPaint.measureText(text.substring(i, i+1));
                i--;
            }

        }
    }


    //下面代码是利用上面计算的显示行数，将文字画在画布上，实时更新。
    @Override
    public void onDraw(Canvas canvas) {

        switch (direction) {
            case DIRECTION_BOTTOM_TO_UP:
                break;
            case DIRECTION_UP_TO_BOTTOM:
                break;
            case DIRECTION_LEFT_TO_RIGHT:
                break;
            case DIRECTION_RIGHT_TO_LEFT:
                break;
            default:
                break;
        }

        if (direction == DIRECTION_BOTTOM_TO_UP) {
            if(textList.size()==0)  return;
            for (int i = 0; i < textList.size(); i++) {
                canvas.drawText(textList.get(i), 0, this.getHeight()+(i+1)*mPaint.getTextSize()-step, mPaint);
            }
            switch (scroll_speed) {
                case SCROLL_SPEED_LOW:
                    step += 0.5f;
                    break;
                case SCROLL_SPEED_MIDDLE:
                    step += 1.0f;
                    break;
                case SCROLL_SPEED_HIGH:
                    step += 2.0f;
                    break;
            }
            if (step >= this.getHeight()+textList.size()*mPaint.getTextSize()) {
                step = 0;
            }
        }
        else if (direction == DIRECTION_UP_TO_BOTTOM) {

            if(textList.size()==0)  return;
            for (int i = 0; i < textList.size(); i++) {
                canvas.drawText(textList.get(i), 0, -(textList.size() - i)*mPaint.getTextSize() + step, mPaint);
            }
            switch (scroll_speed) {
                case SCROLL_SPEED_LOW:
                    step += 0.5f;
                    break;
                case SCROLL_SPEED_MIDDLE:
                    step += 1.0f;
                    break;
                case SCROLL_SPEED_HIGH:
                    step += 2.0f;
                    break;
            }
            if (step >= this.getHeight() + textList.size() * mPaint.getTextSize()) {
                step = 0;
            }
        }
        else if (direction == DIRECTION_LEFT_TO_RIGHT) {
            if (step >= this.getWidth() + text.length() * mPaint.getTextSize()) {
                step = 0;
            }
            switch (scroll_speed) {
                case SCROLL_SPEED_LOW:
                    step += 0.5f;
                    break;
                case SCROLL_SPEED_MIDDLE:
                    step += 1.0f;
                    break;
                case SCROLL_SPEED_HIGH:
                    step += 2.0f;
                    break;
            }
            canvas.drawText(text, this.getWidth() - step, 200, mPaint);
        }
        else if (direction == DIRECTION_RIGHT_TO_LEFT) {
            if (step >= this.getWidth() + text.length() * mPaint.getTextSize()) {
                step = 0;
            }
            switch (scroll_speed) {
                case SCROLL_SPEED_LOW:
                    step += 0.5f;
                    break;
                case SCROLL_SPEED_MIDDLE:
                    step += 1.0f;
                    break;
                case SCROLL_SPEED_HIGH:
                    step += 2.0f;
                    break;
            }
            canvas.drawText(text, this.getWidth() - step, 200, mPaint);
        }

        invalidate();
    }
}
