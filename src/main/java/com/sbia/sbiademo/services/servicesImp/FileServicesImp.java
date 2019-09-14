package com.sbia.sbiademo.services.servicesImp;

import com.sbia.sbiademo.services.services.FileServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@PropertySource("classpath:application.properties")
public class FileServicesImp implements FileServices {
    @Value("${file.upload}")
    String path;

    /*
     * 保存文件，并返回相对于主机地址的url
     */
    @Override
    public String saveFile(MultipartFile multipartFile) {
        String fileName=multipartFile.getOriginalFilename();
        File dir=new File(path);
        try{
            if(!dir.exists()){
                dir.mkdirs();
            }
            //文件保存到磁盘
            multipartFile.transferTo(new File(dir+"/"+fileName));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return multipartFile.getOriginalFilename();
    }
}
