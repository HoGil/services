package personal.development.gilho.backgroundservice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements ThreadListener {

    private BackgroundThread backThread;
    private Handler handler;
    private String activityOrigin, activityDestination;
    private TextView viewOrigin, viewDestination;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewOrigin = (TextView)findViewById(R.id.view_origin);
        viewDestination = (TextView)findViewById(R.id.view_destination);

        // create background thread and pass the context which has the ThreadListener
        backThread = new BackgroundThread(this);

        // start the background thread
        backThread.start();

        // create the handler for this UI Thread (automatically attaches to Looper)
        handler = new Handler();

        // start the download and give to it the background thread so it can access its class
        // variables
        backThread.startDownload(new DownloadTask(backThread));

    }

    @Override
    public void handleDownload() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                activityOrigin = backThread.getThreadOrigin();
                activityDestination = backThread.getThreadDestination();
                viewOrigin.setText("Origin: " + activityOrigin);
                viewDestination.setText("Destination: " + activityDestination);

            }
        });
    }


}
