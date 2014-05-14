package com.app.android_app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.app.android_app.R;
import com.app.android_app.util.Constants;
import com.app.logic.beans.Task;
import com.app.logic.beans.objectives.impl.TestObjective;
import com.app.logic.timer.TaskRunner;
import com.app.logic.timer.properties.impl.TimerAtProps;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Aliaksandr_Pleski
 *         <p/>
 *         This class is an activity for show currently executing tasks
 *         current_tasks.xml layout
 */
public class CurrentTaskActivity extends Activity {
    private static final String TEMP_TIME_STRING_PATTERN = "%s%s%s%s.";
    private static final String TIME_STRING_PATTERN = "%02d:%02d";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_tasks);

        Intent intent = getIntent();

        /* TODO refactor it further */
        /* TODO add some condition for getting current tasks*/
        StringBuilder lastTaskString = new StringBuilder("Here is the title. Task will be executed at ");
        Calendar calendar = (Calendar) intent.getSerializableExtra(Constants.CALENDAR);
        lastTaskString.append(calendar.get(Calendar.MONTH) + 1)
                .append("/").append(calendar.get(Calendar.DAY_OF_MONTH)).append("/")
                .append(calendar.get(Calendar.YEAR)).append(", ")
                .append(String.format(TIME_STRING_PATTERN, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE)));

        TextView lastTaskView = (TextView) findViewById(R.id.lastTaskView);
        lastTaskView.setText(lastTaskString.toString());

        TextView remainingView = (TextView) findViewById(R.id.remainingView);

        run(calendar, this);

    }

    private void/*???*/ run(Calendar calendar, Activity activity) {
        Date date = calendar.getTime();

        Task task = new Task(new TimerAtProps(date), new TestObjective("Task test"));
        TaskRunner runner = new TaskRunner(task, 1, activity);
        runner.schedule();

        /**TODO
         * insert into DB a record:
         *   id??? - some thread id may be think of it in Thread Pool Model
         *   datetime
         *   status
         * after the thread completion update status
         */
    }
}
