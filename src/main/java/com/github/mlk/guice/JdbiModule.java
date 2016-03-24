package com.github.mlk.guice;

import org.skife.jdbi.v2.DBI;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class JdbiModule extends ExternalCreationModule {

    private final List<String> packagesToScan;
    private final Function<Class<?>, ?> function;

    JdbiModule(Function<Class<?>, ?> function, List<String> packagesToScan) {
        super(function);
        this.packagesToScan = packagesToScan;
        this.function = function;
    }

    @Override
    protected void configure() {
        requestInjection(function);
        for(String scanPackage : packagesToScan) {
            scan(scanPackage);
        }
    }

    public static JdbiModuleBuilder builder() {
        return new JdbiModuleBuilder();
    }

    public static class JdbiModuleBuilder {
        private DBI builder;
        private final List<String> packages = new ArrayList<>();

        public JdbiModuleBuilder withBuilder(DBI builder) {
            this.builder = builder;
            return this;

        }

        public JdbiModuleBuilder scan(String packageToScan) {
            packages.add(packageToScan);
            return this;
        }

        public JdbiModuleBuilder scan(String... packagesToScan) {
            for(String packageToScan : packagesToScan) {
                packages.add(packageToScan);
            }
            return this;
        }

        public JdbiModule build() {
            return new JdbiModule(new JdbiSuppler(builder), packages);
        }
    }
}
