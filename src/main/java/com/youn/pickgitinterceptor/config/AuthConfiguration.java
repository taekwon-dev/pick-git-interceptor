package com.youn.pickgitinterceptor.config;

import com.youn.pickgitinterceptor.authentication.presentation.interceptor.AuthenticationInterceptor;
import com.youn.pickgitinterceptor.authentication.presentation.interceptor.IgnoreAuthenticationInterceptor;
import com.youn.pickgitinterceptor.authentication.presentation.interceptor.PathMatchInterceptor;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.AutoAuthorizationInterceptorRegister;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.UriParser;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.register_type.AuthenticateStorageForRegisterType;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.register_type.IgnoreAuthenticateStorageForRegisterType;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.register_type.StorageForRegisterType;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.scanner.ControllerScanner;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.scanner.ForGuestScanner;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.scanner.ForLoginUserScanner;
import com.youn.pickgitinterceptor.config.auth_interceptor_register.scanner.packgage_scanner.PackageScanner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class AuthConfiguration implements WebMvcConfigurer {

    private static final String PACKAGE = "com.youn.pickgitinterceptor";

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        PathMatchInterceptor authenticationInterceptor =
                new PathMatchInterceptor(authenticationInterceptor());
        PathMatchInterceptor ignoreAuthenticationInterceptor =
                new PathMatchInterceptor(ignoreAuthenticationInterceptor());

        AutoAuthorizationInterceptorRegister autoAuthorizationInterceptorRegister =
            AutoAuthorizationInterceptorRegister.builder()
                .storageForRegisterTypes(getStorageForRegisterTypes())
                .authenticationInterceptor(authenticationInterceptor)
                .ignoreAuthenticationInterceptor(ignoreAuthenticationInterceptor)
                .uriParser(getUriParser())
                .build();

        autoAuthorizationInterceptorRegister.execute();

        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**");

        registry.addInterceptor(ignoreAuthenticationInterceptor)
                .addPathPatterns("/**");
    }

    private AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    private IgnoreAuthenticationInterceptor ignoreAuthenticationInterceptor() {
        return new IgnoreAuthenticationInterceptor();
    }

    private List<StorageForRegisterType> getStorageForRegisterTypes() {
        return List.of(
                new AuthenticateStorageForRegisterType(),
                new IgnoreAuthenticateStorageForRegisterType()
        );
    }

    private UriParser getUriParser() {
        return new UriParser(
                new ControllerScanner(parseClassesNames()),
                new ForGuestScanner(),
                new ForLoginUserScanner()
        );
    }

    private List<String> parseClassesNames() {
        PackageScanner packageScanner = new PackageScanner(PACKAGE);
        return packageScanner.getAllClassNames();
    }

}
