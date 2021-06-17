/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.blockchain.eth;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jayxu.playground.blockchain.ChainService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author xujiajing
 */
@Service
public class EthereumChainService implements ChainService {
    private static final String API_BASE = "https://api.etherscan.io/";
    private static final Logger log = LoggerFactory
        .getLogger(EthereumChainService.class);
    private EthereumApi api = new Retrofit.Builder()
        .baseUrl(EthereumChainService.API_BASE)
        .addConverterFactory(GsonConverterFactory.create()).build()
        .create(EthereumApi.class);

    @Override
    public long getHeight() throws IOException {
        var res = this.api
            .query("proxy", "eth_blockNumber",
                "KEHVBH9UAWQSKKWSFFS5NHEDY8ACDH7PAW")
            .execute()
            .body().getResult();
        EthereumChainService.log.info(res);
        return Long.parseLong(res.substring(2), 16);
    }

}
