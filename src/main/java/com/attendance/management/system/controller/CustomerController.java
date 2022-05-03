package com.attendance.management.system.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerController {
    public static void main(String arg[]){
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        String formattedDate = dateFormat.format(new Date()).toString();
        System.out.println(formattedDate);
    }
}
