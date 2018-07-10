package com.example.dell.resumebuilderdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalActivity extends AppCompatActivity {
    EditText newName,newEmail,newPhone,newAddress,newCity,newState,newCountry,newObjective,newSkills,newAchievements,newHobbies,newLanguage;
    RadioGroup rgMaritial,rgGender;
    RadioButton rbMaritial,rbGender;
    TextView textEducation,textWork;
    Button saveResume;
    CheckBox checkDec;
    String marStatus,gender;
    String name,email,phone,address,city,state,country,objective,skills,achievements,hobbies,languages;
    String education=" ",work=" ";
    Button addWork,addQualifications;
    int gen_select,mar_select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        ActionBar actionBar=getSupportActionBar();
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        newName=(EditText)findViewById(R.id.newName);
        newEmail=(EditText)findViewById(R.id.newEmail);
        newPhone=(EditText)findViewById(R.id.newPhone);
        newAddress=(EditText)findViewById(R.id.newAddress);
        newCity=(EditText)findViewById(R.id.newCity);
        newState=(EditText)findViewById(R.id.newState);
        newCountry=(EditText)findViewById(R.id.newCountry);
        newObjective=(EditText)findViewById(R.id.newObjective);
        newSkills=(EditText)findViewById(R.id.newSkills);
        newAchievements=(EditText)findViewById(R.id.newAchievements);
        newHobbies=(EditText)findViewById(R.id.newHobbies);
        newLanguage=(EditText)findViewById(R.id.newLanguage);
        addWork=(Button)findViewById(R.id.addWork);
        addQualifications=(Button)findViewById(R.id.addQualifications);
        textEducation=(TextView)findViewById(R.id.textEducation);
        textWork=(TextView)findViewById(R.id.textWork);

        rgMaritial=(RadioGroup)findViewById(R.id.rgMaritial);
        rgGender=(RadioGroup)findViewById(R.id.rgGender);
        saveResume=(Button)findViewById(R.id.saveResume);
        checkDec=(CheckBox)findViewById(R.id.checkDec);

        SharedPreferences sharedPreferences=PersonalActivity.this.getSharedPreferences("APP_PREF_FILE",MODE_PRIVATE);
        newName.setText(sharedPreferences.getString("name",""));
        newEmail.setText(sharedPreferences.getString("email",""));
        newPhone.setText(sharedPreferences.getString("phone",""));
        newAddress.setText(sharedPreferences.getString("address",""));
        newCity.setText(sharedPreferences.getString("city",""));
        newState.setText(sharedPreferences.getString("state",""));
        newCountry.setText(sharedPreferences.getString("country",""));
        newObjective.setText(sharedPreferences.getString("objective",""));
        //textEducation.setText(sharedPreferences.getString("education",""));
        //textWork.setText(sharedPreferences.getString("work",""));
        newSkills.setText(sharedPreferences.getString("skills",""));
        newAchievements.setText(sharedPreferences.getString("achievements",""));
        newHobbies.setText(sharedPreferences.getString("hobbies",""));
        newLanguage.setText(sharedPreferences.getString("language",""));

        addQualifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=PersonalActivity.this.getSharedPreferences("APP_PREF_FILE",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name",newName.getText().toString());
                editor.putString("email",newEmail.getText().toString());
                editor.putString("phone",newPhone.getText().toString());
                editor.putString("address",newAddress.getText().toString());
                editor.putString("city",newCity.getText().toString());
                editor.putString("state",newState.getText().toString());
                editor.putString("country",newCountry.getText().toString());
                editor.putString("objective",newObjective.getText().toString());
                editor.putString("skills",newSkills.getText().toString());
                editor.putString("achievements",newAchievements.getText().toString());
                editor.putString("hobbies",newHobbies.getText().toString());
                editor.putString("language",newLanguage.getText().toString());
                editor.putString("work",textWork.getText().toString());
                editor.putInt("maritial",mar_select);
                editor.putInt("gender",gen_select);
                editor.commit();
                education=textEducation.getText().toString();
                work=textWork.getText().toString();
                Intent i=new Intent(PersonalActivity.this,EducationActivity.class);
                i.putExtra("educations",education);
                i.putExtra("type",1);
                i.putExtra("works",work);
                startActivity(i);
            }
        });

        textEducation.setText(getIntent().getStringExtra("educations"));
        textWork.setText(getIntent().getStringExtra("works"));

        addWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences=PersonalActivity.this.getSharedPreferences("APP_PREF_FILE",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("name",newName.getText().toString());
                editor.putString("email",newEmail.getText().toString());
                editor.putString("phone",newPhone.getText().toString());
                editor.putString("address",newAddress.getText().toString());
                editor.putString("city",newCity.getText().toString());
                editor.putString("state",newState.getText().toString());
                editor.putString("country",newCountry.getText().toString());
                editor.putString("objective",newObjective.getText().toString());
                editor.putString("education",textEducation.getText().toString());
                editor.putString("skills",newSkills.getText().toString());
                editor.putString("achievements",newAchievements.getText().toString());
                editor.putString("hobbies",newHobbies.getText().toString());
                editor.putString("language",newLanguage.getText().toString());
                editor.commit();

                work=textWork.getText().toString();
                education=textEducation.getText().toString();
                Intent i=new Intent(PersonalActivity.this,WorkActivity.class);
                i.putExtra("works",work);
                i.putExtra("type",1);
                i.putExtra("educations",education);
                startActivity(i);

            }
        });
        textWork.setText(getIntent().getStringExtra("works"));
        textEducation.setText(getIntent().getStringExtra("educations"));

        saveResume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = newEmail.getText().toString();
                ResumeDatabaseHelper user=new ResumeDatabaseHelper(PersonalActivity.this);
                SQLiteDatabase database=user.getReadableDatabase();
                Cursor cursor=user.searchEmail(email,database);

                cursor.moveToFirst();
                if(cursor.getCount()==0) {
                    if (checkDec.isChecked() && rgMaritial.getCheckedRadioButtonId() != -1 && rgGender.getCheckedRadioButtonId() != -1) {
                        mar_select = rgMaritial.getCheckedRadioButtonId();
                        gen_select = rgGender.getCheckedRadioButtonId();

                        rbMaritial = (RadioButton) findViewById(mar_select);
                        rbGender = (RadioButton) findViewById(gen_select);

                        marStatus = rbMaritial.getText().toString();
                        gender = rbGender.getText().toString();

                        name = newName.getText().toString();
                        email = newEmail.getText().toString();
                        phone = newPhone.getText().toString();
                        address = newAddress.getText().toString();
                        city = newCity.getText().toString();
                        state = newState.getText().toString();
                        country = newCountry.getText().toString();
                        objective = newObjective.getText().toString();
                        skills = newSkills.getText().toString();
                        achievements = newAchievements.getText().toString();
                        hobbies = newHobbies.getText().toString();
                        languages = newLanguage.getText().toString();

                        work = textWork.getText().toString();
                        education = textEducation.getText().toString();

                        ResumeDatabaseHelper resumeDatabaseHelper = new ResumeDatabaseHelper(PersonalActivity.this);
                        SQLiteDatabase db = resumeDatabaseHelper.getWritableDatabase();

                        Cursor cursor2=resumeDatabaseHelper.searchEmail(email,db);

                        if(cursor2.getCount()==0) {
                            Cursor cursor1 = resumeDatabaseHelper.insertData(name, email, phone, address, marStatus, gender, city, state, country, objective, education, work, skills, achievements, hobbies, languages, db);
                            if (cursor1.getCount() == 0) {
                                Toast.makeText(PersonalActivity.this, "Resume built Successfully", Toast.LENGTH_LONG).show();
                                Intent i=new Intent(PersonalActivity.this,MainActivity.class);
                                startActivity(i);
                            }
                        }
                        else
                            Toast.makeText(PersonalActivity.this, "Email id Already Exists", Toast.LENGTH_LONG).show();

                    } else if (!checkDec.isChecked()) {
                        Toast.makeText(PersonalActivity.this, "Confirm Declaration First", Toast.LENGTH_LONG).show();
                    } else if (rgMaritial.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(PersonalActivity.this, "Enter Your Maritial Status", Toast.LENGTH_LONG).show();
                    } else if (rgGender.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(PersonalActivity.this, "Enter Your Gender", Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(PersonalActivity.this, "Email Id Already Exists", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
