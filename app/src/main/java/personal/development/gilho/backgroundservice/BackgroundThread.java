package personal.development.gilho.backgroundservice;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by gilho on 25/02/17.
 */

public class BackgroundThread extends Thread {

    private Handler handler;
    private String threadOrigin, threadDestination;

    private ThreadListener listener;

    // receive the UI Thread's listener instance
    public BackgroundThread(ThreadListener listener) {
        this.listener = listener;
    }

    // Make this thread into a Pipeline Thread
    @Override
    public void run() {

        try {
            Looper.prepare();
            handler = new Handler();
            Looper.loop();
        } catch(Throwable t) {

        }

    }

    // method that starts the download by giving it the task
    public synchronized void startDownload(final DownloadTask task) {

        // adding task to this thread's task queue
        handler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    // starting the task
                    // the task will result in this thread's class variables being set
                    task.run();

                } finally {
                    // this class' variables will be passed onto the UI Thread's variables
                    updateUI();
                }
            }
        });

    }

    public void updateUI() {
        // call the UI Thread's Handler so that we can add a task to its task queue
        if (listener != null) {
            listener.handleDownload();
        }
    }

    public String getThreadOrigin() {
        return threadOrigin;
    }

    public void setThreadOrigin(String threadOrigin) {
        this.threadOrigin = threadOrigin;
    }

    public String getThreadDestination() {
        return threadDestination;
    }

    public void setThreadDestination(String threadDestination) {
        this.threadDestination = threadDestination;
    }
}






























