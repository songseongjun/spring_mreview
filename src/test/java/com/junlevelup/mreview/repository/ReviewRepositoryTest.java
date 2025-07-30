package com.junlevelup.mreview.repository;


import com.junlevelup.mreview.domain.entity.Member;
import com.junlevelup.mreview.domain.entity.Movie;
import com.junlevelup.mreview.domain.entity.Review;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class ReviewRepositoryTest {
  @Autowired
    ReviewRepository reviewRepository;
  @Test
  public void testExist(){
    Assertions.assertNotNull(reviewRepository);
  }

  @Test
  @Transactional
  public void insertReviews(){
    IntStream.rangeClosed(1,200).forEach(i->{
      Long mno = new Random().nextLong(100) + 1;
      Long mid = new Random().nextLong(100) + 1;

      Member member = Member.builder().mid(mid).build();
      Movie movie = Movie.builder().mno(mno).build();

      reviewRepository.save(Review.builder()
                      .member(member)
                      .movie(movie)
                      .grade(new Random().nextInt(5)+1)
                      .text("이 영화애 대한 느낌" +i)
                      .build());
    });
  }

  @Test
//  @Transactional(readOnly = true)
  public void testFindByMovie_Mno(){
    reviewRepository.findByMovie_mno(98L).forEach(r -> {
      log.info(r);// n+1이라서 리뷰가 2개있으면 3개가나오는것 3개있으면 4개가나옴
      log.info(r.getMember().getEmail());
//      log.info(r.getMovie().getTitle());
    });
  }


}
