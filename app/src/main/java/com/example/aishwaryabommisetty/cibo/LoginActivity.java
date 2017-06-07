package com.example.aishwaryabommisetty.cibo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText ET_UNAME,ET_PASS;
    String user_pass,user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ET_UNAME=(EditText)findViewById(R.id.loginusername);
        ET_PASS=(EditText)findViewById(R.id.loginpassword);
    }
    public void userLog(View view)
    {

        user_name=ET_UNAME.getText().toString();
        user_pass=ET_PASS.getText().toString();
        String method="Login";
        BackgroundWorker bg=new BackgroundWorker(this);
        bg.execute(method,user_name,user_pass);
        finish();
    }
}
