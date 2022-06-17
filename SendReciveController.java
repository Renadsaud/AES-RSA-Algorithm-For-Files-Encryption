/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg_is;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author razan
 */
public class SendReciveController {

    public void sendFile(File file, Socket socket) throws FileNotFoundException, IOException {

        FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());

        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        String fileName = file.getName();
        byte[] fileNameBytes = fileName.getBytes();

        dataOutputStream.writeInt(fileNameBytes.length);

        dataOutputStream.write(fileNameBytes);

        byte[] fileContentBytes = new byte[(int) file.length()];

        fileInputStream.read(fileContentBytes);

        dataOutputStream.writeInt(fileContentBytes.length);

        dataOutputStream.write(fileContentBytes);

    }

    public File reciveFile(Socket socket) throws IOException {

        System.out.println("reciveFile method start ..");

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

        int fileNameLength = dataInputStream.readInt();

        if (fileNameLength > 0) {

            byte[] fileNameBytes = new byte[fileNameLength];
            dataInputStream.readFully(fileNameBytes, 0, fileNameLength);
            String fileName = new String(fileNameBytes);

            System.out.println("fileName : " + fileName);

            int fileContentLength = dataInputStream.readInt();

            if (fileContentLength > 0) {

                byte[] fileContentBytes = new byte[fileContentLength];
                dataInputStream.readFully(fileContentBytes, 0, fileContentLength);
//            String fileContent = new String(fileContentBytes);

                System.out.println("getFileExtention : " + getFileExtention(fileName));
                //------------PATH TO DOWNLOAD-------------->
                String pathToDownload = "C:\\Users\\razan\\Desktop\\";

                File file = fileDownloader(pathToDownload, fileName, fileContentBytes);

                return file;

            }
        }

        return new File("");

    }

    public String getFileExtention(String filename) {

        int i = filename.indexOf('.');

        if (i > 0) {
            return filename.substring(i + 1);
        } else {
            return "no extention found.";
        }

    }

    public File fileDownloader(String downloadPath, String fileName, byte[] fileContentBytes) throws FileNotFoundException, IOException {

        File fileToDownload = new File(downloadPath + fileName);

        FileOutputStream fileOutputStream = new FileOutputStream(fileToDownload);

        fileOutputStream.write(fileContentBytes);

        fileOutputStream.close();

        return fileToDownload;

    }

}
