/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.blockchain.eth;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author xujiajing
 */
public interface EthereumApi {
    @GET("api")
    Call<ApiModel> query(@Query("module") String module,
            @Query("action") String action,
            @Query("apikey") String apikey);
}
