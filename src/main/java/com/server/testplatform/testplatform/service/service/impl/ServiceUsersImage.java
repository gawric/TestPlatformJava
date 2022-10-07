package com.server.testplatform.testplatform.service.service.impl;

import com.server.testplatform.testplatform.model.UserModel;
import com.server.testplatform.testplatform.model.upload.UploadImageModel;
import com.server.testplatform.testplatform.service.db.inter.IServiceUserDb;
import com.server.testplatform.testplatform.service.service.inter.IServiceUsersImage;
import com.server.testplatform.testplatform.variable.ConstSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service("IServiceUsersImage")
public class ServiceUsersImage implements IServiceUsersImage {

    //работа с базой
    @Autowired
    private IServiceUserDb serviceUserDb;


    @Override
    public InputStream getImage(long id , HttpServletResponse response) {

        UploadImageModel uim = serviceUserDb.findUploadById(id);
        String ct = detectedImage(uim.getName());
        response.setContentType(ct);
        InputStream in = null;
        if(new File(uim.getPath()).exists()){
            try {
                in = new FileInputStream(uim.getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return  in;
        }
        return null;
    }

    private String detectedImage(String filename)
    {

        if(filename.indexOf(".png") > -1){
            return "image/png";
        }else if(filename.indexOf(".jpg") > -1){
            return "image/jpg";
        }else if(filename.indexOf(".jpeg") > -1){
            return "image/jpeg";
        }else if(filename.indexOf(".bmp") > -1){
            return "image/bmp";
        }
        else{
            return "";
        }
    }

    @Override
    public ResponseEntity<Object> findUploadById(long id , long user_id) {
            try
            {
                UploadImageModel uim = serviceUserDb.findUploadById(id);
                return  new ResponseEntity<>(uim, HttpStatus.OK);

            }
            catch(Exception ex){
                return new ResponseEntity<>("{\"status\" : \"error\"}", HttpStatus.NO_CONTENT);
            }

    }

    @Override
    public ResponseEntity<Object> getListUploadImg(long user_id) {
        try
        {
            List<UploadImageModel> luim = serviceUserDb.findById(user_id).getListUpload();
            return  new ResponseEntity<>(luim, HttpStatus.OK);

        }
        catch(Exception ex){
            return new ResponseEntity<>("{\"status\" : \"error\"}", HttpStatus.NO_CONTENT);
        }
    }


    @Override
    public ResponseEntity<Object> handleFileUpload(String name, MultipartFile file , long user_id) {

        if (!file.isEmpty()) {
            try
            {
                String absolutePath = writeToFs(name, file , ConstSetting.imgFolderDocker);
                UserModel user =  serviceUserDb.findById(user_id);
                UploadImageModel uim = saveBase(user,  absolutePath,  name,  file.getSize());
                UploadImageModel uimupdate = searchUploadToPath( user.getListUpload() ,  absolutePath);
                System.out.println("Вы удачно загрузили " + name + " в " + name + "-uploaded ! путь у файлу " + absolutePath);
                return  new ResponseEntity<>(uimupdate, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("{\"status\" : \"error\"}", HttpStatus.NO_CONTENT);
            }
        } else {
            return new ResponseEntity<>("{\"status\" : \"error\"}", HttpStatus.NO_CONTENT);
        }
    }
    private UploadImageModel saveBase(UserModel user, String path , String filename , long size){

        UploadImageModel uim = createModel( filename ,  path , size);

       if(!serviceUserDb.findUploadByPath(path)){
           List<UploadImageModel> list = user.getListUpload();
           if(list == null) list = new ArrayList();
           uim.setUser(user);
           list.add(uim);
           user.setListUpload(list);
           serviceUserDb.saveUser(user);
       }
       else{
           System.out.println("ServiceUsersImage>saveBase: Запись в базу не возможно такой файл уже был записан. Но мы перезаписали сам файл на сервере!!");
       }

        return uim;
    }

    private UploadImageModel searchUploadToPath(List<UploadImageModel> list , String path){
        return list.stream()
                .filter(x -> x.getPath().equals(path))
                .findFirst()
                .get();
    }

    private UploadImageModel createModel(String filename , String path , long size){
        UploadImageModel uim = new UploadImageModel();
        uim.setName(filename);
        uim.setPath(path);
        uim.setSize(size);
        uim.setCreatedata(LocalDateTime.now());

        return uim;
    }
    private String writeToFs(String name, MultipartFile file , String fsPath) throws IOException {
        byte[] bytes = file.getBytes();
       // File fileOut = new File(name);
        System.out.println(fsPath + name);
        String absolutepath = fsPath + name;
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(absolutepath));
        stream.write(bytes);
        stream.close();

        return absolutepath;
    }
}
