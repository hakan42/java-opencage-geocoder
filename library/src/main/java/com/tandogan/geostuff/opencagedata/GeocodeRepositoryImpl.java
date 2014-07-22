package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Repository
public class GeocodeRepositoryImpl implements GeocodeRepository
{
    private final static String API_KEY = "key";

    private final static String QUERY = "q";

    @Value("${API_KEY}")
    private String apiKey;

    @Value("${URL_BASE}")
    private String urlBase;

    private RestOperations template;

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

    public GeocodeResponse query(String query)
    {
        URI serviceUrl = UriComponentsBuilder.fromUriString(getUrlBase())
                .queryParam(API_KEY, apiKey)
                .queryParam(QUERY, query)
                .build()
                .toUri();

        GeocodeResponse result = template.getForObject(serviceUrl, GeocodeResponse.class);
        return result;
    }
}
