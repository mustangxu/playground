/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.web3;

import java.io.IOException;
import java.math.BigInteger;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthChainId;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jayxu
 */
@Component
@Slf4j
public class Web3Client implements InitializingBean {
    @Value("${web3.eth.url:https://cloudflare-eth.com}")
    private String ethNetUrl;
    private Web3j web3;

    public Web3ClientVersion web3ClientVersion() throws IOException {
        return this.web3.web3ClientVersion().send();
    }

    public EthGasPrice ethGasPrice() throws IOException {
        return this.web3.ethGasPrice().send();
    }

    public EthChainId ethChainId() throws IOException {
        return this.web3.ethChainId().send();
    }

    public EthBlock getBlockByNumber(BigInteger number) throws IOException {
        return this.web3.ethGetBlockByNumber(DefaultBlockParameter.valueOf(number), false).send();
    }

    public EthBlockNumber ethBlockNumber() throws IOException {
        return this.web3.ethBlockNumber().send();
    }

    @Override
    public void afterPropertiesSet() {
        this.web3 = Web3j.build(new HttpService(this.ethNetUrl));
        log.info("{} initialized", ToStringBuilder.reflectionToString(this.web3));
    }
}
