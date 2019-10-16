package com.learning.myproject.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ApiModel("用户实体")
public class UserDTO {

    @ApiModelProperty(value = "id",example = "123",notes = "新增请忽略")
    private Integer id;

    @ApiModelProperty(value = "用户名",required = true)
    private String username;

    @ApiModelProperty(value = "密码",required = true)
    private String password;

    @JsonIgnore
    private String salt;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像url")
    private String avatar;

    @ApiModelProperty(value = "手机",required = true)
    private String phone;

    @ApiModelProperty(value = "邮箱",required = true)
    private String email;

    @ApiModelProperty(value = "真实姓名")
    private String realname;

    @ApiModelProperty(value = "性别",required = true)
    private String sex;

    @ApiModelProperty(value = "集团id",required = true)
    private Integer groupId;

    @ApiModelProperty(value = "公司id",required = true)
    private Integer companyId;

    @ApiModelProperty(value = "部门id",required = true)
    private Integer departmentId;

    @ApiModelProperty(value = "职位",required = true)
    private String position;

    @ApiModelProperty(value = "微信")
    @JSONField(name = "weiChat")
    private String weiChat;

    @ApiModelProperty(value = "电话")
    private String tel;

    @ApiModelProperty("qq")
    private String qq;

    @ApiModelProperty(value = "授信额度",required = true)
    private BigDecimal creditLimit;

    @ApiModelProperty(value = "身份证号",required = true)
    private String idNo;

    @ApiModelProperty(value = "出生日期",required = true)
    private LocalDate birthDate;

    @ApiModelProperty(value = "入职日期",required = true)
    private LocalDate joinDate;

    @ApiModelProperty(value = "限额",required = true)
     private BigDecimal  limit;

    @ApiModelProperty(value = "汇报对象",required = true)
    private String reportTo;

    @ApiModelProperty(value = "就业时间",required = true)
    private LocalDate jobDate;

    @ApiModelProperty(value = "员工编号",required = true)
    private String number;

    @ApiModelProperty(value = "用工类型id",example = "123",required = true ,notes = "1-小时工，2-临时工，3-全职")
    private Integer jobCateId;

    @ApiModelProperty(value = "角色id")
    private Integer roleId;

    @ApiModelProperty(value = "是否是分销商 0 是 1 否",required = true)
    private Byte isDistributor;

    @ApiModelProperty(value = "分销商id")
    private  Integer distributorId;

    @ApiModelProperty(value = "产品相关 上月基数")
    private Integer projectBase;

    @ApiModelProperty(value = "员工等级")
    private Integer level;

    @ApiModelProperty("创建人id")
    private Integer createUserId;

    @ApiModelProperty(value = "创建时间")
    @JsonIgnore
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @JsonIgnore
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "产品相关 登录系数 2位小数")
    private Double projectFactor;

    @ApiModelProperty(value = "产品相关 本月限额")
    private Double projectLimit;
}
