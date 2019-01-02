package com.securitydemo.config;

import com.securitydemo.domain.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
* @version:1.0.0
* @author: lironghong
* @date: 2018/12/31 15:27
* @description: 
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserService myUserService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //spring security 基于内存的验证(可以提供多个用户)
       auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("admin").password("123456").roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("zhangsan").password("123456").roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("user").password("user").roles("USER");

       //自定义用户处理
        //auth.userDetailsService(myUserService).passwordEncoder(new MyPasswordEncoder());

        //默认数据库管理(如果使用这种需要使用官方给定的表结构)
        /*auth.jdbcAuthentication().usersByUsernameQuery("")
                .authoritiesByUsernameQuery("")
                .passwordEncoder(new MyPasswordEncoder());*/
    }

    //http请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //项目根路径放行
                .antMatchers("/").permitAll()
                //其他请求都要认证
                .anyRequest().authenticated()
                .and()
                //允许注销
                .logout().permitAll()
                .and()
                //允许表单登录
                .formLogin();
        //关闭默认csrf认证
        http.csrf().disable();
    }

    //web资源
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**","/css/**","/images/**");
    }
}
