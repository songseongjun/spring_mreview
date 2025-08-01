package com.junlevelup.mreview.service;

import com.junlevelup.mreview.domain.dto.ReviewDTO;
import com.junlevelup.mreview.domain.entity.Review;
import com.junlevelup.mreview.repository.ReviewRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public non-sealed class ReviewServiceImpl implements ReviewService{

  private final ReviewRepository repository;

  @Override
  public List<ReviewDTO> getListWithMovie(Long mno) {
    return repository.findByMovie_mno(mno).stream().map(this::toDTO).toList();
  }

  @Override
  public Long register(ReviewDTO dto) {
    return repository.save(toEntity(dto)).getReviewnum();
  }

  @Override
  public void modify(ReviewDTO dto) {
    Review review = repository.findById(dto.getReviewnum()).orElseThrow(() -> new IllegalArgumentException("Review Not Found"));
    review.setGrade(dto.getGrade());
    review.setText(dto.getText());
  }

  @Override
  public void remove(Long reviewnum) {
    repository.deleteById(reviewnum);
  }
}