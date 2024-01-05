package com.udacity.SuperDuperDriver.SuperDuperDriver.service;

import com.udacity.SuperDuperDriver.SuperDuperDriver.mapper.FileMapper;
import com.udacity.SuperDuperDriver.SuperDuperDriver.model.File;

import java.io.IOException;
import java.util.List;

import com.udacity.SuperDuperDriver.SuperDuperDriver.model.FileForm;
import com.udacity.SuperDuperDriver.SuperDuperDriver.model.User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

        private UserService userService;
        private FileMapper fileMapper;

    public FileService(UserService userService, FileMapper fileMapper) {
        this.userService = userService;
        this.fileMapper = fileMapper;
    }

    public List<File> getAllFiles(Integer userId){
        try{
            return fileMapper.getFilesByUserId(userId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public File getFile(Integer fileId){
        try{
            return fileMapper.findById(fileId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public void addFile(FileForm file , Integer userId) throws IOException {
        MultipartFile multipartFile = file.getFile();
        File uploadeFile = new File(multipartFile , userId);

        try{
            fileMapper.insertFile(uploadeFile);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    public void deleteFile(Integer fileId)  {
        try{
            fileMapper.deleteFile(fileId);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

}
