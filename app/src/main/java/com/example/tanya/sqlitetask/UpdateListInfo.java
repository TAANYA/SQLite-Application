package com.example.tanya.sqlitetask;

import android.app.Dialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

public class UpdateListInfo extends Dialog
{
    private Context context;
    private SqliteDatabase db;
    private MyAdapter adapter;
    private ListItem listItem;

    private Button buttonupdate;
    private EditText edtname, edtlname, edtbranch;
    String sn, sln, sb;
    int position;

    public UpdateListInfo(@NonNull Context context, SqliteDatabase db, ListItem listItem, MyAdapter adapter, int position)
    {
        super(context);
        this.context = context;
        this.db = db;
        this.listItem = listItem;
        this.adapter = adapter;
        this.position = position;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_update);

        buttonupdate = (Button)findViewById(R.id.buttonupdate);
        edtname = (EditText)findViewById(R.id.editTextname);
        edtlname = (EditText)findViewById(R.id.editTextlname);
        edtbranch = (EditText)findViewById(R.id.editTextbranch);


        buttonupdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                sn = edtname.getText().toString();
                sln = edtlname.getText().toString();
                sb = edtbranch.getText().toString();

                ListItem listItem2 = new ListItem(listItem.getId(),sn, sln, sb);
                db = new SqliteDatabase(context, SqliteDatabase.DATABASE_NAME,null,1);
                db.updateData(sn, sln, sb,listItem.getId());
                adapter.updateList(listItem,listItem2);
                dismiss();
            }
        });
    }
}
