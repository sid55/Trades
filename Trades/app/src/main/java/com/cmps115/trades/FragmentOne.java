package app.src.main.java.com.cmps115.trades;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;


public class FragmentOne extends Fragment {

    private static final int PROGRESS = 0x1;
    private ProgressBar progressBar;
    private int mProgressStatus = 0;
    private Handler mHandler = new Handler();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setMax(100);

        // Start lengthy operation in a background thread
        new Thread(new Runnable() {
            public void run() {
                mProgressStatus = 33;

                // Update the progress bar
                mHandler.post(new Runnable() {
                    public void run() {
                        progressBar.setProgress(mProgressStatus);
                    }
                });

            }
        }).start();


        return view;
    }
}
