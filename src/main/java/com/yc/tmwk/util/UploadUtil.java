package com.yc.tmwk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class UploadUtil {

    public static final String UPLOAD_PATH = "d:/upload";
    private static final Logger log = LoggerFactory.getLogger(UploadUtil.class);


    public static String upload(MultipartFile file){
        String path = "/record";
        String tempPath = "/"+System.currentTimeMillis()+"/";
        String fileName = path + tempPath + file.getOriginalFilename();
        String fileFolder =UPLOAD_PATH + path + tempPath;
        int size = (int) file.getSize();

        File folder = new File(fileFolder);
        File dest = new File(UPLOAD_PATH + fileName);
        if(!folder.exists()){ // Is parent directory is exist
            folder.mkdir();
        }
        try {
            file.transferTo(dest); //Save file to directory
            return fileName;
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }


    public static void download(String path, HttpServletResponse response){
        String filename= path.substring(path.lastIndexOf("/")+1,path.length());
        String filePath = "F:/test" ;
        File file = new File(UPLOAD_PATH + "/" + path);
        if(file.exists()){ //Is File exist
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //File input stream
            BufferedInputStream bis = null;

            OutputStream os = null; //out
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("----------file download" + filename);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
