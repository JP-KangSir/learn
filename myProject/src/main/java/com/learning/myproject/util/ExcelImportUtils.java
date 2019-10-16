package com.learning.myproject.util;

import com.learning.myproject.exception.MyException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @Date: 2019/2/22 0022
 * @Time: 14:28
 */
public class ExcelImportUtils {

    private final static Integer INDEX=0;

    /**
     * 读取出fis
     *
     */

    public static List<String> getDataFromExcel(InputStream fis) throws Exception
    {
        return getDataFromExcel(fis,INDEX);
    }

    public static List<Integer> getDataFromExcelInteger(InputStream fis) throws Exception
    {
        List<Integer> result=new ArrayList<>();
        List<String> dataFromExcel = getDataFromExcel(fis, INDEX);
        dataFromExcel.forEach(e->{
            if (dataFromExcel.indexOf(e)!=0)result.add(Integer.valueOf(e));
        });
        return result;
    }

    public static List<String> getDataFromExcel(InputStream fis,Integer columnNum) throws Exception
    {
        List<String> result=new ArrayList<>();
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(INDEX);
        int totalRowNum = sheet.getLastRowNum();
        for(int i = INDEX ; i <= totalRowNum ; i++)
        {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(columnNum);
            String column="";
            if (cell.getCellType().equals(CellType.NUMERIC)){
                column=Integer.toString((int)cell.getNumericCellValue());
            }else{
                column = cell.getStringCellValue();
            }
            result.add(column);
        }
        return result;
    }

    public static List<List<String>> getAllDataFromExcel(InputStream fis) throws Exception
    {
        List<List<String>> result=new ArrayList<>();
        XSSFWorkbook workbook=new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(INDEX);
        int totalRowNum = sheet.getLastRowNum();
        int columnNum=INDEX;
        for(int i = INDEX ; i <= totalRowNum ; i++)
        {
            List<String> e=new ArrayList<>();
            Row row=sheet.getRow(i);
            if (i==INDEX){
                columnNum=row.getLastCellNum();
            }else{
                if (columnNum!=row.getLastCellNum())throw new MyException("数据格式错误");
            }
            for (int j=INDEX;j<columnNum;j++){
                Cell cell = row.getCell(j);
                String column="";
                if (cell.getCellType().equals(CellType.NUMERIC)){
                    column=Integer.toString((int)cell.getNumericCellValue());
                }else{
                    column = cell.getStringCellValue();
                }
                e.add(column);
            }
            result.add(e);
        }
        return result;
    }

}
