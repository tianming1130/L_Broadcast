package cn.zknu.l_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BatteryChangeActivity extends AppCompatActivity {

    private TextView tvShowPower;
    private BatteryChangeReceiver batteryChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_power);

        tvShowPower = (TextView) findViewById(R.id.tv_show_power);

        batteryChangeReceiver= new BatteryChangeReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        //注册showPowerReceiver
        registerReceiver(batteryChangeReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryChangeReceiver);
    }

    class BatteryChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //当前电量
            int level = intent.getIntExtra("level", 0);
            //总电量
            int scale = intent.getIntExtra("scale", 100);
            int current = level * 100 / scale;
            tvShowPower.setText("当前电量：" + current + "%");
        }
    }
}
