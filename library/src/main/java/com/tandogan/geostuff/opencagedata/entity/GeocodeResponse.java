package com.tandogan.geostuff.opencagedata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResponse
{
    @JsonProperty("results")
    private List<OpencageResult> results;

    @JsonProperty("rate")
    private OpencageRate rate;

    @JsonProperty("status")
    private OpencageStatus status;

    @JsonProperty("total_results")
    private int totalResults;

    public List<OpencageResult> getResults()
    {
        return results;
    }

    public void setResults(List<OpencageResult> results)
    {
        this.results = results;
    }

    public OpencageStatus getStatus()
    {
        return status;
    }

    public void setStatus(OpencageStatus status)
    {
        this.status = status;
    }

    public int getTotalResults()
    {
        return totalResults;
    }

    public void setTotalResults(int totalResults)
    {
        this.totalResults = totalResults;
    }

    public OpencageRate getRate()
    {
        return rate;
    }

    public void setRate(OpencageRate rate)
    {
        this.rate = rate;
    }
}
