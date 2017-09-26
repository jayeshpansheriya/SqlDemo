package com.example.jayeshp.sqldemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactAct extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
    Button btn;
DBhelper helper=new DBhelper(ContactAct.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        ed1=(EditText)findViewById(R.id.name);
        ed2=(EditText)findViewById(R.id.number);
        ed3=(EditText)findViewById(R.id.email);
        ed4=(EditText)findViewById(R.id.address);
        btn=(Button)findViewById(R.id.save);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u=ed1.getText().toString();
                String n=ed2.getText().toString();
                String e=ed3.getText().toString();
                String a=ed4.getText().toString();
                helper.AddData(new Contact(u,n,e,a));
                Toast.makeText(ContactAct.this, "successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}

