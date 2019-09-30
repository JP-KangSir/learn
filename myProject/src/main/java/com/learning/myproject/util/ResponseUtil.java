package com.learning.myproject.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.learning.myproject.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: kjp
 */
public class ResponseUtil {

    public static void responseReturn(HttpServletResponse response, Integer status, Result result) throws IOException {
        if (status == null) {
            status = HttpServletResponse.SC_OK;
        }
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(status);
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(result, SerializerFeature.WriteMapNullValue));
        out.flush();
        out.close();
    }
}
