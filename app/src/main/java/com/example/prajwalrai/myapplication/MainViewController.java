package com.example.prajwalrai.myapplication;

import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class MainViewController {

    private MainActivity mainActivity;
    private LinkedHashMap<String, ArrayList<Sms>> smsHashMap;
    private static final String ZERO_HOUR = "A few mins ago";
    private static final String ONE_HOUR = "One hour ago";
    private static final String TWO_HOUR = "Two hours ago";
    private static final String THREE_HOUR = "Three hours ago";
    private static final String FOUR_HOUR = "Four hours ago";
    private static final String TODAY = "Within 24 Hours";
    private static final String OLDER = "Older";


    public MainViewController(MainActivity activity) {
            mainActivity=activity;
            smsHashMap=new LinkedHashMap<>();
    }


    private void arrangeSms(List<Sms> lst) {
        for (Sms sms : lst) {
            double timeBefore = System.currentTimeMillis() - Double.parseDouble(sms.getTime());
            sms.setArrivedBefore(timeBefore);
            if (timeBefore / 60000 > 0 && timeBefore / 60000 < 60) {
                if (smsHashMap.get(ZERO_HOUR) != null) {
                    ArrayList<Sms> smsArrayList = smsHashMap.get(ZERO_HOUR);
                    smsArrayList.add(sms);
                    smsHashMap.put(ZERO_HOUR, smsArrayList);
                } else {
                    ArrayList<Sms> smsArrayList = new ArrayList<>();
                    smsArrayList.add(sms);
                    smsHashMap.put(ZERO_HOUR, smsArrayList);
                }
            } else if (timeBefore / 60000 > 61 && timeBefore / 60000 < 120) {
                if (smsHashMap.get(ONE_HOUR) != null) {
                    ArrayList<Sms> smsArrayList = smsHashMap.get(ONE_HOUR);
                    smsArrayList.add(sms);
                    smsHashMap.put(ONE_HOUR, smsArrayList);
                } else {
                    ArrayList<Sms> smsArrayList = new ArrayList<>();
                    smsArrayList.add(sms);
                    smsHashMap.put(ONE_HOUR, smsArrayList);
                }
            } else if (timeBefore / 60000 > 121 && timeBefore / 60000 < 180) {
                if (smsHashMap.get(TWO_HOUR) != null) {
                    ArrayList<Sms> smsArrayList = smsHashMap.get(TWO_HOUR);
                    smsArrayList.add(sms);
                    smsHashMap.put(TWO_HOUR, smsArrayList);
                } else {
                    ArrayList<Sms> smsArrayList = new ArrayList<>();
                    smsArrayList.add(sms);
                    smsHashMap.put(TWO_HOUR, smsArrayList);
                }
            } else if (timeBefore / 60000 > 181 && timeBefore / 60000 < 240) {
                if (smsHashMap.get(THREE_HOUR) != null) {
                    ArrayList<Sms> smsArrayList = smsHashMap.get(THREE_HOUR);
                    smsArrayList.add(sms);
                    smsHashMap.put(THREE_HOUR, smsArrayList);
                } else {
                    ArrayList<Sms> smsArrayList = new ArrayList<>();
                    smsArrayList.add(sms);
                    smsHashMap.put(THREE_HOUR, smsArrayList);
                }
            } else if (timeBefore / 60000 > 241 && timeBefore / 60000 < 300) {
                if (smsHashMap.get(FOUR_HOUR) != null) {
                    ArrayList<Sms> smsArrayList = smsHashMap.get(FOUR_HOUR);
                    smsArrayList.add(sms);
                    smsHashMap.put(FOUR_HOUR, smsArrayList);
                } else {
                    ArrayList<Sms> smsArrayList = new ArrayList<>();
                    smsArrayList.add(sms);
                    smsHashMap.put(FOUR_HOUR, smsArrayList);
                }
            } else if (timeBefore / 60000 > 301 && timeBefore / 60000 < 1440) {
                if (smsHashMap.get(TODAY) != null) {
                    ArrayList<Sms> smsArrayList = smsHashMap.get(TODAY);
                    smsArrayList.add(sms);
                    smsHashMap.put(TODAY, smsArrayList);
                } else {
                    ArrayList<Sms> smsArrayList = new ArrayList<>();
                    smsArrayList.add(sms);
                    smsHashMap.put(TODAY, smsArrayList);
                }
            } else {
                if (smsHashMap.get(OLDER) != null) {
                    ArrayList<Sms> smsArrayList = smsHashMap.get(OLDER);
                    smsArrayList.add(sms);
                    smsHashMap.put(OLDER, smsArrayList);
                } else {
                    ArrayList<Sms> smsArrayList = new ArrayList<>();
                    smsArrayList.add(sms);
                    smsHashMap.put(OLDER, smsArrayList);
                }
            }
        }
        mainActivity.showSms(smsHashMap);
    }

    public void getAllSms() {
        List<Sms> lstSms = new ArrayList<>();
        Sms objSms;
        Uri message = Uri.parse("content://sms/");

        Cursor c = mainActivity.getContentResolver().query(message, null, null, null, null);
        if (c != null) {
            int totalSMS = c.getCount();
            if (c.moveToFirst()) {
                for (int i = 0; i < totalSMS; i++) {

                    objSms = new Sms();
                    objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
                    objSms.setAddress(c.getString(c
                            .getColumnIndexOrThrow("address")));
                    objSms.setMsg(c.getString(c.getColumnIndexOrThrow("body")));
                    objSms.setReadState(c.getString(c.getColumnIndex("read")));
                    objSms.setTime(c.getString(c.getColumnIndexOrThrow("date")));
                    if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
                        objSms.setFolderName("inbox");
                    } else {
                        objSms.setFolderName("sent");
                    }

                    lstSms.add(objSms);
                    c.moveToNext();
                }
            }
            c.close();
            c = null;
        }
        if (lstSms.size() > 0) {
            mainActivity.hideEmptyView();
            arrangeSms(lstSms);
        } else {
            mainActivity.showEmptyView();
        }
    }
}
