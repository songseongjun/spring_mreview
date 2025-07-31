package com.junlevelup.mreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.util.UriComponentsBuilder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieImageDTO {
  private String origin;
  private String uuid;
  private String path;
  private Long mno;

  public String getUrl(){
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
  builder.queryParam("origin", origin);
  builder.queryParam("uuid", uuid);
  builder.queryParam("path", path);
  return builder.build().toUriString();
  }

  public String getThumb(){
    UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
    builder.queryParam("origin", origin);
    builder.queryParam("uuid","s_" + uuid);
    builder.queryParam("path", path);
    return builder.build().toUriString();
  }
}
