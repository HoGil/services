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
    public BackgroundThread(ThreadListener listener) {
        this.listener = listener;
    }

    @Override
    public void run() {

        try {
            Looper.prepare();
            handler = new Handler();
            Looper.loop();
        } catch(Throwable t) {

        }

    }

    public synchronized void startDownload(final DownloadTask task) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                try {

                    task.run();

                } finally {
                    updateUI();
                }
            }
        });

    }

    public void updateUI() {
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






























