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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    int count=0;
    EditText updateName,updatePhone,updateAddress,updateCity,updateState,updateCountry,updateObjective,updateSkills,updateAchievements,updateHobbies,updateLanguage;
    RadioGroup rgMaritial,rgGender;
    RadioButton rbMaritial,rbGender;
    TextView textUpdateEducation,textUpdateWork,updateEmail;
    Button saveResume;
    CheckBox checkDec;
    String marStatus,gender;
    String name,email,phone,address,city,state,country,objective,skills,achievements,hobbies,languages;
    String name1,email1;
    String education=" ",work=" ";
    Button addWork,addQualifications;
    int gen_select,mar_select;
    String oldName,oldPhone,oldAddress,oldCity,oldState,oldCountry,oldObjective,oldSkills,oldAchievements,oldHobbies,oldLanguages;
    String educations,works;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_edit);

            ActionBar actionBar=getSupportActionBar();
            getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

            updateName=(EditText)findViewById(R.id.updateName);
            updateEmail=(TextView)findViewById(R.id.updateEmail);
            updatePhone=(EditText)findViewById(R.id.updatePhone);
            updateAddress=(EditText)findViewById(R.id.updateAddress);
            updateCity=(EditText)findViewById(R.id.updateCity);
            updateState=(EditText)findViewById(R.id.updateState);
            updateCountry=(EditText)findViewById(R.id.updateCountry);
            updateObjective=(EditText)findViewById(R.id.updateObjective);
            updateSkills=(EditText)findViewById(R.id.updateSkills);
            updateAchievements=(EditText)findViewById(R.id.updateAchievements);
            updateHobbies=(EditText)findViewById(R.id.updateHobbies);
            updateLanguage=(EditText)findViewById(R.id.updateLanguage);
            addWork=(Button)findViewById(R.id.addWorkUpdate);
            addQualifications=(Button)findViewById(R.id.addQualificationsUpdate);
            textUpdateEducation=(TextView)findViewById(R.id.textUpdateEducation);
            textUpdateWork=(TextView)findViewById(R.id.textUpdateWork);

            rgMaritial=(RadioGroup)findViewById(R.id.rgUpdateMaritial);
            rgGender=(RadioGroup)findViewById(R.id.rgUpdateGender);
            saveResume=(Button)findViewById(R.id.saveResumeUpdate);
            checkDec=(CheckBox)findViewById(R.id.checkDecUpdate);

        name1=getIntent().getStringExtra("name");
        email1=getIntent().getStringExtra("email");

            ResumeDatabaseHelper resumeDatabaseHelper = new ResumeDatabaseHelper(EditActivity.this);
            SQLiteDatabase db = resumeDatabaseHelper.getReadableDatabase();
            Cursor cursor = resumeDatabaseHelper.viewData(name1, email1, db);
            cursor.moveToFirst();
            if (cursor.getCount() > 0) {

                oldName = cursor.getString(1);
                oldPhone = cursor.getString(3);
                oldAddress = cursor.getString(4);
                oldCity = cursor.getString(7);
                oldState = cursor.getString(8);
                oldCountry = cursor.getString(9);
                oldObjective = cursor.getString(10);
                educations=cursor.getString(11);
                works=cursor.getString(12);
                oldSkills = cursor.getString(13);
                oldAchievements = cursor.getString(14);
                oldHobbies = cursor.getString(15);
                oldLanguages = cursor.getString(16);
            }

                SharedPreferences sharedPreferences = EditActivity.this.getSharedPreferences("APP_PREF_FILE", MODE_PRIVATE);
                    updateName.setText(sharedPreferences.getString("name",oldName ));
                    updateEmail.setText(sharedPreferences.getString("email",email1));
                    updatePhone.setText(sharedPreferences.getString("phone", oldPhone));
                    updateAddress.setText(sharedPreferences.getString("address", oldAddress));
                    updateCity.setText(sharedPreferences.getString("city", oldCity));
                    updateState.setText(sharedPreferences.getString("state", oldState));
                    updateCountry.setText(sharedPreferences.getString("country", oldCountry));
                    updateObjective.setText(sharedPreferences.getString("objective", oldObjective));
                    updateSkills.setText(sharedPreferences.getString("skills", oldSkills));
                    updateAchievements.setText(sharedPreferences.getString("achievements", oldAchievements));
                    updateHobbies.setText(sharedPreferences.getString("hobbies", oldHobbies));
                    updateLanguage.setText(sharedPreferences.getString("language", oldLanguages));



        addQualifications.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences=EditActivity.this.getSharedPreferences("APP_PREF_FILE",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name",updateName.getText().toString());
                    editor.putString("email",updateEmail.getText().toString());
                    editor.putString("phone",updatePhone.getText().toString());
                    editor.putString("address",updateAddress.getText().toString());
                    editor.putString("city",updateCity.getText().toString());
                    editor.putString("state",updateState.getText().toString());
                    editor.putString("country",updateCountry.getText().toString());
                    editor.putString("objective",updateObjective.getText().toString());
                    editor.putString("skills",updateSkills.getText().toString());
                    editor.putString("achievements",updateAchievements.getText().toString());
                    editor.putString("hobbies",updateHobbies.getText().toString());
                    editor.putString("language",updateLanguage.getText().toString());
                    editor.putString("work",textUpdateWork.getText().toString());
                    editor.putInt("maritial",mar_select);
                    editor.putInt("gender",gen_select);
                    editor.commit();
                    count=1;
                    education=textUpdateEducation.getText().toString();
                    work=textUpdateWork.getText().toString();
                    Intent i=new Intent(EditActivity.this,EducationActivity.class);
                    i.putExtra("educations",education);
                    i.putExtra("type",2);
                    i.putExtra("count",0);
                    i.putExtra("works",work);
                    startActivity(i);
                }
            });

        if(getIntent().getStringExtra("educations")!=null && getIntent().getStringExtra("works")!=null) {
            textUpdateEducation.setText(getIntent().getStringExtra("educations"));
            textUpdateWork.setText(getIntent().getStringExtra("works"));
        }
        else{
            textUpdateWork.setText(works);
            textUpdateEducation.setText(educations);
        }
            addWork.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences=EditActivity.this.getSharedPreferences("APP_PREF_FILE",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("name",updateName.getText().toString());
                    editor.putString("email",updateEmail.getText().toString());
                    editor.putString("phone",updatePhone.getText().toString());
                    editor.putString("address",updateAddress.getText().toString());
                    editor.putString("city",updateCity.getText().toString());
                    editor.putString("state",updateState.getText().toString());
                    editor.putString("country",updateCountry.getText().toString());
                    editor.putString("objective",updateObjective.getText().toString());
                    editor.putString("education",textUpdateEducation.getText().toString());
                    editor.putString("skills",updateSkills.getText().toString());
                    editor.putString("achievements",updateAchievements.getText().toString());
                    editor.putString("hobbies",updateHobbies.getText().toString());
                    editor.putString("language",updateLanguage.getText().toString());
                    editor.commit();
                    count=1;
                    work=textUpdateWork.getText().toString();
                    education=textUpdateEducation.getText().toString();
                    Intent i=new Intent(EditActivity.this,WorkActivity.class);
                    i.putExtra("works",work);
                    i.putExtra("type",2);
                    i.putExtra("educations",education);
                    startActivity(i);

                }
            });
        if(getIntent().getStringExtra("educations")!=null && getIntent().getStringExtra("works")!=null) {
            textUpdateWork.setText(getIntent().getStringExtra("works"));
            textUpdateEducation.setText(getIntent().getStringExtra("educations"));
        }
        else{
            textUpdateWork.setText(works);
            textUpdateEducation.setText(educations);
        }


            saveResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    count=1;
                        if (checkDec.isChecked() && rgMaritial.getCheckedRadioButtonId() != -1 && rgGender.getCheckedRadioButtonId() != -1) {
                            mar_select = rgMaritial.getCheckedRadioButtonId();
                            gen_select = rgGender.getCheckedRadioButtonId();

                            rbMaritial = (RadioButton) findViewById(mar_select);
                            rbGender = (RadioButton) findViewById(gen_select);

                            marStatus = rbMaritial.getText().toString();
                            gender = rbGender.getText().toString();

                            name = updateName.getText().toString();
                            email = updateEmail.getText().toString();
                            phone = updatePhone.getText().toString();
                            address = updateAddress.getText().toString();
                            city = updateCity.getText().toString();
                            state = updateState.getText().toString();
                            country = updateCountry.getText().toString();
                            objective = updateObjective.getText().toString();
                            skills = updateSkills.getText().toString();
                            achievements = updateAchievements.getText().toString();
                            hobbies = updateHobbies.getText().toString();
                            languages = updateLanguage.getText().toString();

                            work = textUpdateWork.getText().toString();
                            education = textUpdateEducation.getText().toString();

                            ResumeDatabaseHelper resumeDatabaseHelper = new ResumeDatabaseHelper(EditActivity.this);
                            SQLiteDatabase db = resumeDatabaseHelper.getWritableDatabase();


                            Cursor cursor1 = resumeDatabaseHelper.updateData(name, email, phone, address, marStatus, gender, city, state, country, objective, education, work, skills, achievements, hobbies, languages, db);
                                if (cursor1.getCount() == 0) {
                                    Toast.makeText(EditActivity.this, "Resume Updated Successfully", Toast.LENGTH_LONG).show();
                                    Intent i=new Intent(EditActivity.this,ListActivity.class);
                                    startActivity(i);
                                }

                        } else if (!checkDec.isChecked()) {
                            Toast.makeText(EditActivity.this, "Confirm Declaration First", Toast.LENGTH_LONG).show();
                        } else if (rgMaritial.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(EditActivity.this, "Enter Your Maritial Status", Toast.LENGTH_LONG).show();
                        } else if (rgGender.getCheckedRadioButtonId() == -1) {
                            Toast.makeText(EditActivity.this, "Enter Your Gender", Toast.LENGTH_LONG).show();
                        }

                    }

            });

        }
    }

