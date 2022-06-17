package pkg_is;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import static pkg_is.enc.Keys_Folder_Path;

public class AESKeyGenerator {

    private static SecretKey secretKey;

    public static SecretKey AES() throws Exception {
        int KEY_SIZE = 128;
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(KEY_SIZE);
        secretKey = generator.generateKey();
        Save(Keys_Folder_Path + "AES.key", secretKey.getEncoded());
        return secretKey;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }

    public static void Save(String fileName, byte[] AESBytes) throws IOException {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        try {
            fileOut.write(AESBytes);
            fileOut.flush();
        } catch (Exception e) {
            throw new IOException("Unexpected error");
        } finally {
            fileOut.close();
        }
    }

    public static String convertSecretKeyToString(SecretKey secretKey) throws NoSuchAlgorithmException {
        byte[] rawData = secretKey.getEncoded();
        String encodedKey = Base64.getEncoder().encodeToString(rawData);
        return encodedKey;
    }

    public static SecretKey convertStringToSecretKey(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }

}
