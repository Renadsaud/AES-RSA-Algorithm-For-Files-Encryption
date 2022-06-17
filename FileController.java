/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg_is;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author RAWAN
 */
public class FileController {

    public static String reead(String file) throws Exception {

        FileInputStream fileInputStream = new FileInputStream(file);

        String content = "";
        int i = 0;
        while ((i = fileInputStream.read()) != -1) {
            content += ((char) i);
        }
        fileInputStream.close();
        return content;

    }
    
    public static void write(String file, String content) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        byte[] contentArray = content.getBytes();
        fileOutputStream.write(contentArray);
        fileOutputStream.close();
    }


    static Key readKeyFromFile(String keyFileName) throws IOException {
        InputStream in = new FileInputStream(keyFileName);
        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));

        try {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            KeyFactory fact = KeyFactory.getInstance("RSA");
            if (keyFileName.substring(keyFileName.length() - 10).equals("public.key")) {
                return fact.generatePublic(new RSAPublicKeySpec(m, e));
            } else {
                return fact.generatePrivate(new RSAPrivateKeySpec(m, e));
            }

        } catch (Exception e) {
            throw new RuntimeException("Error", e);
        } finally {
            oin.close();
            System.out.println("close reading rsa key fiel: ");
        }
    }

    public static SecretKey readAESKey(String AESKeyPath) throws Exception {

        File file = new File(AESKeyPath);
        byte filePlainContent[] = new byte[(int) file.length()];

        FileInputStream fin = null;
        SecretKey originalKey = null;
        try {
          
            fin = new FileInputStream(AESKeyPath);

            fin.read(filePlainContent);

            originalKey = new SecretKeySpec(filePlainContent, 0, filePlainContent.length, "AES");
            System.out.println("AES file content:" + filePlainContent.length);
        } catch (FileNotFoundException e) {
            System.out.println("AES file  not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading file" + ioe);
        } finally {

            try {
                if (fin != null) {
                    fin.close();
                }

            } catch (IOException ioe) {
                System.out.println("Error closing stream:" + ioe);
            }
        }

        return originalKey;
    }

    public static byte[] raed(String file) throws Exception {

        File f = new File(file);
        byte Content[] = new byte[(int) f.length()];
        FileInputStream fin = null;
        
        try {
            fin = new FileInputStream(file);
            fin.read(Content);
            String s = new String(Content);
            System.out.println("file content: " + s);
        } catch (IOException ioe) {
            System.out.println("Erzor");}
        
        try {
            if (fin != null) 
                fin.close();
        } catch (IOException ioe) {
            System.out.println("Erzor");}
        
        return Content;

    }

}
