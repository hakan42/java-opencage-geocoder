package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import com.tandogan.geostuff.opencagedata.entity.OpencageRate;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Repository
@Slf4j
public class GeocodeRepositoryImpl implements GeocodeRepository
{
    private static final String API_KEY = "key";

    private static final String QUERY = "q";

    @Value("${API_KEY:}")
    @Getter
    @Setter
    private String apiKey;

    @Value("${URL_BASE}")
    @Getter
    @Setter
    private String urlBase;

    @Getter
    @Setter
    private RestOperations template;

    private static final OpencageRate rate = new OpencageRate();

    @Getter
    private LocalDateTime reset = LocalDateTime.ofEpochSecond(0, 0, ZoneOffset.UTC);

    public GeocodeRepositoryImpl()
    {
        this.template = new RestTemplate();
    }

    public GeocodeRepositoryImpl(RestOperations template)
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

    public GeocodeResponse query(String query)
    {
        URI serviceUrl = UriComponentsBuilder.fromUriString(getUrlBase())
                .queryParam(API_KEY, apiKey)
                .queryParam(QUERY, query)
                .build()
                .encode()
                .toUri();

        log.debug("geocoding query: {}", serviceUrl);

        GeocodeResponse result = new GeocodeResponse();

        try
        {
            ResponseEntity<GeocodeResponse> response = template.getForEntity(serviceUrl, GeocodeResponse.class);

            switch (response.getStatusCode())
            {
                // TODO proper handling of quota excessions
                case OK:
                    result = response.getBody();

                    if (result != null)
                    {
                        if (result.getRate() != null)
                        {
                            rate.setLimit(result.getRate().getLimit());
                            rate.setRemaining(result.getRate().getRemaining());
                            rate.setReset(result.getRate().getReset());

                            reset = LocalDateTime.ofEpochSecond((long) (result.getRate().getReset()), 0, ZoneOffset.UTC);
                        }
                    }
                    break;

                default:
                    break;
            }

            log.debug("  {} of {} queries remaining", rate.getRemaining(), rate.getLimit());
            log.debug("  limit will be reset at {}, now it is {}", reset, LocalDateTime.now());
        }
        catch (RestClientException restClientException)
        {
            log.error("While calling API", restClientException);
        }

        return result;
    }

    public GeocodeResponse reverse(double latitude, double longitude)
    {
        log.debug("reverse geocoding {}, {}", latitude, longitude);

        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(8);

        String query = df.format(latitude) + "+" + df.format(longitude);

        log.info("query is '{}'", query);

        return query(query);
    }
}
