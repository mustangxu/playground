/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.blockchain.eth;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.jayxu.playground.blockchain.ChainService;

import lombok.extern.slf4j.XSlf4j;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xujiajing
 */
@Service
@XSlf4j
public class EtherScanService implements ChainService {
    private static final String API_BASE = "https://api.etherscan.io/";
    private EtherScanApi api = new Retrofit.Builder()
        .baseUrl(EtherScanService.API_BASE)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(EtherScanApi.class);

    @Override
    public long getHeight() throws IOException {
        var res = this.api
            .query("proxy", "eth_blockNumber",
                "KEHVBH9UAWQSKKWSFFS5NHEDY8ACDH7PAW")
            .execute().body().getResult();
        log.info(res);
        return Long.parseLong(res.substring(2), 16);
    }

}
