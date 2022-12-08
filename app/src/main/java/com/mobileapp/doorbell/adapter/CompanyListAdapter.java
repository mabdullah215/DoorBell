package com.mobileapp.doorbell.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.mobileapp.doorbell.R;
import com.mobileapp.doorbell.model.Company;
import java.util.ArrayList;
import java.util.List;


public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.ViewHolder>
{
    private List<Company> dataList;
    Context mContext;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public List<Company> getDataList() {
        return dataList;
    }

    public CompanyListAdapter(Context context, List<Company> eventList)
    {
        this.dataList = eventList;
        mContext=context;
    }

    public void setDataList(ArrayList<Company> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_company, parent, false);
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
        final Company item = dataList.get(position);
        holder.setDetails(item,position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mTitle;

        public ViewHolder(View itemView)
        {
            super(itemView);
            mTitle=itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(this);
        }

         public void setDetails(final Company item,int position)
         {
             mTitle.setText(item.getName());
         }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(getAdapterPosition());
        }
    }


}


