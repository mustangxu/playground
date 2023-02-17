/**
 *
 */
package com.jayxu.playground.blockchain.eth;

import java.math.BigInteger;
import java.util.Arrays;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xujiajing
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Slf4j
public class MyRawTransaction extends RawTransaction {
    private SignatureData signature;
    private byte chainId = 1;
    private boolean signed;

    public MyRawTransaction(BigInteger nonce, BigInteger gasPrice,
            BigInteger gasLimit, String to, BigInteger value, String data,
            int chainId) {
        super(nonce, gasPrice, gasLimit, to, value, data);
        this.chainId = (byte) chainId;
    }

    public byte[] getEncodedHash(boolean includeSignature) {
        return Hash.sha3(this.getEncodedRaw(includeSignature));
    }

    public byte[] getEncodedRaw(boolean includeSignature) {
        return RlpEncoder.encode(new RlpList(TransactionEncoder
            .asRlpValues(this, includeSignature ? this.signature : null)));
    }

    protected void verify(ECKeyPair key) {
        var sig = Sign.signMessage(this.getEncodedRaw(false), key);

        log.debug("sig:\n{}", ToStringBuilder.reflectionToString(sig,
            ToStringStyle.MULTI_LINE_STYLE));
        log.debug("signature:\n{}", ToStringBuilder.reflectionToString(
            this.signature, ToStringStyle.MULTI_LINE_STYLE));

        if (!Arrays.equals(sig.getR(), this.signature.getR())
            || !Arrays.equals(sig.getS(), this.signature.getS())) {
            throw new RuntimeException("TX verify failed");
        }
    }
}
