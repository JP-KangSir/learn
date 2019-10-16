package com.learning.myproject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("`user`")
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    @TableField("id")
    private Integer id;
    
    @TableField("username")
    private String username;
    
    @TableField("password")
    private String password;
    
    @TableField("salt")
    private String salt;
    
    @TableField("nickname")
    private String nickname;
    
    @TableField("phone")
    private String phone;
    
    @TableField("email")
    private String email;
    
    @TableField("avatar")
    private String avatar;
    
    @TableField("realname")
    private String realname;
    
    @TableField("sex")
    private String sex;
    
    @TableField("groupid")
    private Integer groupId;
    
    @TableField("companyid")
    private Integer companyId;
    
    @TableField("departmentid")
    private Integer departmentId;
    
    @TableField("position")
    private String position;
    
    @TableField("reportto")
    private String reportTo;
    
    @TableField("joinDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime joinDate;
    
    @TableField("jobDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime jobDate;
    
    @TableField("number")
    private String number;
    
    @TableField("roleid")
    private Integer roleId;
    
    @TableField("`level`")
    private String level;
    
    @TableField("`jobcateid`")
    private Integer jobCateId;
    
    @TableField("`creditlimit`")
    private BigDecimal creditLimit;
    
    @TableField("`limit`")
    private BigDecimal limit;
    
    @TableField("birthDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime birthDate;
    
    @TableField("idno")
    private String idno;
    
    @TableField("tel")
    private String tel;
    
    @TableField("qq")
    private String qq;
    
    @TableField("weichat")
    private String weichat;
    
    @TableField("lastloginip")
    private String lastLoginIp;

    @TableField("lastlogintime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastLoginTime;

    @TableField("createUserId")
    private Integer createUserId;

    @TableField("createtime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createTime;
    
    @TableField("updatetime")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updateTime;
    
    @TableField("`status`")
    private Byte status;

    @TableField("canLogin")
    private Byte canLogin;
    
    @TableField("remark")
    private String remark;
    
//    是否是分销商 0 是 1 否
    @TableField("isDistributor")
    private Byte isDistributor;

    @TableField("distributorId")
    private  Integer distributorId;
    
    //产品相关 上月基数
    @TableField("projectBase")
    private Integer projectBase;
    
    //产品相关 登录系数 2位小数
    @TableField("projectFactor")
    private Double projectFactor;
    
    //产品相关 本月限额
    @TableField("projectLimit")
    private Double projectLimit;

}