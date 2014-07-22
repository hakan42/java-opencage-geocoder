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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@ComponentScan(basePackageClasses = {EntityComponentScanMarker.class, RepositoryComponentScanMarker.class})
@PropertySources({
        @PropertySource("classpath:/opencagedata-default.properties"),
        @PropertySource(value = "file:${HOME}/.config/opencagedata", ignoreResourceNotFound = true)
})
@RunWith(MockitoJUnitRunner.class)
public class GeocodeRepositoryImplTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(GeocodeRepositoryImplTest.class);

    @Autowired
    private Environment env;

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    GeocodeRepositoryImpl testable;

    MockRestServiceServer mockServer;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        mockServer = MockRestServiceServer.createServer(restTemplate);

        testable.setApiKey("foobar-4711");
        testable.setUrlBase("http://api.opencagedata.com/geocode/v1/json");
    }

    @Test
    public void testFoo()
    {
        String data = "{ \"id\" : \"42\", \"name\" : \"Holiday Inn\"}";
        // TODO the mockServer should actually respond to any urls
        mockServer.expect(method(HttpMethod.GET)).andRespond(withSuccess(data, MediaType.APPLICATION_JSON));

        GeocodeResponse result = testable.query("foo");
        assertNull(result);
    }
}
