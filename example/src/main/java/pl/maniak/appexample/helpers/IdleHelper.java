package pl.maniak.appexample.helpers;

import pl.maniak.appexample.common.log.L;

/**
 * Created by pliszka on 12.01.16.
 */
public class IdleHelper extends Thread {

    private long lastUsed;
    private long period;
    private boolean stop;

    public IdleHelper(long period) {
        this.period = period;
        stop = false;
    }

    public void run() {
        long idle = 0;
        touch();

        do {
            idle = System.currentTimeMillis()- lastUsed;
            L.d("Application is idle for " + idle + " ms");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                L.e("IdleHelper.run() ", e);
            }

            if(idle > period) {
                idle = 0;
                // TODO: 12.01.16 do something here
            }
        } while ((!stop));
        L.d("Finishing Waiter thread");
    }

    public synchronized void touch() {
        lastUsed = System.currentTimeMillis();
    }

    public synchronized void forceInterrupt() {
        this.interrupt();
    }

    public synchronized void stopIdle() {
        stop = true;
    }

    public synchronized void setPeriod(long period) {
        this.period = period;
    }

// http://stackoverflow.com/questions/4075180/application-idle-time/4075857#comment-7234627
}
