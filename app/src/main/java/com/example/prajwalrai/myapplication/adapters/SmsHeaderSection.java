package com.example.prajwalrai.myapplication.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.prajwalrai.myapplication.R;
import com.example.prajwalrai.myapplication.sectionedrecyclerview.Section;

public class SmsHeaderSection extends Section<SmsHeaderSection.SmsHeaderViewHolder> {

    private String title;

    public SmsHeaderSection(String title) {
        this.title = title;
    }

    @Override
    public SmsHeaderViewHolder getViewHolder(View view) {
        View v = LayoutInflater.from(view.getContext()).inflate(R.layout.recycler_view_header_layout, null);
        return new SmsHeaderViewHolder(v);
    }

    @Override
    public void onBind(SmsHeaderViewHolder holder, int position) {
        holder.smsHeader.setText(title);
    }

    @Override
    public int getLayout() {
        return R.layout.recycler_view_header_layout;
    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public class SmsHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView smsHeader;

        public SmsHeaderViewHolder(View itemView) {
            super(itemView);
            smsHeader = itemView.findViewById(R.id.tv_sms_header_title);
        }
    }
}
