package com.youn.pickgitinterceptor.config.auth_interceptor_register.scanner.packgage_scanner;

import java.util.HashSet;
import java.util.List;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import static java.util.stream.Collectors.toList;

public class PackageScanner {

    private final String basePackage;

    public PackageScanner(String basePackage) {
        this.basePackage = basePackage;
    }

    public List<String> getAllClassNames() {
        Reflections reflections = new Reflections(
            basePackage,
            new SubTypesScanner(false)
        );
        return new HashSet<>(reflections.getSubTypesOf(Object.class)).stream()
                .map(Class::getName)
                .collect(toList());
    }
}
