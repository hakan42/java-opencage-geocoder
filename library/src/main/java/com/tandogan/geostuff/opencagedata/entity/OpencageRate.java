package com.tandogan.geostuff.opencagedata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpencageRate
{
    @JsonProperty("limit")
    private int limit;

    @JsonProperty("remaining")
    private int remaining;

    @JsonProperty("reset")
    private int reset;

    public int getLimit()
    {
        return limit;
    }

    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    public int getRemaining()
    {
        return remaining;
    }

    public void setRemaining(int remaining)
    {
        this.remaining = remaining;
    }

    public int getReset()
    {
        return reset;
    }

    public void setReset(int reset)
    {
        this.reset = reset;
    }
}
