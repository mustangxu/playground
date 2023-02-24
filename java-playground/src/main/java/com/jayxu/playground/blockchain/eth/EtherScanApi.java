/**
 * Authored by jayxu @2021
 */
package com.jayxu.playground.blockchain.eth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author xujiajing
 */
@FeignClient(name = "EtherScanApi", url = "https://api.etherscan.io/")
public interface EtherScanApi {
    @GetMapping("api")
    ApiModel query(@RequestParam("module") String module,
            @RequestParam("action") String action,
            @RequestParam("apikey") String apikey);
}
