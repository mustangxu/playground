/**
 *
 */
package com.jayxu.playground.blockchain.eth;

import java.math.BigInteger;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.util.Assert;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.Hash;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.rlp.RlpEncoder;
import org.web3j.rlp.RlpList;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author jayxu
 */
@Data
@EqualsAndHashCode(callSuper = false)
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

    protected byte[] getEncodedHash(boolean includeSignature) {
        return Hash.sha3(this.getEncodedRaw(includeSignature));
    }

    protected byte[] getEncodedRaw(boolean includeSignature) {
        return RlpEncoder.encode(new RlpList(TransactionEncoder
            .asRlpValues(this, includeSignature ? this.signature : null)));
    }

    public byte[] hash() {
        return this.getEncodedHash(false);
    }

    public byte[] signature() {
        return this.getEncodedRaw(true);
    }

    public byte[] raw() {
        return this.getEncodedRaw(false);
    }

    protected void verify(BigInteger pubKey) {
        var sig = new ECDSASignature(new BigInteger(1, this.signature.getR()),
            new BigInteger(1, this.signature.getS()));
        var header = this.signature.getV()[0];
        var pub = Sign.recoverFromSignature(header - 27, sig, this.hash());

        Assert.isTrue(pub.equals(pubKey), "PUBKEY not equal");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
