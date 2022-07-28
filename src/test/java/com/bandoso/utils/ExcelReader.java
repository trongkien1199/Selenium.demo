package com.bandoso.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {
    public static void main(String[] args) {
        String excelFileName = "Login.xlsx";
        File excelFileLocation = new File(System.getProperty("user.dir") + "/data/"+ excelFileName);
        System.out.println(excelFileLocation);
        try{
            FileInputStream fis = new FileInputStream(excelFileLocation);
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet("accounts");
            for (Row row : sheet){
                for (Cell cell : row){
                    System.out.print(cell);
                }
                System.out.println("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
