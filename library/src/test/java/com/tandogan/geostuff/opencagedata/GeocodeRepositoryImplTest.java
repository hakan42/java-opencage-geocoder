package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeEntityComponentScanMarker;
import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertNull;

@ComponentScan(basePackageClasses = {GeocodeEntityComponentScanMarker.class, GeocodeRepositoryComponentScanMarker.class})
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

    RestTemplate restTemplate;

    GeocodeRepositoryImpl testable;

    MockRestServiceServer mockServer;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        restTemplate = new RestTemplate();

        mockServer = MockRestServiceServer.createServer(restTemplate);

        testable = new GeocodeRepositoryImpl(restTemplate);
        testable.setApiKey("test-4711");
        testable.setUrlBase("http://api.opencagedata.com/geocode/v1/json");
    }

    @Test
    @Ignore("Mocking does not work satisfactorily")
    public void testGermering()
    {
        String data = "{ \"id\" : \"42\", \"name\" : \"Holiday Inn\"}";

        // mockServer.
        // expect(requestTo(startsWith("http"))).
        // andExpect(method(HttpMethod.GET)).
        // andRespond(withSuccess(data, MediaType.APPLICATION_JSON));

        GeocodeResponse result = testable.query("foo");
        assertNull(result);
    }
}
