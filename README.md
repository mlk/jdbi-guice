[![Build Status](https://travis-ci.org/mlk/jdbi-guice.svg?branch=master)](https://travis-ci.org/mlk/jdbi-guice) [![codecov.io](https://codecov.io/github/mlk/jdbi-guice/coverage.svg?branch=master)](https://codecov.io/github/mlk/jdbi-guice?branch=master)

# jdbi-guice
A module to marry together JDBI bound resources to the Guice dependency injection framework.
```
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

````


It is on Maven, so use it with:
```
<dependency>
  <groupId>com.github.mlk</groupId>
  <artifactId>jdbi-guice</artifactId>
  <version>1.0.0</version>
</dependency>
```
