package yfkj.weatherstation.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import yfkj.weatherstation.activity.Main;
import yfkj.weatherstation.activity.MainActivity;
import yfkj.weatherstation.utils.LogUtils;

/**
 * @author 49927
 */
public class BootBroadcastReceiver extends BroadcastReceiver {
    public BootBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String str = "android.intent.action.BOOT_COMPLETED";
        if (intent.getAction().equals(str)) {
            Intent bootStartIntent = new Intent(context, MainActivity.class);
            bootStartIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(bootStartIntent);
        }
    }
}
