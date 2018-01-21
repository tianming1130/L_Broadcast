package cn.zknu.l_broadcast;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStatic, btnDynamic, btnShowPower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStatic = (Button) findViewById(R.id.btn_static);
        btnDynamic = (Button) findViewById(R.id.btn_dynamic);
        btnShowPower = (Button) findViewById(R.id.btn_show_power);

        btnStatic.setOnClickListener(this);
        btnDynamic.setOnClickListener(this);
        btnShowPower.setOnClickListener(this);

        _checkPermission();
    }

    private void _checkPermission() {
        String[] permissions = new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission
                .READ_PHONE_STATE};
        List<String> unPermissions = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager
                    .PERMISSION_GRANTED) {
                unPermissions.add(permissions[i]);
            }
        }
        if (!unPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, unPermissions.toArray(new
                    String[unPermissions.size()]), 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_static:
                _static();
                break;
            case R.id.btn_dynamic:
                dynamic();
                break;
            case R.id.btn_show_power:
                showPower();
                break;
        }
    }

    private void showPower() {
        startActivity(new Intent(this, BatteryChangeActivity.class));
    }

    private void dynamic() {
        startActivity(new Intent(this, DynamicActivity.class));
    }

    private void _static() {
        startActivity(new Intent(this, StaticActivity.class));
    }
}
