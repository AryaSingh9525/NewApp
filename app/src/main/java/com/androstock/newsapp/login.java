package com.androstock.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText e1,e2;
    Button bt;
    String name,reg,dept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1= (EditText) findViewById(R.id.txtName);
        e2= (EditText) findViewById(R.id.txtPwd);
        bt= (Button) findViewById(R.id.btnLogin);
        TextView login = (TextView)findViewById(R.id.btnLogin);
        login.setMovementMethod(LinkMovementMethod.getInstance());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
//                startActivity(intent);
                name=e1.getText().toString();
                reg=e2.getText().toString();


                if (name.isEmpty() || reg.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(),"Fields can not be empty",Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    //Intent For Navigating to Second Activity
                    Intent i = new Intent(login.this,MainActivity.class);

                    //For Passing the Values to Second Activity
                    i.putExtra("name_key", name);
                    i.putExtra("reg_key",reg);
                    //i.putExtra("dept_key", dept);
                    startActivity(i);

                }


            }
        });
    }
}
