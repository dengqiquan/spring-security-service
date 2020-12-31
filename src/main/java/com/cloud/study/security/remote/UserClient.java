package com.cloud.study.security.remote;

import com.cloud.study.domain.Response;
import com.cloud.study.security.domain.HrDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @description: userFeign
 * @author: dqq
 * @date: 2020/9/28 8:56
 */
@FeignClient(name = "cloud-user-service",fallback = UserClientFallback.class,url = "http://localhost:9003")
public interface UserClient {
    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    @RequestMapping(value = "getUser/by/name",method = RequestMethod.GET)
    Response<HrDto> getEpUserByName(@RequestParam("userName") String userName);
}
