/**
 * Authored by jayxu @2023
 */
package com.jayxu.playground.spring.mvc;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthBlock.Block;
import org.web3j.protocol.core.methods.response.EthBlockNumber;
import org.web3j.protocol.core.methods.response.EthChainId;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;

import com.jayxu.playground.web3.Web3Client;

import lombok.SneakyThrows;

/**
 * @author jayxu
 */
@RestController("/web3")
public class Web3Controller {
    @Autowired
    private Web3Client client;

    @GetMapping("/clientVersion")
    @SneakyThrows
    public Web3ClientVersion web3ClientVersion() {
        return this.client.web3ClientVersion();
    }

    @GetMapping("/gasPrice")
    @SneakyThrows
    public EthGasPrice ethGasPrice() {
        return this.client.ethGasPrice();
    }

    @GetMapping("/chainId")
    @SneakyThrows
    public EthChainId ethChainId() {
        return this.client.ethChainId();
    }

    @GetMapping("/blockNumber")
    @SneakyThrows
    public EthBlockNumber ethBlockNumber() {
        return this.client.ethBlockNumber();
    }

    @GetMapping("/block/latest")
    @SneakyThrows
    public Block getLatestBlock() {
        return minify(this.client
            .getBlockByNumber(this.client.ethBlockNumber().getBlockNumber()));
    }

    @GetMapping("/block")
    @SneakyThrows
    public Block getBlockByNumber(@RequestParam BigInteger number) {
        return minify(this.client.getBlockByNumber(number));
    }

    private static Block minify(EthBlock raw) {
        raw.getBlock().getTransactions().clear();
        return raw.getBlock();
    }
}
