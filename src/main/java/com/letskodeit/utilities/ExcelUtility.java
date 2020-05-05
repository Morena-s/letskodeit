package com.letskodeit.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class ExcelUtility {
    private  static XSSFWorkbook ExcelWorkbook;
    private static XSSFSheet ExcelSheet;
//    private static XSSFCell ExcelCell;

    public static void setExcelFile(String path, String sheetName){
        try{
            FileInputStream excelFile = new FileInputStream(path);
            ExcelWorkbook = new XSSFWorkbook(excelFile);
            ExcelSheet = ExcelWorkbook.getSheet(sheetName);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static String[][] getTestData(String tableName){
        String[][] testData = null;

        try{
            DataFormatter formatter = new DataFormatter();
            XSSFCell[] boundaryCells =findTableNameCells(tableName);
            XSSFCell startCell = boundaryCells[0];
            XSSFCell endCell = boundaryCells[1];

            int startRow = startCell.getRowIndex() +1;
            int startCol = startCell.getColumnIndex() +1;
            int endRow = endCell.getRowIndex() -1;
            int endCol = endCell.getColumnIndex() -1;

            testData = new String[endRow - startRow +1][endCol -startCol +1];

            for(int i = startRow ; i<=endRow ; i++){
                for(int j = startCol ; j<=endCol; j++){
                    Cell cell = ExcelSheet.getRow(i).getCell(j);
                    testData[i - startRow][j - startCol] = formatter.formatCellValue(cell);
                }
            }



        }catch(Exception e){

        }
        return testData;

    }

    public static XSSFCell[] findTableNameCells(String tableName){
        DataFormatter formatter = new DataFormatter();
        String pos ="begin";
        XSSFCell[] cells = new XSSFCell[2];
        for(Row row : ExcelSheet){
            for(Cell cell: row){
                if(tableName.equals(formatter.formatCellValue(cell))){
                    if(pos.equalsIgnoreCase("begin")){
                        cells[0] = (XSSFCell)cell;
                        pos ="end";
                    }else{
                        cells[1]=(XSSFCell)cell;
                    }
                }
            }
        }
        return cells;

    }
}
