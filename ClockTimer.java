import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.RunnableFuture;

public class ClockTimer implements Runnable{
    int hours, minutes, seconds, miliSeconds;
    Thread t;
    ClockTimerListener listener;
    public ClockTimer(int hours, int minutes, int seconds, int miliSeconds){
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.miliSeconds = miliSeconds;
        t = new Thread(this);
    }
    public void start(){
        t.start();
    }
    public void setTimerListener(ClockTimer.ClockTimerListener listener){
        this.listener = listener;
    }

    @Override
    public void run() {
        System.out.println("Starting new Thread");
        int s = seconds + (60*minutes) + (3600*hours);
        for(int i=1;i<=s;i++){
            try {
                t.sleep(1000);
                listener.onSecondsUpdate(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            t.sleep(miliSeconds);
            listener.onSecondsUpdate(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listener.onTimerEnd();
    }

    public interface ClockTimerListener{
        void onSecondsUpdate(ClockTimer remainingTime);
        void onTimerEnd();
    }
}

