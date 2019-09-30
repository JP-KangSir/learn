package com.learning.myproject.config;



import com.learning.myproject.constant.AppConstant;
import com.learning.myproject.constant.JwtConstant;
import com.learning.myproject.exception.ForbidLoginException;
import com.learning.myproject.exception.StatusDiscontinueException;
import com.learning.myproject.exception.TokenNotException;
import com.learning.myproject.result.Result;
import com.learning.myproject.util.ResponseUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能说明:
 * 开发人员: regtech regtech@hzregtech.com <br>
 * 开发时间: 2019/9/30 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)  {
        try {

            executeLogin(request, response);
            return true;
        }catch (TokenNotException e){
            try {
                ResponseUtil.responseReturn((HttpServletResponse) response, AppConstant.ERROR_VALIDATE_FAILURE, Result.error(AppConstant.ERROR_VALIDATE_FAILURE,0, AppConstant.ERROR_VALIDATE_FAILURE_MSG));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }catch (StatusDiscontinueException e){
            try {
                ResponseUtil.responseReturn((HttpServletResponse) response, AppConstant.ERROR_WRONG_ACCOUNT_FORBIDDEN,Result.error(AppConstant.ERROR_WRONG_ACCOUNT_FORBIDDEN,0, AppConstant.ERROR_WRONG_ACCOUNT_FORBIDDEN_MSG));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }catch (ForbidLoginException e){
            try {
                ResponseUtil.responseReturn((HttpServletResponse) response, AppConstant.FORBIDDEN,Result.error(AppConstant.FORBIDDEN,0, AppConstant.FORBIDDEN_MSG));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }catch (Exception e){
            try {
                ResponseUtil.responseReturn((HttpServletResponse) response, AppConstant.ERROR_EXCEPTION,Result.error(AppConstant.ERROR_EXCEPTION,0, AppConstant.ERROR_EXCEPTION_MSG));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws TokenNotException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = httpServletRequest.getHeader(JwtConstant.JWT_AUTHORIZATION);
        if (jwt==null) {
            jwt = httpServletRequest.getHeader(JwtConstant.JWT_TOKEN);
        }
        if (jwt==null) {
            throw new TokenNotException();
        }
        Claims claims = JwtHelper.verifyJwt(jwt);
        if (claims==null || claims.get(JwtConstant.JWT_TOKEN_USERNAME)==null) {
            throw new TokenNotException();
        }
        UsernamePasswordToken token=new UsernamePasswordToken((String) claims.get(JwtConstant.JWT_TOKEN_USERNAME),(String) claims.get(JwtConstant.JWT_TOKEN_PASSWORD));
        getSubject(request,response).login(token);
        return true;
    }

}