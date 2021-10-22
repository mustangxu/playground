package com.jayxu.playground.crypt;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.Throughput)
@Measurement(iterations = 3, time = 10, timeUnit = TimeUnit.SECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(value = 2)
public class CryptBenchmark {
    private static PrivateKey privKey;
    private static PublicKey pubKey;

    static {
        Security.addProvider(
            new BouncyCastleProvider());

        CryptBenchmark.privKey = CryptBenchmark.loadPrivKey();
        CryptBenchmark.pubKey = CryptBenchmark.loadPubKey();
    }

    public static void main(String[] args) throws Exception {
        new CryptBenchmark().rsaDecrypt(null);
    }

    @Benchmark
    public void rsaDecrypt(Blackhole hole) throws Exception {
        var cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
        cipher.init(Cipher.DECRYPT_MODE, CryptBenchmark.privKey);
        var raw = new String(
            cipher.doFinal(CryptBenchmark.loadFile("crypted")));
//        System.out.println(raw);

        if (hole != null) {
            hole.consume(raw);
        }
    }

    @Benchmark
    public void rsaEncrypt(Blackhole hole) throws Exception {
        var cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");
        cipher.init(Cipher.ENCRYPT_MODE, CryptBenchmark.pubKey);
        var encrypted = cipher
            .doFinal(
                UUID.randomUUID().toString().getBytes());
//        System.out.println(Base64.encodeBase64String(encrypted));

        if (hole != null) {
            hole.consume(encrypted);
        }
    }

    private static byte[] loadFile(String filename) throws IOException {
        try (var is = CryptBenchmark.class.getClassLoader()
            .getResourceAsStream(filename)) {
            return Base64.decodeBase64(is.readAllBytes());
        }
    }

    private static PublicKey loadPubKey() {
        try {
            var kf = KeyFactory.getInstance("RSA");

            return kf
                .generatePublic(
                    new X509EncodedKeySpec(
                        CryptBenchmark.loadFile("pubkey.pem")));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static PrivateKey loadPrivKey() {
        try {
            var kf = KeyFactory.getInstance("RSA");

            return kf
                .generatePrivate(
                    new PKCS8EncodedKeySpec(
                        CryptBenchmark.loadFile("privkey.pem")));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
