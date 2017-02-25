package personal.development.gilho.backgroundservice;

import android.util.Log;

import java.util.Random;

/**
 * Created by gilho on 25/02/17.
 */

public class DownloadTask implements Runnable {

    private static final Random random = new Random();
    private int lengthSec;
    private BackgroundThread backgroundThread;
    private String origin, destination;

    // task created while also having access to the background thread's class variables
    public DownloadTask(BackgroundThread thread) {
        lengthSec = random.nextInt(3) + 1;
        backgroundThread = thread;
    }

    @Override
    public void run() {
        try {

            // simulates a delay
            Thread.sleep(lengthSec * 1000);
            origin = "Sydney";
            destination = "Paris";

            // set thread's origin and destination variables
            backgroundThread.setThreadOrigin(origin);
            backgroundThread.setThreadDestination(destination);

        } catch (Throwable t) {
            Log.e("Download Task", "Some Error", t);
        }
    }
}
