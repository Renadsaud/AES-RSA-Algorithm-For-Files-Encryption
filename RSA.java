/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_is;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

/**
 *
 * @author RAWAN
 */
public class RSA {
    
  

   public static void Encrypt (String file_loc, String file_des, String Key_Path) throws Exception { 
        FileReader file = new FileReader(file_loc);//AES Key 
        BufferedReader reader = new BufferedReader(file); 
        String text = ""; 
        String line = reader.readLine(); 
        
        while (line != null) { 
            text += line;
         line= reader.readLine();
        }
        reader.close();
        
        File file2 = new File(file_loc);
        byte[] data = new byte[(int) file2.length()];
        int i ;
        System.out.println ("Start RSA Encryption");
        Key pubKey = FileController.readKeyFromFile(Key_Path);
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
        FileInputStream fileIn = new FileInputStream(file_loc);  
        FileOutputStream fileOut = new FileOutputStream(file_des);  
         
         CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher); 
     
         while ((i = fileIn.read(data)) != -1) { 
            cipherOut.write(data, 0, i);
        }
        
      
         cipherOut.close();
         fileIn.close();
         
         System.out.println("encryption file Created");
        }
   
   
    public static void Decrypt (String file_loc, String file_des, String Key_Path) throws Exception { 
    
        File file2 = new File(file_loc);
        byte[] data = new byte[(int)file2.length()];
        int i ;
        
        System.out.println ("Start RSA Decryption");
        
        Key priKey = FileController.readKeyFromFile(Key_Path);
        Cipher cipher = Cipher.getInstance ("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, priKey);  
        
        FileInputStream fileIn = new FileInputStream(file_loc);  
        CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher); 
        FileOutputStream fileOut = new FileOutputStream(file_des);  
         
         while ((i = cipherIn.read()) != -1) { 
            fileOut.write(i);
        }
        
         
         fileIn.close();
         cipherIn.close();
         fileOut.close();
       
         
         System.out.println("decryption file crated");
        }
}
