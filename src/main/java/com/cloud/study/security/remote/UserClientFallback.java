package com.cloud.study.security.remote;

import com.cloud.study.domain.Response;
import com.cloud.study.security.domain.HrDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: userClientFallback
 * @author: dqq
 * @date: 2020/9/28 10:29
 */
@Slf4j
@Component
public class UserClientFallback implements UserClient{

    @Override
    public Response<HrDto> getEpUserByName(String userName) {
        Response response = new Response();
        response.failure("服务已熔断");
        System.out.println("**********************************************");
        System.out.println(userName);
        System.out.println("**********************************************");
        return response;
    }
}
