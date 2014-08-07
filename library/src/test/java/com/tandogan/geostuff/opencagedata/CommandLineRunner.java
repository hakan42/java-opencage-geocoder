package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class CommandLineRunner
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineRunner.class);

    private static final String API_URL = "http://api.opencagedata.com/geocode/v1/json";

    public static void main(String args[])
    {
        RestTemplate restTemplate = new RestTemplate();

        GeocodeRepositoryImpl testable = new GeocodeRepositoryImpl(restTemplate);

        testable.setApiKey("test-4711");
        testable.setUrlBase(API_URL);

        GeocodeResponse response = testable.query("germering");

        LOGGER.info("Obtained result: {}", response);
    }
}
