package com.example.tanya.sqlitetask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.List;

public class AddListActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<ListItem> listItems;
    SqliteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_list);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        db = new SqliteDatabase(this, SqliteDatabase.DATABASE_NAME,null, 1);

        adapter = new MyAdapter(db.getData(),this,db);
        recyclerView.setAdapter(adapter);
    }

}
