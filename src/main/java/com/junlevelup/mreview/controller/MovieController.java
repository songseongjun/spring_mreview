package com.junlevelup.mreview.controller;


import com.junlevelup.mreview.domain.dto.MovieDTO;
import com.junlevelup.mreview.domain.dto.PageRequestDTO;
import com.junlevelup.mreview.service.MovieService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("movie")
@Data
public class MovieController {
  private final MovieService movieService;

  @GetMapping("register")
  public void  register(){

  }
  @PostMapping("register")
  public String  register(MovieDTO movieDTO, RedirectAttributes redirectAttributes){
  redirectAttributes.addAttribute("msg" , movieService.register(movieDTO));
    return "redirect:/movie/register";
  }

  @GetMapping("list")
  public void  list(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO, Model model){
    model.addAttribute("movies",movieService.getlist(pageRequestDTO));
  }
}

