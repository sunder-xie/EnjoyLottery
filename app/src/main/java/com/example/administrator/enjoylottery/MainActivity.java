package com.example.administrator.enjoylottery;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.enjoylottery.activity.BaseActivity;
import com.example.administrator.enjoylottery.activity.HomeActivity;
import com.example.administrator.enjoylottery.listener.PermissonsCallBack;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > 22) {
            ApplyPermissons(new String[]{P_GPS, P_SD, P_CALL,P_CAMERA}, new PermissonsCallBack() {
                @Override
                public void ps_CallBack(String[] permissionNames, int result) {
                    if (result == 1 || result == 0) {
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this,"请给予App对应权限,否则某些功能可能无法使用",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                }
            });
        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void widgetClick(View v) {

    }
}
