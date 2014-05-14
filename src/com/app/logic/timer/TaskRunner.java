package com.app.logic.timer;

import android.app.Activity;
import android.widget.TextView;
import com.app.android_app.R;
import com.app.logic.beans.Task;
import com.app.logic.timer.properties.TimerProperties;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Aliaksandr_Pleski
 */
public class TaskRunner {

    private Timer timer;
    private Task task;
    private int delay;
    //TODO think of this mix of 'view' and 'modal'
    private Activity activity;

    public TaskRunner(Task task, int delay, Activity activity) {
        this.task = task;
        this.delay = delay;
        this.timer = new Timer();
        this.activity = activity;
    }

    public void schedule() {
        timer.schedule(new RunnerTimerTask(), delay * 1000, 1000);
    }

    private class RunnerTimerTask extends TimerTask {
        @Override
        public void run() {
            //TODO think of this mix of 'view' and 'modal'
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TimerProperties taskProps = task.getProps();
                    taskProps.decrease();
                    System.out.println("Execute " + task.getObjective().getDescription() +
                            ". " + taskProps.getStatus());

                    TextView remainingView = (TextView) activity.findViewById(R.id.remainingView);
                    remainingView.setText(taskProps.getStatus());

                    if (taskProps.isCompleted()) {
                        timer.cancel();
                        System.out.println("Executing....");
                        task.getObjective().toDo();
                        String finishString = "Executed....\nThe objective status is " + (task.getObjective().isDone() ? "DONE" : "FAILED");
                        remainingView.setText(finishString);
                        System.out.println(finishString);
                    }
                }
            });

        }
    }

    public void stop() {
        timer.cancel();
    }
}
