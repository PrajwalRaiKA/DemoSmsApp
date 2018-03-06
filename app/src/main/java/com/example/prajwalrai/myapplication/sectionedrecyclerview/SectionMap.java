package com.example.prajwalrai.myapplication.sectionedrecyclerview;

/**
 * Created by shaishav.gandhi on 11/26/16.
 */

public class SectionMap {

    private Section section;
    private int startPosition;

    public SectionMap(int startPosition, Section section) {
        this.startPosition = startPosition;
        this.section = section;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }
}
