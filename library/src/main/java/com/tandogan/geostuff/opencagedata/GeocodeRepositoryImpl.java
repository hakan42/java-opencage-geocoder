package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import com.tandogan.geostuff.opencagedata.entity.OpencageRate;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Repository
public class GeocodeRepositoryImpl implements GeocodeRepository
{
    private final static Logger LOGGER = LoggerFactory.getLogger(GeocodeRepositoryImpl.class);

    private static final String API_KEY = "key";

    private static final String QUERY = "q";

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${URL_BASE}")
    private String urlBase;

    private RestOperations template;

    private static final OpencageRate rate = new OpencageRate();

    private static final DateTime reset = new DateTime().withMillis(0);

    public GeocodeRepositoryImpl()
    {
        this.template = new RestTemplate();
    }

    public GeocodeRepositoryImpl(RestOperations template)
    {
        this.template = template;
    }

    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String apiKey)
    {
        this.apiKey = apiKey;
    }

    public String getUrlBase()
    {
        return urlBase;
    }

    public void setUrlBase(String urlBase)
    {
        this.urlBase = urlBase;
    }

    public RestOperations getTemplate()
    {
        return template;
    }

    public void setTemplate(RestOperations template)
    {
        this.template = template;
    }

    public int getLimit()
    {
        return rate.getLimit();
    }

    public int getRemaining()
    {
        return rate.getRemaining();
    }

    public DateTime getReset()
    {
        return reset;
    }

    public GeocodeResponse query(String query)
    {
        URI serviceUrl = UriComponentsBuilder.fromUriString(getUrlBase())
                .queryParam(API_KEY, apiKey)
                .queryParam(QUERY, query)
                .build()
                .toUri();

        LOGGER.debug("REST template is {}", template);

        LOGGER.debug("geocoding query: {}", serviceUrl.toString());

        GeocodeResponse result = template.getForObject(serviceUrl, GeocodeResponse.class);

        if (result != null)
        {
            if (result.getRate() != null)
            {
                rate.setLimit(result.getRate().getLimit());
                rate.setRemaining(result.getRate().getRemaining());
                rate.setReset(result.getRate().getReset());

                reset.withMillis((long) (result.getRate().getReset() * 1000));
            }
        }

        LOGGER.debug("  {} of {} queries remaining", rate.getRemaining(), rate.getLimit());
        LOGGER.debug("  limit will be reset at {}, now it is {}", reset, DateTime.now());

        return result;
    }

    public GeocodeResponse reverse(double latitude, double longitude)
    {
        LOGGER.debug("reverse geocoding {}, {}", latitude, longitude);

        String query = "";

        query = query + latitude;

        query = query + "+";

        query = query + longitude;

        LOGGER.info("query is '{}'", query);

        return query(query);
    }
}
