package com.junlevelup.mreview.service;

import com.junlevelup.mreview.domain.dto.MovieDTO;
import com.junlevelup.mreview.domain.dto.PageRequestDTO;
import com.junlevelup.mreview.domain.dto.PageResponseDTO;
import com.junlevelup.mreview.domain.entity.Movie;
import com.junlevelup.mreview.domain.entity.MovieImage;
import com.junlevelup.mreview.repository.MovieImageRepository;
import com.junlevelup.mreview.repository.MovieRepository;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Data
public non-sealed class MovieServiceImpl implements MovieService {
  private final MovieRepository movieRepository;
  private final MovieImageRepository movieImageRepository;


  @Override
  @Transactional
  public Long register(MovieDTO dto) {
    Map<String,Object> map =toEntity(dto);
    Movie movie = (Movie) map.get("movie");
    movieRepository.save(movie);
    List<MovieImage> list = ((List<MovieImage>)map.get("images"));
//    list.forEach(image -> image.setMovie(movie));
    list.forEach(movieImageRepository::save);
    return movie.getMno();
  }

  @Override
  public PageResponseDTO<MovieDTO, Object[]> getlist(PageRequestDTO dto) {
    return new PageResponseDTO<>(movieRepository.getListPage(dto.getPageable(Sort.by(Sort.Direction.DESC,"mno"))),
            arr -> toDTO((Movie) arr[0],(List<MovieImage>)(Arrays.asList((MovieImage)arr[1])),(Double)arr[2],(Long)arr[3]));

  }
}
