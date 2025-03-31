/**
 *
 */
package com.jayxu.playground.blockchain.eth;

import java.util.Objects;

import org.bouncycastle.asn1.sec.SECNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.ECDSASignature;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.utils.Numeric;

/**
 * Impl. using BC, only use ECKey from EthereumJ
 *
 * @author jayxu
 */
public class EthSigner implements KeyGenerator {
    private static final X9ECParameters params = SECNamedCurves
        .getByName(CURVE_ID);
    public static final ECDomainParameters CURVE = new ECDomainParameters(
        params.getCurve(), params.getG(), params.getN(), params.getH());

//    private static final byte[] EMPTY_BYTE_ARRAY = {};
    // private static final byte[] ZERO_BYTE_ARRAY = new byte[] { 0 };
    static ECKeyPair keyFromPrivateHex(String privKeyHex) {
        return ECKeyPair.create(Hex.decode(privKeyHex));
    }

    public SignatureData signTx(ECKeyPair key, MyRawTransaction tx) {
        var raw = tx.hash();

        var signer = new ECDSASigner(
            new HMacDSAKCalculator(new SHA256Digest()));
        var privKeyParams = new ECPrivateKeyParameters(key.getPrivateKey(),
            CURVE);

        signer.init(true, privKeyParams);
        var components = signer.generateSignature(raw);

        var sig = new ECDSASignature(components[0], components[1])
            .toCanonicalised();

        var recId = -1;
        var thisKey = key.getPublicKey();
        for (var i = 0; i < 4; i++) {
            var k = Sign.recoverFromSignature(i, sig, raw);
            if (k != null && Objects.equals(k, thisKey)) {
                recId = i;
                break;
            }
        }
        if (recId == -1) {
            throw new RuntimeException(
                "Could not construct a recoverable key. This should never happen.");
        }

        byte[] v = { (byte) (recId + 27) };
        var r = Numeric.toBytesPadded(sig.r, 32);
        var s = Numeric.toBytesPadded(sig.s, 32);

        // tx.sign(key.toECKey()); // this call will set sender addr.

        var sign = new SignatureData(v, r, s);
        tx.setSignature(sign);
        tx.verify(key.getPublicKey());
        tx.setSigned(true);

        return sign;
    }

    public String ping() {
        return "OK";
    }

}
