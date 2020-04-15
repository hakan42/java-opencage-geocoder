package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;

import java.time.LocalDateTime;

public interface GeocodeRepository
{

    int getLimit();

    int getRemaining();

    LocalDateTime getReset();

    GeocodeResponse query(String query);

    GeocodeResponse reverse(double latitude, double longitude);
}
