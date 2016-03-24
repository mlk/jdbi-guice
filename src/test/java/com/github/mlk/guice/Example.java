package com.github.mlk.guice;

import com.github.mlk.guice.dao.MyDAO;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.skife.jdbi.v2.DBI;

public class Example {

    public static void main(String... argv) {
        Injector injector = Guice.createInjector(JdbiModule.builder().scan("com.github.mlk.guice.dao").build(),
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(DBI.class).toInstance(new DBI("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"));
                    }
                });
        MyDAO myDAO = injector.getInstance(MyDAO.class);

        myDAO.createSomethingTable();
        myDAO.insert(1, "fred");
        System.out.println(myDAO.findNameById(1));
    }
}
