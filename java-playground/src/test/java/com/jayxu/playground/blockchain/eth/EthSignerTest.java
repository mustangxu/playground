package com.jayxu.playground.blockchain.eth;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.jupiter.api.Test;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EthSignerTest {
    private static final String PRIV_KEY = "289c2857d4598e37fb9647507e47a309d6133539bf21a8b9cb6df88fd5232032";
    // eckey
    private static ECKeyPair key = EthSigner.keyFromPrivateHex(PRIV_KEY);
    // address
    private static String addr = Keys.getAddress(key);

    @Test
    public void test() throws Exception {
        log.info("eckey:\n\tprivKey:{}\n\tpubKey:{}",
            key.getPrivateKey().toString(16), key.getPublicKey().toString(16));
        log.info("addr: {}", addr);

        // tx
        var tx1 = buildTx(addr);
        var tx2 = buildTx(addr);

        var s1 = new Web3JSigner();
        var s2 = new EthSigner();

        // rlp(tx)
        assertArrayEquals(tx1.getEncodedRaw(false), tx2.getEncodedRaw(false),
            "rlp encoded");

        // sha3(rlp(tx))
        assertArrayEquals(tx1.getEncodedHash(false), tx2.getEncodedHash(false),
            "rlp encoded hash");

        // key.sign(sha3(rlp(tx)))
        var sign1 = s1.signTx(key, tx1);
        debug(tx1, 1);

//      var sign2 = Sign.signMessage(tx2.getEncodedRaw(), key, true);
        var sign2 = s2.signTx(key, tx2);
        debug(tx2, 2);

        assertEquals(sign1, sign2, "sig");
        assertArrayEquals(tx1.getEncodedHash(true), tx2.getEncodedHash(true),
            "encoded");
        assertEquals(tx1, tx2, "tx object");

        log.info("Java version: " + System.getProperty("java.version"));
        var tx3 = buildTx(addr);
        var tx4 = buildTx(addr);
        var newKey = KeyGenerator.generateECKey();
        var sign3 = s1.signTx(newKey, tx3);
        debug(tx3, 3);

        var sign4 = s2.signTx(newKey, tx4);
        debug(tx4, 4);
        assertEquals(sign3, sign4, "new sig");
    }

    static MyRawTransaction buildTx(String to) {
        var gasLimit = new BigInteger("120000");
        var gasPrice = new BigInteger("200000000000");
        var value = BigInteger.TWO.pow(64);
        var data = Hex.encodeHexString("hello world".getBytes());
        var nonce = BigInteger
            .valueOf(System.currentTimeMillis() / 10000 * 10000);

        var tx = new MyRawTransaction(nonce, gasPrice, gasLimit, to, value,
            data, 1);
        log.info(tx.toString());

        return tx;
    }

    private static void debug(MyRawTransaction tx, int n) {
        log.info("SIGN-{}: {}", n, ToStringBuilder.reflectionToString(
            tx.getSignature(), ToStringStyle.MULTI_LINE_STYLE));

        /*
         * log.info("tx1 hash: {}\n\traw: {}",
         * Hex.encodeHexString(tx1.getHash()),
         * Hex.encodeHexString(tx1.getRawHash()));
         */
        log.info("TX-{} encoded:\n\thash: {}\n\traw: {}", n,
            Hex.encodeHexString(tx.getEncodedHash(true)),
            Hex.encodeHexString(tx.getEncodedRaw(true)));
    }
}
