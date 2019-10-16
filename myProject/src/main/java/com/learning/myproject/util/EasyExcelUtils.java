package com.learning.myproject.util;

import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @Date: 2019/3/25 0022
 * @Time: 14:28
 */
public class EasyExcelUtils {

    private static final Logger log = LoggerFactory.getLogger(EasyExcelUtils.class);

    private static final String EXCEL_SUFFIX=".xlsx";

    private static final String SHEEP_INDEX="sheet1";

    private static final String CONTENT_DISPOSITION="Content-disposition";

    private static final String CONTENT_DISPOSITION_VALUES="attachment;filename=";

    private static final String CONTENT_TYPE="Content-type";

    private static final String CONTENT_TYPE_VALUES="application/octet-stream;charset=UTF-8";

    private static final String CLASS="class";

    public static void importExcel(HttpServletResponse response, String fileName, Class model, List list){

        List<ExcelExportEntity> entity = new ArrayList();
        List<Map<String, Object>> data = new ArrayList();
        Field[] fields = model.getDeclaredFields();
        for (Field f : fields) {
            ApiModelProperty apiModelProperty = f.getAnnotation(ApiModelProperty.class);
            JsonIgnore jsonIgnore = f.getAnnotation(JsonIgnore.class);
            if (apiModelProperty != null && jsonIgnore==null) {
                entity.add(new ExcelExportEntity(apiModelProperty.value(), f.getName()));
            }
        }
        list.forEach(e -> {
            Map map = null;
            try {
                map = objectToMap(e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            data.add(map);
        });
        Workbook sheets = cn.afterturn.easypoi.excel.ExcelExportUtil.exportExcel(new ExportParams(fileName, SHEEP_INDEX, ExcelType.XSSF), entity, data);
        try {
            response.reset();
            response.setHeader(CONTENT_DISPOSITION, CONTENT_DISPOSITION_VALUES+model.getSimpleName()+EXCEL_SUFFIX);
            response.setHeader(CONTENT_TYPE, CONTENT_TYPE_VALUES);
            response.flushBuffer();
            sheets.write(response.getOutputStream());
        }catch (IOException e){
            log.error(e.getMessage());
        }
    }

    public static Map<String, Object> objectToMap(Object obj) throws InvocationTargetException, IllegalAccessException, IntrospectionException {
        if (obj == null)return null;
        Map<String, Object> map = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase(CLASS) == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }
        return map;
    }

}
