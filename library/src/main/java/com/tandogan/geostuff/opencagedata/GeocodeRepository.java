package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;

public interface GeocodeRepository
{
    GeocodeResponse query(String query);
}