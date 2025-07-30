package com.junlevelup.mreview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Controller
@Log4j2
public class UploadController {
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;

  @PostMapping("uploadAjax")
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files) {
    return ResponseEntity.ok(Arrays.stream(files).map(f -> {
      String uuid = null;
      String folderPath = null;
      try {
        //이미지만 업로드 가능
        if(!f.getContentType().startsWith("image/")){
      log.warn(f.getContentType() + "is not supported");
      return ResponseEntity.badRequest().build();
        }
        log.info(f.getOriginalFilename());
//        LocalDate now = LocalDate.now();
         folderPath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
         uuid = UUID.randomUUID().toString();
         folderPath = uploadPath + "/" + folderPath;
        String saveName = uuid + "_" + f.getOriginalFilename();

        new File(folderPath).mkdirs();
        //파일 저장

        f.transferTo(new File(folderPath,saveName));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return Map.of("fileName",f.getOriginalFilename(),"size", f.getSize(),"uuid",uuid,"path",folderPath);
    }).toList());

  }
  @GetMapping("uploadEx")
  public void uploadEx() {}
}