/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/9/30  regtech  新增
 * ========    =======  ============================================
 */
package com.learning.myproject.controller.login;

import com.learning.myproject.config.JwtHelper;
import com.learning.myproject.entity.Authentication;
import com.learning.myproject.entity.User;
import com.learning.myproject.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 功能说明:
 * 开发人员: regtech regtech@hzregtech.com <br>
 * 开发时间: 2019/9/30 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Api("登录模块")
@RestController
@RequestMapping("/login")
public class LoginControlle {

  @ApiOperation("（测试）获取token")
  @GetMapping("getToken")
  public String getToken(){
    return JwtHelper.generateToken(new UsernamePasswordToken("kjp","123456"));
  }

  @ApiOperation("用户登录")
  @PostMapping("/in")
  public Result login(@RequestBody Authentication user, HttpServletRequest request) {
    //String ip = request.getRemoteAddr();
    UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
    SecurityUtils.getSubject().login(token);
    Object jwt = JwtHelper.generateToken(token);
    return Result.loginOk((String) jwt);
  }

  @ApiOperation("用户注销")
  @PostMapping("/out")
  public Result logout() {
    SecurityUtils.getSubject().logout();
    return Result.ok("注销成功");
  }
}