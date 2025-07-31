package com.junlevelup.mreview.service;

import com.junlevelup.mreview.domain.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MoviceServiceTest {
  @Autowired
  private MovieService movieService;

  @Test
  public void testList(){
    movieService.getlist(PageRequestDTO.builder().build()).getList().forEach(log::info);
  }
}
