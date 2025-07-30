package com.junlevelup.mreview.repository;

import com.junlevelup.mreview.domain.entity.Member;
import com.junlevelup.mreview.domain.entity.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.LongStream;

@SpringBootTest
public class MemberRepositoryTest {

  @Autowired
  private MemberReposotory memberReposotory;
  private ReviewRepository reviewRepository;

  @Test
  public void testExist(){
    Assertions.assertNotNull(memberReposotory);
  }
  @Test
  public void insertMember(){
    LongStream.rangeClosed(1,100).forEach(i->{
      Member member = Member.builder()
              .email("r" + i +"@gmail.com")
              .pw("1111")
              .nickname("reviewer"+i).build();
      memberReposotory.save(member);
    });
  }
  @Test
  @Transactional
  @Commit
  public void testDeleteByMemberMid(){
    Long mid = 20L;
//    reviewRepository.deleteByMember_mid(mid);
    memberReposotory.deleteById(mid);
  }
}
