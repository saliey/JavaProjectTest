package com.example;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class RSA {
    public RSA(){}

    /* example: 测试
    * pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg9xRuU0c+4TUsSEQRsDAwtoF5ztSUUBOQ2gxO0A61Q3r81BhJnr5WXKSAbl9vxE5SjM5Y1x10hUnT6rkSjCQe+tN60pNAR5xPcHIHCvQLvwkLlOBEY4serZuMzmaR/bVwTA+ZNW/VXBTXXlUJ9y0ce8i+6xwVH6Fn1F19oRcT/bZq2b5j3m/W2/ewT+NVOIU2FQbcnotIPG8wuf0l9O/ehd7idUw6ZRKt91zmlaHCoatGADovnDd7mfKJp+5Dc75k0SiEq/s1fSWwL8mZwjk/rA5DzJa0EYWbRQkbR//RBU1GYFuyBieGu6oRS5cS/ieoqbS+JQrIM5raLxX2/mONwIDAQAB";
      priKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCD3FG5TRz7hNSxIRBGwMDC2gXnO1JRQE5DaDE7QDrVDevzUGEmevlZcpIBuX2/ETlKMzljXHXSFSdPquRKMJB7603rSk0BHnE9wcgcK9Au/CQuU4ERjix6tm4zOZpH9tXBMD5k1b9VcFNdeVQn3LRx7yL7rHBUfoWfUXX2hFxP9tmrZvmPeb9bb97BP41U4hTYVBtyei0g8bzC5/SX0796F3uJ1TDplEq33XOaVocKhq0YAOi+cN3uZ8omn7kNzvmTRKISr+zV9JbAvyZnCOT+sDkPMlrQRhZtFCRtH/9EFTUZgW7IGJ4a7qhFLlxL+J6iptL4lCsgzmtovFfb+Y43AgMBAAECggEAEQeTRms/Ivy0u2uZvomGW6Psv0BaEURM10fNk+lnr7xsjLAE3ANl0LeLQ83A/7wpufuDneGDFe7FdPSNJ0e+YUKyfYDLESntVhyAy6Kc8HR/97uxH6NM+0w0T0lRM5odYSXp6PvyCgbHvXq986f9ZfmMexR6LW9psnnC0mOlyG3ZYfbDF8iJ7hj//oq4kVuFGr2O1XL3r6F12wA3Tbg7aYg0xVydyuDQ8Ebp9R4OkNqKy57zi2PhRMht/YmF4lvcEo+V6x0YOM+sutic+GFlrS46e4khgz+JzQIj84KmFeWcn429bGnDci9iCesu9gGNEsfPd7pR9Z0lPlXMApubgQKBgQDKNDgzm7gQTRd1LC5HE67Z80kRAvX0cFXMcunvutCUCGQWYeqcSx4aRrsUg70nIKcsDR4+mH0FMxNjQAOr2lEyh8f+4E74mzz5m69JjHKCyw9y0ObT8HYOUSGmXrLbYWRoO7kA7weqNeV29lF01BX+VDlMDqLH/Fx8Z8c2N2yDowKBgQCm8SER+TZCdF3LG8KplFASDhjJA4uRK7wyO/eofqjh1UgDeairnvzZTRX2rdPRq6d/42RiNqpj15ad43qOiRgzAmxpSQhLKJKq3E+BXFSgrAJL/0ZK+enE8nOsV8jMCQ0nsHa8a/bTHB5bnNwm09Eci71OyqzcIJnFhbR0n/cUXQKBgHerzjNlDtu+9Ioper4gPRTK8sx5px1jOrREsoVqWDW0fZ/tlY+ouYktoV0z1sgIgEgG/4Zmc1+XafsMkN+t1u6EA+6pzLNAFSdZZ7WsxLz59wup7WMz5nr0s2imlU5XZrh12DlgPnYTbdopT+5H8zhRmpOT+e1IQ2hxdyp0ROnnAoGBAIsIpWcnq5Xa/kZq30uGNs1iqie3aSRFTV507QSKQDKdA3XJe707ExATECi6in/DuyNaiT72kh/G7CygyHAvI1VQPmPNLDgUGVptU1XERMLvnpYWiz9kNdoNpmKxVY0AONGGOM0iCZW2hF3+HKfFnxFsHASj7SUDja2gdjLvCsMNAoGAMPevsdc2r7VKGBNnc+kVAIQsQyqpu5P1PnO5CZuz3gauaO8HkMP8ij1YwdHFsozItRc/BTnp0uGWiei6p8PjmZWmMYEVqr0rUVugDa+dnPQuQPQ0CZfLU76gVb/t5RrJB6fXK9RLaaquvVEksqYBiOqic3FUO/1f7FMxvYHzRTw=";

      rsaEncrypt(pubKey, "123") = VIsqNUuSOQJKWuyxbIiDLpWEnANCZqDYEX5BH0xalWfPBedL8N5vLiP/90UWflZfvD5Sqp8OpVygN/BWSkSMBqJkSvdrnfZbDcgG+jzUjdpUkymlxr+Xo1fHS5pBzD/GhmtWW7w/Rdu/uQvbS7HGwCrgVhjcPU8w9Rd8xSkXDmubYg6nw+pEKxLMZzEUSngnzODyPqR29GDuniZ8TQpy0D0aD1v3EuRgynkC4xtgUD2gDpLeLUA7lcGHgCnHkCC2SStBpZ+jrfv6ZbBzDtAHI7M5Mtg/+dmqXg3aIVt8OhGzkeqQ8MMrJ8+/+qTzHWhvCN5R9KQ6hPMZUIpHJgtW0g==

      rsaDecrypt(priKey, "VIsqNUuSOQJKWuyxbIiDLpWEnANCZqDYEX5BH0xalWfPBedL8N5vLiP/90UWflZfvD5Sqp8OpVygN/BWSkSMBqJkSvdrnfZbDcgG+jzUjdpUkymlxr+Xo1fHS5pBzD/GhmtWW7w/Rdu/uQvbS7HGwCrgVhjcPU8w9Rd8xSkXDmubYg6nw+pEKxLMZzEUSngnzODyPqR29GDuniZ8TQpy0D0aD1v3EuRgynkC4xtgUD2gDpLeLUA7lcGHgCnHkCC2SStBpZ+jrfv6ZbBzDtAHI7M5Mtg/+dmqXg3aIVt8OhGzkeqQ8MMrJ8+/+qTzHWhvCN5R9KQ6hPMZUIpHJgtW0g==")
      = "123"
    *
    * */

    public void GenKey() {
        try {
            Log.i("RSA GenKey in");
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            Log.i("RSA GenKey publicKey " + publicKey.getEncoded().length);
            Log.i("RSA GenKey privateKey " + privateKey.getEncoded().length);

            byte[] pubBytes = publicKey.getEncoded();
            byte[] priBytes = privateKey.getEncoded();
            String basePubKey = Base64.getEncoder().encodeToString(pubBytes);
            String basePriKey = Base64.getEncoder().encodeToString(priBytes);
            Log.i("RSA GenKey basePubKey " + basePubKey);
            Log.i("RSA GenKey basePriKey " + basePriKey);

            byte[] deBaseBytes = Base64.getDecoder().decode(basePubKey);
            if (Arrays.equals(deBaseBytes, pubBytes)) {
                Log.i("RSA GenKey decode success.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String Encrypt(String data, String key) {
        Log.i("RSA Encrypt in");
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(keyBytes));

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));
            String result = Base64.getEncoder().encodeToString(bytes);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String Decrypt(String data, String key) {
        Log.i("RSA Encrypt in");
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(keyBytes));

            byte[] bytes = Base64.getDecoder().decode(data);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] finBytes = cipher.doFinal(bytes);
            String result = new String(finBytes, "UTF-8");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
