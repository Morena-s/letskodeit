package com.letskodeit.overview;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.util.Properties;


public class ExcelReadExample {

//    @DataProvider(name = "testcasedataprovider")
//    public static Object[][] newone(){
//        return new Object[][]{{"Sally", "Askar"}, {"Amro","Omar" };
//    }

    public static Properties getPropertiesFile(String path){
        Properties pro = new Properties();

        try {
            FileInputStream file = new FileInputStream(path);
            pro.load(file);

        }catch(Exception e){
            e.printStackTrace();
        }
        return pro;
    }


    public static void main(String[] args){
        XSSFWorkbook excelWB;
        XSSFSheet excelWSheet;
        XSSFCell excelCell;

        String path = System.getProperty("user.dir") + "//src//main//resources//testdata//ExcelExample.xlsx";
        String sheetName = "Scenario1";

        try{
            FileInputStream excelFile = new FileInputStream(path);
            excelWB = new XSSFWorkbook(excelFile);
            excelWSheet = excelWB.getSheet(sheetName);
            excelCell = excelWSheet.getRow(0).getCell(1);
            String excelData = excelCell.getStringCellValue();
            System.out.println(excelData);
            System.out.println(ExcelReadExample.class.getSimpleName());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

