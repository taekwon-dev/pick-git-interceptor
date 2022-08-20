package com.youn.pickgitinterceptor.config.auth_interceptor_register.register_type;

import com.youn.pickgitinterceptor.authentication.presentation.interceptor.PathMatchInterceptor;
import org.springframework.http.HttpMethod;

public interface StorageForRegisterType {

    void appendTo(PathMatchInterceptor include);
    boolean isSatisfiedBy(RegisterType registerType);
    void put(String key, HttpMethod value);
    RegisterType getType();
}
