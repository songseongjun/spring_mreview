package com.junlevelup.mreview.domain.dto;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<DTO, Entity> {
  private List<DTO> list;

  private  int totalPage,page,size,start,end;
  private  boolean prev,next;

  private List<Integer> pageList;

  private String type;
  private String keyword;

          //fuction은 엔티티타입(T)을 리턴타입인 DTO로변화해준다는것
  public PageResponseDTO(Page<Entity> page, Function<Entity, DTO> mapper) {
    list = page.stream().map(mapper).toList();
    totalPage = page.getTotalPages();
    makePageList(page.getPageable());
  }

  private void makePageList(Pageable pageable) {
    final int PAGE_VIEW_COUNT = 5;

    page = pageable.getPageNumber() + 1;
    size = pageable.getPageSize();

    int tempEnd = (int)(Math.ceil(page / 1d / PAGE_VIEW_COUNT)) * PAGE_VIEW_COUNT;
    start = tempEnd - (PAGE_VIEW_COUNT - 1);
    prev = start > 1 ;
    end = totalPage > tempEnd ? tempEnd : totalPage;
    next = totalPage > tempEnd;

    pageList = IntStream.rangeClosed(start,end).boxed().toList();
  }
}
