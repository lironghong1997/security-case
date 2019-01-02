package com.securitydemo.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
* @version:1.0.0
* @author: lironghong
* @date: 2018/12/31 15:57
* @description:
 * 有些版本不要求这样子做, 所以就不会有该问题的发生,
 * 我现在用的是5.0版本,强制要求提供一个,所以我们就给一个PasswordEncoder给他.
 * 我们也可以使用Spring自带的PasswordEncoder.为了方便起见,我们以明文形式存密码在后台,故使用自定义PasswordEncoder
*/
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(charSequence);
        return encode;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        boolean matches = bCryptPasswordEncoder.matches(charSequence, s);
        return matches;
    }
}
