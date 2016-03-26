package com.tandogan.geostuff.opencagedata;

import com.tandogan.geostuff.opencagedata.entity.GeocodeResponse;
import org.joda.time.DateTime;

public interface GeocodeRepository
{

    int getLimit();

    int getRemaining();

    DateTime getReset();

    GeocodeResponse query(String query);

    GeocodeResponse reverse(double latitude, double longitude);
}
