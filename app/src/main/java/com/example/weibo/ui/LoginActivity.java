package com.example.weibo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.weibo.MainActivity;
import com.example.weibo.R;

public class LoginActivity extends AppCompatActivity {

    private Button login_btn;
    private EditText account;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

    }


    public void init() {
        login_btn = (Button) findViewById(R.id.login_btn);
        account = (EditText) findViewById(R.id.account_input);
        password = (EditText) findViewById(R.id.password_input);
    }
}
