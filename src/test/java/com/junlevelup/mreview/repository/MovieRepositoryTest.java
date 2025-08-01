package com.junlevelup.mreview.repository;

import com.junlevelup.mreview.domain.entity.Movie;
import com.junlevelup.mreview.domain.entity.MovieImage;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@Log4j2
@SpringBootTest
public class MovieRepositoryTest {


  @Autowired
  private MovieRepository movieRepository;

  @Autowired
  private MovieImageRepository imageRepository;

  @Commit
  @Transactional
  @Test
  public void testinsertMovies(){
    IntStream.rangeClosed(1,100).forEach(i->{
      Movie movie = Movie.builder().title("Movie...."+i).build();
      System.out.println("--------------------");
      movieRepository.save(movie);

      int count =(int)(Math.random()*5) +1;
      for (int j=0; j<count; j++){
        MovieImage movieImage = MovieImage.builder()
                .uuid(UUID.randomUUID().toString())
                .movie(movie)
                .imgName("test" + i + ".jpg").build();
        imageRepository.save(movieImage);
        System.out.println("------------------------");
      }
    });
  }

  @Test
  public void testGetListPage(){
    movieRepository.getListPage(PageRequest.of(0,10,Sort.Direction.DESC,"mno")).forEach(m ->log.info(Arrays.toString(m)));

//    Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC,"mno"));
//    Page<Object[]> moviePage = movieRepository.getListPage(pageable);
//    for (Object[] objects : moviePage.getContent()) {
//      System.out.println(Arrays.toString(objects));
//    }

  }
  @Test
  public void testGetMovieWithAll(){
    movieRepository.getMovieWithAll(103L).forEach(m ->log.info(Arrays.toString(m)));
  }

}
