package utilities;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;

public class ExcelUtility {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelUtility(String filePath, String sheetName) {

        try {
            FileInputStream fis = new FileInputStream("src/main/resources/testdata.xlsx");

            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if(sheet==null){
                throw new RuntimeException("Sheet '"+sheetName+"' not found");
            }

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel File:"+filePath,e);
        }
    }

    public int getRowCount() {

        return sheet.getLastRowNum();
    }

    public int getColumnCount() {
        XSSFRow headRow=sheet.getRow(0);
        if(headRow==null){
            return 0;
        }

     return headRow.getLastCellNum();   //return sheet.getRow(0).getLastCellNum();
    }
    public String getCellData(int rowNum, int columnNum){
        XSSFRow row=sheet.getRow(rowNum);
        if(row==null){
            return "";
        }
        XSSFCell cell=row.getCell(columnNum);
        if(cell==null){
            return "";
        }
        return cell.toString().trim();
    }
    public void closeWorkbook(){
        try{
            if(workbook!=null){
                workbook.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }




}
