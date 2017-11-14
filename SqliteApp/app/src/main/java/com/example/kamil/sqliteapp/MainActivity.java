package com.example.kamil.sqliteapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.provider.FontsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.*;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity  {
    DatabaseHelper databaseHelper;
    EditText editName,editSurname,editMarks, editId;
    Button btnAddData;
    Button btnviewAll;
    Button btnUpdate;
    Button btnDelete;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);

        editName = (EditText) findViewById(R.id.editText);
        editSurname = (EditText) findViewById(R.id.editText2);
        editMarks = (EditText) findViewById(R.id.editText3);
        editId = (EditText) findViewById(R.id.editText4);
        btnAddData = (Button) findViewById(R.id.button);
        btnviewAll = (Button) findViewById(R.id.button2);
        btnUpdate = (Button) findViewById(R.id.button3);
        btnDelete = (Button) findViewById(R.id.button4);
        btnLog = (Button) findViewById(R.id.button5);
        AddData();
        ViewAll();
        UpdateData();
        DeleteData();
        LogData();
    }

    public void LogData(){

        btnLog.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {                                           }
                    String crs = databaseHelper.crsString(Cursor.);

                }

        );


    }



    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deleteRows = databaseHelper.deleteData(editId.getText().toString());
                     if(deleteRows > 0)
                         Toast.makeText(MainActivity.this, "Data Delete", Toast.LENGTH_LONG).show();
                     else
                         Toast.makeText(MainActivity.this, "Data not delete", Toast.LENGTH_LONG).show();
                    }
                }

        );

    }


    public void UpdateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = databaseHelper.updateData(editId.getText().toString(), editName.getText().toString(), editSurname.getText().toString(), editMarks.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(MainActivity.this, "Data Update", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Update", Toast.LENGTH_LONG).show();


                    }
                }

        );

    }


    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       boolean isInserted = databaseHelper.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                       if(isInserted = true)
                           Toast.makeText(MainActivity.this,"Data Insert", Toast.LENGTH_LONG).show();
                       else
                           Toast.makeText(MainActivity.this,"Data not Insert", Toast.LENGTH_LONG).show();
                    }
                }
        );


    }

    public void ViewAll(){

        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = databaseHelper.getAllData();
                        if(res.getCount() == 0){
                            showMassage("Error", "Nothing Found");

                            return;


                        }
                    StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()) {
                        buffer.append("Id : " + res.getString(0) + "\n");
                        buffer.append("Name : " + res.getString(1) + "\n");
                        buffer.append("Surname : " + res.getString(2) + "\n");
                        buffer.append("Narks : " + res.getString(3) + "\n\n");
                        }
                    showMassage("Data", buffer.toString());

                    }
                }

        );


    }

    public void showMassage(String title, String massage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(massage);
        builder.show();


    }

}
