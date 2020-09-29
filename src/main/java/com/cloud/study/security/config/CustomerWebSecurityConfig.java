package com.cloud.study.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @description: security 认证策略信息
 * @author: dqq
 * @date: 2020/9/27 15:44
 */
@Configuration
public class CustomerWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 开启跨域支持
//                .cors().and()
                // 禁用crsf
                .csrf().disable()
                // 授权异常
//                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 不创建会话 jwt（采用无状态的）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                // 排除js,css的拦截
                .antMatchers("/**/*.js","/**/*.css","/**/*.ico")
                .anonymous()
                // 排除登陆的拦截
                .antMatchers("/auth/login")
                .anonymous()
                // 排除管理员登陆的拦截
                .antMatchers("/auth/manager/login")
                .anonymous()
                // 排除模拟用户的拦截
                .antMatchers("/auth/mockup/user")
                .anonymous()
                // 排除退出拦截
                .antMatchers("/auth/logout")
                .anonymous()
                // 排除微信登录拦截
                .antMatchers("/auth/wxlogin")
                .anonymous()
                // 排除微信注册
                .antMatchers("/auth/wxregister")
                .anonymous()
                // 排除app登录拦截
                .antMatchers("/auth/applogin")
                .anonymous()
                // 排除获取验证码
                .antMatchers("/auth/getCode")
                .anonymous()
                // 排除微获取token拦截
                .antMatchers("/auth/wxgettoken")
                .anonymous()
                // 排除接口文档swagger的拦截
                .antMatchers("/swagger-ui.html")
                .anonymous()
                .antMatchers("/swagger-resources/**")
                .anonymous()
                .antMatchers("/webjars/**")
                .anonymous()
                .antMatchers("/*/api-docs")
                .anonymous()
                // 排除银行回调
                .antMatchers("/park/parking/tfPayCallbackPayPark")
                .anonymous()
                // 排除健康检查接口
                .antMatchers("/actuator/**")
                .anonymous()

                .antMatchers("/get/verify/code")
                .anonymous()
                // 所有请求都需要认证
                .anyRequest().authenticated()
                // 防止iframe 造成跨域
                .and().headers().frameOptions().disable();

//        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
}
