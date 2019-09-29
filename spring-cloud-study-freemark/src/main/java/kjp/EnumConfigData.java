/*
 * 软件版权: 杭州瑞可科技有限公司
 * 修改记录:
 * 修改日期     修改人员  修改说明
 * ========    =======  ============================================
 * 2019/9/29  regtech  新增
 * ========    =======  ============================================
 */
package kjp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 功能说明: 模板生成配置项
 * 开发人员: regtech regtech@hzregtech.com <br>
 * 开发时间: 2019/9/29 <br>
 * 功能描述: 填写自身的数据集
 */

@AllArgsConstructor
@NoArgsConstructor
public enum EnumConfigData {
  AUTHOR("作者","kjp"),

  DRIVER_NAME("数据库驱动","com.mysql.jdbc.Driver"),
  USERNAME("数据库账号","shouhu"),
  PASSWORD("数据库密码","asdf1234"),
  URL("数据库连接地址","jdbc:mysql://127.0.0.0:3306/190829_olk?characterEncoding=utf8"),

  OUTPUT_DIRECTORY("输出目录","D://代码生成器"),

  PACKET_PATH("包路径","com.kjp"),
  CONTROLLER("控制器包名","controller"),

  //如不需要策略 isAllow修改为false    !!!!注意 “需要生成的表” 和“排除生成的表” 是互斥关系，且选择这两个"要操作的表前缀"自动默认为false
  TABLE_PREFIX("要操作的表前缀","",new String[]{"lrk_", "base_"},true),
  INCLUDE("需要生成的表","",new String[] { "msc_exception" },true),
  EXCLUDE("排除生成的表","",new String[] { "msc_exception" },false)


  ;
  /**
   * 数据说明
   */
  private String explain;
  /**
   * Object类数据
   */
  private Object data;
  /**
   * 数组类数据
   */
  private String[] strings;
  /**
   * 属性是否生效
   */
  private Boolean isAllow;


  public String getExplain() {
    return explain;
  }

  public Object getData() {
    return data;
  }

  public String[] getStrings() {
    return strings;
  }

  public Boolean getAllow() {
    return isAllow;
  }

  EnumConfigData(String explain, String[] strings) {
    this.explain = explain;
    this.strings = strings;
  }

  EnumConfigData(String explain, Object data) {
    this.explain = explain;
    this.data = data;
  }
}