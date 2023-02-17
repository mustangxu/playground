/**
 * Copyright(c) 2010-2021 by Youxin Financial Group
 * All Rights Reserved
 */
package com.jayxu.playground.blockchain.eth;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.ECGenParameterSpec;

import org.web3j.crypto.ECKeyPair;

/**
 * @author jay
 */
public interface KeyGenerator {
    String CURVE_ID = "secp256k1";
    // static ECPoint extractPublicKey(ECPublicKey ecPublicKey) {
    // final java.security.spec.ECPoint publicPointW = ecPublicKey.getW();
    // final BigInteger xCoord = publicPointW.getAffineX();
    // final BigInteger yCoord = publicPointW.getAffineY();
    //
    // return MyECKey.CURVE.getCurve().createPoint(xCoord, yCoord);
    // }

    static KeyPair generateKey() throws GeneralSecurityException {
        var keyPairGen = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGen.initialize(new ECGenParameterSpec(CURVE_ID));

        return keyPairGen.generateKeyPair();
    }

    static ECKeyPair generateECKey() throws GeneralSecurityException {
        return ECKeyPair
            .create(((ECPrivateKey) generateKey().getPrivate()).getS());
    }
}
