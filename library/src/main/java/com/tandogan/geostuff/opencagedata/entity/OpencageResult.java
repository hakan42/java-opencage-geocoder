package com.tandogan.geostuff.opencagedata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpencageResult
{
    @JsonProperty("confidence")
    private int confidence;

    @JsonProperty("formatted")
    private String formatted;

    @JsonProperty("components")
    private OpencageComponent components;

    @JsonProperty("geometry")
    private OpencageGeometry geometry;

    public int getConfidence()
    {
        return confidence;
    }

    public void setConfidence(int confidence)
    {
        this.confidence = confidence;
    }

    public String getFormatted()
    {
        return formatted;
    }

    public void setFormatted(String formatted)
    {
        this.formatted = formatted;
    }

    public OpencageComponent getComponents()
    {
        return components;
    }

    public void setComponents(OpencageComponent components)
    {
        this.components = components;
    }

    public OpencageGeometry getGeometry()
    {
        return geometry;
    }

    public void setGeometry(OpencageGeometry geometry)
    {
        this.geometry = geometry;
    }
}
