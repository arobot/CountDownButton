# CountDownButton
Extends Button with count down founction

You can dependency it with <br>
    compile 'github.arobot:countdownbuttonlibrary:1.0.0'


##How to use it

###In XML
    <github.arobot.countdownbuttonlibrary.CountDownButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@+id/text"
        android:text="点击倒计时"
        app:countDown="60"
        app:countDownInterval="2000"
        app:countDownStrFormatter="倒计时%s秒" />

"countDown" count down from this number<br>
"countDownInterval" count down interval,velocity of your count down event<br>
"countDownStrFormatter" string formatter for your countting down label<br>

###In java code

