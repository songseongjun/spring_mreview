package com.junlevelup.mreview.controller.unit;

import com.junlevelup.mreview.controller.ReviewController;
import com.junlevelup.mreview.repository.ReviewRepository;
import com.junlevelup.mreview.service.ReviewService;
import com.junlevelup.mreview.service.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(ReviewController.class)
@ContextConfiguration(name = "application.yaml")
public class ReviewControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReviewServiceImpl service;
  @MockBean
  private ReviewRepository repository;

  @Test
  public void 단순_목록_조회() throws Exception {
    Long mno = 100L;
    mockMvc.perform(MockMvcRequestBuilders.get(String.format("/review/%d/all",mno)));
  }
}
