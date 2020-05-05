package com.letskodeit.base;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckPoint {
    public static HashMap<String, String> resultMap = new HashMap<>();
    public static Logger log = LogManager.getLogger(CheckPoint.class.getName());

    private static String pass ="PASS";
    private static String fail ="FAIL";

    /**
     * Clear the results hash map
     */

    public static void clearHashMap(){
        System.out.println("Clearing Results Hash Map .. ");
        resultMap.clear();
    }

    private static void setStatus(String mapKey, String status){
        resultMap.put(mapKey,status);
        log.info(mapKey + "->" + resultMap.get(mapKey) );
    }
    public static void mark(String testName, boolean result, String resultMessage){
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try{
            if(result)
                setStatus(mapKey,pass);
            else
                setStatus(mapKey,fail);
        }catch (Exception e){
            System.out.println("Exception Occurred ..");
            setStatus(mapKey,fail);
            e.printStackTrace();
        }
    }
    public static void markFinal(String testName, boolean result, String resultMessage){
        testName = testName.toLowerCase();
        String mapKey = testName + "." + resultMessage;
        try{
            if(result)
                setStatus(mapKey,pass);
            else
                setStatus(mapKey,fail);
        }catch (Exception e){
            log.error("Exception Occurred ..");
            setStatus(mapKey,fail);
            e.printStackTrace();
        }
        ArrayList<String> resultList = new ArrayList<>();
        for(String key:resultMap.keySet()){
            resultList.add(resultMap.get(key));
        }

        for(int i=0; i < resultList.size(); i++ ){
            if(resultList.contains("FAIL")){
                System.out.println("Test method failed");
                Assert.assertTrue(false);
            }else{
                System.out.println("Test method successful");
                Assert.assertTrue(true);}

        }
    }
}
