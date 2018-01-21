package cn.zknu.l_broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DynamicActivity extends AppCompatActivity implements View.OnClickListener,Interaction{

    private Button btnRegister,btnSend;
    private TextView tvShowMsg;
    private MyReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        btnRegister =(Button)findViewById(R.id.btn_register);
        btnSend=(Button)findViewById(R.id.btn_send);
        tvShowMsg=(TextView)findViewById(R.id.tv_show_msg);

        btnRegister.setOnClickListener(this);
        btnSend.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                register();
                break;
            case R.id.btn_send:
                send();
                break;
        }
    }

    private void send() {
        Intent intent=new Intent();
        intent.setAction("cn.zknu.l_broadcast.dynamicactivity");
        intent.putExtra("key","动态注册及数据传递！");
        sendBroadcast(intent);
    }

    private void register() {
        receiver=new MyReceiver();
        receiver.setInteractionListener(this);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("cn.zknu.l_broadcast.dynamicactivity");
        registerReceiver(receiver,intentFilter);

    }

    @Override
    public void setText(String strText) {
        tvShowMsg.setText(strText);
    }

}
