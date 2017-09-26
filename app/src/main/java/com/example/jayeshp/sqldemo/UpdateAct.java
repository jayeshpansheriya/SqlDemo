package com.example.jayeshp.sqldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateAct extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4;
    Button btn;
    int id;
    public static String DB_id="id";
    public static String DB_user="name";
    public static String DB_phone="phone";
    public static String DB_email="email";
    public static String DB_address="address";
    DBhelper helper =new DBhelper(UpdateAct.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ed1=(EditText)findViewById(R.id.editText);
        ed2=(EditText)findViewById(R.id.editText2);
        ed3=(EditText)findViewById(R.id.editText3);
        ed4=(EditText)findViewById(R.id.editText4);
        btn=(Button)findViewById(R.id.button);

        Intent intent=getIntent();
        id=intent.getIntExtra(DB_id,0);
        ed1.setText(intent.getStringExtra(DB_user));
        ed2.setText(intent.getStringExtra(DB_phone));
        ed3.setText(intent.getStringExtra(DB_email));
        ed4.setText(intent.getStringExtra(DB_address));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact contact=new Contact();
                String n1=ed1.getText().toString();
                String p1=ed2.getText().toString();
                String e1=ed3.getText().toString();
                String a1=ed4.getText().toString();
                contact.setId(id);
                contact.setName(n1);
                contact.setPhone(p1);
                contact.setEmail(e1);
                contact.setAddress(a1);
                helper.Update(contact);
                Toast.makeText(UpdateAct.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}
