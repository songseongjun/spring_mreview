package com.junlevelup.mreview.controller;

import com.junlevelup.mreview.domain.dto.UploadResultDTO;
import jakarta.servlet.http.Part;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Log4j2
@Controller
public class UploadController {
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;

  public static final String UPLOAD_PATH = "d:/upload/files";


  @PostMapping("uploadAjax")

  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files) throws IOException {
    List<UploadResultDTO> list = new ArrayList<>();
    for (MultipartFile f : files) {
      if (!f.getContentType().startsWith("image/")) {
        log.warn(f.getContentType() + "is not supported");
        return ResponseEntity.badRequest().body("잘못된 파일 형식입니다.");
      }
      String uuid = UUID.randomUUID().toString();
      String path = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
      String savePath = uploadPath + "/" + path;

            //원본 변경 to webp
      new File(savePath).mkdirs();
      String origin = f.getOriginalFilename().substring(0,f.getOriginalFilename().lastIndexOf(".")) +".webp";
      String saveName = uuid + "_" + origin;// 위에서 처리
      ImageIO.write(ImageIO.read(f.getInputStream()),"webp",new File(savePath,saveName));

      //섬네일 생성
      String thumbName = "s_" + saveName;
      BufferedImage thumbnail = Thumbnails.of(ImageIO.read(f.getInputStream()))
              .size(200,200)
              .asBufferedImage();
      ImageIO.write(thumbnail,"webp",new File(savePath,thumbName));
      list.add(UploadResultDTO.builder().origin(origin).uuid(uuid).path(path).build());

      //응답 목록에추가
    }
    return ResponseEntity.ok(list);
  }
  @GetMapping("display")
  public ResponseEntity<?> display(UploadResultDTO dto) throws IOException {
    File file = new File(uploadPath + "/" + dto.getPath(), dto.getUuid()+"_" + dto.getOrigin());
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", Files.probeContentType(file.toPath()));
    return ResponseEntity.ok().headers(headers).body(Files.readAllBytes(file.toPath()));
  }

  @GetMapping("uploadEx")
  public void uploadEx() {

  }

}