package com.bootdo.clouddoadmin.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色
 * @author bootdo 1992lcg@163.com
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleVO {

    private Long id;

    private String name;

    private boolean checked;

}
