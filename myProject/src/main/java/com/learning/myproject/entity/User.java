/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/9/30  regtech  新增
 * ========    =======  ============================================
 */
package com.learning.myproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能说明:
 * 开发人员: regtech regtech@hzregtech.com <br>
 * 开发时间: 2019/9/30 <br>
 * 功能描述: 写明作用，调用方式，使用场景，以及特殊情况<br>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  /**
   * 用户名
   */
  private String username;

  /**
   * 密码
   */
  private String password;
}