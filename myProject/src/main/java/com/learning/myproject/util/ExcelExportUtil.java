package com.learning.myproject.util;


import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelExportUtil {

    public enum ExcelType {
        HSSF, XSSF
    }

    private ExcelExportUtil() {
    }

    private static class ExcelUtilHolder {
        private final static ExcelExportUtil INSTANCE = new ExcelExportUtil();
    }

    public static ExcelExportUtil getInstance() {
        return ExcelUtilHolder.INSTANCE;
    }

    private ExcelType type;

    private Workbook workbook;


    public ExcelExportUtil newWorkbook(ExcelType type) {
        this.type = type;
        switch (this.type) {
            case XSSF:
                this.workbook = new XSSFWorkbook();
                break;
            case HSSF:
                this.workbook = new HSSFWorkbook();
                break;
            default:
                this.workbook = new HSSFWorkbook();
        }
        return this;
    }


    public ExcelExportUtil createExcelSheet(String sheetName, List<String> headers, List<String> fields, List<Map> contentList) {
        Sheet sheet = this.workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(18);
        Row row = sheet.createRow(0);
        row.setHeight((short)600);
        Cell cell;
        for (int i = 0; i < headers.size(); i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(headers.get(i));
        }
        // 内容
        for (int i = 0; i < contentList.size(); i++) {
            row = sheet.createRow(i + 1);
            row.setHeight((short)600);
            Map content = contentList.get(i);
            for (int j = 0; j < fields.size(); j++) {

                row.createCell(0).setCellValue(i + 1);
                if (null != content.get(fields.get(j))) {
                    row.createCell(j).setCellValue(String.valueOf(content.get(fields.get(j))));
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        return this;
    }


    public ExcelExportUtil createExcelProductInquirySheet(String sheetName, List<String> headers, List<String> fields, List<Map> contentList) {
        Sheet sheet = this.workbook.createSheet(sheetName);
        //列宽
        sheet.setDefaultColumnWidth(16);
        Row row = sheet.createRow(0);
        row.setHeight((short)600);
        //表头
        Cell cell;
        for (int i = 0; i < headers.size(); i++) {
            cell = row.createCell((short) i);
            cell.setCellValue(headers.get(i));
        }

        //生产的Excel总行数
        Integer listRow = 1;

        // 内容
        for (int i = 0; i < contentList.size(); i++) {

            Map content = contentList.get(i);
            //当前一行数据，获取需要合并的单元格个数
            Integer listRowMerge = getListRowMerge(fields, content);

            //创建行起始位置
            int rowStart = listRow;
            List<Row> rowList = new ArrayList<>();
            for (int r = 0; r < listRowMerge; r++) {
                //创建一行
                rowList.add(sheet.createRow(listRow));
                listRow++;
            }
            //创建行结束位置
            int rowEnd = listRow - 1;

            for (int j = 0; j < fields.size(); j++) {
                //序号
                rowList.get(0).createCell(0).setCellValue(i + 1);

                //新建单元格
                Boolean result = content.get(fields.get(j)) instanceof List;
                if (!result) {
                    //非列表，合并单元格
                    if (listRowMerge > 1) {
                        sheet.addMergedRegion(new CellRangeAddress(rowStart, rowEnd, j, j));
                    }
                    //存入数据
                    if (null != content.get(fields.get(j))) {
                        rowList.get(0).createCell(j).setCellValue(String.valueOf(content.get(fields.get(j))));
                    } else {
                        rowList.get(0).createCell(j).setCellValue("");
                    }
                } else {
                    String json = String.valueOf(content.get(fields.get(j)));
                    json = json.substring(1, json.length() - 1);
                    if (!"null".equals(json)) {
                        String[] strs = json.split(",");
                        List<String> list = Arrays.asList(strs);
                        if (list.size() > 0) {
                            // TODO: 2019-02-25
                            for (int k = 0; k < list.size(); k++) {
                                //创建单个单元格
                                if (null != list.get(k)) {
                                    rowList.get(k).createCell(j).setCellValue(String.valueOf(list.get(k)).trim());
                                } else {
                                    rowList.get(k).createCell(j).setCellValue("");
                                }
                            }
                        }
                    }
                }
            }
        }
        return this;
    }

    /**
     * 获取当前行需要合并的单元格个数
     *
     * @param fields
     * @param content
     * @return
     */
    private Integer getListRowMerge(List<String> fields, Map content) {
        Integer listRowMerge = 1;
        for (int j = 0; j < fields.size(); j++) {
            Boolean result = content.get(fields.get(j)) instanceof List;
            if (result) {
                String json = String.valueOf(content.get(fields.get(j)));
                json = json.substring(1, json.length() - 1);
                if (!"null".equals(json)) {
                    String[] strs = json.split(",");
                    List<String> list = Arrays.asList(strs);
                    if (list.size() > 0 && list.size() > listRowMerge) {
                        listRowMerge = list.size();
                    }
                }
            }
        }
        return listRowMerge;
    }

    public void export(HttpServletResponse response, String excelName) throws IOException {
        switch (this.type) {
            case XSSF:
                excelName += ".xlsx";
                break;
            case HSSF:
                excelName += ".xls";
                break;
            default:
                excelName += ".xls";
        }
        response.setCharacterEncoding("UTF-8");
        //默认Excel名称
        response.setHeader("Content-disposition", "attachment;filename=" + excelName);
        response.setHeader("Content-type", "application/octet-stream;charset=UTF-8");
        response.flushBuffer();
        this.workbook.write(response.getOutputStream());
    }

    /**
     * @param dropData  下拉数据集(第几列:下拉的字段)
     * @param headers   表头名称
     * @param sheetName sheet名称
     * @author xuk
     * @date 2019-01-02
     */
    public ExcelExportUtil createExcelTemplate(String sheetName, String[] headers,
                                               Map<Integer, List<String>> dropData) {
        Workbook wb = this.workbook;
        //表头样式
        CellStyle style = wb.createCellStyle();
        // 创建一个居中格式
        style.setAlignment(HorizontalAlignment.CENTER);
        //字体样式
        Font fontStyle = wb.createFont();
        fontStyle.setFontName("微软雅黑");
        fontStyle.setFontHeightInPoints((short) 12);
        style.setFont(fontStyle);

        //新建sheet
        Sheet sheet = wb.createSheet(sheetName);


        //生成sheet内容
        Row rowFirst = sheet.createRow(0);
        //写标题
        for (int i = 0; i < headers.length; i++) {
            //获取第一行的每个单元格
            Cell cell = rowFirst.createCell(i);
            //设置每列的列宽
            sheet.setColumnWidth(i, 4000);
            rowFirst.setHeight((short) 100);
            //加样式
            cell.setCellStyle(style);
            //往单元格里写数据
            cell.setCellValue(headers[i]);
        }
        dropData.forEach((k, v) -> {
            // 设置第一列的1-10行为下拉列表
            CellRangeAddressList regions = new CellRangeAddressList(1, 50000, k - 1, k - 1);
            // 创建下拉列表数据
            // 绑定
            DataValidationHelper helper = sheet.getDataValidationHelper();
            DataValidationConstraint explicitListConstraint = helper.createExplicitListConstraint(v.toArray(new String[]{}));
            DataValidation validation = helper.createValidation(explicitListConstraint, regions);
            if (validation instanceof XSSFDataValidation) {
                validation.setSuppressDropDownArrow(true);
                validation.setShowErrorBox(true);
            } else {
                validation.setSuppressDropDownArrow(true);
            }
            sheet.addValidationData(validation);
        });
        return this;
    }

    /**
     *
     * @param clazz  要导出的对象
     * @param datas  要导出的数据
     * @param sheetName  sheet的名字
     * @param response  获取导出流
     */
    public static void outputExcel(Class clazz, List datas, String sheetName, HttpServletResponse response) {
        Field[] declaredFields = clazz.getDeclaredFields();
        //excel格式处理
        if (declaredFields != null) {
            //对应实体类中的字段
            List<String> fields = new ArrayList<>();
            //第一行  属性说明行
            List<String> headers = new ArrayList<>();
            //数据
            List<Map> list = new ArrayList<>();
            for (Field declaredField : declaredFields) {
                fields.add(declaredField.getName());
                ApiModelProperty annotation = declaredField.getAnnotation(ApiModelProperty.class);
                headers.add(annotation.value());
            }

            //数据处理
            datas.forEach(data -> {
                HashMap<String, Object> map = new HashMap<>(2 << 3);
                for (Field declaredField : declaredFields) {
                    String name = declaredField.getName();
                    if (name != null && name != "") {
                        name = name.substring(0, 1).toUpperCase() + name.substring(1);
                    }
                    try {
                        map.put(declaredField.getName(),data.getClass().getMethod("get"+name).invoke(data));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                list.add(map);
            });
            //导出excel
            try {
                ExcelExportUtil.getInstance().newWorkbook(ExcelExportUtil.ExcelType.XSSF)
                        .createExcelSheet(sheetName, headers, fields, list)
                        .export(response, clazz.getName() +
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
