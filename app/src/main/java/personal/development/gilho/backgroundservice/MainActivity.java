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

        // create background thread
        backThread = new BackgroundThread(this);
        // start the background thread
        backThread.start();

        // create the handler
        handler = new Handler();

        // start the download
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
