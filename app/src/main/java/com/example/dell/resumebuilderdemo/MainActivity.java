package com.example.dell.resumebuilderdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button oldResume,newResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        oldResume=(Button)findViewById(R.id.oldResume);
        newResume=(Button)findViewById(R.id.newResume);

        oldResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ListActivity.class);
                startActivity(i);
            }
        });

        newResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=MainActivity.this.getSharedPreferences("APP_PREF_FILE",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent i=new Intent(MainActivity.this,PersonalActivity.class);
                startActivity(i);
            }
        });
    }
}
