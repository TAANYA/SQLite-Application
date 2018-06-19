package com.example.tanya.sqlitetask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtname, edtlname, edtbranch;
    Button buttonadd, buttonview;
    SqliteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtname = (EditText) findViewById(R.id.editName);
        edtlname = (EditText) findViewById(R.id.editLname);
        edtbranch = (EditText) findViewById(R.id.editBranch);
        buttonadd = (Button) findViewById(R.id.btnAdd);
        buttonview = (Button)findViewById(R.id.btnView);

        buttonview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(),AddListActivity.class);
                startActivity(intent);

            }
        });


        addData();
    }

    public void addData() {
        buttonadd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                db = new SqliteDatabase(getApplicationContext(),SqliteDatabase.DATABASE_NAME,null,1);
                boolean isInserted = db.insertData(edtname.getText().toString(),
                        edtlname.getText().toString()
                        , edtbranch.getText().toString());

                if (isInserted)
                {
                    Intent intent = new Intent(getApplicationContext(),AddListActivity.class);
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else
                    Toast.makeText(MainActivity.this,"Data is not inserted",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
