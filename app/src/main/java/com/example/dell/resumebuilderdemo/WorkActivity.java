package com.example.dell.resumebuilderdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

public class WorkActivity extends AppCompatActivity {
    EditText newJobTitle,newJobDescription,newCompanyName,newFromDate,newToDate;
    TextView textToDate;
    CheckBox checkJobFinish;
    Button newWorkButton;
    String title,description,company,todate,fromdate;
    String works;
    String educations;
    int type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        newJobTitle=(EditText)findViewById(R.id.newJobTitle);
        newJobDescription=(EditText)findViewById(R.id.newJobDescription);
        newCompanyName=(EditText)findViewById(R.id.newCompanyName);
        newFromDate=(EditText)findViewById(R.id.newFromDate);
        newToDate=(EditText)findViewById(R.id.newToDate);
        textToDate=(TextView)findViewById(R.id.textToDate);

        checkJobFinish=(CheckBox)findViewById(R.id.checkJobFinish);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        newWorkButton=(Button)findViewById(R.id.newWorkBtn);

        checkJobFinish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    newToDate.setText("Present");
                }
                else
                {
                    newToDate.setText("");
                }
            }
        });

        newWorkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title=newJobTitle.getText().toString();
                description=newJobDescription.getText().toString();
                company=newCompanyName.getText().toString();
                fromdate=newFromDate.getText().toString();
                todate=newToDate.getText().toString();
                educations=getIntent().getStringExtra("educations");
                type=getIntent().getIntExtra("type",1);
                works=(getIntent().getStringExtra("works")+"\nJob Title:"+title+"\nJob Description:"+description+"\nCompany Name:"+company+"\nFrom:"+fromdate+"\nTo:"+todate+"\n\n");
                if(type==1) {
                    Intent i = new Intent(WorkActivity.this, PersonalActivity.class);
                    i.putExtra("works", works);
                    i.putExtra("educations", educations);
                    startActivity(i);
                }
                else if(type==2){
                    Intent i = new Intent(WorkActivity.this, EditActivity.class);
                    i.putExtra("works", works);
                    i.putExtra("educations", educations);
                    startActivity(i);
                }
            }
        });
    }
}
