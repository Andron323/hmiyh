package com.freedev.hmiyh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RegSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_second);

        TextView btnUPRegSec = findViewById(R.id.btnUPRegSec);
        btnUPRegSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegSecondActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}