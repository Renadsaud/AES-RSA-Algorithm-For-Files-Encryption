

package pkg_is;

import static pkg_is.enc.Keys_Folder_Path;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
/**
 *
 * @author RAWAN
 */
public class AES {

    private static SecretKeySpec secretKey;
    private static byte[] key;
    private static final String Path = Keys_Folder_Path;
    static SecretKey srtkey;

    public static void setKey(String mykey) {
        try {
            key = mykey.getBytes("UTF-8");
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(byte[] msg, String key) {
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(msg);
        
            FileOutputStream fileOut = new FileOutputStream(Path + "_cipher.enc");
            try {
                fileOut.write(cipherText);
                fileOut.flush();
            } catch (Exception e) {
                throw new IOException(" Unexpected error ");
            } finally {
                fileOut.close();             
            }
            return cipherText;

        } catch (Exception e) {
            System.out.println("Error while encrypting :" + e.toString());
        }

        return null;
    }

    public static byte[] decrypt(byte[] msg, String key) throws IOException {
       
        FileOutputStream fileOut = null;

        try {
            
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] Decrypt_str = cipher.doFinal(msg);
            
            fileOut = new FileOutputStream(Path + "AES_encrypted.key");
            fileOut.write(Decrypt_str);
            fileOut.flush();

            return Decrypt_str;

        } catch (Exception e) {
            System.out.println("Error while decrypting :" + e.toString());
        }

        return null;
    }
}
