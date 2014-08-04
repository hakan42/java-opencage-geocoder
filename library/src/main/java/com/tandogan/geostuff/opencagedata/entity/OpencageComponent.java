package com.tandogan.geostuff.opencagedata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpencageComponent
{
    private String city;

    private String country;

    @JsonProperty("country_code")
    private String countryCode;

    private String county;

    private String state;

    @JsonProperty("state_district")
    private String stateDistrict;

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCountryCode()
    {
        return countryCode;
    }

    public void setCountryCode(String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCounty()
    {
        return county;
    }

    public void setCounty(String county)
    {
        this.county = county;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getStateDistrict()
    {
        return stateDistrict;
    }

    public void setStateDistrict(String stateDistrict)
    {
        this.stateDistrict = stateDistrict;
    }
}
