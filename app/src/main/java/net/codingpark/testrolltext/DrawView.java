package net.codingpark.testrolltext;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by ethanshan on 15-2-15.
 */
public class DrawView extends View{

    private static final String TAG = DrawView.class.getSimpleName();

    private TextPaint mPaint        = null;
    private String mText            = "讪笑西王学文王学宾0dsfasdfasd***#####&……%%&**"
            + "讪讪讪笑西王学文王学宾0dsfasdfasd***#####&……"
            + "%%&**笑西王学文王学宾0dsfasdfasd***#####&……%%&"
            + "%%&**笑西王学文王学宾0dsfasdfasd***#####&……%%&"
            + "%%&**笑西王学文王学宾0dsfasdfasd***#####&……%%&"
            + "**笑西王学文王学宾0dsfasdfasd***#####&……%%&**";
    private float pos               = 0f;
    private float mTextLen          = 0f;
    private Handler mHandler        = null;
    private Path mPath              = null;

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new TextPaint();
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(50f);
        mPaint.setAntiAlias(true);
        //mPaint.setStyle(Paint.Style.FILL);
        mPaint.setTextAlign(Paint.Align.CENTER);

        //setBackgroundColor(Color.BLACK);
        //pos = mPaint.measureText(mText);
        pos = 0;
        mTextLen = mPaint.measureText(mText);
        float len = 0;
        for (int i = 0; i < mText.length(); i++) {
            len += mPaint.measureText(String.valueOf(mText.charAt(i)));
            Log.d(TAG, "len = " + mPaint.measureText(String.valueOf(mText.charAt(i))));
        }
        Log.d(TAG, "Total length = " + len);
        Log.d(TAG, "mTextSize = " + mPaint.getTextSize());
        Log.d(TAG, "mTextCount = " + mText.length());
        Log.d(TAG, "mTextLen = " + mTextLen);

        mPath = new Path();
        mPath.moveTo(50, 0);
        mPath.lineTo(50, mTextLen);

        mHandler = new Handler();
        mHandler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (pos < -mTextLen)
                pos = 0f;
            pos -= 1.0f;
            try {
                DrawView.this.invalidate();
                Thread.sleep(0, 1);
                mHandler.post(runnable);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, pos, 200, mPaint);
/*        Log.d(TAG, "pos = " + pos);*/
        /*
        canvas.save();
        canvas.rotate(90);
        canvas.drawText(mText, 0, 200, mPaint);
        canvas.restore();
        */
        /*
        canvas.drawPath(mPath, mPaint);
        canvas.drawTextOnPath(mText, mPath, -100, 0, mPaint);
        */
        /*
        TextPaint tp = new TextPaint();
        tp.setColor(Color.BLUE);
        tp.setStyle(Style.FILL);
        tp.setTextSize(50);
        String message = "paint,draw paint指用颜色画,如油画颜料、水彩或者水墨画,而draw 通常指用铅笔、钢笔或者粉笔画,后者一般并不涂上颜料。两动词的相应名词分别为p";
        */
        /*
        StaticLayout myStaticLayout = new StaticLayout(mText, mPaint, 60, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        myStaticLayout.draw(canvas);
        canvas.restore();
        */
        /*
        char[] arrs = mText.toCharArray();
        for (int i = 0; i < arrs.length; i++) {
            canvas.drawText(String.valueOf(arrs[i]), 50, i * 60 + pos, mPaint);
        }
        */
    }


}
