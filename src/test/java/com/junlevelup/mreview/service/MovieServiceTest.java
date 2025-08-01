package com.junlevelup.mreview.service;


import com.junlevelup.mreview.domain.dto.PageRequestDTO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
public class MovieServiceTest {
  @Autowired
  private MovieService movieService;

  @Test
  public void testExist () {
    movieService.getlist(PageRequestDTO.builder().build()).getList().forEach(log::info);

  }

  @Test
  public void testGet(){
    Long mno = 103L;
    log.info(movieService.get(mno));
  }


}