package io.tlaabs.github.model;

import java.util.List;

import io.tlaabs.github.domain.StoreVO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("store")
    Call<List<StoreVO>> getStores(
            @Query("myLat") double myLat,
            @Query("myLng") double myLng,
            @Query("d") int d
            );
}
