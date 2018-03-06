package com.example.prajwalrai.myapplication.adapters;


import android.support.v7.widget.RecyclerView;

import com.example.prajwalrai.myapplication.MainActivity;
import com.example.prajwalrai.myapplication.Sms;
import com.example.prajwalrai.myapplication.sectionedrecyclerview.SectionedRecyclerAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SmsAdapter extends SectionedRecyclerAdapter<RecyclerView.ViewHolder> {


    public SmsAdapter(LinkedHashMap<String, ArrayList<Sms>> smsHashMap, MainActivity mParentActivity) {
        super(mParentActivity);

        for (String key : smsHashMap.keySet()) {
            if (smsHashMap.get(key) != null && smsHashMap.get(key).size() > 0) {
                addSection(new SmsHeaderSection(key));
                addSection(new SmsSection(smsHashMap.get(key)));
            }
        }
    }

}
