package com.exercise.catmash.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RankingPolicyFactory {

  @Bean
  public RankingPolicy rankingPolicy() {
    return new SimpleRankingPolicy();
  }
}
