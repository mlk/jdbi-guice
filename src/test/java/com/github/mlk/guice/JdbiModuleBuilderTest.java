package com.github.mlk.guice;

import com.github.mlk.guice.thingies.A;
import com.github.mlk.guice.thingies.B;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JdbiModuleBuilderTest {
    @Test
    public void scansEachPackageGiven() {
        Injector injector = Guice.createInjector(new JdbiModule((x) -> null, Collections.singletonList("com.github.mlk.guice.thingies")));
        assertThat(injector.getInstance(A.class), is(nullValue()));
        assertThat(injector.getInstance(B.class), is(nullValue()));
    }
}