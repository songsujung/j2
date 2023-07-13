package org.zerock.j2.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Component
@Log4j2
public class FileUploader {

    public static class UploadException extends RuntimeException{

        public UploadException(String msg) {
            super(msg);
        }
    }

    @Value("${org.zerock.upload.path}")
    private String path;

    public void removeFiles(List<String> fileNames) {
        if(fileNames == null || fileNames.size() == 0){
            return;
        }

        for ( String fname : fileNames) {

            File original = new File(path, fname);
            File thumb = new File(path, "s_" + fname);

            if(thumb.exists()) {
                thumb.delete();
            }
            original.delete();
        }
    }

    public List<String> uploadFiles(List<MultipartFile> files, boolean makeThumbnail){

            // 파일이 존재하지 않을 떄
            if(files == null || files.size() ==0){
                throw new UploadException("No File");
            }
            
            List<String> uploadFileNames = new ArrayList<>();

            log.info("path: " + path);

            log.info(files);

            // loop 파일 하나씩 하나씩 세이브
            for (MultipartFile mFile : files) {
                
                // 오리지날 파일이름 얻기
                String originalFileName = mFile.getOriginalFilename();

                // uuid
                String uuid = UUID.randomUUID().toString();

                // 파일의 이름은 = uuid+_+오리지날파일네임 - 나중에 썸네일만들때 편하게 만들려고 따로 변수로 빼놓은 것
                String saveFileName = uuid+"_"+originalFileName;

                // 새로운 파일 생성
                File saveFile = new File(path, saveFileName);

                // 자동으로 close 됨 try_resource
                try ( InputStream in = mFile.getInputStream();
                    OutputStream out = new FileOutputStream(saveFile);
                ) {
                    // 원본 파일 카피
                    FileCopyUtils.copy(in, out);

                    if(makeThumbnail){

                    // out파일 생성
                    File thumbOutFile = new File(path, "s_"+saveFileName);

                    // 썸네일 생성
                    Thumbnailator.createThumbnail(saveFile, thumbOutFile, 200, 200);

                    }//end if

                    // 파일 추가
                    uploadFileNames.add(saveFileName);

                } catch (Exception e) {
                    // 오류나면 UploadException 에러메세지
                    throw new UploadException("Upload Fail: " + e.getMessage());
                }
            }

            return uploadFileNames;
            
        }
    
}
