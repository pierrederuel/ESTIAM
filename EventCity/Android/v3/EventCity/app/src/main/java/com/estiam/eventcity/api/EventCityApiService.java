package com.estiam.eventcity.api;

import com.estiam.eventcity.model.City;
import com.estiam.eventcity.model.EventCityResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by ESTIAM on 22/06/2017.
 */

public interface EventCityApiService {

    @GET("api/v1/cities?populate=events")
    Observable<EventCityResponse<City>> getCities();

}
