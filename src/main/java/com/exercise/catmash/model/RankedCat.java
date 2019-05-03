package com.exercise.catmash.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RankedCat {
  private String id;
  private String url;
  private Integer rank = 1000;

}
