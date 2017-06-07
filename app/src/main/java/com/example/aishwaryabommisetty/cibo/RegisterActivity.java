package com.example.aishwaryabommisetty.cibo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {
    EditText ET_NAME,ET_UNAME,ET_PASS;
    String name,user_name,user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ET_NAME = (EditText)findViewById(R.id.name);
        ET_UNAME = (EditText)findViewById(R.id.new_user_name);
        ET_PASS =  (EditText)findViewById(R.id.new_user_pass);
    }
    public void userReg(View view)
    {
        name=ET_NAME.getText().toString();
        user_name=ET_UNAME.getText().toString();
        user_pass=ET_PASS.getText().toString();
        String method="Register";
        BackgroundTask bg=new BackgroundTask(this);
        bg.execute(method,name,user_name,user_pass);
        finish();
    }
}
