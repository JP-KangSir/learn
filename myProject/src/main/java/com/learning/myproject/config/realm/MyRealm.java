/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/9/30  regtech  新增
 * ========    =======  ============================================
 */
package com.learning.myproject.config.realm;

import com.learning.myproject.entity.User;
import com.learning.myproject.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能说明:
 * 开发人员: regtech regtech@hzregtech.com <br>
 * 开发时间: 2019/9/30 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
public class MyRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;

  /**
   * 授权(验证权限时调用)
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    return null;
  }

  /**
   * 认证(登录时调用)
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    String accessToken = (String) authenticationToken.getPrincipal();
    User user = userService.getUser();

    if (user == null) {
      throw new UnknownAccountException();
    }
    //当前登录用户信息(后续补上redis缓存)
    return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
  }
}