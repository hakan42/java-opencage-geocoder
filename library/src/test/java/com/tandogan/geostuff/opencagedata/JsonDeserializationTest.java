package com.tandogan.geostuff.opencagedata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import com.tandogan.geostuff.opencagedata.entity.OpencageResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.StringBuilderWriter;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class JsonDeserializationTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonDeserializationTest.class);

    private static final double LATLON_DELTA = 0.1;

    private ObjectMapper mapper;

    @Before
    public void setUp()
    {
        mapper = new ObjectMapper();
    }

    @Test
    public void testGermering() throws Exception
    {
        String data = loadData("de-by-germering.json");

        GeocodeResponse response = mapper.readValue(data, GeocodeResponse.class);
        assertNotNull(response);
        assertNotEquals(0, response.getResults().size());

        OpencageResult result = response.getResults().get(0);
        assertNotNull(result);
        assertNotEquals(0, result.getConfidence());
        assertEquals("Germering", result.getComponents().getCity());
        assertEquals(48.134, result.getGeometry().getLatitude(), LATLON_DELTA);
        assertEquals(11.365, result.getGeometry().getLongitude(), LATLON_DELTA);
    }

    @Test
    public void testAntalyaKas() throws Exception
    {
        String data = loadData("tr-antalya-kas.json");

        GeocodeResponse response = mapper.readValue(data, GeocodeResponse.class);
        assertNotNull(response);
        assertNotEquals(0, response.getResults().size());

        OpencageResult result = response.getResults().get(0);
        assertNotNull(result);
        assertNotEquals(0, result.getConfidence());
        assertEquals("Kaş", result.getComponents().getCounty());
        assertEquals(36.375, result.getGeometry().getLatitude(), LATLON_DELTA);
        assertEquals(29.603, result.getGeometry().getLongitude(), LATLON_DELTA);
    }

    @Test
    public void testAntalyaSerik() throws Exception
    {
        String data = loadData("tr-antalya-serik.json");

        GeocodeResponse response = mapper.readValue(data, GeocodeResponse.class);
        assertNotNull(response);
        assertNotEquals(0, response.getResults().size());

        OpencageResult result = response.getResults().get(0);
        assertNotNull(result);
        assertNotEquals(0, result.getConfidence());

        // Bad input data, should have been City
        assertEquals("Serik", result.getComponents().getCounty());
        assertEquals(37.102, result.getGeometry().getLatitude(), LATLON_DELTA);
        assertEquals(30.998, result.getGeometry().getLongitude(), LATLON_DELTA);
    }

    @Test
    public void testIspartaKeciborlu() throws Exception
    {
        String data = loadData("tr-isparta-keciborlu.json");

        GeocodeResponse response = mapper.readValue(data, GeocodeResponse.class);
        assertNotNull(response);
        assertNotEquals(0, response.getResults().size());

        OpencageResult result = response.getResults().get(0);
        assertNotNull(result);
        assertNotEquals(0, result.getConfidence());

        // Bad input data, should have been City
        assertEquals("Keçiborlu", result.getComponents().getCounty());
        assertEquals(37.944, result.getGeometry().getLatitude(), LATLON_DELTA);
        assertEquals(30.300, result.getGeometry().getLongitude(), LATLON_DELTA);
    }

    private String loadData(String name)
    {
        String data = null;

        InputStream reader = getClass().getClassLoader().getResourceAsStream(name);

        StringBuilder output = new StringBuilder();
        StringBuilderWriter writer = new StringBuilderWriter(output);

        try
        {
            IOUtils.copy(reader, writer);
        }
        catch (IOException ioe)
        {
            LOGGER.error("While loading test data", ioe);
        }

        data = writer.toString();

        return data;
    }
}
