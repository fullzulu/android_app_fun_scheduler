package com.app.android_app.activities;

import android.app.Activity;
import android.os.Bundle;
import com.app.android_app.R;

/**
 * @author Aliaksandr_Pleski
 *         <p/>
 *         This class is an activity for showing history, variety of executed tasks
 *         history.xml layout
 */
public class HistoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
    }
}
