package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.EntityComponentScanMarker;
import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestOperations;

import static org.junit.Assert.assertNull;

@ComponentScan(basePackageClasses = {EntityComponentScanMarker.class, RepositoryComponentScanMarker.class})
public class GeocodeRepositoryTest
{
    @Mock
    RestOperations restTemplate;

    GeocodeRepositoryImpl testable;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        testable = new GeocodeRepositoryImpl(restTemplate);
        testable.setApiKey("4711");
        testable.setUrlBase("http://api.opencagedata.com/geocode/v1/json");
    }

    @Test
    public void testFoo()
    {
        GeocodeResponse result = testable.query("foo");
        assertNull(result);
    }
}
