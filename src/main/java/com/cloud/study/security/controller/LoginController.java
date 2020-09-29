package com.cloud.study.security.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.study.domain.Response;
import com.cloud.study.security.config.UserDetailService;
import com.cloud.study.security.domain.AuthorizationUser;
import com.cloud.study.security.domain.HrDto;
import com.cloud.study.security.enums.ErrorCodeEnum;
import com.cloud.study.security.exception.SecurityGlobalException;
import com.cloud.study.security.utils.JwtTokenUtils;
import com.cloud.study.security.utils.RedisUtil;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 登录授权
 * @author: dqq
 * @date: 2020/9/28 12:38
 */
@RestController
@RequestMapping("auth")
@Slf4j
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private UserDetailService userDetailService;

    @Resource
    RedisUtil redisUtil;

    @Value("${jwt.expiration}")
    private Long expiration;
    @Resource
    private JwtTokenUtils jwtTokenUtils;

    public static final String TOKEN_FILE = "TOKEN:";

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Response login(@RequestBody AuthorizationUser authorizationUser) {
        logger.info("登录用户信息：" + authorizationUser.toString());
        AuthorizationUser userDetails = null;
        String inputUser = JSON.toJSONString(authorizationUser);
        userDetails = (AuthorizationUser) userDetailService.loadUserByUsername(inputUser);
        if(userDetails == null){
            throw new SecurityGlobalException(ErrorCodeEnum.NOT_EXIST_ACCOUNT);
        }
        if(!userDetails.getPassword().equals(authorizationUser.getPassword())){
            throw new SecurityGlobalException(ErrorCodeEnum.ERROR_PASSWORD);
        }
        HrDto hrDto = new HrDto();
        BeanUtils.copyProperties(userDetails, hrDto);
        hrDto.setPassword(null);
        String token = jwtTokenUtils.generateToken(userDetails);
        redisUtil.set(TOKEN_FILE + token, JSON.toJSONString(hrDto), expiration);
        return new Response().success().data(token);
    }
}
