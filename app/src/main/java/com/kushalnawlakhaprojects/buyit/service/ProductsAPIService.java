package com.kushalnawlakhaprojects.buyit.service;

import com.kushalnawlakhaprojects.buyit.model.ProductResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by vanaik on 2/7/2017.
 */
public interface ProductsAPIService {
    @GET("?key=b743e26728e16b81da139182bb2094357c31d331")
    Call<ProductResponse> getProductResponse(@Query("term") String term);

}
