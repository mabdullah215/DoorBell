package com.mobileapp.doorbell.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.mobileapp.doorbell.R;
import com.mobileapp.doorbell.model.EventModel;

import java.util.List;

public class CalendarViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<EventModel> mData;
    private List<Integer> matchList;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    int selected=0;
    String [] colorArray={"#DB1659","#FD8507","#46c4f3","#472CB4","#4E5D6B","#E8AC57"};
    private  int TYPE_EMPTY = -1;
    // data is passed into the constructor
    public CalendarViewAdapter(Context context, List<EventModel> data,List<Integer>matchList)
    {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.matchList=matchList;
    }

    public void selectedPosition(int postion)
    {
        selected = postion;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view;
        view=mInflater.inflate(R.layout.control_calendar_day, parent, false);
        if(viewType==TYPE_EMPTY)
        {
            return new EmptyViewHolder(view);
        }
        else
        {
            return new CellViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position)
    {
        if (getItemViewType(position) == TYPE_EMPTY)
        {
            ((EmptyViewHolder) holder).setDetails();
        }

        else
        {
            ((CellViewHolder) holder).setDetails(position);
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        return mData.get(position).getEventDate();
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder
    {

        TextView myTextView;
        MaterialCardView cellCard;

        EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.circular_text);
            cellCard=itemView.findViewById(R.id.cell_card);
        }

        void setDetails()
        {
            myTextView.setText("");
            cellCard.setVisibility(View.GONE);
        }
    }


    public class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        MaterialCardView cellCard;
        CellViewHolder(View itemView)
        {
            super(itemView);
            myTextView = itemView.findViewById(R.id.circular_text);
            cellCard=itemView.findViewById(R.id.cell_card);
            itemView.setOnClickListener(this);
        }


        public void setDetails(int position)
        {
            EventModel item= mData.get(position);
            myTextView.setText(""+item.getEventDate());

            if(item.getEventColor()==-1)
            {
               /* cellCard.setStrokeColor(Color.parseColor("#76b850"));
                cellCard.setStrokeWidth(1);*/
               // cellCard.setCardBackgroundColor(Color.parseColor("#ffffff"));
               // myTextView.setTextColor(Color.parseColor("#999999"));
            }
            else
            {
                int color=Color.parseColor(colorArray[item.getEventColor()]);
               // cellCard.setCardBackgroundColor(color);
                myTextView.setTextColor(Color.WHITE);
            }

        }

        @Override
        public void onClick(View view)
        {
            if (mClickListener != null)
            {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }



    public List<EventModel>getCurrentDataList()
    {
        return mData;
    }



    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public long getItemId(int position)
    {
       return position;
    }

    // stores and recycles views as they are scrolled off screen


    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}