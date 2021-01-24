package com.example.opscsem2task2.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.opscsem2task2.R;
import com.example.opscsem2task2.ui.ui.main.SettingsFragment;

public class SettingFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SettingsFragment.newInstance())
                    .commitNow();
        }
    }
}