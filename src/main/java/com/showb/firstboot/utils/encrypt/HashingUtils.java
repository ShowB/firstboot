package com.showb.firstboot.utils.encrypt;

import com.showb.firstboot.exceptions.FirstBootException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HashingUtils {
    private static final String SHA_512 = "SHA-512";


    public static String hashingSHA512(String str){
        try {
            MessageDigest digest = MessageDigest.getInstance(SHA_512);
            digest.reset();
            digest.update(str.getBytes(StandardCharsets.UTF_8));
            return String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new FirstBootException("failed to encode", e);
        }
    }
}
