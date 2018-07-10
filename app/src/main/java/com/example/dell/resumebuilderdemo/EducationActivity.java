package com.example.dell.resumebuilderdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EducationActivity extends AppCompatActivity {
    EditText newDegree,newInstitute,newBoard,newPercent,newYear;
    Button newQualBtn;
    String degree,institute,board,percent,year;
    String educations;
    String works;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));

        newDegree=(EditText)findViewById(R.id.newDegree);
        newInstitute=(EditText)findViewById(R.id.newInstitute);
        newBoard=(EditText)findViewById(R.id.newBoard);
        newPercent=(EditText)findViewById(R.id.newPercent);
        newYear=(EditText)findViewById(R.id.newYear);

        newQualBtn=(Button)findViewById(R.id.newQualBtn);

        newQualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                degree=newDegree.getText().toString();
                institute=newInstitute.getText().toString();
                board=newBoard.getText().toString();
                percent=newPercent.getText().toString();
                year=newYear.getText().toString();

                works=getIntent().getStringExtra("works");
                type=getIntent().getIntExtra("type",1);
                educations=(getIntent().getStringExtra("educations")+"\nQualification:"+degree+"\nInstitute:"+institute+"\nBoard/University:"+board+"\nPercentage/CGPA:"+percent+"\nPassing Year:"+year+"\n\n");
                if(type==1) {
                    Intent i = new Intent(EducationActivity.this, PersonalActivity.class);
                    i.putExtra("works", works);
                    i.putExtra("educations", educations);
                    startActivity(i);
                }
                else if(type==2){
                    Intent i = new Intent(EducationActivity.this, EditActivity.class);
                    i.putExtra("works", works);
                    i.putExtra("educations", educations);
                    startActivity(i);
                }
            }
        });
    }
}
