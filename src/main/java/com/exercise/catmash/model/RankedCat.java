package com.exercise.catmash.model;

import lombok.Data;

@Data
public class RankedCat {
  private String id;
  private String url;
  private Integer rank = 1000;

}
