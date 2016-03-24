package com.github.mlk.guice;

import com.github.mlk.guice.thingies.A;
import com.google.inject.Injector;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import static org.mockito.Mockito.*;

public class JdbiSupplerTest {
    @Test
    public void createsUsingTheGivenBuilder() {
        DBI builder = mock(DBI.class);

        JdbiSuppler subject = new JdbiSuppler(builder);
        subject.apply(A.class);

        verify(builder).onDemand(A.class);
    }

    @Test
    public void whenBuilderIsNullRequestFromInjector() {
        Injector injector = mock(Injector.class);
        DBI builder = mock(DBI.class);
        when(injector.getInstance(DBI.class)).thenReturn(builder);

        JdbiSuppler subject = new JdbiSuppler(null);
        subject.setInjector(injector);

        subject.apply(A.class);

        verify(builder).onDemand(A.class);
    }
}