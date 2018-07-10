package com.example.dell.resumebuilderdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ResumeViewActivity extends AppCompatActivity {
    TextView resumeViewPer,resumeViewObj,resumeViewEdu,resumeViewWork,resumeViewOther;
    String name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_view);

        resumeViewPer=(TextView)findViewById(R.id.resumeViewPer);
        resumeViewObj=(TextView)findViewById(R.id.resumeViewObj);
        resumeViewEdu=(TextView)findViewById(R.id.resumeViewEdu);
        resumeViewWork=(TextView)findViewById(R.id.resumeViewWork);
        resumeViewOther=(TextView)findViewById(R.id.resumeViewOther);

        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");

        ResumeDatabaseHelper resumeDatabaseHelper=new ResumeDatabaseHelper(ResumeViewActivity.this);
        SQLiteDatabase db=resumeDatabaseHelper.getReadableDatabase();
        Cursor cursor=resumeDatabaseHelper.viewData(name,email,db);
        cursor.moveToFirst();
        if(cursor.getCount()>0) {
            do{
                resumeViewPer.setText("\nName:" + cursor.getString(1)+"\nEmail ID:"+cursor.getString(2)+"\nPhone:"+cursor.getString(3)+
                "\nAddress:"+cursor.getString(4)+"\n\nMartial Status:"+cursor.getString(5)+"\nGender:"+cursor.getString(6)+"\n\nCity:"+cursor.getString(7)+
                "\nState:"+cursor.getString(8)+"\nCountry:"+cursor.getString(9)+"\n");
                resumeViewObj.setText("\n"+cursor.getString(10)+"\n\n");
                resumeViewEdu.setText("\n"+cursor.getString(11)+"\n");
                resumeViewWork.setText("\n"+cursor.getString(12)+"\n");
                resumeViewOther.setText("\nSkills:\n"+cursor.getString(13)+"\n\nAchievements:\n"+cursor.getString(14)+"\n\nHobbies:\n"+cursor.getString(15)+"\n\nLanguages:\n"+cursor.getString(16));
            }while(cursor.moveToNext());
        }
    }
}
