package com.example.quanlyguixe.util;

import java.text.SimpleDateFormat;

public class Constant {
    public static final String DD_MM_YYYY = "dd/MM/yyyy";

    public static final int DEFAULT_START_YEAR = 1900;
    public static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat(DD_MM_YYYY);

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm");

    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM/dd/yyyy hh:mm");
    public static final String KEY_BUNDLE_TICKET = "tickets";
    public static final String KEY_BUNDLE_PARKING_LOT = "parkinglots";
    public static final String KEY_BUNDLE_EMPLOYEE = "employee";
    public static final String KEY_BUNDLE_IS_UPDATE = "isUpdate";
}
