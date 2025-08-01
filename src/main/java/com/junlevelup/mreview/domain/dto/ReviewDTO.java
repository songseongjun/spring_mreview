package com.junlevelup.mreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
  private Long reviewnum;
  private Long mno;
  private Long mid;
  private String nickname;
  private  String email;
  private  int grade;
  private String text;
  private LocalDateTime regDate,modDate;
}
