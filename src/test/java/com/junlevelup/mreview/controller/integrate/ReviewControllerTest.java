package com.junlevelup.mreview.controller.integrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.junlevelup.mreview.domain.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testExist() {
    log.info(mockMvc);
    log.info(objectMapper);
  }

  @Test
  public void  testList() throws Exception {
    Long mno = 100L;
   mockMvc.perform(get(String.format("/review/%d/all",mno)))
           .andExpect(status().is(200))
           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
           .andExpect(jsonPath("$[0].mno").value(mno))
           .andExpect(jsonPath("$[0].records").doesNotExist());
  }

  @Test
  public void testCreate() throws Exception {
    ReviewDTO dto = ReviewDTO.builder()
            .mno(100L)
            .mid(63L)
            .grade(5)
            .text("통합테스트리뷰다인")
            .build();
    MvcResult result = mockMvc.perform(post(String.format("/review/%d",100))
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto))
    )
            .andExpect(status().is(200))
            .andReturn();

    String resultStr = result.getResponse().getContentAsString();
    log.info(resultStr);
  }
}
