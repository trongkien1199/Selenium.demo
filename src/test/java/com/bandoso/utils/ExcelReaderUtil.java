package com.bandoso.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ExcelReaderUtil {
    private File excelFileLocation;
    private FileInputStream fis;
    private String sheetName;
    private int startRow;
    private int startColumn;
    private int totalRows;
    private int totalCols;
    private Workbook workbook;
    private Sheet sheet;
    private Row row;
    private Cell cell;

    public ExcelReaderUtil(File excelFileLocation, String sheetName, int startRow, int startColumn){
        this.excelFileLocation = excelFileLocation;
        this.sheetName = sheetName;
        this.startRow = startRow;
        this.startColumn = startColumn;
        try{
            fis = new FileInputStream(excelFileLocation);
            workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(this.sheetName);
            totalRows = sheet.getPhysicalNumberOfRows();
            totalCols = sheet.getRow(0).getPhysicalNumberOfCells();
            fis.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getCellValue(int rowNo, int colNo){
        Row row = sheet.getRow(rowNo);
        Cell cell = row.getCell(colNo);
        String cellValue = "";
        CellType cellType = cell.getCellType();

        if(cellType.equals(CellType.STRING)){
            cellValue = cell.getStringCellValue();
        }
        if(cellType.equals(CellType.NUMERIC)){
            NumberFormat nf = new DecimalFormat("#.####");
            cellValue = String.valueOf(nf.format(cell.getNumericCellValue()));
        }
        return cellValue;
    }
    public int getTotalRows(){
        return this.totalRows;
    }
    public int getTotalCols(){
        return this.totalCols;
    }

    public static void main(String[] args) {
        String excelFileName = "Login.xlsx";
        File excelFileLocation = new File(System.getProperty("user.dir") + "/data/"+ excelFileName);
        ExcelReaderUtil excelReaderUtil = new ExcelReaderUtil(excelFileLocation, "accounts", 1,0);
        System.out.println(excelReaderUtil.getCellValue(1, 0));
        System.out.println(excelReaderUtil.getCellValue(1, 1));
        System.out.println(excelReaderUtil.getCellValue(2, 0));
        System.out.println(excelReaderUtil.getCellValue(2, 1));
    }
}
