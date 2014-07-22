package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.EntityComponentScanMarker;
import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestOperations;

import static org.junit.Assert.assertNull;

@ComponentScan(basePackageClasses = {EntityComponentScanMarker.class, RepositoryComponentScanMarker.class})
@PropertySources({
        @PropertySource("classpath:/opencagedata-default.properties"),
        @PropertySource(value = "file:${HOME}/.config/opencagedata", ignoreResourceNotFound = true)
})
@RunWith(MockitoJUnitRunner.class)
public class GeocodeRepositoryImplTest
{
    @Autowired
    private Environment env;

    @Mock
    RestOperations restTemplate;

    @InjectMocks
    GeocodeRepositoryImpl testable;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        testable.setApiKey(env.getProperty("${API_KEY}"));
        testable.setUrlBase("http://api.opencagedata.com/geocode/v1/json");
    }

    @Test
    public void testFoo()
    {
        GeocodeResponse result = testable.query("foo");
        assertNull(result);
    }
}
