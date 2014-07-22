package com.tandogan.geostuff.opencagedata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotNull;

public class DeserializationTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(DeserializationTest.class);

    private ObjectMapper mapper;

    @Before
    public void setUp()
    {
        mapper = new ObjectMapper();
    }

    @Test
    public void testFoo() throws Exception
    {
        String data = "{ \"id\" : \"42\", \"name\" : \"Holiday Inn\"}";

        GeocodeResponse partType = mapper.readValue(data, GeocodeResponse.class);
        assertNotNull(partType);

        LOGGER.debug("Part: {}.", partType);
    }
}
