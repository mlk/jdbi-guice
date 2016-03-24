package com.github.mlk.guice;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.skife.jdbi.v2.DBI;

import java.util.function.Function;

public class JdbiSuppler implements Function<Class<?>, Object> {
    private final DBI dbi;
    private Injector injector;

    JdbiSuppler(DBI dbi) {
        this.dbi = dbi;
    }

    @Inject
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @Override
    public Object apply(Class<?> aClass) {
        DBI dbi = this.dbi;
        if(dbi == null) {
            dbi = injector.getInstance(DBI.class);
        }
        return dbi.onDemand(aClass);
    }
}
