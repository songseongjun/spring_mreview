package com.junlevelup.mreview.llb;


import net.coobird.thumbnailator.Thumbnails;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@SpringBootTest
public class ThumbnailTest {
  @Test
  public void testConvert() throws IOException{
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\test","cute_cat_final.webp"));

    BufferedImage thumbnail = Thumbnails.of(originalImage)
            .size(200,200)
            .asBufferedImage();
    ImageIO.write(thumbnail,"webp",new File("C:\\Users\\tj\\Desktop\\test","cute_cat_final_output.webp"));
  }

  // 원본 이미지의 해상도 유지. 포맷만 webp로 컴버트
  @Test
  public void testUpload() throws IOException{
    BufferedImage originalImage = ImageIO.read(new File("C:\\Users\\tj\\Desktop\\test","cute_cat_final.webp"));
    ImageIO.write(originalImage,"webp",new File("C:\\Users\\tj\\Desktop\\test","cute_cat_final_해상도고정.webp"));
  }

}

