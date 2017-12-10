package com.base.baseproject.utils.encryption;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class CodeHandler {

    public static String base64Encode(String token){
        // encode data on your side using BASE64
        return Base64.encodeToString(token.getBytes(), Base64.DEFAULT).trim();
    }
    public static String base64Decode(String data){
        byte[] bytes = Base64.decode(data.getBytes(), Base64.DEFAULT);
        return new String(bytes);
    }

    public static String md5Encode(String strIn){
        if(strIn==null)
            return "";

        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest
                    .getInstance(MD5);
            digest.update(strIn.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
