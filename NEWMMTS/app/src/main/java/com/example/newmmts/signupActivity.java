package com.example.newmmts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import android.database.sqlite.SQLiteDatabase;
public class signupActivity extends AppCompatActivity {
    EditText name , number , email,pass,conform_pass;
    String NameHolder, EmailHolder, PasswordHolder,ConformPassword,PhoneNumber;
    Toolbar login;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // assigning ID of the toolbar to a variable
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // using toolbar as ActionBar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        name=findViewById(R.id.et_username);
        number=findViewById(R.id.et_phone);
        email=findViewById(R.id.et_email);
        pass=findViewById(R.id.et_password);
        conform_pass=findViewById(R.id.et_confirm_password);
        Button signUpAcc = findViewById(R.id.button_signin);
        sqLiteHelper = new SQLiteHelper(this);
        signUpAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating SQLite database if dose n't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if dose n't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                // Method to check Email is already exists or not.
                CheckingEmailAlreadyExistsOrNot();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();

            }
        });



        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DO SOMETHING HERE
                Intent i = new Intent(signupActivity.this,loginActivity.class);
                startActivity(i);
                finish();
            }
        });

        toolbar.getChildAt(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //do whatever you want here
                Intent i = new Intent(signupActivity.this,loginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    // SQLite database build method.
    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    // SQLite table build method.
    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " INTEGER PRIMARY KEY, " + SQLiteHelper.Table_Column_1_Name + " VARCHAR, " + SQLiteHelper.Table_Column_2_Email + " VARCHAR, " + SQLiteHelper.Table_Column_3_Password + " VARCHAR, " + SQLiteHelper.Table_Column_4_mobile + " VARCHAR);");
       // sqLiteDatabaseObj.execSQL("create Table IF NOT EXISTS UserDetails(userID TEXT primary key,name TEXT,password PASSWORD,number NUMBER)");

    }

    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase(){

        // If editText is not empty then this block will executed.
        if(EditTextEmptyHolder == true)
        {

            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,email,password,number) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PasswordHolder+"', '"+PhoneNumber+"');";

            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            // Closing SQLite database object.
            sqLiteDatabaseObj.close();

            // Printing toast message after done inserting.
            Toast.makeText(signupActivity.this,"User Registered Successfully", Toast.LENGTH_LONG).show();

        }
        // This block will execute if any of the registration EditText is empty.
        else {
            EditTextEmptyHolder=false;
            // Printing toast message if any of EditText is empty.
            Toast.makeText(signupActivity.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){
        if(EditTextEmptyHolder == true) {
            name.getText().clear();

            number.getText().clear();

            email.getText().clear();
            pass.getText().clear();

            conform_pass.getText().clear();
        }
    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = name.getText().toString() ;
        EmailHolder = email.getText().toString();
        PasswordHolder = pass.getText().toString();
        ConformPassword=conform_pass.getText().toString();
        PhoneNumber=number.getText().toString();
      if(isValidEmail(EmailHolder)) {
          if (TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder) || TextUtils.isEmpty(ConformPassword) || TextUtils.isEmpty(PhoneNumber)) {

              EditTextEmptyHolder = false;

          } else {
              if (PasswordHolder.equals(ConformPassword)) {
                  EditTextEmptyHolder = true;
              } else {
                  EditTextEmptyHolder=false;
                  Toast.makeText(this, "Password Does Not Match!!", Toast.LENGTH_SHORT).show();
              }
          }
      }
      else {
          EditTextEmptyHolder = false;
          Toast.makeText(this, "Invalid Email ID!!", Toast.LENGTH_SHORT).show();
      }
    }

    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){

        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new String[]{EmailHolder}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";

                // Closing cursor.
                cursor.close();
            }
        }

        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();

    }


    // Checking result
    public void CheckFinalResult(){

        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase("Email Found"))
        {

            // If email is exists then toast msg will display.
            Toast.makeText(signupActivity.this,"Email Already Exists",Toast.LENGTH_LONG).show();
            EditTextEmptyHolder = false;
        }
        else {

            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found" ;

    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}