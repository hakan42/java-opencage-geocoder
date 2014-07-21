package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNull;

public class GeocodeRepositoryTest
{
    GeocodeRepository testable;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        testable = new GeocodeRepositoryImpl();
    }

    @Test
    public void testFoo()
    {
        GeocodeResponse result = testable.query("foo");
        assertNull(result);
    }
}
