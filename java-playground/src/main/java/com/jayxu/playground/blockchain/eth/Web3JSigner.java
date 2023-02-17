package com.jayxu.playground.blockchain.eth;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

/**
 * Use EthereumJ lib
 *
 * @author xujiajing
 */
public class Web3JSigner extends EthSigner {
    @Override
    public SignatureData signTx(ECKeyPair key, MyRawTransaction tx) {
        var sig = Sign.signMessage(tx.getEncodedRaw(false), key);
        tx.setSignature(sig);
        tx.setSigned(true);

        return sig;
    }

}
