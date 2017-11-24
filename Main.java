import java.io.IOException;
import java.util.Date;

public class Main{
    public static void main(String[] args) {
        ClockTimer timer = new ClockTimer(0,0,5,400);
        timer.start();
        Date date = new Date();
        timer.setTimerListener(new ClockTimer.ClockTimerListener() {
            @Override
            public void onSecondsUpdate(ClockTimer remainingTime) {
                Double time = Double.parseDouble(String.valueOf(new Date().getTime() - date.getTime()));
                time /= 1000;
                System.out.println("Time elapsed in Seconds: " + time);
            }

            @Override
            public void onTimerEnd() {
                System.out.println("Time Up!!");
            }
        });
    }
}
