package com.example.registerlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {
    TextView alreadyhaveanaccount;
    DatePickerDialog picker;

    EditText firstname, lastname, email, currentpassword, eText;
    Button button, btnGet, button1;

    TextView tvw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // try block to hide Action bar
        try {
            this.getSupportActionBar().hide();
        }
        // catch block to handle NullPointerException
        catch (NullPointerException e) {
        }

        //added id to render login page
        alreadyhaveanaccount = findViewById(R.id.alreadyhaveanaccount);

        //Redirect to Login Page from Register Page, if user have a credentials to login
        alreadyhaveanaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });



        //registration ids
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        email = findViewById(R.id.email);
        currentpassword = findViewById(R.id.currentpassword);
        //picker = (DatePicker) findViewById(R.id.dob);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String first_name = firstname.getText().toString();
                String last_name = lastname.getText().toString();
                String email_id = email.getText().toString();
                String current_password = currentpassword.getText().toString();


                //Function for validation and passing all parameters
                boolean check = validation(first_name, last_name, email_id, current_password);

                if (check == true) {
                    Toast.makeText(getApplicationContext(), "Registration Success", Toast.LENGTH_SHORT).show();

                    //finish() the login activity when login is successful.
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry, Please check all the information are correct", Toast.LENGTH_SHORT).show();
                }
            }
        });


        tvw =  findViewById(R.id.age);
        eText =findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(
                        RegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
//        btnGet = (Button) findViewById(R.id.button1);
//        btnGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tvw.setText("Selected Date: " + eText.getText());
//            }
//        });

    }



    private Boolean validation(String first_name,
                               String last_name,
                               String email_id,
                               String current_password){
        if (first_name.length()==0){
            firstname.requestFocus();
            firstname.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if (!first_name.matches("[a-zA-Z]+")){
            firstname.requestFocus();
            firstname.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }
        else if ((first_name.length() < 3) || (first_name.length() > 30)){
            firstname.requestFocus();
            firstname.setError("ENTER ONLY ALPHABETICAL 3 or 30 CHARACTER");
            return false;
        }
        if (last_name.length()==0){
            lastname.requestFocus();
            lastname.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if (!last_name.matches("[a-zA-Z]+")){
            lastname.requestFocus();
            lastname.setError("ENTER ONLY ALPHABETICAL CHARACTER");
            return false;
        }
        else if (email_id.length()==0){
            email.requestFocus();
            email.setError("FIELD CANNOT BE EMPTY");
            return false;
        }
        else if (!email_id.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
            email.requestFocus();
            email.setError("ENTER VALID EMAIL");
            return false;
        }
        else if (current_password.length()<=5){
            currentpassword.requestFocus();
            currentpassword.setError("MINIMUM 6 CHARACTERS REQUIRED");
            return false;
        }
        else {
            return true;
        }
    }
}