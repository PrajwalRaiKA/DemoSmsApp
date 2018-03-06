package com.example.prajwalrai.myapplication.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.prajwalrai.myapplication.R;
import com.example.prajwalrai.myapplication.Sms;
import com.example.prajwalrai.myapplication.sectionedrecyclerview.Section;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SmsSection extends Section<SmsSection.SmsHeaderViewHolder> {

    private ArrayList<Sms> smsArrayList;

    public SmsSection(ArrayList<Sms> smsArrayList) {
        this.smsArrayList = smsArrayList;
    }

    @Override
    public SmsHeaderViewHolder getViewHolder(View view) {
        View v = LayoutInflater.from(view.getContext()).inflate(R.layout.recycler_view_sms_layout, null);
        return new SmsHeaderViewHolder(v);
    }

    @Override
    public void onBind(SmsHeaderViewHolder holder, int position) {
        Sms sms = smsArrayList.get(position);
        holder.smsSender.setText(sms.getAddress());
        holder.smsDesc.setText(sms.getMsg());
        holder.smsTime.setText(getDateFromMilli(Long.parseLong(sms.getTime())));
    }

    @Override
    public int getLayout() {
        return R.layout.recycler_view_sms_layout;
    }

    @Override
    public int getItemCount() {
        return smsArrayList.size();
    }


    public class SmsHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView smsSender;
        private TextView smsDesc;
        private TextView smsTime;

        public SmsHeaderViewHolder(View itemView) {
            super(itemView);
            smsSender = itemView.findViewById(R.id.tv_sms_sender);
            smsDesc = itemView.findViewById(R.id.tv_sms_desc);
            smsTime = itemView.findViewById(R.id.tv_sms_date);
        }
    }

    private String getDateFromMilli(long milliSeconds) {

        DateFormat formatter = new SimpleDateFormat("hh:mma dd/MM/yy");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
