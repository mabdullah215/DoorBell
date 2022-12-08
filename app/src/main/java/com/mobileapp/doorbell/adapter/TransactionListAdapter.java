package com.mobileapp.doorbell.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapp.doorbell.R;
import com.mobileapp.doorbell.model.Transaction;

import java.util.ArrayList;
import java.util.List;


public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.ViewHolder>
{
    private List<Transaction> dataList;
    Context mContext;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<Transaction> getDataList() {
        return dataList;
    }

    public TransactionListAdapter(Context context, List<Transaction> eventList)
    {
        this.dataList = eventList;
        mContext=context;
    }

    public void setDataList(ArrayList<Transaction> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_transaction, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public int getItemCount()
    {
        return dataList == null? 0: dataList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
    {
        final Transaction item = dataList.get(position);
        holder.setDetails(item,position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mTitle;
        private TextView mAmount;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mTitle=itemView.findViewById(R.id.tv_name);
            mAmount=itemView.findViewById(R.id.tv_amount);
            itemView.setOnClickListener(this);
        }

         public void setDetails(final Transaction item,int position)
         {
             mTitle.setText(item.getName());
             mAmount.setText(item.getAmount());
         }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }


}


