package com.letskodeit.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Util {

    public static void sleep(long milli, String info){
        if(info != null){
            System.out.println("Wait "+ milli + "for " + info);
        }

        try{
            Thread.sleep(milli);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void sleep(long milli){
        sleep(milli, null);
    }

    public static int getRandomNumber(int min, int max){
        int diff = max- min;
        return (int)(min + Math.random()*diff);
    }

    public static int getRandomNumber(int num){

        return getRandomNumber(1,num);
    }

    public static String getRandomString(int length){
        StringBuilder sb = new StringBuilder();
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        for (int i=0; i<length; i++){
            int index = (int)(Math.random()*chars.length());
            sb.append(chars.charAt(index));
        }
        return sb.toString();
    }

    public static String getRandomString(){
        return getRandomString(10);
    }

    public static LocalDate getLocalDate(){
        LocalDate date = LocalDate.now();
        return date;
    }

    public static void getLocalDateTime(){

    }

    public static String getReportName(){
        LocalDateTime localDateTime = LocalDateTime.now();
        StringBuilder sb = new StringBuilder().append("AutomationReport_").append(localDateTime).append(".html");
        return sb.toString();
    }

    public static String getScreenShotName(String methodName, String browserName){
        LocalDateTime dateTime = LocalDateTime.now();
        StringBuilder name = new StringBuilder().append(browserName).append(methodName).append(dateTime).append(".png");
        return name.toString();
    }
}
