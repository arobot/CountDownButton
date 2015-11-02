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



- "**countDown**" 	count down from this number<br>


- "**countDownInterval**" 	 count down interval,velocity of your count down event<br>


- "**countDownStrFormatter**" 	string formatter for your counting down label<br>

###In java code

     CountDownButton.startCountDown();                          //直接开始倒计时
     CountDownButton.setCanCountDown(false);                    //关闭倒计时模式
     CountDownButton.setCountDown(1000,"点我开始倒计时%s");     //设置倒计时参数，倒计时数字以占位符的形式添加的button上进行显示
     CountDownButton.setCountInterval(100);                     //倒计时间隔
     CountDownButton.setCountLength(1500);                      //倒计时长度

