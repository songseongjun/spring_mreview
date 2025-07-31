package com.junlevelup.mreview.service;

import com.junlevelup.mreview.domain.dto.MovieDTO;
import com.junlevelup.mreview.domain.dto.MovieImageDTO;
import com.junlevelup.mreview.domain.entity.Movie;
import com.junlevelup.mreview.domain.entity.MovieImage;

public interface MovieImageService {
  static MovieImage toEntity(MovieImageDTO dto) {
    return MovieImage.builder()
            .movie(Movie.builder().mno(dto.getMno()).build())
            .uuid(dto.getUuid())
            .path(dto.getPath())
            .imgName(dto.getOrigin())
            .build();
  }
}
