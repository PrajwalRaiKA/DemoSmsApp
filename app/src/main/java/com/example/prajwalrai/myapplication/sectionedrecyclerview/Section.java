package com.example.prajwalrai.myapplication.sectionedrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by shaishav.gandhi on 11/19/16.
 */

public abstract class Section<VH extends RecyclerView.ViewHolder>{

    public abstract void onBind(VH holder, int position);

    public abstract int getLayout();

    public abstract int getItemCount();

    public abstract VH getViewHolder(View view);

}
