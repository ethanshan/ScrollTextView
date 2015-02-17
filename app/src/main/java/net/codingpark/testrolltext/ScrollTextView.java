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
 */
public class ScrollTextView extends TextView {

    private static final String TAG         = ScrollTextView.class.getSimpleName();

    public static enum ScrollDirection {
        /**
         * Scroll text from bottom to up
         */
        DIRECTION_BOTTOM_TO_UP,
        /**
         * Scroll text from up to bottom
         */
        DIRECTION_UP_TO_BOTTOM,
        /**
         * Scroll text from left to right
         */
        DIRECTION_LEFT_TO_RIGHT,
        /**
         * Scroll text from right to left
         */
        DIRECTION_RIGHT_TO_LEFT
    }

    private int direction                   = 0;

    private float step                      = 0f;

    private Paint mPaint                    = null;

    private String text                     =
              "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010"
            + "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010"
            + "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010"
            + "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010"
            + "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010"
            + "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010"
            + "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010"
            + "2001开始开始茶杯*#06#20102001开始开始茶杯*#06#20102001开始开始茶杯*#06#2010";

    private float width                     = 0;
    //分行保存textview的显示信息
    private List<String> textList           = new ArrayList<String>();


    public ScrollTextView(Context context) {
        super(context);
        init();
    }

    public ScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new TextPaint();
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(80f);
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
        if(textList.size()==0)  return;
        for (int i = 0; i < textList.size(); i++) {
            canvas.drawText(textList.get(i), 0, this.getHeight()+(i+1)*mPaint.getTextSize()-step, mPaint);
        }

        step = step + 0.2f;
        if (step >= this.getHeight()+textList.size()*mPaint.getTextSize()) {
            step = 0;
        }

        invalidate();
    }
}
