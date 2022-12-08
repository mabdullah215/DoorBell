package com.mobileapp.doorbell.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.mobileapp.doorbell.R;
import com.mobileapp.doorbell.model.EventModel;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.MyViewHolder>
    {
        private Context context;
        private ArrayList<EventModel> arrayList = new ArrayList<>();


        public CalendarAdapter(Context context, ArrayList<EventModel> arrayList)
        {
            this.context = context;
            this.arrayList = arrayList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.control_calendar, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
        {


        }


        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder
        {
            public MyViewHolder(@NonNull View itemView)
            {
                super(itemView);
            }
        }

    }
