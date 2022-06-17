/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_is;

import static pkg_is.RSAKeyGenerator.KeyFactory;
import static pkg_is.loginWindos.username;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import static pkg_is.loginWindos.save_keys_path;

/**
 *
 * @author RAWAN
 */

public class RSAKeyGenerator {

    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static void RSA() throws Exception {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        privateKey = keyPair.getPrivate();
        publicKey = keyPair.getPublic();
        KeyFactory();
    }

    public static void KeyFactory() throws Exception {

        KeyFactory x = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec pub = x.getKeySpec(getPublicKey(), RSAPublicKeySpec.class);
        RSAPrivateKeySpec pri = x.getKeySpec(getPrivateKey(), RSAPrivateKeySpec.class);

        save(save_keys_path + "\\" + username.getText() + "_public.key", pub.getModulus(), pub.getPublicExponent());
        save(save_keys_path + "\\" + username.getText() + "_private.key", pri.getModulus(), pri.getPrivateExponent());

     
    }

    public static void save(String fileName, BigInteger mod, BigInteger exp) throws IOException {
        ObjectOutputStream fileOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
        try {
            fileOut.writeObject(mod);
            fileOut.writeObject(exp);
        } catch (Exception e) {
            throw new IOException("Unexpected error");
        } finally {
            fileOut.close();
        }
        
    }

    public static PublicKey getPublicKey() {
        return publicKey;
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

}
