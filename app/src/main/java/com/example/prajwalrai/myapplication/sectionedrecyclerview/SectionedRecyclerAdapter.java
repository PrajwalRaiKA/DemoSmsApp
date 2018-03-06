package com.example.prajwalrai.myapplication.sectionedrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shaishav.gandhi on 11/19/16.
 */

public abstract class SectionedRecyclerAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    Context mContext;

    List<Section> mSections;

    public SectionedRecyclerAdapter(Context context) {
        mContext = context;

        mSections = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        SectionMap sectionMap = getSectionMap(position);
        return mSections.indexOf(sectionMap.getSection());
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (int i = 0; i < mSections.size(); i++) {
            count += mSections.get(i).getItemCount();

        }
        return count;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Section section = null;

        section = mSections.get(viewType);

        View view = inflater.inflate(section.getLayout(), parent, false);

        return (VH) section.getViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        SectionMap sectionMap = getSectionMap(position);
        sectionMap.getSection().onBind(holder, position - sectionMap.getStartPosition());

    }


    public void addSection(Section... sections) {
        for (Section section : sections) {
            mSections.add(section);
        }
    }

    public void removeSection(Section... sections) {
        for (Section section : sections) {
            mSections.remove(section);
        }
    }

    private SectionMap getSectionMap(int position) {
        int count = 0;
        SectionMap sectionMap = null;
        for (int i = 0; i < mSections.size(); i++) {
            count += mSections.get(i).getItemCount();
            if (position < count) {
                sectionMap = new SectionMap(count - mSections.get(i).getItemCount(), mSections.get(i));
                break;
            }
        }
        return sectionMap;
    }

}
