package cn.zknu.l_broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StaticActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG ="StaticActivity" ;
    private Button btnSend;
    private static TextView tvReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static);

        btnSend=(Button)findViewById(R.id.btn_register);
        tvReceiver=(TextView)findViewById(R.id.tv_receiver);

        btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent=new Intent();
        intent.setAction("cn.zknu.l_broadcast.staticactivity");
        intent.putExtra("key","静态注册及数据传递！");
        sendBroadcast(intent);
    }

    public static class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String strReceive=intent.getStringExtra("key");
            tvReceiver.setText(strReceive);
        }
    }
}
