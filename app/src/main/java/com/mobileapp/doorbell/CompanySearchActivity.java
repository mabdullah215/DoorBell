package com.mobileapp.doorbell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.mobileapp.doorbell.adapter.CompanyListAdapter;
import com.mobileapp.doorbell.model.Company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CompanySearchActivity extends AppCompatActivity {

    CompanyListAdapter adapter;
    ArrayList<Company> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_search);
        TextView tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText("Select Company");
        ImageView imgBack=findViewById(R.id.img_back);
        imgBack.setOnClickListener(view -> finish());
        readFile();
        adapter=new CompanyListAdapter(getBaseContext(),list);
        RecyclerView recyclerView=findViewById(R.id.data_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        EditText etSearchBox=findViewById(R.id.et_search);
        etSearchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                String text=editable.toString().trim();
                searchTxt(text);
            }
        });

        adapter.setOnItemClickListener(new CompanyListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position)
            {
                startActivity(new Intent(getBaseContext(),MainActivity.class));
                Animatoo.INSTANCE.animateSlideLeft(CompanySearchActivity.this);
            }
        });
    }

    public void readFile()
    {
        BufferedReader reader;
        try{
            final InputStream file = getAssets().open("companylist.txt");
            reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            while(line != null)
            {
                list.add(new Company(line));
                line = reader.readLine();
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public void searchTxt(String query)
    {
        if(query.isEmpty())
        {
            adapter.setDataList(list);
        }
        else
        {
            ArrayList<Company>filterList=new ArrayList<>();
            for(Company company: list)
            {
                if(company.getName().toLowerCase().startsWith(query.toLowerCase()))
                {
                    filterList.add(company);
                }
            }

            adapter.setDataList(filterList);
        }
    }

}