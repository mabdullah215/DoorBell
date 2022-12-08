package com.mobileapp.doorbell.utils;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mobileapp.doorbell.R;
import com.mobileapp.doorbell.adapter.CalendarViewAdapter;
import com.mobileapp.doorbell.model.EventModel;

import java.util.ArrayList;
import java.util.Calendar;


public class GregorianCalView extends LinearLayout
{

	ArrayList<EventModel> cells = new ArrayList<>();
	CalendarViewAdapter adapter;
	RecyclerView mCalendarView;
	Context mContext;

	public GregorianCalView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mContext=context;
		initControl();
	}

	private void initControl()
	{
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.control_calendar,this);
		mCalendarView = findViewById(R.id.calendar_grid);
		mCalendarView.setHasFixedSize(true);
	}

	public void updateEventsList(ArrayList<EventModel>mlist)
	{

			for (int i = 0; i < cells.size(); i++)
			{
				cells.get(i).setEventColor(Color.WHITE);
				for(int j=0;j<mlist.size();j++)
				{

					if(mlist.get(j).getEventDate()==cells.get(i).getEventDate())
					{
						cells.get(i).setEventColor(mlist.get(j).getEventColor());
						break;
					}
				}

			}

			adapter.notifyDataSetChanged();
	}

	public void updateView(int month,int year)
	{
		final Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR,year);
		calendar.set(Calendar.MONTH,month);
		calendar.set(Calendar.DATE,1);
		int day= calendar.get(Calendar.DAY_OF_WEEK)-1;
		int dayCount=1;
		cells.clear();


		while (dayCount<=calendar.getActualMaximum(Calendar.DATE))
		{

			EventModel model= new EventModel();
			if(cells.size()<day)
			{
				model.setEventDate(-1);
				cells.add(model);
			}

			else
			{
				model.setEventDate(dayCount);
				model.setEventMonth(calendar.get(Calendar.MONTH));
				model.setEventYear(calendar.get(Calendar.YEAR));
				cells.add(model);
				dayCount++;
			}
		}

		ArrayList<Integer>matchList=new ArrayList<>();

		adapter=new CalendarViewAdapter(mContext,cells,matchList);
		GridLayoutManager gidlayout= new GridLayoutManager(mContext,7);
		mCalendarView.setLayoutManager(gidlayout);
		mCalendarView.setItemAnimator(new DefaultItemAnimator());
		mCalendarView.setAdapter(adapter);

	}


	public void updatePosition()
	{
		adapter.notifyDataSetChanged();
	}


	public void setEventHandler(EventHandler eventHandler)
	{

	}

	public interface EventHandler
	{
		void onDayPress(EventModel date, int pos);
	}


}
