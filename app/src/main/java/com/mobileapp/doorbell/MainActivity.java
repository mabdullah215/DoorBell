package com.mobileapp.doorbell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.mobileapp.doorbell.adapter.TransactionListAdapter;
import com.mobileapp.doorbell.model.Transaction;
import com.mobileapp.doorbell.utils.GregorianCalView;
import com.mobileapp.doorbell.utils.MainScreenMenu;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateCalendarView();
        RecyclerView recyclerView=findViewById(R.id.transaction_list);
        ArrayList<Transaction>list=new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list.add(new Transaction("$ 115","27-07-2022","Jonathan Campbell"));
        TransactionListAdapter adapter=new TransactionListAdapter(this,list);
        recyclerView.setAdapter(adapter);
        MainScreenMenu mainScreenMenu=findViewById(R.id.main_menu);
        mainScreenMenu.setUpdateListener(new MainScreenMenu.PositionUpdateListener() {
            @Override
            public void onPositionUpdate(int pos)
            {
                if(pos==1)
                {
                    startActivity(new Intent(getBaseContext(),NotificationActivity.class));
                    Animatoo.INSTANCE.animateSlideLeft(MainActivity.this);
                }
                else if (pos==2)
                {
                    startActivity(new Intent(getBaseContext(),SettingsActivity.class));
                    Animatoo.INSTANCE.animateSlideLeft(MainActivity.this);
                }
            }
        });
    }
    public void updateCalendarView()
    {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        GregorianCalView calView=findViewById(R.id.calview);
        calView.updateView(month,year);
    }
}