package com.app.android_app.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author Aliaksandr_Pleski
 *         <p/>
 *         Util class for using in activities classes
 */
public class ActivityUtils {

    private static Intent getIntent(Context context, Class clazz) {
        return new Intent(context, clazz);
    }

    /**
     * This method starts another activity from given context
     *
     * @param context context to start activity from
     * @param clazz   class of the activity to call
     */
    public static void startActivity(Context context, Class clazz) {
        startActivity(context, clazz, null);
    }

    /**
     * This method starts another activity from given context with extras
     *
     * @param context context to start activity from
     * @param clazz   class of the activity to call
     * @param extras  extras to set
     */
    public static void startActivity(Context context, Class clazz, Bundle extras) {
        Intent intent = getIntent(context, clazz);
        if (extras != null) {
            intent.putExtras(extras);
        }
        context.startActivity(intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}
