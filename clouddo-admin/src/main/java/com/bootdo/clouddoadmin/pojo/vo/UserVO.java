package com.bootdo.clouddoadmin.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserVO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户真实姓名
      */
    private String name;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 部门名字
     */
    private String deptName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;

    /**
     * 创建用户id
     */
    private Long userIdCreate;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtModified;

    /**
     * 角色
     */
    private List<Long> roleIds;

    /**
     * 性别
     */
    private Long sex;

    /**
     * 出生日期
     */
    private Date birth;

    /**
     * 图片ID
     */
    private Long picId;

    /**
     * 现居住地
     */
    private String liveAddress;

    /**
     * 爱好
     */
    private String hobby;

    /**
     * 省份
     */
    private String province;
    /**
     * 所在城市
     */
    private String city;

    /**
     * 所在地区
     */
    private String district;

}
