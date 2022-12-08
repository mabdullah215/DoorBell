package com.mobileapp.doorbell.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mobileapp.doorbell.R;

import java.util.ArrayList;


public class MainScreenMenu extends RelativeLayout
{
    ImageView imgHome,imgNotifications,imgSettings;
    ArrayList<ImageView>imgList=new ArrayList<>();
    PositionUpdateListener updateListener;
    int selected=0;
    Context mContext;

    public interface PositionUpdateListener
    {
        void onPositionUpdate(int pos);
    }

    public MainScreenMenu(Context context)
    {
        super(context);
        mContext=context;
        initView();
    }

    public MainScreenMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView();
    }

    public MainScreenMenu(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext=context;
        initView();
    }


    public MainScreenMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView();
    }
    private void initView()
    {
        inflate(getContext(), R.layout.menu_screen, this);
        imgHome=findViewById(R.id.img_home);
        imgSettings=findViewById(R.id.img_settings);
        imgNotifications=findViewById(R.id.img_notification);
        imgList.add(imgHome);
        imgList.add(imgNotifications);
        imgList.add(imgSettings);
        selected=0;


        imgHome.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {
                selected=0;
                selectOption(selected);
            }
        });


        imgNotifications.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)
            {   selected=2;
                selectOption(selected);
            }
        });


        selectOption(selected);
    }


    public void setUpdateListener(PositionUpdateListener updateListener) {
        this.updateListener = updateListener;
    }

    public void selectOption(int num)
    {
        if(updateListener!=null)
        {
            updateListener.onPositionUpdate(num);
        }
    }

    public int getSelected() {
        return selected;
    }
}
