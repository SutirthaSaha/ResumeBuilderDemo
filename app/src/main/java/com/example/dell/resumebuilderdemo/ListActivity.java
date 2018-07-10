package com.example.dell.resumebuilderdemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> arrayListName=new ArrayList<String>();
    ArrayList<String> arrayListEmail=new ArrayList<String>();
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView=(ListView)findViewById(R.id.listView);
        ResumeDatabaseHelper resumeDatabaseHelper=new ResumeDatabaseHelper(ListActivity.this);
        SQLiteDatabase db=resumeDatabaseHelper.getReadableDatabase();
        Cursor cursor=resumeDatabaseHelper.viewNames(db);
        cursor.moveToFirst();

        if(cursor.getCount()==0){
            Toast.makeText(ListActivity.this,"No Profiles Avialable",Toast.LENGTH_LONG).show();
        }
        else{
            do {
                arrayListName.add(cursor.getString(1));
                arrayListEmail.add(cursor.getString(2));
            }while(cursor.moveToNext());
        }

        registerForContextMenu(listView);
        adapter=new CustomAdapter();
        listView.setAdapter(adapter);

        SharedPreferences sharedPreferences=ListActivity.this.getSharedPreferences("APP_PREF_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater=new MenuInflater(ListActivity.this);
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final MenuItem item2=item;
        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int selected_id=item.getItemId();
        if(selected_id==R.id.openResume){

            String name=arrayListName.get(info.position);
            String email=arrayListEmail.get(info.position);
            Intent i=new Intent(ListActivity.this,ResumeViewActivity.class);
            i.putExtra("name",name);
            i.putExtra("email",email);
            startActivity(i);
            return true;
        }
        else if(selected_id==R.id.delProfile){
            AlertDialog.Builder alertBuilder=new AlertDialog.Builder(ListActivity.this);
            alertBuilder.setMessage("Are you sure you want to delete Profile?")
                    .setCancelable(true)
                    .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item2.getMenuInfo();
                            String name=arrayListName.get(info.position);
                            //String email=arrayListEmail.get(info.position);
                            ResumeDatabaseHelper resumeDatabaseHelper=new ResumeDatabaseHelper(ListActivity.this);
                            SQLiteDatabase db=resumeDatabaseHelper.getWritableDatabase();
                            resumeDatabaseHelper.deleteData(name,db);
                            arrayListName.remove(info.position);
                            arrayListEmail.remove(info.position);
                            adapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel(); //to close the dialog box
                        }
                    });
            AlertDialog alert=alertBuilder.create();
            alert.show();
            return true;
        }
        else if (selected_id==R.id.editResume){
            String name=arrayListName.get(info.position);
            String email=arrayListEmail.get(info.position);
            Intent i=new Intent(ListActivity.this,EditActivity.class);
            i.putExtra("name",name);
            i.putExtra("email",email);
            startActivity(i);
            return true;
        }
        return false;
    }
    public class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {return arrayListName.size();

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.list_layout,null);
            TextView oldNameView=(TextView)convertView.findViewById(R.id.oldNameView);
            TextView oldEmailView=(TextView)convertView.findViewById(R.id.oldEmailView);

            oldNameView.setText(arrayListName.get(position));
            oldEmailView.setText(arrayListEmail.get(position));
            return convertView;
        }
    }
}
