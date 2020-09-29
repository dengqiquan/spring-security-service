package com.cloud.study.security.config;

import com.alibaba.fastjson.JSON;
import com.cloud.study.domain.Response;
import com.cloud.study.security.domain.AuthorizationUser;
import com.cloud.study.security.domain.HrDto;
import com.cloud.study.security.enums.ErrorCodeEnum;
import com.cloud.study.security.exception.SecurityGlobalException;
import com.cloud.study.security.remote.UserClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @author: dqq
 * @date: 2020/9/28 12:18
 */
@Service
public class UserDetailService implements UserDetailsService {

    @Resource
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String inputUser) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(inputUser)){
            throw new SecurityGlobalException(ErrorCodeEnum.ERROR_ACCOUNT);
        }
        AuthorizationUser authorizationUser = JSON.parseObject(inputUser, AuthorizationUser.class);
        if (authorizationUser == null){
            throw new SecurityGlobalException(ErrorCodeEnum.EXCHANGE_JSON_EXCEPTION);
        }
        if(StringUtils.isEmpty(authorizationUser.getUsername())){
            throw new SecurityGlobalException(ErrorCodeEnum.ERROR_USERNAME);
        }
        if(StringUtils.isEmpty(authorizationUser.getPassword())){
            throw new SecurityGlobalException(ErrorCodeEnum.ERROR_PASSWORD);
        }
        Response<HrDto> userResponse = userClient.getEpUserByName(authorizationUser.getUsername());
        if(userResponse.getData() == null){
            throw new SecurityGlobalException(ErrorCodeEnum.NOT_EXIST_ACCOUNT);
        }
        HrDto hrDto = userResponse.getData();
        UserDetails userDetails = createUserDetails(hrDto);
        return userDetails;
    }


    /**
     * 转换生成授权对象
     * @param hrDto
     * @return
     */
    private UserDetails createUserDetails(HrDto hrDto) {
        AuthorizationUser authorizationUser = new AuthorizationUser(hrDto.getId(),hrDto.getUsername(),hrDto.getPassword(),hrDto.getRoleDtoList());
        return authorizationUser;
    }
}
