package cn.zknu.l_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by sun on 2018/1/17.
 */

public class MyReceiver extends BroadcastReceiver {
    private Interaction interaction;
    @Override
    public void onReceive(Context context, Intent intent) {
        String strText=intent.getStringExtra("key");
        interaction.setText(strText);
    }
    public void setInteractionListener(Interaction interaction){
        this.interaction=interaction;
    }
}
