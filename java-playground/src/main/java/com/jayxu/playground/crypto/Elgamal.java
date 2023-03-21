/**
 * Authored by jayxu @2022
 */
package com.jayxu.playground.crypto;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

/**
 * @author xujiajing
 */
public class Elgamal {
    private Cipher cipher;
    private SecureRandom random = new SecureRandom();
    private KeyPair keypair;
    private int keysize;

    static {
        Security.insertProviderAt(new BouncyCastleProvider(), 1);
    }

    public Elgamal(int keysize)
            throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.keysize = keysize;
        this.keypair = this.createKeyPair();
        this.cipher = Cipher.getInstance("ElGamal/None/PKCS1Padding");
    }

    private KeyPair createKeyPair() throws NoSuchAlgorithmException {
        var generator = KeyPairGenerator.getInstance("ELGamal");
        generator.initialize(this.keysize, this.random);
        return generator.generateKeyPair();
    }

    public byte[] encrypt(byte[] raw) throws GeneralSecurityException {
        this.cipher.init(Cipher.ENCRYPT_MODE, this.keypair.getPublic(),
            this.random);

        return this.cipher.doFinal(raw);
    }

    public byte[] decrypt(byte[] input) throws GeneralSecurityException {
        this.cipher.init(Cipher.DECRYPT_MODE, this.keypair.getPrivate(),
            this.random);

        return this.cipher.doFinal(input);
    }
}
