package github.arobot.countdownbuttonlibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by arobot on 2015/10/30 0030.
 */
public class CountDownButton extends Button {
    /**
     * Default Text from {@link #getText()}
     */
    private String defaultText;
    /**
     * Running number
     */
    private int count;
    /**
     * Max number ,count down from this
     */
    private int countLength;
    /**
     * String formatter showing while counting down
     */
    private String format;
    /**
     * flag ,count down
     */
    private boolean canCountDown = false;
    /**
     * OnClickListener
     */
    private OnClickListener onClickListener;
    /**
     * Count down  interval
     */
    private int countInterval = 1 * 1000;

    public CountDownButton(Context context) {
        super(context);
    }

    public CountDownButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义的XML属性名称
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CountDownButton);
        if (a.hasValue(R.styleable.CountDownButton_countDown)) {
            this.canCountDown = true;
            this.countLength = a.getInt(R.styleable.CountDownButton_countDown, 0);
        }
        this.format = a.getString(R.styleable.CountDownButton_countDownStrFormatter);
        this.countInterval = a.getInt(R.styleable.CountDownButton_countDownInterval, 1000);
        a.recycle();
    }

    public CountDownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                Rect rect = new Rect();
                this.getGlobalVisibleRect(rect);
                if (onClickListener != null && rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    onClickListener.onClick(this);
                }
                if (canCountDown && rect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    defaultText = this.getText().toString();
                    count = countLength;
                    handler.removeMessages(0);
                    handler.sendEmptyMessage(0);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
        }
        return super.onTouchEvent(event);
    }

    public void setCanCountDown(boolean canCountDown) {
        this.canCountDown = canCountDown;
    }

    public int getCountLength() {
        return countLength;
    }

    /**
     * @param countLength
     */
    public void setCountLength(int countLength) {
       this.countLength = countLength;
    }


    public String getFormat() {
        return format;
    }

    /**
     * Count down text formatter.{@link java.util.Formatter#format}
     *
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * set count down attrs
     *
     * @param countDown count down from this number
     * @param format    string formatter for your countting down label
     */
    public void setCountDown(int countDown, String format) {
        this.countLength = countDown;
        this.format = format;
        setCanCountDown(true);
    }

    /**
     * start count down without click this button,it needs call {@link #setCanCountDown(boolean)} or set "countDown" in xml first
     *
     * @return count down enable
     */
    public boolean startCountDown() {
        if (canCountDown) {
            defaultText = this.getText().toString();
            count = countLength;
            handler.removeMessages(0);
            handler.sendEmptyMessage(0);
        }
        return canCountDown;
    }

    public int getCountInterval() {
        return countInterval;
    }

    /**
     * Count time interval,default is 1 sec.
     *
     * @param countInterval
     */
    public void setCountInterval(int countInterval) {
        this.countInterval = countInterval;
    }

    private void setCountDownText(int number, String format) {
        if (number > 0) {
            this.setEnabled(false);
            this.setClickable(false);
            if (TextUtils.isEmpty(format))
                this.setText(number);
            else
                this.setText(String.format(format, number));
        } else {
            this.setText(defaultText);
            this.setEnabled(true);
            this.setClickable(true);
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            count--;
            setCountDownText(count, format);
            if (count >= 0)
                handler.sendEmptyMessageDelayed(0, countInterval);
        }
    };

    public OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}
