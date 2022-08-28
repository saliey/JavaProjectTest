package com.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AES {
    public AES() {}

    public String Encrypt(String data, String key) {
        Log.i("AES Encrypt in");
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            byte[] bb = cipher.doFinal(data.getBytes());
            String result = Base64.getEncoder().encodeToString(bb);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String Decrypt(String data, String key) {
        Log.i("AES Encrypt in");
        try {
            byte[] byteData = Base64.getDecoder().decode(data.getBytes());

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"));
            byte[] bb = cipher.doFinal(byteData);
            String result = new String(bb);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
