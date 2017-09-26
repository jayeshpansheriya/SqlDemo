package com.example.jayeshp.sqldemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView lv;
ArrayList<HashMap<String,String>>hashMapArrayList=new ArrayList<>();
    List<Contact>list;
    public static String DB_id="id";
    public static String DB_user="name";
    public static String DB_phone="phone";
    public static String DB_email="email";
    public static String DB_address="address";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem m1=menu.add(0,1,0,"Create");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle()==("Create"))
        {
            Intent intent=new Intent(MainActivity.this,ContactAct.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ShowData();
        registerForContextMenu(lv);
    }
    public void ShowData(){
        hashMapArrayList.clear();
        ListAdapter adapter;
        DBhelper helper =new DBhelper(getApplicationContext());
        list=helper.ReadAlldata();
        for (Contact c:list){
            HashMap<String,String>hashMap=new HashMap<>();
            hashMap.put(DB_user,c.getName());
            hashMap.put(DB_phone,c.getPhone());
            hashMap.put(DB_email,c.getEmail());
            hashMap.put(DB_address,c.getAddress());
            hashMapArrayList.add(hashMap);
        }
        adapter=new SimpleAdapter(MainActivity.this,hashMapArrayList,R.layout.list_item,new String[]{DB_user,DB_phone,DB_email,DB_address},new int[]{R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4});
        lv.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.list){
            menu.setHeaderTitle("Set Action");
            MenuItem menuItem=menu.add(0,1,0,"Update Contact");
            MenuItem menuItem1=menu.add(0,2,0,"Delete Contact");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int itemindex=info.position;
        final Contact contact=list.get(itemindex);
         switch (item.getItemId()){
             case 1:
                 Intent intent=new Intent(MainActivity.this,UpdateAct.class);
                 intent.putExtra(DB_id,contact.getId());
                 intent.putExtra(DB_user,contact.getName());
                 intent.putExtra(DB_phone,contact.getPhone());
                 intent.putExtra(DB_email,contact.getEmail());
                 intent.putExtra(DB_address,contact.getAddress());
                 startActivity(intent);
                 break;

             case 2:
                 AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                 dialog.setMessage("You are sure to delete this contact");
                 dialog.setTitle("Conformation");
                 dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                       DBhelper bhelper=new DBhelper(MainActivity.this);
                         bhelper.delete(contact);
                         ShowData();
                         Toast.makeText(MainActivity.this, "Contact Deleted", Toast.LENGTH_SHORT).show();

                     }
                 });
                 dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.cancel();
                     }
                 });

                 dialog.show();
         }
        return super.onContextItemSelected(item);

    }
}
