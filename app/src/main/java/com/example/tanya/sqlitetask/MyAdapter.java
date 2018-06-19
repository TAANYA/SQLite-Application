package com.example.tanya.sqlitetask;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
{
    private List<ListItem> listItems;
    private Context context;
    private SqliteDatabase db;
    private MyAdapter adapter;

    public MyAdapter(List<ListItem> listItems, Context context, SqliteDatabase db)
    {
        this.db = db;
        this.listItems = listItems;
        this.context = context;
        adapter = this;
    }

    public void setAdapter(MyAdapter adapter)
    {
        this.adapter = adapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listinfo,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position)
    {
        final ListItem listItem = listItems.get(position);

        holder.txtname.setText(listItem.getName());
        holder.txtlname.setText(listItem.getLname());
        holder.txtbranch.setText(listItem.getBranch());

        holder.buttonupdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                UpdateListInfo updateListInfo = new UpdateListInfo(context,db,listItem,adapter,position);
                updateListInfo.show();
            }
        });

        holder.buttondelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                boolean check = db.deleteData(listItems.get(position).getId());

                if (check)
                {
                    listItems.remove(position);
                    notifyDataSetChanged();
                }
            }
        });


    }

    public void updateList(ListItem oldListItem, ListItem newListItem )
    {
//        listItems.get(position).setName(newListItem.getName());
//        listItems.get(position).setName(newListItem.getLname());
//        listItems.get(position).setName(newListItem.getBranch());

//        for (int i =0 ;i <listItems.size() ; i++)
//        {
//            if (listItems.get(i).getId() == oldListItem.getId())
//            {
//
//            }
//        }
        oldListItem.setName(newListItem.getName());
        oldListItem.setLname(newListItem.getLname());
        oldListItem.setBranch(newListItem.getBranch());

        swapData(listItems);
    }


    public void swapData(List<ListItem> list)
    {

        listItems = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtname, txtlname,txtbranch;
        ImageButton buttonupdate, buttondelete;

        public ViewHolder(View itemView)
        {
            super(itemView);

            txtname = (TextView) itemView.findViewById(R.id.txt1);
            txtlname = (TextView) itemView.findViewById(R.id.txt2);
            txtbranch = (TextView) itemView.findViewById(R.id.txt3);
            buttonupdate =(ImageButton)itemView.findViewById(R.id.imgupdate);
            buttondelete =(ImageButton)itemView.findViewById(R.id.imgdelete);

        }
    }

}
