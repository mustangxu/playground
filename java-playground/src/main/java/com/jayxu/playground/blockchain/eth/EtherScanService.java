/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.blockchain.eth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayxu.playground.blockchain.ChainService;

import lombok.extern.slf4j.XSlf4j;

/**
 * @author xujiajing
 */
@Service
@XSlf4j
public class EtherScanService implements ChainService {
    @Autowired
    private EtherScanApi api;

    @Override
    public long getHeight() {
        var res = this.api.query("proxy", "eth_blockNumber",
            "KEHVBH9UAWQSKKWSFFS5NHEDY8ACDH7PAW").getResult();
        log.info(res);
        return Long.parseLong(res.substring(2), 16);
    }

}
