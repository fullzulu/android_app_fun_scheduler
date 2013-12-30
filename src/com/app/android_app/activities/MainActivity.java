package com.app.android_app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.app.android_app.R;
import com.app.android_app.util.ActivityUtils;

/**
 * @author Aliaksandr_Pleski
 *         <p/>
 *         This class is a main activity of the application.
 *         main.xml layout
 */
public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initAddTaskButton();
        initCurrentTasksButton();
        initHistoryButton();
        initAboutButton();
    }

    private void initAddTaskButton() {
        Button addTask = (Button) findViewById(R.id.addTaskButton);
        addTask.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(getApplicationContext(), AddTaskActivity.class);
            }
        });
    }

    private void initCurrentTasksButton() {
        Button addTask = (Button) findViewById(R.id.currentTasksButton);
        addTask.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(getApplicationContext(), CurrentTaskActivity.class);
            }
        });
    }

    private void initHistoryButton() {
        Button addTask = (Button) findViewById(R.id.historyButton);
        addTask.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(getApplicationContext(), HistoryActivity.class);
            }
        });
    }

    private void initAboutButton() {
        Button addTask = (Button) findViewById(R.id.aboutButton);
        addTask.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(getApplicationContext(), AboutActivity.class);
            }
        });
    }
}
