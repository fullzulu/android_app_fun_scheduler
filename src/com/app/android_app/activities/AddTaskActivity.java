package com.app.android_app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.app.android_app.R;
import com.app.android_app.util.ActivityUtils;
import com.app.android_app.util.Constants;

import java.io.File;
import java.util.Calendar;

/**
 * @author Aliaksandr_Pleski
 *         <p/>
 *         This class is an activity for adding tasks
 *         add_task.xml layout
 */
public class AddTaskActivity extends Activity {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    static final int DATE_DIALOG_ID = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        initRadioGroup();
        initTimePicker();
        initCurrentTasksButton();
        initStartButton();
        setDateTimeOnView();
    }

    private void initStartButton() {
        Button startTask = (Button) findViewById(R.id.startTask);
        startTask.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
                TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

                final Calendar calendar = Calendar.getInstance();

                if (datePicker.getVisibility() == View.VISIBLE) {
                    calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), timePicker.getCurrentHour(), timePicker.getCurrentMinute());
                } else {
                    calendar.add(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
                    calendar.add(Calendar.MINUTE, timePicker.getCurrentMinute());
                }
                calendar.set(Calendar.SECOND, 0);

                Bundle extras = new Bundle();
                extras.putSerializable(Constants.CALENDAR, calendar);

                ActivityUtils.startActivity(getApplicationContext(), CurrentTaskActivity.class, extras);
            }
        });
    }

    private void saveTaskInfo() {
        File tasks = new File("../res/xml/tasks.xml");
        if (!tasks.exists()) {

        }
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

    private void initTimePicker() {
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
    }

    private void initRadioGroup() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
                TextView textView = (TextView) findViewById(R.id.chooseTitle);
                if (checkedId == R.id.atRadioButton) {
                    datePicker.setVisibility(DatePicker.VISIBLE);
                    textView.setText(Constants.CHOOSE_TIME_AND_DATE);
                } else {
                    datePicker.setVisibility(DatePicker.INVISIBLE);
                    textView.setText(Constants.CHOOSE_TIME);
                }
            }
        });
    }

    private void setDateTimeOnView() {

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        Calendar c = Calendar.getInstance();
        /*TODO uncomment these rows for datepicker restriction*/
//        c.add(Calendar.DAY_OF_MONTH, 1);
        datePicker.setMinDate(c.getTimeInMillis());
//        c = Calendar.getInstance();

        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH) + 1;
        hour = 00;//c.get(Calendar.HOUR_OF_DAY); //get current hour!!!!!
        minute = 01;//c.get(Calendar.MINUTE);
        second = c.get(Calendar.SECOND);

        // set current date into datepicker
        datePicker.init(year, month, day, null);
        timePicker.setCurrentHour(hour);
        timePicker.setCurrentMinute(minute);

    }

}
