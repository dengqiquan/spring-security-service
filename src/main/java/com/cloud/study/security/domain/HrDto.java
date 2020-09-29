package com.cloud.study.security.domain;


import com.cloud.study.entity.Hr;
import lombok.Data;

import java.util.List;

/**
 * @description: HrDto对象
 * @author: dqq
 * @date: 2020/9/28 11:16
 */
@Data
public class HrDto extends Hr {
    /**
     * 角色列表
     */
    private List<RoleDto> roleDtoList;

}
