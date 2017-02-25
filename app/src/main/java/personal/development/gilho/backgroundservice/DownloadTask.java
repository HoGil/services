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

    public DownloadTask(BackgroundThread thread) {
        lengthSec = random.nextInt(3) + 1;
        backgroundThread = thread;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(lengthSec * 1000);
            origin = "Sydney";
            destination = "Paris";
            backgroundThread.setThreadOrigin(origin);
            backgroundThread.setThreadDestination(destination);

        } catch (Throwable t) {
            Log.e("Download Task", "Some Error", t);
        }
    }
}
