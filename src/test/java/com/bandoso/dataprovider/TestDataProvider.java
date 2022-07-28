package com.bandoso.dataprovider;

import com.bandoso.utils.ExcelReaderUtil;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

public class TestDataProvider {
     @Test(dataProvider = "loginData")
    public void login(String username, String password, String usernameAtferLogin){
         System.out.println(username + "\t" + password + "\t" + usernameAtferLogin);

     }
     @DataProvider
    public Object[][] loginData(){
        //array 2 rows and 2 column
         /**
          admin.tbi Nexle@2020
          admin.tbi 123456
         **/
         String excelFileName = "Login.xlsx";
         File excelFileLocation = new File(System.getProperty("user.dir") + "/data/" +excelFileName);
         String sheetName = "accounts";
         int startRowIndex = 1;
         int startColIndex = 0;
         ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(excelFileLocation, sheetName, startRowIndex, startColIndex);
         int totalRows = excelReaderUtil.getTotalRows();
         int totalCols = excelReaderUtil.getTotalCols();
         Object[][] loginData = new Object[totalRows - startRowIndex][totalCols - startColIndex];
         for (int startRow = startRowIndex; startRow < totalRows; startRow++){
             for (int startCol = startColIndex; startCol < totalCols; startCol++){
                 loginData[startRow - startRowIndex][startCol]= excelReaderUtil.getCellValue(startRow, startCol);
             }
         }
         return loginData;
     }
}
